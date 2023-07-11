package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class BedrockOresConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Bedrock Ores. Default: false")
    public boolean enabled = false;

    @Config.Comment("Allow specifying the warp location in the warp sub command. Default: false")
    public boolean setWarpCommandLocation = false;

    BedrockOresConfig() {
    }
}
