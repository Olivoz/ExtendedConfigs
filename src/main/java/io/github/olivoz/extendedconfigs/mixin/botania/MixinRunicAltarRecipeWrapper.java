package io.github.olivoz.extendedconfigs.mixin.botania;

import com.google.common.collect.ImmutableList;
import io.github.olivoz.extendedconfigs.configs.Config;
import mezz.jei.api.recipe.IRecipeWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.client.integration.jei.runicaltar.RunicAltarRecipeWrapper;

import javax.annotation.Nonnull;
import java.util.List;

@Mixin(value = RunicAltarRecipeWrapper.class, remap = false)
public abstract class MixinRunicAltarRecipeWrapper implements IRecipeWrapper {

    @Shadow
    @Final
    private int manaUsage;

    @Override
    public @Nonnull List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (Config.BOTANIA.runicAltar.showManaCost && mouseX > 6 && mouseX < 111 && mouseY > 98 && mouseY < 102) {
            return ImmutableList.of(manaUsage + " Mana");
        }

        return ImmutableList.of();
    }
}
