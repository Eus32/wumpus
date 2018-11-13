package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.utils.Position;

public class Wumpus implements Character {
	
	private Position position;
	private boolean isAlive;
	
	public Wumpus (Position position) {
		this.position = position;
		this.isAlive = true;
	}

	public PerceptionType getPerception(int row, int column, Direction direction, ActionType actionType) {
		int currentX = position.getPositionX();
		int currentY = position.getPositionY();
		
		if (actionType == ActionType.WALK && isAlive && (
				row == currentX     && column == currentY - 1 || 
				row == currentX - 1 && column == currentY     ||
				row == currentX + 1 && column == currentY     ||
				row == currentX     && column == currentY + 1 )) {
			return PerceptionType.STINK;
		}
		
		if (actionType == ActionType.WALK && isAlive && (
				row == currentX && column == currentY)
			) {
			return PerceptionType.WUMPUS;
		}
		
		if (actionType == ActionType.THROUGH_ARROW && isAlive && isWumpusImpacted(row, column, direction)) {
			isAlive = false;
			return PerceptionType.SHOUT;
		}
		
		return PerceptionType.NO_PERCEPTION;
	}
	
	private boolean isWumpusImpacted(int row, int column, Direction direction) {
		if(direction == Direction.NORTH &&
				column == position.getPositionY() &&
				row > position.getPositionX()) {
			return true;
		}
		if(direction == Direction.EAST &&
				column < position.getPositionY() &&
				row == position.getPositionX()) {
			return true;
		}
		if(direction == Direction.SOUTH &&
				column == position.getPositionY() &&
				row < position.getPositionX()) {
			return true;
		}
		if(direction == Direction.WEST &&
				column > position.getPositionY() &&
				row == position.getPositionX()) {
			return true;
		}
		
		return false;
	}
	
}
