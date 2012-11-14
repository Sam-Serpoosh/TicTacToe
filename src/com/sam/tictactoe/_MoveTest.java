package com.sam.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class _MoveTest {
	private final static String X = "X";

	@Test
	public void setsCoordinatesAtCreation() {
		Move move = new Move(0, 1, X);
		assertEquals(0, move.X);
		assertEquals(1, move.Y);
	}
	
	@Test
	public void setsValueOfMoveAtCreation() {
		Move move = new Move(0, 0, X);
		assertEquals(X, move.Value);
	}
	
	@Test
	public void eqaulsWhenCoordinatesAndValueAreEqual() {
		Move move1 = new Move(0, 0, X);
		Move move2 = new Move(0, 0, X);
		
		assertEquals(move1, move2);
	}
	
	@Test
	public void toStringTest() {
		Move move = new Move(0, 0, X);
		assertEquals("0, 0, X", move.toString());
	}
	
}
