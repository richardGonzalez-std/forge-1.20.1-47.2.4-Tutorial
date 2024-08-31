package net.richard.tutorialmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.richard.tutorialmod.TutorialMod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModPoiTagTypeProvider extends PoiTypeTagsProvider {
    public ModPoiTagTypeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(new ResourceLocation(TutorialMod.MOD_ID, "sound_poi"));
    }
}
