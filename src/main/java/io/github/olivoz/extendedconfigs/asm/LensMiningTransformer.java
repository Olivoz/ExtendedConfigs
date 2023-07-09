package io.github.olivoz.extendedconfigs.asm;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraft.init.Blocks;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public final class LensMiningTransformer implements IClassTransformer {

    private static final String TARGET_NAME = "de.ellpeck.actuallyadditions.mod.items.lens.LensMining";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!name.equals(TARGET_NAME)) return basicClass;

        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classReader.accept(new TransformClassVisitor(classWriter), 0);

        return classWriter.toByteArray();
    }

    static final class TransformClassVisitor extends ClassVisitor {

        public TransformClassVisitor(final ClassWriter cw) {
            super(Opcodes.ASM5, cw);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            switch (name) {
                case "init":
                    if (Config.ACTUALLY_ADDITIONS.lens.removeHardcodedOres) return new TransformInitMethod(mv);
                    break;

                case "invoke":
                    if (Config.ACTUALLY_ADDITIONS.lens.useEndstone) return new TransformInvokeMethod(mv);
                    break;

                default:
                    return mv;
            }

            return mv;
        }
    }

    static final class TransformInitMethod extends MethodVisitor {
        private boolean hasPassedHardCodedWeights = false;

        public TransformInitMethod(final MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            if (name.equals("MINING_LENS_EXTRA_WHITELIST")) hasPassedHardCodedWeights = true;
            if (hasPassedHardCodedWeights) super.visitFieldInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (hasPassedHardCodedWeights) super.visitMethodInsn(opcode, owner, name, desc, itf);
        }

        @Override
        public void visitLdcInsn(Object cst) {
            if (hasPassedHardCodedWeights) super.visitLdcInsn(cst);
        }

        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (hasPassedHardCodedWeights) super.visitIntInsn(opcode, operand);
        }

        @Override
        public void visitInsn(int opcode) {
            if (hasPassedHardCodedWeights) super.visitInsn(opcode);
        }
    }

    static final class TransformInvokeMethod extends MethodVisitor {

        private static final int ADAPTED_USE_VAR_ID = 4;
        private static final int ORE_VAR_ID = 5;

        boolean cancelAstore = false;
        boolean replaceIfeq = false;

        public TransformInvokeMethod(final MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            super.visitFieldInsn(opcode, owner, name, desc);
            if (!name.equals("STONE_ORES")) return;
            cancelAstore = true;
            super.visitVarInsn(Opcodes.ASTORE, ORE_VAR_ID);
            super.visitIincInsn(ADAPTED_USE_VAR_ID, 10000);
        }

        @Override
        public void visitVarInsn(int opcode, int varId) {
            if (!cancelAstore || opcode != Opcodes.ASTORE) {
                super.visitVarInsn(opcode, varId);
                return;
            }

            cancelAstore = false;
        }

        @Override
        public void visitIincInsn(int varId, int increment) {
            if (varId != ADAPTED_USE_VAR_ID) super.visitIincInsn(varId, increment);
        }

        @Override
        public void visitTypeInsn(int opcode, String type) {
            if (opcode != Opcodes.INSTANCEOF || !type.equals("net/minecraft/block/BlockStone")) {
                super.visitTypeInsn(opcode, type);
                return;
            }

            Type blockType = Type.getType(Blocks.class);
            replaceIfeq = true;
            super.visitFieldInsn(Opcodes.GETSTATIC, blockType.getInternalName(), ObfuscationReflectionHelper.findField(Blocks.class, "field_150377_bs").getName(), "Lnet/minecraft/block/Block;");
        }

        @Override
        public void visitJumpInsn(int opcode, Label label) {
            if (opcode != Opcodes.IFEQ || !replaceIfeq) {
                super.visitJumpInsn(opcode, label);
                return;
            }

            super.visitJumpInsn(Opcodes.IF_ACMPNE, label);
            replaceIfeq = false;
        }
    }
}
