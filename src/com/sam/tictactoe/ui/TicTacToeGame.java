package com.sam.tictactoe.ui;

import com.sam.tictactoe.Cell;
import com.sam.tictactoe.CellNeighborsFinder;
import com.sam.tictactoe.ComputerPlayer;
import com.sam.tictactoe.GameBoard;
import com.sam.tictactoe.Player;
import com.sam.tictactoe.WinChecker;
import com.sam.tictactoe.WinningPatternFinder;

public class TicTacToeGame {
	private static GameBoard _gameBoard;
	private static Player _player;
	private static ComputerPlayer _computerPlayer;
	private static WinChecker _winChecker;
	private static TicTacToeFrame _gameBoardFrame;
	
	public static void setup(TicTacToeFrame gameBoardFrame) {
		_gameBoardFrame = gameBoardFrame;
		_gameBoard = _gameBoardFrame.getGameBoard();
		_player = new Player(_gameBoard);
		_winChecker = new WinChecker(_gameBoard, new CellNeighborsFinder(_gameBoard));
		_computerPlayer = new ComputerPlayer(new WinningPatternFinder(_gameBoard), _gameBoard);
	}
	
	public static void nextMove(Cell cell) {
		_player.play(cell);
		boolean playerWon = checkPlayerWon();
		if (!playerWon)
			_computerPlayer.play();
		checkComputerWon();
		checkIfTheGameTied();
		_gameBoardFrame.drawGameBoard();
	}
	
	private static boolean checkPlayerWon() {
		boolean playerWon = _winChecker.playerWon();
		if (playerWon) {
			_gameBoardFrame.showPlayerWonMessage();
			resetGame();
		}
		return playerWon;
	}
	
	private static void checkComputerWon() {
		boolean computerWon = _winChecker.computerWon();
		if (computerWon) {
			_gameBoardFrame.showPlayerLostMessage();
			resetGame();
		}
	}
	
	
	private static void checkIfTheGameTied() {
		if (_gameBoard.isFull()) {
			_gameBoardFrame.showTiedMessage();
			resetGame();
		}
	}
	public static void resetGame() {
		_gameBoard.reset();
		_gameBoardFrame.drawGameBoard();
	}
	
}
