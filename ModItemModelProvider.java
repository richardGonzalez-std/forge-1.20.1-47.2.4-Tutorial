package net.richard.tutorialmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    private static final LinkedHashMap<ResourceKey<TrimMaterial>,Float> trimMaterials = new LinkedHashMap<>();

    static{
        trimMaterials.put(TrimMaterials.AMETHYST,0.1f);
        trimMaterials.put(TrimMaterials.IRON,0.2f);
        trimMaterials.put(TrimMaterials.DIAMOND,0.3f);
        trimMaterials.put(TrimMaterials.GOLD,0.4f);
        trimMaterials.put(TrimMaterials.EMERALD,0.5f);
        trimMaterials.put(TrimMaterials.COPPER,0.6f);
        trimMaterials.put(TrimMaterials.LAPIS,0.7f);
        trimMaterials.put(TrimMaterials.NETHERITE,0.8f);
        trimMaterials.put(TrimMaterials.QUARTZ,0.9f);
        trimMaterials.put(TrimMaterials.REDSTONE,1.0f);

    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = TutorialMod.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
    @Override
    protected void registerModels() {
        simpleItems(ModItems.METAL_DETECTOR);
        simpleItems(ModItems.PINE_CONE);
        simpleItems(ModItems.PINEDARINE);
        simpleItems(ModItems.RAW_SAPPHIRE);
        simpleItems(ModItems.SAPPHIRE);
        simpleItems(ModItems.STRAWBERRY);
        handleItems(ModItems.SAPPHIRE_HOE);
        handleItems(ModItems.SAPPHIRE_AXE);
        handleItems(ModItems.SAPPHIRE_PICKAXE);
        handleItems(ModItems.SAPPHIRE_SWORD);
        handleItems(ModItems.SAPPHIRE_SHOVEL);
        handleItems(ModItems.RUBY_SWORD);
        simpleItems(ModItems.IRON_BOW);
        simpleItems(ModItems.STRAWBERRY_SEEDS);
        simpleItems(ModItems.CORN_SEEDS);
        simpleItems(ModItems.CORN);
        simpleItems(ModItems.LIFE_CHECKER);
        simpleItems(ModItems.BAR_BRAWL_MUSIC_DISC);
        simpleBlockItem(ModBlocks.RUBY_DOOR);

        fenceItem(ModBlocks.RUBY_FENCE, ModBlocks.SAPPHIRE_BLOCK);
        wallItem(ModBlocks.RUBY_WALL, ModBlocks.SAPPHIRE_BLOCK);
        buttonItem(ModBlocks.RUBY_BUTTON, ModBlocks.SAPPHIRE_BLOCK);

        evenSimplerBlockItem(ModBlocks.RUBY_STAIRS);
        evenSimplerBlockItem(ModBlocks.RUBY_SLAB);
        evenSimplerBlockItem(ModBlocks.RUBY_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.RUBY_FENCE_GATE);

        trapdoorItem(ModBlocks.RUBY_TRAPDOOR);

        trimmedArmorItem(ModItems.SAPPHIRE_LEGGINGS);
        trimmedArmorItem(ModItems.SAPPHIRE_BOOTS);
        trimmedArmorItem(ModItems.SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(ModItems.SAPPHIRE_HELMET);

        withExistingParent(ModItems.RHINO_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        blockItemBlockTexture(ModBlocks.CATMINT);
    }

    private ItemModelBuilder handleItems (RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID,"item/"+item.getId().getPath()));
    }
    private ItemModelBuilder simpleItems (RegistryObject<Item> itemRegistryObject){
        return withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID, "item/"+itemRegistryObject.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block){
        this.withExistingParent(TutorialMod.MOD_ID+":"+ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/"+ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void fenceItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(TutorialMod.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(TutorialMod.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(TutorialMod.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID, "item/"+ item.getId().getPath()));
    }

    private ItemModelBuilder blockItemBlockTexture(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID, "block/"+ item.getId().getPath()));
    }

}
