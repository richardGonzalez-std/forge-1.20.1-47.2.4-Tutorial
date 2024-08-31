package net.richard.tutorialmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.entity.ModMobs;
import net.richard.tutorialmod.entity.client.ModModelLayers;
import net.richard.tutorialmod.entity.client.RhinoModel;
import net.richard.tutorialmod.entity.custom.PenguinEntity;
import net.richard.tutorialmod.entity.custom.RhinoEntity;

@Mod.EventBusSubscriber(modid= TutorialMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class BusEventBusEvent {
@SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModMobs.RHINO.get(), RhinoEntity.createAttributes().build());
        event.put(ModMobs.PENGUIN.get(), PenguinEntity.createAttributes().build());
    }

}
