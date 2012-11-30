package com.sam.tictactoe;

import java.util.List;
import java.util.Random;

public class ComputerPlayer {
	private WinningPatternFinder _patternFinder;
	private Random _random;
	
	public ComputerPlayer(WinningPatternFinder patternFinder) {
		_patternFinder = patternFinder;
		_random = new Random();
	}

	public void play() {
		if (_patternFinder.anyPotentialWinningCells())
			blockPotentialWin();
		else
			playRandomMove();
	}

	private void blockPotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCells();
		potentialWinningCells.get(0).fill(PlayerMoves.O);
	}
	
	private void playRandomMove() { //TODO: This will change completely!
		List<Cell> emptyCells = _patternFinder.emptyCells();
		if (emptyCells.size() == 0)
			return;
		int selectedCellPosition = _random.nextInt(emptyCells.size());
		emptyCells.get(selectedCellPosition).fill(PlayerMoves.O);
	}
	
}
