package com.sam.tictactoe;

import java.util.List;
import java.util.Random;

public class ComputerPlayer {
	private WinningPatternFinder _patternFinder;
	private GameBoard _gameBoard;
	private Random _random;
	
	public ComputerPlayer(WinningPatternFinder patternFinder, GameBoard gameBoard) {
		_patternFinder = patternFinder;
		_gameBoard = gameBoard;
		_random = new Random();
	}

	public void play() {
		if (_patternFinder.anyPotentialWinningCellsForPlayer())
			blockPotentialWin();
		else if (_patternFinder.anyPotentialWinningCellsForComputer())
			usePotentialWin();
		else
			playRandomMove();
	}

	private void blockPotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForPlayer();
		Cell potentialCellForPlayer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForPlayer.getX(), potentialCellForPlayer.getY(), PlayerMoves.O);
	}
	
	private void usePotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForComputer();
		Cell potentialCellForComputer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForComputer.getX(), potentialCellForComputer.getY(), PlayerMoves.O);
	}
	
	private void playRandomMove() { //TODO: This will change completely!
		List<Cell> emptyCells = _patternFinder.emptyCells();
		if (emptyCells.size() == 0)
			return;
		int selectedCellPosition = _random.nextInt(emptyCells.size());
		Cell selectedCell = emptyCells.get(selectedCellPosition);
		_gameBoard.fillCell(selectedCell.getX(), selectedCell.getY(), PlayerMoves.O);
	}
	
}
