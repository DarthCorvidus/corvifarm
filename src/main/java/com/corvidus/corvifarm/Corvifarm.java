package com.corvidus.corvifarm;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.terminal.VT100;

/**
 *
 * @author hm
 */
public class Corvifarm implements CalendarObserver {
	private Calendar cal;
	public Corvifarm() {
		this.cal = new Calendar();
	}
	
	public void run() {
		VT100.clearScreen();
		this.cal.addCalendarObserver(this);
		while(true) {
			try {
				this.cal.run();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
	}

	public static void main(String[] args) {
		Corvifarm farm = new Corvifarm();
		farm.run();
	}

	@Override
	public void onSecond(Calendar calendar) {
		VT100.cursorHome();
		System.out.print(calendar.getDate());
	}

	@Override
	public void onWakeup(Calendar calendar) {
		
	}
}
