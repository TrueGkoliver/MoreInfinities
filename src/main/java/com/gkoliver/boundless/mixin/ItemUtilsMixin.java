package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemUtils.class)
public abstract class ItemUtilsMixin {
    @Redirect(method="createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/item/ItemStack;", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private static boolean overrideFlag(Abilities instance, ItemStack p_41818_, Player p_41819_, ItemStack p_41820_, boolean p_41821_) {
        return 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),p_41818_) || p_41819_.getAbilities().instabuild;
    }
}
