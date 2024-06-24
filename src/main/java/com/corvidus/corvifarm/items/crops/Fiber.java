package com.corvidus.corvifarm.items.crops;

import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;

public class Fiber extends ItemAbstract{
	@Override
	public int getBaseDemand() {
		return 2;
	}

	@Override
	public String getName() {
		return "Fiber";
	}

	@Override
	public int getId() {
		return ItemFactory.FIBER;
	}
	
}
