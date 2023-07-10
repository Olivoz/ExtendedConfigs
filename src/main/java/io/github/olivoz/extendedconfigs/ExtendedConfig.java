package io.github.olivoz.extendedconfigs;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

@Mod(modid = ExtendedConfig.MODID, name = ExtendedConfig.NAME, version = ExtendedConfig.VERSION)
public final class ExtendedConfig {

    public static final String MODID = "${mod_id}";
    public static final String NAME = "${mod_name}";
    public static final String VERSION = "${mod_version}";
    private static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        if (Config.ACTUALLY_ADDITIONS.enabled) loadMixin("actuallyadditions");
        loadMixin("bedrockores");
        loadMixin("botania");
    }

    private static void loadMixin(final String target) {
        if (!Loader.isModLoaded(target)) return;
        Mixins.addConfiguration(ExtendedConfig.MODID + "." + target + ".mixins.json");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("Initializing {}", NAME);
    }

}
