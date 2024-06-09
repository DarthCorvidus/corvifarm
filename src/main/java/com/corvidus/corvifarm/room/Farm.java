package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
public class Farm extends RoomAbstract {
	private Farm() {
		super();
	}
	
	private void init() {
		this.wasd.addElement(new WASDSelectString("15×Oak Tree"));
		this.wasd.addElement(new WASDSelectString("17×Maple Tree"));
		this.wasd.addElement(new WASDSelectString("28×Stone"));
	}
	
	public static Farm fromScratch() {
		Farm farm = new Farm();
		farm.init();
	return farm;
	}
	
	@Override
	public void onEnter() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public String getName() {
		return "Your Farm";
	}

	@Override
	public void onSecond(Calendar calendar) {
		
	}

	@Override
	public void onWakeup(Calendar calendar) {
		
	}
}
