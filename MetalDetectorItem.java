package net.richard.tutorialmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.richard.tutorialmod.item.util.ModTags;
import net.richard.tutorialmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(!pContext.getLevel().isClientSide){
            BlockPos blockPos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= blockPos.getY() +64; i++){
                BlockState state = pContext.getLevel().getBlockState(blockPos.below(i));

                if(isValuable(state)){
                    outputValuableCoordinates(blockPos.below(i),player,state.getBlock());
                    foundBlock = true;

                    pContext.getLevel().playSeededSound(null,blockPos.getX(),blockPos.getY(),blockPos.getZ(),
                            ModSounds.METAL_DETECTOR_ORE_FOUND.get(), SoundSource.BLOCKS,1f,1f,0);
                    break;
                }else if(!isValuable(state)){

                }
            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No found Valuable!"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player ->
                player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(Component.literal("Make sweet sounds when right click").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private boolean isValuable(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found "+ I18n.get(block.getDescriptionId()) + " at "
        + below.getX() + ", " + below.getY() + ", " + below.getZ()+ ")"));
    }
}
