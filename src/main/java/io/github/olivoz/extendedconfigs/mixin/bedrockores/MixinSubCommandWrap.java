package io.github.olivoz.extendedconfigs.mixin.bedrockores;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "li.cil.bedrockores.common.command.SubCommandWrap", remap = false)
public abstract class MixinSubCommandWrap {

    @Unique
    private String[] extendedConfigs$args = new String[0];

    @Inject(method = "execute", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lli/cil/bedrockores/common/command/SubCommandWrap;getLookedAtBlockPos(Lnet/minecraft/command/ICommandSender;)Lnet/minecraft/util/math/BlockPos;"))
    public void extendedConfigsMixinExecuteStoreArgs(MinecraftServer server, ICommandSender sender, String[] extendedConfigs$args, CallbackInfo ci) {
        this.extendedConfigs$args = extendedConfigs$args;
    }

    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Lli/cil/bedrockores/common/command/SubCommandWrap;getLookedAtBlockPos(Lnet/minecraft/command/ICommandSender;)Lnet/minecraft/util/math/BlockPos;"))
    public BlockPos extendedConfigsMixinExecute(ICommandSender sender) throws CommandException {
        return extendedConfigs$args.length > 3 ? new BlockPos(CommandBase.parseInt(extendedConfigs$args[1]), CommandBase.parseInt(extendedConfigs$args[2]), CommandBase.parseInt(extendedConfigs$args[3])) : AbstractSubCommandAccessor.getLookedAtBlockPos(sender);
    }
}
