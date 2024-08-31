package net.richard.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.richard.tutorialmod.TutorialMod;

public class ThirstHubOverlay {

    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/filled_thirst.png");

    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = ((gui,guiGraphics,partialTick,width,height)->{
        int x = width/2;
        int y = height;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, EMPTY_THIRST);

        for (int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY_THIRST,x-94+(i*9),y-54,0,0,12,12,12,12);
        }
        RenderSystem.setShaderTexture(0,FILLED_THIRST);
        for (int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst()>i){
                guiGraphics.blit(FILLED_THIRST,x-94+(i*9),y-54,0,0,12,12,12,12);
            }else{
                break;
            }
        }
    });

}
