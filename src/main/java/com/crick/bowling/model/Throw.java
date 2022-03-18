package com.crick.bowling.model;

public enum Throw {

	NOT_THROWN(0),
	GUTTER(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	STRIKE(10),
	SPARE(10);
	
	private int score;
	
	private Throw(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
}
