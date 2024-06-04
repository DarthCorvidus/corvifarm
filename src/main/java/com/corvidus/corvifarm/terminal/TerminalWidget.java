package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
public interface TerminalWidget {
	public int getPosX();
	public int getPosY();
	public int getHeight();
	public int getWidth();
	public TextImage getTextImage();
	public void onInput(KeyStroke keyStroke);
	public void addInputObserver(WidgetInputObserver inputObserver);
	public void removeInputObserver(WidgetInputObserver inputObserver);
}