package net.richard.tutorialmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.client.ThirstHubOverlay;
import net.richard.tutorialmod.networking.ModNetworking;
import net.richard.tutorialmod.networking.packet.ExampleC2SPacket;
import net.richard.tutorialmod.networking.packet.ThirstC2SPacket;
import net.richard.tutorialmod.utils.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.EXAMPLE_MAPPING.consumeClick()){
                ModNetworking.sendToServer(new ThirstC2SPacket());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID,value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.EXAMPLE_MAPPING);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("thirst", ThirstHubOverlay.HUD_THIRST);
        }
    }


}
