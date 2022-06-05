package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FireworkRocketItem.class)
public abstract class FireworkRocketItemMixin extends Item {
    public FireworkRocketItemMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Inject(method = "useOn", at=@At(shift=At.Shift.BEFORE, value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endEarly(UseOnContext p_41216_, CallbackInfoReturnable<InteractionResult> cir, Level level, ItemStack itemstack, Vec3 vec3, Direction direction, FireworkRocketEntity fireworkrocketentity) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack)) {
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
    @Inject(method = "use", cancellable = true, at=@At(value="INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endEarly(Level p_41218_, Player p_41219_, InteractionHand p_41220_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemstack) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack)) {
            p_41219_.awardStat(Stats.ITEM_USED.get(this));
            cir.setReturnValue(InteractionResultHolder.sidedSuccess(p_41219_.getItemInHand(p_41220_), p_41218_.isClientSide()));
        }
    }
}
