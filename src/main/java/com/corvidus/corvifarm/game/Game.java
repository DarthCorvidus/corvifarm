/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.yesno.WidgetYesNo;
import com.corvidus.corvifarm.terminal.yesno.YesNoObserver;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game implements CalendarObserver, WidgetInputObserver, YesNoObserver {
	public Calendar calendar;
	public UserInterface userInterface;
	public Game() {
		this.userInterface = new UserInterface();
		this.calendar = new Calendar();
		this.userInterface.addWidget(calendar);
		this.userInterface.refresh();
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
			WidgetYesNo yesno = new WidgetYesNo(10, 5, 20, 4, "Quit (y/N)");
			yesno.addYesNoObserver(this);
			this.calendar.pause();
			this.userInterface.setFocus(yesno);
			this.userInterface.refresh();
		}
	}

	@Override
	public void onYes(WidgetYesNo widgetYesNo) {
		System.exit(0);
	}

	@Override
	public void onNo(WidgetYesNo widgetYesNo) {
		this.calendar.resume();
		this.userInterface.removeWidget(widgetYesNo);
	}

	@Override
	public void onInvalid(WidgetYesNo widgetYesNo) {
		this.calendar.resume();
		this.userInterface.removeWidget(widgetYesNo);
	}
}
