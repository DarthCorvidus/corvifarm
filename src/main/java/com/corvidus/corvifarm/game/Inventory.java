package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.tools.Hoe;
import com.corvidus.corvifarm.items.tools.Pickaxe;
import com.corvidus.corvifarm.items.tools.Scythe;
import com.corvidus.corvifarm.items.tools.Watercan;
import com.corvidus.corvifarm.persistence.Bitmap;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetTextLines;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory implements TerminalWidget {
	private Map<Integer, Item> items = new HashMap<>();
	private WidgetTextLines text;
	private int selected = 0;
	private int page = 0;
	private int pages = 3;
	static int MAX_PAGES = 3;
	public Inventory() {
		this.text = new WidgetTextLines(0, 2, 20, 10);
		this.refresh();
	}
	
	public static Inventory fromScratch() {
		Inventory inventory = new Inventory();
		inventory.addItem(new Hoe());
		inventory.addItem(new Watercan());
		inventory.addItem(new Axe());
		inventory.addItem(new Pickaxe());
		inventory.addItem(new Scythe());
		inventory.addItem(ItemFactory.getPrototype(ItemFactory.WHEAT, 15));
	return inventory;
	}
	
	public static Inventory fromBinary(DataInputStream dis) throws IOException {
		Inventory inventory = new Inventory();
		byte[] bytes = new byte[4];
		for(int i = 0; i<bytes.length;i++) {
			bytes[i] = dis.readByte();
		}
		Bitmap bitmap = Bitmap.fromBytes(bytes);
		for(int i = 0; i<MAX_PAGES*10;i++) {
			if(bitmap.getBoolean(i)==false) {
				Item.fromBinary(dis);
			continue;
			}
			Item item = Item.fromBinary(dis);
			inventory.items.put(i, item);
		}
		inventory.refresh();
	return inventory;
	}
	
	public void toBinary(DataOutputStream dos) throws IOException {
		Bitmap bitmap = new Bitmap(4);
		for(int i = 0; i<MAX_PAGES*10;i++) {
			Item item = this.items.get(i);
			if(item != null) {
				bitmap.setBoolean(i, true);
			}
		}
		dos.write(bitmap.getBytes());
		for(int i = 0; i<MAX_PAGES*10;i++) {
			Item item = this.items.get(i);
			if(item != null) {
				item.toBinary(dos);
			} else {
				Item.writeEmpty(dos);
			}
		}
	}
	
	public boolean hasCurrentItem() {
		Item item = this.items.get(this.selected+(this.page*10));
		return item != null;
	}
	
	public Item getCurrentItem() throws IndexOutOfBoundsException {
		Item item = this.items.get(this.selected+(this.page*10));
		if(item == null) {
			throw new IndexOutOfBoundsException("no item at current inventory position");
		}
	return item;
	}
	
	public void subCurrentItem() {
		if(!this.hasCurrentItem()) {
			return;
		}
		Item item = this.getCurrentItem();
		item.setAmount(item.getAmount() - 1);
		if(item.getAmount() == 0) {
			this.items.remove(this.selected);
		}
		this.refresh();
	}
	
	private void refresh() {
		for(int i = 0;i<10;i++) {
			String mark = " ";
			String itemName = "";
			Item item;
			if(this.items.get(i+(this.page*10)) != null) {
				item = this.items.get(i+(this.page*10));
				itemName = item.getName();
				if(item.getAmount()>1) {
					itemName = item.getAmount()+"×"+itemName;
				}
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
		for(int i = 0; i<this.pages*10;i++) {
			Item existing = this.items.get(i);
			if(existing == null) {
				this.items.put(i, item);
				this.refresh();
				return;
			}
			if(existing.getName().equals(item.getName())) {
				existing.addAmount(item.getAmount());
				this.refresh();
				return;
				
			}
		}
		this.refresh();
	}
	
	public void addItems(List<Item> items) {
		for(Item item : items) {
			if(item.getAmount()==0) {
				continue;
			}
			this.addItem(item);
		}
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
		if(keyStroke.getKeyType() == KeyType.Tab) {
			if(this.page < 2) {
				this.page++;
			} else {
				this.page = 0;
			}
			this.refresh();
		}
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
