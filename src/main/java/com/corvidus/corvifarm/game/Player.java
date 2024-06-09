package com.corvidus.corvifarm.game;
public class Player {
	private int gold;
	private int energy;
	public static int defaultGold = 500;
	public static int defaultEnergy = 270;
	private Player() {
		
	}
	
	public static Player fromScratch() {
		Player player = new Player();
		player.energy = Player.defaultEnergy;
		player.gold = Player.defaultGold;
	return player;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public void subEnergy(int energy) throws InvalidActionException {
		if(this.energy - energy < 0) {
			throw new InvalidActionException("Not enough energy.");
		}
		this.energy -= energy;
	}

	public void addEnergy(int energy) {
		this.energy += energy;
		if(this.energy > Player.defaultEnergy) {
			this.energy = Player.defaultEnergy;
		}
	}
	
	public void subGold(int gold) throws InvalidActionException {
		if(this.gold - gold < 0) {
			throw new InvalidActionException("Not enough gold.");
		}
		this.gold -= gold;
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}
	
}
