package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;

public class Calendar implements TerminalWidget {
	private int seconds = 0;
	private final Cooldown cooldown;
	private final ArrayList<CalendarObserver> calendarObservers = new ArrayList<>();
	private final WidgetString widgetString;
	private boolean paused = false;
	public Calendar() {
		this.seconds = 6*60;
		this.cooldown = new Cooldown(1000);
		this.widgetString = new WidgetString(0, 0, 40, this.getDate());
	}
	
	public void pause() {
		this.paused = true;
	}
	
	public void resume() {
		this.paused = false;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void addCalendarObserver(CalendarObserver calendarObserver) {
		this.calendarObservers.add(calendarObserver);
	}
	
	public String getTime() {
		int minutes = this.seconds % 60;
		int hours = Math.floorDiv(this.seconds, 60) % 24;
	return String.format("%02d:%02d", hours, minutes);
	}
	
	public int getJulian() {
		return Math.floorDiv(this.seconds, 60*24);
	}
	
	public int getYear() {
		return Math.floorDiv(this.seconds, 60*24*28*4)+1;
	}
	
	public int getDay() {
		return (Math.floorDiv(this.seconds, (60*24)) % 28)+1;
	}

	public String getSeason() {
		int season = Math.floorDiv(this.seconds, (60*24*28)) % 4;
		String[] seasons = {"Spring", "Summer", "Fall", "Winter"};
	return seasons[season];
	}
	
	
	public final String getDate() {
		int day = this.getDay();
		String season = this.getSeason();
		int year = this.getYear();
		String time = this.getTime();
		return String.format("Day %d %s (%s, Year %d)", day, time, season, year);
	}
	
	public int getSeconds() {
		return this.seconds;
	}

	public void incr() {
		this.seconds++;
		this.widgetString.setString(this.getDate());
		for(CalendarObserver obs : this.calendarObservers) {
			obs.onSecond(this);
		}
	}
	
	public void fastForward(int seconds) {
		for(int i = 0; i<seconds;i++) {
			this.incr();
		}
	}
	
	public void sleep() {
		int days = this.getJulian();
		int seconds = days * 60*24;
		if(this.seconds<=seconds + (2*60)) {
			this.seconds = (days * 60*24) + (6*60);
		} else {
			this.seconds = (days * 60*24) + (30*60);
		}
		this.widgetString.setString(this.getDate());
		for(CalendarObserver obs : this.calendarObservers) {
			obs.onWakeup(this);
		}
	}

	public void run() {
		if(this.cooldown.ready() && !this.paused) {
			this.incr();
			if(this.getTime().equals("02:00")) {
				this.sleep();
			}
		}
	}

	@Override
	public TextImage getTextImage() {
		return this.widgetString.getTextImage();
	}

	@Override
	public int getPosX() {
		return this.widgetString.getPosX();
	}

	@Override
	public int getPosY() {
		return this.widgetString.getPosY();
	}

	@Override
	public int getHeight() {
		return this.widgetString.getHeight();
	}

	@Override
	public int getWidth() {
		return this.widgetString.getWidth();
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		char c = keyStroke.getCharacter();
		int delay = this.cooldown.getDelayMilliseconds();
		if(c == '+' && delay > 100) {
				this.cooldown.setDelayMilliseconds(delay - 100);
			return;
		}

		if(c == '+' && delay > 10 && delay <= 100) {
			this.cooldown.setDelayMilliseconds(delay - 10);
			return;
		}
		/*
		if(c == '-' && this.delay < 100) {
			this.delay += 10;
			return;
		}
		
		if(c == '-' && this.delay >= 100) {
			this.delay += 100;
			return;
		}
		*/
	}
}
