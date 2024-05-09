package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessConfig;
import com.gkoliver.boundless.BoundlessEnchantment;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets={"net.minecraft.world.entity.monster.ZombieVillager", "net.minecraft.world.entity.animal.Dolphin"})
public abstract class ZombieVillagerMixin {
    @Redirect(method="mobInteract", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean redirCreative(Abilities instance, Player p_34394_, InteractionHand p_34395_) {
        ItemStack i = p_34394_.getItemInHand(p_34395_);
        return instance.instabuild|| (BoundlessConfig.feeding()&&BoundlessEnchantment.hasBoundless(i));
    }
}
