package com.corvidus.corvifarm;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.terminal.VT100;
import com.corvidus.corvifarm.input.Input;
import com.corvidus.corvifarm.input.InputObserver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author hm
 */
public class Corvifarm {
	private Game game;
	public Corvifarm() {
		File save = new File("game.sav");
		if(save.exists() && !save.isDirectory()) {
			System.out.println("Load from save? Y/n");
			try {
				int i = System.in.read();
				if(i == 'n') {
					this.game = Game.fromScratch();
				} else {
					this.game = Game.fromBinary();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(255);
			}
		} else {
			this.game = Game.fromScratch();
		}
		
	}
	
	public void run() {
		this.game.run();
	}

	public static void main(String[] args) {
		Corvifarm farm = new Corvifarm();
		farm.run();
	}
}
