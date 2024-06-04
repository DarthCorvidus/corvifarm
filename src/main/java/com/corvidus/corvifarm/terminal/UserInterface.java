package com.corvidus.corvifarm.terminal;

import com.googlecode.lanterna.graphics.TextGraphics;
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
	public UserInterface() {
		DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
		try {
			this.screen = defaultTerminalFactory.createScreen();
			this.screen.startScreen();
			this.tg = this.screen.newTextGraphics();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run() {
		while(true) {
			
		}
	}
}
