package io.github.olivoz.extendedconfigs.mixin.excompressum;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.blay09.mods.excompressum.tile.TileAutoSieve;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = TileAutoSieve.class, remap = false)
public final class MixinTileAutoSieve {

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/blay09/mods/excompressum/tile/TileAutoSieve$1;<init>(Lnet/blay09/mods/excompressum/tile/TileAutoSieve;I)V"))
    public void extendedConfigsConstructEnergyStorageModifiable(Args args) {
        args.set(1, Config.EX_COMPRESSUM.autoSieve.capacity);
    }

}
