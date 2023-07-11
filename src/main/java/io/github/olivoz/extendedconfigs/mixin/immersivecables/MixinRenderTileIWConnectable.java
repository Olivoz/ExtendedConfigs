package io.github.olivoz.extendedconfigs.mixin.immersivecables;

import de.sanandrew.mods.immersivecables.client.render.RenderTileIWConnectable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RenderTileIWConnectable.class, remap = false)
public final class MixinRenderTileIWConnectable {

    @Redirect(method = "tessellateConnectionFast", at = @At(value = "INVOKE", target = "Lde/sanandrew/mods/immersivecables/client/render/RenderTileIWConnectable;setLightmap(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/client/renderer/BufferBuilder;"))
    private static BufferBuilder extendedConfigsMixinSetLightmap(BufferBuilder buffer, Vec3d pos) {
        return buffer.lightmap(0, 240);
    }

}
