package org.eus.wumpus.model;

import org.eus.wumpus.constants.CharacterType;
import org.eus.wumpus.utils.Position;

public class CharacterFactory {
	
	private static CharacterFactory instance;

	public static CharacterFactory getInstance () {
		if (instance == null) {
			instance = new CharacterFactory ();
		}
		return instance;
	}
	
	private CharacterFactory () { }

	public Character createCharacter(CharacterType characterType, Position position) {
		if (characterType == null) {
			return null;
		}
		
		if (characterType.equals(CharacterType.GOLD)) {
			return new Gold(position);
		}
		
		if (characterType.equals(CharacterType.WELL)) {
			return new Well(position);
		}
		
		if (characterType.equals(CharacterType.WUMPUS)) {
			return new Wumpus(position);
		}

		return null;
	}


}
