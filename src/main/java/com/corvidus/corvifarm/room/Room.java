package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.game.CalendarObserver;
import com.corvidus.corvifarm.terminal.TerminalWidget;

public interface Room extends TerminalWidget, CalendarObserver {
	public void onEnter();
	public String getName();
}
