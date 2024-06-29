package com.corvidus.corvifarm.persistence;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.room.Room;
import com.corvidus.corvifarm.room.Rooms;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.TileFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persistence {
	private Calendar calendar;
	private Player player;
	private Rooms rooms;
	private Map<Integer, Tile> tiles = new HashMap<>();
	private List<PersistenceTile> pTiles = new ArrayList<>();
	private List<PersistenceItem> pItem = new ArrayList<>();
	private int serialTile = 0;
	private int serialItem = 0;
	private Persistence() {
		
	}
	public Persistence(Calendar calendar, Player player) {
		this.calendar = calendar;
		this.player = player;
	}
	
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	private int getSerialTile() {
		return this.serialTile++;
	}
	
	private int getSerialItem() {
		return this.serialItem++;
	}
	
	
	public void addRoom(Room room) {
		for(Tile tile: room.getTiles()) {
			this.addTile(tile, room.getId());
		}
	}
	
	private void addTile(Tile tile, int roomId) {
		if(!tile.hasOverlay()) {
			PersistenceTile pt = PersistenceTile.fromTile(this.getSerialTile(), tile, roomId);
			this.pTiles.add(pt);
		return;
		}
		PersistenceTile pt = PersistenceTile.fromTile(this.getSerialTile(), tile, roomId);
		this.pTiles.add(pt);
		this.addItem(tile.getOverlay(), pt);
	}
	
	private void addItem(Item item, PersistenceTile pt) {
		PersistenceItem pi = PersistenceItem.fromItem(this.getSerialItem(), item, pt);
		this.pItem.add(pi);
	}
	
	
	public void toFile() {
		try (FileOutputStream fos = new FileOutputStream("game.sav.tmp")) {
			DataOutputStream dos = new DataOutputStream(fos);
			this.calendar.toBinary(dos);
			this.player.toBinary(dos);
			dos.writeInt(this.pTiles.size());
			for(PersistenceTile pt : this.pTiles) {
				pt.toBinary(dos);
			}
			dos.writeInt(this.pItem.size());
			for(PersistenceItem pi : this.pItem) {
				pi.toBinary(dos);
			}
			
			Files.move(Paths.get("game.sav.tmp"), Paths.get("game.sav"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static Persistence fromFile() {
		Persistence persistence = new Persistence();
		try (FileInputStream fis = new FileInputStream("game.sav")) {
			DataInputStream dis = new DataInputStream(fis);
			persistence.calendar = Calendar.fromBinary(dis);
			persistence.player = Player.fromBinary(dis);
			int amountTiles = dis.readInt();
			for(int i = 0; i <amountTiles; i++) {
				persistence.pTiles.add(PersistenceTile.fromBinary(dis));
			}
			int amountItems = dis.readInt();
			for(int i = 0; i <amountItems; i++) {
				persistence.pItem.add(PersistenceItem.fromBinary(dis));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	return persistence;
	}
}
