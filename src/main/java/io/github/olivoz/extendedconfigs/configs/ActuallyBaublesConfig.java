package io.github.olivoz.extendedconfigs.configs;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraftforge.common.config.Config;

import java.util.Map;

public final class ActuallyBaublesConfig {

    @Config.Comment("Toggle for all changes to Actually Baubles. Default: false")
    public boolean enabled = false;

    @net.minecraftforge.common.config.Config.Comment({"The amount of energy (CF) that a bauble battery can contain. Removing an entry will use defaults.", "Defaults:", "battery_bauble: 200000", "battery_double_bauble: 350000", "battery_triple_bauble: 600000", "battery_quadruple_bauble: 1000000", "battery_quintuple_bauble: 2000000",})
    public Map<String, Integer> capacities;

    @net.minecraftforge.common.config.Config.Name("transfer_rates")
    @Config.Comment({"The amount of energy (CF/t) that a bauble battery can transfer per tick. Removing an entry will use defaults.", "Defaults:", "battery_bauble: 1000", "battery_double_bauble: 5000", "battery_triple_bauble: 10000", "battery_quadruple_bauble: 30000", "battery_quintuple_bauble: 100000",})
    public Map<String, Integer> transferRates;

    ActuallyBaublesConfig() {
        String[] keys = {"battery_bauble", "battery_double_bauble", "battery_triple_bauble", "battery_quadruple_bauble", "battery_quintuple_bauble"};
        int[] capacityValues = {200000, 350000, 600000, 1000000, 2000000};
        int[] transferRateValues = {1000, 5000, 10000, 30000, 100000};
        capacities = new Object2IntOpenHashMap<>(keys, capacityValues);
        transferRates = new Object2IntOpenHashMap<>(keys, transferRateValues);
    }

}
