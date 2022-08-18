package com.crick.bowling.util;

import com.crick.bowling.model.Game;
import com.crick.bowling.model.Throw;
import com.crick.bowling.service.GameBuilder;
import com.crick.bowling.service.GameFactory;

public class TestGames {
	
	private TestGames() {
		
	}
	
	public static Game generate300() {
		return new GameBuilder(new GameFactory())
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
	}

	public static Game generate0() {
		return new GameBuilder(new GameFactory())
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.GUTTER)
			.finish();
	}
	
	public static Game generate235() {
		return new GameBuilder(new GameFactory())
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.EIGHT)
			.addThrow(Throw.SPARE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.finish();
	}

	public static Game generate267() {
		return new GameBuilder(new GameFactory())
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.NINE)
			.addThrow(Throw.GUTTER)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.addThrow(Throw.STRIKE)
			.finish();
	}
}
