package io.github.olivoz.extendedconfigs.configs;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraftforge.common.config.Config;

import java.util.Map;

public final class ActuallyAdditionsConfig {

    @Config.Comment("Toggle for all changes to Actually Additions. Default: false")
    public boolean enabled = false;

    @Config.Name("display_stand")
    public DisplayStand displayStand = new DisplayStand();

    public Batteries batteries = new Batteries();

    @Config.Name("handheld_filler")
    public FillingWand fillingWand = new FillingWand();

    @Config.Name("potion_ring")
    public PotionRing potionRing = new PotionRing();

    @Config.Name("mining_lens")
    public MiningLens miningLens = new MiningLens();

    public static final class DisplayStand {

        @Config.Comment("The amount of energy (CF) that a display stand can contain. Default: 80000")
        public int capacity = 80000;

        @Config.Comment("The amount of energy (CF/t) that can be transferred to a display stand each tick. Default: 1000")
        public int maxReceive = 1000;

        @Config.Comment("The amount of energy (CF/t) that can be extracted from a display stand each tick. Default: 0")
        public int maxExtract = 0;
    }

    public static final class Batteries {

        @Config.Comment({"The amount of energy (CF) that a battery can contain. Removing an entry will use defaults.", "Defaults:", "item_battery: 200000", "item_battery_double: 350000", "item_battery_triple: 600000", "item_battery_quadruple: 1000000", "item_battery_quintuple: 2000000",})
        public Map<String, Integer> capacities;

        @Config.Name("transfer_rates")
        @Config.Comment({"The amount of energy (CF/t) that a battery can transfer per tick. Removing an entry will use defaults.", "Defaults:", "item_battery: 1000", "item_battery_double: 5000", "item_battery_triple: 10000", "item_battery_quadruple: 30000", "item_battery_quintuple: 100000",})
        public Map<String, Integer> transferRates;

        public Batteries() {
            String[] keys = {"item_battery", "item_battery_double", "item_battery_triple", "item_battery_quadruple", "item_battery_quintuple"};
            int[] capacityValues = {200000, 350000, 600000, 1000000, 2000000};
            int[] transferRateValues = {1000, 5000, 10000, 30000, 100000};
            capacities = new Object2IntOpenHashMap<>(keys, capacityValues);
            transferRates = new Object2IntOpenHashMap<>(keys, transferRateValues);
        }
    }

    public static final class FillingWand {

        @Config.Comment("The amount of energy (CF) that the item filling want will use per block filled. Default: 1500")
        public int energyPerBlock = 1500;
    }

    public static final class PotionRing {

        @Config.Comment("The amount of ticks per blaze usage. Default: 10")
        public int ticksPerBlazeUsage = 10;
    }

    public static final class MiningLens {

        @Config.Comment("Actually Additions adds common ores and their weights by default. You can disable this behaviour here. Default: false")
        public boolean removeHardcodedOres = false;

        @Config.Comment("Use Endstone instead of Stone when creating ores. Default: false")
        public boolean useEndstone = false;
    }
}
