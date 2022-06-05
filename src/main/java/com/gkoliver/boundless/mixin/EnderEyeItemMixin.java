package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnderEyeItem.class)
public abstract class EnderEyeItemMixin extends Item {

    public EnderEyeItemMixin(Properties p_41383_) {
        super(p_41383_);
    }
    @Redirect(method="useOn", at=@At(value="INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void shrinkConditional(ItemStack instance, int p_41775_) {
        if (!(0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),instance))) {
            instance.shrink(p_41775_);
        }
    }

    //INVOKEVIRTUAL net/minecraft/world/level/Level.playSound
    @Inject(method="use", at=@At(value = "INVOKE", shift=At.Shift.AFTER, target = "Lnet/minecraft/world/level/Level;levelEvent(Lnet/minecraft/world/entity/player/Player;ILnet/minecraft/core/BlockPos;I)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void useInject(Level p_41184_, Player p_41185_, InteractionHand p_41186_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemstack, HitResult hitresult, ServerLevel serverlevel, BlockPos blockpos, EyeOfEnder eyeofender) {
        if (0 < EnchantmentHelper.getItemEnchantmentLevel(BoundlessEnchantments.BOUNDLESS.get(),itemstack)) {

            p_41185_.awardStat(Stats.ITEM_USED.get(this));
            p_41185_.swing(p_41186_, true);
            cir.setReturnValue(InteractionResultHolder.success(itemstack));
        }
    }
}
