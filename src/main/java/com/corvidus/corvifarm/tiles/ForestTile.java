package com.corvidus.corvifarm.tiles;
public class ForestTile extends TileAbstract {
	@Override
	public void passDay() {
		
	}

	@Override
	public String getName() {
		return "Forest floor";
	}

	@Override
	public int getId() {
		return TileFactory.FOREST;
	}

}
