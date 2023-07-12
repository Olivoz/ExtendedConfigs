package io.github.olivoz.extendedconfigs.mixin.opencomputers;

import io.github.olivoz.extendedconfigs.configs.Config;
import li.cil.oc.integration.opencomputers.DriverUpgradeTank$;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = DriverUpgradeTank$.class, remap = false)
public final class MixinDriverUpgradeTank {

    @ModifyArgs(method = "createEnvironment", at = @At(value = "INVOKE", target = "Lli/cil/oc/server/component/UpgradeTank;<init>(Lli/cil/oc/api/network/EnvironmentHost;I)V"))
    public void extendedConfigsMixinMaxCapacity(Args args) {
        args.set(1, Config.OPEN_COMPUTERS.upgradeTank.capacity);
    }

}
