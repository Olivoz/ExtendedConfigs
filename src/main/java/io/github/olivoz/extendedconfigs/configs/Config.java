package io.github.olivoz.extendedconfigs.configs;

import io.github.olivoz.extendedconfigs.ExtendedConfig;

@net.minecraftforge.common.config.Config(modid = ExtendedConfig.MODID, name = ExtendedConfig.MODID)
public final class Config {

    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Actually Additions mod")
    public static final ActuallyAdditionsConfig ACTUALLY_ADDITIONS = new ActuallyAdditionsConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Actually Baubles mod")
    public static final ActuallyBaublesConfig ACTUALLY_BAUBLES = new ActuallyBaublesConfig();

    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Bedrock Ores mod")
    public static final BedrockOresConfig BEDROCK_ORES = new BedrockOresConfig();

    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Botania mod")
    public static final BotaniaConfig BOTANIA = new BotaniaConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Ex Compressum mod")
    public static final ExCompressumConfig EX_COMPRESSUM = new ExCompressumConfig();
}
