package com.gkoliver.boundless;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BoundlessConfig {
    public static class Configuration {
        public final ForgeConfigSpec.BooleanValue FOODS;
        public final ForgeConfigSpec.BooleanValue ARROWS;
        public final ForgeConfigSpec.BooleanValue BUCKETS;
        public final ForgeConfigSpec.BooleanValue MOB_BUCKETS;
        public final ForgeConfigSpec.BooleanValue THROWABLES;
        public final ForgeConfigSpec.BooleanValue XP_BOTTLES;
        public final ForgeConfigSpec.BooleanValue ENDER_EYES;
        public final ForgeConfigSpec.BooleanValue POTIONS;
        public final ForgeConfigSpec.BooleanValue FIREWORKS;
        public final ForgeConfigSpec.BooleanValue BONE_MEAL;
        public final ForgeConfigSpec.BooleanValue FIRE_CHARGES;
        public final ForgeConfigSpec.BooleanValue TOTEMS;
        public final ForgeConfigSpec.BooleanValue ENDER_CRYSTALS;
        public final ForgeConfigSpec.BooleanValue MOB_EGGS;
        public final ForgeConfigSpec.BooleanValue BREEDING_FEEDING;
        public final ForgeConfigSpec.BooleanValue CRAFTING;
        public final ForgeConfigSpec.BooleanValue TNT;
        public Configuration(ForgeConfigSpec.Builder builderIn) {
            builderIn.comment("Boundless Items")
                    .push("general");
            FOODS = builderIn.comment("If true, foods can be enchanted with the Boundless enchant, making them consumable indefinitely.")
                    .define("foods_allowed", true);

            ARROWS = builderIn.comment("If true, arrows can be enchanted with the Boundless enchant, allowing them to be shot out of Dispensers forever. However, they can not be recovered.")
                    .define("arrows_allowed", true);

            BUCKETS = builderIn.comment("If true, buckets can be enchanted with the Boundless enchant, allowing infinite access to that respective liquid. Does not include mob buckets.")
                    .define("buckets_allowed", true);

            MOB_BUCKETS = builderIn.comment("If true, mob buckets can be enchanted with the Boundless enchant, allowing them to be placed forever.")
                    .define("mob_buckets_allowed", false);

            THROWABLES = builderIn.comment("If true, all throwables (snowballs, ender pearls, eggs) not otherwise touched on in this config will have the Boundless enchantment available to them.")
                    .define("throwables_allowed", true);

            XP_BOTTLES = builderIn.comment("If true, Bottles o' Enchanting will be able to have the Boundless enchantment applied to them.")
                    .define("xp_bottles_allowed", false);

            ENDER_EYES = builderIn.comment("If true, eyes of ender will be able to have the Boundless enchantment applied to them. This includes both their stronghold-finding use and their use in filling end portals.")
                    .define("ender_eyes_allowed", false);

            POTIONS = builderIn.comment("If true, all potions, including regular ones, splash ones, and lingering ones, will have the Boundless enchantment applied to them.")
                    .define("potions_allowed", true);

            BONE_MEAL = builderIn.comment("If true, bone meal will have the Boundless enchantment able to be applied to it.")
                    .define("bone_meal_allowed", false);

            FIREWORKS = builderIn.comment("If true, fireworks will have the Boundless enchantment able to be applied to them. This includes their elytra usages.")
                    .define("fireworks_allowed", true);

            FIRE_CHARGES = builderIn.comment("If true, fire charges will have the Boundless enchantment able to be applied to them.")
                    .define("fire_charges_allowed", true);
            TOTEMS = builderIn.comment("If true, Totems of Undying can be enchanted with the Boundless enchantment.")
                            .define("totems_allowed", false);
            ENDER_CRYSTALS = builderIn.comment("If true, Ender Crystals can be enchanted with the Boundless enchantment.")
                            .define("end_crystals_allowed", false);
            MOB_EGGS = builderIn.comment("If true, mob spawn eggs can be enchanted with the Boundless enchantment.")
                            .define("spawn_eggs_allowed", true);
            BREEDING_FEEDING = builderIn.comment("If true, foods will be able to breed mobs & animals if they are enchanted with the Boundless enchant without being consumed. This includes curing zombie villagers & feeding animals.")
                            .define("breeding_feeding", false);
            CRAFTING = builderIn.comment("If true, all Boundless items will be unconsumable in the crafting table - like a bucket, it will be left.")
                            .define("boundless_crafting", false);
            TNT = builderIn.comment("If true, TNT can be enchanted with Boundless. This will not effect block placement, simply dispenser placement.")
                    .define("tnt_allowed", false);
            builderIn.pop(1);
        }
    }
    public static final ForgeConfigSpec CONFIGSPEC;
    public static final Configuration CONFIG;
    static {
        final Pair<Configuration, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Configuration::new);
        CONFIGSPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
    public static boolean foods() {
        return CONFIG.FOODS.get();
    }
    public static boolean arrows() {
        return CONFIG.ARROWS.get();
    }
    public static boolean buckets() {
        return CONFIG.BUCKETS.get();
    }
    public static boolean throwables() {
        return CONFIG.THROWABLES.get();
    }
    public static boolean xpBottles() {
        return CONFIG.XP_BOTTLES.get();
    }
    public static boolean endereyes() {
        return CONFIG.ENDER_EYES.get();
    }
    public static boolean potions() {
        return CONFIG.POTIONS.get();
    }
    public static boolean mobBuckets() {
        return CONFIG.MOB_BUCKETS.get();
    }
    public static boolean boneMeal() {
        return CONFIG.BONE_MEAL.get();
    }
    public static boolean fireCharges() {
        return CONFIG.FIRE_CHARGES.get();
    }
    public static boolean totems() { return CONFIG.TOTEMS.get();}
    public static boolean mobEggs() { return CONFIG.MOB_EGGS.get();}
    public static boolean enderCrystals() { return CONFIG.ENDER_CRYSTALS.get();}
    public static boolean fireworks() {return CONFIG.FIREWORKS.get();}
    public static boolean feeding() {return CONFIG.BREEDING_FEEDING.get();}
    public static boolean crafting() {return CONFIG.CRAFTING.get();}
    public static boolean tnt() { return CONFIG.TNT.get(); }
}
