package com.corvidus.corvifarm.items.crops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crops {
	public static final int WHEAT = 0;
	public static final int PARSNIP = 1;
	public static final int POTATO = 2;
	private static Crops crops;
	Map<Integer, Crop> prototypes;
	private Crops() {
		this.prototypes = new HashMap<>();
		this.addPrototype(new Crop(WHEAT, 4, 10, "Wheat"));
		this.addPrototype(new Crop(PARSNIP, 4, 10, "Parsnip"));
		this.addPrototype(new Crop(POTATO, 6, 40, "Potato"));
	}
	
	private void addPrototype(Crop crop) {
		this.prototypes.put(crop.getId(), crop);
	}
	
	public static Crops getInstance() {
		if(Crops.crops == null) {
			Crops.crops = new Crops();
		}
	return crops;
	}
	
	public static List<Crop> getPrototypes() {
		ArrayList<Crop> templates = new ArrayList<>();
		for(int key : Crops.getInstance().prototypes.keySet()) {
			templates.add(Crops.createSeed(crops.prototypes.get(key).getId()));
		}
	return templates;
	}
	
	public static Crop create(int id) {
		Crop proto = Crops.getInstance().prototypes.get(id);
		Crop inst = proto.create();
	return inst;
	}

	public static Crop create(int id, int amount) {
		Crop inst = Crops.create(id);
		inst.setAmount(amount);
	return inst;
	}
	
	public static Crop createSeed(int id) {
		Crop crop = Crops.create(id);
		crop.setState(Crop.SEED);
	return crop;
	}
	
	public static Crop createSeed(int id, int amount) {
		Crop crop = Crops.createSeed(id);
		crop.setAmount(amount);
	return crop;
	}
	
	public static Crop createProduce(int id) {
		Crop crop = Crops.create(id);
		crop.setState(Crop.PRODUCE);
	return crop;
	}

	public static Crop createProduce(int id, int amount) {
		Crop crop = Crops.createProduce(id);
		crop.setAmount(amount);
	return crop;
	}

}
