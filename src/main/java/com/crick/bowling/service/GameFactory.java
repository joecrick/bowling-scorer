package com.crick.bowling.service;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;

/**
 * Factory to make new {@link Game}s.
 * 
 * @author jcrick
 */
public class GameFactory {

	public Game createNewGame() {
		Frame firstFrame = this.generateFrames();
		return new Game(firstFrame);
	}

	private Frame generateFrames() {
		Frame previousFrame = null;
		for(int x = 10; x > 0; x--) {
			previousFrame = x == 10 ? new FinalFrame() : new Frame(previousFrame);
		}
		return previousFrame;
	}
	
}
