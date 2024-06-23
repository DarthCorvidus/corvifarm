package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.items.Item;
public class ImmutableTile extends TileAbstract {
	public ImmutableTile() {
		
	}
	public ImmutableTile(Item overlay) {
		super.setOverlay(overlay);
	}

	@Override
	public void passDay() {
		
	}

	@Override
	public String getName() {
		if(this.hasOverlay()) {
			return this.getOverlay().getName();
		}
		return "Immutable";
	}

	@Override
	public int getId() {
		return TileFactory.IMMUTABLE;
	}
}
