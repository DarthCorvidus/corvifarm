package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Tile {
	public void passDay();
	public boolean hasOverlay();
	public Item getOverlay();
	public void setOverlay(Item item);
	public Item removeOverlay();
	public void setTileObserver(TileObserver observer);
	public String getName();
	public int getId();
	public void setModifiers(byte a, byte b, byte c, byte d);
	public byte[] getModifiers();
	public void toBinary(DataOutputStream dos) throws IOException;
	public static Tile fromBinary(DataInputStream dis) throws IOException {
		int tileId = dis.readShort();
		byte a = dis.readByte();
		byte b = dis.readByte();
		byte c = dis.readByte();
		byte d = dis.readByte();
		int overlayId = dis.readInt();
		Tile tile = TileFactory.getPrototype(tileId);
		System.out.println("Setting modifier a: "+a);
		tile.setModifiers(a, b, c, d);
	return tile;
	}
}

