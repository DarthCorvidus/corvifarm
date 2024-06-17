package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.items.crops.Weeds;
import com.corvidus.corvifarm.items.interactive.Bed;
import com.corvidus.corvifarm.items.interactive.Exit;
import com.corvidus.corvifarm.items.interactive.ShippingBin;
import com.corvidus.corvifarm.items.interactive.Shop;
import com.corvidus.corvifarm.items.stone.Stone;
import com.corvidus.corvifarm.items.wood.Trees;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.ImmutableTile;
import com.corvidus.corvifarm.tiles.WASDSelectGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Farm extends RoomAbstract {
	private Farm() {
		super();
		this.addImmutableTile(new ImmutableTile(new Exit(Rooms.YOUR_HOUSE, "Farm House")));
		this.addImmutableTile(new ImmutableTile(new ShippingBin()));
		this.addImmutableTile(new ImmutableTile(new Shop()));
	}
	
	private void init() {
		for(int i = 0;i<4096;i++) {
			Tile tile = new FarmTile();
			this.tiles.add(tile);
			/*
			 * Spawn Wood.
			*/
			if(this.rand.nextInt(10)<1) {
				tile.setOverlay(new Wood());
				continue;
			}
			if(this.rand.nextInt(10)<1) {
				tile.setOverlay(new Stone());
				continue;
			}
			if(this.rand.nextInt(10)<1) {
				tile.setOverlay(new Weeds());
				continue;
			}
			if(this.rand.nextInt(10)<1) {
				tile.setOverlay(Trees.createRandom());
				continue;
			}
		}
	}
	
	public static Farm fromScratch() {
		Farm farm = new Farm();
		farm.init();
		farm.refresh();
	return farm;
	}
	
	@Override
	public void onEnter() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public String getName() {
		return "Your Farm";
	}
}
