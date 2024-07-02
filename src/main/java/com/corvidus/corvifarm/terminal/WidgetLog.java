package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public class WidgetLog extends WidgetAbstract {
	private String[] lines;
	public WidgetLog(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.lines = new String[this.getHeight()];
		for(int i = 0; i<this.getHeight();i++) {
			this.lines[i] = "";
		}
	}
	
	public void clear() {
		for(int i = 0; i<this.getHeight();i++) {
			this.tg.putString(0, i, " ".repeat(this.getWidth()));
		}
	}
	
	private void addLine(String line) {
		for(int i = 0; i<this.getHeight()-1;i++) {
			this.lines[i] = this.lines[i+1];
		}
		this.lines[this.getHeight()-1] = line;
	}
	
	public void addMessage(String message) {
		WordWrapper ww = new WordWrapper(message, this.getWidth());
		for(String str : ww.getLines()) {
			this.addLine(str);
		}
		for(int i = 0; i<this.getHeight();i++) {
			this.tg.putString(0, i, " ".repeat(this.getWidth()));
			this.tg.putString(0, i, this.lines[i]);
		}
	}
	
	@Override
	public void onInput(KeyStroke keyStroke) {
		
	}
	
}
