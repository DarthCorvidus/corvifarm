/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.ui.YesNoQuit;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game implements CalendarObserver, WidgetInputObserver {
	public Calendar calendar;
	public UserInterface userInterface;
	public Game() {
		this.userInterface = new UserInterface();
		this.calendar = new Calendar();
		this.userInterface.addWidget(calendar);
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
}
