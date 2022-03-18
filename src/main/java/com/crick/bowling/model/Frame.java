package com.crick.bowling.model;

public class Frame {
	
	private Throw firstThrow = Throw.NOT_THROWN;
	
	private Throw secondThrow = Throw.NOT_THROWN;

	private Frame nextFrame;
	
	public Frame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
	
	public Throw getFirstThrow() {
		return firstThrow;
	}

	public void setFirstThrow(Throw firstThrow) {
		this.firstThrow = firstThrow;
	}

	public Throw getSecondThrow() {
		return secondThrow;
	}

	public void setSecondThrow(Throw secondThrow) {
		this.secondThrow = secondThrow;
	}

	public Frame getNextFrame() {
		return nextFrame;
	}

}
