/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextImage;
import java.util.ArrayList;

/**
 *
 * @author hm
 */
public class WidgetPane extends WidgetAbstract {
	private ArrayList<TerminalWidget> widgets = new ArrayList<>();
			
	public WidgetPane(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	public void addWidget(TerminalWidget widget) {
		this.widgets.add(widget);
	}
	
	public void removeWidget(TerminalWidget widget) {
		this.widgets.remove(widget);
	}
	
	@Override
	public TextImage getTextImage() {
		/**
		 * We have to redraw, otherwise changes made to one of the widgets do not
		 * apply.
		 */
		for(TerminalWidget widget: this.widgets) {
			this.tg.drawImage(new TerminalPosition(widget.getPosX(), widget.getPosY()), widget.getTextImage());
		}
		return this.ti;
	}
}
