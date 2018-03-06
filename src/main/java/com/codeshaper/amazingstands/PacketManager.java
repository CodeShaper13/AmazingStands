package com.codeshaper.amazingstands;

import com.codeshaper.amazingstands.packets.PacketBasePlateStack;
import com.codeshaper.amazingstands.packets.PacketModelId;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {
	
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(AmazingStands.MOD_ID);

	public static void registerPackets() {
		int packetId = 0;
		INSTANCE.registerMessage(PacketModelId.Handler.class, PacketModelId.class, packetId++, Side.CLIENT);
		INSTANCE.registerMessage(PacketBasePlateStack.Handler.class, PacketBasePlateStack.class, packetId++, Side.CLIENT);
	}
}
