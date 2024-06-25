package com.corvidus.corvifarm.items;
public abstract class ItemAbstract implements Item {
	static int NONE = 0;
	private int amount = 1;
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
	public byte[] getModifiers() {
		byte[] mods = {0, 0, 0, 0};
	return mods;
	}
	
	@Override
	public void setModifiers(byte[] mods) {
		
	}

}
