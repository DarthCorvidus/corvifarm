package com.corvidus.corvifarm;

import com.corvidus.corvifarm.game.Splash;

/**
 *
 * @author hm
 */
public class Corvifarm {
	private Splash splash;
	public Corvifarm() {
		this.splash = new Splash();
	}
	
	public void run() {
		this.splash.run();
	}

	public static void main(String[] args) {
		Corvifarm farm = new Corvifarm();
		farm.run();
	}
}
