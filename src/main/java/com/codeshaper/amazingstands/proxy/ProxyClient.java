package com.codeshaper.amazingstands.proxy;

import com.codeshaper.amazingstands.armorStandRendering.RenderArmorStandNew;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon {
	
	@Override
	public void registerRendering() {		
		RenderingRegistry.registerEntityRenderingHandler(EntityArmorStand.class, new IRenderFactory<EntityArmorStand>() {
			@Override
			public Render<EntityArmorStand> createRenderFor(RenderManager manager) {
				return new RenderArmorStandNew(manager);
			}
		});
	}
}

