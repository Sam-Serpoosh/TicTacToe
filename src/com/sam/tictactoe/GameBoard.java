package com.sam.tictactoe;

public class GameBoard {
	private Cell[][] _cells;
	private final static int ROW_COUNT = 3;
	private final static int COLUMN_COUNT = 3;
	private Move _playerLastMove;
	
	public GameBoard() {
		_cells = new Cell[ROW_COUNT][COLUMN_COUNT];
		fillBoardWithEmptyCells();
	}
	
	public Move getPlayerLastMove() {
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
		_playerLastMove = new Move(row, column, value);
	}

	public String valueOf(int row, int column) {
		return _cells[row][column].getValue();
	}
	
	public void reset() {
		fillBoardWithEmptyCells();
	}

	private void fillBoardWithEmptyCells() {
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				_cells[row][column] = new Cell(row, column);
	}
	
}
