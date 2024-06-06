/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;

/**
 *
 * @author hm
 */
public class WidgetPane extends WidgetAbstract {
	private boolean clear = false;
	private TerminalWidget focus = null;
	private final ArrayList<TerminalWidget> widgets = new ArrayList<>();
	public WidgetPane(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	public void addWidget(TerminalWidget widget) {
		this.widgets.add(widget);
	}
	
	/**
	 * Remove widget. If widget was used as focus, set focus to null.
	 * @param widget 
	 */
	public void removeWidget(TerminalWidget widget) {
		this.clear = true;
		this.widgets.remove(widget);
		if(this.focus == widget) {
			this.focus = null;
		}
	}
	
	/**
	 * Set Focus to widget. If widget was not already added to WidgetPane, add
	 * automatically.
	 * @param widget 
	 */
	public void setFocus(TerminalWidget widget) {
		if(!this.widgets.contains(widget)) {
			this.addWidget(widget);
		}
		this.focus = widget;
	}
	
	@Override
	public TextImage getTextImage() {
		/**
		 * If this.clear is true, clear picture once.
		 */
		if(this.clear) {
			this.clear = false;
			this.ti.setAll(TextCharacter.DEFAULT_CHARACTER);
		}
		/**
		 * We have to redraw, otherwise changes made to one of the widgets do not
		 * apply.
		 */
		for(TerminalWidget widget: this.widgets) {
			this.tg.drawImage(new TerminalPosition(widget.getPosX(), widget.getPosY()), widget.getTextImage());
		}
		return this.ti;
	}
	
	@Override
	public void onInput(KeyStroke keyStroke) {
		/**
		 * If a focus is set, call focus only.
		 */
		if(this.focus != null) {
			this.focus.onInput(keyStroke);
			return;
		}
		super.onInput(keyStroke);
		/**
		 * If a focus is set by WidgetPane's own callback, do not call onInput
		 * on it's children.
		 */
		if(this.focus != null) {
			return;
		}
		/**
		 * We want to be able to add widgets out of observers, for instance
		 * when using 'x' to quit in the main game. This will add another widget
		 * to this.widgets while onInput is still iterating over the list of
		 * widgets, which causes a concurrency error with ArrayList.
		 * Therefore, we create a copy named tmp and iterate over it.
		 */
		ArrayList<TerminalWidget> tmp = new ArrayList<>(this.widgets);
		for(TerminalWidget widget : tmp) {
			widget.onInput(keyStroke);
		}
	}
}
