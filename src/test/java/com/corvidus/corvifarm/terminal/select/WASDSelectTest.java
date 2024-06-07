package com.corvidus.corvifarm.terminal.select;

import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class WASDSelectTest {
	
	public WASDSelectTest() {
	}
	
	@BeforeAll
	public static void setUpClass() {
	}
	
	@AfterAll
	public static void tearDownClass() {
	}
	
	@BeforeEach
	public void setUp() {
	}
	
	@AfterEach
	public void tearDown() {
	}

	/**
	 * Test of addElement method, of class WASDSelect.
	 */
	@Test
	public void testAddElement() {
		System.out.println("addElement");
		WASDSelect instance = null;
		instance.addElement();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of onInput method, of class WASDSelect.
	 */
	@Test
	public void testOnInput() {
		System.out.println("onInput");
		KeyStroke keyStroke = null;
		WASDSelect instance = null;
		instance.onInput(keyStroke);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}