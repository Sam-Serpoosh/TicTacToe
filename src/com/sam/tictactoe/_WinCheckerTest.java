package com.sam.tictactoe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class _WinCheckerTest {
	
	private GameBoard _gameBoard;
	private WinChecker _winningChecker;
	
	@Before
	public void setup() {
		_gameBoard = new GameBoard();
		CellNeighborsFinder cellNeighborsFinder = new CellNeighborsFinder(_gameBoard);
		_winningChecker = new WinChecker(_gameBoard, cellNeighborsFinder);
	}
	
	@Test
	public void knowsWhenThePlayerWinsInRow() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		
		assertTrue(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThePlayerDoesNotWinInRow() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		
		assertFalse(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenPlayerWinsInColumn() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 0, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		
		assertTrue(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThePlayerDoesNotWinInColumn() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 0, PlayerMoves.X);
		
		assertFalse(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThenPlayerWinsInEqualSlant() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		_gameBoard.fillCell(2, 2, PlayerMoves.X);
		
		assertTrue(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThenPlayerDoesNotWinsInEqualSlant() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 2, PlayerMoves.X);
		_gameBoard.fillCell(2, 2, PlayerMoves.X);
		
		assertFalse(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThenPlayerWinsInNotEqualSlant() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		
		assertTrue(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThenPlayerDoesNotWinsInNotEqualSlant() {
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		
		assertFalse(_winningChecker.playerWon());
	}
	
	@Test
	public void knowsWhenThePlayerWinsInCentralCell() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		
		assertTrue(_winningChecker.playerWon());
	}
	
}
