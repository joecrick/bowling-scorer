package com.crick.bowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;
import com.crick.bowling.model.Throw;

/**
 * <p>Test class for {@link GameBuilder}.</p>
 * 
 * <p>Using {@link GameFactory} code here to help generate the game.  If it does more than just running a loop to generate frame, then mocking should be introduced.  It's also a sign that GameFactory's methods should probably just be moved to GameBuilder.</p>
 * 
 * @author jcrick
 */
@ExtendWith(MockitoExtension.class)
public class GameBuilderTest {

	private GameBuilder gameBuilder;
	
	@BeforeEach
	public void setup() {
		this.gameBuilder = new GameBuilder(new GameFactory());
	}

	@Test
	public void test300() {
		Game game = this.gameBuilder
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.finish();
		
		Frame frame = game.getFirstFrame();
		while(frame.getNextFrame() != null) {
			frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		}
		this.assertFinalFrame(frame, Throw.STRIKE, Throw.STRIKE, Throw.STRIKE);
	}
	
	@Test
	public void testNoSpareInFinalFrame() {
		Game game = this.gameBuilder
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.FIVE)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.THREE)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.EIGHT)
			.addThrow(Throw.ONE)
			.finish();

		Frame frame = this.assertFrame(game.getFirstFrame(), Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.NINE, Throw.SPARE);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.FIVE, Throw.GUTTER);
		frame = this.assertFrame(frame, Throw.THREE, Throw.SPARE);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		this.assertFinalFrame(frame, Throw.EIGHT, Throw.ONE, Throw.NOT_THROWN);
	}

	@Test
	public void testSpareInFinalFrame() {
		Game game = this.gameBuilder
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.FIVE)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.THREE)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.EIGHT)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.finish();

		Frame frame = this.assertFrame(game.getFirstFrame(), Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.NINE, Throw.SPARE);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.FIVE, Throw.GUTTER);
		frame = this.assertFrame(frame, Throw.THREE, Throw.SPARE);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		frame = this.assertFrame(frame, Throw.STRIKE, Throw.NOT_THROWN);
		this.assertFinalFrame(frame, Throw.EIGHT, Throw.SPARE, Throw.STRIKE);
	}

	@Test
	public void testFinishResets() {
		Game game = this.gameBuilder
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.finish();

		game = this.gameBuilder.finish();

		Frame frame = game.getFirstFrame();
		while(frame.getNextFrame() != null) {
			frame = this.assertFrame(frame, Throw.NOT_THROWN, Throw.NOT_THROWN);
		}
		this.assertFinalFrame(frame, Throw.NOT_THROWN, Throw.NOT_THROWN, Throw.NOT_THROWN);
	}

	private Frame assertFrame(Frame frame, Throw firstThrow, Throw secondThrow) {
		assertEquals(firstThrow, frame.getFirstThrow());
		assertEquals(secondThrow, frame.getSecondThrow());
		return frame.getNextFrame();
	}
	
	private void assertFinalFrame(Frame frame, Throw firstThrow, Throw secondThrow, Throw thirdThrow) {
		FinalFrame finalFrame = (FinalFrame)frame;
		assertEquals(firstThrow, finalFrame.getFirstThrow());
		assertEquals(secondThrow, finalFrame.getSecondThrow());
		assertEquals(thirdThrow, finalFrame.getThirdThrow());				
	}
}
