package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;

public class Player implements TerminalWidget {
	private int gold = 500;
	private int energy = 270;
	public static int defaultEnergy = 270;
	private WidgetPane pane;
	private WidgetString labelEnergy;
	private WidgetString labelGold;
	private WidgetString valueEnergy;
	private WidgetString valueGold;
	private Player() {
		this.pane = new WidgetPane(0, 2, 20, 12);
		this.labelGold = new WidgetString(0, 0, 7, "Gold:");
		this.labelEnergy = new WidgetString(0, 1, 7, "Energy:");
		this.valueGold = new WidgetString(8, 0, 8, Integer.toString(this.gold));
		this.valueEnergy = new WidgetString(8, 1, 8, Integer.toString(this.energy));
		this.pane.addWidget(this.labelEnergy);
		this.pane.addWidget(this.labelGold);
		this.pane.addWidget(this.valueEnergy);
		this.pane.addWidget(this.valueGold);
		
	}
	
	public static Player fromScratch() {
		Player player = new Player();
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

	@Override
	public int getPosX() {
		return this.pane.getPosX();
	}

	@Override
	public int getPosY() {
		return this.pane.getPosY();
	}

	@Override
	public int getHeight() {
		return this.pane.getHeight();
	}

	@Override
	public int getWidth() {
		return this.pane.getWidth();
	}

	@Override
	public TextImage getTextImage() {
		return this.pane.getTextImage();
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
	}
	
}
