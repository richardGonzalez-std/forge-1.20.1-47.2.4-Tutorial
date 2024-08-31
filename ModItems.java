package net.richard.tutorialmod.item;


import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.entity.ModMobs;
import net.richard.tutorialmod.item.custom.*;
import net.richard.tutorialmod.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds"
            ,()-> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            ()-> new Item(new Item.Properties().food(ModFood.CORN)));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            ()-> new Item(new Item.Properties()));



    public static final RegistryObject<Item> LIFE_CHECKER = ITEMS.register("life_checker",
            ()-> new Life_detector(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            ()->new MetalDetectorItem(new Item.Properties().durability(30)));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            ()-> new SwordItem(ModToolsTier.SAPPHIRE,3,2.4f, new Item.Properties()));
    public static final RegistryObject<Item> PINEDARINE = ITEMS.register("pinedarine",
            ()-> new Item(new Item.Properties().food(ModFood.PINEDARINE)));

    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            ()-> new RecordItem(5, ModSounds.BAR_BRAWL,new Item.Properties().stacksTo(1), 2440));

    public  static final RegistryObject<Item> RHINO_SPAWN_EGG = ITEMS.register("rhino_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModMobs.RHINO,0x7e9680, 0xc5d1c5,
                    new Item.Properties()));
    public static final RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow",
            ()-> new BowItem(new Item.Properties().stacksTo(1).durability(300)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            ()-> new Item(new Item.Properties().food(ModFood.STRAWBERRY)));

    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            ()-> new SwordItem(ModToolsTier.SAPPHIRE,4,2, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            ()-> new PickaxeItem(ModToolsTier.SAPPHIRE,2,4,new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            ()-> new AxeItem(ModToolsTier.SAPPHIRE,2,4,new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            ()-> new SapphireArmorItem(SapphireMaterials.SAPPHIRE, ArmorItem.Type.HELMET,new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            ()-> new SapphireArmorItem(SapphireMaterials.SAPPHIRE, ArmorItem.Type.BOOTS,new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            ()-> new SapphireArmorItem(SapphireMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            ()-> new SapphireArmorItem(SapphireMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS,new Item.Properties()));
    public static final RegistryObject<Item> RADAR = ITEMS.register("radar",()->
        new exterminatorItem(new Item.Properties())
    );

    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            ()-> new HoeItem(ModToolsTier.SAPPHIRE,2,4,new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            ()-> new ShovelItem(ModToolsTier.SAPPHIRE,2,4,new Item.Properties()));

    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            ()-> new fuelItem(new Item.Properties(), 400));
}
