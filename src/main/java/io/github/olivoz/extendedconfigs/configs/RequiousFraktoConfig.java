package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class RequiousFraktoConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Requious Frakto. Default: false")
    public boolean enabled = false;

    public Assembler assembler = new Assembler();

    RequiousFraktoConfig() {
    }

    public static final class Assembler {

        @Config.Comment("Fixes a crash when breaking an assembler block. Default: false")
        public boolean fixBreakingCrash = false;

        Assembler() {
        }
    }
}
