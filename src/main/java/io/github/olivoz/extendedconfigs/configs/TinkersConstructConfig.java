package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class TinkersConstructConfig {

    @Config.RequiresMcRestart
    @Config.Comment("Toggle for all changes to Tinker's Construct. Default: false")
    public boolean enabled = false;

    public Modifiers modifiers = new Modifiers();

    TinkersConstructConfig() {
    }

    public static final class Modifiers {

        @Config.Comment({
                "Use an alternate sharpness formula: level^4 / (20^4 / 8) * baseAttack + 0.1 * attack * level + level",
                "Default: false"
        })
        public boolean useAlternateSharpnessFormula = false;

        Modifiers() {
        }
    }
}
