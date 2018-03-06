package com.codeshaper.amazingstands.capabilities;

import net.minecraft.item.ItemStack;

public interface IAmazingStandData {

	public int getModelId();
	
	public void setModelId(int i);
	
	public ItemStack getStandStack();
	
	public void setStandStack(ItemStack stack);
}
