package io.github.olivoz.extendedconfigs;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import crazypants.enderio.base.EnderIO;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import io.github.olivoz.extendedconfigs.configs.Config;
import me.jacky1356400.actuallybaubles.ActuallyBaubles;
import net.blay09.mods.excompressum.ExCompressum;
import net.minecraftforge.fml.common.Loader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class ExtendedConfigMixinPlugin implements IMixinConfigPlugin {

    private List<String> mixins = null;

    private static boolean isMixin(String name) {
        String fileName = name.substring(name.lastIndexOf('/') + 1);
        return fileName.startsWith("Mixin");
    }

    private static boolean isMixinEnabled(String name) {
        String modName = name.substring(0, name.lastIndexOf('/'));
        return Loader.isModLoaded(modName) && isMixinEnabledConfig(modName);
    }

    private static boolean isMixinEnabledConfig(String modName) {
        switch (modName) {
            case ActuallyAdditions.MODID:
                return Config.ACTUALLY_ADDITIONS.enabled;

            case ActuallyBaubles.MODID:
                return Config.ACTUALLY_BAUBLES.enabled;

            case "bedrockores":
                return Config.BEDROCK_ORES.enabled;

            case "botania":
                return Config.BOTANIA.enabled;

            case ExCompressum.MOD_ID:
                return Config.EX_COMPRESSUM.enabled;

            case "immersivecables":
                return Config.IMMERSIVE_CABLES.enabled && Config.IMMERSIVE_CABLES.disableWireLighting;

            case ImmersiveEngineering.MODID:
                return Config.IMMERSIVE_ENGINEERING.enabled;

            case "jeresources":
                return Config.JUST_ENOUGH_RESOURCES.enabled;

            case "libvulpes":
                return Config.LIB_VULPES.enabled;

            case EnderIO.MODID:
                return Config.ENDER_IO.enabled;

            case "oldjava":
                return Config.OLD_JAVA_WARNING.enabled && Config.OLD_JAVA_WARNING.fixVersionCheck;

            default:
                return false;
        }
    }

    private static Stream<Path> streamPath(Path path) {
        if (Files.isRegularFile(path)) return Stream.of(path);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            List<Path> folderFiles = new ArrayList<>();
            directoryStream.forEach(folderFiles::add);
            return folderFiles.stream();
        } catch (IOException e) {
            return Stream.of(path);
        }
    }

    @Override
    public void onLoad(String mixinPackage) {
        String[] mixinArray = new String[0];
        String packageFolder = mixinPackage.replaceAll("[.]", "/");
        ClassLoader classLoader = ExtendedConfig.class.getClassLoader();
        URL packageURL = classLoader.getResource(packageFolder);

        if (packageURL == null) return;
        String[] uriParts = packageURL.getPath().split("!");
        Path jarPath = Paths.get(URI.create(uriParts[0]));
        try (FileSystem fs = FileSystems.newFileSystem(jarPath, classLoader);
             DirectoryStream<Path> directoryStream = Files.newDirectoryStream(fs.getPath(uriParts[1]))
        ) {
            List<Path> files = Lists.newArrayList(directoryStream);
            mixinArray = files
                    .stream()
                    .flatMap(ExtendedConfigMixinPlugin::streamPath)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(".class"))
                    .map(name -> name.substring(mixinPackage.length() + 2, name.length() - ".class".length()))
                    .filter(ExtendedConfigMixinPlugin::isMixin)
                    .filter(ExtendedConfigMixinPlugin::isMixinEnabled)
                    .map(name -> name.replace('/', '.'))
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.mixins = ImmutableList.copyOf(mixinArray);
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
