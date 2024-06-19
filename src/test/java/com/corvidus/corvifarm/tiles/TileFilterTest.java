/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.corvidus.corvifarm.tiles;

import com.corvidus.corvifarm.game.InvalidActionException;
import com.corvidus.corvifarm.items.crops.Weeds;
import com.corvidus.corvifarm.items.interactive.Bed;
import com.corvidus.corvifarm.items.wood.Tree;
import com.corvidus.corvifarm.items.wood.Trees;
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
		assertSame(true, filter.match(new FarmTile()));
		assertSame(false, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
	}

	@Test
	public void testIncludeTileInterface() {
		TileFilter filter = new TileFilter();
		filter.includeTileClass(Tillable.class);
		assertSame(true, filter.match(new FarmTile()));
		assertSame(true, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
	}
	
	@Test
	public void testExcludeTileClass() {
		TileFilter filter = new TileFilter();
		filter.excludeTileClass(FarmTile.class);
		assertSame(false, filter.match(new FarmTile()));
		assertSame(true, filter.match(new GroundTile()));
		assertSame(true, filter.match(new ForestTile()));
	}
	
	@Test
	public void testExcludeTileInterface() {
		TileFilter filter = new TileFilter();
		filter.excludeTileClass(Tillable.class);
		assertSame(false, filter.match(new FarmTile()));
		assertSame(false, filter.match(new GroundTile()));
		assertSame(true, filter.match(new ForestTile()));
	}

	@Test
	public void testIncludeOverlay() {
		TileFilter filter = new TileFilter();
		filter.includeOverlayClass(Tree.class);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(Trees.createRandom());
		
		assertSame(false, filter.match(empty));
		assertSame(false, filter.match(weed));
		assertSame(true, filter.match(tree));
	}
	
	@Test
	public void testExcludeOverlay() {
		TileFilter filter = new TileFilter();
		filter.excludeOverlayClass(Tree.class);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(Trees.createRandom());
		
		assertSame(false, filter.match(empty));
		assertSame(true, filter.match(weed));
		assertSame(false, filter.match(tree));
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
		assertSame(false, filter.match(ft1));
		assertSame(true, filter.match(ft2));
		assertSame(false, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
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
		assertSame(true, filter.match(ft1));
		assertSame(false, filter.match(ft2));
		assertSame(true, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
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
		assertSame(false, filter.match(ft1));
		assertSame(true, filter.match(ft2));
		assertSame(false, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
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
		assertSame(true, filter.match(ft1));
		assertSame(false, filter.match(ft2));
		assertSame(true, filter.match(new GroundTile()));
		assertSame(false, filter.match(new ForestTile()));
	}

	@Test
	public void testhasOverlay() {
		TileFilter filter = new TileFilter();
		filter.hasOverlay(true);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(Trees.createRandom());
		
		assertSame(false, filter.match(empty));
		assertSame(true, filter.match(weed));
		assertSame(true, filter.match(tree));
	}

	@Test
	public void testEmptyOverlay() {
		TileFilter filter = new TileFilter();
		filter.hasOverlay(false);
		Tile empty = new FarmTile();
		Tile weed = new FarmTile();
		weed.setOverlay(new Weeds());
		Tile tree = new FarmTile();
		tree.setOverlay(Trees.createRandom());
		
		assertSame(true, filter.match(empty));
		assertSame(false, filter.match(weed));
		assertSame(false, filter.match(tree));
	}

}
