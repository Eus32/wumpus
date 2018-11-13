package org.eus.wumpus.model;

import java.util.ArrayList;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.PerceptionType;

public interface Board {

	public ActionType isMovementAllowed(); 
	
	public ArrayList<PerceptionType> getPerceptions (ActionType actionType);
	
	public MainCharacter getMainCharacter();
	
}
