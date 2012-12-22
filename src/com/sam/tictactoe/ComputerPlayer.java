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
		if (_patternFinder.anyPotentialWinningCellsForComputer())
			usePotentialWin();
		else if (_patternFinder.anyPotentialWinningCellsForPlayer())
			blockPotentialWin();
		else
			fillTheBestAvailableCell();
	}
	
	private void usePotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForComputer();
		Cell potentialCellForComputer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForComputer.getRow(), potentialCellForComputer.getColumn(), PlayerMoves.O);
	}
	
	private void blockPotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForPlayer();
		Cell potentialCellForPlayer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForPlayer.getRow(), potentialCellForPlayer.getColumn(), PlayerMoves.O);
	}
	
	public void fillTheBestAvailableCell() {
		if (centerIsEmpty())
			fillCenter();
		else if (appropriateToGoWithAdjacentCellsToCenter())
			fillAnAdjacentCell();
		else if (appropriateToGoWithCornerCells())
			fillCorner();
		else
			fillAnEmptyCellRandomly();
	}
	
	private boolean centerIsEmpty() {
		return _gameBoard.cellAt(1, 1).isEmpty();
	}
	
	private void fillCenter() {
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
	}
	
	private boolean appropriateToGoWithAdjacentCellsToCenter() {
		return centerIsFilledWithComputer() && playerLastMoveWasAtCorner() && anyAvailableAdjacentCellToCenter();
	}
	
	private boolean centerIsFilledWithComputer() {
		return _gameBoard.cellAt(1, 1).hasValue(PlayerMoves.O);
	}
	
	private boolean playerLastMoveWasAtCorner() {
		return _gameBoard.playerLastMove().isInCorner();
	}
	
	private boolean anyAvailableAdjacentCellToCenter() {
		return _gameBoard.adjacentCellsOfCentralCellInRowAndColumn().size() > 0;
	}
	
	private void fillAnAdjacentCell() {
		fillOneCellRandomlyIn(_gameBoard.adjacentCellsOfCentralCellInRowAndColumn());
	}

	private boolean appropriateToGoWithCornerCells() {
		return (anyAvailableCornerCells() && cornerCellInTheSameRowOfPlayerLastMoveExists()) ||
			   (anyAvailableCornerCells() && cornerCellAtTheSameColumnOfPlayerLastMoveExists()) ||
				_gameBoard.onlyCenterCellIsFilledByPlayer();
	}
	
	private boolean anyAvailableCornerCells() {
		return _gameBoard.availableCorners().size() > 0; 
	}
	
	private boolean cornerCellInTheSameRowOfPlayerLastMoveExists() {
		return _gameBoard.cornerCellsInTheRowOf(_gameBoard.playerLastMove()).size() > 0;
	}
	
	private boolean cornerCellAtTheSameColumnOfPlayerLastMoveExists() {
		return _gameBoard.cornerCellsInTheColumnOf(_gameBoard.playerLastMove()).size() > 0;
	}
	
	/**
	 * I'm getting availableCorners twice here! Once in anyAvailableCornerCells and once
	 * in fillAvailableCorner! I can make it once instead of twice but since at the moment 
	 * there's no performance issue and it's not making any problem I didn't do it! Cause 
	 * according to YAGNI I don't need to optimize now! If in future there will be any issue 
	 * cause of heavy computation in this part or something like that which makes performance 
	 * issues then I'll optimize it! Postpone it to the last possible moment! #Agile/XP!
	 * 
	 * Same story for adjacent cells to center!
	 * 
	 */
	
	private void fillCorner() {
		if (_gameBoard.onlyCenterCellIsFilledByPlayer())
			fillCornerAfterPlayerFilledCenter();
		else if (cornerCellInTheSameRowOfPlayerLastMoveExists())
			fillCornerInTheRowOfPlayerLastMove();
		else if (cornerCellAtTheSameColumnOfPlayerLastMoveExists())
			fillCornerInTheColumnOfPlayerLastMove();
	}
	
	private void fillCornerAfterPlayerFilledCenter() {
		fillOneCellRandomlyIn(_gameBoard.availableCorners());
	}
	
	private void fillCornerInTheRowOfPlayerLastMove() {
		fillOneCellRandomlyIn(_gameBoard.cornerCellsInTheRowOf(_gameBoard.playerLastMove()));
	}
	
	private void fillCornerInTheColumnOfPlayerLastMove() {
		fillOneCellRandomlyIn(_gameBoard.cornerCellsInTheColumnOf(_gameBoard.playerLastMove()));
	}

	private void fillAnEmptyCellRandomly() {
		List<Cell> emptyCells = _patternFinder.emptyCells();
		if (emptyCells.size() == 0)
			return;
		fillOneCellRandomlyIn(emptyCells);		
	}
	
	private void fillOneCellRandomlyIn(List<Cell> cells) {
		int cellSelector = _random.nextInt(cells.size());
		Cell selectedCell = cells.get(cellSelector);
		_gameBoard.fillCell(selectedCell.getRow(), selectedCell.getColumn(), PlayerMoves.O);
	}
}
