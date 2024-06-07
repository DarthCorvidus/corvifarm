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
	return wasd;
	}
	
	public void fill(WASDSelect wasd, int amount) {
		for(int i = 0;i<amount;i++) {
			WASDSelectString element = new WASDSelectString(String.format("TST%02d", i));
			wasd.addElement(element);
		}
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
		this.fill(wasd, 9);
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01   TST04   TST07");
		tg.putString(0, 2, "  TST02   TST05   TST08");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}
	
	@Test
	public void testConstructSparse() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}
	
	/**
	 * Go down one using 's'.
	 */
	@Test
	public void testDown() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('s'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03        ");
		tg.putString(0, 1, "* TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}
	
	/**
	 * Go down to the last entry.
	 */
	@Test
	public void testDownEnd() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		for(int i = 0; i<8;i++) {
			wasd.onInput(new KeyStroke(new Character('s'), true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02         *      ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	/**
	 * 'Bump' to the last entry, ie try to go farther than there are entries.
	 */
	@Test
	public void testDownEndBump() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		for(int i = 0; i<15;i++) {
			wasd.onInput(new KeyStroke(new Character('s'), true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02         *      ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	/**
	 * Move up.
	 */
	@Test
	public void testUp() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		for(int i = 0; i<15;i++) {
			wasd.onInput(new KeyStroke(new Character('s'), true, true));
		}
		wasd.onInput(new KeyStroke(new Character('w'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03        ");
		tg.putString(0, 1, "  TST01         *      ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	/**
	 * Move up from 0, ie bump to the upperleftmost position.
	 */
	@Test
	public void testUpBump() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('w'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}
	
	@Test
	public void testMoveRight() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00 * TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	@Test
	public void testMoveRightBump() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03 *      ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	@Test
	public void testMoveLeft() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		wasd.onInput(new KeyStroke(new Character('d'), true, true));
		wasd.onInput(new KeyStroke(new Character('a'), true, true));
		wasd.onInput(new KeyStroke(new Character('a'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

	@Test
	public void testMoveLeftBump() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 4);
		wasd.onInput(new KeyStroke(new Character('a'), true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "* TST00   TST03        ");
		tg.putString(0, 1, "  TST01                ");
		tg.putString(0, 2, "  TST02                ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
	}

}