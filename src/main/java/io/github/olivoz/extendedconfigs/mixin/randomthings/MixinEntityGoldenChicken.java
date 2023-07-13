package io.github.olivoz.extendedconfigs.mixin.randomthings;

import io.github.olivoz.extendedconfigs.configs.Config;
import lumien.randomthings.entitys.EntityGoldenChicken;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = EntityGoldenChicken.class, remap = false)
public abstract class MixinEntityGoldenChicken extends EntityAnimal {

    @Unique
    private static final String DEFAULT_GOLD_ORE_ID = "oreGold";
    @Shadow
    public int ingotDropTimer;

    private MixinEntityGoldenChicken(World worldIn) {
        super(worldIn);
    }

    @ModifyArgs(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Llumien/randomthings/entitys/EntityGoldenChicken;dropItem(Lnet/minecraft/item/Item;I)Lnet/minecraft/entity/item/EntityItem;"))
    public void extendedConfigsMixinGoldDropCount(Args args) {
        args.set(1, Config.RANDOM_THINGS.goldenChicken.goldDropCount);
    }

    @ModifyConstant(method = "onLivingUpdate", constant = @Constant(stringValue = DEFAULT_GOLD_ORE_ID))
    public String extendedConfigsMixinDrop(String constant) {
        return Config.RANDOM_THINGS.goldenChicken.goldOreDict;
    }

    @Inject(method = "onLivingUpdate",
            at = @At(value = "FIELD", target = "Llumien/randomthings/entitys/EntityGoldenChicken;ingotDropTimer:I", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=" + DEFAULT_GOLD_ORE_ID))
    )
    public void extendedConfigsMixinDropTimer(CallbackInfo ci) {
        this.ingotDropTimer = Config.RANDOM_THINGS.goldenChicken.itemDropTimer + this.rand.nextInt(Config.RANDOM_THINGS.goldenChicken.itemDropTimer);
    }

}
