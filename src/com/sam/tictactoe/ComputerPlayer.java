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
	
	public void fillTheBestAvailableCell() {
		if (centerIsEmpty())
			fillCenter();
		else if (playerLastMoveWasAtCorner() && anyAvailableAdjacentCellToCenter()) {
			fillAnAdjacentCell();
		}
		else if (anyAvailableCornerCells())
			fillCorner();
		else
			fillAnEmptyCellRandomly();
	}
	
	private boolean playerLastMoveWasAtCorner() {
		return _gameBoard.playerLastMove().isInCorner();
	}
	
	private void fillAnAdjacentCell() {
		List<Cell> adjacentCellsToCenter = _gameBoard.adjacentCellsOfCentralCellInRowAndColumn();
		int cellSelector = _random.nextInt(adjacentCellsToCenter.size());
		Cell adjacentCellToCenter = adjacentCellsToCenter.get(cellSelector);
		_gameBoard.fillCell(adjacentCellToCenter.getRow(), adjacentCellToCenter.getColumn(), PlayerMoves.O);
	}

	private boolean centerIsEmpty() {
		return _gameBoard.cellAt(1, 1).isEmpty();
	}
	
	private void fillCenter() {
		_gameBoard.fillCell(1, 1, PlayerMoves.O);
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
	
	private boolean anyAvailableAdjacentCellToCenter() {
		List<Cell> adjacentCellsToCenter = _gameBoard.adjacentCellsOfCentralCellInRowAndColumn();
		return adjacentCellsToCenter.size() > 0;
	}
	
	private boolean anyAvailableCornerCells() {
		List<Cell> availableCorners = _gameBoard.availableCorners(); 
		return availableCorners.size() > 0;
	}
	
	private void fillCorner() {
		List<Cell> availableCorners = _gameBoard.availableCorners();
		int cellSelector = _random.nextInt(availableCorners.size());
		Cell cornerCell = availableCorners.get(cellSelector);
		_gameBoard.fillCell(cornerCell.getRow(), cornerCell.getColumn(), PlayerMoves.O);
	}

	private void blockPotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForPlayer();
		Cell potentialCellForPlayer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForPlayer.getRow(), potentialCellForPlayer.getColumn(), PlayerMoves.O);
	}
	
	private void usePotentialWin() {
		List<Cell> potentialWinningCells = _patternFinder.potentialWinningCellsForComputer();
		Cell potentialCellForComputer = potentialWinningCells.get(0); 
		_gameBoard.fillCell(potentialCellForComputer.getRow(), potentialCellForComputer.getColumn(), PlayerMoves.O);
	}
	
	private void fillAnEmptyCellRandomly() {
		List<Cell> emptyCells = _patternFinder.emptyCells();
		if (emptyCells.size() == 0)
			return;
		int selectedCellPosition = _random.nextInt(emptyCells.size());
		Cell selectedCell = emptyCells.get(selectedCellPosition);
		_gameBoard.fillCell(selectedCell.getRow(), selectedCell.getColumn(), PlayerMoves.O);		
	}
	
}
