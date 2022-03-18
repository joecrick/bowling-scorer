package com.crick.bowling.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.ScoreFactory;

@ExtendWith(MockitoExtension.class)
public class FinalFrameScorerTest {
	

	@Mock
	private ScoreFactory scoreFactory;
	
	private FinalFrameScorer finalFrameScorer;
	
	@BeforeEach
	public void setup() {
		this.finalFrameScorer = new FinalFrameScorer(this.scoreFactory);
		when(this.scoreFactory.newFrameScore()).thenReturn(new FrameScore());
	}
	
	@Test
	public void testScoreAllStrikes() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.setFirstThrow(Throw.STRIKE);
		finalFrame.setSecondThrow(Throw.STRIKE);
		finalFrame.setThirdThrow(Throw.STRIKE);
		
		FrameScore result = this.finalFrameScorer.score(finalFrame);
		
		assertEquals(30, result.getTotal());
	}

	@Test
	public void testScoreASpare() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.setFirstThrow(Throw.NINE);
		finalFrame.setSecondThrow(Throw.SPARE);
		finalFrame.setThirdThrow(Throw.STRIKE);
		
		FrameScore result = this.finalFrameScorer.score(finalFrame);
		
		assertEquals(20, result.getTotal());
	}

	@Test
	public void testScoreASpareNoStrike() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.setFirstThrow(Throw.NINE);
		finalFrame.setSecondThrow(Throw.SPARE);
		finalFrame.setThirdThrow(Throw.SEVEN);
		
		FrameScore result = this.finalFrameScorer.score(finalFrame);
		
		assertEquals(17, result.getTotal());
	}

	@Test
	public void testScoreNoSpareOrStrike() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.setFirstThrow(Throw.NINE);
		finalFrame.setSecondThrow(Throw.GUTTER);
		finalFrame.setThirdThrow(Throw.GUTTER);
		
		FrameScore result = this.finalFrameScorer.score(finalFrame);
		
		assertEquals(9, result.getTotal());
	}

	@Test
	public void testScoreShouldNotCountThirdThrowIfOpen() {
		
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.setFirstThrow(Throw.NINE);
		finalFrame.setSecondThrow(Throw.GUTTER);
		finalFrame.setThirdThrow(Throw.STRIKE);
		
		FrameScore result = this.finalFrameScorer.score(finalFrame);
		
		assertEquals(9, result.getTotal());
	}

}
