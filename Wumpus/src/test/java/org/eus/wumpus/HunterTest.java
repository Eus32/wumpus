package org.eus.wumpus;

import static org.junit.Assert.assertEquals;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.model.Hunter;
import org.eus.wumpus.utils.Position;
import org.junit.Before;
import org.junit.Test;

public class HunterTest {
	private Hunter hunter;
	   
	@Before
	public void initComponents() {
		hunter = new Hunter (1, Direction.SOUTH, new Position(3, 3));
	}
	
	@Test
	public void walkTest() {
		hunter.walk();
		assertEquals(hunter.getPosition(), new Position(4,3));
	}
	
	@Test
	public void turnLeft() {
		hunter.turnLeft();
		assertEquals(hunter.getDirection(), Direction.EAST);
		hunter.turnLeft();
		assertEquals(hunter.getDirection(), Direction.NORTH);
		hunter.turnLeft();
		assertEquals(hunter.getDirection(), Direction.WEST);
		hunter.turnLeft();
		assertEquals(hunter.getDirection(), Direction.SOUTH);	
	}
	
	@Test
	public void turnRight() {
		hunter.turnRight();
		assertEquals(hunter.getDirection(), Direction.WEST);
		hunter.turnRight();
		assertEquals(hunter.getDirection(), Direction.NORTH);
		hunter.turnRight();
		assertEquals(hunter.getDirection(), Direction.EAST);
		hunter.turnRight();
		assertEquals(hunter.getDirection(), Direction.SOUTH);
	}
	
	@Test
	public void throwArrow() {
		assertEquals(ActionType.ACTION_ALLOWED, hunter.throwArrow());
		assertEquals(ActionType.ACTION_NOT_ALLOWED, hunter.throwArrow() );
	}
	
	
}
