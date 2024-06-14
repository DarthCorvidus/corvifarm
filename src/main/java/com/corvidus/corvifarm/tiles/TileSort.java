package com.corvidus.corvifarm.tiles;

import java.util.Comparator;
public class TileSort implements Comparator<Tile>{
	@Override
	public int compare(Tile o1, Tile o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
