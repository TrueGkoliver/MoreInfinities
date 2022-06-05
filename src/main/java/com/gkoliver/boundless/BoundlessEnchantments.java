package com.gkoliver.boundless;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BoundlessEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "boundless");
    public static final RegistryObject<Enchantment> BOUNDLESS = ENCHANTMENTS.register("boundless", ()->new BoundlessEnchantment(Enchantment.Rarity.VERY_RARE));
}
