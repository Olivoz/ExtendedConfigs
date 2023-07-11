package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class ImmersiveCablesConfig {

    @Config.Comment("Toggle for all changes to Immersive Cables. Default: false")
    public boolean enabled = false;

    @Config.Comment("Disable wire lighting. This is MUCH faster, though it will look worse. Default: false")
    public boolean disableWireLighting = false;

    ImmersiveCablesConfig() {
    }
}
