package net.richard.tutorialmod.loot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;

import java.util.Set;

public class CustomLootItemCondition1 implements LootItemCondition {
    final Item item;
    final Player player;

    public CustomLootItemCondition1(Item item, Player player) {
        this.item = item;
        this.player = player;
    }

    @Override
    public LootItemConditionType getType() {
        return LootItemConditions.MATCH_TOOL;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return false;
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return LootItemCondition.super.getReferencedContextParams();
    }
}
