package com.corvidus.corvifarm.items.crops;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.Scythable;
import java.util.ArrayList;
import java.util.List;

public class Weeds extends ItemAbstract implements Scythable {
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Weeds";
	}

	@Override
	public void scythe() throws InvalidActionException {
		
	}

	@Override
	public boolean yieldsItem() {
		return true;
	}

	@Override
	public List<Item> getScythedItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Fiber());
	return items;
	}

}
