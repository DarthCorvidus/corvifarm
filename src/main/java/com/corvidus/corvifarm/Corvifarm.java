package com.corvidus.corvifarm;

import com.corvidus.corvifarm.game.Calendar;
import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.terminal.VT100;
import com.corvidus.corvifarm.input.Input;
import com.corvidus.corvifarm.input.InputObserver;

/**
 *
 * @author hm
 */
public class Corvifarm implements CalendarObserver, InputObserver {
	private Calendar cal;
	private Input input;
	public Corvifarm() {
		this.cal = new Calendar();
		this.input = new Input();
	}
	
	public void run() {
		VT100.clearScreen();
		this.cal.addCalendarObserver(this);
		this.input.addInputObserver(this);
		while(true) {
			try {
				this.cal.run();
				this.input.run();
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

	@Override
	public void onInput(Input input, char c) {
		if(c == 'x') {
			System.exit(0);
		}
		if(c == 's') {
			this.cal.sleep();
		}
	}
}
