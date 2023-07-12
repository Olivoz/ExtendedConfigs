package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class OldJavaWarningConfig {

    @Config.Comment("Toggle for all changes to Old Java Warning. Default: false")
    public boolean enabled = false;

    @Config.Comment("Should we try and patch the old java version check. Default: false")
    public boolean fixVersionCheck = false;

    OldJavaWarningConfig() {
    }
}
