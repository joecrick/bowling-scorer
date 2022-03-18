package com.crick.bowling.scoring;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.ScoreFactory;

public class FinalFrameScorer {

	private ScoreFactory scoreFactory;

	public FinalFrameScorer(ScoreFactory scoreFactory) {
		this.scoreFactory = scoreFactory;
	}

	public FrameScore score(FinalFrame finalFrame) {
		FrameScore frameScore = this.scoreFactory.newFrameScore();
		int score = 0;
		if(finalFrame.getSecondThrow() == Throw.SPARE) {
			score += 10 + finalFrame.getThirdThrow().getScore();
		} else if(finalFrame.getSecondThrow() == Throw.GUTTER) {
			score += finalFrame.getFirstThrow().getScore();
		} else {
			score = finalFrame.getFirstThrow().getScore() + finalFrame.getSecondThrow().getScore() + finalFrame.getThirdThrow().getScore();
		}
		frameScore.setTotal(score);
		return frameScore;
	}

}