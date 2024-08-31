package net.richard.tutorialmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.richard.tutorialmod.TutorialMod;
import net.richard.tutorialmod.block.ModBlocks;
import net.richard.tutorialmod.recipe.GemPolishingRecipe;
import org.jetbrains.annotations.Nullable;

public class GemPolishingCategory implements IRecipeCategory<GemPolishingRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(TutorialMod.MOD_ID,"gem_polishing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/gui/gem_polishing_station_gui.png");

    public static final RecipeType<GemPolishingRecipe> GEM_POLISHING_RECIPE_TYPE =
            new RecipeType<>(ID, GemPolishingRecipe.class);


    private final IDrawable background;
    private final IDrawable icon;

    public GemPolishingCategory(IGuiHelper background) {
        this.background = background.createDrawable(TEXTURE,0,0,176,78);
        this.icon = background.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.GEM_POLISHING_STATION.get()));
    }

    @Override
    public RecipeType<GemPolishingRecipe> getRecipeType() {
        return GEM_POLISHING_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.tutorialmod.gem_polishing_station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon    ;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder,
                          GemPolishingRecipe gemPolishingRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,80,11)
                .addIngredients(gemPolishingRecipe.getIngredients().get(0));

        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,80,59)
                .addItemStack(gemPolishingRecipe.getResultItem(null));
    }
}
