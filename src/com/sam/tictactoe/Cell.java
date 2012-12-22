package com.sam.tictactoe;

public class Cell {
	private int _row;
	private int _column;
	private String _value;
	
	public Cell(int row, int column) {
		_row = row;
		_column = column;
		_value = "";
	}
	
	public int getRow() {
		return _row;
	}
	
	public int getColumn() {
		return _column;
	}
	
	public String getValue() {
		return _value;
	}
	
	public boolean hasValue(String value) {
		return getValue().equals(value);
	}
	
	public void fill(String value) {
		_value = value;
	}
	
	public boolean isEmpty() {
		return _value == "";
	}
	
	public boolean isInEqualSlant() {
		return getRow() == getColumn();
	}
	
	public boolean isInCenter() {
		return getRow() == 1 && getColumn() == 1;
	}
	
	public boolean isInCorner() {
		return Math.abs(getRow() - getColumn()) == 2 || (getRow() == getColumn() && getRow() != 1);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Cell == false)
			return false;
		Cell otherCell = (Cell)other;
		return otherCell.getRow() == getRow() && otherCell.getColumn() == getColumn();
	}
	
	@Override
	public String toString() {
		return getRow() + ", " + getColumn() + ", " + getValue();  
	}

	public boolean isInNotEqualSlant() {
		return Math.abs(getRow() - getColumn()) == 2;
	}
	
}
