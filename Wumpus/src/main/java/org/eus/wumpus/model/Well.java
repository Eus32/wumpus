package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.utils.Position;

public class Well implements Character {
	
	private Position position;
	
	public Well (Position position) {
		this.position = position;
	}

	public PerceptionType getPerception(int row, int column, Direction direction, ActionType actionType) {
		int currentX = position.getPositionX();
		int currentY = position.getPositionY();
		
		if (row == currentX     && column == currentY - 1 || 
				row == currentX - 1 && column == currentY     ||
				row == currentX + 1 && column == currentY     ||
				row == currentX     && column == currentY + 1 ) {
			return PerceptionType.BREEZE;
		}
		
		if (row == currentX && column == currentY) {
			return PerceptionType.HOLE;
		}
		
		return PerceptionType.NO_PERCEPTION;
	}
}
