package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class EnderIOConfig {

    @Config.Comment("Toggle for all changes to Ender IO. Default: false")
    public boolean enabled = false;

    public Tank tank = new Tank();

    EnderIOConfig() {
    }

    public static final class Tank {

        @Config.Comment("Fix missing recipes in JEI when both enableTankFluidInOutJEIRecipes and enableTankMendingJEIRecipes are set to false. Default: false")
        public boolean fixMissingRecipes = false;

        Tank() {
        }
    }
}
