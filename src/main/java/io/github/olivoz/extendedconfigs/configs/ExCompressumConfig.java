package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class ExCompressumConfig {

    @Config.Comment("Toggle for all changes to Ex Compressum. Default: false")
    public boolean enabled = false;

    @Config.Name("auto_sieve")
    public AutoSieve autoSieve = new AutoSieve();

    public static final class AutoSieve {

        @Config.Comment("The amount of energy (RF) that the Auto Sieve can hold. Default: 32000")
        public int capacity = 32000;
    }

}
