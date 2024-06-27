package com.corvidus.corvifarm.items;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Item {
	public String getName();
	public int getBaseDemand();
	public int getDemand();
	public void setAmount(int amount);
	public int getAmount();
	public void addAmount(int amount);
	public int getId();
	public byte[] getModifiers();
	public void setModifiers(byte[] mods);
	public void toBinary(DataOutputStream dos) throws IOException;
}
