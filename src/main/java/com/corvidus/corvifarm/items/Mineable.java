package com.corvidus.corvifarm.items;

import java.util.List;

public interface Mineable {
	public void mine();
	/**
	 * Mining a Mineable does not necessarily yield an item every time.
	 * @return boolean
	 */
	public boolean yieldsItem();
	public List<Item> getMinedItems();
}
