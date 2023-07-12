package io.github.olivoz.extendedconfigs.mixin.opencomputers;

import io.github.olivoz.extendedconfigs.configs.Config;
import li.cil.oc.common.item.UpgradeTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = UpgradeTank.class, remap = false)
public final class MixinUpgradeTank {

    @Unique
    private static final String DEFAULT_MAX_CAPACITY_TOOLIP = "/16000";

    @ModifyConstant(method = "tooltipLines", constant = @Constant(stringValue = DEFAULT_MAX_CAPACITY_TOOLIP))
    public String extendedConfigsMixinTooltipLines(String constant) {
        return "/" + Config.OPEN_COMPUTERS.upgradeTank.capacity;
    }

}
