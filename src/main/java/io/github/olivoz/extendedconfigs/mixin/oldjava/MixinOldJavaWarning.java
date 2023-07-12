package io.github.olivoz.extendedconfigs.mixin.oldjava;

import net.darkhax.oldjava.OldJavaWarning;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = OldJavaWarning.class, remap = false)
public final class MixinOldJavaWarning {

    @Shadow
    @Final
    private static Logger LOG;

    @Redirect(method = "checkJavaVersion", at = @At(value = "INVOKE", target = "Ljava/lang/String;compareTo(Ljava/lang/String;)I"))
    private static int extendedConfigsMixinCheckJavaVersion(String minVersion, String installedVersion) {
        String[] versionConfig = minVersion.split("_");
        String[] versionSystem = installedVersion.split("_");

        if (!versionSystem[0].equals(versionConfig[0])) {
            LOG.info("Detected major Java version {}, which is different from the recommended {}. Skipping version check!", versionSystem[0], versionConfig[0]);
            return 0;
        }

        try {
            if (Integer.parseInt(versionConfig[1]) > Integer.parseInt(versionSystem[1])) return 1;
            else return 0;
        } catch (NumberFormatException ex) {
            LOG.error("Bad config or unknown installed Java build, ignoring...", ex);
            return 0;
        }
    }

}
