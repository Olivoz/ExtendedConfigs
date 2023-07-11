package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class ImmersiveEngineeringConfig {

    @Config.Comment("Toggle for all changes to Immersive Engineering. Default: false")
    public boolean enabled = false;

    public Silo silo = new Silo();

    ImmersiveEngineeringConfig() {
    }

    public static final class Silo {

        @Config.RangeInt(min = 6)
        public int maxStorage = 41472;

        Silo() {
        }
    }
}
