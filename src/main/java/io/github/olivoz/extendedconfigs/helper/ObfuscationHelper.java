package io.github.olivoz.extendedconfigs.helper;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

public final class ObfuscationHelper {

    private ObfuscationHelper() {
    }

    public static String remapFieldName(String internalName, String srgName, String desc) {
        if (!FMLLaunchHandler.isDeobfuscatedEnvironment()) return srgName;
        String internalClassName = FMLDeobfuscatingRemapper.INSTANCE.unmap(internalName);
        return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(internalClassName, srgName, desc);
    }

    public static String remapMethodName(String internalName, String srgName, String desc) {
        if (!FMLLaunchHandler.isDeobfuscatedEnvironment()) return srgName;
        String internalClassName = FMLDeobfuscatingRemapper.INSTANCE.unmap(internalName);
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(internalClassName, srgName, desc);
    }

}
