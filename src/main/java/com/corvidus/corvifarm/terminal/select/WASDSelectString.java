package com.corvidus.corvifarm.terminal.select;
public class WASDSelectString implements WASDSelectElement {
	public String string;
	public WASDSelectString(String string) {
		this.string = string;
	}
	@Override
	public String getWASDString() {
		return this.string;
	}

	@Override
	public Object getObject() {
		return this.string;
	}
	
}
