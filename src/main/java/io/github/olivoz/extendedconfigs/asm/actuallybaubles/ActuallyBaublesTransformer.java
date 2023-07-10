package io.github.olivoz.extendedconfigs.asm.actuallybaubles;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.util.Constants;

public final class ActuallyBaublesTransformer implements IClassTransformer {

    private static final String TARGET_NAME = "me.jacky1356400.actuallybaubles.ActuallyBaubles";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!name.equals(TARGET_NAME)) return basicClass;

        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classReader.accept(new TransformClassVisitor(classWriter), 0);

        return classWriter.toByteArray();
    }

    static final class TransformClassVisitor extends ClassVisitor {

        public TransformClassVisitor(final ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (!name.equals(Constants.CLINIT)) return mv;
            return new BatteryTransformer(mv);
        }
    }

    static final class BatteryTransformer extends MethodVisitor {

        String batteryName = null;
        boolean isCapacity = true;

        public BatteryTransformer(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitLdcInsn(Object cst) {
            if (cst instanceof String) {
                String s = (String) cst;
                if (s.startsWith("battery_")) {
                    batteryName = s;
                    isCapacity = true;
                }
            }

            if (batteryName != null && cst instanceof Integer) {
                if (isCapacity) {
                    isCapacity = false;

                    Integer newValue = Config.ACTUALLY_BAUBLES.capacities.getOrDefault(batteryName, (Integer) cst);
                    insertValue(newValue);
                    return;
                }

                Integer newValue = Config.ACTUALLY_BAUBLES.transferRates.getOrDefault(batteryName, (Integer) cst);
                insertValue(newValue);
                return;
            }

            super.visitLdcInsn(cst);
        }

        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (batteryName != null && opcode != Opcodes.NEWARRAY) {
                if (isCapacity) {
                    isCapacity = false;

                    Integer newValue = Config.ACTUALLY_BAUBLES.capacities.getOrDefault(batteryName, operand);
                    insertValue(newValue);
                    return;
                }

                Integer newValue = Config.ACTUALLY_BAUBLES.transferRates.getOrDefault(batteryName, operand);
                insertValue(newValue);
                return;
            }

            super.visitIntInsn(opcode, operand);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            super.visitFieldInsn(opcode, owner, name, desc);
            if (batteryName == null) return;
            batteryName = null;
            isCapacity = true;
        }

        private void insertValue(Integer newValue) {
            if (newValue <= Byte.MAX_VALUE) super.visitIntInsn(Opcodes.BIPUSH, newValue);
            else if (newValue <= Short.MAX_VALUE) super.visitIntInsn(Opcodes.SIPUSH, newValue);
            else super.visitLdcInsn(newValue);
        }

    }
}
