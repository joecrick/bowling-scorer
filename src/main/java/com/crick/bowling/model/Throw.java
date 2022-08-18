package com.crick.bowling.model;

public enum Throw {

	NOT_THROWN(0, ' '),
	GUTTER(0, '-'),
	ONE(1, '1'),
	TWO(2, '2'),
	THREE(3, '3'),
	FOUR(4, '4'),
	FIVE(5, '5'),
	SIX(6, '6'),
	SEVEN(7, '7'),
	EIGHT(8, '8'),
	NINE(9, '9'),
	STRIKE(10, 'X'),
	SPARE(10, '/');
	
	private int score;
	
	private char symbol;
	
	private Throw(int score, char symbol) {
		this.score = score;
		this.symbol = symbol;
	}

	public static Throw findBySymbol(char symbol) {
		for(Throw t : Throw.values()) {
			if(symbol == t.symbol) {
				return t;
			}
		}
		return Throw.NOT_THROWN;
	}
	
	public boolean hasBonus() {
		return this == STRIKE || this == SPARE;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
}
