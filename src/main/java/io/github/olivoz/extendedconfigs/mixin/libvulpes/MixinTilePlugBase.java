package io.github.olivoz.extendedconfigs.mixin.libvulpes;

import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import zmaster587.libVulpes.tile.energy.TilePlugBase;

@Mixin(value = TilePlugBase.class, remap = false)
public final class MixinTilePlugBase {

    @Unique
    private static final int DEFAULT_DRAIN_MULTIPLIER = 250;

    @Unique
    private static final int DEFAULT_MAX_ENERGY_MULTIPLIER = 10000;

    @ModifyVariable(method = "getMaxEnergy", at = @At("HEAD"), argsOnly = true)
    private int extendedConfigsMixinGetMaxEnergyCap(int tier) {
        int cap = Config.LIB_VULPES.plugBase.getMaxEnergyTierCap;
        if (cap < 0) return tier;
        return Math.min(tier, cap);
    }

    @ModifyConstant(method = "getMaxEnergy", constant = @Constant(intValue = DEFAULT_MAX_ENERGY_MULTIPLIER))
    private int extendedConfigsMixinGetMaxEnergy(int constant) {
        return Config.LIB_VULPES.plugBase.getMaxEnergyModifier;
    }

    @ModifyConstant(method = "getMaxDrainRate", constant = @Constant(intValue = DEFAULT_DRAIN_MULTIPLIER))
    private int extendedConfigsMixinGetMaxDrainRate(int constant) {
        return Config.LIB_VULPES.plugBase.getMaxDrainRateModifier;
    }

}
