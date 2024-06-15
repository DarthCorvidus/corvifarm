package com.corvidus.corvifarm.terminal.select;

import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Crops;

public class WASDSelectShop implements WASDSelectElement {
	private Crop crop;
	public WASDSelectShop(Crop crop) {
		this.crop = Crops.createSeed(crop.getID());
	}
	@Override
	public String getWASDString() {
		return this.crop.getName()+" ("+Integer.toString(this.crop.getBaseDemand())+")";
	}

	@Override
	public Object getObject() {
		return Crops.createSeed(this.crop.getID());
	}
	
}
