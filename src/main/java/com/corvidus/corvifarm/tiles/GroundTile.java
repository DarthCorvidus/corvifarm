package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;

public class GroundTile extends TileAbstract implements Tillable {
	private boolean tilled = false;
	@Override
	public void passDay() {
		this.tilled = false;
	}

	@Override
	public String getName() {
		if(this.tilled) {
			return "Tilled ground";
		}
		return "Empty ground";
	}

	@Override
	public void till() throws InvalidActionException {
		this.tilled = true;
	}

	@Override
	public boolean isTilled() {
		return this.tilled;
	}

	@Override
	public int getId() {
		return TileFactory.GROUND;
	}
}
