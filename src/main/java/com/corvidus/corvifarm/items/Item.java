package com.corvidus.corvifarm.items;
public interface Item {
	public String getName();
	public String getLongName();
	public int getBaseDemand();
	public int getDemand();
	public void setAmount(int amount);
	public int getAmount();
	public void addAmount(int amount);
	public void setQuality(int quality);
	public int getId();
}
