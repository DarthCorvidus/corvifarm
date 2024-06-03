package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.graphics.TextImage;

public interface TerminalWidget {
	public int getPosX();
	public int getPosY();
	public int getHeight();
	public int getWidth();
	public TextImage getTextImage();
}