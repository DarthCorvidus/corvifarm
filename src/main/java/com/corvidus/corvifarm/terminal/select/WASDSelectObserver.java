package com.corvidus.corvifarm.terminal.select;
public interface WASDSelectObserver {
	public void onFocus(WASDSelect wasdSelect, WASDSelectElement element);
	public void onSelect(WASDSelect wasdSelect, WASDSelectElement element);
	public void onFocusEmpty(WASDSelect wasdSelect);
	public void onSelectEmpty(WASDSelect wasdSelect);
}
