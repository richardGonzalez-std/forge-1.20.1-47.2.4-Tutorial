package net.richard.tutorialmod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.richard.tutorialmod.TutorialMod;

public class ModModelLayers {

    public static final ModelLayerLocation RHINO_LAYER =
            new ModelLayerLocation(new ResourceLocation(TutorialMod.MOD_ID,"rhino_layer"),"main");

    public static final ModelLayerLocation PENGUIN_LAYER =
            new ModelLayerLocation(new ResourceLocation(TutorialMod.MOD_ID, "penguin_layer"),"main");
}
