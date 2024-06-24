package com.corvidus.corvifarm.items;
/**
 * Item prototypes are Items which can create a prototype of themselves. Yes, I
 * know what 'Cloneable' is, but createPrototype is specifically to create the
 * basic version of an item, such as always creating Crop Seeds from a crop.
 * The idea is to represent a wide variety of objects by only one class, such as
 * crops & trees, as only their attributes, but not their methods differ.
 * @author hm
 */
public interface ItemPrototype {
	public Item createPrototype();
}
