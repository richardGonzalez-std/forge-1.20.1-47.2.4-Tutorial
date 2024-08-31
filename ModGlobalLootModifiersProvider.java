package net.richard.tutorialmod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.item.ModItems;
import net.richard.tutorialmod.loot.AddItemModifiers;
import net.richard.tutorialmod.loot.AddSusSandModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    ItemLike ruby = ModItems.RUBY_SWORD.get();
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, TutorialMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("pine_cone_from_grass", new AddItemModifiers(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()
        }, ModItems.PINE_CONE.get()));
        add("creeper_head_from_creeper", new AddItemModifiers(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ruby)).build(),
                LootItemKilledByPlayerCondition.killedByPlayer().build(),
        }, Items.CREEPER_HEAD.asItem()));
        add("strawberry_from_suspicious_sand", new AddSusSandModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        },ModItems.STRAWBERRY.get()));

    }
}
