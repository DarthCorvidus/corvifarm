package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.WASDSelectGroup;
import java.util.HashMap;
public class Farm extends RoomAbstract {
	private Farm() {
		super();
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

	@Override
	public void onSecond(Calendar calendar) {
		
	}

	@Override
	public void onWakeup(Calendar calendar) {
		
	}
}
