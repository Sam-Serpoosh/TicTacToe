package com.sam.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class _CellNeighborsFinderTest {
	
	private CellNeighborsFinder _cellNeighborsFinder;
	
	@Before
	public void setup() {
		_cellNeighborsFinder = new CellNeighborsFinder(new GameBoard());
	}
	
	@Test
	public void getsNeighborsOfCellInTheRow() {
		List<Cell> rowNeighbors = _cellNeighborsFinder.rowNeighborsOf(new Cell(0, 1));
		assertEquals(2, rowNeighbors.size());
		assertTrue(rowNeighbors.contains(new Cell(0, 0)));
		assertTrue(rowNeighbors.contains(new Cell(0, 2)));
		assertFalse(rowNeighbors.contains(new Cell(0, 1)));
	}
	
	@Test
	public void getsNeighborsOfCellInColumn() {
		List<Cell> columnNeighbors = _cellNeighborsFinder.columnNeighborsOf(new Cell(0, 2));
		assertEquals(2, columnNeighbors.size());
		assertTrue(columnNeighbors.contains(new Cell(1, 2)));
		assertTrue(columnNeighbors.contains(new Cell(2, 2)));
		assertFalse(columnNeighbors.contains(new Cell(0, 2)));
	}
	
	
	@Test
	public void thereIsNoSlantNeighborWhenTheCellIsNotInSlant() {
		List<Cell> slantNeighbors = _cellNeighborsFinder.slantNeighborsOf(new Cell(0, 1));
		assertEquals(0, slantNeighbors.size());
	}
	
	@Test
	public void getsAllSlantNeighborsOfCellInCenter() {
		List<Cell> slantNeighbors = _cellNeighborsFinder.slantNeighborsOf(new Cell(1, 1));
		assertEquals(4, slantNeighbors.size());
		assertTrue(slantNeighbors.contains(new Cell(2, 2)));
		assertTrue(slantNeighbors.contains(new Cell(0, 0)));
		assertTrue(slantNeighbors.contains(new Cell(2, 0)));
		assertTrue(slantNeighbors.contains(new Cell(0, 2)));
		assertFalse(slantNeighbors.contains(new Cell(1, 1)));
	}
	
	@Test
	public void gestNeighborsInNotEqualSlantOfCell() {
		List<Cell> slantNeighbors = _cellNeighborsFinder.slantNeighborsOf(new Cell(2, 0));
		assertEquals(2, slantNeighbors.size());
		assertTrue(slantNeighbors.contains(new Cell(0, 2)));
		assertTrue(slantNeighbors.contains(new Cell(1, 1)));
	}
	
}
