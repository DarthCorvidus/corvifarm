package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.Tillable;
public class Hoe extends ItemAbstract implements TileManipulator {
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Hoe";
	}

	@Override
	public int getId() {
		return ItemFactory.HOE;
	}

	@Override
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!(tile instanceof Tillable)) {
			throw new InvalidActionException("Tile cannot be tilled");
		}
		if(tile.hasOverlay()) {
			throw new InvalidActionException("Tile is occupied and cannot be tilled");
		}
		Tillable tillable = (Tillable)tile;
		tillable.till();
	}

	@Override
	public int getBaseEnergyCost() {
		return 2;
	}
	
}
