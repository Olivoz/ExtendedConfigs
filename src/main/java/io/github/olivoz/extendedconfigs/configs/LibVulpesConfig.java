package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class LibVulpesConfig {

    @Config.Comment("Toggle for all changes to Lib Vulpes. Default: false")
    public boolean enabled = false;

    @Config.Name("plug_base")
    public PlugBase plugBase = new PlugBase();

    LibVulpesConfig() {
    }

    public static final class PlugBase {

        @Config.Comment("Max drain rate formula: modifier * 10^tier. Default: 10000")
        public int getMaxEnergyModifier = 10000;

        @Config.Comment("Max drain rate formula: modifier * 2^tier. Default: 250")
        public int getMaxDrainRateModifier = 250;

        @Config.Comment("The tier level cap after which the max energy stops changing. A negative number will disable this setting. Default: -1")
        public int getMaxEnergyTierCap = -1;

        PlugBase() {
        }
    }
}
