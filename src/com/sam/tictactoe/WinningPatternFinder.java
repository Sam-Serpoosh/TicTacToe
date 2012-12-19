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
	
	public boolean anyPotentialWinningCellsForPlayer() {
		return potentialWinningCellsForPlayer().size() != 0;
	}
	
	public boolean anyPotentialWinningCellsForComputer() {
		return potentialWinningCellsForComputer().size() != 0;
	}
	
	public List<Cell> potentialWinningCellsForPlayer() {
		Cell playerLastMove = _gameBoard.playerLastMove();
		return potentialWinningCellsBasedOn(playerLastMove);
	}
	
	public List<Cell> potentialWinningCellsForComputer() {
		Cell computerLastMove = _gameBoard.computerLastMove();
		if (computerLastMove == null)
			return new ArrayList<Cell>();
		
		return potentialWinningCellsBasedOn(computerLastMove);
	}
	
	private List<Cell> potentialWinningCellsBasedOn(Cell lastMove) {
		List<Cell> potentialWins = new ArrayList<Cell>();
		potentialWins.addAll(potentialWinningCellsInRowBasedOn(lastMove));
		potentialWins.addAll(potentialWinningCellsInColumnBasedOn(lastMove));
		potentialWins.addAll(potentialWinningCellsInSlantBasedOn(lastMove));
		
		return potentialWins;
	}

	public List<Cell> potentialWinningCellsInRowBasedOn(Cell lastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.rowNeighborsOf(lastMove);
		return potentialWinnignCellsIn(neighbors, lastMove);
	}
	
	public List<Cell> potentialWinningCellsInColumnBasedOn(Cell lastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.columnNeighborsOf(lastMove);
		return potentialWinnignCellsIn(neighbors, lastMove);
	}
	
	public List<Cell> potentialWinningCellsInSlantBasedOn(Cell lastMove) {
		if (lastMove.isInCenter())
			return potentialWinningCellsForCentralCell(lastMove);
		if (lastMove.isInEqualSlant())
			return potentialWinningCellsInEqualSlant(lastMove);
		
		return potentialWinningCellsInNotEqualSlant(lastMove);
	}

	private List<Cell> potentialWinningCellsInNotEqualSlant(Cell lastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.neighborsInNotEqualSlant(lastMove);
		return potentialWinnignCellsIn(neighbors, lastMove);
	}

	private List<Cell> potentialWinningCellsInEqualSlant(Cell lastMove) {
		List<Cell> neighbors = _cellNeighborsFinder.neighborsInEqualSlant(lastMove);
		return potentialWinnignCellsIn(neighbors, lastMove);
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
	
	private List<Cell> potentialWinnignCellsIn(List<Cell> neighbors, Cell lastMove) {
		List<Cell> winningCells = new ArrayList<Cell>();
		if (neighborsAreEmpty(neighbors) || neighborsFilledWithOpponent(neighbors, lastMove))
			return winningCells;
		winningCells.add(potentialWinningCell(neighbors, lastMove));
		
		return winningCells;
	}

	private Cell potentialWinningCell(List<Cell> neighbors, Cell lastMove) {
		if (neighbors.get(0).hasValue(lastMove.getValue()))
			return neighbors.get(1);
		
		return neighbors.get(0);
	}

	private boolean neighborsAreEmpty(List<Cell> neighbors) {
		for (Cell cell : neighbors)
			if (!cell.isEmpty())
				return false;
		return true;
	}
	
	private boolean neighborsFilledWithOpponent(List<Cell> neighbors, Cell lastMove) {
		boolean filledWithOpponent = false;
		for (Cell neighbor : neighbors)
			filledWithOpponent = filledWithOpponent || !neighbor.isEmpty() && 
				!neighbor.hasValue(lastMove.getValue()); 
			
		return filledWithOpponent;
	}
	
}
