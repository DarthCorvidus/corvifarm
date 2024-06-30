package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.persistence.Persistence;
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
	public static Rooms asEmpty() {
		Rooms rooms = new Rooms();
		rooms.current = new YourHouse();
		rooms.add(rooms.current);
		rooms.add(new Farm());
	return rooms;
	}
	
	public static Rooms fromScratch() {
		Rooms rooms = Rooms.asEmpty();
		rooms.init();
	return rooms;
	}
	
	public void save(Persistence persistence) {
		for(int key : this.rooms.keySet()) {
			this.rooms.get(key).save(persistence);
		}
	}
	
	private void init() {
		for(int key : this.rooms.keySet()) {
			this.rooms.get(key).init();
			this.rooms.get(key).refresh();
		}
	}
	
	private void add(Room room) {
		room.refresh();
		this.rooms.put(room.getId(), room);
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
