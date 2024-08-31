package net.richard.tutorialmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.custom.CornCropBlock;
import net.richard.tutorialmod.block.custom.GemPolishingStationBlock;
import net.richard.tutorialmod.block.custom.SoundBlock;
import net.richard.tutorialmod.block.custom.strawberryCropBlock;
import net.richard.tutorialmod.item.ModItems;
import net.richard.tutorialmod.item.custom.RustIron;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registryObject("raw_sapphire_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registryObject("sapphire_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> SOUND_BLOCK = registryObject("sound_block",
            ()-> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)));


    public static final RegistryObject<Block> SAPPHIRE_ORE = registryObject("sapphire_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)
                    .strength(1.7f).requiresCorrectToolForDrops(), UniformInt.of(1,3)));

    public static final RegistryObject<Block> GEM_POLISHING_STATION = registryObject("gem_polishing_station",
            ()-> new GemPolishingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registryObject("deepslate_sapphire_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)
                    .strength(3f,8.2F).requiresCorrectToolForDrops(), UniformInt.of(1,3)));

    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registryObject("nether_sapphire_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .strength(3.5f).requiresCorrectToolForDrops(), UniformInt.of(1,3)));

    public static final RegistryObject<Block> END_STONE_SAPPHIRE_ORE = registryObject("end_stone_sapphire_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                    .strength(3.5f).requiresCorrectToolForDrops(), UniformInt.of(1,3)));


    public static final RegistryObject<Block> RUST_IRON = registryObject("rust_iron",
            ()-> new RustIron(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(4.3f,2.0f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RUBY_STAIRS = registryObject("ruby_stairs",
            ()-> new StairBlock(()-> ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RUBY_SLAB = registryObject("ruby_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RUBY_BUTTON = registryObject("ruby_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).sound(SoundType.AMETHYST),
                    BlockSetType.IRON, 10,false));
    public static final RegistryObject<Block> RUBY_PRESSURE_PLATE = registryObject("ruby_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).sound(SoundType.AMETHYST),
                    BlockSetType.IRON));
    public static final RegistryObject<Block> RUBY_FENCE = registryObject("ruby_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RUBY_FENCE_GATE = registryObject("ruby_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_FENCE), WoodType.CRIMSON));

    public static final RegistryObject<Block> RUBY_DOOR = registryObject("ruby_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR),BlockSetType.IRON));

    public static final RegistryObject<Block> RUBY_WALL = registryObject("ruby_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.RED_NETHER_BRICK_WALL)));

    public static final RegistryObject<Block> RUBY_TRAPDOOR = registryObject("ruby_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_TRAPDOOR).sound(SoundType.AZALEA),BlockSetType.CRIMSON));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            ()-> new strawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> CATMINT = registryObject("catmint",
            ()-> new FlowerBlock(()-> MobEffects.CONFUSION,3,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));

    public static final RegistryObject<Block> POTTED_CATMINT = BLOCKS.register("potted_catmint",
            ()-> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CATMINT,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            ()-> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));



    private static <T extends Block> RegistryObject<T> registryObject(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
