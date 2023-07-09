package io.github.olivoz.extendedconfigs.mixin;

import de.ellpeck.actuallyadditions.mod.items.InitItems;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = InitItems.class, remap = false)
public final class MixinInitItems {

    @ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lde/ellpeck/actuallyadditions/mod/items/ItemBattery;<init>(Ljava/lang/String;II)V"))
    private static void extendedConfigsMixinItemsInit(Args args) {
        String name = args.get(0);
        Integer capacity = Config.ACTUALLY_ADDITIONS.batteries.capacities.get(name);
        Integer transferRate = Config.ACTUALLY_ADDITIONS.batteries.transferRates.get(name);
        if (capacity != null) args.set(1, capacity);
        if (transferRate != null) args.set(2, capacity);
    }

}
