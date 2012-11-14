package com.sam.tictactoe;

public class Move { //just a value object meaningful in the domain! instead of primitive obsession!
	public int X;
	public int Y;
	public String Value;

	public Move(int x, int y, String value) {
		X = x;
		Y = y;
		Value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Move == false)
			return false;
		
		Move otherMove = (Move)obj;
		return otherMove.X == X && otherMove.Y == Y && otherMove.Value == Value;
	}
	
	@Override
	public String toString() {
		return X + ", " + Y + ", " + Value; 
	}
	
}
