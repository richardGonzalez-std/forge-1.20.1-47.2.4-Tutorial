package net.richard.tutorialmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.item.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.SAPPHIRE_ORE.get()).addTags(Tags.Blocks.ORES)
                .remove(Blocks.COAL_ORE)
                .remove(Blocks.DEEPSLATE_COAL_ORE)
                .remove(Blocks.DEEPSLATE_LAPIS_ORE)
                .remove(Blocks.REDSTONE_ORE)
                .remove(Blocks.DEEPSLATE_REDSTONE_ORE)
                .remove(Blocks.LAPIS_ORE);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.RUST_IRON.get());

        this.tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_CATMINT.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.RUST_IRON.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.END_STONE_SAPPHIRE_ORE.get())
                .add(ModBlocks.SOUND_BLOCK.get())
                .add(ModBlocks.RUBY_BUTTON.get())
                .add(ModBlocks.RUBY_FENCE.get())
                .add(ModBlocks.RUBY_FENCE_GATE.get())
                .add(ModBlocks.RUBY_STAIRS.get())
                .add(ModBlocks.RUBY_WALL.get())
                .add(ModBlocks.RUBY_PRESSURE_PLATE.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.RUBY_DOOR.get())
                .add(ModBlocks.RUBY_TRAPDOOR.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.RUBY_BUTTON.get())
                .add(ModBlocks.RUBY_FENCE.get())
                .add(ModBlocks.RUBY_FENCE_GATE.get())
                .add(ModBlocks.RUBY_STAIRS.get())
                .add(ModBlocks.RUBY_WALL.get())
                .add(ModBlocks.RUBY_PRESSURE_PLATE.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.RUBY_DOOR.get())
                .add(ModBlocks.RUBY_TRAPDOOR.get());

        this.tag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.RUBY_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.RUBY_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.RUBY_WALL.get());
    }
}
