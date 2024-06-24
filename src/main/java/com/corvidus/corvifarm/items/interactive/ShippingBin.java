package com.corvidus.corvifarm.items.interactive;

import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Inventory;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;

public class ShippingBin extends ItemAbstract implements Interactive {

	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Shipping Bin";
	}
	
	@Override
	public int getId() {
		return ItemFactory.SHIPPING_BIN;
	}

	@Override
	public void interact(Game game) throws InvalidActionException {
		Player player = game.getPlayer();
		Inventory inv = game.getPlayer().getInventory();
		try {
			Item item = inv.getCurrentItem();
			if(item.getBaseDemand() == 0) {
				throw new InvalidActionException("Item cannot be sold.");
			}
			inv.subCurrentItem();
			player.addGold(item.getBaseDemand());
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		
	}
}
