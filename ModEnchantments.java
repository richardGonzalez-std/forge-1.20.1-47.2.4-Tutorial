package net.richard.tutorialmod.item;

import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

import net.minecraft.world.item.enchantment.MendingEnchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.enchantment.speedEnchantment;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TutorialMod.MOD_ID);

    public static final RegistryObject<Enchantment> SPEED =
            ENCHANTMENTS.register("speed",
                    ()-> new speedEnchantment(Enchantment.Rarity.COMMON,EquipmentSlot.FEET));

    public static void register(IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }

}
