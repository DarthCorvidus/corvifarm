package com.corvidus.corvifarm.items;

import com.corvidus.corvifarm.items.tools.Axe;
import com.corvidus.corvifarm.items.tools.Hoe;
import com.corvidus.corvifarm.items.tools.Pickaxe;
import com.corvidus.corvifarm.items.tools.Scythe;
import com.corvidus.corvifarm.items.tools.Watercan;
import com.corvidus.corvifarm.items.wood.Tree;
import java.util.HashMap;
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
	public static final int OAK = 200;
	public static final int PINE = 201;
	public static final int CYPRESS = 202;
	public static final int FIG = 203;
	public static final int OLIVE = 204;
	public static final int CEDRUS = 205;
	public static final int MAHOGANY = 206;
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
	private ItemFactory() {
		this.prototypes = new HashMap<>();
		this.addPrototype(new Hoe());
		this.addPrototype(new Watercan());
		this.addPrototype(new Axe());
		this.addPrototype(new Pickaxe());
		this.addPrototype(new Scythe());
		this.addPrototype(new Tree(OAK, "Oak"));
		this.addPrototype(new Tree(PINE, "Pine"));
		this.addPrototype(new Tree(CYPRESS, "Cypress"));
		this.addPrototype(new Tree(CEDRUS, "Cedrus"));
		this.addPrototype(new Tree(FIG, "Fig"));
		this.addPrototype(new Tree(OLIVE, "Olive"));
	}

	private void addPrototype(Item item) {
		if(this.prototypes.containsKey(item.getId())) {
			throw new Error("Trying to add ambiguous item");
		}
		this.prototypes.put(item.getId(), item);
	}
}
