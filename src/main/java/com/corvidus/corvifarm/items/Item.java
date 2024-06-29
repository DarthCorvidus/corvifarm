package com.corvidus.corvifarm.items;

import java.io.DataInputStream;
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
	public static Item fromBinary(DataInputStream dis) throws IOException {
		int id = dis.readInt();
		int amount = dis.readShort();
		byte[] modifiers = new byte[4];
		for(int i = 0; i<4; i++) {
			modifiers[i] = dis.readByte();
		}

		Item item = ItemFactory.getPrototype(id, amount);
		item.setModifiers(modifiers);
	return item;
	}
	
	public static void writeEmpty(DataOutputStream dos) throws IOException {
		dos.writeInt(0);
		dos.writeShort(0);
		for(int i = 0; i<4; i++) {
			dos.writeByte(0);
		}
	}
}
