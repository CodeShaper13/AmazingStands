package com.codeshaper.amazingstands;

import com.codeshaper.amazingstands.proxy.ProxyCommon;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = AmazingStands.MOD_ID, version = AmazingStands.VERSION, updateJSON = "https://github.com/RangerPJ/AmazingStands/tree/master/src/main/resources/assets/amazingstands/updates.json", name = "Amazing Stands")
public class AmazingStands {

	public static final String MOD_ID = "amazingstands";
	public static final String VERSION = "0.9";

	@SidedProxy(clientSide = "com.codeshaper.amazingstands.proxy.ProxyClient", serverSide = "com.codeshaper.amazingstands.proxy.ProxyCommon")
	public static ProxyCommon proxy;
	
	public static PacketManager packetManager;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerRendering();
		PacketManager.registerPackets();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();

		MinecraftForge.EVENT_BUS.register(new AmazingStandEventHandler());
	}

	//@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
			//IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
			//modRegistry.remove(new ResourceLocation("minecraft:armor_stand"));
		}
	}
}
