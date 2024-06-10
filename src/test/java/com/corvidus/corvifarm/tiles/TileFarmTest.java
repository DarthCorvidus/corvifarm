/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class TileFarmTest {
	public TileFarmTest() {
		
	}

	/**
	 * Test of getName method, of class TileFarm.
	 */	
	@Test
	public void testGetName() {
		Tile tile = new TileFarm();
		assertEquals("Untilled", tile.getName());
	}
	
	/**
	 * Test of till method, of class TileFarm.
	 */
	@Test
	public void testTill() throws Exception {
		TileFarm tile = new TileFarm();
		tile.till();
		assertEquals("Unwatered", tile.getName());
	}

	@Test
	public void testTillTill() throws Exception {
		TileFarm tile = new TileFarm();
		tile.till();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.till());
		assertEquals("Already tilled.", ex.getMessage());
	}

	/**
	 * Test of water method, of class TileFarm.
	 */
	@Test
	public void testWater() throws Exception {
		TileFarm tile = new TileFarm();
		tile.till();
		tile.water();
		assertEquals("Watered", tile.getName());
	}

	@Test
	public void testWaterUntilled() throws Exception {
		TileFarm tile = new TileFarm();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.water());
		assertEquals("Cannot water untilled tile.", ex.getMessage());
	}

	@Test
	public void testWaterWater() throws Exception {
		TileFarm tile = new TileFarm();
		tile.till();
		tile.water();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.water());
		assertEquals("Already watered.", ex.getMessage());
	}

	/**
	 * Test of passDay method, of class TileFarm.
	 */
	@Test
	public void testPassDay() {
	}
}
