package net.richard.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class ModFood {

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2).fast().saturationMod(.2f)
            .effect(()-> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200), 0.1f).build();

    public static final FoodProperties PINEDARINE = new FoodProperties.Builder().nutrition(4).alwaysEat().fast().build();

    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(1).effect(()->new MobEffectInstance(MobEffects.HEAL,100),0.3f).build();
}
