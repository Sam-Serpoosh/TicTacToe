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
	
	public void fill(String value) {
		_value = value;
	}
	
	public boolean isFilled() {
		return _value != "";
	}
	
	public boolean isInSlant() {
		return getX() == getY();
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
	
}
