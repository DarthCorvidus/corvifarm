package com.corvidus.corvifarm.items.crops;
public class Wheat extends Crop {
	public Wheat(int amount, int state) {
		super(amount, state);
	}
	@Override
	public int getGrowTime() {
		return 4;
	}

	@Override
	public String getCropName() {
		return "Wheat";
	}

	@Override
	public int getBaseDemand() {
		return 40;
	}
	
}
