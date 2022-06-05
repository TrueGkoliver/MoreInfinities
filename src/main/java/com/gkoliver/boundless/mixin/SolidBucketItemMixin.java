package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SolidBucketItem.class)
public abstract class SolidBucketItemMixin {
    @Redirect(method="useOn", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z"))
    private boolean useOnRedir(Player instance, UseOnContext p_151197_) {
        return 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(), p_151197_.getItemInHand()) || instance.isCreative();
    }

}
