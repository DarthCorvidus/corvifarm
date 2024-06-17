package com.corvidus.corvifarm.items.wood;

import com.corvidus.corvifarm.items.crops.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Trees {
	public static final int OAK = 0;
	public static final int PINE = 1;
	public static final int CYPRESS = 2;
	public static final int FIG = 3;
	public static final int OLIVE = 4;
	public static final int CEDRUS = 5;
	public static final int MAX = 6;
	private Random rand;
	private static Trees trees;
	Map<Integer, Tree> prototypes;
	private Trees() {
		this.rand = new Random();
		this.prototypes = new HashMap<>();
		this.addPrototype(new Tree(OAK, "Oak"));
		this.addPrototype(new Tree(PINE, "Pine"));
		this.addPrototype(new Tree(CYPRESS, "Cypress"));
		this.addPrototype(new Tree(CEDRUS, "Cedrus"));
		this.addPrototype(new Tree(FIG, "Fig"));
		this.addPrototype(new Tree(OLIVE, "Olive"));
	}
	
	private void addPrototype(Tree tree) {
		this.prototypes.put(tree.getID(), tree);
	}
	
	public static Trees getInstance() {
		if(Trees.trees == null) {
			Trees.trees = new Trees();
		}
	return trees;
	}
	
	public static List<Tree> getPrototypes() {
		ArrayList<Tree> templates = new ArrayList<>();
		for(int key : Trees.getInstance().prototypes.keySet()) {
			templates.add(Trees.create(trees.prototypes.get(key).getID()));
		}
	return templates;
	}
	
	public static Tree create(int id) {
		Tree proto = Trees.getInstance().prototypes.get(id);
		Tree inst = proto.create();
	return inst;
	}
	
	public static Tree createRandom() {
		int id = Trees.getInstance().rand.nextInt(Trees.MAX);
		int state = Trees.getInstance().rand.nextInt(Tree.TREE+1);
		Tree tree = Trees.create(id);
		tree.setState(state);
	return tree;
	}
}
