package com.gkoliver.boundless.mixin.dispenser;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.core.BlockSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.core.dispenser.DispenseItemBehavior$16"})
public abstract class BucketDispenseBehaviorMixin {
    @Inject(method = "execute", cancellable = true, at=@At(value = "RETURN"))
    private void returnEarly(BlockSource p_123561_, ItemStack p_123562_, CallbackInfoReturnable<ItemStack> cir) {
        if (BoundlessEnchantment.hasBoundless(p_123562_)) {
            cir.setReturnValue(p_123562_);
        }
    }
}
