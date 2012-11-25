package com.sam.tictactoe.ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sam.tictactoe.GameBoard;

public class GameBoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameBoard _gameBoard;

	public GameBoardPanel(GameBoard gameBoard) {
		_gameBoard = gameBoard;
		
		setSize(400, 400);
		setLayout(null);
		addCellButtons();
		addResetButton();
		setVisible(true);
	}

	public void paintComponent(Graphics g) { }

	public void addCellButtons() {
		int yPositionInRow = 100;
		for (int row = 0; row < GameBoard.ROW_COUNT; row++) {
			int xPositionInRow = 70;
			for (int column = 0; column < GameBoard.COLUMN_COUNT; column++) {
				CellButton cellButton = new CellButton(xPositionInRow, yPositionInRow, 
						_gameBoard.cellAt(row, column));
				add(cellButton);
				xPositionInRow += CellButton.WIDTH + 10;
			}
			yPositionInRow += CellButton.HEIGHT + 10;
		}
	}
	
	public void addResetButton() {
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(300, 300, CellButton.WIDTH, CellButton.HEIGHT);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				TicTacToeGame.resetGame();
			}
		});
		add(resetButton);
	}

}