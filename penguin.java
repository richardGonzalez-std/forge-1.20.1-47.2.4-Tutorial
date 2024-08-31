package net.richard.tutorialmod.entity.client;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports



import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.richard.tutorialmod.entity.animations.ModAnimationDefinition;
import net.richard.tutorialmod.entity.custom.PenguinEntity;

public class penguin<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart Penguin;
	private final ModelPart head;

	public penguin(ModelPart root) {
		this.Penguin = root.getChild("Penguin");
		this.head = Penguin.getChild("face");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Penguin = partdefinition.addOrReplaceChild("Penguin", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition torso = Penguin.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition alas = torso.addOrReplaceChild("alas", CubeListBuilder.create().texOffs(6, 9).addBox(-2.7F, -7.8F, -0.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(1.7F, -7.8F, -0.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition face = Penguin.addOrReplaceChild("face", CubeListBuilder.create().texOffs(12, 7).addBox(-1.0F, -10.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-0.5F, -9.0F, -0.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition low_body = Penguin.addOrReplaceChild("low_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition piernas = low_body.addOrReplaceChild("piernas", CubeListBuilder.create().texOffs(14, 0).addBox(-1.5F, -2.0F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 11).addBox(0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw,headPitch,ageInTicks);

		this.animateWalk(ModAnimationDefinition.penguin_walk, limbSwing,limbSwingAmount, 2f,2.5f);
		this.animate(((PenguinEntity) entity).idleAnimationState, ModAnimationDefinition.penguin_idle,ageInTicks,1f);
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0f,30.0f);
		headPitch = Mth.clamp(headPitch, -25.0f,45.0f);

		this.head.yRot = netHeadYaw * ((float)Math.PI /180F);
		this.head.xRot = headPitch * ((float)Math.PI /180F);
	}


	@Override
	public ModelPart root() {
		return Penguin;
	}

}