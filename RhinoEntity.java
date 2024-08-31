package net.richard.tutorialmod.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.richard.tutorialmod.entity.ModMobs;
import net.richard.tutorialmod.entity.ai.RhinoAttackGoal;
import net.richard.tutorialmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class RhinoEntity extends Animal {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.BOOLEAN);

    public RhinoEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide){
            setupAnimationStates();
        }
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <=0){
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }
        if(this.isAttacking() && this.attackAnimationTimeout<=0){
            attackAnimationTimeout= 80; // length in ticks of the animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking()){
            attackAnimationState.stop();
        }
    }


    public void setAttacking(boolean attacking){

        this.entityData.set(ATTACKING,attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING,false);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6F, 1f);

        }else{
            f = 0f;
        }
    this.walkAnimation.update(f,0.2f);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));

        this.goalSelector.addGoal(1, new RhinoAttackGoal(this,1.00,true));

        this.goalSelector.addGoal(1,new BreedGoal(this,1.25D));

        this.goalSelector.addGoal(2, new TemptGoal(this,1.2D, Ingredient.of(Items.COOKED_BEEF), false));

        this.goalSelector.addGoal(3, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this,1.1D));

        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class,3f));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.ATTACK_DAMAGE, 5.5D)
                .add(Attributes.ATTACK_KNOCKBACK,1.5F)
                .add(Attributes.ARMOR,50D)
                .add(Attributes.ARMOR_TOUGHNESS,0.259f)
                .add(Attributes.FOLLOW_RANGE,300f)
                .add(Attributes.MOVEMENT_SPEED,0.25D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModMobs.RHINO.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.COOKED_BEEF);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.RHINO_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
}
