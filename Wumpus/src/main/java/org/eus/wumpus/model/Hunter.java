package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.utils.Position;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class Hunter implements MainCharacter{
	@Getter
	@NonNull
	private int arrows;
	
	@Getter
	@NonNull
	private Direction direction;
	
	@Getter
	@NonNull
	private Position position;
	
	public ActionType walk() {
		switch (direction) {
		case NORTH:
			this.position.setPositionX(this.position.getPositionX() - 1);
			break;
			
		case WEST:
			this.position.setPositionY(this.position.getPositionY() - 1);
			break;
			
		case SOUTH:
			this.position.setPositionX(this.position.getPositionX() + 1);
			break;
			
		case EAST:
			this.position.setPositionY(this.position.getPositionY() + 1);
			break;
		}
		System.out.println("Position: " + position.getPositionX() + "," + position.getPositionY());	
		return ActionType.ACTION_ALLOWED;
	}
	
	public ActionType turnLeft() {
		switch (direction) {
		case NORTH:
			this.direction = Direction.WEST;
			break;
			
		case WEST:
			this.direction = Direction.SOUTH;
			break;
			
		case SOUTH:
			this.direction = Direction.EAST;
			break;
			
		case EAST:
			this.direction = Direction.NORTH;
			break;
			
		default:
			this.direction = Direction.NORTH;
		}
		
		return ActionType.ACTION_ALLOWED;
	}
	
	public ActionType turnRight() {
		switch (direction) {
		case NORTH:
			this.direction = Direction.EAST;
			break;
			
		case EAST:
			this.direction = Direction.SOUTH;
			break;
			
		case SOUTH:
			this.direction = Direction.WEST;
			break;
			
		case WEST:
			this.direction = Direction.NORTH;
			break;
		default:
			this.direction = Direction.NORTH;
		}
		
		return ActionType.ACTION_ALLOWED;
	}
	
	public ActionType throwArrow() {
		if (arrows > 0) {
			arrows--;
			return ActionType.ACTION_ALLOWED;
		}
		return ActionType.ACTION_NOT_ALLOWED;
	}

}
