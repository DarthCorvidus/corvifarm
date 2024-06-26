package com.corvidus.corvifarm.persistence;

import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.interactive.ShippingBin;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.Tile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PersistenceItemTest {
	
	public PersistenceItemTest() {
	}

	/**
	 * Test of fromItem method, of class PersistenceItem.
	 */
	@Test
	public void testToBinary() {
		byte[] expected = {0, 0, 82, 40, 0, 0, 0, (byte)207, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3};
		Item item = ItemFactory.getPrototype(ItemFactory.WOOD, 15);
		PersistenceItem entry = PersistenceItem.fromItem(21032, item);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			entry.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
	}

	@Test
	public void testToBinaryWheat() {
		byte[] expected = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3};
		Crop crop = (Crop)ItemFactory.getPrototype(ItemFactory.POTATO, 15);
		for(int i = 0; i < 10; i++) {
			crop.grow();
		}
		assertEquals("Grown Potato", crop.getName());
		assertEquals(5, crop.getAge());
		
		PersistenceItem entry = PersistenceItem.fromItem(21032, crop);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			entry.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
	}
	
	@Test
	public void testWheatFromBinary() {
		byte[] input = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3};
		ByteArrayInputStream bi = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bi);
		try {
			PersistenceItem entry = PersistenceItem.fromBinary(dis);
			Crop crop = (Crop)entry.createItem();
			assertEquals("Grown Potato", crop.getName());
			assertEquals(5, crop.getAge());
			assertEquals(21032, entry.getPrimaryKey());
			assertEquals(false, entry.hasForeignKeyTile());
			assertEquals(false, entry.hasForeignKeyItem());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testWheatToBinaryWithTile() {
		Tile tile = new FarmTile();
		PersistenceTile pt = PersistenceTile.fromTile(1956, tile, 15);
		byte[] expected = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 7, (byte)164, 0, 0, 0, 0, 2};
		Crop crop = (Crop)ItemFactory.getPrototype(ItemFactory.POTATO, 15);
		for(int i = 0; i < 10; i++) {
			crop.grow();
		}
		assertEquals("Grown Potato", crop.getName());
		assertEquals(5, crop.getAge());
		
		PersistenceItem entry = PersistenceItem.fromItem(21032, crop, pt);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			entry.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
	}

	@Test
	public void testWheatFromBinaryWithTile() {
		byte[] input = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 7, (byte)164, 0, 0, 0, 0, 2};
		ByteArrayInputStream bi = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bi);
		try {
			PersistenceItem entry = PersistenceItem.fromBinary(dis);
			Crop crop = (Crop)entry.createItem();
			assertEquals("Grown Potato", crop.getName());
			assertEquals(5, crop.getAge());
			assertEquals(21032, entry.getPrimaryKey());
			assertEquals(true, entry.hasForeignKeyTile());
			assertEquals(1956, entry.getForeignKeyTile());
			assertEquals(false, entry.hasForeignKeyItem());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testWheatToBinaryWithItem() {
		Item item = new ShippingBin();
		PersistenceItem pi = PersistenceItem.fromItem(1957, item);
		byte[] expected = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 0, 0, 0, 0, 7, (byte)165, 1};
		Crop crop = (Crop)ItemFactory.getPrototype(ItemFactory.POTATO, 15);
		for(int i = 0; i < 10; i++) {
			crop.grow();
		}
		assertEquals("Grown Potato", crop.getName());
		assertEquals(5, crop.getAge());
		
		PersistenceItem entry = PersistenceItem.fromItem(21032, crop, pi);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bo);
		try {
			entry.toBinary(dos);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		byte[] bytes = bo.toByteArray();
		assertArrayEquals(expected, bytes);
	}

	
	@Test
	public void testWheatFromBinaryWithItem() {
		byte[] input = {0, 0, 82, 40, 0, 0, 0, ItemFactory.POTATO, 0, 15, Crop.GROWN, 5, 0, 0, 0, 0, 0, 0, 0, 0, 7, (byte)165, 1};
		ByteArrayInputStream bi = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bi);
		try {
			PersistenceItem entry = PersistenceItem.fromBinary(dis);
			Crop crop = (Crop)entry.createItem();
			assertEquals("Grown Potato", crop.getName());
			assertEquals(5, crop.getAge());
			assertEquals(21032, entry.getPrimaryKey());
			assertEquals(false, entry.hasForeignKeyTile());
			assertEquals(true, entry.hasForeignKeyItem());
			assertEquals(1957, entry.getForeignKeyItem());
			
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
