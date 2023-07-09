package io.github.olivoz.extendedconfigs.configs;

import io.github.olivoz.extendedconfigs.ExtendedConfig;

@net.minecraftforge.common.config.Config(modid = ExtendedConfig.MODID)
public final class Config {

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Actually Additions mod")
    public static final ActuallyAdditionsConfig ACTUALLY_ADDITIONS = new ActuallyAdditionsConfig();
}