package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessConfig;
import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob {
    protected AnimalMixin(EntityType<? extends AgeableMob> p_146738_, Level p_146739_) {
        super(p_146738_, p_146739_);
    }
    @Redirect(method = "usePlayerItem", at = @At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean redirInstabuild(Abilities instance, Player p_148715_, InteractionHand p_148716_, ItemStack p_148717_) {
        return instance.instabuild || (BoundlessConfig.feeding()&&BoundlessEnchantment.hasBoundless(p_148717_));
    }
}
