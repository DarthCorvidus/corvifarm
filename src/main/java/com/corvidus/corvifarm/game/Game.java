/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.UserInterface;

public class Game implements CalendarObserver {
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
}
