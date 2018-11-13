package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;

public interface Character {
	
	public PerceptionType getPerception(int row, int column, Direction direction, ActionType actionType);
	
}
