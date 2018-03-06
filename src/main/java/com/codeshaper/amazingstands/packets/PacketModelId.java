package com.codeshaper.amazingstands.packets;

import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;
import com.codeshaper.amazingstands.capabilities.IAmazingStandData;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketModelId implements IMessage {

	public static class Handler implements IMessageHandler<PacketModelId, IMessage> {
		@Override
		public IMessage onMessage(PacketModelId message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft mc = Minecraft.getMinecraft();
				mc.addScheduledTask(new Runnable() {
					@Override
					public void run() {
						Entity e = mc.world.getEntityByID(message.targetEntityId);
						IAmazingStandData cap = e.getCapability(AmazingStandDataProvider.CAPABILITY, null);
						cap.setModelId(message.modelId);
					}
				});
			} else if (ctx.side == Side.SERVER) {
			}
			return null;
		}
	}

	public int targetEntityId;
	public int modelId;

	public PacketModelId() {}

	public PacketModelId(int entityId, int modelId) {
		this.targetEntityId = entityId;
		this.modelId = modelId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.targetEntityId = buf.readInt();
		this.modelId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.targetEntityId);
		buf.writeInt(this.modelId);
	}
}