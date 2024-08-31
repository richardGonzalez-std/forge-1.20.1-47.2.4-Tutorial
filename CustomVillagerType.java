package net.richard.tutorialmod.item.custom;


import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;

public class CustomVillagerType {

    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TutorialMod.MOD_ID);

    public static final RegistryObject<PoiType> SOUND_POI = POI_TYPE.register("sound_poi",
            ()-> new PoiType(ImmutableSet.copyOf(ModBlocks.SOUND_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1,1));


    public static  final RegistryObject<VillagerProfession> SOUND_MASTER = VILLAGER_PROFESSION.register("soundmaster",
            ()-> new VillagerProfession("soundmaster", holder-> holder.get() == SOUND_POI.get(),holder -> holder.get() == SOUND_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
    public static void register(IEventBus eventBus){
        POI_TYPE.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }

}
