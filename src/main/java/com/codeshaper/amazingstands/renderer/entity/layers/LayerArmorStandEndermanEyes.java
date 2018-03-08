package com.codeshaper.amazingstands.renderer.entity.layers;

import com.codeshaper.amazingstands.armorStandRendering.RenderArmorStandNew;
import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderArmorStand;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;

/**
 * A copy of {@linkplain LayerEndermanEyes} with different entity types.
 */
public class LayerArmorStandEndermanEyes implements LayerRenderer<EntityArmorStand> {
	
    private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
    private final RenderArmorStand armorStandRenderer;

    public LayerArmorStandEndermanEyes(RenderArmorStand endermanRendererIn) {
        this.armorStandRenderer = endermanRendererIn;
    }
	
	@Override
	public void doRenderLayer(EntityArmorStand entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(entitylivingbaseIn.getCapability(AmazingStandDataProvider.CAPABILITY, null).getModelId() != 6) {
			return;
		}
		
		this.armorStandRenderer.bindTexture(RES_ENDERMAN_EYES);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
        int i = 61680;
        int j = 61680;
        int k = 0;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        RenderArmorStandNew.endermanModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        
		// Method not on RenderArmorStand, so copied out to here!
		//this.armorStandRenderer.setLightmap(entitylivingbaseIn);
        int i1 = entitylivingbaseIn.getBrightnessForRender();
        int j1 = i1 % 65536;
        int k1 = i1 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j1, (float)k1);	
        
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();

	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
