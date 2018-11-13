package org.eus.wumpus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.model.Board;
import org.eus.wumpus.model.GameBoard;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	private Board board;
   
	@Before
	public void initComponents() {
		board = new GameBoard (5, 5, 3, 3)	;
	}
	
	@Test
	public void initializeBoardTest () {
		assertEquals(board.getMainCharacter().getDirection(), Direction.SOUTH);
		assertEquals(board.getMainCharacter().getPosition().getPositionX(), 0);
		assertEquals(board.getMainCharacter().getPosition().getPositionY(), 0);
	}
	
	@Test
	public void isMovementAllowedTest() {
		assertEquals(board.isMovementAllowed(), ActionType.ACTION_ALLOWED);
		board.getMainCharacter().walk();
		board.getMainCharacter().walk();
		board.getMainCharacter().walk();
		board.getMainCharacter().walk();
		assertEquals(board.isMovementAllowed(), ActionType.ACTION_NOT_ALLOWED);
	}
	
	@Test
	public void getPerceptionsTest () {
		ArrayList<PerceptionType> perceptions = board.getPerceptions(ActionType.WALK);
		assertTrue(perceptions.contains(PerceptionType.COLLISION));
	}
	
}
