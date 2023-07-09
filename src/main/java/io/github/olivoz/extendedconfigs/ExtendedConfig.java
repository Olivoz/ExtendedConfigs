package io.github.olivoz.extendedconfigs;

import com.cleanroommc.configanytime.ConfigAnytime;
import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExtendedConfig.MODID, name = ExtendedConfig.NAME, version = ExtendedConfig.VERSION)
public final class ExtendedConfig {

    public static final String MODID = "${mod_id}";
    public static final String NAME = "${mod_name}";
    public static final String VERSION = "${mod_version}";
    public static final Config CONFIG = new Config();
    private static Logger logger;

    static {
        ConfigAnytime.register(Config.class);
    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("Initializing {}", NAME);
    }

}
