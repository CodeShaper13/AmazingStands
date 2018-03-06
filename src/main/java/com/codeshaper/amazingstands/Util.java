package com.codeshaper.amazingstands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class Util {

	public static void func(Entity entity, ModelBiped model) {
		EntityArmorStand entityarmorstand = (EntityArmorStand)entity;
		model.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
		model.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
		model.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
		model.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
		model.bipedBody.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
		model.bipedBody.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
		model.bipedBody.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
		model.bipedLeftArm.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
		model.bipedLeftArm.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
		model.bipedLeftArm.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
		model.bipedRightArm.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
		model.bipedRightArm.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
		model.bipedRightArm.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
		model.bipedLeftLeg.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
		model.bipedLeftLeg.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
		model.bipedLeftLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
		model.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
		model.bipedRightLeg.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
		model.bipedRightLeg.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
		model.bipedRightLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
		model.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
		ModelBase.copyModelAngles(model.bipedHead, model.bipedHeadwear);
	}
}
