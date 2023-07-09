package io.github.olivoz.extendedconfigs;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name("ExtendedConfigs-Core")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class ExtendedConfigsCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    private static final List<String> MIXINS = Collections.singletonList(ExtendedConfig.MODID + ".mixins.json");
    private static final String[] TRANSFORMERS = {"io.github.olivoz.extendedconfigs.asm.LensMiningTransformer"};

    @Override
    public String[] getASMTransformerClass() {
        return TRANSFORMERS;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return MIXINS;
    }
}
