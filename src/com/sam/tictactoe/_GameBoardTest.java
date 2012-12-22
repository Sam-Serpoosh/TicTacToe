package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class _GameBoardTest {
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
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		assertFalse(_gameBoard.isEmpty());
	}
	
	@Test
	public void canResetTheGameBoard() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.reset();
		assertTrue(_gameBoard.isEmpty());
	}
	
	@Test
	public void knowsWhenTheBoardIsFull() {
		for (int row = 0; row < GameBoard.ROW_COUNT; row++)
			for (int column = 0; column < GameBoard.COLUMN_COUNT; column++)
				_gameBoard.fillCell(row, column, PlayerMoves.X);
		
		assertTrue(_gameBoard.isFull());
	}
	
	@Test
	public void knowsWhenOnleCentralCellFilledByPlayer() {
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		assertTrue(_gameBoard.onlyCenterCellIsFilledByPlayer());
	}
	
	@Test
	public void canFillCellWithXOrO() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		assertEquals(PlayerMoves.X, _gameBoard.valueOf(0, 0));
	}
	
	@Test
	public void knowsPlayerLastMove() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		assertEquals(new Cell(0, 0), _gameBoard.playerLastMove());
	}
	
	@Test
	public void playerLastMoveDoesNotChangeAfterOpponentMove() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(0, 1, PlayerMoves.O);
		assertEquals(new Cell(0, 0), _gameBoard.playerLastMove());
	}
	
	@Test
	public void knowsComputerLastMove() {
		_gameBoard.fillCell(0, 0, PlayerMoves.O);
		assertEquals(new Cell(0, 0), _gameBoard.computerLastMove());
	}
	
	@Test
	public void computerLastMoveDoesNotChangeAfterPlayerMove() {
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		assertEquals(new Cell(1, 1), _gameBoard.computerLastMove());
	}
	
	@Test
	public void getsEmptyCells() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		List<Cell> emptyCells = _gameBoard.emptyCells();
		
		assertEquals(8, emptyCells.size());
		assertFalse(emptyCells.contains(new Cell(0, 0)));
	}
	
	@Test
	public void getsAvailableCornerCells() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(2, 2, PlayerMoves.O);
		
		List<Cell> availableCornerCells = _gameBoard.availableCorners();

		assertEquals(2, availableCornerCells.size());
		assertTrue(availableCornerCells.contains(new Cell(2, 0)));
		assertTrue(availableCornerCells.contains(new Cell(0, 2)));
	}
	
	@Test
	public void getsAdjacentCellsOfCentralCellWhichAreInItsRowAndColumn() {
		List<Cell> adjacentCellsOfCentralCell = _gameBoard.adjacentCellsOfCentralCellInRowAndColumn();
		
		assertEquals(4, adjacentCellsOfCentralCell.size());
		assertTrue(adjacentCellsOfCentralCell.contains(new Cell(1, 0)));
		assertTrue(adjacentCellsOfCentralCell.contains(new Cell(1, 2)));
		assertTrue(adjacentCellsOfCentralCell.contains(new Cell(0, 1)));
		assertTrue(adjacentCellsOfCentralCell.contains(new Cell(2, 1)));
	}
	
}
