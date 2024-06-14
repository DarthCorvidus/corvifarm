package com.corvidus.corvifarm.items.interactive;

import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.ItemAbstract;

public class Bed extends ItemAbstract implements Interactive {
	@Override
	public void interact(Game game) {
		game.calendar.sleep();
	}

	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Bed";
	}
}
