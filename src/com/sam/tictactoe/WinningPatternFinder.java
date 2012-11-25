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
		return getPotentialWinningCells().size() != 0;
	}
	
	public List<Cell> getPotentialWinningCells() {
		List<Cell> potentialWins = new ArrayList<Cell>();
		potentialWins.addAll(getPotentialWinningCellsInRow());
		potentialWins.addAll(getPotentialWinningCellsInColumn());
		potentialWins.addAll(getPotentialWinningCellsInSlant());
		
		return potentialWins;
	}

	public List<Cell> getPotentialWinningCellsInRow() {
		List<Cell> neighbors = _cellNeighborsFinder.getRowNeighborsOf(_gameBoard.getPlayerLastMove());
		return getPotentialWinnignCells(neighbors);
	}
	
	public List<Cell> getPotentialWinningCellsInColumn() {
		List<Cell> neighbors = _cellNeighborsFinder.getColumnNeighbors(_gameBoard.getPlayerLastMove());
		return getPotentialWinnignCells(neighbors);
	}
	
	public List<Cell> getPotentialWinningCellsInSlant() {
		Cell playerLastMove = _gameBoard.getPlayerLastMove();
		List<Cell> potentialWinningCells = new ArrayList<Cell>();
		if (playerLastMove.isInCenter()) {
			List<Cell> neighbors = _cellNeighborsFinder.getNeighborsInEqualSlant(playerLastMove);
			potentialWinningCells.addAll(getPotentialWinnignCells(neighbors));
			neighbors = _cellNeighborsFinder.getNeighborsOfNotEqualSlant(playerLastMove);
			potentialWinningCells.addAll(getPotentialWinnignCells(neighbors));
		}
		else {
			if (playerLastMove.isInEqualSlant()) {
				List<Cell> neighbors = _cellNeighborsFinder.getNeighborsInEqualSlant(playerLastMove);
				potentialWinningCells.addAll(getPotentialWinnignCells(neighbors));
			}
			else if (playerLastMove.isInNotEqualSlant()) {
				List<Cell> neighbors = _cellNeighborsFinder.getNeighborsOfNotEqualSlant(playerLastMove);
				potentialWinningCells.addAll(getPotentialWinnignCells(neighbors));
			}
		}
		
		return potentialWinningCells;
	}
	
	public List<Cell> getEmptyCells() {
		return _gameBoard.getEmptyCells();
	}
	
	private List<Cell> getPotentialWinnignCells(List<Cell> neighbors) {
		List<Cell> winningCells = new ArrayList<Cell>();
		if (neighborsAreEmpty(neighbors) || neighborsFilledWithOpponent(neighbors))
			return winningCells;
		winningCells.add(neighborsWinningPotentialCell(neighbors));
		
		return winningCells;
	}

	private Cell neighborsWinningPotentialCell(List<Cell> neighbors) {
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
