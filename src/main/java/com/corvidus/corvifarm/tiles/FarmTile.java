package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.Daily;
import com.corvidus.corvifarm.items.crops.Crop;
import java.util.Random;
public class FarmTile extends TileAbstract implements Tillable, Waterable {
	private byte tilledWatered = 0;
	public static byte FLAT = 0;
	public static byte TILLED = 1;
	public static byte WATERED = 2;
	private final Random rand = new Random();
	@Override
	public void till() throws InvalidActionException {
		if(this.tilledWatered == FarmTile.TILLED) {
			throw new InvalidActionException("Already tilled.");
		}
		if(this.hasOverlay()) {
			throw new InvalidActionException("Cannot till used tile.");
		}
		this.tilledWatered = FarmTile.TILLED;
	}

	@Override
	public void water() throws InvalidActionException {
		if(this.tilledWatered == FarmTile.FLAT) {
			throw new InvalidActionException("Cannot water untilled tile.");
		}
		if(this.tilledWatered == FarmTile.WATERED) {
			throw new InvalidActionException("Already watered.");
		}
		this.tilledWatered = FarmTile.WATERED;
	}

	@Override
	public void passDay() {
		if(this.hasOverlay() && this.getOverlay() instanceof Daily) {
			Daily daily = (Daily)this.getOverlay();
			daily.passDay(this);
		}
		this.tilledWatered = FarmTile.TILLED;
		if(this.rand.nextInt(10)<5 && !this.hasOverlay()) {
			this.tilledWatered = FarmTile.FLAT;
		}
	}
	
	@Override
	public String getName() {
		if(this.hasOverlay() && this.getOverlay() instanceof Crop && this.tilledWatered == FarmTile.TILLED) {
			return "!"+this.getOverlay().getName();
		}
		if(this.hasOverlay()) {
			return this.getOverlay().getName();
		}
		if(this.tilledWatered == FarmTile.FLAT) {
			return "Untilled";
		}
		if(this.tilledWatered == FarmTile.TILLED) {
			return "Unwatered";
		}
	return "Watered";
	}
	
	@Override
	public boolean isTilled() {
		return this.tilledWatered >= FarmTile.TILLED;
	}
	
	@Override
	public boolean isWatered() {
		return this.tilledWatered == FarmTile.WATERED;
	}

	@Override
	public void setModifiers(byte tilledWatered, byte b, byte c, byte d) {
		this.tilledWatered = tilledWatered;
	}
	
	@Override
	public byte[] getModifiers() {
		byte[] mods = {0, 0, 0, 0};
		mods[0] = this.tilledWatered;
	return mods;
	}
	
	@Override
	public int getId() {
		return TileFactory.FARM;
	}
}
