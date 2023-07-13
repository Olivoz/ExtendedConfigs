package io.github.olivoz.extendedconfigs.asm.randomthings;

import io.github.olivoz.extendedconfigs.configs.Config;
import io.github.olivoz.extendedconfigs.helper.ObfuscationHelper;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class EntityGoldenChickenTransformer implements IClassTransformer {

    private static final String TARGET_NAME = "lumien.randomthings.entitys.EntityGoldenChicken";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!name.equals(TARGET_NAME) || Config.RANDOM_THINGS.goldenChicken.minDropTimer == 0) return basicClass;

        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classReader.accept(new TransformClassVisitor(classWriter), 0);

        return classWriter.toByteArray();
    }

    static final class TransformClassVisitor extends ClassVisitor {

        private static final String TARGET_NAME = ObfuscationHelper.remapMethodName("net/minecraft/entity/EntityLivingBase", "func_70636_d", "()V");

        public TransformClassVisitor(final ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (!name.equals(TARGET_NAME)) return mv;
            return new DropTimeTransformer(mv);
        }
    }

    static final class DropTimeTransformer extends MethodVisitor {

        private boolean isFinished = false;
        private boolean isDropTimer = false;

        public DropTimeTransformer(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            super.visitFieldInsn(opcode, owner, name, desc);
            if (!isFinished) isDropTimer = opcode == Opcodes.PUTFIELD && name.equals("ingotDropTimer");
        }

        @Override
        public void visitJumpInsn(int opcode, Label label) {
            if (!isFinished && opcode == Opcodes.IFGT && isDropTimer) {
                isFinished = true;
                insertValue(Config.RANDOM_THINGS.goldenChicken.minDropTimer);
                super.visitJumpInsn(Opcodes.IF_ICMPGT, label);
                return;
            }

            super.visitJumpInsn(opcode, label);
        }

        private void insertValue(int newValue) {
            if (newValue <= Byte.MAX_VALUE) super.visitIntInsn(Opcodes.BIPUSH, newValue);
            else if (newValue <= Short.MAX_VALUE) super.visitIntInsn(Opcodes.SIPUSH, newValue);
            else super.visitLdcInsn(newValue);
        }

    }
}
