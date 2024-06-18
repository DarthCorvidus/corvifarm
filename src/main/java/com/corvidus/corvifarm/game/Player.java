package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Crops;
import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.tools.Hoe;
import com.corvidus.corvifarm.items.tools.Pickaxe;
import com.corvidus.corvifarm.items.tools.Scythe;
import com.corvidus.corvifarm.items.tools.Watercan;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;

public class Player implements TerminalWidget, CalendarObserver {
	private int gold = 500;
	private int energy = 270;
	public static int defaultEnergy = 270;
	private WidgetPane pane;
	private WidgetString labelEnergy;
	private WidgetString labelGold;
	private WidgetString valueEnergy;
	private WidgetString valueGold;
	private Inventory inventory;
	private Player() {
		this.pane = new WidgetPane(0, 2, 20, 12);
		this.labelGold = new WidgetString(0, 0, 7, "Gold:");
		this.labelEnergy = new WidgetString(0, 1, 7, "Energy:");
		this.valueGold = new WidgetString(8, 0, 8, Integer.toString(this.gold));
		this.valueEnergy = new WidgetString(8, 1, 8, Integer.toString(this.energy));
		this.inventory = new Inventory();
		this.inventory.addItem(new Hoe());
		this.inventory.addItem(new Watercan());
		this.inventory.addItem(new Axe());
		this.inventory.addItem(new Pickaxe());
		this.inventory.addItem(new Scythe());
		this.inventory.addItem(Crops.create(Crops.WHEAT, 15));
		this.pane.addWidget(this.labelEnergy);
		this.pane.addWidget(this.labelGold);
		this.pane.addWidget(this.valueEnergy);
		this.pane.addWidget(this.valueGold);
		this.pane.addWidget(this.inventory);
	}

	public Inventory getInventory() {
		return this.inventory;
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
	
	public void assertEnergy(int energy) throws InvalidActionException {
		if(this.energy < energy) {
			throw new InvalidActionException("Not enough energy.");
		}
	}
	
	public void subEnergy(int energy) throws InvalidActionException {
		this.assertEnergy(energy);
		this.energy -= energy;
		this.valueEnergy.setString(Integer.toString(this.energy));
	}

	public void addEnergy(int energy) {
		this.energy += energy;
		if(this.energy > Player.defaultEnergy) {
			this.energy = Player.defaultEnergy;
		}
		this.valueEnergy.setString(Integer.toString(this.energy));
	}
	
	public void assertGold(int gold) throws InvalidActionException {
		if(this.gold < gold) {
			throw new InvalidActionException("Not enough gold.");
		}
	}
	
	public void subGold(int gold) throws InvalidActionException {
		this.assertGold(gold);
		if(this.gold - gold < 0) {
			throw new InvalidActionException("Not enough gold.");
		}
		this.gold -= gold;
		this.valueGold.setString(Integer.toString(this.gold));
	}
	
	public void addGold(int gold) {
		this.gold += gold;
		this.valueGold.setString(Integer.toString(this.gold));
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
		this.pane.onInput(keyStroke);
	}

	@Override
	public void onSecond(Calendar calendar) {
		
	}

	@Override
	public void onWakeup(Calendar calendar) {
		this.addEnergy(Player.defaultEnergy);
	}
	
}
