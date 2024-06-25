package com.corvidus.corvifarm.persistence;

import com.corvidus.corvifarm.items.ItemFactory;
import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.tiles.FarmTile;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.TileFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PersistenceTileTest {
	@Test
	public void testToBinary() throws Exception {
		byte[] expected = {0, 0, 82, 40, 0, (byte)TileFactory.FARM, FarmTile.WATERED, 0, 0, 0, 0, 15};
		FarmTile tile = new FarmTile();
		tile.till();
		tile.water();
		
		PersistenceTile entry = PersistenceTile.fromTile(21032, tile, 15);
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
	public void testFromBinary() {
		byte[] input = {0, 0, 82, 40, 0, (byte)TileFactory.FARM, FarmTile.WATERED, 0, 0, 0, 0, 15};
		ByteArrayInputStream bi = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bi);
		try {
			PersistenceTile persistence = PersistenceTile.fromBinary(dis);
			FarmTile tile = (FarmTile)persistence.createTile();
			assertEquals("Watered", tile.getName());
			assertEquals(21032, persistence.getPrimaryKey());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
