package io.github.olivoz.extendedconfigs.mixin.openterraingenerator;

import com.pg85.otg.worldsave.DimensionData;
import io.github.olivoz.extendedconfigs.configs.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

@Mixin(value = DimensionData.class, remap = false)
public final class MixinDimensionData {

    @Redirect(method = "deleteDimSavedData",
            at = @At(value = "INVOKE", target = "Ljava/nio/file/Files;exists(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z"),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=DIM"),
                    to = @At(value = "CONSTANT", args = "stringValue=Deleting MC world save data for dimension ")
            )
    )
    private static boolean extendedConfigsMixinDeleteDimSavedData(Path path, LinkOption[] linkOptions) {
        if (Config.OPEN_TERRAIN_GENERATOR.preventDimDataDeletion) return false;
        return Files.exists(path, linkOptions);
    }

}
