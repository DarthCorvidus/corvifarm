package com.corvidus.corvifarm.terminal;
public abstract class WidgetAbstract implements TerminalWidget {
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	
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
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

}
