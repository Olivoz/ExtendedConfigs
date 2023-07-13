package io.github.olivoz.extendedconfigs.mixin.enderio;

import crazypants.enderio.machines.integration.jei.TankRecipeCategory;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(value = TankRecipeCategory.class, remap = false)
public final class MixinTankRecipeCategory {

    @Redirect(method = "register",
            at = @At(value = "INVOKE", target = "Ljava/lang/Boolean;booleanValue()Z"),
            slice = @Slice(to = @At(value = "INVOKE", target = "Lcrazypants/enderio/base/integration/jei/RecipeWrapperBase;setLevelData(Ljava/lang/Class;Lmezz/jei/api/IGuiHelper;IILjava/lang/String;Ljava/lang/String;)V"))
    )
    private static boolean extendedConfigsMixinRegister(Boolean instance) {
        return instance || Config.ENDER_IO.tank.fixMissingRecipes;
    }

}
