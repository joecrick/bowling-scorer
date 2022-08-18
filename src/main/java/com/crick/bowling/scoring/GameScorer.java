package com.crick.bowling.scoring;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Game;
import com.crick.bowling.model.GameScore;
import com.crick.bowling.service.ScoreFactory;

public class GameScorer {

	private FrameScorer frameScorer;

	private FinalFrameScorer finalFrameScorer;

	private ScoreFactory scoreFactory;

	public GameScorer(FrameScorer frameScorer, ScoreFactory scoreFactory, FinalFrameScorer finalFrameScorer) {
		this.frameScorer = frameScorer;
		this.scoreFactory = scoreFactory;
		this.finalFrameScorer = finalFrameScorer;
	}

	public GameScore score(Game game) {
		GameScore gameScore = this.scoreFactory.newGameScore();

		Frame frame = game.getFirstFrame();
		while(!(frame instanceof FinalFrame)) {
			FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);
			FrameScore score = this.frameScorer.score(frame);
		    gameScore.setTotal(gameScore.getTotal() + frameScore.getTotal());
		    gameScore.setFrameTotal(frameScore.canCount() ? frameScore.getTotal() : score.getTotal());
			frame = frame.getNextFrame();
		}

		FrameScore score = this.finalFrameScorer.score((FinalFrame)frame);
		gameScore.setTotal(gameScore.getTotal() + score.getTotal());

		return gameScore;
	}

}
