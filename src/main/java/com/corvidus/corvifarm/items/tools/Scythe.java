package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Crops;
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
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!tile.hasOverlay()) {
			return;
		}
		if(!(tile.getOverlay() instanceof Crop)) {
			throw new InvalidActionException("Cannot be scythed.");
		}
		Crop crop = (Crop)tile.getOverlay();
		if(crop.getState() != Crop.GROWN) {
			throw new InvalidActionException("Crop not ready.");
		}
		Crop produce = Crops.createProduce(crop.getID());
		player.getInventory().addItem(produce);
		tile.removeOverlay();
	}
	
}
