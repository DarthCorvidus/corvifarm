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
		byte[] expected = {0, 0, 0, 0};
		Bitmap bitmap = new Bitmap(4);
		assertArrayEquals(expected, bitmap.getBytes());
	}
	
	@Test
	public void testSetBoolean() {
		byte[] expected = {(byte)255, (byte)153, 15, 3};
		Bitmap bitmap = new Bitmap(4);
		bitmap.setBoolean(0, true);
		bitmap.setBoolean(1, true);
		bitmap.setBoolean(2, true);
		bitmap.setBoolean(3, true);
		bitmap.setBoolean(4, true);
		bitmap.setBoolean(5, true);
		bitmap.setBoolean(6, true);
		bitmap.setBoolean(7, true);
		
		bitmap.setBoolean(8, true);
		bitmap.setBoolean(11, true);
		bitmap.setBoolean(12, true);
		bitmap.setBoolean(15, true);
		
		bitmap.setBoolean(20, true);
		bitmap.setBoolean(21, true);
		bitmap.setBoolean(22, true);
		bitmap.setBoolean(23, true);
		
		bitmap.setBoolean(30, true);
		bitmap.setBoolean(31, true);
		assertArrayEquals(expected, bitmap.getBytes());

	}
	
	@Test
	public void testGetBoolean() {
		boolean[] expected = {true, true, true, true, true, true, true, true, true, false, false, true, true, false, false, true};
		Bitmap bitmap = new Bitmap(4);
		bitmap.setBoolean(0, true);
		bitmap.setBoolean(1, true);
		bitmap.setBoolean(2, true);
		bitmap.setBoolean(3, true);
		bitmap.setBoolean(4, true);
		bitmap.setBoolean(5, true);
		bitmap.setBoolean(6, true);
		bitmap.setBoolean(7, true);
		
		bitmap.setBoolean(8, true);
		bitmap.setBoolean(11, true);
		bitmap.setBoolean(12, true);
		bitmap.setBoolean(15, true);
		
		bitmap.setBoolean(20, true);
		bitmap.setBoolean(21, true);
		bitmap.setBoolean(22, true);
		bitmap.setBoolean(23, true);
		
		bitmap.setBoolean(30, true);
		bitmap.setBoolean(31, true);
		
		for(int i = 0; i<expected.length;i++) {
			assertEquals(expected[i], bitmap.getBoolean(i));
		}
	}
}
