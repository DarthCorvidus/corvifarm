package com.corvidus.corvifarm.terminal;
public class WidgetTextLines extends WidgetAbstract {
	private String[] lines;
	public WidgetTextLines(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	public void addLine(int i, String line) {
		if(i >= this.height) {
			return;
		}
		this.lines[i] = line;
	}
}
