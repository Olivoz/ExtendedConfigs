package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class RandomThingsConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Random Things. Default: false")
    public boolean enabled = false;

    @Config.Name("fox_hound")
    public FoxHound foxHound = new FoxHound();

    @Config.Name("golden_chicken")
    public GoldenChicken goldenChicken = new GoldenChicken();

    RandomThingsConfig() {
    }

    public static final class FoxHound {

        @Config.Comment("Specify the amount of extra cook time that should be added every tick when a tamed Fox Hound is on top of a Furnace. Default: 1")
        public int furnaceCookTimeIncrease = 1;

        FoxHound() {
        }
    }

    public static final class GoldenChicken {

        @Config.Comment("The amount of gold the Golden Chicken will drop when consuming a gold ore. Default: 3")
        public int goldDropCount = 3;

        @Config.Comment("The ore dictionary entry of the item to consume. Default: oreGold")
        public String goldOreDict = "oreGold";

        @Config.Comment("The time before the Golden Chicken will drop items. Formula: time + random between 0 and time. Default: 600")
        public int itemDropTimer = 600;

        @Config.Comment("Ticks left when starting to drop items. Setting this to a value higher than 0 will cause items to be dropped multiple times. Default: 0")
        public int minDropTimer = 0;

        GoldenChicken() {
        }
    }
}
