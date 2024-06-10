package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;

public interface Tile {
	public void passDay();
	public boolean hasOverlay();
	public Item getOverlay();
	public void setOverlay(Item item);
	public Item removeOverlay();
	public void setTileObserver(TileObserver observer);
	public String getName();
}

