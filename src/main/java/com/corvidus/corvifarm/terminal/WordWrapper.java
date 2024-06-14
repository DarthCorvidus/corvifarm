package com.corvidus.corvifarm.terminal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WordWrapper {
	private int width = 0;
	private List<String> lines = new ArrayList<>();
	public WordWrapper(String string, int width) {
		this.width = width;
		String[] split = string.split("\n");
		for(String line: split) {
			this.wrapLine(line);
		}
	}
	
	public WordWrapper(Path path, int width) throws IOException {
		this.width = width;
		List<String> lines;
        try {
            lines = Files.readAllLines(path);
			this.wrapText(lines);
        } catch (IOException e) {
            throw e;
        }
	}
	
	private void wrapText(List<String> lines) {
		for(String line : lines) {
			this.wrapLine(line.trim());
		}
	}
	
	private void wrapLine(String line) {
		String[] split = line.split(" ");
		String tmp = "";
		for(String word : split) {
			if(tmp.length() + word.length()<this.width) {
				tmp += word+" ";
			} else {
				tmp = tmp.trim();
				this.lines.add(tmp);
				tmp = word+" ";
			}
		}
		this.lines.add(tmp.trim());
	}
	
	public List<String> getLines() {
		return this.lines;
	}
}
