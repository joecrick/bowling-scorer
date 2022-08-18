package com.crick.bowling.service;

import com.crick.bowling.model.FinalFrame;
import com.crick.bowling.model.Frame;
import com.crick.bowling.model.Game;

/**
 * Class used to find frames in a game.  Since we're using more of a linked list type for the game, this can be difficult since we only know the head (first frame).
 * 
 * Also breaking this logic out of {@link Game} to keep it small.
 * 
 * @author jcrick
 */
public class FrameFinder {

	/**
	 * Returns the given frame.
	 * 
	 * @param game - the game to get the frame for
	 * @param frameNumber - the frame number to search for
	 * @return the given frame.  Will return the first frame for negative or 0, and will return the last frame if out of bounds.
	 */
	public Frame findFrame(Game game, int frameNumber) {
		Frame frame = game.getFirstFrame();
		
		if(frameNumber <= 1) {
			return frame;
		}

		int count = 2;
		while(frame.getNextFrame() != null) {
			frame = frame.getNextFrame();
			if(count == frameNumber) {
				return frame;
			}
			count++;
		}
		return frame;
	}

	/**
	 * Get the final frame.  Assumes the final frame is always a {@link FinalFrame}.
	 * 
	 * @param game - the game to get the final frame for
	 * @return the final frame
	 */
	public FinalFrame getFinalFrame(Game game) {
		Frame frame = game.getFirstFrame();
		while(frame.getNextFrame() != null) {
			frame = frame.getNextFrame();
		}
		return (FinalFrame)frame;
	}

	public Frame findPreviousFrame(Game game, Frame searchFrame) {
		Frame frame = game.getFirstFrame();

		while(frame.getNextFrame() != null) {
			if(frame.getNextFrame() == searchFrame) {
				return frame;
			}
			frame = frame.getNextFrame();
		}
		
		return null;
	}
	
}
