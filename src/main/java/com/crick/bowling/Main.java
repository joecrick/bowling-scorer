package com.crick.bowling;

import com.crick.bowling.model.Game;
import com.crick.bowling.model.GameScore;
import com.crick.bowling.scoring.FinalFrameScorer;
import com.crick.bowling.scoring.FrameScorer;
import com.crick.bowling.scoring.GameScorer;
import com.crick.bowling.service.GameFactory;
import com.crick.bowling.service.ScoreFactory;
import com.crick.bowling.service.ThrowFinder;

public class Main {

	public static void main(String[] args) {
		Game game = new GameFactory().createNewGame();
		
		ThrowFinder throwFinder = new ThrowFinder();
		ScoreFactory scoreFactory = new ScoreFactory();
		
		FrameScorer frameScorer = new FrameScorer(throwFinder, scoreFactory);
		
		FinalFrameScorer finalFrameScorer = new FinalFrameScorer(scoreFactory);
		
		GameScorer gameScorer = new GameScorer(frameScorer, scoreFactory, finalFrameScorer);
		GameScore gameScore = gameScorer.score(game);
		
		System.out.println(gameScore.getTotal());
	}

}
