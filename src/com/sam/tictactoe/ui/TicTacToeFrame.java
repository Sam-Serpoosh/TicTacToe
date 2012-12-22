package com.sam.tictactoe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sam.tictactoe.GameBoard;

public class TicTacToeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GameBoard _gameBoard;
	
	public TicTacToeFrame() {
		super("Tic Tac Toe Board");
	}
	
	public TicTacToeFrame(GameBoard gameBoard) {
		this();
		_gameBoard = gameBoard;
	}
	
	public GameBoard getGameBoard() {
		return _gameBoard;
	}
	
	public void showPlayerWonMessage() {
		JOptionPane.showMessageDialog(this, "Congratulation, You Won!");
	}
	
	public void showPlayerLostMessage() {
		JOptionPane.showMessageDialog(this, "Sorry, You Lost!");
	}
	
	public void showTiedMessage() {
		JOptionPane.showMessageDialog(this, "We tied!");
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
