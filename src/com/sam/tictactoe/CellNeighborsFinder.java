package com.sam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class CellNeighborsFinder {
	private GameBoard _gameBoard;
	
	public CellNeighborsFinder(GameBoard gameBoard) {
		_gameBoard = gameBoard;
	}

	public List<Cell> rowNeighborsOf(Cell cell) {
		List<Cell> rowNeighbors = new ArrayList<Cell>();
		
		int row = cell.getX();
		for (int column = 0; column < GameBoard.COLUMN_COUNT; column++)
			if (!_gameBoard.cellAt(row, column).equals(cell))
				rowNeighbors.add(_gameBoard.cellAt(row, column));
		
		return rowNeighbors;		
	}
	
	public List<Cell> columnNeighborsOf(Cell cell) {
		List<Cell> columnNeighbors = new ArrayList<Cell>();
		
		int column = cell.getY();
		for (int row = 0; row < GameBoard.ROW_COUNT; row++)
			if (!_gameBoard.cellAt(row, column).equals(cell))
				columnNeighbors.add(_gameBoard.cellAt(row, column));
		
		return columnNeighbors;
	}
	
	public List<Cell> slantNeighborsOf(Cell cell) {
		List<Cell> slantNeighbors = new ArrayList<Cell>();
		
		if (!cell.isInEqualSlant() && !cell.isInNotEqualSlant())
			return slantNeighbors;
		if (cell.isInCenter()) {
			slantNeighbors.addAll(getNeighborsOfCentralCell(cell));
			return slantNeighbors;
		}
		if (cell.isInNotEqualSlant()) {
			slantNeighbors.addAll(neighborsInNotEqualSlant(cell));
			return slantNeighbors;
		}
		slantNeighbors.addAll(neighborsInEqualSlant(cell));
		
		return slantNeighbors;
	}

	public List<Cell> neighborsInEqualSlant(Cell cell) {
		List<Cell> equalSlantNeighbors = new ArrayList<Cell>();
		for (int row = 0; row < GameBoard.ROW_COUNT; row++)
			if (!_gameBoard.cellAt(row, row).equals(cell))
				equalSlantNeighbors.add(_gameBoard.cellAt(row, row));
		
		return equalSlantNeighbors;
	}

	public List<Cell> getNeighborsOfCentralCell(Cell cell) {
		List<Cell> centralCellNeighbors = new ArrayList<Cell>();
		for (int row = 0; row < GameBoard.ROW_COUNT; row++)
			if (!_gameBoard.cellAt(row, row).equals(new Cell(1, 1))) {
				centralCellNeighbors.add(_gameBoard.cellAt(row, row));
				centralCellNeighbors.add(_gameBoard.cellAt(row, GameBoard.ROW_COUNT - row - 1));
			}
		
		return centralCellNeighbors;
	}
	
	public List<Cell> neighborsInNotEqualSlant(Cell cell) {
		List<Cell> notEqualSlantCellNeighbors = new ArrayList<Cell>();
		for (int row = 0; row < GameBoard.ROW_COUNT; row++)
			if (!_gameBoard.cellAt(row, GameBoard.ROW_COUNT - row - 1).equals(cell))
				notEqualSlantCellNeighbors.add(_gameBoard.cellAt(row, GameBoard.ROW_COUNT - row - 1));
		
		return notEqualSlantCellNeighbors;
	}
	
}
