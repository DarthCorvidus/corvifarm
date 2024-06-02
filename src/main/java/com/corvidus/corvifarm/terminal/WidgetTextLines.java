package com.corvidus.corvifarm.terminal;
public class WidgetTextLines extends WidgetAbstract {
	private String[] lines;
	public WidgetTextLines(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.lines = new String[height];
	}
	
	public void addLine(int i, String line) {
		if(i >= this.height) {
			return;
		}
		this.lines[i] = line;
	}

	@Override
	public String getString() {
		String data = "";
		String padded = "";
		for(String string : this.lines) {
			if(string == null) {
				data += " ".repeat(this.width);
			continue;
			}
			if(string.length()==this.width) {
				data += string;
			}
			if(string.length()<this.width) {
				data += string + " ".repeat(this.width-string.length());
			}
			
			if(string.length()>this.width) {
				data += string.substring(0, this.width);
			}
			
		}
	return data;
	}
	
}
