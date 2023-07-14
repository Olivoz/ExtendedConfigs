package io.github.olivoz.extendedconfigs.mixin.requious;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import requious.tile.TileEntityAssembly;

@Mixin(value = TileEntityAssembly.class, remap = false)
public abstract class MixinTileEntityAssembly extends TileEntity {

    @Inject(method = "hasCapability", at = @At("HEAD"), cancellable = true)
    public void extendedConfigsMixinHasCapability(Capability<?> capability, EnumFacing facing, CallbackInfoReturnable<Boolean> cir) {
        if (Config.REQUIOUS_FRAKTO.assembler.fixBreakingCrash && getWorld().isAirBlock(getPos()))
            cir.setReturnValue(false);
    }

}
