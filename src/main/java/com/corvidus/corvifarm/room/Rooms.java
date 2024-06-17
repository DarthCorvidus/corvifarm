package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rooms implements CalendarObserver {
	private Map<Integer, Room> rooms = new HashMap<>();
	private Room current;
	public static int YOUR_HOUSE = 0;
	public static int YOUR_FARM = 1;
	private Rooms() {
		
	}
	public static Rooms fromScratch() {
		Rooms rooms = new Rooms();
		rooms.current = YourHouse.fromScratch();
		rooms.rooms.put(Rooms.YOUR_HOUSE, rooms.current);
		rooms.rooms.put(Rooms.YOUR_FARM, Farm.fromScratch());
	return rooms;
	}
	
	public Room getCurrent() {
		return this.current;
	}
	
	public void change(int id) {
		this.current = this.rooms.get(id);
	}
	
	public Room getRoom(int room) {
		return this.rooms.get(room);
	}

	@Override
	public void onSecond(Calendar calendar) {
		//for(int key : this.rooms.keySet()) {
		//	this.rooms.get(key).onSecond(calendar);
		//}
	}

	@Override
	public void onWakeup(Calendar calendar) {
		for(int key : this.rooms.keySet()) {
			this.rooms.get(key).onWakeup(calendar);
		}
	}
}
