/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.room.Farm;
import com.corvidus.corvifarm.room.Room;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectObserver;
import com.corvidus.corvifarm.terminal.select.WASDSelectString;
import com.corvidus.corvifarm.ui.YesNoQuit;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game implements CalendarObserver, WidgetInputObserver {
	public Calendar calendar;
	public UserInterface userInterface;
	private WidgetString debug;
	private int called = 0;
	private Player player;
	private Room room;
	public Game() {
		this.player = Player.fromScratch();
		this.debug = new WidgetString(40, 0, 40, "Debug");	
		this.userInterface = new UserInterface();
		this.calendar = new Calendar();
		this.room = Farm.fromScratch();
		this.calendar.addCalendarObserver(this.room);
		this.userInterface.addWidget(this.debug);
		this.userInterface.addWidget(calendar);
		this.userInterface.addWidget(player);
		this.userInterface.addWidget(this.room);
		this.userInterface.refresh();
	}
	
	public UserInterface getUserInterface() {
		return this.userInterface;
	}
	
	public Calendar getCalendar() {
		return this.calendar;
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
		this.userInterface.refresh();
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
	}
	/*
	@Override
	public void onFocus(WASDSelect wasdSelect, WASDSelectElement element) {
		this.called++;
		this.debug.setString(element.getWASDString()+"/"+this.called);
	}
	
	@Override
	public void onSelect(WASDSelect wasdSelect, WASDSelectElement element) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void onFocusEmpty(WASDSelect wasdSelect) {
		this.called++;
		this.debug.setString("/"+this.called);
	}

	@Override
	public void onSelectEmpty(WASDSelect wasdSelect) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
	*/
}
