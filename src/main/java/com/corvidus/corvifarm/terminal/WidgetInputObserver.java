package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.input.KeyStroke;

public interface WidgetInputObserver {
	public boolean hasFilter();
	public void onInput(TerminalWidget widget, KeyStroke keyStroke);
}
