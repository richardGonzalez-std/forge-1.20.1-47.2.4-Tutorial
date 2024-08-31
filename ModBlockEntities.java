package net.richard.tutorialmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_BE =
            BLOCK_ENTITY.register("gem_polishing_be", ()-> BlockEntityType.Builder.of(GemPolishingStationBlockEntity::new,
                    ModBlocks.GEM_POLISHING_STATION.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITY.register(eventBus);
    }
}
