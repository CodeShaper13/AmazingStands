package com.codeshaper.amazingstands.proxy;

import com.codeshaper.amazingstands.capabilities.AmazingStandData;
import com.codeshaper.amazingstands.capabilities.AmazingStandDataStorage;
import com.codeshaper.amazingstands.capabilities.IAmazingStandData;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ProxyCommon {
	
	public void init() {		
		CapabilityManager.INSTANCE.register(IAmazingStandData.class, new AmazingStandDataStorage(), AmazingStandData::new);
	}

	public void registerRendering() {
		
	}
}