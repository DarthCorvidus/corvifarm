package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.corvidus.corvifarm.terminal.select.WASDSelectObserver;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.ImmutableTile;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.TileSort;
import com.corvidus.corvifarm.tiles.WASDSelectGroup;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public abstract class RoomAbstract implements Room, CalendarObserver {
	protected WidgetPane pane;
	protected WASDSelect wasd;
	protected WidgetString name;
	protected Random rand = new Random();
	protected ArrayList<Tile> tiles = new ArrayList<>();
	private List<ImmutableTile> immutableTiles = new ArrayList<>();
	protected RoomAbstract() {
		this.pane = new WidgetPane(20, 2, 60, 18);
		this.wasd = new WASDSelect(0, 1, 60, 17, 15);
		this.name = new WidgetString(0, 0, 60, this.getName());
		this.pane.addWidget(this.wasd);
		this.pane.addWidget(this.name);
	}
	
	protected void addImmutableTile(ImmutableTile tile) {
		this.immutableTiles.add(tile);
	}
	
	
	@Override
	public int getPosX() {
		return this.pane.getPosX();
	}

	@Override
	public int getPosY() {
		return this.pane.getPosY();
	}

	@Override
	public int getHeight() {
		return this.pane.getHeight();
	}

	@Override
	public int getWidth() {
		return this.pane.getWidth();
	}

	@Override
	public TextImage getTextImage() {
		return this.pane.getTextImage();
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
		this.wasd.onInput(keyStroke);
	}
	public void addWASDSelectObserver(WASDSelectObserver observer) {
		this.wasd.addWASDSelectObserver(observer);
	}
	
	public void removeWASDSelectObserver(WASDSelectObserver observer) {
		this.wasd.removeWASDSelectObserver(observer);
	}
	
	@Override
	public void refresh() {
		List<Tile> merged = new ArrayList<>();
		merged.addAll(this.immutableTiles);
		
		Collections.sort(this.tiles, new TileSort());
		merged.addAll(this.tiles);
		LinkedHashMap<String, WASDSelectGroup> group = new LinkedHashMap<>();
		this.wasd.clear();
		for(Tile tile : merged) {
			if(!group.containsKey(tile.getName())) {
				group.put(tile.getName(), new WASDSelectGroup());
			}
			group.get(tile.getName()).addTile(tile);
		}
		for(String key : group.keySet()) {
			this.wasd.addElement(group.get(key));
		}

	}

	@Override
	public void onSecond(Calendar calendar) {
		
	}

	@Override
	public void onWakeup(Calendar calendar) {
		for(Tile tile : this.tiles) {
			tile.passDay();
		}
		this.refresh();
	}
}
