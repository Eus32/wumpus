package org.eus.wumpus;

import static org.junit.Assert.assertEquals;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.model.Well;
import org.eus.wumpus.utils.Position;
import org.junit.Before;
import org.junit.Test;

public class WellTest {
	private Well well;
	   
	@Before
	public void initComponents() {
		well = new Well (new Position(3, 3));
	}

	@Test
	public void getPerceptionsTest () {
		assertEquals(well.getPerception(3,4, Direction.NORTH, ActionType.WALK), PerceptionType.BREEZE);
		assertEquals(well.getPerception(3,3, Direction.NORTH, ActionType.WALK), PerceptionType.HOLE);
		assertEquals(well.getPerception(0,0, Direction.NORTH, ActionType.WALK), PerceptionType.NO_PERCEPTION);
	}
}
