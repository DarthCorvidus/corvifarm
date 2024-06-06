package com.corvidus.corvifarm.terminal.yesno;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.TerminalWidgetObservable;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetPane;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;

public class WidgetYesNo implements TerminalWidget, TerminalWidgetObservable {
	private final WidgetPane pane;
	private final WidgetString string;
	private final ArrayList<YesNoObserver> observers = new ArrayList<>();
	public WidgetYesNo(int posX, int posY, int width, int height, String question) {
		this.pane = new WidgetPane(posX, posY, width, height);
		this.string = new WidgetString(0, 0, width, question);
		this.pane.addWidget(this.string);
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
		for(YesNoObserver obs: this.observers) {
			if(keyStroke.getCharacter() == 'y') {
				obs.onYes(this);
				return;
			}
			if(keyStroke.getCharacter() == 'n') {
				obs.onNo(this);
				return;
			}
			obs.onInvalid(this);
		}
	}

	@Override
	public void addInputObserver(WidgetInputObserver inputObserver) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void removeInputObserver(WidgetInputObserver inputObserver) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
	
	public void addYesNoObserver(YesNoObserver yesNoObserver) {
		this.observers.add(yesNoObserver);
	}
}
