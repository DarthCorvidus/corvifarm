package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.input.KeyStroke;

public interface WidgetInputObserver {
	public void onInput(TerminalWidget widget, KeyStroke keyStroke);
}
