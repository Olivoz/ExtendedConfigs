package io.github.olivoz.extendedconfigs.mixin.tconstruct;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.tools.modifiers.ModSharpness;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

@Mixin(value = ModSharpness.class, remap = false)
public abstract class MixinModSharpness extends ToolModifier {

    @Shadow
    @Final
    private int max;

    protected MixinModSharpness(String identifier, int color) {
        super(identifier, color);
    }

    @Inject(method = "applyEffect",
            at = @At(value = "FIELD", target = "Lslimeknights/tconstruct/tools/modifiers/ModSharpness;max:I", shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true
    )
    public void extendedConfigsMixinApplyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag, CallbackInfo ci, ModifierNBT.IntegerNBT data, ToolNBT toolData, float attack) {
        if (!Config.TINKERS_CONSTRUCT.modifiers.useAlternateSharpnessFormula) return;

        float level = (float) data.current / (float) this.max;
        attack += ((Math.pow(level, 4) / (Math.pow(20, 4) / 8F)) * attack) + (0.1f * attack * level) + level;
        NBTTagCompound tag = TagUtil.getToolTag(rootCompound);
        attack -= toolData.attack;
        attack += tag.getFloat("Attack");
        tag.setFloat("Attack", attack);
        ci.cancel();
    }
}
