package com.corvidus.corvifarm.persistence;
public class Bitmap {
	private static final int[] lookup = {128, 64, 32, 16, 8, 4, 2, 1};
	private final int[] bitmap;
	public Bitmap(int bytes) {
		this.bitmap = new int[bytes];
		for(int i = 0; i<bytes;i++) {
			this.bitmap[i] = 0; 
		}
	}
	
	public static Bitmap fromBytes(byte[] bytes) {
		Bitmap bitmap = new Bitmap(bytes.length);
		for(int i = 0; i<bytes.length;i++) {
			bitmap.bitmap[i] = bytes[i];
		}
	return bitmap;
	}
	
	public void setBoolean(int index, boolean value) {
		int byteIndex = Math.floorDiv(index, 8);
		int dec = Bitmap.lookup[index % 8];
		if(value == true) {
			this.bitmap[byteIndex] = this.bitmap[byteIndex] | dec;
		} else {
			this.bitmap[byteIndex] = this.bitmap[byteIndex] ^ dec;
		}
	}
	
	public boolean getBoolean(int index) {
		int byteIndex = Math.floorDiv(index, 8);
		int dec = Bitmap.lookup[index % 8];
		int and = this.bitmap[byteIndex] & dec;
	return and == dec;
	}
	
	public byte[] getBytes() {
		byte[] bytes = new byte[this.bitmap.length];
		for(int i = 0; i < this.bitmap.length;i++) {
			bytes[i] = (byte)this.bitmap[i];
		}
	return bytes;
	}
}
