/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corvidus.corvifarm.persistence;

/**
 *
 * @author hm
 */
public class Bitmap {
	private byte[] bytes;
	private static int[] lookup = {128, 64, 32, 16, 8, 4, 2, 1};
	private int bitmap = 0;
	public Bitmap(int bytes) {
		this.bytes = new byte[bytes];
		for(int i = 0; i<bytes;i++) {
			this.bytes[i] = 0;
		}
		
	}
	
	public void setBoolean(int index, boolean value) {
		int dec = Bitmap.lookup[index];
		if(value == true) {
			this.bitmap = this.bitmap | dec;
		} else {
			this.bitmap = this.bitmap ^ dec;
		}
	}
	
	public boolean getBoolean(int index) {
		int dec = Bitmap.lookup[index];
		int and = this.bitmap & dec;
	return and == dec;
	}
	
	public byte[] getBytes() {
		return this.bytes;
	}
	
	
	static String padLeft(String padstr, int amount) {
		String str = String.format("%"+amount+"s", padstr);
		str = str.replace(' ', '0');
	return str;
	}
	
	static String padLeft(int padint, int amount) {
		String padstr = Integer.toBinaryString(padint);
		padstr = Bitmap.padLeft(padstr, amount);
	return padstr;
	}
}
