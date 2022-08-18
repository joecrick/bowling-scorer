package com.crick.bowling;

import java.util.Scanner;

import com.crick.bowling.model.Game;
import com.crick.bowling.model.GameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.out.SystemOutFramePrinter;
import com.crick.bowling.out.SystemOutGamePrinter;
import com.crick.bowling.scoring.FinalFrameScorer;
import com.crick.bowling.scoring.FrameScorer;
import com.crick.bowling.scoring.GameScorer;
import com.crick.bowling.service.FrameFinder;
import com.crick.bowling.service.GameBuilder;
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

		GameBuilder gameBuilder = new GameBuilder(new GameFactory());
		SystemOutGamePrinter gamePrinter = new SystemOutGamePrinter(new SystemOutFramePrinter(), gameScorer, new FrameFinder(), frameScorer);
		
		Main.startGame(gameBuilder, gamePrinter, gameScorer);
		
//		System.out.println(gameScore.getTotal());
	}

	public static void startGame(GameBuilder gameBuilder, SystemOutGamePrinter gamePrinter, GameScorer gameScorer) {
		Scanner scanner = new Scanner(System.in);
		String readString = "";
		while(readString != null) {
			if (scanner.hasNextLine()) {
				readString = scanner.nextLine();
				Throw t = Throw.findBySymbol(readString.charAt(0));
				gameBuilder.addThrow(t);
				System.out.println("added throw " + t.getSymbol());
				gamePrinter.print(gameBuilder.get());
			} else {
				System.out.println("wtf");
				readString = null;
			}
		}
	}
	
}
