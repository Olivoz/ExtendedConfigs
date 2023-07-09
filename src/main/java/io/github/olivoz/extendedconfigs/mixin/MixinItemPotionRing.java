package io.github.olivoz.extendedconfigs.mixin;

import de.ellpeck.actuallyadditions.mod.items.ItemPotionRing;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ItemPotionRing.class, remap = false)
public final class MixinItemPotionRing {

    @Unique
    private static final int TICKS_PER_BLAZE_USAGE = 10;

    @ModifyConstant(method = "onUpdate(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V", constant = @Constant(longValue = TICKS_PER_BLAZE_USAGE))
    public long extendedConfigsMixinOnUpdate(long constant) {
        return Config.ACTUALLY_ADDITIONS.potionRing.ticksPerBlazeUsage;
    }

}
