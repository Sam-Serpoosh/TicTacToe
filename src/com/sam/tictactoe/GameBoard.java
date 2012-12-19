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

	public List<Cell> emptyCells() {
		List<Cell> emptyCells = new ArrayList<Cell>();
		for (int row = 0; row < ROW_COUNT; row++)
			for (int column = 0; column < COLUMN_COUNT; column++)
				if (_cells[row][column].isEmpty())
					emptyCells.add(_cells[row][column]);
		
		return emptyCells;
	}
	
}
