package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	
	@Before
	public void setup() {
		_patternFinder = mock(WinningPatternFinder.class);
		_computerPlayer = new ComputerPlayer(_patternFinder);
	}

	@Test
	public void blocksThePotentialWinningCellForPlayer() {
		Cell potentialWinCell = new Cell(0, 0);
		List<Cell> winningPotentialCells = new ArrayList<Cell>();
		winningPotentialCells.add(potentialWinCell);
		when(_patternFinder.anyPotentialWinningCells()).thenReturn(true);
		when(_patternFinder.getPotentialWinningCells()).thenReturn(winningPotentialCells);
		_computerPlayer.play();
		
		assertFalse(potentialWinCell.isEmpty());
		assertTrue(potentialWinCell.hasValue(PlayerMoves.O));
	}
	
}
