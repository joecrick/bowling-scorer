package com.crick.bowling.scoring;

import java.util.List;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.ScoreFactory;
import com.crick.bowling.service.ThrowFinder;

public class FrameScorer {

	private ThrowFinder throwFinder;
	
	private ScoreFactory scoreFactory;

	public FrameScorer(ThrowFinder throwFinder, ScoreFactory scoreFactory) {
		this.throwFinder = throwFinder;
		this.scoreFactory = scoreFactory;
	}

	public FrameScore score(Frame frame) {
		int score = frame.getFirstThrow() == Throw.STRIKE || frame.getSecondThrow() == Throw.SPARE ? 10 : 
					frame.getFirstThrow().getScore() + frame.getSecondThrow().getScore();

		FrameScore frameScore = this.scoreFactory.newFrameScore();
		frameScore.setTotal(score);
		return frameScore;
	}

	public FrameScore scoreCountingStrikesAndSpares(Frame frame) {
		FrameScore frameScore = this.score(frame);
		this.addSparesAndStrikes(frameScore, frame);
		return frameScore;
	}

	private void addSparesAndStrikes(FrameScore frameScore, Frame frame) {
		if(frame.getFirstThrow() == Throw.STRIKE) {
			this.addStrike(frameScore, frame);
		} else if(frame.getSecondThrow() == Throw.SPARE) {
			Throw nextThrow = this.throwFinder.findNextThrow(frame);
			frameScore.setTotal(frameScore.getTotal() + nextThrow.getScore());
			frameScore.setCanCount(nextThrow != Throw.NOT_THROWN);
		} else {
			frameScore.setCanCount(frame.getSecondThrow() != Throw.NOT_THROWN);
		}
	}

	private void addStrike(FrameScore frameScore, Frame frame) {
		List<Throw> nextThrows = this.throwFinder.findNextTwoThrows(frame);
		if(nextThrows.get(1) == Throw.SPARE) {
			frameScore.setTotal(frameScore.getTotal() + 10);
			frameScore.setCanCount(true);
		} else {
			for(Throw t: nextThrows) {
				frameScore.setTotal(frameScore.getTotal() + t.getScore());
			}
			frameScore.setCanCount(nextThrows.get(1) != Throw.NOT_THROWN);
		}
	}

}
