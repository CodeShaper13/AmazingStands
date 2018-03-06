package com.codeshaper.amazingstands.capabilities;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class AmazingStandDataStorage implements IStorage<IAmazingStandData> {

	@Override
	public NBTBase writeNBT(Capability<IAmazingStandData> capability, IAmazingStandData instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setInteger("model", instance.getModelId());
		
		ItemStack stack = instance.getStandStack();
        if (!stack.isEmpty()) {
            tag.setTag("baseStack", stack.writeToNBT(new NBTTagCompound()));
        }
		
		return tag;
	}

	@Override
	public void readNBT(Capability<IAmazingStandData> capability, IAmazingStandData instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound)nbt;
		instance.setModelId(tag.getInteger("model"));
		if(tag.hasKey("baseStack")) {
			instance.setStandStack(new ItemStack(tag.getCompoundTag("baseStack")));			
		} else {
			instance.setStandStack(new ItemStack(Blocks.STONE_SLAB));
		}
	}
}