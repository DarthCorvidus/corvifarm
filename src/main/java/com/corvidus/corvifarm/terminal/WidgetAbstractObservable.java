/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.input.KeyStroke;

/**
 *
 * @author hm
 */
public class WidgetAbstractObservable extends WidgetAbstract implements TerminalWidgetObservable {
	public WidgetAbstractObservable(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
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
