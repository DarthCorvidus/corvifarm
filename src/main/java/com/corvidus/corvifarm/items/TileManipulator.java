package com.corvidus.corvifarm.items;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.tiles.Tile;

public interface TileManipulator {
	public void apply(Player player, Tile tile) throws InvalidActionException;
}
