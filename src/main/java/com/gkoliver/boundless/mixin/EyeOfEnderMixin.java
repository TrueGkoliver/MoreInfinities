package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EyeOfEnder.class)
public abstract class EyeOfEnderMixin extends Entity {
    public EyeOfEnderMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    @Shadow
    @Final
    private static EntityDataAccessor<ItemStack>  DATA_ITEM_STACK;
    @Shadow
    private boolean surviveAfterDeath;

    @Inject(method="signalTo", at=@At("TAIL"))
    private void signalToInject(BlockPos p_36968_, CallbackInfo ci) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(), this.getEntityData().get(DATA_ITEM_STACK))) { //TODO fix
            this.surviveAfterDeath = false;
        }
    }
}
