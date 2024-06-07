package com.corvidus.corvifarm.terminal.select;

import com.corvidus.corvifarm.terminal.WidgetAbstract;
import com.corvidus.corvifarm.terminal.WidgetString;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;

public class WASDSelect extends WidgetAbstract {
	private int colwidth;
	private int visible = 0;
	private WidgetString[] strWidgets;
	private ArrayList<WASDSelectElement> elements = new ArrayList<>();
	private int offset;
	private int selected = 0;
	private int screenSelected = 0;
	private int visibleColumns;
	//private ArrayList<WidgetString> strWidgets = new ArrayList<>();
	public WASDSelect(int posX, int posY, int width, int height, int colwidth) {
		super(posX, posY, width, height);
		this.colwidth = colwidth;
		int wideWidth = colwidth + 3;
		/*
		 * WideWith is colWidth + 3: [* CONTENT ]. The last column is one character
		 * too wide, because it does not need the blank to separate itself from
		 * the next column. Hence, the width is extended by this additional
		 * blank to get a correct result for "how many columns of width w fit into
		 * total width tw."
		 */
		int columns = Math.floorDiv(this.getWidth()+1, wideWidth);
		int rows = this.getHeight();
		this.visible = rows*columns;
		this.visibleColumns = columns;
		this.strWidgets = new WidgetString[this.visible];
		int k = 0;
		/**
		 * Loop to create the maximum amount of fields which can be visible on
		 * the screen at one time.
		 */
		for(int col = 0; col<columns;col++) {
			for(int row = 0;row < this.getHeight(); row++) {
				this.strWidgets[k] = new WidgetString((col*wideWidth), row, wideWidth);
				if(k == 0) {
					this.strWidgets[k].setString("*");
				}
				k++;
			}
		}
	}
	
	public void addElement(WASDSelectElement element) {
		this.elements.add(element);
	}
	
	public void removeElement(WASDSelectElement element) {
		this.elements.remove(element);
	}
	
	@Override
	public void onInput(KeyStroke keyStroke) {
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		
		if(keyStroke.getCharacter() == 's' && this.screenSelected < this.strWidgets.length - 1) {
			this.screenSelected++;
		}
		
		if(keyStroke.getCharacter() == 'w' && this.screenSelected > 0) {
			this.screenSelected--;
		}

		if(keyStroke.getCharacter() == 'd' && this.screenSelected + this.visibleColumns < this.strWidgets.length -1) {
			this.screenSelected += this.visibleColumns;
		}

		if(keyStroke.getCharacter() == 'a' && this.screenSelected - this.visibleColumns >= 0) {
			this.screenSelected -= this.visibleColumns;
		}

	}
	
	private void rewriteStrings(TextGraphics th) {
		for(int i = 0; i<this.strWidgets.length;i++) {
			WidgetString strWidget = this.strWidgets[i];
			String elementName = "";
			try {
				elementName = this.elements.get(i).getWASDString();
			} catch (IndexOutOfBoundsException e) {
				
			}
			
			if(i == this.screenSelected) {
				strWidget.setString("* "+elementName);
			} else {
				strWidget.setString("  "+elementName);
			}
			tg.drawImage(new TerminalPosition(strWidget.getPosX(), strWidget.getPosY()), strWidget.getTextImage());
		}
	}
	
	@Override
	public TextImage getTextImage() {
		TextImage ti = super.getTextImage();
		TextGraphics tg = ti.newTextGraphics();
		this.rewriteStrings(tg);
	return ti;
	}
}