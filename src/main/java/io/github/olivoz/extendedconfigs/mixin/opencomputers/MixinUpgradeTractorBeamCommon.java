package io.github.olivoz.extendedconfigs.mixin.opencomputers;

import io.github.olivoz.extendedconfigs.configs.Config;
import li.cil.oc.server.component.UpgradeTractorBeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = UpgradeTractorBeam.Common.class, remap = false)
public final class MixinUpgradeTractorBeamCommon {

    @Unique
    private static final int DEFAULT_PICKUP_RADIUS = 3;

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = DEFAULT_PICKUP_RADIUS))
    public int extendedConfigsMixinTractorBeamPickupRadius(int constant) {
        return Config.OPEN_COMPUTERS.upgradeTractorBeam.pickupRadius;
    }

}
