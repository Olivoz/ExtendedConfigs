package io.github.olivoz.extendedconfigs.configs;

import net.minecraftforge.common.config.Config;

public final class OpenComputersConfig {

    @Config.Comment("Toggle for all changes to Open Computers. Default: false")
    public boolean enabled = false;

    @Config.Name("upgrade_tractor_beam")
    public UpgradeTractorBeam upgradeTractorBeam = new UpgradeTractorBeam();

    @Config.Name("upgrade_tank")
    public UpgradeTank upgradeTank = new UpgradeTank();

    @Config.Name("jei_integration")
    public JEIIntegration jeiIntegration = new JEIIntegration();

    OpenComputersConfig() {
    }

    public static final class UpgradeTractorBeam {

        @Config.Comment("Change the pickup radius for the tractor beam upgrade. Default: 3")
        public int pickupRadius = 3;

        UpgradeTractorBeam() {
        }
    }

    public static final class UpgradeTank {

        @Config.Comment("Change the tank upgrade capacity. Default: 16000")
        public int capacity = 16000;

        UpgradeTank() {
        }
    }

    public static final class JEIIntegration {

        @Config.Comment("Removes the usage recipe for opening the manual. Default: false")
        public boolean removeOpenManualRecipe = false;

        @Config.Comment("Removes the usage recipes for API documentation. Default: false")
        public boolean removeAPIDocRecipe = false;

        JEIIntegration() {
        }
    }
}
