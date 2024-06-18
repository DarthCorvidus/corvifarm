package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Axable;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.Tile;
import java.util.List;
public class Axe extends ItemAbstract implements TileManipulator {
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Axe";
	}

	@Override
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!tile.hasOverlay()) {
			throw new InvalidActionException("Nothing to chop down here.");
		}
		Item item = tile.getOverlay();
		if(!(item instanceof Axable)) {
			throw new InvalidActionException("Object cannot be chopped down");
		}
		Axable axable = (Axable)item;
		axable.axe();
		if(!axable.yieldsItem()) {
			return;
		}
		List<Item> items = axable.getAxedItems();
		player.getInventory().addItems(items);
		tile.removeOverlay();
	}

	@Override
	public int getBaseEnergyCost() {
		return 2;
	}
	
}
