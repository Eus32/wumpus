package org.eus.wumpus;

import static org.junit.Assert.assertEquals;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.model.Gold;
import org.eus.wumpus.utils.Position;
import org.junit.Before;
import org.junit.Test;

public class GoldTest {
	
	private Gold gold;
	   
	@Before
	public void initComponents() {
		gold = new Gold (new Position(3, 3));
	}

	@Test
	public void getPerceptionsTest () {
		assertEquals(gold.getPerception(3,4, Direction.NORTH, ActionType.WALK), PerceptionType.SHINE);
		assertEquals(gold.getPerception(3,3, Direction.NORTH, ActionType.WALK), PerceptionType.GOLD);
		assertEquals(gold.getPerception(0,0, Direction.NORTH, ActionType.WALK), PerceptionType.NO_PERCEPTION);
	}
	
}
