package io.github.olivoz.extendedconfigs.mixin.actuallyadditions;

import de.ellpeck.actuallyadditions.mod.tile.CustomEnergyStorage;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityDisplayStand;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityDisplayStand.class, remap = false)
public abstract class MixinTileEntityDisplayStand {

    @Mutable
    @Final
    @Shadow
    public CustomEnergyStorage storage = new CustomEnergyStorage(Config.ACTUALLY_ADDITIONS.displayStand.capacity, Config.ACTUALLY_ADDITIONS.displayStand.maxReceive, Config.ACTUALLY_ADDITIONS.displayStand.maxExtract);


}
