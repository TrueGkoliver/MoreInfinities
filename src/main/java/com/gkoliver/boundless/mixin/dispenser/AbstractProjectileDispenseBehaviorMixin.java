package com.gkoliver.boundless.mixin.dispenser;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractProjectileDispenseBehavior.class)
public abstract class AbstractProjectileDispenseBehaviorMixin {
    @Redirect(method = "execute", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void redir(ItemStack instance, int p_41775_) {
        if (!BoundlessEnchantment.hasBoundless(instance) || !BoundlessEnchantment.BOUNDLESS.canEnchant(instance.getItem())) {
            instance.shrink(p_41775_);
        }
    }
}
