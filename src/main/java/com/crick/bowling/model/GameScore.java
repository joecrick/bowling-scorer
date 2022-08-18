package com.crick.bowling.model;

public class GameScore {

	private int total;

	private int frameTotal;

	private boolean waiting;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getFrameTotal() {
		return frameTotal;
	}

	public void setFrameTotal(int frameTotal) {
		this.frameTotal = frameTotal;
	}

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

}
