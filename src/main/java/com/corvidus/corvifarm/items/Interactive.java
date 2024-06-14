package com.corvidus.corvifarm.items;
import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.game.InvalidActionException;
public interface Interactive {
	public void interact(Game game) throws InvalidActionException;
}
