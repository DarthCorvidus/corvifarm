package com.corvidus.corvifarm;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.terminal.VT100;
import com.corvidus.corvifarm.input.Input;
import com.corvidus.corvifarm.input.InputObserver;

/**
 *
 * @author hm
 */
public class Corvifarm {
	private Game game;
	public Corvifarm() {
		this.game = new Game();
	}
	
	public void run() {
		this.game.run();
	}

	public static void main(String[] args) {
		Corvifarm farm = new Corvifarm();
		farm.run();
	}
}
