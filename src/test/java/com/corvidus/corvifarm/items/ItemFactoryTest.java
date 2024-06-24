package com.corvidus.corvifarm.items;

import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.wood.Tree;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.tiles.TileFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
public class ItemFactoryTest {
	@Test
	public void testGetPrototype() {
		Axe axe = (Axe)ItemFactory.getPrototype(ItemFactory.AXE);
	}

	public void testGetPrototypeAmount() {
		Wood wood = (Wood)ItemFactory.getPrototype(ItemFactory.WOOD, 25);
		assertEquals(25, wood.getAmount());
	}

	/*
	 * Test that two prototypes are unique, ie changing wood1 here does not
	 * affect wood2.
	 */
	@Test
	public void testGetPrototypeUnique() {
		Wood wood1 = (Wood)ItemFactory.getPrototype(ItemFactory.WOOD);
		wood1.setAmount(15);
		Wood wood2 = (Wood)ItemFactory.getPrototype(ItemFactory.WOOD);
		assertNotEquals(wood1.getAmount(), wood2.getAmount());
	}

	@Test
	public void testGetPrototypeCrop() {
		Crop crop = (Crop)ItemFactory.getPrototype(ItemFactory.WHEAT);
		assertEquals("Wheat Seeds", crop.getName());
		assertEquals(Crop.SEED, crop.getState());
	}

	@Test
	public void testGetPrototypeTree() {
		Tree tree = (Tree)ItemFactory.getPrototype(ItemFactory.OAK);
		assertEquals("Oak Seed", tree.getName());
		assertEquals(1, tree.getAmount());
	}
	
	@Test 
	public void testGetByClass() {
		List<Item> items = ItemFactory.getByClass(Tree.class);
		for(Item item : items) {
			Tree tree = (Tree)item;
		}
		assertEquals(false, items.isEmpty());
	}

}
