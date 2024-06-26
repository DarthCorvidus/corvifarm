package com.corvidus.corvifarm.items;

import com.corvidus.corvifarm.items.crops.Crop;
import com.corvidus.corvifarm.items.crops.Fiber;
import com.corvidus.corvifarm.items.crops.Weeds;
import com.corvidus.corvifarm.items.stone.Stone;
import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.tools.Hoe;
import com.corvidus.corvifarm.items.tools.Pickaxe;
import com.corvidus.corvifarm.items.tools.Scythe;
import com.corvidus.corvifarm.items.tools.Watercan;
import com.corvidus.corvifarm.items.wood.Tree;
import com.corvidus.corvifarm.items.wood.Wood;
import com.corvidus.corvifarm.tiles.Tile;
import com.corvidus.corvifarm.tiles.TileFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemFactory {
	// Tools & Special Items
	public static final int HOE = 0;
	public static final int WATERCAN = 1;
	public static final int AXE = 2;
	public static final int PICKAXE = 3;
	public static final int SCYTHE = 4;
	public static final int SHIPPING_BIN = 5;
	public static final int SHOP = 6;
	public static final int EXIT = 7;
	public static final int BED = 8;
	
	// Crops
	public static final int WHEAT = 100;
	public static final int PARSNIP = 101;
	public static final int POTATO = 102;
	// -"-
	public static final int SEEDS = 197;
	public static final int FIBER = 198;
	public static final int WEEDS = 199;
	
	//Trees
	public static final int TREE_OAK = 200;
	public static final int TREE_PINE = 201;
	public static final int TREE_CYPRESS = 202;
	public static final int TREE_FIG = 203;
	public static final int TREE_OLIVE = 204;
	public static final int TREE_CEDRUS = 205;
	public static final int TREE_MAHOGANY = 206;
	public static final int WOOD = 207;
	public static final int HARDWOOD = 208;
	
	// Forage
	public static final int TRUFFLE = 300;
	
	// Mining
	public static final int STONE = 400;
	public static final int COAL = 401;
	public static final int COPPER_ORE = 402;
	public static final int IRON_ORE = 403;
	public static final int COPPER_SCRAP = 404;
	public static final int IRON_SCRAP = 405;
	public static final int ALUMINUM_SCRAP = 406;
	public static final int GOLD_SCRAP = 407;
	public static final int TIN_SCRAP = 408;

	Map<Integer, Item> prototypes;
	Random rand;
	private static ItemFactory instance;
	private ItemFactory() {
		this.rand = new Random();
		this.prototypes = new HashMap<>();
		this.addPrototype(new Hoe());
		this.addPrototype(new Watercan());
		this.addPrototype(new Axe());
		this.addPrototype(new Pickaxe());
		this.addPrototype(new Scythe());
		this.addPrototype(new Tree(TREE_OAK, "Oak"));
		this.addPrototype(new Tree(TREE_PINE, "Pine"));
		this.addPrototype(new Tree(TREE_CYPRESS, "Cypress"));
		this.addPrototype(new Tree(TREE_CEDRUS, "Cedrus"));
		this.addPrototype(new Tree(TREE_FIG, "Fig"));
		this.addPrototype(new Tree(TREE_OLIVE, "Olive"));
		this.addPrototype(new Crop(WHEAT, 4, 10, "Wheat"));
		this.addPrototype(new Crop(PARSNIP, 4, 10, "Parsnip"));
		this.addPrototype(new Crop(POTATO, 6, 40, "Potato"));
		this.addPrototype(new Fiber());
		this.addPrototype(new Weeds());
		this.addPrototype(new Wood());
		this.addPrototype(new Stone());
	}

	private void addPrototype(Item item) {
		if(this.prototypes.containsKey(item.getId())) {
			throw new Error("Trying to add ambiguous item");
		}
		this.prototypes.put(item.getId(), item);
	}
	
	public static Item getPrototype(int id) {
		if(ItemFactory.instance == null) {
			ItemFactory.instance = new ItemFactory();
		}
		Item prototype = ItemFactory.instance.prototypes.get(id);
		if(prototype instanceof ItemPrototype proto) {
			return proto.createPrototype();
		}
		
		Class<?> clazz = prototype.getClass();
		try {
			Object newInstance = clazz.getDeclaredConstructor().newInstance();
			return (Item)newInstance;
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			throw new Error(e);
		}
	}
	
	public static Item getPrototype(int id, int amount) {
		Item item = ItemFactory.getPrototype(id);
		item.setAmount(amount);
	return item;
	}
	
	public static List<Item> getByClass(Class clazz) {
		if(ItemFactory.instance == null) {
			ItemFactory.instance = new ItemFactory();
		}
		List<Item> items = new ArrayList<>();
		for(int key : ItemFactory.instance.prototypes.keySet()) {
			Item item = ItemFactory.instance.prototypes.get(key);
			if(clazz.isInstance(item)) {
				items.add(ItemFactory.getPrototype(item.getId()));
			}
		}
	return items;
	}
	
	public static Tree createRandomTree() {
		List<Item> items = ItemFactory.getByClass(Tree.class);
		Tree tree = (Tree)items.get(ItemFactory.instance.rand.nextInt(items.size()));
		tree.setState(ItemFactory.instance.rand.nextInt(Tree.TREE+1));
	return tree;
	}
}
