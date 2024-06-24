package com.corvidus.corvifarm.items.stone;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.Mineable;
import java.util.ArrayList;
import java.util.List;

public class Stone extends ItemAbstract implements Mineable {
	@Override
	public int getBaseDemand() {
		return 10;
	}

	@Override
	public String getName() {
		return "Stone";
	}

	@Override
	public int getId() {
		return ItemFactory.STONE;
	}
	
	@Override
	public void mine() {
		
	}

	@Override
	public boolean yieldsItem() {
		return true;
	}

	@Override
	public List<Item> getMinedItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Stone());
	return items;
	}

}
