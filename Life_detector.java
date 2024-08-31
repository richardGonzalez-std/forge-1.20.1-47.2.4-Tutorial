package net.richard.tutorialmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import net.richard.tutorialmod.item.ModItems;

import java.util.List;

@SuppressWarnings("ALL")
public class Life_detector extends Item {


    public Life_detector(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;

    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if(pStack.getItem()==ModItems.LIFE_CHECKER.get()){
            checkLife(pInteractionTarget,pPlayer, InteractionHand.MAIN_HAND);

        }
        return InteractionResult.SUCCESS;
    }
    private void checkLife(LivingEntity livingEntity, Player player, InteractionHand hand){
       if(livingEntity instanceof Mob mob ){

        player.displayClientMessage(Component.literal(String.valueOf(Math.floor(mob.getHealth()))),true);

       }
    }





    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Right-click to get the info of a mob").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}