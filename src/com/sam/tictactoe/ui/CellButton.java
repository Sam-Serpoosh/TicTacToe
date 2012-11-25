package com.sam.tictactoe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.sam.tictactoe.Cell;

public class CellButton extends JButton {
	private static final long serialVersionUID = 1L;
	protected static final int WIDTH = 70;
	protected static final int HEIGHT = 30;
	
	public CellButton(int x, int y, Cell cell) {
		if (cell.isEmpty())
			setText("Empty");
		else
			setText(cell.getValue());
		setBounds(x, y, WIDTH, HEIGHT);
		addActionListener(new CellButtonActionListener(cell));
	}
	
}

class CellButtonActionListener implements ActionListener {
	private Cell _cell;
	
	public CellButtonActionListener(Cell cell) {
		_cell = cell;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (_cell.isEmpty())
			TicTacToeGame.nextMove(_cell);
	}
	
}