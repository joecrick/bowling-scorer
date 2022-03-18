package com.crick.bowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crick.bowling.model.FrameScore;
import com.crick.bowling.model.GameScore;

/**
 * Test class for {@link ScoreFactory}
 * 
 * @author jcrick
 */
public class ScoreFactoryTest {

	private ScoreFactory scoreFactory;
	
	@BeforeEach
	public void setup() {
		this.scoreFactory = new ScoreFactory();
	}
	
	@Test
	public void testNewFrameScore() {
		FrameScore frameScore = this.scoreFactory.newFrameScore();
		
		assertEquals(0, frameScore.getTotal());
	}
	
	@Test
	public void testNewGameScore() {
		GameScore gameScore = this.scoreFactory.newGameScore();

		assertEquals(0, gameScore.getTotal());
	}

}
