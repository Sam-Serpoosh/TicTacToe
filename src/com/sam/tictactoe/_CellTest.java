package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class _CellTest {
	private static final String X = "X";
	
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
	public void isNotFilledAtCreation() {
		assertFalse(_cell.isFilled());
	}
	
	@Test
	public void canFillsTheCell() {
		_cell.fill(X);
		assertTrue(_cell.isFilled());
	}
	
	@Test
	public void canFetchValueOfTheCell() {
		String x = X;
		_cell.fill(x);
		assertEquals(x, _cell.getValue());
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
		assertFalse(cell.isInSlant());
		
		cell = new Cell(0, 0);
		assertTrue(cell.isInSlant());
	}
	
	@Test
	public void toStringTest() {
		Cell cell = new Cell(0, 1);
		cell.fill(X);
		assertEquals("0, 1, X", cell.toString());
	}

}
