package net.richard.tutorialmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.custom.CornCropBlock;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.block.custom.strawberryCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.RUST_IRON);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);

        simpleBlockWithItem(ModBlocks.CATMINT.get(),models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(),models().singleTexture("potted_catmint",
                new ResourceLocation("flower_pot_cross"),"plant",
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

        stairsBlock(((StairBlock) ModBlocks.RUBY_STAIRS.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        slabBlock(((SlabBlock) ModBlocks.RUBY_SLAB.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.RUBY_BUTTON.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.RUBY_PRESSURE_PLATE.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.RUBY_FENCE.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.RUBY_FENCE_GATE.get()),blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        wallBlock(((WallBlock) ModBlocks.RUBY_WALL.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));


        doorBlockWithRenderType(((DoorBlock) ModBlocks.RUBY_DOOR.get()),modLoc("block/ruby_door_bottom"),
                modLoc("block/ruby_door_top"),"cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.RUBY_TRAPDOOR.get()),modLoc("block/ruby_trapdoor"),
                true,"cutout");

        makeStrawberryCrop((CropBlock)ModBlocks.STRAWBERRY_CROP.get());
        makeCornCrop((CropBlock) ModBlocks.CORN_CROP.get());

        simpleBlockWithItem(ModBlocks.GEM_POLISHING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));
    }

    private void makeCornCrop(CropBlock cropBlock) {
        Function<BlockState,ConfiguredModel[]> function = state -> corn_states(state,cropBlock);

        getVariantBuilder(cropBlock).forAllStates(function);
    }

    private ConfiguredModel[] corn_states(BlockState state, CropBlock cropBlock) {

        ConfiguredModel[] models = new ConfiguredModel[1];

        models[0] = new ConfiguredModel(models().crop("corn_stage_" + state.getValue(((CornCropBlock) cropBlock).getAgeProperty()),
                new ResourceLocation(TutorialMod.MOD_ID, "block/"+ "corn_stage_" + state.getValue(((CornCropBlock) cropBlock).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private void makeStrawberryCrop(CropBlock cropBlock) {

        Function<BlockState, ConfiguredModel[]> function = state -> strawberry_states(state,cropBlock);

        getVariantBuilder(cropBlock).forAllStates(function);

    }

    private void blockWithItem (RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private ConfiguredModel[] strawberry_states(BlockState state, CropBlock cropBlock){
        ConfiguredModel[] models = new ConfiguredModel[1];

        models[0] = new ConfiguredModel(models().crop("strawberry_stage" + state.getValue(((strawberryCropBlock) cropBlock).getAgeProperty()),
                new ResourceLocation(TutorialMod.MOD_ID, "block/" + "strawberry_stage" + state.getValue(((strawberryCropBlock) cropBlock)
                        .getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
