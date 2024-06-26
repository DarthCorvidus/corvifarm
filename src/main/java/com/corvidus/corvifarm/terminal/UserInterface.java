package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

/**
 * Writing my own user interface here, as opposed to using the existing one.
 * This is not just a case of "Not Invented Here", but of having different needs:
 * - different aesthetics which look less like a traditional windows system
 * - focus management in a way that several widgets should either react in
 *   parallel to key presses (such as the character, inventory and room) or one
 *   or two widgets should get privileged access (for instance a store or
 *   inventory management)
 * - 
 * 
 * @author hm
 */
public class UserInterface {
	private Screen screen;
	private TextGraphics tg;
	private WidgetPane pane;
	public UserInterface() {
		/**
		 * For now, I plan to limit the game to 80×24.
		 */
		this.pane = new WidgetPane(0, 0, 80, 24);
		DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
		try {
			this.screen = defaultTerminalFactory.createScreen();
			this.screen.startScreen();
			this.tg = this.screen.newTextGraphics();
			this.screen.setCursorPosition(null);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void setFocus(TerminalWidget widget) {
		this.pane.setFocus(widget);
	}
	
	public void addWidget(TerminalWidget widget) {
		this.pane.addWidget(widget);
	}
	
	public void removeWidget(TerminalWidget widget) {
		this.pane.removeWidget(widget);
	}
	
	public void addInputObserver(WidgetInputObserver widgetInputObserver) {
		this.pane.addInputObserver(widgetInputObserver);
	}
	
	public void removeInputObserver(WidgetInputObserver widgetInputObserver) {
		this.pane.removeInputObserver(widgetInputObserver);
	}

	public void refresh() {
		try {
			this.tg.drawImage(new TerminalPosition(0, 0), this.pane.getTextImage());
			this.screen.refresh();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run() {
		try {
			KeyStroke keyStroke = this.screen.pollInput();
			if(keyStroke == null) {
				return;
			}
			this.pane.onInput(keyStroke);
			this.refresh();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
