package com.sam.tictactoe;

import java.util.List;

public class WinChecker {
	private GameBoard _gameBoard;
	private CellNeighborsFinder _cellNeighborsFinder;

	public WinChecker(GameBoard gameBoard, CellNeighborsFinder cellNeighborsFinder) {
		_gameBoard = gameBoard;
		_cellNeighborsFinder = cellNeighborsFinder;
	}

	public boolean playerWon() {
		Cell playerLastMove = _gameBoard.playerLastMove();
		return wonInRow(playerLastMove) || wonInColumn(playerLastMove) || wonInSlant(playerLastMove);
	}
	
	public boolean computerWon() {
		Cell computerLastMove = _gameBoard.computerLastMove();
		if (computerLastMove == null)
			return false;
		
		return wonInRow(computerLastMove) || wonInColumn(computerLastMove) || wonInSlant(computerLastMove);
	}
	
	private boolean wonInRow(Cell lastMove) {
		List<Cell> rowNeighbors = _cellNeighborsFinder.rowNeighborsOf(lastMove);
		return rowNeighbors.get(0).hasValue(lastMove.getValue()) && rowNeighbors.get(1).hasValue(lastMove.getValue());
	}
	
	private boolean wonInColumn(Cell lastMove) {
		List<Cell> columnNeighbors = _cellNeighborsFinder.columnNeighborsOf(lastMove);
		return columnNeighbors.get(0).hasValue(lastMove.getValue()) && columnNeighbors.get(1).hasValue(lastMove.getValue());
	}
	
	private boolean wonInSlant(Cell lastMove) {
		if (lastMove.isInCenter())
			return wonInEqualSlant(lastMove) || wonInNotEqualSlant(lastMove);
		if (lastMove.isInEqualSlant())
			return wonInEqualSlant(lastMove);
		if (lastMove.isInNotEqualSlant())
			return wonInNotEqualSlant(lastMove);
		
		return false;
	}
	
	private boolean wonInEqualSlant(Cell lastMove) {
		List<Cell> equalSlantNeighbors = _cellNeighborsFinder.neighborsInEqualSlant(lastMove);
		return equalSlantNeighbors.get(0).hasValue(lastMove.getValue()) && 
			equalSlantNeighbors.get(1).hasValue(lastMove.getValue());
	}
	
	private boolean wonInNotEqualSlant(Cell lastMove) {
		List<Cell> equalSlantNeighbors = _cellNeighborsFinder.neighborsInNotEqualSlant(lastMove);
		return equalSlantNeighbors.get(0).hasValue(lastMove.getValue()) && 
			equalSlantNeighbors.get(1).hasValue(lastMove.getValue());
	}
	
}
