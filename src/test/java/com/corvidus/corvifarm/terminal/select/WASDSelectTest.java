package com.corvidus.corvifarm.terminal.select;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
	
	public WASDSelect createDefault() {
		WASDSelect wasd = new WASDSelect(0, 0, 23, 3, 5);
		for(int i = 0;i<80;i++) {
			WASDSelectString element = new WASDSelectString(String.format("TST%02d", i));
			wasd.addElement(element);
		}
	return wasd;
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
	 * Test constructor, get empty image.
	 */
	@Test
	public void testConstructEmpty() {
		WASDSelect wasd = new WASDSelect(0, 0, 23, 3, 5);
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "*");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	/**
	 * Test of onInput method, of class WASDSelect.
	 */
	@Test
	public void testConstructFilled() {
		WASDSelect wasd = this.createDefault();
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01   TST04   TST07");
		tg.putString(0, 2, "  TST02   TST05   TST08");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}
}