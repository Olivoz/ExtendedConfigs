package io.github.olivoz.extendedconfigs;

import io.github.olivoz.extendedconfigs.configs.Config;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
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
        List<String> transformers = new ArrayList<>();
        if (Config.ACTUALLY_ADDITIONS.enabled)
            transformers.add("io.github.olivoz.extendedconfigs.asm.actuallyadditions.LensMiningTransformer");

        return transformers.toArray(new String[0]);
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
