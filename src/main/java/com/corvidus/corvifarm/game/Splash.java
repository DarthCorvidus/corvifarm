package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.terminal.TerminalWidget;
import com.corvidus.corvifarm.terminal.UserInterface;
import com.corvidus.corvifarm.terminal.WidgetInputObserver;
import com.corvidus.corvifarm.terminal.WidgetTextLines;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.File;
import java.io.IOException;

public class Splash implements WidgetInputObserver {
	private UserInterface userInterface;
	private boolean active = true;
	private Game game;
	private WidgetTextLines text;
	public Splash() {
		this.userInterface = new UserInterface();
		this.text = new WidgetTextLines(20, 10, 40, 3);
		this.text.setLine(0, "l load game");
		this.text.setLine(1, "n new game");
		this.text.setLine(2, "x quit game");
		this.userInterface.addWidget(text);
		this.userInterface.refresh();
	}
	
	public void run() {
		this.userInterface.addInputObserver(this);
		while(this.game == null) {
			try {
				this.userInterface.run();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
		this.game.run();
	}
	
	private void load() {
		File save = new File("game.sav");
		if(save.exists() && !save.isDirectory()) {
			this.userInterface.removeInputObserver(this);
			this.userInterface.removeWidget(this.text);
			this.game = Game.fromBinary(this.userInterface);
		}
	}
	
	private void newGame() {
		this.userInterface.removeInputObserver(this);
		this.userInterface.removeWidget(this.text);
		this.userInterface.refresh();
		this.game = Game.fromScratch(this.userInterface);
	}

	@Override
	public void onInput(TerminalWidget widget, KeyStroke keyStroke) {
		if(keyStroke.getKeyType() != KeyType.Character) {
			return;
		}
		if(keyStroke.getCharacter() == 'n') {
			this.newGame();
		}
		if(keyStroke.getCharacter() == 'l') {
			this.load();
		}
		
		if(keyStroke.getCharacter() == 'x') {
			System.exit(0);
		}
	}
}
