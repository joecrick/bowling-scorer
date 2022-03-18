package com.crick.bowling.scoring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.Frame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.ScoreFactory;
import com.crick.bowling.service.ThrowFinder;

@ExtendWith(MockitoExtension.class)
public class FrameScorerTest {

	@Mock
	private ThrowFinder throwFinder;

	@Mock
	private ScoreFactory scoreFactory;

	private FrameScorer frameScorer;

	@BeforeEach
	public void setup() {
		when(this.scoreFactory.newFrameScore()).thenReturn(new FrameScore());
		this.frameScorer = new FrameScorer(this.throwFinder, this.scoreFactory);
	}

	@Test
	public void testScoreFrameStrike() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.STRIKE);
	
		FrameScore frameScore = this.frameScorer.score(frame);
		
		assertThat(frameScore.getTotal(), is(10));
	}

	@Test
	public void testScoreFrameSpare() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.ONE);
	    frame.setSecondThrow(Throw.SPARE);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(10));
	}
	
	@Test
	public void testScoreFrameNumber() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.ONE);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(1));
	}
	
	@Test
	public void testScoreFrameNumberTwoThrows() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.ONE);
		frame.setSecondThrow(Throw.EIGHT);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(9));
	}

	@Test
	public void testScoreFrameNumberTwoThrowsWithGutter() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.ONE);
		frame.setSecondThrow(Throw.GUTTER);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(1));
	}

	@Test
	public void testScoreFrameNumberTwoThrowsWithGutterInFirstThrow() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.GUTTER);
		frame.setSecondThrow(Throw.TWO);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(2));
	}

	@Test
	public void testScoreFrameNumberTwoGutters() {
		Frame frame = new Frame(null);
		frame.setFirstThrow(Throw.GUTTER);
		frame.setSecondThrow(Throw.GUTTER);

		FrameScore frameScore = this.frameScorer.score(frame);

		assertThat(frameScore.getTotal(), is(0));
	}

	@Test
	public void testScoreWithStrikesAndSparesTurkey() {
		Frame frame = mock(Frame.class);
		when(frame.getFirstThrow()).thenReturn(Throw.STRIKE);
		
		List<Throw> nextTwoThrows = Arrays.asList(Throw.STRIKE, Throw.STRIKE);
		when(this.throwFinder.findNextTwoThrows(frame)).thenReturn(nextTwoThrows);

		FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);

		assertThat(frameScore.getTotal(), is(30));
	}

	@Test
	public void testScoreWithStrikesAndSpares() {
		Frame frame = mock(Frame.class);
		when(frame.getFirstThrow()).thenReturn(Throw.STRIKE);
		
		List<Throw> nextTwoThrows = Arrays.asList(Throw.STRIKE, Throw.EIGHT);
		when(this.throwFinder.findNextTwoThrows(frame)).thenReturn(nextTwoThrows);

		FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);

		assertThat(frameScore.getTotal(), is(28));
	}

	
	@Test
	public void testScoreWithStrikesAndSparesSpareWithStrike() {
		Frame frame = mock(Frame.class);
		when(frame.getFirstThrow()).thenReturn(Throw.ONE);
		when(frame.getSecondThrow()).thenReturn(Throw.SPARE);
		
		when(this.throwFinder.findNextThrow(frame)).thenReturn(Throw.STRIKE);

		FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);

		assertThat(frameScore.getTotal(), is(20));
	}

	@Test
	public void testScoreWithStrikesAndSparesSpareWithNumber() {
		Frame frame = mock(Frame.class);
		when(frame.getFirstThrow()).thenReturn(Throw.ONE);
		when(frame.getSecondThrow()).thenReturn(Throw.SPARE);
		
		when(this.throwFinder.findNextThrow(frame)).thenReturn(Throw.NINE);

		FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);

		assertThat(frameScore.getTotal(), is(19));
	}

	@Test
	public void testScoreWithStrikesAndSparesNoMark() {
		Frame frame = mock(Frame.class);
		when(frame.getFirstThrow()).thenReturn(Throw.ONE);
		when(frame.getSecondThrow()).thenReturn(Throw.TWO);
		
		FrameScore frameScore = this.frameScorer.scoreCountingStrikesAndSpares(frame);

		assertThat(frameScore.getTotal(), is(3));
	}

}
