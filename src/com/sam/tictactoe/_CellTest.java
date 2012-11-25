package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class _CellTest {
	private Cell _cell;
	
	@Before
	public void setup() {
		_cell = new Cell(0, 1);
	}
	
	@Test
	public void setsCoordinatesAtCreation() {
		assertEquals(0, _cell.getX());
		assertEquals(1, _cell.getY());
	}
	
	@Test
	public void isEmptyAtCreation() {
		assertTrue(_cell.isEmpty());
	}
	
	@Test
	public void canFillsTheCell() {
		_cell.fill(PlayerMoves.X);
		assertFalse(_cell.isEmpty());
	}
	
	@Test
	public void canFetchValueOfTheCell() {
		_cell.fill(PlayerMoves.X);
		assertEquals(PlayerMoves.X, _cell.getValue());
	}
	
	@Test
	public void canCheckItsValue() {
		_cell.fill(PlayerMoves.X);
		assertTrue(_cell.hasValue(PlayerMoves.X));
		
		_cell.fill(PlayerMoves.O);
		assertTrue(_cell.hasValue(PlayerMoves.O));
	}
	
	@Test
	public void cellsAreEqualWhenTheyHaveSameCoordinates() {
		Cell cell1 = new Cell(0, 1);
		Cell cell2 = new Cell(0, 1);
		assertTrue(cell1.equals(cell2));
	}
	
	@Test
	public void cellsWithDifferentCoordinatesAreNotEqual() {
		Cell cell1 = new Cell(0, 0);
		Cell cell2 = new Cell(0, 1);
		
		assertFalse(cell1.equals(cell2));
	}
	
	@Test
	public void knowsWhenIsInSlantLine() {
		Cell cell = new Cell(0, 1);
		assertFalse(cell.isInEqualSlant());
		
		cell = new Cell(0, 0);
		assertTrue(cell.isInEqualSlant());
	}
	
	@Test
	public void knowsWhenIsInNotEqualSlant() {
		Cell cell = new Cell(2, 0);
		assertTrue(cell.isInNotEqualSlant());
	}
	
	@Test
	public void toStringTest() {
		Cell cell = new Cell(0, 1);
		cell.fill(PlayerMoves.X);
		assertEquals("0, 1, " + PlayerMoves.X, cell.toString());
	}

}
