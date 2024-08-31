package net.richard.tutorialmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.item.util.ModTags;

import java.util.List;

public class ModToolsTier {
    public static final Tier SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5,1500,5f,4f,25,
                    ModTags.Blocks.NEEDS_SAPPHIRE_TOOL,()-> Ingredient.of(ModItems.SAPPHIRE.get())),
            new ResourceLocation(TutorialMod.MOD_ID,"sapphire"), List.of(Tiers.NETHERITE), List.of()
    );
}
