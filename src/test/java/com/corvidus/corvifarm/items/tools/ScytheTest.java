package com.corvidus.corvifarm.items.tools;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.game.Player;
import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Crops;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.Tile;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ScytheTest {
	
	public ScytheTest() {
	}

	/**
	 * Test of getBaseDemand method, of class Scythe.
	 */
	@Test
	public void testGetBaseDemand() {
		Scythe scythe = new Scythe();
		assertEquals(0, scythe.getBaseDemand());
	}

	/**
	 * Test of getName method, of class Scythe.
	 */
	@Test
	public void testGetName() {
		Scythe scythe = new Scythe();
		assertEquals("Scythe", scythe.getName());
	}

	/**
	 * Test of apply method, of class Scythe.
	 */
	@Test
	public void testApply() throws Exception {
		Player player = Player.fromScratch();
		Scythe scythe = new Scythe();
		Crop crop = Crops.create(Crops.WHEAT);
		crop.setState(Crop.GROWN);
		Tile tile = new FarmTile();
		tile.setOverlay(crop);
		scythe.apply(player, tile);
		player.getInventory().onInput(new KeyStroke('7', false, false));
		Crop harvested = (Crop)player.getInventory().getCurrentItem();
		assertEquals("Wheat", harvested.getName());
		assertEquals(Crop.PRODUCE, harvested.getState());
	}

	@Test
	public void testApplyWrongItem() throws Exception {
		Player player = Player.fromScratch();
		player.getInventory().onInput(new KeyStroke('5', false, false));
		Scythe scythe = (Scythe)player.getInventory().getCurrentItem();
		Tile tile = new FarmTile();
		tile.setOverlay(new Wood());
		InvalidActionException e = assertThrows(InvalidActionException.class, () -> scythe.apply(player, tile));
		assertEquals("Cannot be scythed.", e.getMessage());
	}

	@Test
	public void testApplyNotReady() throws Exception {
		Player player = Player.fromScratch();
		player.getInventory().onInput(new KeyStroke('5', false, false));
		Scythe scythe = (Scythe)player.getInventory().getCurrentItem();
		Tile tile = new FarmTile();
		tile.setOverlay(Crops.createSeed(Crops.WHEAT));
		InvalidActionException e = assertThrows(InvalidActionException.class, () -> scythe.apply(player, tile));
		assertEquals("Crop not ready.", e.getMessage());
	}
}
