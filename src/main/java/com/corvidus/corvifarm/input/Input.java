package com.corvidus.corvifarm.input;
import java.io.IOException;
import java.util.ArrayList;
public class Input {
	private ArrayList<InputObserver> inputObservers = new ArrayList<InputObserver>();
	public Input() {
		
	}
	public void addInputObserver(InputObserver inputObserver) {
		this.inputObservers.add(inputObserver);
	}
	
	public void run() {
		int read = 0;
		try {
			read = RawConsoleInput.read(false);
			if(read<0) {
				return;
			}
		} catch (IOException e) {
			System.exit(0);
		}
		for(InputObserver inputObserver : this.inputObservers) {
			inputObserver.onInput(this, (char)read);
		}
	}
}
