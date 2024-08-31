package net.richard.tutorialmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.networking.packet.ThirstC2SPacket;
import net.richard.tutorialmod.networking.packet.ThirstSyncDataS2CPacket;

public class ModNetworking {
    private static SimpleChannel INSTANCE;
    private static int packetId =0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TutorialMod.MOD_ID,"messages"))
                .networkProtocolVersion(()->"1.0")
                .clientAcceptedVersions(s->true)
                .serverAcceptedVersions(s->true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ThirstC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ThirstC2SPacket::new)
                .encoder(ThirstC2SPacket::toBytes)
                .consumerMainThread(ThirstC2SPacket::handle)
                .add();

        net.messageBuilder(ThirstSyncDataS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstSyncDataS2CPacket::new)
                .encoder(ThirstSyncDataS2CPacket::toBytes)
                .consumerMainThread(ThirstSyncDataS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player), message);
    }
}
