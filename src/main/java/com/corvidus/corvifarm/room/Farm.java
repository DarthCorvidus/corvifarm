package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Weeds;
import com.corvidus.corvifarm.items.interactive.Bed;
import com.corvidus.corvifarm.items.interactive.Exit;
import com.corvidus.corvifarm.items.interactive.ShippingBin;
import com.corvidus.corvifarm.items.interactive.Shop;
import com.corvidus.corvifarm.items.stone.Stone;
import com.corvidus.corvifarm.items.wood.Tree;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.ImmutableTile;
import com.corvidus.corvifarm.tiles.Tillable;
import com.corvidus.corvifarm.tiles.WASDSelectGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Farm extends RoomAbstract {
	public Farm() {
		super();
		this.addImmutableTile(new ImmutableTile(new Exit(Rooms.YOUR_HOUSE, "Farm House")));
		this.addImmutableTile(new ImmutableTile(new ShippingBin()));
		this.addImmutableTile(new ImmutableTile(new Shop()));
	}
	
	@Override
	public int getId() {
		return Rooms.YOUR_FARM;
	}
	
	@Override
	public void init() {
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
				tile.setOverlay(ItemFactory.createRandomTree());
				continue;
			}
		}
	}
	
	@Override
	public void onEnter() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public String getName() {
		return "Your Farm";
	}

	@Override
	public void respawn() {
		int[] debris = {ItemFactory.TREE_CEDRUS, ItemFactory.TREE_CYPRESS, ItemFactory.TREE_OAK, ItemFactory.WOOD, ItemFactory.STONE, ItemFactory.WEEDS};
		for(Tile tile: this.tiles) {
			if(tile.hasOverlay()) {
				continue;
			}
			if(tile instanceof FarmTile ft && ft.isTilled()) {
				continue;
			}
			
			if(this.rand.nextInt(100)<1) {
				int randIndex = this.rand.nextInt(debris.length);
				tile.setOverlay(ItemFactory.getPrototype(debris[randIndex]));
			}
		}
	}
}
