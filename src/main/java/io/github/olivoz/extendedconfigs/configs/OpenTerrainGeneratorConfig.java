package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class OpenTerrainGeneratorConfig {

    @Config.Comment("Toggle for all changes to Open Terrain Generator (OTG). Default: false")
    public boolean enabled = false;

    @Config.Comment("Prevent the deletion of dimension data. Default: false")
    public boolean preventDimDataDeletion = false;

    OpenTerrainGeneratorConfig() {
    }

}
