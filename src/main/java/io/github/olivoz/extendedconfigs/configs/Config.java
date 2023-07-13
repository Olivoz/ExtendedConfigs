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

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Immersive Cables mod")
    public static final ImmersiveCablesConfig IMMERSIVE_CABLES = new ImmersiveCablesConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Immersive Engineering mod")
    public static final ImmersiveEngineeringConfig IMMERSIVE_ENGINEERING = new ImmersiveEngineeringConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Just Enough Resources mod")
    public static final JustEnoughResourcesConfig JUST_ENOUGH_RESOURCES = new JustEnoughResourcesConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Lib Vulpes mod")
    public static final LibVulpesConfig LIB_VULPES = new LibVulpesConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Ender IO mod")
    public static final EnderIOConfig ENDER_IO = new EnderIOConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Old Java Warning mod")
    public static final OldJavaWarningConfig OLD_JAVA_WARNING = new OldJavaWarningConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Open Computers mod")
    public static final OpenComputersConfig OPEN_COMPUTERS = new OpenComputersConfig();

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Open Terrain Generator (OTG) mod")
    public static final OpenTerrainGeneratorConfig OPEN_TERRAIN_GENERATOR = new OpenTerrainGeneratorConfig();

    @net.minecraftforge.common.config.Config.Comment("This section contains settings for the Quark mod")
    public static final QuarkConfig QUARK = new QuarkConfig();
}
