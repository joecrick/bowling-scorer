package com.crick.bowling.scoring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.Game;
import com.crick.bowling.model.GameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.GameBuilder;
import com.crick.bowling.service.GameFactory;
import com.crick.bowling.service.ScoreFactory;
import com.crick.bowling.service.ThrowFinder;
import com.crick.bowling.util.TestGames;

@ExtendWith(MockitoExtension.class)
public class GameScorerTest {

	private ScoreFactory scoreFactory = new ScoreFactory();

	private FrameScorer frameScorer = new FrameScorer(new ThrowFinder(), this.scoreFactory);
	
	private FinalFrameScorer finalFrameScorer = new FinalFrameScorer(this.scoreFactory);
	
	private GameScorer gameScorer;
	
	@BeforeEach
	public void setup() {
		this.gameScorer = new GameScorer(this.frameScorer, this.scoreFactory, this.finalFrameScorer);
	}
	
	@Test
	public void test300() {
		Game game = TestGames.generate300();

		GameScore score = this.gameScorer.score(game);
		
		assertThat(score.getTotal(), is(300));
	}

	@Test
	public void test0() {
		Game game = TestGames.generate0();

		GameScore score = this.gameScorer.score(game);
		
		assertThat(score.getTotal(), is(0));		
	}

	@Test
	public void test235() {
		Game game = TestGames.generate235();

		GameScore score = this.gameScorer.score(game);
		
		assertThat(score.getTotal(), is(235));		
	}

	@Test
	public void test267() {
		Game game = TestGames.generate267();
		
		GameScore score = this.gameScorer.score(game);
		
		assertThat(score.getTotal(), is(267));
	}
	
	@Test
	public void testIncompleteWithNoClosedFrame() {
		Game game = new GameBuilder(new GameFactory())
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.STRIKE)
			.get();
	}

}
