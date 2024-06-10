package com.corvidus.corvifarm.items.wood;
import com.corvidus.corvifarm.items.Axable;
import com.corvidus.corvifarm.items.Item;
import com.corvidus.corvifarm.items.ItemAbstract;
import com.corvidus.corvifarm.tiles.Tile;
import java.util.ArrayList;
import java.util.Random;
abstract class Tree extends ItemAbstract implements Axable {
	protected int state = 0;
	protected int maxHP = 0;
	protected int wood;
	protected int hp = 0;
	private final Random rand;
	public static final int SEED = 0;
	public static final int SAPLING = 1;
	public static final int TREELING = 2;
	public static final int SMALL = 3;
	public static final int TREE = 4;
	public Tree(int state) {
		this.state = state;
		this.rand = new Random();
		this.regenerateHP();
	}
	
	public void setState(int state) {
		if(this.state == state) {
			return;
		}
		this.state = state;
		this.regenerateHP();
	}
	
	/*
	public function apply(Land land, Player player): void {
		filter = new TileFilter();
		filter->hasOverlay(false);
		tiles = land->getTiles()->getFiltered(filter);
		if(tiles->getCount()==0) {
			return;
		}
		tiles->getTile(0)->setOverlay(this);
		this.setAmount(this.getAmount()-1);
		land->updateText();
	}
	*/

	public boolean isGrown() {
		return this.state == this.TREE;
	}
	
	public abstract Tree asSeed();
	
	private void regenerateHP() {
		switch (this.state) {
			case SEED:
				this.maxHP = 0;
				this.wood = 0;
				break;
			case Tree.SAPLING:
				this.maxHP = 0;
				this.wood = 0;
			break;
			case Tree.TREELING:
				this.maxHP = 10;
				this.wood = 2;
			break;
			case Tree.SMALL:
				this.maxHP = 25;
				this.wood = 5;
			break;
			case Tree.TREE:
				this.maxHP = 50;
				this.wood = 15;
			break;
		}
	this.hp = this.maxHP;
	}
	
	public void progress() {
		if(this.state<Tree.TREE) {
			this.state++;
		}
	}
	
	public void passDay(Tile tile) {
		if(this.rand.nextInt(10)<2) {
			this.progress();
		}
		this.regenerateHP();
	}
	
	@Override
	public void axe() {
		this.hp -= 10;
		if(this.hp <= 0) {
			this.setAmount(0);
		}
	}
	
	@Override
	public ArrayList<Item> getAxedItems() {
		ArrayList<Item> items = new ArrayList<>();
		if(this.state == Tree.SEED) {
			Tree seed = this;
			seed.setAmount(1);
			items.add(seed);
		return items;
		}
		Wood wood = new Wood();
		wood.setAmount(this.wood);
		items.add(wood);
		if(this.rand.nextInt(10)<3) {
			items.add(this.asSeed());
		}
	return items;
	}

	@Override
	public boolean yieldsItem() {
		if(this.hp <= 0) {
			return true;
		}
	return false;
	}
	
	@Override
	public int getBaseDemand() {
		return 0;
	}
}
