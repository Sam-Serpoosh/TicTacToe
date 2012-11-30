package com.sam.tictactoe.ui;

import com.sam.tictactoe.GameBoard;

public class TicTacToeUserInterface {

	public static void main(String[] args) {
		TicTacToeFrame gameBoardFrame = new TicTacToeFrame(new GameBoard());
		TicTacToeGame.setup(gameBoardFrame);
		gameBoardFrame.drawGameBoard();
	}
	
}
