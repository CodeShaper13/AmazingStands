package com.codeshaper.amazingstands.packets;

import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;
import com.codeshaper.amazingstands.capabilities.IAmazingStandData;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBasePlateStack implements IMessage {

	public static class Handler implements IMessageHandler<PacketBasePlateStack, IMessage> {

		@Override
		public IMessage onMessage(PacketBasePlateStack message, MessageContext ctx) {
			Minecraft mc = Minecraft.getMinecraft();
			mc.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					Entity e = mc.world.getEntityByID(message.targetEntityId);
					IAmazingStandData cap = e.getCapability(AmazingStandDataProvider.CAPABILITY, null);
					cap.setStandStack(message.basePlateStack);
				}
			});
			return null;
		}
	}

	private int targetEntityId = 0;
	private ItemStack basePlateStack = ItemStack.EMPTY;

	public PacketBasePlateStack() {
	}

	public PacketBasePlateStack(int id, ItemStack stack) {
		this.targetEntityId = id;
		this.basePlateStack = stack;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.targetEntityId = buf.readInt();
		this.basePlateStack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.targetEntityId);
		ByteBufUtils.writeItemStack(buf, this.basePlateStack);
	}
}