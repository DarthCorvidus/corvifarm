package com.corvidus.corvifarm.terminal;
public class VT100 {
	static public void clearScreen() {
		System.out.print((char)27+"[2J");
	}
	
	static public void cursorHome() {
		System.out.print((char)27+"[H");
	}
	
	static public void cursorUp(int lines) {
		System.out.print((char)27+"["+lines+"A");
	}
	
	static public void cursorDown(int lines) {
		System.out.print((char)27+"["+lines+"B");
	}

	static public void cursorRight(int columns) {
		System.out.print((char)27+"["+columns+"C");
	}

	static public void cursorLeft(int columns) {
		System.out.print((char)27+"["+columns+"D");
	}

	static public void cursorPos(int col, int row) {
		//System.out.print("["+col+";"+row+"H");
		System.out.print((char)27+"["+row+";"+col+"H");
	}
}
