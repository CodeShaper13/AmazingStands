package com.codeshaper.amazingstands.armorStandRendering;

import com.codeshaper.amazingstands.Util;
import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;
import com.codeshaper.amazingstands.renderer.entity.layers.LayerArmorStandBasePlate;
import com.codeshaper.amazingstands.renderer.entity.layers.LayerArmorStandEndermanEyes;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArmorStand;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;

public class RenderArmorStandNew extends RenderArmorStand {

	// Make sure these are correct when vanilla updates, as they must be hard coded.
	private static final ResourceLocation armorStandTexture = new ResourceLocation("textures/entity/armorstand/wood.png");
	private static final ResourceLocation skeletonTexture = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation witherSkeletonTexture = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	private static final ResourceLocation zombieTexture = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation creeperTexture = new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation steveTexture = new ResourceLocation("textures/entity/steve.png");
	private static final ResourceLocation alexTexture = new ResourceLocation("textures/entity/alex.png");
	private static final ResourceLocation endermanTexture = new ResourceLocation("textures/entity/enderman/enderman.png");

	private static final ModelArmorStand armorStandModel = new ModelArmorStand();
	private static final ModelSkeleton skeletonModel = new ModelSkeleton() {
		@Override
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
				float partialTickTime) {
		}

		@Override
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			Util.func(entityIn, this);
		}
	};
	private static final ModelZombie zombieModel = new ModelZombie() {
		@Override
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
				float partialTickTime) {
		}

		@Override
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			Util.func(entityIn, this);
		}

		@Override
		public void setModelAttributes(ModelBase model) {
			super.setModelAttributes(model);

			this.isChild = false;
		}
	};
	private static final ModelPlayer steveModel = new NewPlayerModel(0.0f, false);
	private static final ModelPlayer alexModel = new NewPlayerModel(0.0f, true);
	private static final ModelCreeper creeperModel = new ModelCreeper() {
		@Override
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
				float partialTickTime) {
		}

		@Override
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
			this.head.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
			this.head.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
			this.head.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
			this.body.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
			this.body.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
			this.body.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
			// Left arm.
			this.leg4.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
			this.leg4.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
			this.leg4.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
			// Right arm.
			this.leg4.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
			this.leg4.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
			this.leg4.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
			// Left leg.
			this.leg2.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
			this.leg2.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
			this.leg2.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
			// Right leg.
			this.leg1.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
			this.leg1.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
			this.leg1.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
		}
	};
	public static final ModelEnderman endermanModel = new ModelEnderman(0.0f) {		
		@Override
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
				float partialTickTime) {
		}

		@Override
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			Util.func(entityIn, this);
			this.bipedRightArm.rotationPointX = -5.0f;
		}
	};
	
	public RenderArmorStandNew(RenderManager manager) {
		super(manager);
		
		this.addLayer(new LayerArmorStandEndermanEyes(this));
		this.addLayer(new LayerArmorStandBasePlate(this));
	}
	
	@Override
	public ModelArmorStand getMainModel() {
		return RenderArmorStandNew.armorStandModel;
	}

	@Override
	public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
		boolean flag = entity.getShowArms();
		this.mainModel = this.getModelForRender(entity);
		
		RenderArmorStandNew.armorStandModel.bipedLeftArm.isHidden = !flag;
		RenderArmorStandNew.armorStandModel.bipedRightArm.isHidden = !flag;
		
		RenderArmorStandNew.armorStandModel.isChild = entity.isChild();
		
		// Hide vanilla base if the model is an armor stand.
		if (this.mainModel instanceof ModelArmorStand) {
			((ModelArmorStand) this.mainModel).standBase.isHidden = true;
		}

		// Set visibility of model arms.
		if (this.mainModel instanceof ModelBiped) {
			ModelBiped mb = ((ModelBiped) this.mainModel);
			mb.bipedLeftArm.isHidden = !flag;
			mb.bipedRightArm.isHidden = !flag;
		}
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityArmorStand armorStand) {
		switch (armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId()) {
		case 0:
			return armorStandTexture;
		case 1:
			return skeletonTexture;
		case 2:
			return witherSkeletonTexture;
		case 3:
			return zombieTexture;
		case 4:
			return steveTexture;
		case 5:
			return creeperTexture;
		case 6:
			return endermanTexture;
		default:
			return armorStandTexture;
		}
	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	@Override
	protected void preRenderCallback(EntityArmorStand entitylivingbaseIn, float partialTickTime) {
		// Scale model if it's a Wither Skeleton.
		if (entitylivingbaseIn.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId() == 2) {
			GlStateManager.scale(1.2F, 1.2F, 1.2F);
		}
		
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	/**
	 * Returns a model to render with based on the Armor Stands model id.
	 */
	private ModelBase getModelForRender(EntityArmorStand armorStand) {
		switch (armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId()) {
		case 0:
			return armorStandModel;
		case 1:
		case 2:
			return skeletonModel;
		case 3:
			return zombieModel;
		case 4:
			return steveModel;
		case 5:
			return creeperModel;
		case 6:
			return endermanModel;
		default:
			return armorStandModel;
		}
	}
}
