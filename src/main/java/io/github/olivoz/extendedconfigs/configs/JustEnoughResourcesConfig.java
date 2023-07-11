package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class JustEnoughResourcesConfig {

    @Config.Comment("Toggle for all changes to Just Enough Resources. Default: false")
    public boolean enabled = false;

    @Config.Name("world_gen")
    public WorldGen worldGen = new WorldGen();

    JustEnoughResourcesConfig() {
    }

    public static final class WorldGen {

        @Config.Comment("Sort world gen entries by dimension id. Default: false")
        public boolean sortDimensions = false;

        WorldGen() {
        }
    }
}
