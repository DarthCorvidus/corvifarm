package com.corvidus.corvifarm.items;

import java.util.List;

public interface Axable {
	public void axe();
	/**
	 * Axing an Overlay does not necessarily yield an item every time; a tree
	 * takes several blows before it yields wood.
	 * @return boolean
	 */
	public boolean yieldsItem();
	public List<Item> getAxedItems();
}
