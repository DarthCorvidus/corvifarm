package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;

public interface Tillable {
	public void till() throws InvalidActionException;
	public boolean isTilled();
}
