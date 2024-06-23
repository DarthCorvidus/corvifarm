package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.Daily;
import com.corvidus.corvifarm.items.crops.Crop;
import java.util.Random;
public class FarmTile extends TileAbstract implements Tillable, Waterable {
	private boolean tilled = false;
	private boolean watered = false;
	private final Random rand = new Random();
	@Override
	public void till() throws InvalidActionException {
		if(this.tilled) {
			throw new InvalidActionException("Already tilled.");
		}
		if(this.hasOverlay()) {
			throw new InvalidActionException("Cannot till used tile.");
		}
		this.tilled = true;
	}

	@Override
	public void water() throws InvalidActionException {
		if(!this.tilled) {
			throw new InvalidActionException("Cannot water untilled tile.");
		}
		if(this.watered) {
			throw new InvalidActionException("Already watered.");
		}
		this.watered = true;
	}

	@Override
	public void passDay() {
		if(this.hasOverlay() && this.getOverlay() instanceof Daily) {
			Daily daily = (Daily)this.getOverlay();
			daily.passDay(this);
		}
		if(this.watered) {
			this.watered = false;
		}
		if(this.tilled && this.rand.nextInt(10)<5 && !this.hasOverlay()) {
			this.tilled = false;
		}
	}
	
	@Override
	public String getName() {
		if(this.hasOverlay() && this.getOverlay() instanceof Crop && !this.watered) {
			return "!"+this.getOverlay().getName();
		}
		if(this.hasOverlay()) {
			return this.getOverlay().getName();
		}
		if(!this.tilled) {
			return "Untilled";
		}
		if(!this.watered) {
			return "Unwatered";
		}
	return "Watered";
	}
	
	@Override
	public boolean isTilled() {
		return this.tilled;
	}
	
	@Override
	public boolean isWatered() {
		return this.watered;
	}

	@Override
	public void setModifiers(byte a, byte b, byte c, byte d) {
		if(a == 2) {
			this.tilled = true;
			this.watered = true;
		}
		if(a == 1) {
			this.tilled = true;
		}
	}
	
	@Override
	public byte[] getModifiers() {
		byte[] mods = {0, 0, 0, 0};
		if(this.tilled) {
			mods[0] = 1;
		}
		if(this.watered) {
			mods[0] = 2;
		}
		for(byte b : mods) {
			System.out.println("Byte "+b);
		}
	return mods;
	}
	
	@Override
	public int getId() {
		return TileFactory.FARM;
	}
}
