package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class QuarkConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Quark. Default: false")
    public boolean enabled = false;

    @Config.Name("fox_hound")
    public FoxHound foxHound = new FoxHound();

    QuarkConfig() {
    }

    public static final class FoxHound {

        @Config.Comment("Specify the amount of extra cook time that should be added every tick when a tamed Fox Hound is on top of a Furnace. Default: 1")
        public int furnaceCookTimeIncrease = 1;

        FoxHound() {
        }
    }
}
