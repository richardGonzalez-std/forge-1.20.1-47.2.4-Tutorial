package net.richard.tutorialmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.repository.Pack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(),new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(),ModLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(),new ModBlockStateProvider(packOutput,existingFileHelper));
        generator.addProvider(event.includeClient(),new ModItemModelProvider(packOutput,existingFileHelper));


        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput,lookupProvider,existingFileHelper));

        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput,lookupProvider,
                blockTagGenerator.contentsGetter(),existingFileHelper));


        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModPoiTagTypeProvider(packOutput, lookupProvider, existingFileHelper));


    }
}
