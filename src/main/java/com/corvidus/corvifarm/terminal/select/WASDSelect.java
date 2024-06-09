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
	private int stringsVisible = 0;
	private WidgetString[] strWidgets;
	private ArrayList<WASDSelectElement> elements = new ArrayList<>();
	private int offset;
	private int selected = 0;
	private int screenSelected = 0;
	private int columnsVisible;
	private int columnsTotal = 0;
	private ArrayList<WASDSelectObserver> wasdSelectObservers = new ArrayList<>();
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
		this.columnsVisible = Math.floorDiv(this.getWidth()+1, wideWidth);
		
		int rows = this.getHeight();
		this.stringsVisible =  rows*this.columnsVisible;
		this.strWidgets = new WidgetString[this.stringsVisible];
		int k = 0;
		/**
		 * Loop to create the maximum amount of fields which can be visible on
		 * the screen at one time.
		 */
		for(int col = 0; col<this.columnsVisible;col++) {
			for(int row = 0;row < this.getHeight(); row++) {
				this.strWidgets[k] = new WidgetString((col*wideWidth), row, wideWidth-1);
				if(k == 0) {
					this.strWidgets[k].setString("*");
				}
				k++;
			}
		}
	}
	
	public void addElement(WASDSelectElement element) {
		this.elements.add(element);
		this.columnsTotal = (int)Math.ceil((double)this.elements.size()/this.getHeight());
	}
	
	public void removeElement(WASDSelectElement element) {
		this.elements.remove(element);
		this.columnsTotal = (int)Math.ceil(this.elements.size()/this.getHeight());
	}
	
	public void addWASDSelectObserver(WASDSelectObserver observer) {
		this.wasdSelectObservers.add(observer);
	}

	public void removeWASDSelectObserver(WASDSelectObserver observer) {
		this.wasdSelectObservers.remove(observer);
	}
	
	private void callOnFocus() {
		ArrayList<WASDSelectObserver> tmp = new ArrayList<>(this.wasdSelectObservers);
		try {
			WASDSelectElement element = this.elements.get(this.selected);
			for(WASDSelectObserver observer : tmp) {
				observer.onFocus(this, element);
			}
		} catch (IndexOutOfBoundsException e) {
			for(WASDSelectObserver observer : tmp) {
				observer.onFocusEmpty(this);
			}
		}
	}
	
	public int getSelectedIndex() {
		return this.selected;
	}

	private boolean cursorRightMost() {
		int selectedVisibleColumn = Math.floorDiv(this.screenSelected, this.getHeight());
	return selectedVisibleColumn + 1 == this.columnsVisible;
	}

	private boolean cursorLeftMost() {
		int selectedColumn = Math.floorDiv(this.screenSelected, this.getHeight());
	return selectedColumn == 0;
	}
	
	private void cursorDown() {
		int selectedColumn = Math.floorDiv(this.selected, this.getHeight());
		if(this.screenSelected < this.strWidgets.length - 1) {
			this.screenSelected++;
			this.selected++;
			this.callOnFocus();
		}
		int selectedRow = this.screenSelected % this.getHeight();
		if(this.cursorRightMost() && selectedColumn + 1 < this.columnsTotal && selectedRow == this.getHeight()-1) {
			this.offset++;
			this.screenSelected -= (this.getHeight()-1);
			this.selected++;
			this.callOnFocus();
		}
	}
	
	private void cursorUp() {
		boolean leftmost = this.cursorLeftMost();
		if(this.screenSelected > 0) {
			this.screenSelected--;
			this.selected--;
			this.callOnFocus();
		}
		int selectedRow = this.screenSelected % this.getHeight();
		if(leftmost && this.offset > 0 && selectedRow == 0) {
			this.offset--;
			this.screenSelected += this.getHeight()-1;
			this.selected--;
			this.callOnFocus();
		}
	}

	private void cursorRight() {
		int selectedColumn = Math.floorDiv(this.selected, this.getHeight());
		boolean rightmost = this.cursorRightMost();
		if(!rightmost) {
			this.screenSelected += this.getHeight();
			this.selected += this.getHeight();
			this.callOnFocus();
		}
		
		if(rightmost && selectedColumn + 1 < this.columnsTotal) {
			this.offset++;
			this.selected += this.getHeight();
			this.callOnFocus();
		}
	}
	
	private void cursorLeft() {
		boolean leftmost = this.cursorLeftMost();
		if(!leftmost) {
			this.screenSelected -= this.getHeight();
			this.selected -= this.getHeight();
			this.callOnFocus();
		}
		
		if(leftmost && this.offset > 0) {
			this.offset--;
			this.selected -= this.getHeight();
			this.callOnFocus();
		}
	}
	
	@Override
	public void onInput(KeyStroke keyStroke) {
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		
		if(keyStroke.getCharacter() == 's') {
			this.cursorDown();
		}
		
		if(keyStroke.getCharacter() == 'w') {
			this.cursorUp();
		}

		if(keyStroke.getCharacter() == 'd') {
			this.cursorRight();
		}

		if(keyStroke.getCharacter() == 'a') {
			this.cursorLeft();
		}
	}
	
	private void rewriteStrings(TextGraphics th) {
		for(int i = 0; i<this.strWidgets.length;i++) {
			WidgetString strWidget = this.strWidgets[i];
			String elementName = "";
			try {
				elementName = this.elements.get(i+(this.offset*this.getHeight())).getWASDString();
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
