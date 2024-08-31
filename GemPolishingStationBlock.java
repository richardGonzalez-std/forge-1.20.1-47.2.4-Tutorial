package net.richard.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.richard.tutorialmod.block.entity.GemPolishingStationBlockEntity;
import net.richard.tutorialmod.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class GemPolishingStationBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE= Block.box(0,0,0,16,12,16);
    public GemPolishingStationBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity block = pLevel.getBlockEntity(pPos);
            if(block instanceof GemPolishingStationBlockEntity){
                ((GemPolishingStationBlockEntity) block).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if(blockEntity instanceof GemPolishingStationBlockEntity){
                    NetworkHooks.openScreen(((ServerPlayer) pPlayer), (GemPolishingStationBlockEntity) blockEntity, pPos);
                }else{
                    throw new IllegalStateException("Our container provider is missing");
                }

        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

       if(pLevel.isClientSide){
           return null;
       }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.GEM_POLISHING_BE.get(),
                (plevel1,pPos,pState1,pBlockEntity)->
            pBlockEntity.tick(plevel1,pPos,pState1));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GemPolishingStationBlockEntity(pPos,pState);
    }
}
