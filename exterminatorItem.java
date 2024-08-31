package net.richard.tutorialmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class exterminatorItem extends Item {
    public exterminatorItem(Properties properties) {
        super(properties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        final var TotalCows = pLevel.getNearbyEntities(Cow.class, TargetingConditions.forNonCombat(), pPlayer, pPlayer.getBoundingBox().inflate(5));
        if(pLevel.isClientSide){
            if(pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this.asItem()){
                if(TotalCows.size()>5){
                    if(pPlayer.getInventory().getFreeSlot()==0 || pPlayer.getInventory().getItem(0).getItem() == Items.ACACIA_FENCE){
                        pPlayer.getInventory().add(0, Items.ACACIA_FENCE.getDefaultInstance());
                    }else{
                        pPlayer.sendSystemMessage(Component.literal("You don't have free slot on your item Bar"));
                    }


            }else{
                pPlayer.sendSystemMessage(Component.literal("No hay vacas"));
            }



            }

        }


        return super.use(pLevel, pPlayer, pUsedHand);

    }
}
