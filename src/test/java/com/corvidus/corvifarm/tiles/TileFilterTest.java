/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Weeds;
import com.corvidus.corvifarm.items.interactive.Bed;
import com.corvidus.corvifarm.items.wood.Tree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hm
 */
public class TileFilterTest {
	
	public TileFilterTest() {
	}

	/**
	 * Test of includeInstance method, of class TileFilter.
	 */
	@Test
	public void testIncludeTileClass() {
		TileFilter filter = new TileFilter();
		filter.includeTileClass(FarmTile.class);
		assertSame(true, filter.test(new FarmTile()));
		assertSame(false, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}

	@Test
	public void testIncludeTileInterface() {
		TileFilter filter = new TileFilter();
		filter.includeTileClass(Tillable.class);
		assertSame(true, filter.test(new FarmTile()));
		assertSame(true, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}
	
	@Test
	public void testExcludeTileClass() {
		TileFilter filter = new TileFilter();
		filter.excludeTileClass(FarmTile.class);
		assertSame(false, filter.test(new FarmTile()));
		assertSame(true, filter.test(new GroundTile()));
		assertSame(true, filter.test(new ForestTile()));
	}
	
	@Test
	public void testExcludeTileInterface() {
		TileFilter filter = new TileFilter();
		filter.excludeTileClass(Tillable.class);
		assertSame(false, filter.test(new FarmTile()));
		assertSame(false, filter.test(new GroundTile()));
		assertSame(true, filter.test(new ForestTile()));
	}

	@Test
	public void testIncludeOverlay() {
		TileFilter filter = new TileFilter();
		filter.includeOverlayClass(Tree.class);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(ItemFactory.createRandomTree());
		
		assertSame(false, filter.test(empty));
		assertSame(false, filter.test(weed));
		assertSame(true, filter.test(tree));
	}
	
	@Test
	public void testExcludeOverlay() {
		TileFilter filter = new TileFilter();
		filter.excludeOverlayClass(Tree.class);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(ItemFactory.createRandomTree());
		
		assertSame(false, filter.test(empty));
		assertSame(true, filter.test(weed));
		assertSame(false, filter.test(tree));
	}
	
	@Test
	public void testGetTilled() {
		TileFilter filter = new TileFilter();
		filter.tilled(true);
		FarmTile ft1 = new FarmTile();
		FarmTile ft2 = new FarmTile();
		try {
			ft2.till();
		} catch (InvalidActionException e) {
			
		}
		assertSame(false, filter.test(ft1));
		assertSame(true, filter.test(ft2));
		assertSame(false, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}
	
	@Test
	public void testGetUntilled() {
		TileFilter filter = new TileFilter();
		filter.tilled(false);
		FarmTile ft1 = new FarmTile();
		FarmTile ft2 = new FarmTile();
		try {
			ft2.till();
		} catch (InvalidActionException e) {
			
		}
		assertSame(true, filter.test(ft1));
		assertSame(false, filter.test(ft2));
		assertSame(true, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}

	@Test
	public void testGetWatered() {
		TileFilter filter = new TileFilter();
		filter.tilled(true);
		FarmTile ft1 = new FarmTile();
		FarmTile ft2 = new FarmTile();
		try {
			ft2.till();
			ft2.water();
		} catch (InvalidActionException e) {
			
		}
		assertSame(false, filter.test(ft1));
		assertSame(true, filter.test(ft2));
		assertSame(false, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}
	
	@Test
	public void testGetUnwatered() {
		TileFilter filter = new TileFilter();
		filter.tilled(false);
		FarmTile ft1 = new FarmTile();
		FarmTile ft2 = new FarmTile();
		try {
			ft2.till();
			ft2.water();
		} catch (InvalidActionException e) {
			
		}
		assertSame(true, filter.test(ft1));
		assertSame(false, filter.test(ft2));
		assertSame(true, filter.test(new GroundTile()));
		assertSame(false, filter.test(new ForestTile()));
	}

	@Test
	public void testhasOverlay() {
		TileFilter filter = new TileFilter();
		filter.hasOverlay(true);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(ItemFactory.createRandomTree());
		
		assertSame(false, filter.test(empty));
		assertSame(true, filter.test(weed));
		assertSame(true, filter.test(tree));
	}

	@Test
	public void testEmptyOverlay() {
		TileFilter filter = new TileFilter();
		filter.hasOverlay(false);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(ItemFactory.createRandomTree());
		
		assertSame(true, filter.test(empty));
		assertSame(false, filter.test(weed));
		assertSame(false, filter.test(tree));
	}

}
