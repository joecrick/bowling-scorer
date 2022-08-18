package com.crick.bowling.out;

import com.crick.bowling.model.Frame;

public class SystemOutFramePrinter {

	public void print(Frame frame) {
		System.out.println("--------");
		System.out.print("| " + frame.getFirstThrow().getSymbol() + " " + frame.getSecondThrow().getSymbol());
		System.out.println(" |");
		System.out.println("-------");
	}
	
}
