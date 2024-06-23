package com.corvidus.corvifarm.tiles;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TileFactory {
	public static int FARM = 0;
	public static int FOREST = 1;
	public static int GROUND = 2;
	public static int IMMUTABLE = 3;
	private static final Map<Integer, Tile> prototypes = new HashMap<>();
	private static TileFactory instance = null;
	private TileFactory() {
		this.addPrototype(new FarmTile());
		this.addPrototype(new ForestTile());
		this.addPrototype(new GroundTile());
		this.addPrototype(new ImmutableTile());
	}
	
	private void addPrototype(Tile tile) {
		TileFactory.prototypes.put(tile.getId(), tile);
	}
	
	public static Tile getPrototype(int id) {
		if(TileFactory.instance == null) {
			TileFactory.instance = new TileFactory();
		}
		Tile prototype = TileFactory.prototypes.get(id);
		Class<?> clazz = prototype.getClass();
		try {
			Object newInstance = clazz.getDeclaredConstructor().newInstance();
			return (Tile)newInstance;
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			throw new Error(e);
		}
	}
}
