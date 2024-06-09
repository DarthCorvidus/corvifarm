package com.corvidus.corvifarm.room;

import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;

public abstract class RoomAbstract implements Room {
	protected WidgetPane pane;
	protected WASDSelect wasd;
	protected WidgetString name;
	protected RoomAbstract() {
		this.pane = new WidgetPane(20, 2, 60, 18);
		this.wasd = new WASDSelect(0, 1, 60, 17, 15);
		this.wasd.setModeCursor();
		this.name = new WidgetString(0, 0, 60, this.getName());
		this.pane.addWidget(this.wasd);
		this.pane.addWidget(this.name);
	}
	
	@Override
	public int getPosX() {
		return this.pane.getPosX();
	}

	@Override
	public int getPosY() {
		return this.pane.getPosY();
	}

	@Override
	public int getHeight() {
		return this.pane.getHeight();
	}

	@Override
	public int getWidth() {
		return this.pane.getWidth();
	}

	@Override
	public TextImage getTextImage() {
		return this.pane.getTextImage();
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
		this.wasd.onInput(keyStroke);
	}
	
}
