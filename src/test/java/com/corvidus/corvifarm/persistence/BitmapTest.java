/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class BitmapTest {
	
	public BitmapTest() {
	}

	/**
	 * Test of setBoolean method, of class Bitmap.
	 */
	@Test
	public void testGetDefaultBoolean() {
		Bitmap bitmap = new Bitmap(4);
		assertEquals(false, bitmap.getBoolean(0));
		assertEquals(false, bitmap.getBoolean(1));
		assertEquals(false, bitmap.getBoolean(2));
		assertEquals(false, bitmap.getBoolean(3));
		assertEquals(false, bitmap.getBoolean(4));
		assertEquals(false, bitmap.getBoolean(5));
		assertEquals(false, bitmap.getBoolean(6));
		assertEquals(false, bitmap.getBoolean(7));
	}
	
	@Test
	public void testGetSetBoolean() {
		Bitmap bitmap = new Bitmap(4);
		assertEquals(false, bitmap.getBoolean(0));
		assertEquals(false, bitmap.getBoolean(1));
		assertEquals(false, bitmap.getBoolean(2));
		assertEquals(false, bitmap.getBoolean(3));
		assertEquals(false, bitmap.getBoolean(4));
		assertEquals(false, bitmap.getBoolean(5));
		assertEquals(false, bitmap.getBoolean(6));
		assertEquals(false, bitmap.getBoolean(7));
		bitmap.setBoolean(0, true);
		assertEquals(true, bitmap.getBoolean(0));
		assertEquals(false, bitmap.getBoolean(1));
		assertEquals(false, bitmap.getBoolean(2));
		assertEquals(false, bitmap.getBoolean(3));
		assertEquals(false, bitmap.getBoolean(4));
		assertEquals(false, bitmap.getBoolean(5));
		assertEquals(false, bitmap.getBoolean(6));
		assertEquals(false, bitmap.getBoolean(7));
		bitmap.setBoolean(1, true);
		assertEquals(true, bitmap.getBoolean(1));
		bitmap.setBoolean(2, true);
		assertEquals(true, bitmap.getBoolean(2));
		bitmap.setBoolean(2, false);
		assertEquals(false, bitmap.getBoolean(2));
	}
}
