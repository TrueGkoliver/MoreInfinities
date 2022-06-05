package com.gkoliver.boundless.mixin.dispenser;

import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = {"net.minecraft.core.dispenser.DispenseItemBehavior$3"})
public abstract class SpectralArrowDispenseBehaviorMixin {
    @Redirect(method="getProjectile", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;pickup:Lnet/minecraft/world/entity/projectile/AbstractArrow$Pickup;", opcode = Opcodes.PUTFIELD))
    private void changePickup(AbstractArrow instance, AbstractArrow.Pickup value, Level p_123360_, Position p_123361_, ItemStack p_123362_) {
        instance.pickup = BoundlessEnchantment.hasBoundless(p_123362_) ? AbstractArrow.Pickup.CREATIVE_ONLY : AbstractArrow.Pickup.ALLOWED;
    }
}
