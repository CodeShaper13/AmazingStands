package com.codeshaper.amazingstands.armorStandRendering;

import java.util.Iterator;

import com.codeshaper.amazingstands.Util;
import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArmorStand;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderArmorStandNew extends RenderArmorStand {
	
	// Make sure these are correct when vanilla updates, as they must be hard coded
    private static final ResourceLocation armorStandTexture = new ResourceLocation("textures/entity/armorstand/wood.png");
    private static final ResourceLocation skeletonTexture = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    private static final ResourceLocation witherSkeletonTexture = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
    private static final ResourceLocation zombieTexture = new ResourceLocation("textures/entity/zombie/zombie.png");
    private static final ResourceLocation creeperTexture = new ResourceLocation("textures/entity/creeper/creeper.png");
    private static final ResourceLocation steveTexture = new ResourceLocation("textures/entity/steve.png");
    private static final ResourceLocation alexTexture = new ResourceLocation("textures/entity/alex.png");

    private static final ModelArmorStand armorStandModel = new ModelArmorStand();
    private static final ModelSkeleton skeletonModel = new ModelSkeleton() {
    	@Override
    	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) { }

    	@Override
    	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    			float headPitch, float scaleFactor, Entity entityIn) {
    		Util.func(entityIn, this);
    	}
    };
    private static final ModelZombie zombieModel = new ModelZombie() {
    	@Override
    	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) { }

    	@Override
    	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    			float headPitch, float scaleFactor, Entity entityIn) {
    		Util.func(entityIn, this);
    	}
    };
    private static final ModelPlayer steveModel = new ModelPlayer(0.0f, false) {
    	@Override
    	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) { }

    	@Override
    	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    			float headPitch, float scaleFactor, Entity entityIn) {
    		Util.func(entityIn, this);
    	}
    };
    private static final ModelPlayer alexModel = new ModelPlayer(0.0f, true) {
    	@Override
    	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) { }

    	@Override
    	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    			float headPitch, float scaleFactor, Entity entityIn) {
    		Util.func(entityIn, this);
    	}
    };
    private static final ModelCreeper creeperModel = new ModelCreeper() {
    	@Override
    	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) { }

    	@Override
    	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    			float headPitch, float scaleFactor, Entity entityIn) {
    		EntityArmorStand entityarmorstand = (EntityArmorStand)entityIn;
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
    
	public RenderArmorStandNew(RenderManager manager) {
		super(manager);
		
		// Hacky fix for the two layers.
		for(Iterator<LayerRenderer<EntityArmorStand>> iterator = this.layerRenderers.iterator(); iterator.hasNext();) {
			LayerRenderer<?> lr = iterator.next(); // this.layerRenderers.get(i);
			if(lr instanceof LayerCustomHead || lr instanceof LayerHeldItem) {
				iterator.remove();

			}
		}
        this.addLayer(new LayerHeldItem(new RenderLivingBase<EntityArmorStand>(manager, this.mainModel, this.shadowSize) {
			@Override
			protected ResourceLocation getEntityTexture(EntityArmorStand entity) {
				System.out.println("PROBLEM!!!!!");
				return null;
			}
			}));
		this.addLayer(new LayerCustomHead(((ModelBiped)this.mainModel).bipedHead));
		// End hacky fix.
		
		this.addLayer(new LayerArmorStandBasePlate(this));
	}

	@Override
	public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.mainModel = this.getModelForRender(entity);
		if(this.mainModel instanceof ModelArmorStand) {
			((ModelArmorStand)this.mainModel).standBase.isHidden = true;
		}
		
		if(this.mainModel instanceof ModelBiped) {
			boolean flag = entity.getShowArms();
			ModelBiped mb = ((ModelBiped)this.mainModel);
			mb.bipedLeftArm.isHidden = !flag;
			mb.bipedRightArm.isHidden = !flag;	
		}
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityArmorStand armorStand) {
		int i = armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId();
		switch(i) {
    		case 0: return armorStandTexture;
    		case 1: return skeletonTexture;
    		case 2: return witherSkeletonTexture;
    		case 3: return zombieTexture;
    		case 4: return steveTexture;
    		case 5: return creeperTexture;
    		default: return armorStandTexture;
    	}
	}
	
    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
	@Override
    protected void preRenderCallback(EntityArmorStand entitylivingbaseIn, float partialTickTime) {
		if(entitylivingbaseIn.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId() == 2) {
			GlStateManager.scale(1.2F, 1.2F, 1.2F);			
		}
    }	
	
	private ModelBase getModelForRender(EntityArmorStand armorStand) {		
		switch(armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId()) {
			case 0: return armorStandModel;
			case 1: case 2: return skeletonModel;
			case 3: return zombieModel;
			case 4: return steveModel;
			case 5: return creeperModel;
			default: return armorStandModel;
		}
	}
}
