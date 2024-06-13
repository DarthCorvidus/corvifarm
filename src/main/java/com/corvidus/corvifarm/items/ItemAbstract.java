package com.corvidus.corvifarm.items;
public abstract class ItemAbstract implements Item {
	static int NONE = 0;
	static int COPPER = 1;
	static int IRON = 2;
	static int GOLD = 3;
	static int PLATINUM = 4;
	private int quality = 0;
	private int amount = 1;
	@Override
	public String getLongName() {
		String name = "";
		if(this.getAmount() != 1) {
			name += this.getAmount()+" ";
		}
		name += this.getName();
		if(this.quality != 0) {
			name += "("+this.quality+")";
		}
	return name;
	}
	
	@Override
	public abstract int getBaseDemand();
	
	@Override
	public int getDemand() {
		return this.getBaseDemand();
	}
	
	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public int getAmount() {
		return this.amount;
	}
	
	@Override
	public void addAmount(int amount) {
		this.amount += amount;
	}

	@Override	
	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	public int getQuality() {
		return this.quality;
	}
}
