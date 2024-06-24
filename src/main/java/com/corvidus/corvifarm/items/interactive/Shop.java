package com.corvidus.corvifarm.items.interactive;

import com.corvidus.corvifarm.game.Game;
import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.Interactive;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Crops;
import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.select.WASDSelect;
import com.corvidus.corvifarm.terminal.select.WASDSelectElement;
import com.corvidus.corvifarm.terminal.select.WASDSelectObserver;
import com.corvidus.corvifarm.terminal.select.WASDSelectShop;
import com.corvidus.corvifarm.tiles.WASDSelectGroup;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.List;

public class Shop extends ItemAbstract implements Interactive, WidgetInputObserver, WASDSelectObserver {
	private Game game;
	private WASDSelect wasd;
	private WidgetPane pane;
	@Override
	public int getBaseDemand() {
		return 0;
	}

	@Override
	public String getName() {
		return "Shop";
	}

	@Override
	public int getId() {
		return ItemFactory.SHOP;
	}

	@Override
	public void interact(Game game) throws InvalidActionException {
		this.game = game;
		this.game.getCalendar().pause();
		this.pane = new WidgetPane(20, 2, 60, 18);
		this.wasd = new WASDSelect(0, 1, 60, 17, 20);
		this.pane.addInputObserver(this);
		this.wasd.addWASDSelectObserver(this);
		this.pane.addWidget(wasd);
		this.game.getUserInterface().setFocus(this.pane);
		for(Crop crop : Crops.getPrototypes()) {
			this.wasd.addElement(new WASDSelectShop(crop));
		}
	}

	@Override
	public void onInput(TerminalWidget widget, KeyStroke keyStroke) {
		if(keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'x') {
			this.game.getUserInterface().removeWidget(this.pane);
			this.game.getCalendar().resume();
		}
	}

	@Override
	public void onFocus(WASDSelect wasdSelect, WASDSelectElement element) {
		
	}

	@Override
	public void onSelect(WASDSelect wasdSelect, WASDSelectElement element) {
		Crop crop = (Crop)element.getObject();
		crop = Crops.createSeed(crop.getId());
		try {
			this.game.getPlayer().subGold(crop.getBaseDemand());
			this.game.getPlayer().getInventory().addItem(crop);
		} catch (InvalidActionException e) {
			this.game.getLog().addMessage(e.getMessage());
		}
	}

	@Override
	public void onFocusEmpty(WASDSelect wasdSelect) {
		
	}

	@Override
	public void onSelectEmpty(WASDSelect wasdSelect) {
		
	}
	
}
