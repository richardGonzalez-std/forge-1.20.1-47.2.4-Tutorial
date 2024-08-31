package net.richard.tutorialmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
     this.tag(ItemTags.TRIMMABLE_ARMOR)
             .add(ModItems.SAPPHIRE_HELMET.get())
             .add(ModItems.SAPPHIRE_BOOTS.get())
             .add(ModItems.SAPPHIRE_CHESTPLATE.get())
             .add(ModItems.SAPPHIRE_LEGGINGS.get());
     this.tag(ItemTags.MUSIC_DISCS)
             .add(ModItems.BAR_BRAWL_MUSIC_DISC.get());
    }
}
