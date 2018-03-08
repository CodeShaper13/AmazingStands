package com.codeshaper.amazingstands.armorStandRendering;

import com.codeshaper.amazingstands.Util;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class NewPlayerModel extends ModelPlayer {

	public NewPlayerModel(float modelSize, boolean smallArmsIn) {
		super(modelSize, smallArmsIn);
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTickTime) {
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		Util.func(entityIn, this);
	}
}