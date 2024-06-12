package com.corvidus.corvifarm.game;

import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.tools.Hoe;
import com.corvidus.corvifarm.items.tools.Pickaxe;
import com.corvidus.corvifarm.items.tools.Scythe;
import com.corvidus.corvifarm.items.tools.Watercan;
import com.corvidus.corvifarm.items.wood.Wood;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class InventoryTest {
	public InventoryTest() {
	}

	@Test
	public void testEmpty() {
		Inventory iv = new Inventory();
		TextImage ti = new BasicTextImage(20, 10);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "1*");
		tg.putString(0, 1, "2");
		tg.putString(0, 2, "3");
		tg.putString(0, 3, "4");
		tg.putString(0, 4, "5");
		tg.putString(0, 5, "6");
		tg.putString(0, 6, "7");
		tg.putString(0, 7, "8");
		tg.putString(0, 8, "9");
		tg.putString(0, 9, "0");
		assertEquals(ti.toString(), iv.getTextImage().toString());
	}
	
	@Test
	public void testSelect() {
		Inventory iv = new Inventory();
		iv.onInput(new KeyStroke('2', false, false));
		TextImage ti = new BasicTextImage(20, 10);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "1");
		tg.putString(0, 1, "2*");
		tg.putString(0, 2, "3");
		tg.putString(0, 3, "4");
		tg.putString(0, 4, "5");
		tg.putString(0, 5, "6");
		tg.putString(0, 6, "7");
		tg.putString(0, 7, "8");
		tg.putString(0, 8, "9");
		tg.putString(0, 9, "0");
		assertEquals(ti.toString(), iv.getTextImage().toString());
	}
	
	@Test
	public void testSelectLast() {
		Inventory iv = new Inventory();
		iv.onInput(new KeyStroke('0', false, false));
		TextImage ti = new BasicTextImage(20, 10);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "1");
		tg.putString(0, 1, "2");
		tg.putString(0, 2, "3");
		tg.putString(0, 3, "4");
		tg.putString(0, 4, "5");
		tg.putString(0, 5, "6");
		tg.putString(0, 6, "7");
		tg.putString(0, 7, "8");
		tg.putString(0, 8, "9");
		tg.putString(0, 9, "0*");
		assertEquals(ti.toString(), iv.getTextImage().toString());
	}

	@Test
	public void testAddItem() {
		Inventory iv = new Inventory();
		iv.addItem(new Hoe());
		iv.addItem(new Watercan());
		iv.addItem(new Axe());
		iv.addItem(new Pickaxe());
		iv.addItem(new Scythe());
		Wood wood = new Wood();
		wood.setAmount(500);
		iv.addItem(wood);
		TextImage ti = new BasicTextImage(20, 10);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "1*Hoe");
		tg.putString(0, 1, "2 Watercan");
		tg.putString(0, 2, "3 Axe");
		tg.putString(0, 3, "4 Pickaxe");
		tg.putString(0, 4, "5 Scythe");
		tg.putString(0, 5, "6 500×Wood");
		tg.putString(0, 6, "7");
		tg.putString(0, 7, "8");
		tg.putString(0, 8, "9");
		tg.putString(0, 9, "0");
		assertEquals(ti.toString(), iv.getTextImage().toString());
	}

}
