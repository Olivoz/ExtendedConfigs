package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class BotaniaConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Botania. Default: false")
    public boolean enabled = false;

    @Config.Name("mana_pool")
    public ManaPool manaPool = new ManaPool();

    @Config.Name("runic_altar")
    public RunicAltar runicAltar = new RunicAltar();

    BotaniaConfig() {
    }

    public static final class ManaPool {

        @Config.Comment("Show the exact mana usage when hovering over mana bar in JEI. Default: false")
        public boolean showManaCost = false;

        ManaPool() {
        }
    }

    public static final class RunicAltar {

        @Config.Comment("Show the exact mana usage when hovering over mana bar in JEI. Default: false")
        public boolean showManaCost = false;

        RunicAltar() {
        }
    }
}
