package com.gkoliver.boundless.mixin.dispenser;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DefaultDispenseItemBehavior.class)
public abstract class DefaultDispenseItemBehaviorMixin {
    @Redirect(method = "execute", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;split(I)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack stop(ItemStack instance, int p_41621_) {
        if (!BoundlessEnchantment.hasBoundless(instance) || !BoundlessEnchantment.BOUNDLESS.canEnchant(instance.getItem())) {
            return instance.split(p_41621_);
        }
        return instance;
    }
}
