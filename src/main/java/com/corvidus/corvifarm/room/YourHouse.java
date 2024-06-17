package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.items.interactive.Bed;
import com.corvidus.corvifarm.items.interactive.Exit;
import com.corvidus.corvifarm.tiles.ImmutableTile;

public class YourHouse extends RoomAbstract {
	public YourHouse() {
		super();
		this.addImmutableTile(new ImmutableTile(new Bed()));
		this.addImmutableTile(new ImmutableTile(new Exit(Rooms.YOUR_FARM, "Farm")));
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public String getName() {
		return "Your House";
	}
	
	public static YourHouse fromScratch() {
		YourHouse yh = new YourHouse();
		yh.refresh();
	return yh;
	}
	
}
