package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class _ComputerPlayerTest {
	private WinningPatternFinder _patternFinder;
	private ComputerPlayer _computerPlayer;
	private GameBoard _gameBoard;
	
	@Before
	public void setup() {
		_patternFinder = mock(WinningPatternFinder.class);
		_gameBoard = new GameBoard();
		_computerPlayer = new ComputerPlayer(_patternFinder, _gameBoard);
	}

	@Test
	public void blocksThePotentialWinningCellForPlayer() {
		Cell potentialWinCell = new Cell(0, 0);
		List<Cell> winningPotentialCells = new ArrayList<Cell>();
		winningPotentialCells.add(potentialWinCell);
		when(_patternFinder.anyPotentialWinningCellsForPlayer()).thenReturn(true);
		when(_patternFinder.potentialWinningCellsForPlayer()).thenReturn(winningPotentialCells);
		_computerPlayer.play();

		assertEquals(PlayerMoves.O, _gameBoard.valueOf(potentialWinCell.getRow(), potentialWinCell.getColumn()));
	}
	
	@Test
	public void fillTheCentralCellIfPlayerDidNotFillItAlready() {
		_gameBoard.fillCell(0, 0, PlayerMoves.X);
		_computerPlayer.fillTheBestAvailableCell();
		assertEquals(PlayerMoves.O, _gameBoard.valueOf(1, 1));
	}
	
	@Test
	public void fillAdjacentCellToCenterWhenCenterIsFilledByComputerAndPlayerLastMoveIsAtCorner() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(2, 0, PlayerMoves.X);
		
		_computerPlayer.fillTheBestAvailableCell();
		computerShouldFillOneAdjacentOfCenter();
	}
	
	@Test
	public void fillOneAvailableCornerWhenPlayerFilledTheCentralCell() {
		_gameBoard.fillCell(1, 1, PlayerMoves.X);
		_computerPlayer.fillTheBestAvailableCell();
		computerShouldFillOneCorner();
	}
	
	@Test
	public void fillCornerCellWhichIsInTheRowOfPlayerLastMove() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(2, 1, PlayerMoves.X);
		
		_computerPlayer.fillTheBestAvailableCell();
		
		assertTrue(_gameBoard.cellAt(2, 0).hasValue(PlayerMoves.O) || 
				   _gameBoard.cellAt(2, 2).hasValue(PlayerMoves.O));
		
	}
	
	@Test
	public void fillCornerCellWhichIsInTheColumnOfPlayerLastMove() {
		_gameBoard.fillCell(0, 2, PlayerMoves.X);
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
		_gameBoard.fillCell(1, 0, PlayerMoves.X);
		
		_computerPlayer.fillTheBestAvailableCell();
		
		assertTrue(_gameBoard.cellAt(0, 0).hasValue(PlayerMoves.O) || 
				   _gameBoard.cellAt(2, 0).hasValue(PlayerMoves.O));
		
	}
	
	private void computerShouldFillOneAdjacentOfCenter() {
		boolean oneAdjacentOfCenterIsFilled = _gameBoard.cellAt(1, 0).hasValue(PlayerMoves.O) || 
											  _gameBoard.cellAt(1, 2).hasValue(PlayerMoves.O) ||
											  _gameBoard.cellAt(0, 1).hasValue(PlayerMoves.O) ||
											  _gameBoard.cellAt(2, 1).hasValue(PlayerMoves.O);
		assertTrue(oneAdjacentOfCenterIsFilled);
	}
	
	private void computerShouldFillOneCorner() {
		boolean oneCornerIsFilled = _gameBoard.cellAt(0, 0).hasValue(PlayerMoves.O) ||
									_gameBoard.cellAt(0, 2).hasValue(PlayerMoves.O) ||
									_gameBoard.cellAt(2, 0).hasValue(PlayerMoves.O) ||
									_gameBoard.cellAt(2, 2).hasValue(PlayerMoves.O);
		assertTrue(oneCornerIsFilled);
	}
	
}
