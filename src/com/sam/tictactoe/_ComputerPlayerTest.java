package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
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

		assertEquals(PlayerMoves.O, _gameBoard.valueOf(potentialWinCell.getX(), potentialWinCell.getY()));
	}
	
}
