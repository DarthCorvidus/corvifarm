package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.game.Ground;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.select.WASDSelectObserver;
import com.corvidus.corvifarm.tiles.Tile;
import java.util.List;

public interface Room extends TerminalWidget, CalendarObserver {
	public void onEnter();
	public String getName();
	public void addWASDSelectObserver(WASDSelectObserver observer);
	public void removeWASDSelectObserver(WASDSelectObserver observer);
	public void refresh();
	public Ground getGround();
	public void init();
	public void respawn();
	public List<Tile> getTiles();
	public int getId();
}
