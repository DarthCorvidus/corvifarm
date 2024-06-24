package com.corvidus.corvifarm.items.interactive;

import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;
public class Exit extends ItemAbstract implements Interactive {
	private int target;
	private String name;
	public Exit(int target, String name) {
		this.target = target;
		this.name = name;
	}

	@Override
	public String getName() {
		return "To "+this.name;
	}
	
	@Override
	public int getId() {
		return ItemFactory.EXIT;
	}

	@Override
	public void interact(Game game) throws InvalidActionException {
		game.changeRoom(this.target);
	}

	@Override
	public int getBaseDemand() {
		return 0;
	}
	
}
