package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import java.util.ArrayList;
public class WASDSelectGroup implements WASDSelectElement {
	private final ArrayList<Tile> tiles = new ArrayList<>();
	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}
	
	public boolean isEmpty() {
		return this.tiles.isEmpty();
	}
	
	@Override
	public String getWASDString() {
		if(this.tiles.isEmpty()) {
			return "INVALID";
		}
	return this.tiles.size()+"×"+this.tiles.get(0).getName();
	}

	@Override
	public Object getObject() {
		return this.tiles.get(0);
	}
	
}
