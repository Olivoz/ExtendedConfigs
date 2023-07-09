package io.github.olivoz.extendedconfigs.mixin;

import de.ellpeck.actuallyadditions.mod.items.ItemFillingWand;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ItemFillingWand.class)
public final class MixinItemFillingWand {

    @Unique
    private static final int DEFAULT_ENERGY_USAGE = 1500;

    @ModifyConstant(method = "onUpdate(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V", constant = @Constant(intValue = DEFAULT_ENERGY_USAGE))
    public int extendedConfigsMixinOnUpdate(int constant) {
        return Config.ACTUALLY_ADDITIONS.fillingWand.energyPerBlock;
    }

}
