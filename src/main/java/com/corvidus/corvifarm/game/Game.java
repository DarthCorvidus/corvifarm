package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.TileManipulator;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.persistence.Persistence;
import com.corvidus.corvifarm.room.Rooms;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetLog;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectObserver;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.ui.YesNoQuit;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game implements CalendarObserver, WidgetInputObserver, WASDSelectObserver {
	public Calendar calendar;
	public UserInterface userInterface;
	private WidgetString debug;
	private int called = 0;
	private Player player;
	private Rooms rooms;
	private WidgetLog log;
	private Game(UserInterface userInterface) {
		this.log = new WidgetLog(0, 20, 80, 4);
		this.debug = new WidgetString(40, 0, 40, "Debug");	
		this.userInterface = userInterface;
	}

	private void init() {
		this.rooms.getCurrent().addWASDSelectObserver(this);
		this.rooms.getCurrent().getGround().addItem(new Wood());
		// Not correct, needs to be applied to all rooms.
		this.calendar.addCalendarObserver(this.rooms);
		this.calendar.addCalendarObserver(this.player);
		this.userInterface.addWidget(this.debug);
		this.userInterface.addWidget(this.calendar);
		this.userInterface.addWidget(this.player);
		this.userInterface.addWidget(this.rooms.getCurrent());
		this.userInterface.addWidget(this.rooms.getCurrent().getGround());
		this.userInterface.addWidget(this.log);
		this.userInterface.refresh();
	}
	
	public static Game fromScratch(UserInterface userInterface) {
		Game game = new Game(userInterface);
		game.player = Player.fromScratch();
		game.calendar = Calendar.fromScratch();
		game.rooms = Rooms.fromScratch();
		game.init();
		game.toBinary();
	return game;
	}
	
	public static Game fromBinary(UserInterface userInterface) {
		Game game = new Game(userInterface);
		Persistence persistence = Persistence.fromFile();
		game.calendar = persistence.getCalendar();
		game.player = persistence.getPlayer();
		game.rooms = Rooms.asEmpty();
		game.rooms.load(persistence);
		game.init();
	return game;
	}
	
	public void toBinary() {
		Persistence persistence = new Persistence(this.calendar, this.player);
		this.rooms.save(persistence);
		persistence.toFile();
	}
	
	public UserInterface getUserInterface() {
		return this.userInterface;
	}
	
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public WidgetLog getLog() {
		return this.log;
	}
	
	public Rooms getRooms() {
		return this.rooms;
	}
	
	public void run() {
		this.calendar.addCalendarObserver(this);
		this.userInterface.addInputObserver(this);
		while(true) {
			try {
				this.calendar.run();
				this.userInterface.run();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void gracefulQuit() {
		//Not so graceful quit for the moment ;-).
		System.exit(0);
	}

	@Override
	public void onSecond(Calendar calendar) {
		this.userInterface.refresh();
	}

	@Override
	public void onWakeup(Calendar calendar) {
		this.changeRoom(Rooms.YOUR_HOUSE);
		this.userInterface.refresh();
		this.toBinary();
	}
	
	public void changeRoom(int id) {
		this.userInterface.removeWidget(this.rooms.getCurrent());
		this.userInterface.removeWidget(this.rooms.getCurrent().getGround());
		this.rooms.getCurrent().removeWASDSelectObserver(this);
		this.rooms.change(id);
		this.userInterface.addWidget(this.rooms.getCurrent());
		this.userInterface.addWidget(this.rooms.getCurrent().getGround());
		this.rooms.getCurrent().addWASDSelectObserver(this);
	}

	@Override
	public void onInput(TerminalWidget widget, KeyStroke keyStroke) {
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		if(keyStroke.getCharacter() == 'x') {
			YesNoQuit yesNo = new YesNoQuit(this);
			yesNo.run();
		}
		
		if(keyStroke.getCharacter() == 'r') {
			this.calendar.sleep();
		}
	}

	@Override
	public void onFocus(WASDSelect wasdSelect, WASDSelectElement element) {
		this.called++;
		this.debug.setString(element.getWASDString()+"/"+this.called);
	}
	
	@Override
	public void onSelect(WASDSelect wasdSelect, WASDSelectElement element) {
		Tile tile = (Tile)element.getObject();
		Item item;
		// Tiles with interactive items have precedence.
		if(tile.hasOverlay() && tile.getOverlay() instanceof Interactive interactive) {
			try {
				interactive.interact(this);
			} catch (InvalidActionException e) {
				log.addMessage(e.getMessage());
			}
		return;
		}
		// Check if there is an item in the inventory, otherwise return.
		try {
			item = this.player.getInventory().getCurrentItem();
		} catch (IndexOutOfBoundsException e) {
			this.log.addMessage("No selected item");
		return;
		}
		/*
		 * Alternative, more beautiful pattern:
		 * if(item instanceof TileManipulator tm) {
		*/
		/**
		 * Check if item is an instance of tile manipulator, if so, apply, or
		 * write message (for now, there will be other possible actions, like
		 * eating something or using a device on a tile).
		 */
		if(item instanceof TileManipulator tm) {
			try {
				this.assertValidTime();
				this.player.assertEnergy(tm.getBaseEnergyCost());
				tm.apply(this.player, tile);
				this.player.subEnergy(tm.getBaseEnergyCost());
				this.calendar.fastForward(10);
				this.rooms.getCurrent().refresh();
			} catch (InvalidActionException e) {
				log.addMessage(e.getMessage());
			}
		}
		this.userInterface.refresh();
	}

	private void assertValidTime() throws InvalidActionException {
		if(this.calendar.getDaySeconds()>=60 && this.calendar.getDaySeconds()<=120) {
			throw new InvalidActionException("You are too tired for hard work.");
		}
	}
	
	@Override
	public void onFocusEmpty(WASDSelect wasdSelect) {
		this.called++;
		this.debug.setString("/"+this.called);
	}

	@Override
	public void onSelectEmpty(WASDSelect wasdSelect) {

	}
}
