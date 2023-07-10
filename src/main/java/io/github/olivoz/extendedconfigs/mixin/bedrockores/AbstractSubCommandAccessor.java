package io.github.olivoz.extendedconfigs.mixin.bedrockores;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(targets = "li.cil.bedrockores.common.command.AbstractSubCommand")
public interface AbstractSubCommandAccessor {

    @Invoker("getLookedAtBlockPos")
    static @Nullable BlockPos getLookedAtBlockPos(ICommandSender sender) throws CommandException {
        throw new AssertionError();
    }

}
