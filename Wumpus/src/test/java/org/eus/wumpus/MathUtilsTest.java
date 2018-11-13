package org.eus.wumpus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.eus.wumpus.utils.MathUtils;
import org.eus.wumpus.utils.Position;
import org.junit.Before;
import org.junit.Test;

public class MathUtilsTest {
	
	private int maxRow = 5;
	private int maxColumn = 5;
	
	private ArrayList<Position> positionsToAvoid = new ArrayList<>();
	
	@Before
	public void before() {
		Position positionToAvoid = new Position(0, 0);
		positionsToAvoid.add(positionToAvoid);
	}
	
	@Test
	public void getRandomPositionTest () {
		
		Position position = MathUtils.getRandomPosition(maxRow, maxColumn, positionsToAvoid);
		assertEquals(position.getPositionX() < maxRow, true);
		assertEquals(position.getPositionY() < maxColumn, true);
		
		assertNotEquals(position, new Position(0,0));
	}
	
	@Test
	public void getRandomPositionEvenTest () {
		
		Position position = MathUtils.getRandomPositionInEvenColumns(maxRow, maxColumn, positionsToAvoid);
		assertEquals(position.getPositionX() < maxRow, true);
		assertEquals(position.getPositionY() != 1 || position.getPositionY() != 3, true);
		
		assertNotEquals(position, new Position(0,0));
	}
	
	
}
