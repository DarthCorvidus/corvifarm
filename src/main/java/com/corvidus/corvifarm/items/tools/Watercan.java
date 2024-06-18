package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.Tillable;
import com.corvidus.corvifarm.tiles.Waterable;
public class Watercan extends ItemAbstract implements TileManipulator {
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Watercan";
	}
	
	@Override
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!(tile instanceof Waterable)) {
			throw new InvalidActionException("Tile cannot be watered");
		}
		Waterable waterable = (Waterable)tile;
		waterable.water();
	}

	@Override
	public int getBaseEnergyCost() {
		return 2;
	}

}
