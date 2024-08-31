package net.richard.tutorialmod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.entity.custom.PenguinEntity;
import net.richard.tutorialmod.entity.custom.RhinoEntity;

public class ModMobs {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino",
                    ()-> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                            .sized(2.5f,2.5f).build("rhino"));

    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin",
                    ()-> EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE).sized(1.5f,1.5f).build("penguin"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
