package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnEggItem.class)
public abstract class MobEggItemMixin
{
    @Redirect(method={"use", "useOn", "spawnOffspringFromSpawnEgg"}, at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    public void redirShrink(ItemStack instance, int val) {
        if (!BoundlessEnchantment.hasBoundless(instance)) {
            instance.shrink(val);
        }
    }
}
