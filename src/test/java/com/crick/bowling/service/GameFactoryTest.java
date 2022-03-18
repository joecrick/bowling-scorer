package com.crick.bowling.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;

/**
 * Test class for {@link GameFactory}
 * 
 * @author jcrick
 */
@ExtendWith(MockitoExtension.class)
public class GameFactoryTest {

	private GameFactory gameFactory;

	@BeforeEach
	public void setup() {
		this.gameFactory = new GameFactory();
	}

	@Test
	public void testCreateNewGame() {
		Game game = this.gameFactory.createNewGame();

		int frameCount = 1;
		Frame frame = game.getFirstFrame();
		while(frame.getNextFrame() != null) {
			frameCount++;
			frame = frame.getNextFrame();
		}

		assertEquals(10, frameCount);
		assertThat(frame, CoreMatchers.instanceOf(FinalFrame.class));
	}

}
