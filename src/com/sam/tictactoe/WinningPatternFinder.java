package com.sam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class WinningPatternFinder {
	private GameBoard _gameBoard;

	public WinningPatternFinder(GameBoard gameBoard) {
		_gameBoard = gameBoard;
	}
	
	public List<Cell> getPotentialWinningCells() {
		List<Cell> potentialWins = new ArrayList<Cell>();
		potentialWins.addAll(getPotentialWinningCellsInRow());
		potentialWins.addAll(getPotentialWinningCellsInColumn());
		potentialWins.addAll(getPotentialWinningCellsInSlant());
		
		return potentialWins;
	}

	public List<Cell> getPotentialWinningCellsInRow() {
		List<Cell> neighbors = _gameBoard.getRowNeighborsOf(_gameBoard.getPlayerLastMove());
		return getPotentialWinningCells(neighbors);
	}
	
	public List<Cell> getPotentialWinningCellsInColumn() {
		List<Cell> neighbors = _gameBoard.getColumnNeighbors(_gameBoard.getPlayerLastMove());
		return getPotentialWinningCells(neighbors);
	}
	
	public List<Cell> getPotentialWinningCellsInSlant() {
		List<Cell> neighbors = _gameBoard.getSlantNeighbors(_gameBoard.getPlayerLastMove());
		return getPotentialWinningCells(neighbors);
	}
	
	private List<Cell> getPotentialWinningCells(List<Cell> neighbors) {
		List<Cell> winningCells = new ArrayList<Cell>();
		if (neighborsAreEmpty(neighbors) || neighborsFilledWithOpponent(neighbors))
			return winningCells;
		if (neighbors.get(0).hasValue(PlayerMoves.X))
			winningCells.add(neighbors.get(1));
		else if (neighbors.get(1).hasValue(PlayerMoves.X))
			winningCells.add(neighbors.get(0));
		
		return winningCells;
	}

	private boolean neighborsAreEmpty(List<Cell> rowNeighbors) {
		return rowNeighbors.get(0).isEmpty() && rowNeighbors.get(1).isEmpty();
	}
	
	private boolean neighborsFilledWithOpponent(List<Cell> neighbors) {
		return neighbors.get(0).hasValue(PlayerMoves.O) || neighbors.get(1).hasValue(PlayerMoves.O);
	}
	
}
