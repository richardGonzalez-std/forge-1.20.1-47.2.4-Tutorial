package net.richard.tutorialmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;

import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.item.ModItems;
import net.richard.tutorialmod.item.custom.CustomVillagerType;
import net.richard.tutorialmod.networking.ModNetworking;
import net.richard.tutorialmod.networking.packet.ThirstSyncDataS2CPacket;
import net.richard.tutorialmod.thirst.PlayerThirst;
import net.richard.tutorialmod.thirst.PlayerThirstProvider;

import java.util.List;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent tradesEvent){

        if(tradesEvent.getType() == VillagerProfession.FARMER){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();

            trades.get(1).add((pTrader, pRandom)  -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,2),
                    new ItemStack(ModItems.STRAWBERRY.get(),12),
                    10,8,0.035f
            ));
            trades.get(1).add((pTrader, pRandom)  -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,64),
                    new ItemStack(Items.EMERALD,64),
                    new ItemStack(ModItems.LIFE_CHECKER.get(),1),
                    10,8,0.035f
            ));

            trades.get(1).add((pTrader, pRandom)  -> new MerchantOffer(
                    new ItemStack(ModItems.RAW_SAPPHIRE.get(),32),
                    new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get(),4),
                    10,8,0.035f
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,5),
                    new ItemStack(ModItems.CORN.get(),6),
                    10,5,0.035f
            ));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND,3),
                    new ItemStack(ModItems.METAL_DETECTOR.get(),1),
                    3,9,0.035f
            ));
        } else if(tradesEvent.getType() == VillagerProfession.LIBRARIAN){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();
            ItemStack ThornBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.THORNS,2));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND,7),
                    ThornBook,
                    2 ,10, 0.0245f
            ));
        } else if (tradesEvent.getType() == CustomVillagerType.SOUND_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.GOAT_HORN,2),
                    new ItemStack(ModItems.BAR_BRAWL_MUSIC_DISC.get(),1),
                    2,15,0.0521f
            ));
        }
    }
    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent wanderEvent){
        List<VillagerTrades.ItemListing> genericTrades = wanderEvent.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = wanderEvent.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.PAPER,6),
                new ItemStack(Items.MAP,1),
                5, 2,0.054f
        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(ModItems.STRAWBERRY.get(),6),
                new ItemStack(ModItems.STRAWBERRY_SEEDS.get(),2),
                12, 6 , 0.043f
        ));
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()){
                event.addCapability(new ResourceLocation(TutorialMod.MOD_ID,"properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore->{
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore->{
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapability(RegisterCapabilitiesEvent event){
        event.register(PlayerThirst.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.side == LogicalSide.SERVER){
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst->{
                if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f){
                    thirst.subThirst(1);
                    ModNetworking.sendToPlayer(new ThirstSyncDataS2CPacket(thirst.getThirst()), (ServerPlayer) event.player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
        if(!event.getLevel().isClientSide){
            if(event.getEntity() instanceof ServerPlayer player){
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst->{
                    ModNetworking.sendToPlayer(new ThirstSyncDataS2CPacket(thirst.getThirst()),player);
                });
            }
        }
    }
}
