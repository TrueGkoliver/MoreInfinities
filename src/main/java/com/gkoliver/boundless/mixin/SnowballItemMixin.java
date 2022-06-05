package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SnowballItem.class)
public abstract class SnowballItemMixin {
    @Inject(method="use", cancellable = true, at=@At(value="INVOKE", target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/stats/Stat;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void earlyEnd(Level p_43142_, Player p_43143_, InteractionHand p_43144_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemstack) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack)) {
            cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemstack, p_43142_.isClientSide()));
        }
    }
}
