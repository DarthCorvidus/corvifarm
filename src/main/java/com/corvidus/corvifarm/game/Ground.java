package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetTextLines;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public class Ground implements TerminalWidget {
	private WidgetTextLines text;
	private List<Item> items = new ArrayList<>();
	public Ground() {
		this.text = new WidgetTextLines(0, 14, 20, 6);
		this.text.setLine(0, "Ground");
	}

	@Override
	public int getPosX() {
		return this.text.getPosX();
	}

	@Override
	public int getPosY() {
		return this.text.getPosY();
	}

	@Override
	public int getHeight() {
		return this.text.getHeight();
	}

	@Override
	public int getWidth() {
		return this.text.getWidth();
	}

	@Override
	public TextImage getTextImage() {
		return this.text.getTextImage();
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
	}

	public void refresh() {
		int i = 1;
		for(Item it: this.items) {
			if(i == 5) {
				return;
			}
			this.text.setLine(i, it.getName());
			i++;
		}
	}
	
	public void addItem(Item item) {
		for(Item it: this.items) {
			if(it.getName().equals(item.getName())) {
				it.addAmount(item.getAmount());
				this.refresh();
				return;
			}
		}
		this.items.add(item);
		this.refresh();
	}
}
