package io.github.olivoz.extendedconfigs.mixin.scalinghealth;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.silentchaos512.scalinghealth.event.DifficultyHandler;
import net.silentchaos512.scalinghealth.lib.EnumAreaDifficultyMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DifficultyHandler.class, remap = false)
public final class MixinDifficultyHandler {

    @Redirect(method = "increaseEntityHealth", at = @At(value = "INVOKE", target = "Lnet/silentchaos512/scalinghealth/lib/EnumAreaDifficultyMode;getAreaDifficulty(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)D"))
    public double extendedConfigsMixinIncreaseEntityHealth(EnumAreaDifficultyMode instance, World world, BlockPos pos) {
        double areaDifficulty = net.silentchaos512.scalinghealth.config.Config.Difficulty.AREA_DIFFICULTY_MODE.getAreaDifficulty(world, pos);
        if (Config.SCALING_HEALTH.exponentialMobHealth) return areaDifficulty;
        return areaDifficulty * areaDifficulty / net.silentchaos512.scalinghealth.config.Config.Difficulty.maxValue;
    }

}
