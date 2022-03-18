package com.crick.bowling.model;

public class FinalFrame extends Frame {

	private Throw thirdThrow = Throw.NOT_THROWN;

	public FinalFrame() {
		super(null);
	}

	public Throw getThirdThrow() {
		return thirdThrow;
	}

	public void setThirdThrow(Throw thirdThrow) {
		this.thirdThrow = thirdThrow;
	}

}
