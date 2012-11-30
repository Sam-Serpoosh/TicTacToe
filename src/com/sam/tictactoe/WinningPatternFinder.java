package com.sam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class WinningPatternFinder {
	private GameBoard _gameBoard;
	private CellNeighborsFinder _cellNeighborsFinder;

	public WinningPatternFinder(GameBoard gameBoard) {
		_gameBoard = gameBoard;
		_cellNeighborsFinder = new CellNeighborsFinder(_gameBoard);
	}
	
	public boolean anyPotentialWinningCells() {
		return potentialWinningCells().size() != 0;
	}
	
	public List<Cell> potentialWinningCells() {
		List<Cell> potentialWins = new ArrayList<Cell>();
		potentialWins.addAll(potentialWinningCellsInRow());
		potentialWins.addAll(potentialWinningCellsInColumn());
		potentialWins.addAll(potentialWinningCellsInSlant());
		
		return potentialWins;
	}

	public List<Cell> potentialWinningCellsInRow() {
		List<Cell> neighbors = _cellNeighborsFinder.rowNeighborsOf(_gameBoard.playerLastMove());
		return potentialWinnignCellsIn(neighbors);
	}
	
	public List<Cell> potentialWinningCellsInColumn() {
		List<Cell> neighbors = _cellNeighborsFinder.columnNeighbors(_gameBoard.playerLastMove());
		return potentialWinnignCellsIn(neighbors);
	}
	
	public List<Cell> potentialWinningCellsInSlant() {
		Cell playerLastMove = _gameBoard.playerLastMove();
		if (playerLastMove.isInCenter())
			return potentialWinningCellsForCentralCell(playerLastMove);
		if (playerLastMove.isInEqualSlant())
			return potentialWinningCellsInEqualSlant(playerLastMove);
		
		return potentialWinningCellsInNotEqualSlant(playerLastMove);
	}

	private List<Cell> potentialWinningCellsInNotEqualSlant(Cell playerLastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.neighborsInNotEqualSlant(playerLastMove);
		return potentialWinnignCellsIn(neighbors);
	}

	private List<Cell> potentialWinningCellsInEqualSlant(Cell playerLastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.neighborsInEqualSlant(playerLastMove);
		return potentialWinnignCellsIn(neighbors);
	}

	private List<Cell> potentialWinningCellsForCentralCell(Cell playerLastMove) {
		List<Cell> potentialWinningCells = new ArrayList<Cell>();
		potentialWinningCells.addAll(potentialWinningCellsInEqualSlant(playerLastMove));
		potentialWinningCells.addAll(potentialWinningCellsInNotEqualSlant(playerLastMove));
		
		return potentialWinningCells;
	}
	
	public List<Cell> emptyCells() {
		return _gameBoard.emptyCells();
	}
	
	private List<Cell> potentialWinnignCellsIn(List<Cell> neighbors) {
		List<Cell> winningCells = new ArrayList<Cell>();
		if (neighborsAreEmpty(neighbors) || neighborsFilledWithOpponent(neighbors))
			return winningCells;
		winningCells.add(potentialWinningCell(neighbors));
		
		return winningCells;
	}

	private Cell potentialWinningCell(List<Cell> neighbors) {
		if (neighbors.get(0).hasValue(PlayerMoves.X))
			return neighbors.get(1);
		
		return neighbors.get(0);
	}

	private boolean neighborsAreEmpty(List<Cell> neighbors) {
		for (Cell cell : neighbors)
			if (!cell.isEmpty())
				return false;
		return true;
	}
	
	private boolean neighborsFilledWithOpponent(List<Cell> neighbors) {
		return neighbors.get(0).hasValue(PlayerMoves.O) || neighbors.get(1).hasValue(PlayerMoves.O);
	}
	
}
