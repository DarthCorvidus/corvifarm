package com.corvidus.corvifarm.items;

import com.corvidus.corvifarm.game.InvalidActionException;
import java.util.List;

public interface Scythable {
	public void scythe() throws InvalidActionException;
	public boolean yieldsItem();
	public List<Item> getScythedItems();
}
