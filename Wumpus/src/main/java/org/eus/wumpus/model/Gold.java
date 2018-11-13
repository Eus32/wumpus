package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.utils.Position;

public class Gold implements Character {
	
	private Position position;
	private boolean found;
	
	public Gold (Position position) {
		this.position = position;
		this.found = false;
	}

	public PerceptionType getPerception(int row, int column, Direction direction, ActionType actionType) {
		int currentX = position.getPositionX();
		int currentY = position.getPositionY();
		
		if (!found && (
				row == currentX     && column == currentY - 1 || 
				row == currentX - 1 && column == currentY     ||
				row == currentX + 1 && column == currentY     ||
				row == currentX     && column == currentY + 1 )) {
			return PerceptionType.SHINE;
		}
		
		if (!found && (row == currentX && column == currentY)) {
			found = true;
			return PerceptionType.GOLD;
		}
		
		return PerceptionType.NO_PERCEPTION;
	}

}
