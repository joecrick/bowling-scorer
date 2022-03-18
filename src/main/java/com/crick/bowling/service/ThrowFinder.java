package com.crick.bowling.service;

import java.util.ArrayList;
import java.util.List;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Throw;

public class ThrowFinder {
	
	public Throw findNextThrow(Frame frame) {
		if(frame != null && frame.getNextFrame() != null) {
			return frame.getNextFrame().getFirstThrow();
		}
		return Throw.NOT_THROWN;
	}
	
	public Throw findSecondNextThrow(Frame frame) {
		Throw nextThrow = this.findNextThrow(frame);
		if(nextThrow != Throw.NOT_THROWN) {
			if(nextThrow == Throw.STRIKE && !(frame.getNextFrame() instanceof FinalFrame)) {
				return this.findNextThrow(frame.getNextFrame());
			}
			return frame.getNextFrame().getSecondThrow();
		}
		return Throw.NOT_THROWN;
	}

	public List<Throw> findNextTwoThrows(Frame frame) {
		List<Throw> nextThrows = new ArrayList<>(2);
		nextThrows.add(this.findNextThrow(frame));
		nextThrows.add(this.findSecondNextThrow(frame));
		return nextThrows;
	}

}
