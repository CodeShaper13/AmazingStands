package com.codeshaper.amazingstands.capabilities;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class AmazingStandData implements IAmazingStandData {

	private int modelId = 0;
	private ItemStack standStack = new ItemStack(Blocks.STONE_SLAB);
	
	@Override
	public int getModelId() {
		return this.modelId;
	}

	@Override
	public void setModelId(int i) {
		this.modelId = i;		
	}

	@Override
	public ItemStack getStandStack() {
		return this.standStack;
	}

	@Override
	public void setStandStack(ItemStack stack) {
		this.standStack = stack;	
	}
}
