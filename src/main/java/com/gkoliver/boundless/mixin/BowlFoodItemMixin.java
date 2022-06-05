package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowlFoodItem.class)
public abstract class BowlFoodItemMixin {
    @Redirect(method = "finishUsingItem", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean eatRedirect(Abilities instance, ItemStack p_40684_, Level p_40685_, LivingEntity p_40686_) {
        return instance.instabuild || 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),p_40684_);
    }
}
