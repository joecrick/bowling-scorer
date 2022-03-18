package com.crick.bowling.service;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;
import com.crick.bowling.model.Throw;

public class GameBuilder {

	private GameFactory gameFactory;
	
	private Frame currentFrame;
	
	private Game currentGame;
	
	public GameBuilder(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
		this.reset();
	}

	public GameBuilder addThrow(Throw t) {
		if(this.currentFrame.getFirstThrow() == Throw.NOT_THROWN) {
			this.currentFrame.setFirstThrow(t);
			if(!this.isFinalFrame(this.currentFrame) && t == Throw.STRIKE) {
				this.currentFrame = this.currentFrame.getNextFrame();
			}
		} else if(this.currentFrame.getSecondThrow() == Throw.NOT_THROWN) {
			this.currentFrame.setSecondThrow(t);
			if(!this.isFinalFrame(this.currentFrame)) {
				this.currentFrame = this.currentFrame.getNextFrame();
			}
		} else {
			((FinalFrame) this.currentFrame).setThirdThrow(t);
		}
		
		return this;
	}
	
	public Game finish() {
		Game oldGame = this.currentGame;
		this.reset();
		return oldGame;
	}
	
	private void reset() {
		this.currentGame = this.gameFactory.createNewGame();
		this.currentFrame = this.currentGame.getFirstFrame();		
	}
	
	private boolean isFinalFrame(Frame frame) {
		return frame instanceof FinalFrame;
	}

}
