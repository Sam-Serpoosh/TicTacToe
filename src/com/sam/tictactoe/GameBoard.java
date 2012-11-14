package com.sam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	private Cell[][] _cells;
	private final static int ROW_COUNT = 3;
	private final static int COLUMN_COUNT = 3;
	private Cell _playerLastMove;
	
	public GameBoard() {
		_cells = new Cell[ROW_COUNT][COLUMN_COUNT];
		fillBoardWithEmptyCells();
	}
	
	public Cell getPlayerLastMove() {
		return _playerLastMove;
	}

	public boolean isEmpty() {
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				if (_cells[row][column].isFilled())
					return false;
		
		return true;
	}

	public void fillCell(int row, int column, String value) {
		_cells[row][column].fill(value);
		setPlayerLastMove(row, column, value);
	}

	public String valueOf(int row, int column) {
		return _cells[row][column].getValue();
	}
	
	public List<Cell> getRowNeighborsOf(Cell cell) {
		List<Cell> rowNeighbors = new ArrayList<Cell>();
		int row = cell.getX();
		for (int column = 0; column < COLUMN_COUNT; column++)
			if (!_cells[row][column].equals(cell))
				rowNeighbors.add(_cells[row][column]);
		
		return rowNeighbors;		
	}
	
	public List<Cell> getColumnNeighbors(Cell cell) {
		List<Cell> columnNeighbors = new ArrayList<Cell>();
		int column = cell.getY();
		for (int row = 0; row < ROW_COUNT; row++)
			if (!_cells[row][column].equals(cell))
				columnNeighbors.add(_cells[row][column]);
		
		return columnNeighbors;
	}
	
	public List<Cell> getSlantNeighbors(Cell cell) {
		List<Cell> slantNeighbors = new ArrayList<Cell>();
		if (!cell.isInSlant())
			return slantNeighbors;
		
		for (int row = 0; row < ROW_COUNT; row++)
			if (!_cells[row][row].equals(cell))
				slantNeighbors.add(_cells[row][row]);
		
		return slantNeighbors;
	}
	
	public void reset() {
		fillBoardWithEmptyCells();
	}
	
	private void setPlayerLastMove(int row, int column, String value) {
		_playerLastMove = new Cell(row, column);
		_playerLastMove.fill(value);
	}

	private void fillBoardWithEmptyCells() {
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				_cells[row][column] = new Cell(row, column);
	}
	
}
