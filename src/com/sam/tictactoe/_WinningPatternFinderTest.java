package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class _WinningPatternFinderTest {
	private GameBoard _gameBoard;
	private WinningPatternFinder _patternFinder;
	
	@Before
	public void setup() {
		_gameBoard = new GameBoard();
		_patternFinder = new WinningPatternFinder(_gameBoard);
	}
	
	@Test
	public void whenRowNeighborsOfCellAreEmptyThereIsNoWinningPotential() {
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		List<Cell> potentialWinsInRow = _patternFinder.potentialWinningCellsInRowBasedOn(_gameBoard.playerLastMove());
		assertEquals(0, potentialWinsInRow.size());
	}
	
	@Test
	public void whenRowNeighborsOfCellFilledWihtOpponentThereIsNoWinningPotential() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(0, 1, PlayerMoves.O);
		List<Cell> potentialWinsInRow = _patternFinder.potentialWinningCellsInRowBasedOn(_gameBoard.playerLastMove());
		assertEquals(0, potentialWinsInRow.size());
	}
	
	@Test
	public void getsThePotentialWinningCellInRow() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		List<Cell> potentialWinsInRow = _patternFinder.potentialWinningCellsInRowBasedOn(_gameBoard.playerLastMove());
		assertEquals(1, potentialWinsInRow.size());
		assertEquals(new Cell(0, 1), potentialWinsInRow.get(0));
	}
	
	@Test
	public void whenColumnNeighborsOfCellAreEmptyThereIsNoWinningPotential() {
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		List<Cell> potentialWinsInColumn = _patternFinder.potentialWinningCellsInColumnBasedOn(_gameBoard.playerLastMove());
		assertEquals(0, potentialWinsInColumn.size());
	}
	
	@Test
	public void whenColumnNeighborsOfCellFilledWithOpponentThereIsNoWinningPotential() {
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		_gameBoard.fillCell(2, 1, PlayerMoves.O);
		List<Cell> potentialWinsInColumn = _patternFinder.potentialWinningCellsInColumnBasedOn(_gameBoard.playerLastMove());
		assertEquals(0, potentialWinsInColumn.size());
	}
	
	@Test
	public void getsThePotentialWinningCellsInColumn() {
		_gameBoard.fillCell(0, 1, PlayerMoves.X);
		_gameBoard.fillCell(2, 1, PlayerMoves.X);
		List<Cell> potentialWinsInColumn = _patternFinder.potentialWinningCellsInColumnBasedOn(_gameBoard.playerLastMove());
		assertEquals(1, potentialWinsInColumn.size());
		assertEquals(new Cell(1, 1), potentialWinsInColumn.get(0));
	}
	
	@Test
	public void whenSlantNeighborsOfCellAreEmptyThereIsNoWinningPotential() {
		_gameBoard.fillCell(1, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 0, PlayerMoves.O);
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		List<Cell> potentialWinsInSlant = _patternFinder.potentialWinningCellsInSlantBasedOn(_gameBoard.playerLastMove());
		assertEquals(0, potentialWinsInSlant.size());
	}
	
	@Test
	public void getsThePotentialWinningCellsInSlant() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(2, 2, PlayerMoves.X);
		List<Cell> potentialWinsInSlant = _patternFinder.potentialWinningCellsInSlantBasedOn(_gameBoard.playerLastMove());
		assertEquals(1, potentialWinsInSlant.size());
		assertEquals(new Cell(1, 1), potentialWinsInSlant.get(0));
	}
	
	@Test
	public void getsThePotentialWinningCellsOfCentralCell() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		List<Cell> potentialWinsInSlant = _patternFinder.potentialWinningCellsInSlantBasedOn(_gameBoard.playerLastMove());
		assertEquals(2, potentialWinsInSlant.size());
		assertTrue(potentialWinsInSlant.contains(new Cell(0, 2)));
		assertTrue(potentialWinsInSlant.contains(new Cell(2, 2)));
	}
	
	@Test
	public void getsThePotentialWinningCellsInSlantOtherWay() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		List<Cell> potentialWinsInSlant = _patternFinder.potentialWinningCellsInSlantBasedOn(_gameBoard.playerLastMove());
		assertEquals(1, potentialWinsInSlant.size());
		assertEquals(new Cell(1, 1), potentialWinsInSlant.get(0));
	}
	
	@Test
	public void whenCellIsNotInSlantThereIsNoPotentialWinningCellsForItInSlant() {
		_gameBoard.fillCell(1, 2, PlayerMoves.O);
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsInSlantBasedOn(_gameBoard.computerLastMove());
		
		assertEquals(0, potentialWinningCells.size());
	}
	
	@Test
	public void getsThePotentialWinningCellsForPlayerAfterTheLastMove() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(1, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 0, PlayerMoves.O);
		_gameBoard.fillCell(2, 2, PlayerMoves.X);
		List<Cell> potentialWins = _patternFinder.potentialWinningCellsForPlayer();
		assertEquals(1, potentialWins.size());
		assertEquals(new Cell(0, 2), potentialWins.get(0));
	}
	
	@Test
	public void getsThePotentialWinningCellsForComputerPlayerAfterTheLastMove() {
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		_gameBoard.fillCell(1, 0, PlayerMoves.O);
		
		List<Cell> potentialWins = _patternFinder.potentialWinningCellsForComputer();
		assertEquals(1, potentialWins.size());
		assertEquals(new Cell(1, 2), potentialWins.get(0));
	}
	
	@Test
	public void knowsWhenThereIsNoPotentialWinningCells() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		assertFalse(_patternFinder.anyPotentialWinningCellsForPlayer());
	}
	
}
