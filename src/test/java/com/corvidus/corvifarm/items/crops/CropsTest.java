package com.corvidus.corvifarm.items.crops;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CropsTest {

	public CropsTest() {
	}
	
	/**
	 * Test of getPrototypes method, of class Crops.
	 */
	@Test
	public void testGetPrototypes() {
		List<Crop> prototypes = Crops.getPrototypes();
		for(Crop crop: prototypes) {
			assertSame(crop.getState(), Crop.SEED);
		}
	}
	
	public void testCreateSeed() {
		Crop crop = Crops.createSeed(Crops.WHEAT);
		assertSame(Crops.WHEAT, crop.getId());
		assertSame(Crop.SEED, crop.getState());
	}
	
	public void testCreateProduce() {
		Crop crop = Crops.createProduce(Crops.PARSNIP);
		assertSame(Crops.PARSNIP, crop.getId());
		assertSame(Crop.PRODUCE, crop.getState());
	}

}