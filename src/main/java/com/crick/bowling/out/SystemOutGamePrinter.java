package com.crick.bowling.out;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Game;
import com.crick.bowling.model.Throw;
import com.crick.bowling.scoring.FrameScorer;
import com.crick.bowling.scoring.GameScorer;
import com.crick.bowling.service.FrameFinder;

public class SystemOutGamePrinter {

	private GameScorer gameScorer;
	
	private SystemOutFramePrinter framePrinter;
	
	private FrameFinder frameFinder;
	
	private FrameScorer frameScorer;
	
	public SystemOutGamePrinter(SystemOutFramePrinter framePrinter, GameScorer gameScorer, FrameFinder frameFinder, FrameScorer frameScorer) {
		this.gameScorer = gameScorer;
		this.framePrinter = framePrinter;
		this.frameFinder = frameFinder;
		this.frameScorer = frameScorer;
	}

	public void print(Game game) {
		System.out.println("------------------------------------------------------------");
		this.printFirstRow(game);
		System.out.println(System.lineSeparator() + "------------------------------------------------------------");
		this.printScores(game);
		System.out.println(System.lineSeparator() + "------------------------------------------------------------");
		System.out.println(this.gameScorer.score(game).getTotal());
	}

	private void printFirstRow(Game game) {
//		System.out.println(gameScore.getFrameTotal() + " - " + gameScore.getTotal() + " - " + gameScore.isWaiting());
		for(int x = 1; x <= 10; x++) {
			System.out.print("|  ");
			Frame frame = this.frameFinder.findFrame(game, x);
			System.out.print("" + frame.getFirstThrow().getSymbol() + frame.getSecondThrow().getSymbol());
			if(frame instanceof FinalFrame) {
				System.out.print("" + ((FinalFrame) frame).getThirdThrow().getSymbol());
			}
			System.out.print(" |");
		}
	}

	private void printScores(Game game) {
		int frameCount = 0;
		FrameScore previousScore = null;
		for(int x = 1; x <= 10; x++) {
			if(x == 10) {
				int i = 0;
			}
			System.out.print("| ");
			Frame frame = this.frameFinder.findFrame(game, x);
			FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);
			if(frameScore.canCount()) {
				frameCount += frameScore.getTotal();
				System.out.print(frameCount);
			} else {
				System.out.print("  ");
			}
			Frame previousFrame = this.frameFinder.findPreviousFrame(game, frame);
			if(frame instanceof FinalFrame) {
				frameCount += frameScore.getTotal();
				if(previousScore != null && previousScore.canCount()) {
					System.out.print(frameCount);
				}
			} else {
				System.out.print("   ");
			}
			System.out.print(" |");
			previousScore = frameScore;
		}
	}

}
