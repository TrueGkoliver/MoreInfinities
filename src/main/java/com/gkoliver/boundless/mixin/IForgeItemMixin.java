package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessConfig;
import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = IForgeItem.class, remap = false)
public interface IForgeItemMixin {
    @Shadow
    Item self();
    @Overwrite
    default ItemStack getCraftingRemainingItem(ItemStack itemStack)
    {
        if (!hasCraftingRemainingItem(itemStack))
        {
            return ItemStack.EMPTY;
        }
        if (BoundlessConfig.crafting() && BoundlessEnchantment.hasBoundless(itemStack)) {
            return itemStack;
        }
        return new ItemStack(self().getCraftingRemainingItem());
    }
    @Overwrite
    default boolean hasCraftingRemainingItem(ItemStack stack)
    {
        if (BoundlessConfig.crafting() && BoundlessEnchantment.hasBoundless(stack)) return true;
        return self().hasCraftingRemainingItem();
    }
    /*@Inject(method="hasContainerItem", at=@At("RETURN"), cancellable = true)
    private void changeIfBoundless(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (BoundlessConfig.crafting() && BoundlessEnchantment.hasBoundless(stack)) {
            cir.setReturnValue(true);
        }
    }
    @Inject(method="getContainerItem", at=@At("TAIL"), cancellable = true)
    private void changeBound(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (BoundlessConfig.crafting() && BoundlessEnchantment.hasBoundless(stack)) {
            cir.setReturnValue(stack);
        }
    }*/
}
