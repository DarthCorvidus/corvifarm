package com.corvidus.corvifarm.persistence;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PersistenceItem {
	private int itemId;
	private int amount;
	private byte[] modifiers;
	private int primaryKey;
	private Integer parentItemId = null;
	private Integer foreignKeyTile = null;
	private byte nullIndicator = ITEM_NULL + TILE_NULL;
	static final byte ITEM_NULL = 2;
	static final byte TILE_NULL = 1;
	private PersistenceItem() {
		
	}
	
	public Item createItem() {
		Item item = ItemFactory.getPrototype(this.itemId);
		item.setAmount(this.amount);
		item.setModifiers(this.modifiers);
	return item;
	}
	
	public int getPrimaryKey() {
		return this.primaryKey;
	}
	
	public int getForeignKeyTile() {
		return this.foreignKeyTile;
	}
	
	public static PersistenceItem fromItem(int primaryKey, Item item) {
		PersistenceItem entry = new PersistenceItem();
		entry.primaryKey = primaryKey;
		entry.itemId = item.getId();
		entry.amount = item.getAmount();
		entry.modifiers = item.getModifiers();
		entry.parentItemId = 0;
		entry.foreignKeyTile = 0;
		entry.nullIndicator = ITEM_NULL + TILE_NULL;
	return entry;
	}

	public static PersistenceItem fromItem(int primaryKey, Item item, PersistenceTile pt) {
		PersistenceItem entry = new PersistenceItem();
		entry.primaryKey = primaryKey;
		entry.itemId = item.getId();
		entry.amount = item.getAmount();
		entry.modifiers = item.getModifiers();
		entry.parentItemId = 0;
		entry.foreignKeyTile = pt.getPrimaryKey();
		entry.nullIndicator = ITEM_NULL;
	return entry;
	}

	public void toBinary(DataOutputStream dos) throws IOException {
		dos.writeInt(this.primaryKey);
		dos.writeInt(this.itemId);
		dos.writeShort(this.amount);
		for(int i = 0; i<4; i++) {
			dos.writeByte(this.modifiers[i]);
		}
		dos.writeInt(this.foreignKeyTile);
		dos.writeInt(this.parentItemId);
		dos.writeByte(this.nullIndicator);
	}

	public static PersistenceItem fromBinary(DataInputStream dis) throws IOException {
		PersistenceItem entry = new PersistenceItem();
		entry.primaryKey = dis.readInt();
		entry.itemId = dis.readInt();
		entry.amount = dis.readShort();
		entry.modifiers = new byte[4];
		for(int i = 0; i<4; i++) {
			entry.modifiers[i] = dis.readByte();
		}
		entry.foreignKeyTile = dis.readInt();
		entry.parentItemId = dis.readInt();
		entry.nullIndicator = dis.readByte();
		if(entry.nullIndicator == ITEM_NULL || entry.nullIndicator == (ITEM_NULL + TILE_NULL)) {
			entry.parentItemId = null;
		}

		if(entry.nullIndicator == TILE_NULL || entry.nullIndicator == (ITEM_NULL + TILE_NULL)) {
			entry.foreignKeyTile = null;
		}

	return entry;
	}
}
