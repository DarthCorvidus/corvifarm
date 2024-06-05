package com.corvidus.corvifarm.game;

import java.time.Duration;
import java.time.Instant;

class Cooldown {
	private int milli = 1000;
	private Instant start = Instant.now();
	public Cooldown(int lengthMilliseconds) {
		this.milli = lengthMilliseconds;
	}
	
	public int getDelayMilliseconds() {
		return this.milli;
	}
	
	public void setDelayMilliseconds(int delayMilliseconds) {
		this.milli = delayMilliseconds;
	}
	
	public boolean ready() {
		Instant now = Instant.now();
		Duration delta = Duration.between(this.start, now);
		if(delta.toMillis() <this.milli) {
			return false;
		}
		this.start = now;
	return true;
	}
}
