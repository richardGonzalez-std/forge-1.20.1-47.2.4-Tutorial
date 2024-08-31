package net.richard.tutorialmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.richard.tutorialmod.item.ModItems;
import net.richard.tutorialmod.recipe.GemPolishingRecipe;
import net.richard.tutorialmod.recipe.ModRecipes;
import net.richard.tutorialmod.screen.GemPolishingStationMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemPolishingStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler stackHandler = new ItemStackHandler(2);

    private static final int INPUT_SLOT = 0;

    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;
    private int maxProgress = 134;


    public GemPolishingStationBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GEM_POLISHING_BE.get(),pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0-> GemPolishingStationBlockEntity.this.progress;
                    case 1-> GemPolishingStationBlockEntity.this.maxProgress;
                    default-> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {

                switch (pIndex){
                    case 0 -> GemPolishingStationBlockEntity.this.progress = pValue;
                    case 1 -> GemPolishingStationBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if(cap== ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->stackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    public void drops (){
        SimpleContainer inventory = new SimpleContainer(stackHandler.getSlots());
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            inventory.setItem(i,stackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tutorialmod.gem_polishing_station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GemPolishingStationMenu(pContainerId,pPlayerInventory,this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {

        pTag.put("inventory",stackHandler.serializeNBT());
        pTag.putInt("gem_polishing_station.progress",progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        stackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("gem_polishing_station.progress");
    }

    public void tick(Level plevel, BlockPos pPos, BlockState pState) {
            if(hasRecipe()){
                IncreaseCraftingProgress();
                setChanged(plevel,pPos,pState);

                if(hasProgressFinished()){
                    craftItem();
                    resetProgress();
                }
            }else{
                resetProgress();
            }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {

        Optional<GemPolishingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.stackHandler.extractItem(INPUT_SLOT,1,false);

        this.stackHandler.setStackInSlot(OUTPUT_SLOT,new ItemStack(result.getItem(),
                this.stackHandler.getStackInSlot(OUTPUT_SLOT).getCount()+result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress>= maxProgress;
    }

    private void IncreaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<GemPolishingRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<GemPolishingRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.stackHandler.getSlots());

        for (int i = 0; i < stackHandler.getSlots(); i++) {
            inventory.setItem(i,this.stackHandler.getStackInSlot(i));

        }

        return this.level.getRecipeManager().getRecipeFor(GemPolishingRecipe.Type.INSTANCE,inventory,level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.stackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || stackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.stackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= stackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
