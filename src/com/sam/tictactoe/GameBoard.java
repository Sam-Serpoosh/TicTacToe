package com.sam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	public final static int ROW_COUNT = 3;
	public final static int COLUMN_COUNT = 3;
	
	private Cell[][] _cells;
	private Cell _playerLastMove;
	private Cell _computerLastMove;
	
	public GameBoard() {
		_cells = new Cell[ROW_COUNT][COLUMN_COUNT];
		fillBoardWithEmptyCells();
	}
	
	public Cell playerLastMove() {
		return _playerLastMove;
	}
	
	public Cell computerLastMove() {
		return _computerLastMove;
	}

	public boolean isEmpty() {
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				if (!_cells[row][column].isEmpty())
					return false;
		
		return true;
	}
	
	public boolean isFull() {
		return emptyCells().size() == 0;
	}
	
	public boolean onlyCenterCellIsFilledByPlayer() {
		return emptyCells().size() == 8 && cellAt(1, 1).hasValue(PlayerMoves.X);
	}
	
	public Cell cellAt(int row, int column) {
		return _cells[row][column];
	}

	public void fillCell(int row, int column, String value) {
		_cells[row][column].fill(value);
		setPlayerLastMove(row, column, value);
		setComputerLastMove(row, column, value);
	}

	public String valueOf(int row, int column) {
		return _cells[row][column].getValue();
	}
	
	public void reset() {
		fillBoardWithEmptyCells();
	}
	
	private void setPlayerLastMove(int row, int column, String value) {
		if (value.equals(PlayerMoves.O))
			return;
		_playerLastMove = new Cell(row, column);
		_playerLastMove.fill(value);
	}
	
	private void setComputerLastMove(int row, int column, String value) {
		if (value.equals(PlayerMoves.X))
			return;
		_computerLastMove = new Cell(row, column);
		_computerLastMove.fill(value);
	}

	private void fillBoardWithEmptyCells() {
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				_cells[row][column] = new Cell(row, column);
	}
	
	public List<Cell> availableCorners() {
		List<Cell> availableCornerCells = new ArrayList<Cell>();
		if (cellAt(0, COLUMN_COUNT - 1).isEmpty())
			availableCornerCells.add(cellAt(0, COLUMN_COUNT - 1));	
		if (cellAt(0, 0).isEmpty())
			availableCornerCells.add(cellAt(0, 0));
		if (cellAt(ROW_COUNT - 1, 0).isEmpty())
			availableCornerCells.add(cellAt(ROW_COUNT - 1, 0));
		if (cellAt(ROW_COUNT - 1, COLUMN_COUNT - 1).isEmpty())
			availableCornerCells.add(cellAt(ROW_COUNT - 1, COLUMN_COUNT - 1));
		
		return availableCornerCells;
	}

	public List<Cell> emptyCells() {
		List<Cell> emptyCells = new ArrayList<Cell>();
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				if (_cells[row][column].isEmpty())
					emptyCells.add(_cells[row][column]);
		
		return emptyCells;
	}

	public List<Cell> adjacentCellsOfCentralCellInRowAndColumn() {
		List<Cell> adjacentCellsOfCentralCell = new ArrayList<Cell>();
		int centralRow = cellAt(1, 1).getRow();
		int centralColumn = cellAt(1, 1).getColumn();
		adjacentCellsOfCentralCell.add(new Cell(centralRow - 1, centralColumn));
		adjacentCellsOfCentralCell.add(new Cell(centralRow + 1, centralColumn));
		adjacentCellsOfCentralCell.add(new Cell(centralRow, centralColumn - 1));
		adjacentCellsOfCentralCell.add(new Cell(centralRow, centralColumn + 1));

		return adjacentCellsOfCentralCell;
	}

	public List<Cell> cornerCellsInTheRowOf(Cell cell) {
		List<Cell> cornerCells = availableCorners();
		List<Cell> cornerCellsInTheRowOfPlayerLastMove = new ArrayList<Cell>();
		for (Cell corner : cornerCells)
			if (corner.getRow() == cell.getRow())
				cornerCellsInTheRowOfPlayerLastMove.add(corner);
		
		return cornerCellsInTheRowOfPlayerLastMove;
	}

	public List<Cell> cornerCellsInTheColumnOf(Cell cell) {
		List<Cell> cornerCells = availableCorners();
		List<Cell> cornerCellsInTheColumnOfPlayerLastMove = new ArrayList<Cell>();
		for (Cell corner : cornerCells)
			if (corner.getColumn() == cell.getColumn())
				cornerCellsInTheColumnOfPlayerLastMove.add(corner);
		
		return cornerCellsInTheColumnOfPlayerLastMove;
	}
	
}
