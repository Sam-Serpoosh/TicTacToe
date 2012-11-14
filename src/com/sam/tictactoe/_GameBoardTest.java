package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class _GameBoardTest {
	private final static String X = "X";
	private GameBoard _gameBoard;
	
	@Before
	public void setup() {
		_gameBoard = new GameBoard();
	}

	@Test
	public void setupTheEmptyCellsInTheBoard() {
		assertTrue(_gameBoard.isEmpty());
	}
	
	@Test
	public void detectsWhenBoardIsNotEmpty() {
		_gameBoard.fillCell(0, 0, X);
		assertFalse(_gameBoard.isEmpty());
	}
	
	@Test
	public void canResetTheGameBoard() {
		_gameBoard.fillCell(0, 0, X);
		_gameBoard.reset();
		assertTrue(_gameBoard.isEmpty());
	}
	
	@Test
	public void canFillCellWithXOrO() {
		_gameBoard.fillCell(0, 0, X);
		assertEquals(X, _gameBoard.valueOf(0, 0));
	}
	
	@Test
	public void knowsPlayerLastMove() {
		_gameBoard.fillCell(0, 0, X);
		assertEquals(new Move(0, 0, X), _gameBoard.getPlayerLastMove());
	}
	
}
