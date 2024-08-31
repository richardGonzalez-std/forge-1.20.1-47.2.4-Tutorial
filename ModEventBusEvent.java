package net.richard.tutorialmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.entity.client.ModModelLayers;
import net.richard.tutorialmod.entity.client.RhinoModel;
import net.richard.tutorialmod.entity.client.penguin;

@Mod.EventBusSubscriber(modid= TutorialMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvent {
@SubscribeEvent
    public static void RegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PENGUIN_LAYER, penguin::createBodyLayer);
    }

}
