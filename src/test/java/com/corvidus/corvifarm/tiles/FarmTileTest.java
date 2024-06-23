/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
		FarmTile tile = new FarmTile();
		assertEquals("Untilled", tile.getName());
		assertEquals(false, tile.isTilled());
		assertEquals(false, tile.isWatered());
	}
	
	/**
	 * Test of till method, of class FarmTile.
	 */
	@Test
	public void testTill() throws Exception {
		FarmTile tile = new FarmTile();
		tile.till();
		assertEquals("Unwatered", tile.getName());
		assertEquals(true, tile.isTilled());
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
		assertEquals(true, tile.isWatered());
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

	@Test
	public void testBinary() {
		FarmTile tile = new FarmTile();
		byte[] expected = new byte[10];
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			tile.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
		assertEquals(false, tile.isTilled());
		assertEquals(false, tile.isWatered());
		
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bi);
		try {
			FarmTile loaded = (FarmTile)Tile.fromBinary(dis);
			assertEquals(tile.getClass(), loaded.getClass());
			assertEquals(false, loaded.isTilled());
			assertEquals(false, loaded.isWatered());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	

	@Test
	public void testBinaryTilled() {
		FarmTile tile = new FarmTile();
		try {
			tile.till();
		} catch (InvalidActionException e) {
			fail(e.getMessage());
		}
		byte[] expected = new byte[10];
		expected[2] = 1;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			tile.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bi);
		try {
			FarmTile loaded = (FarmTile)Tile.fromBinary(dis);
			assertEquals(tile.getClass(), loaded.getClass());
			assertEquals(true, loaded.isTilled());
			assertEquals(false, loaded.isWatered());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test of passDay method, of class FarmTile.
	 */
	@Test
	public void testPassDay() {
	}
}
