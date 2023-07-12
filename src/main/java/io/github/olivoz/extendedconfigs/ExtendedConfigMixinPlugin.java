package io.github.olivoz.extendedconfigs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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

    public static final List<String> MIXINS;
    private static final String MIXIN_PACKAGE_NAME = ExtendedConfig.class.getPackage().getName() + ".mixin";

    static {
        String[] mixins = new String[0];
        String packageFolder = MIXIN_PACKAGE_NAME.replaceAll("[.]", "/");
        ClassLoader classLoader = ExtendedConfig.class.getClassLoader();
        URL packageURL = classLoader.getResource(packageFolder);

        assert packageURL != null;
        String[] uriParts = packageURL.getPath().split("!");
        Path jarPath = Paths.get(URI.create(uriParts[0]));
        try (FileSystem fs = FileSystems.newFileSystem(jarPath, classLoader);
             DirectoryStream<Path> directoryStream = Files.newDirectoryStream(fs.getPath(uriParts[1]))
        ) {
            List<Path> files = Lists.newArrayList(directoryStream);
            mixins = files
                    .stream()
                    .flatMap(ExtendedConfigMixinPlugin::streamPath)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(".class"))
                    .map(name -> name.substring(MIXIN_PACKAGE_NAME.length() + 2, name.length() - ".class".length()))
                    .filter(ExtendedConfigMixinPlugin::isMixin)
                    .filter(ExtendedConfigMixinPlugin::isMixinEnabled)
                    .map(name -> name.replace('/', '.'))
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            MIXINS = ImmutableList.copyOf(mixins);
        }
    }

    private static boolean isMixin(String name) {
        String fileName = name.substring(name.lastIndexOf('/') + 1);
        return fileName.startsWith("Mixin");
    }

    private static boolean isMixinEnabled(String name) {
        String modName = name.substring(0, name.lastIndexOf('/'));
        return Loader.isModLoaded(modName);
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
        return MIXINS;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
