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
public class FarmTileTest {
	public FarmTileTest() {
		
	}

	/**
	 * Test of getName method, of class FarmTile.
	 */	
	@Test
	public void testGetName() {
		Tile tile = new FarmTile();
		assertEquals("Untilled", tile.getName());
	}
	
	/**
	 * Test of till method, of class FarmTile.
	 */
	@Test
	public void testTill() throws Exception {
		FarmTile tile = new FarmTile();
		tile.till();
		assertEquals("Unwatered", tile.getName());
	}

	@Test
	public void testTillTill() throws Exception {
		FarmTile tile = new FarmTile();
		tile.till();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.till());
		assertEquals("Already tilled.", ex.getMessage());
	}

	/**
	 * Test of water method, of class FarmTile.
	 */
	@Test
	public void testWater() throws Exception {
		FarmTile tile = new FarmTile();
		tile.till();
		tile.water();
		assertEquals("Watered", tile.getName());
	}

	@Test
	public void testWaterUntilled() throws Exception {
		FarmTile tile = new FarmTile();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.water());
		assertEquals("Cannot water untilled tile.", ex.getMessage());
	}

	@Test
	public void testWaterWater() throws Exception {
		FarmTile tile = new FarmTile();
		tile.till();
		tile.water();
		Exception ex = assertThrows(InvalidActionException.class, () -> tile.water());
		assertEquals("Already watered.", ex.getMessage());
	}

	/**
	 * Test of passDay method, of class FarmTile.
	 */
	@Test
	public void testPassDay() {
	}
}
