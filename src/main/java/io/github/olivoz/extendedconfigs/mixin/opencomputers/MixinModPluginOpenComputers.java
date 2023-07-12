package io.github.olivoz.extendedconfigs.mixin.opencomputers;

import io.github.olivoz.extendedconfigs.configs.Config;
import li.cil.oc.integration.jei.CallbackDocHandler;
import li.cil.oc.integration.jei.ManualUsageHandler;
import li.cil.oc.integration.jei.ModPluginOpenComputers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.Collection;

@Mixin(value = ModPluginOpenComputers.class, remap = false)
public final class MixinModPluginOpenComputers {

    @Unique
    private static final String MANUAL_UID = "oc.manual";
    @Unique
    private static final String DOC_UID = "oc.api";

    @Redirect(method = "registerCategories", at = @At(value = "INVOKE", target = "Lmezz/jei/api/recipe/IRecipeCategoryRegistration;addRecipeCategories([Lmezz/jei/api/recipe/IRecipeCategory;)V"))
    public void extendedConfigsMixinRegisterCategories(IRecipeCategoryRegistration registry, IRecipeCategory[] recipeCategories) {
        boolean hideManualPage = recipeCategories.length > 0 && recipeCategories[0] instanceof ManualUsageHandler.ManualUsageRecipe && Config.OPEN_COMPUTERS.jeiIntegration.removeOpenManualRecipe;
        boolean hideCallbackDocPage = recipeCategories.length > 0 && recipeCategories[0] instanceof CallbackDocHandler.CallbackDocRecipe && Config.OPEN_COMPUTERS.jeiIntegration.removeAPIDocRecipe;
        if (!hideManualPage && !hideCallbackDocPage) registry.addRecipeCategories(recipeCategories);
    }

    @Redirect(method = "register",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lli/cil/oc/integration/jei/CallbackDocHandler$CallbackDocRecipeCategory$;initialize(Lmezz/jei/api/IGuiHelper;)V")),
            at = @At(value = "INVOKE", target = "Lmezz/jei/api/IModRegistry;handleRecipes(Ljava/lang/Class;Lmezz/jei/api/recipe/IRecipeWrapperFactory;Ljava/lang/String;)V")
    )
    public <T> void extendedConfigsMixinHandleRecipes(IModRegistry registry, Class<T> recipeClass, IRecipeWrapperFactory<T> recipeWrapperFactory, String recipeCategoryUid) {
        boolean hideManualPage = recipeCategoryUid.equals(MANUAL_UID) && Config.OPEN_COMPUTERS.jeiIntegration.removeOpenManualRecipe;
        boolean hideCallbackDocPage = recipeCategoryUid.equals(DOC_UID) && Config.OPEN_COMPUTERS.jeiIntegration.removeAPIDocRecipe;
        if (!hideManualPage && !hideCallbackDocPage)
            registry.handleRecipes(recipeClass, recipeWrapperFactory, recipeCategoryUid);
    }

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lmezz/jei/api/IModRegistry;addRecipes(Ljava/util/Collection;Ljava/lang/String;)V"))
    public void extendedConfigsMixinAddRecipes(IModRegistry registry, Collection<?> recipes, String recipeCategoryUid) {
        boolean hideManualPage = recipeCategoryUid.equals(MANUAL_UID) && Config.OPEN_COMPUTERS.jeiIntegration.removeOpenManualRecipe;
        boolean hideCallbackDocPage = recipeCategoryUid.equals(DOC_UID) && Config.OPEN_COMPUTERS.jeiIntegration.removeAPIDocRecipe;
        if (!hideManualPage && !hideCallbackDocPage) registry.addRecipes(recipes, recipeCategoryUid);
    }

}