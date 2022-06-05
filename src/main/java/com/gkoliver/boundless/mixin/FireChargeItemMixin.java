package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin {
    @Redirect(method="useOn", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void shrinkConditional(ItemStack instance, int p_41775_) {
        if (!(0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),instance))) {
            instance.shrink(p_41775_);
        }
    }
}
