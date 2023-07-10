package io.github.olivoz.extendedconfigs.configs;

import io.github.olivoz.extendedconfigs.ExtendedConfig;

@net.minecraftforge.common.config.Config(modid = ExtendedConfig.MODID, name = ExtendedConfig.MODID)
public final class Config {

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Actually Additions mod")
    public static final ActuallyAdditionsConfig ACTUALLY_ADDITIONS = new ActuallyAdditionsConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Actually Baubles mod")
    public static final ActuallyBaublesConfig ACTUALLY_BAUBLES = new ActuallyBaublesConfig();
}
