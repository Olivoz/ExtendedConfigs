package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class ScalingHealthConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Scaling Health. Default: false")
    public boolean enabled = false;

    @Config.Comment("Change the mob health to a exponential scale rather than a linear one. New formula: difficulty * difficulty / maxDifficulty. Default: false")
    public boolean exponentialMobHealth = false;

    ScalingHealthConfig() {
    }
}
