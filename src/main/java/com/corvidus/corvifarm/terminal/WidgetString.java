package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextImage;

public class WidgetString extends WidgetAbstract {
	public WidgetString(int posX, int posY, int width) {
		super(posX, posY, width, 1);
	}

	public WidgetString(int posX, int posY, int width, String string) {
		super(posX, posY, width, 1);
		this.setString(string);
	}
	
	public void setString(String string) {
		this.tg.drawLine(0, 0, this.getWidth(), 0, TextCharacter.DEFAULT_CHARACTER);
		this.tg.putString(0, 0, string);
	}
	
	@Override
	public TextImage getTextImage() {
		return this.ti;
	}
}
