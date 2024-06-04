package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;

public abstract class WidgetAbstract implements TerminalWidget {
	protected int posX;
	protected int posY;
	protected TextImage ti;
	protected TextGraphics tg;
	protected ArrayList<WidgetInputObserver> inputObservers = new ArrayList<>();
	public WidgetAbstract(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.ti = new BasicTextImage(width, height);
		this.tg = this.ti.newTextGraphics();
	}
			
	@Override
	public int getPosX() {
		return this.posX;
	}

	@Override
	public int getPosY() {
		return this.posY;
	}

	@Override
	public int getHeight() {
		return this.ti.getSize().getRows();
	}

	@Override
	public int getWidth() {
		return this.ti.getSize().getColumns();
	}
	
	@Override
	public TextImage getTextImage() {
		return this.ti;
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
		for(WidgetInputObserver obs: this.inputObservers) {
			obs.onInput(this, keyStroke);
		}
	}

	@Override
	public void addInputObserver(WidgetInputObserver inputObserver) {
		this.inputObservers.add(inputObserver);
	}

	@Override
	public void removeInputObserver(WidgetInputObserver inputObserver) {
		this.inputObservers.remove(this.inputObservers);
	}

}
