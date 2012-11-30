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
	
	private boolean wonInRow(Cell playerLastMove) {
		List<Cell> rowNeighbors = _cellNeighborsFinder.rowNeighborsOf(playerLastMove);
		return rowNeighbors.get(0).hasValue(PlayerMoves.X) && rowNeighbors.get(1).hasValue(PlayerMoves.X);
	}
	
	private boolean wonInColumn(Cell playerLastMove) {
		List<Cell> columnNeighbors = _cellNeighborsFinder.columnNeighbors(playerLastMove);
		return columnNeighbors.get(0).hasValue(PlayerMoves.X) && columnNeighbors.get(1).hasValue(PlayerMoves.X);
	}
	
	private boolean wonInSlant(Cell playerLastMove) {
		if (playerLastMove.isInCenter())
			return wonInEqualSlant(playerLastMove) || wonInNotEqualSlant(playerLastMove);
		if (playerLastMove.isInEqualSlant())
			return wonInEqualSlant(playerLastMove);
		if (playerLastMove.isInNotEqualSlant())
			return wonInNotEqualSlant(playerLastMove);
		
		return false;
	}
	
	private boolean wonInEqualSlant(Cell playerLastMove) {
		List<Cell> equalSlantNeighbors = _cellNeighborsFinder.neighborsInEqualSlant(playerLastMove);
		return equalSlantNeighbors.get(0).hasValue(PlayerMoves.X) && 
			equalSlantNeighbors.get(1).hasValue(PlayerMoves.X);
	}
	
	private boolean wonInNotEqualSlant(Cell playerLastMove) {
		List<Cell> equalSlantNeighbors = _cellNeighborsFinder.neighborsInNotEqualSlant(playerLastMove);
		return equalSlantNeighbors.get(0).hasValue(PlayerMoves.X) && 
			equalSlantNeighbors.get(1).hasValue(PlayerMoves.X);
	}
	
	
}
