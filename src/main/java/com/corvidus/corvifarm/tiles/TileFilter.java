package com.corvidus.corvifarm.tiles;
import com.corvidus.corvifarm.items.Item;
import java.util.ArrayList;
import java.util.List;

public class TileFilter {
	private List<Class> includeTileClass = new ArrayList<>();
	private List<Class> excludeTileClass = new ArrayList<>();
	private List<Class> includeOverlayClass = new ArrayList<>();
	private List<Class> excludeOverlayClass = new ArrayList<>();
	private Boolean tilled = null;
	private Boolean watered = null;
	private Boolean overlay = null;
	public void includeTileClass(Class klasse) {
		this.includeTileClass.add(klasse);
	}
	
	public void excludeTileClass(Class klasse) {
		this.excludeTileClass.add(klasse);
	}
	
	public void includeOverlayClass(Class klasse) {
		this.includeOverlayClass.add(klasse);
	}
	
	public void excludeOverlayClass(Class klasse) {
		this.excludeOverlayClass.add(klasse);
	}
	
	public void tilled(boolean tilled) {
		this.tilled = tilled;
	}

	public void watered(boolean watered) {
		this.watered = watered;
	}

	public void hasOverlay(boolean overlay) {
		this.overlay = overlay;
	}
	
	private boolean checkInstance(Object object, Class klasse) {
		return klasse.isInstance(object);
	}

	private boolean matchTileClass(Tile tile) {
		for(Class klasse : this.excludeTileClass) {
			if(this.checkInstance(tile, klasse)) {
				return false;
			}
		}

		for(Class klasse : this.includeTileClass) {
			if(!this.checkInstance(tile, klasse)) {
				return false;
			}
		}
	return true;
	}
	
	private boolean matchOverlayClass(Tile tile) {
		if(this.includeOverlayClass.isEmpty() && this.excludeOverlayClass.isEmpty()) {
			return true;
		}

		if(!tile.hasOverlay()) {
			return false;
		}
		Item item = tile.getOverlay();
		for(Class klasse : this.excludeOverlayClass) {
			if(this.checkInstance(item, klasse)) {
				return false;
			}
		}

		for(Class klasse : this.includeOverlayClass) {
			if(!this.checkInstance(item, klasse)) {
				return false;
			}
		}
	return true;
	}
	
	public boolean match(Tile tile) {
		if(!this.matchTileClass(tile)) {
			return false;
		}

		if(!this.matchOverlayClass(tile)) {
			return false;
		}

		if(this.tilled != null && !(tile instanceof Tillable)) {
			return false;
		}
		// evaluates to false if tilled is set & instance is not tillable or is not tilled.
		if(this.tilled != null && tile instanceof Tillable tl &&  this.tilled != tl.isTilled()) {
			return false;
		}

		if(this.watered != null && !(tile instanceof Waterable)) {
			return false;
		}

		if(this.watered != null && tile instanceof Waterable wt &&  this.watered != wt.isWatered()) {
			return false;
		}

		if(this.overlay != null && this.overlay != tile.hasOverlay()) {
			return false;
		}

	return true;
	}
}
