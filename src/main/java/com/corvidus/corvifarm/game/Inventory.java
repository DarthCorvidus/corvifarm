package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetTextLines;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory implements TerminalWidget {
	private List<Item> items = new ArrayList();
	private WidgetTextLines text;
	private int selected = 0;
	public Inventory() {
		this.text = new WidgetTextLines(0, 2, 20, 10);
		this.refresh();
	}
	
	private void refresh() {
		for(int i = 0;i<10;i++) {
			String mark = " ";
			String itemName = "";
			Item item;
			try {
				item = this.items.get(i);
				itemName = item.getName();
				if(item.getAmount()>1) {
					itemName = item.getAmount()+"×"+itemName;
				}
			} catch(IndexOutOfBoundsException e) {
				
			}
			if(i == this.selected) {
				mark = "*";
			}
			if(i == 9) {
				this.text.setLine(i, Integer.toString(0)+mark+itemName);
			continue;
			}
			this.text.setLine(i, Integer.toString(i+1)+mark+itemName);
		}
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		this.refresh();
	}
	
	@Override
	public int getPosX() {
		return text.getPosX();
	}

	@Override
	public int getPosY() {
		return text.getPosY();
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
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		
		List<Character> numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
		if(!numbers.contains(keyStroke.getCharacter())) {
			return;
		}
		if(keyStroke.getCharacter() == '0') {
			this.selected = 9;
			this.refresh();
		return;
		}
		this.selected = Character.getNumericValue(numbers.get(Character.getNumericValue(keyStroke.getCharacter())-1));
		this.refresh();
	}
}
