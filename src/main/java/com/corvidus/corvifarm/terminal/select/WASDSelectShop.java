package com.corvidus.corvifarm.terminal.select;

import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Crop;

public class WASDSelectShop implements WASDSelectElement {
	private Crop crop;
	public WASDSelectShop(Crop crop) {
		this.crop = (Crop)crop.createPrototype();
	}
	@Override
	public String getWASDString() {
		return this.crop.getName()+" ("+Integer.toString(this.crop.getBaseDemand())+")";
	}

	@Override
	public Object getObject() {
		return this.crop.createPrototype();
	}
	
}
