package com.codeshaper.amazingstands.armorStandRendering;

import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;
import com.codeshaper.amazingstands.capabilities.IAmazingStandData;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;

public class LayerArmorStandBasePlate implements LayerRenderer<EntityArmorStand> {

	//private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/armorstand/wood.png");
    private final Render<EntityArmorStand> armorStandRenderer;
    
    public LayerArmorStandBasePlate(Render<EntityArmorStand> render) {
        this.armorStandRenderer = render;
    }

    @Override
	public void doRenderLayer(EntityArmorStand armorStand, float p_177162_2_, float p_177162_3_, float p_177162_4_, float p_177162_5_, float p_177162_6_, float p_177162_7_, float p_177162_8_) {
		IAmazingStandData cap = armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null);
		ItemStack stack = cap.getStandStack();
		
		if(stack != null && !armorStand.hasNoBasePlate()) {
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.39F, 1.5F, 0.39F);
            //XXX This sort of removes the vanilla feature of the stand never rotating by 45*, but I kind of like it moving.
            GlStateManager.rotate(armorStand.rotationPitch, 0.0F, 0.0F, 0.0F);
            float f7 = 0.75F;
            GlStateManager.scale(-f7, -f7 + 0.5f, f7);
            int i = armorStand.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.armorStandRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            blockrendererdispatcher.renderBlockBrightness(Block.getBlockFromItem(stack.getItem()).getStateFromMeta(stack.getMetadata()), 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
