package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;
import java.io.DataOutputStream;
import java.io.IOException;
public abstract class TileAbstract implements Tile {
	private Item overlay;
	protected TileObserver observer;

	@Override
	public boolean hasOverlay() {
		return this.overlay != null;
	}

	@Override
	public Item getOverlay() {
		return this.overlay;
	}

	@Override
	public void setOverlay(Item item) {
		this.overlay = item;
	}

	@Override
	public Item removeOverlay() {
		Item item = this.overlay;
		this.overlay = null;
	return this.overlay;
	}

	@Override
	public void setTileObserver(TileObserver observer) {
		this.observer = observer;
	}

	@Override
	public void setModifiers(byte a, byte b, byte c, byte d) {
		
	}
	
	public byte[] getModifiers() {
		byte[] bytes = {0, 0, 0, 0};
	return bytes;
	}
	
	public void toBinary(DataOutputStream dos) throws IOException {
		dos.writeShort(this.getId());
		byte[] mod = this.getModifiers();
		for(int i = 0; i < 4; i++) {
			dos.writeByte(mod[i]);
		}
		if(this.overlay == null) {
			dos.writeInt(0);
		} else {
			dos.writeInt(-1);
			/*
			 * When a similar architecture is ready for Items:
			 * dos.writeInt(this.overlay.getId()
			*/
		}
	}
}
