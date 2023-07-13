package io.github.olivoz.extendedconfigs.mixin.quark;

import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import vazkii.quark.world.entity.EntityFoxhound;

@Mixin(value = EntityFoxhound.class)
public final class MixinEntityFoxhound {

    @Unique
    private static final int DEFAULT_COOK_TIME_INCREASE = 1;

    @ModifyConstant(method = "onLivingUpdate",
            constant = @Constant(intValue = DEFAULT_COOK_TIME_INCREASE),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityFurnace;getField(I)I"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityFurnace;setField(II)V")
            )
    )
    private static int extendedConfigsMixinFurnaceSpeedUp(int constant) {
        return Config.QUARK.foxHound.furnaceCookTimeIncrease;
    }

}
