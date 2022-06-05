package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {
    @Redirect(method="applyBonemeal", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private static void stopShrink(ItemStack instance, int p_41775_) {
        if (!BoundlessEnchantment.hasBoundless(instance) || !BoundlessEnchantment.BOUNDLESS.canEnchant(instance.getItem())) {
            instance.shrink(p_41775_);
        }
    }
    @Redirect(method="growWaterPlant", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private static void stopWaterShrink(ItemStack instance, int p_41775_) {
        if (!BoundlessEnchantment.hasBoundless(instance) || !BoundlessEnchantment.BOUNDLESS.canEnchant(instance.getItem())) {
            instance.shrink(p_41775_);
        }
    }
}
