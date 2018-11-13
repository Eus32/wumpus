package org.eus.wumpus.model;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.utils.Position;

public interface MainCharacter {
	
	public ActionType walk();
	
	public ActionType turnLeft();
	
	public ActionType turnRight();
	
	public ActionType throwArrow();
	
	public Position getPosition();
	
	public Direction getDirection();
	
	public int getArrows();
}

