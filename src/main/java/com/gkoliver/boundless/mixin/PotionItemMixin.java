package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    @Redirect(method="finishUsingItem", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean overrideConsumable(Abilities instance, ItemStack p_42984_, Level p_42985_, LivingEntity p_42986_) {
        return instance.instabuild || 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),p_42984_);
    }
}
