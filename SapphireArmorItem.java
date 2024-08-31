package net.richard.tutorialmod.item.custom;

import com.google.common.collect.ImmutableMap;

import net.minecraft.world.effect.MobEffectInstance;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class SapphireArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_MOB_EFFECT_INSTANCE_MAP =
            (new ImmutableMap.Builder<ArmorMaterial,MobEffectInstance>())
                    .put(SapphireMaterials.SAPPHIRE,new MobEffectInstance(MobEffects.HEALTH_BOOST,MobEffectInstance.INFINITE_DURATION,5,false,true,
                            true)).build();
    public SapphireArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {


        if(!level.isClientSide){

            for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_MOB_EFFECT_INSTANCE_MAP.entrySet()){
                MobEffectInstance mapStatusEffect = entry.getValue();

                if(hasFullSuitOfArmorOn(player)){
                evaluateArmorEffects(player);
            }else{
                player.removeEffect(mapStatusEffect.getEffect());
            }
        }
        }
    }

    private void evaluateArmorEffects(Player player) {

        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_MOB_EFFECT_INSTANCE_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();


            if(hasCorrectArmorOn(mapArmorMaterial, player)){
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }

    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {

    boolean hasPlayerEffect= player.hasEffect(mapStatusEffect.getEffect());

        if (hasCorrectArmorOn(mapArmorMaterial, player)&&!hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }

    }

    private boolean hasCorrectArmorOn(ArmorMaterial mapArmorMaterial, Player player) {
        for(ItemStack armorStack : player.getInventory().armor){
            if(!(armorStack.getItem() instanceof ArmorItem)){
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && chestplate.getMaterial() == material
                && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate =  player.getInventory().getArmor(2);
        ItemStack helmet =  player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
}
