package com.crick.bowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Throw;

@ExtendWith(MockitoExtension.class)
public class ThrowFinderTest {

	private ThrowFinder throwFinder;
	
	@BeforeEach
	public void setup() {
		this.throwFinder = new ThrowFinder();
	}
	
	@Test
	public void testFindNextThrow() {
		Frame frame = Mockito.mock(Frame.class);
		
		Frame nextFrame = Mockito.mock(Frame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.STRIKE);
		when(frame.getNextFrame()).thenReturn(nextFrame);
		
		Throw nextThrow = this.throwFinder.findNextThrow(frame);
		
		assertEquals(Throw.STRIKE, nextThrow);
	}

	@Test
	public void testFindNextThrowNull() {
		Throw nextThrow = this.throwFinder.findNextThrow(null);
		
		assertEquals(Throw.NOT_THROWN, nextThrow);
	}

	@Test
	public void testFindNextThrowNextFrameNull() {
		Frame frame = Mockito.mock(Frame.class);
		when(frame.getNextFrame()).thenReturn(null);
		
		Throw nextThrow = this.throwFinder.findNextThrow(frame);
		
		assertEquals(Throw.NOT_THROWN, nextThrow);
	}

	@Test
	public void testFindSecondNextThrowNonStrike() {
		Frame frame = Mockito.mock(Frame.class);

		Frame nextFrame = Mockito.mock(Frame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.EIGHT);
		when(nextFrame.getSecondThrow()).thenReturn(Throw.SPARE);
		when(frame.getNextFrame()).thenReturn(nextFrame);

		Throw nextThrow = this.throwFinder.findSecondNextThrow(frame);

		assertEquals(Throw.SPARE, nextThrow);
	}

	@Test
	public void testFindSecondNextThrowStrike() {
		Frame frame = Mockito.mock(Frame.class);

		Frame nextFrame = Mockito.mock(Frame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.STRIKE);
		when(frame.getNextFrame()).thenReturn(nextFrame);
		
		Frame secondNextFrame = Mockito.mock(Frame.class);
		when(secondNextFrame.getFirstThrow()).thenReturn(Throw.EIGHT);
		when(nextFrame.getNextFrame()).thenReturn(secondNextFrame);

		Throw nextThrow = this.throwFinder.findSecondNextThrow(frame);

		assertEquals(Throw.EIGHT, nextThrow);
	}
	
	@Test
	public void testFindSecondNextThrowForFinalFrame() {
		Frame frame = Mockito.mock(Frame.class);

		FinalFrame nextFrame = Mockito.mock(FinalFrame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.STRIKE);
		when(nextFrame.getSecondThrow()).thenReturn(Throw.FIVE);
		
		when(frame.getNextFrame()).thenReturn(nextFrame);
		
		Throw nextThrow = this.throwFinder.findSecondNextThrow(frame);

		assertEquals(Throw.FIVE, nextThrow);		
	}
	
	@Test
	public void testFindSecondNextThrowNextFrameNull() {
		Frame frame = Mockito.mock(Frame.class);

		when(frame.getNextFrame()).thenReturn(null);

		Throw nextThrow = this.throwFinder.findSecondNextThrow(frame);

		assertEquals(Throw.NOT_THROWN, nextThrow);
	}
	
	@Test
	public void testFindNextTwoThrowsNoStrike() {
		Frame frame = Mockito.mock(Frame.class);

		Frame nextFrame = Mockito.mock(Frame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.EIGHT);
		when(nextFrame.getSecondThrow()).thenReturn(Throw.SPARE);
		when(frame.getNextFrame()).thenReturn(nextFrame);

		List<Throw> nextThrows = this.throwFinder.findNextTwoThrows(frame);

		assertEquals(Throw.EIGHT, nextThrows.get(0));
		assertEquals(Throw.SPARE, nextThrows.get(1));
	}
	
	@Test
	public void testFindNextTwoThrowsStrike() {
		Frame frame = Mockito.mock(Frame.class);

		Frame nextFrame = Mockito.mock(Frame.class);
		when(nextFrame.getFirstThrow()).thenReturn(Throw.STRIKE);
		when(frame.getNextFrame()).thenReturn(nextFrame);
		
		Frame secondNextFrame = Mockito.mock(Frame.class);
		when(secondNextFrame.getFirstThrow()).thenReturn(Throw.EIGHT);
		when(nextFrame.getNextFrame()).thenReturn(secondNextFrame);

		List<Throw> nextThrows = this.throwFinder.findNextTwoThrows(frame);

		assertEquals(Throw.STRIKE, nextThrows.get(0));
		assertEquals(Throw.EIGHT, nextThrows.get(1));	
	}

}
