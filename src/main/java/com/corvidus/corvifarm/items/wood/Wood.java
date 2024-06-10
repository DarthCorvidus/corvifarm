package com.corvidus.corvifarm.items.wood;

import com.corvidus.corvifarm.items.Axable;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import java.util.ArrayList;

public class Wood extends ItemAbstract implements Axable {
	@Override
	public int getBaseDemand() {
		return 10;
	}

	@Override
	public String getName() {
		return "Wood";
	}

	@Override
	public void axe() {
		
	}

	@Override
	public boolean yieldsItem() {
		return true;
	}

	@Override
	public ArrayList<Item> getAxedItems() {
		ArrayList<Item> items = new ArrayList<>();
		items.add(new Wood());
	return items;
	}
}
