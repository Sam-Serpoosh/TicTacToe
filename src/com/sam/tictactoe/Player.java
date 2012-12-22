package com.sam.tictactoe;

public class Player {

	private GameBoard _gameBoard;
	
	public Player(GameBoard gameBoard) {
		_gameBoard = gameBoard;
	}
	
	public void play(Cell cell) {
		_gameBoard.fillCell(cell.getRow(), cell.getColumn(), PlayerMoves.X);
	}
	
}
