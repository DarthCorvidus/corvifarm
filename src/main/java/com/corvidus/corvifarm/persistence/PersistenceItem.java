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
	private Integer parentItemId = null;
	private Integer parentTileId = null;
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
	
	public static PersistenceItem fromItem(Item item) {
		PersistenceItem entry = new PersistenceItem();
		entry.itemId = item.getId();
		entry.amount = item.getAmount();
		entry.modifiers = item.getModifiers();
		entry.parentItemId = 0;
		entry.parentTileId = 0;
		entry.nullIndicator = ITEM_NULL + TILE_NULL;
	return entry;
	}
	
	/*
	public static PersistenceItem fromItem(Item item, int tileForeignId) {
		
	}

	public static PersistenceItem fromItem(Item item, int itemParent) {
		
	}
	*/
	public void toBinary(DataOutputStream dos) throws IOException {
		dos.writeInt(this.itemId);
		dos.writeShort(this.amount);
		for(int i = 0; i<4; i++) {
			dos.writeByte(this.modifiers[i]);
		}
		dos.writeInt(this.parentTileId);
		dos.writeInt(this.parentItemId);
		dos.writeByte(this.nullIndicator);
	}

	public static PersistenceItem fromBinary(DataInputStream dis) throws IOException {
		PersistenceItem entry = new PersistenceItem();
		entry.itemId = dis.readInt();
		entry.amount = dis.readShort();
		entry.modifiers = new byte[4];
		for(int i = 0; i<4; i++) {
			entry.modifiers[i] = dis.readByte();
		}
		entry.parentTileId = dis.readInt();
		entry.parentItemId = dis.readInt();
		entry.nullIndicator = dis.readByte();
	return entry;
	}
}
