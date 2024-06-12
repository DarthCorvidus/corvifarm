package com.corvidus.corvifarm.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
	
	public PlayerTest() {
	}
	
	/**
	 * Test of fromScratch method, of class Player.
	 */
	@Test
	public void testFromScratch() {
		System.out.println("Player.fromScratch");
		Player result = Player.fromScratch();
	}

	/**
	 * Test of getGold method, of class Player.
	 */
	@Test
	public void testGetGold() {
		System.out.println("getGold");
		Player instance = Player.fromScratch();
		int expResult = 500;
		int result = instance.getGold();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testAddGold() {
		System.out.println("addGold");
		Player instance = Player.fromScratch();
		int expResult = 600;
		instance.addGold(100);
		int result = instance.getGold();
		assertEquals(expResult, result);
	}

	@Test
	public void testSubGold() {
		System.out.println("subGold");
		Player instance = Player.fromScratch();
		int expResult = 400;
		try {
			instance.subGold(100);	
		} catch (InvalidActionException e) {
			
		}
		int result = instance.getGold();
		assertEquals(expResult, result);
	}

	@Test
	public void testSubGoldBroke() {
		System.out.println("subGoldBroke");
		Player instance = Player.fromScratch();
		assertThrows(InvalidActionException.class, () -> instance.subGold(600));
	}
	
	@Test
	public void testGetEnergy() {
		Player instance = Player.fromScratch();
		int expResult = 270;
		int result = instance.getEnergy();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSubEnergy() {
		Player instance = Player.fromScratch();
		try {
			instance.subEnergy(10);
		} catch(InvalidActionException e) {
			
		}
		int expResult = 260;
		int result = instance.getEnergy();
		
		assertEquals(expResult, result);
	}
	
	@Test
	public void testAddEnergy() {
		Player instance = Player.fromScratch();
		try {
			instance.subEnergy(20);
		} catch(InvalidActionException e) {
			
		}
		instance.addEnergy(10);
		int expResult = 260;
		int result = instance.getEnergy();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testAddEnergyLimited() {
		Player instance = Player.fromScratch();
		instance.addEnergy(10);
		int expResult = 270;
		int result = instance.getEnergy();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSubEnergyWeak() {
		Player instance = Player.fromScratch();
		assertThrows(InvalidActionException.class, () -> instance.subEnergy(280));
	}
	
	@Test
	public void testGetImage() {
		Player instance = Player.fromScratch();
		TextImage ti = new BasicTextImage(20, 12);
		TextGraphics tg = ti.newTextGraphics();
		tg.putString(0, 0, "Gold:   500");
		tg.putString(0, 1, "Energy: 270");
		tg.putString(0, 2, "1*Hoe");
		tg.putString(0, 3, "2 Watercan");
		tg.putString(0, 4, "3 Axe");
		tg.putString(0, 5, "4 Pickaxe");
		tg.putString(0, 6, "5 Scythe");
		tg.putString(0, 7, "6");
		tg.putString(0, 8, "7");
		tg.putString(0, 9, "8");
		tg.putString(0, 10, "9");
		tg.putString(0, 11, "0");
		assertEquals(ti.toString(), instance.getTextImage().toString());
	}
}
