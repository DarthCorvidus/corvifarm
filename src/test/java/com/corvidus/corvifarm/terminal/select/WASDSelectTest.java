package com.corvidus.corvifarm.terminal.select;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
public class WASDSelectTest implements WASDSelectObserver {
	private WASDSelectElement lastElement;
	private int onFocus = 0;
	private int onSelect = 0;
	private int onSelectEmpty = 0;
	private int onFocusEmpty = 0;
	public WASDSelectTest() {
	}
	
	public WASDSelect createDefault() {
		WASDSelect wasd = new WASDSelect(0, 0, 23, 3, 5);
		wasd.addWASDSelectObserver(this);
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
		this.onFocus = 0;
		this.onSelect = 0;
		this.onSelectEmpty = 0;
		this.onFocusEmpty = 0;
	}
	
	@AfterEach
	public void tearDown() {
		this.onFocus = 0;
		this.onSelect = 0;
		this.onSelectEmpty = 0;
		this.onFocusEmpty = 0;
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
		Assertions.assertEquals(0, wasd.getSelectedIndex());
		Assertions.assertEquals(null, this.lastElement);
		Assertions.assertEquals(0, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(0, wasd.getSelectedIndex());
		Assertions.assertEquals(null, this.lastElement);
		Assertions.assertEquals(0, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(1, wasd.getSelectedIndex());
		Assertions.assertEquals("TST01", this.lastElement.getWASDString());
		Assertions.assertEquals(1, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(8, wasd.getSelectedIndex());
		Assertions.assertEquals("TST03", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		Assertions.assertEquals(5, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);

	}

	/**
	 * 'Bump' to the last entry, ie try to go farther than there are entries.
	 */
	@Test
	public void testDownEndBump() {
		//this.setUp();
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
		Assertions.assertEquals(8, wasd.getSelectedIndex());
		Assertions.assertEquals("TST03", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		// Bumping is not supposed to call the observer again
		Assertions.assertEquals(5, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(7, wasd.getSelectedIndex());
		Assertions.assertEquals("TST03", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		Assertions.assertEquals(6, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(0, wasd.getSelectedIndex());
		Assertions.assertEquals(null, this.lastElement);
		Assertions.assertEquals(0, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(3, wasd.getSelectedIndex());
		Assertions.assertEquals("TST03", this.lastElement.getWASDString());
		Assertions.assertEquals(1, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(6, wasd.getSelectedIndex());
		Assertions.assertEquals("TST03", this.lastElement.getWASDString());
		Assertions.assertEquals(1, this.onFocus);
		Assertions.assertEquals(1, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(0, wasd.getSelectedIndex());
		Assertions.assertEquals("TST00", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		Assertions.assertEquals(1, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
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
		Assertions.assertEquals(0, wasd.getSelectedIndex());
		Assertions.assertEquals(null, this.lastElement);
		Assertions.assertEquals(0, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}

	@Test
	public void testMoveRightPageOne() {
		System.out.println("testMoveRightOne");
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 80);
		wasd.onInput(new KeyStroke('d', true, true));
		wasd.onInput(new KeyStroke('d', true, true));
		wasd.onInput(new KeyStroke('d', true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST03   TST06 * TST09");
		tg.putString(0, 1, "  TST04   TST07   TST10");
		tg.putString(0, 2, "  TST05   TST08   TST11");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(9, wasd.getSelectedIndex());
		Assertions.assertEquals("TST09", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}
	
	/**
	 * Pages by one.
	 */
	@Test
	public void testMoveRightPageBump() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 15);
		for(int i = 0; i < 10;i++) {
			wasd.onInput(new KeyStroke('d', true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST06   TST09 * TST12");
		tg.putString(0, 1, "  TST07   TST10   TST13");
		tg.putString(0, 2, "  TST08   TST11   TST14");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(12, wasd.getSelectedIndex());
		Assertions.assertEquals("TST12", this.lastElement.getWASDString());
		Assertions.assertEquals(4, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}

	/**
	 * Move left 100 times, end up at the last column.
	 */
	@Test
	public void testMoveRightPageBumpFull() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 81);
		// Up the antes a little bit.
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('s', true, true));
		for(int i = 0; i < 80;i++) {
			wasd.onInput(new KeyStroke('d', true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST72   TST75   TST78");
		tg.putString(0, 1, "  TST73   TST76   TST79");
		tg.putString(0, 2, "  TST74   TST77 * TST80");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(80, wasd.getSelectedIndex());
		Assertions.assertEquals("TST80", this.lastElement.getWASDString());
		Assertions.assertEquals(28, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);

	}
	
	public void testMoveRightPageBumpPartly() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 80);
		// Up the antes a little bit.
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('s', true, true));
		for(int i = 0; i < 80;i++) {
			wasd.onInput(new KeyStroke('d', true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST72   TST75   TST78");
		tg.putString(0, 1, "  TST73   TST76   TST79");
		tg.putString(0, 2, "  TST74   TST77 *      ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(80, wasd.getSelectedIndex());
		Assertions.assertEquals(null, this.lastElement);
		Assertions.assertEquals(27, this.onFocus);
		Assertions.assertEquals(1, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);

	}

	public void testMoveLeftPageBumpPartly() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 80);
		// Up the antes a little bit.
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('s', true, true));
		for(int i = 0; i < 80;i++) {
			wasd.onInput(new KeyStroke('d', true, true));
		}
		for(int i = 0; i < 80;i++) {
			wasd.onInput(new KeyStroke('a', true, true));
		}

		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01   TST04   TST07");
		tg.putString(0, 2, "* TST02   TST05   TST08");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(2, wasd.getSelectedIndex());
		Assertions.assertEquals("TST02", this.lastElement.getWASDString());
		Assertions.assertEquals(56, this.onFocus);
		Assertions.assertEquals(1, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}
	
	public void testMoveDownPageOne() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 12);
		for(int i = 0; i < 8;i++) {
			wasd.onInput(new KeyStroke('s', true, true));
		}
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST03   TST06 * TST09");
		tg.putString(0, 1, "  TST04   TST07   TST10");
		tg.putString(0, 2, "  TST05   TST08   TST11");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(9, wasd.getSelectedIndex());
		Assertions.assertEquals("TST09", this.lastElement.getWASDString());
		Assertions.assertEquals(8, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}

	public void testMoveUpPageOne() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 12);
		for(int i = 0; i < 8;i++) {
			wasd.onInput(new KeyStroke('s', true, true));
		}
		wasd.onInput(new KeyStroke('a', true, true));
		wasd.onInput(new KeyStroke('a', true, true));
		wasd.onInput(new KeyStroke('w', true, true));
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01   TST04   TST07");
		tg.putString(0, 2, "* TST02   TST05   TST08");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(2, wasd.getSelectedIndex());
		Assertions.assertEquals("TST02", this.lastElement.getWASDString());
		Assertions.assertEquals(11, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}
	
	@Test
	public void testOnSelect() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 12);
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('d', true, true));
		KeyStroke ks = new KeyStroke(KeyType.Enter, false, false);
		wasd.onInput(ks);
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01 * TST04   TST07");
		tg.putString(0, 2, "  TST02   TST05   TST08");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(4, wasd.getSelectedIndex());
		Assertions.assertEquals("TST04", this.lastElement.getWASDString());
		Assertions.assertEquals(2, this.onFocus);
		Assertions.assertEquals(0, this.onFocusEmpty);
		Assertions.assertEquals(1, this.onSelect);
		Assertions.assertEquals(0, this.onSelectEmpty);
	}

	@Test
	public void testOnSelectEmpty() {
		WASDSelect wasd = this.createDefault();
		this.fill(wasd, 8);
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('s', true, true));
		wasd.onInput(new KeyStroke('d', true, true));
		wasd.onInput(new KeyStroke('d', true, true));
		KeyStroke ks = new KeyStroke(KeyType.Enter, false, false);
		wasd.onInput(ks);
		TextImage ti = new BasicTextImage(23, 3);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "  TST00   TST03   TST06");
		tg.putString(0, 1, "  TST01   TST04   TST07");
		tg.putString(0, 2, "  TST02   TST05 *      ");
		Assertions.assertEquals(ti.toString(), wasd.getTextImage().toString());
		Assertions.assertEquals(8, wasd.getSelectedIndex());
		Assertions.assertEquals("TST05", this.lastElement.getWASDString());
		Assertions.assertEquals(3, this.onFocus);
		Assertions.assertEquals(1, this.onFocusEmpty);
		Assertions.assertEquals(0, this.onSelect);
		Assertions.assertEquals(1, this.onSelectEmpty);
	}

	@Override
	public void onFocus(WASDSelect wasdSelect, WASDSelectElement element) {
		this.lastElement = element;
		this.onFocus++;
	}

	@Override
	public void onSelect(WASDSelect wasdSelect, WASDSelectElement element) {
		this.lastElement = element;
		this.onSelect++;
	}

	@Override
	public void onFocusEmpty(WASDSelect wasdSelect) {
		this.onFocusEmpty++;
	}

	@Override
	public void onSelectEmpty(WASDSelect wasdSelect) {
		this.onSelectEmpty++;
	}
}