package com.corvidus.corvifarm.items.crops;

import com.corvidus.corvifarm.items.ItemAbstract;

public abstract class Crop extends ItemAbstract {
	public final static int SEED = 1;
	public final static int GROWING = 2;
	public final static int GROWN = 3;
	public final static int PRODUCE = 4;
	protected int state;
	public Crop(int amount, int state) {
		this.state = state;
		this.setAmount(amount);
	}
	public abstract int getGrowTime();
	public abstract String getCropName();
	@Override
	public String getName() {
		switch (this.state) {
			case SEED:
				return this.getCropName()+" Seeds";
			case GROWING:
				return this.getCropName();
			case GROWN:
				return "Grown "+this.getCropName();
			default:
				throw new AssertionError();
		}
	}
}
