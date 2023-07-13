package io.github.olivoz.extendedconfigs.mixin.jeresources;

import io.github.olivoz.extendedconfigs.configs.Config;
import jeresources.entry.WorldGenEntry;
import jeresources.registry.WorldGenRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(value = WorldGenRegistry.class, remap = false)
public final class MixinWorldGenRegistry {

    @Unique
    private static final Pattern ID_PATTERN = Pattern.compile("\\((-?\\d+)\\)");

    @Inject(method = "getWorldGen", at = @At("RETURN"))
    public void extendedConfigsMixinGetWorldGen(CallbackInfoReturnable<List<WorldGenEntry>> cir) {
        if (!Config.JUST_ENOUGH_RESOURCES.worldGen.sortDimensions) return;
        List<WorldGenEntry> worldGenEntries = cir.getReturnValue();
        worldGenEntries.sort((entry1, entry2) -> {
            String dim2 = entry2.getDimension();
            String dim1 = entry1.getDimension();
            int diff = extendedConfigs$parseDim(dim2) - extendedConfigs$parseDim(dim1);
            if (diff == 0) return dim2.compareTo(dim1);
            return diff;
        });
    }

    @Unique
    private int extendedConfigs$parseDim(String dim) {
        Matcher matcher = ID_PATTERN.matcher(dim);
        if (matcher.find()) return Math.abs(Integer.parseInt(matcher.group(1)));
        return Integer.MAX_VALUE;
    }

}
