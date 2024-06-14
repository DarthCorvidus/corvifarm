package com.corvidus.corvifarm.items.crops;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Daily;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.Waterable;

public class Crop extends ItemAbstract implements TileManipulator, Daily {
	public final static int SEED = 1;
	public final static int GROWING = 2;
	public final static int GROWN = 3;
	public final static int PRODUCE = 4;
	private int id;
	private int days;
	private int baseDemand;
	private String name;
	private int age;
	private int state = 1;
	public Crop(int id, int days, int baseDemand, String name) {
		this.id = id;
		this.days = days;
		this.baseDemand = baseDemand;
		this.name = name;
		this.age = 0;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}
	
	@Override
	public String getName() {
		switch (this.state) {
			case SEED:
				return this.name+" Seeds";
			case GROWING:
				return this.name+" "+Integer.toString(this.age);
			case PRODUCE:
				return this.name;
			case GROWN:
				return "Grown "+this.name;
			default:
				throw new AssertionError();
		}
	}

	public Crop create() {
		Crop crop = new Crop(this.id, this.days, this.baseDemand, this.name);
	return crop;
	}
	
	@Override
	public int getBaseDemand() {
		return this.baseDemand;
	}

	public void apply(Player player, Tile tile) throws InvalidActionException {
		if(this.state != Crop.SEED) {
			throw new InvalidActionException("Not a seed.");
		}
		if(tile.hasOverlay()) {
			throw new InvalidActionException("Tile is occupied.");
		}
		if(!(tile instanceof FarmTile)) {
			throw new InvalidActionException("Tile cannot be seeded.");
		}
		FarmTile farmTile = (FarmTile)tile;
		if(!farmTile.isTilled()) {
			throw new InvalidActionException("Tile is not tilled.");
		}
		Crop crop = Crops.createSeed(this.id);
		tile.setOverlay(crop);
		player.getInventory().subCurrentItem();
	}

	@Override
	public void passDay(Tile tile) {
		Waterable waterable = (Waterable)tile;
		if(waterable.isWatered()) {
			return;
		}
		if(this.state == Crop.SEED) {
			this.state = Crop.GROWING;
			this.age++;
			return;
		}
		if(this.state == Crop.GROWING && this.age == this.days-1) {
			this.state = Crop.GROWN;
		}
		if(this.state == Crop.GROWING) {
			this.age++;
		}
	}

}
