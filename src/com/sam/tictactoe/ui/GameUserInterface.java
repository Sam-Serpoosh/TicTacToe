package com.sam.tictactoe.ui;

import com.sam.tictactoe.GameBoard;

public class GameUserInterface {

	public static void main(String[] args) {
		GameBoardFrame gameBoardFrame = new GameBoardFrame(new GameBoard());
		TicTacToeGame.setup(gameBoardFrame);
		gameBoardFrame.drawGameBoard();
	}
	
}
