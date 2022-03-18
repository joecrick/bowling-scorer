package com.crick.bowling.service;

import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.GameScore;

public class ScoreFactory {

	public FrameScore newFrameScore() {
		return new FrameScore();
	}

	public GameScore newGameScore() {
		return new GameScore();
	}

}
