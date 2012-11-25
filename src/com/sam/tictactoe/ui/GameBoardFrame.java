package com.sam.tictactoe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.sam.tictactoe.GameBoard;

public class GameBoardFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GameBoard _gameBoard;
	
	public GameBoardFrame() {
		super("Tic Tac Toe Board");
	}
	
	public GameBoardFrame(GameBoard gameBoard) {
		this();
		_gameBoard = gameBoard;
	}
	
	public GameBoard getGameBoard() {
		return _gameBoard;
	}
	
	public void drawGameBoard() {
		getContentPane().removeAll();
		getContentPane().add(new GameBoardPanel(getGameBoard()), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		repaint();
		setVisible(true);
	}
	
}
