package com.sam.tictactoe.ui;

import com.sam.tictactoe.Cell;
import com.sam.tictactoe.ComputerPlayer;
import com.sam.tictactoe.GameBoard;
import com.sam.tictactoe.Player;
import com.sam.tictactoe.WinningPatternFinder;

public class TicTacToeGame {
	private static GameBoard _gameBoard;
	private static Player _player;
	private static ComputerPlayer _computerPlayer;
	private static GameBoardFrame _gameBoardFrame;
	
	public static void setup(GameBoardFrame gameBoardFrame) {
		_gameBoardFrame = gameBoardFrame;
		_gameBoard = _gameBoardFrame.getGameBoard();
		_player = new Player(_gameBoard);
		_computerPlayer = new ComputerPlayer(new WinningPatternFinder(_gameBoard));
	}
	
	public static void nextMove(Cell cell) {
		_player.play(cell);
		//TODO: winner checker!
		_computerPlayer.play();
		//TODO: winner checker!
		_gameBoardFrame.drawGameBoard();
	}
	
	public static void resetGame() {
		_gameBoard.reset();
		_gameBoardFrame.drawGameBoard();
	}
	
}
