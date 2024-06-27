package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Crop;
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
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Player implements TerminalWidget, CalendarObserver {
	public static int GREATER_CROW = 0;
	public static int NORTHLAND_PENGUIN = 1;
	public static int BADGER = 2;
	public static int FOX = 3;
	public static int BEAR = 4;
	public static int MALE = 0;
	public static int FEMALE = 1;
	public static int EXP_FARM = 0;
	public static int EXP_FISH = 1;
	public static int EXP_FORAGE = 2;
	public static int EXP_MINE = 3;
	public static int EXP_PSI = 4;
	private int[] exp = {0, 0, 0, 0, 0};
	private long gold = 500;
	private int energy = 270;
	private int confidence = 100;
	// Corvidae are the best.
	private int species = Player.GREATER_CROW;
	// Greater Crows are ruled by matriarchs. Better play as a female ;-).
	private int gender = Player.FEMALE;
	// Greater Crows like ancient human names.
	private String name = "Heidemarie";
	public static int defaultEnergy = 270;
	public static int defaultConfidence = 100;
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
		this.valueGold = new WidgetString(8, 0, 8, Long.toString(this.gold));
		this.valueEnergy = new WidgetString(8, 1, 8, Integer.toString(this.energy));
		this.inventory = new Inventory();
		this.inventory.addItem(new Hoe());
		this.inventory.addItem(new Watercan());
		this.inventory.addItem(new Axe());
		this.inventory.addItem(new Pickaxe());
		this.inventory.addItem(new Scythe());
		this.inventory.addItem(ItemFactory.getPrototype(ItemFactory.WHEAT, 15));
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
	
	public void toBinary(OutputStream os) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(this.name);
		dos.write(this.species);
		dos.write(this.gender);
		dos.writeLong(this.gold);
		for(int value : this.exp) {
			dos.writeInt(value);
		}
	}

	public static Player fromBinary(DataInputStream dis) throws IOException {
		Player player = new Player();
		player.name = dis.readUTF();
		player.species = dis.read();
		player.gender = dis.read();
		player.gold = dis.readLong();
		for(int i = 0; i<5;i++) {
			player.exp[i] = dis.readInt();
		}
	return player;
	}
	
	public long getGold() {
		return this.gold;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public int getConfidence() {
		return this.confidence;
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
		this.valueGold.setString(Long.toString(this.gold));
	}
	
	public void addGold(int gold) {
		this.gold += gold;
		this.valueGold.setString(Long.toString(this.gold));
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
