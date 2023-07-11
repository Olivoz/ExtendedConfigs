package io.github.olivoz.extendedconfigs.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySilo;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntitySilo.class, remap = false)
public final class MixinTileEntitySilo {

    @Unique
    private static final int DEFAULT_MAX_STORAGE = 41472;

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = DEFAULT_MAX_STORAGE))
    private static int extendedConfigsMixinMaxStorage(int constant) {
        return Config.IMMERSIVE_ENGINEERING.silo.maxStorage;
    }
}
