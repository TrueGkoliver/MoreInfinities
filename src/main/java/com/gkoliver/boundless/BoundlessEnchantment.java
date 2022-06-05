package com.gkoliver.boundless;

import com.gkoliver.boundless.mixin.EnderPearlItemMixin;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.MendingEnchantment;

public class BoundlessEnchantment extends Enchantment  {
    public static boolean hasBoundless(ItemStack itemstack) {
        return 0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack);
    }
    public static final EnchantmentCategory BOUNDLESS = EnchantmentCategory.create("boundless", (item)->{
        return (item.isEdible() && BoundlessConfig.foods()) ||
                (item instanceof PotionItem && BoundlessConfig.potions()) ||
                (item instanceof EggItem && BoundlessConfig.buckets()) ||
                (item instanceof BucketItem && BoundlessConfig.buckets() && !(item instanceof MobBucketItem)) ||
                (item instanceof MobBucketItem && BoundlessConfig.mobBuckets()) ||
                (item instanceof MilkBucketItem && BoundlessConfig.buckets()) ||
                (item instanceof SolidBucketItem && BoundlessConfig.buckets()) ||
                (item instanceof ExperienceBottleItem && BoundlessConfig.xpBottles()) ||
                (item instanceof EnderpearlItem && BoundlessConfig.throwables()) ||
                (item instanceof SnowballItem && BoundlessConfig.throwables()) ||
                (item instanceof EnderEyeItem && BoundlessConfig.endereyes()) ||
                (item instanceof FireChargeItem && BoundlessConfig.fireCharges()) ||
                (item instanceof ArrowItem && BoundlessConfig.arrows()) ||
                (item instanceof BoneMealItem && BoundlessConfig.boneMeal());
    });
    public BoundlessEnchantment(Enchantment.Rarity p_44584_, EquipmentSlot... p_44585_) {
        super(p_44584_, BOUNDLESS, p_44585_);
    }

    public int getMinCost(int p_44588_) {
        return 30;
    }

    public int getMaxCost(int p_44592_) {
        return 60;
    }

    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}
