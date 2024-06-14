/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.terminal;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class WordWrapperTest {
	
	@Test
	public void testConstructString() {
		List<String> expected = new ArrayList<>();
		expected.add("This text is longer than");
		expected.add("thirty characters, at least I");
		expected.add("hope.");
		WordWrapper ww = new WordWrapper("This text is longer than thirty characters, at least I hope.", 30);
		assertEquals(expected, ww.getLines());
	}

	@Test
	public void testConstructFile() {
		List<String> expected = new ArrayList<>();
		expected.add("WordWrapper");
		expected.add("");
		expected.add("WordWrapper is a class which");
		expected.add("helps to wrap larger texts");
		expected.add("automatically.");
		
		WordWrapper ww;
		try {
			ww = new WordWrapper(Paths.get("src/test/resources/WordWrapper.txt"), 30);
			assertEquals(expected, ww.getLines());
		} catch(IOException e) {
			fail(e.getMessage());
		}
		
	}

}
