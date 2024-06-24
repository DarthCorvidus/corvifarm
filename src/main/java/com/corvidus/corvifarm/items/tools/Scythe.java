package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.Scythable;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.Tile;
public class Scythe extends ItemAbstract implements TileManipulator{
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Scythe";
	}

	@Override
	public int getId() {
		return ItemFactory.SCYTHE;
	}

	
	@Override
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!tile.hasOverlay()) {
			return;
		}
		if(!(tile.getOverlay() instanceof Scythable)) {
			throw new InvalidActionException("Cannot be scythed.");
		}
		Scythable scythable = (Scythable)tile.getOverlay();
		scythable.scythe();
		if(scythable.yieldsItem()) {
			player.getInventory().addItems(scythable.getScythedItems());
		}
		tile.removeOverlay();
	}

	@Override
	public int getBaseEnergyCost() {
		return 2;
	}
}
