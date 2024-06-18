package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.Mineable;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.Tile;
import java.util.List;
public class Pickaxe extends ItemAbstract implements TileManipulator {
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Pickaxe";
	}

	@Override
	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(!tile.hasOverlay()) {
			throw new InvalidActionException("Nothing to mine here.");
		}
		Item item = tile.getOverlay();
		if(!(item instanceof Mineable)) {
			throw new InvalidActionException("Object cannot be mined.");
		}
		Mineable mineable = (Mineable)item;
		mineable.mine();
		if(!mineable.yieldsItem()) {
			return;
		}
		List<Item> items = mineable.getMinedItems();
		player.getInventory().addItems(items);
		tile.removeOverlay();

	}

	@Override
	public int getBaseEnergyCost() {
		return 2;
	}
	
}
