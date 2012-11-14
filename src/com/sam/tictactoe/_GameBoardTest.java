package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		Cell playerMove = new Cell(0, 0);
		playerMove.fill(X);
		assertEquals(playerMove, _gameBoard.getPlayerLastMove());
	}
	
	@Test
	public void getsNeighborsOfCellInTheRow() {
		List<Cell> rowNeighbors = _gameBoard.getRowNeighborsOf(new Cell(0, 1));
		assertEquals(2, rowNeighbors.size());
		assertTrue(rowNeighbors.contains(new Cell(0, 0)));
		assertTrue(rowNeighbors.contains(new Cell(0, 2)));
		assertFalse(rowNeighbors.contains(new Cell(0, 1)));
	}
	
	@Test
	public void getsNeighborsOfCellInColumn() {
		List<Cell> columnNeighbors = _gameBoard.getColumnNeighbors(new Cell(0, 2));
		assertEquals(2, columnNeighbors.size());
		assertTrue(columnNeighbors.contains(new Cell(1, 2)));
		assertTrue(columnNeighbors.contains(new Cell(2, 2)));
		assertFalse(columnNeighbors.contains(new Cell(0, 2)));
	}
	
	@Test
	public void thereIsNoSlantNeighborWhenTheCellIsNotInSlant() {
		List<Cell> slantNeighbors = _gameBoard.getSlantNeighbors(new Cell(0, 1));
		assertEquals(0, slantNeighbors.size());
	}
	
	@Test
	public void getsSlantNeighborsOfCell() {
		List<Cell> slantNeighbors = _gameBoard.getSlantNeighbors(new Cell(1, 1));
		assertEquals(2, slantNeighbors.size());
		assertTrue(slantNeighbors.contains(new Cell(2, 2)));
		assertTrue(slantNeighbors.contains(new Cell(0, 0)));
		assertFalse(slantNeighbors.contains(new Cell(1, 1)));
	}
	
}
