package io.github.olivoz.extendedconfigs;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name("ExtendedConfigs-Core")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class ExtendedConfigsCore implements IFMLLoadingPlugin {

    private static final String[] TRANSFORMERS = {"io.github.olivoz.extendedconfigs.asm.actuallyadditions.LensMiningTransformer"};

    public ExtendedConfigsCore() {
        MixinBootstrap.init();
    }

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
}
