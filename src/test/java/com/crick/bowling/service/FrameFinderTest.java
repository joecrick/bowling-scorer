package com.crick.bowling.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;
import com.crick.bowling.model.Throw;
import com.crick.bowling.util.TestGames;

/**
 * Test class for {@link FrameFinder}
 * 
 * @author jcrick
 */
public class FrameFinderTest {
	
	private FrameFinder frameFinder;
	
	@BeforeEach
	public void setup() {
		this.frameFinder = new FrameFinder();
	}
	
	@Test
	public void testFindFrame() {
		Game game = TestGames.generate235();
		Frame frame = this.frameFinder.findFrame(game, 7);
		
		assertEquals(Throw.EIGHT, frame.getFirstThrow());
		assertEquals(Throw.SPARE, frame.getSecondThrow());
	}

	@Test
	public void testLessThanOne() {
		Game game = TestGames.generate235();
		Frame frame = this.frameFinder.findFrame(game, -100);
		
		assertEquals(game.getFirstFrame(), frame);
	}

	@Test
	public void testOutOfBounds() {
		Game game = TestGames.generate235();
		Frame frame = this.frameFinder.findFrame(game, 100);

		assertThat(frame, CoreMatchers.instanceOf(FinalFrame.class));
	}

	@Test
	public void testGetFinalFrame() {
		Game game = TestGames.generate235();
		FinalFrame frame = this.frameFinder.getFinalFrame(game);

		assertNotNull(frame);		
	}
}
