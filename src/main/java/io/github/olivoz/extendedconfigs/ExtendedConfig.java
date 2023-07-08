package io.github.olivoz.extendedconfigs;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExtendedConfig.MODID, name = ExtendedConfig.NAME, version = ExtendedConfig.VERSION)
public final class ExtendedConfig {

    public static final String MODID = "${mod_id}";
    public static final String NAME = "${mod_name}";
    public static final String VERSION = "${mod_version}";

    private static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("Initializing {}", NAME);
    }

}