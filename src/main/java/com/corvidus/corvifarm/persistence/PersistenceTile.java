package com.corvidus.corvifarm.persistence;

import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.TileFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PersistenceTile {
	private int tileId;
	private byte[] modifiers;
	private int roomId;
	private int primaryKey;
	private PersistenceTile() {
		
	}
	
	public static PersistenceTile fromTile(int privateKey, Tile tile, int roomId) {
		PersistenceTile et = new PersistenceTile();
		et.primaryKey = privateKey;
		et.tileId = tile.getId();
		et.modifiers = tile.getModifiers();
		et.roomId = roomId;
	return et;
	}
	
	public Tile createTile() {
		Tile tile = TileFactory.getPrototype(this.tileId);
		tile.setModifiers(this.modifiers[0], this.modifiers[1], this.modifiers[2], this.modifiers[3]);
	return tile;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}
	
	public int getRoomForeignKey() {
		return this.roomId;
	}
	
	public void toBinary(DataOutputStream dos) throws IOException {
		dos.writeInt(this.primaryKey);
		dos.writeShort(tileId);
		for(int i = 0; i<4; i++) {
			dos.writeByte(this.modifiers[i]);
		}
		dos.writeShort(this.roomId);
	}
	
	public static PersistenceTile fromBinary(DataInputStream dis) throws IOException {
		PersistenceTile pt = new PersistenceTile();
		pt.primaryKey = dis.readInt();
		pt.tileId = dis.readShort();
		pt.modifiers = new byte[4];
		for(int i = 0; i<4; i++) {
			pt.modifiers[i] = dis.readByte();
		}
		pt.roomId = dis.readShort();
	return pt;
	}
	
}
