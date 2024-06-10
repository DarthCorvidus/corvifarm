package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;

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
	
}
