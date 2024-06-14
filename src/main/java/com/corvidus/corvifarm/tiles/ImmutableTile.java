/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;

/**
 *
 * @author hm
 */
public class ImmutableTile implements Tile {
	private final Item overlay;
	public ImmutableTile(Item overlay) {
		this.overlay = overlay;
	}
	@Override
	public void passDay() {
		
	}

	@Override
	public boolean hasOverlay() {
		return true;
	}

	@Override
	public Item getOverlay() {
		return this.overlay;
	}

	@Override
	public void setOverlay(Item item) {
		
	}

	@Override
	public Item removeOverlay() {
		return this.overlay;
	}

	@Override
	public void setTileObserver(TileObserver observer) {
		
	}

	@Override
	public String getName() {
		return this.overlay.getName();
	}
	
}
