package com.sam.tictactoe;

public class Cell {
	private int _x;
	private int _y;
	private String _value;
	
	public Cell(int x, int y) {
		_x = x;
		_y = y;
		_value = "";
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
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
		return getX() == getY();
	}
	
	public boolean isInCenter() {
		return getX() == 1 && getY() == 1;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Cell == false)
			return false;
		Cell otherCell = (Cell)other;
		return otherCell.getX() == getX() && otherCell.getY() == getY();
	}
	
	@Override
	public String toString() {
		return getX() + ", " + getY() + ", " + getValue();  
	}

	public boolean isInNotEqualSlant() {
		return Math.abs(getX() - getY()) == 2;
	}
	
}
