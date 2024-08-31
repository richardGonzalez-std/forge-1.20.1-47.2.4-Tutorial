package net.richard.tutorialmod.enchantment;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import static net.minecraft.world.item.enchantment.EnchantmentCategory.ARMOR_FEET;

public class speedEnchantment extends Enchantment {

    final String Description = "Apply it to a boots to boost your speed";
    public speedEnchantment(Rarity pRarity,  EquipmentSlot... pApplicableSlots) {
        super(pRarity, ARMOR_FEET,pApplicableSlots);
    }

    public float getSpeed(ServerPlayer serverPlayer, int level){
       if(serverPlayer.getInventory().getArmor(3).isEnchanted()){
           if(level == 1){
               return 1.0F + (float) 0 * 0.25F;
           }else if(level == 2){
            return 1.0F + (float)Math.max(0,level-1)* 0.28F;
           }else{
               return 1.0F + (float)Math.max(0,level-1)* 0.30F;

           }
       }
       return getSpeed(serverPlayer,level);
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
