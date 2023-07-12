package io.github.olivoz.extendedconfigs.mixin.enderio;

import crazypants.enderio.machines.integration.jei.TankRecipeCategory;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TankRecipeCategory.class, remap = false)
public final class MixinTankRecipeCategory {

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Ljava/lang/Boolean;booleanValue()Z", ordinal = 0))
    private static boolean extendedConfigsMixinRegister(Boolean instance) {
        return instance || Config.ENDER_IO.tank.fixMissingRecipes;
    }

}
