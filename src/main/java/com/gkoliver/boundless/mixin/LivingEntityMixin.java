package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Redirect(method = "eat", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean eatRedirect(Abilities instance, Level p_21067_, ItemStack p_21068_) {
        return instance.instabuild || 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),p_21068_);
    }
}
