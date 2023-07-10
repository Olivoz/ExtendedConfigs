package io.github.olivoz.extendedconfigs.mixin.botania;

import com.google.common.collect.ImmutableList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.client.integration.jei.manapool.ManaPoolRecipeWrapper;

import java.util.List;

@Mixin(value = ManaPoolRecipeWrapper.class, remap = false)
public abstract class MixinManaPoolRecipeWrapper {

    @Shadow
    @Final
    private int mana;

    @Inject(method = "getTooltipStrings(II)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    public void extendedConfigsMixinGetToolTipStrings(int mouseX, int mouseY, CallbackInfoReturnable<List<String>> cir) {
        if (mouseX > 20 && mouseX < 125 && mouseY > 50 && mouseY < 54) {
            cir.setReturnValue(ImmutableList.of(mana + " Mana"));
        }
    }

}
