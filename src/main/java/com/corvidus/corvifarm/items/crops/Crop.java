package com.corvidus.corvifarm.items.crops;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Daily;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemPrototype;
import com.corvidus.corvifarm.items.Scythable;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.Waterable;
import java.util.ArrayList;
import java.util.List;

public class Crop extends ItemAbstract implements TileManipulator, Daily, Scythable, ItemPrototype {
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
	
	@Override
	public int getId() {
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
		if(this.state == Crop.SEED) {
			return this.baseDemand;
		}
		if(this.state == Crop.PRODUCE) {
			return this.baseDemand*4;
		}
		return 0;
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
		Crop crop = (Crop)this.createPrototype();
		tile.setOverlay(crop);
		player.getInventory().subCurrentItem();
	}

	@Override
	public void passDay(Tile tile) {
		Waterable waterable = (Waterable)tile;
		if(!waterable.isWatered()) {
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

	@Override
	public void scythe() throws InvalidActionException {
		if(this.state != Crop.GROWN) {
			throw new InvalidActionException("Crop not ready.");
		}
	}

	@Override
	public boolean yieldsItem() {
		return this.state == Crop.GROWN;
	}

	@Override
	public List<Item> getScythedItems() {
		List<Item> items = new ArrayList<>();
		Crop produce = (Crop)this.create();
		produce.setState(Crop.PRODUCE);
		items.add(produce);
	return items;
	}

	@Override
	public int getBaseEnergyCost() {
		return 0;
	}

	@Override
	public Item createPrototype() {
		Crop crop = new Crop(this.getId(), this.days, this.baseDemand, this.name);
	return crop;
	}

}
