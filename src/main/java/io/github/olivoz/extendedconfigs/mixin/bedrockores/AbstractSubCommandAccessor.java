package io.github.olivoz.extendedconfigs.mixin.bedrockores;

import li.cil.bedrockores.common.command.AbstractSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(value = AbstractSubCommand.class, remap = false)
public interface AbstractSubCommandAccessor {

    @Invoker("getLookedAtBlockPos")
    static @Nullable BlockPos getLookedAtBlockPos(ICommandSender sender) {
        throw new AssertionError();
    }

}
