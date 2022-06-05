package com.gkoliver.boundless.mixin;

import com.gkoliver.boundless.BoundlessConfig;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin extends Item {
    public BlockItemMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Redirect(method="place", at=@At(value="FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;instabuild:Z"))
    private boolean redirCreative(Abilities instance) {
        return (this.getClass().equals(SolidBucketItem.class) && BoundlessConfig.buckets()) || instance.instabuild;
    }

}
