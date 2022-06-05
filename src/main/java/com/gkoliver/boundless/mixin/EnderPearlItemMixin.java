package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnderpearlItem.class)
public abstract class EnderPearlItemMixin {
    @Inject(method="use", cancellable = true, at=@At(value="INVOKE", target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/stats/Stat;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void earlyEnd(Level p_41190_, Player p_41191_, InteractionHand p_41192_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemstack) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack)) {
            cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemstack, p_41190_.isClientSide()));
        }
    }
}
