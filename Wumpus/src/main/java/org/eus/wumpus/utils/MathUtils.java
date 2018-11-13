package org.eus.wumpus.utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class MathUtils {
	
	public static Position getRandomPositionInEvenColumns(int maxRow, int maxColumn, ArrayList<Position> positionsToAvoid) {
		Position positionToReturn = null;
		boolean newPositionFound = false;
		
		while(!newPositionFound) {
			Position position = new Position (getRandomNumber(maxRow), getRandomEvenNumber(maxColumn));
			
			Optional<Position> result = positionsToAvoid
				      .stream().parallel()
				      .filter(positionToAvoid -> positionToAvoid.equals(position)).findFirst();
			
			if (!result.isPresent()) {
				newPositionFound = true;
				positionToReturn = position;
			}
		}
		
		return positionToReturn;
	}
	
	public static Position getRandomPosition(int maxRow, int maxColumn, ArrayList<Position> positionsToAvoid) {
		Position positionToReturn = null;
		boolean newPositionFound = false;
		
		while(!newPositionFound) {
			Position position = new Position (getRandomNumber(maxRow), getRandomNumber(maxColumn));
			
			Optional<Position> result = positionsToAvoid
				      .stream().parallel()
				      .filter(positionToAvoid -> positionToAvoid.equals(position)).findAny();
			
			if (!result.isPresent()) {
				newPositionFound = true;
				positionToReturn = position;
			}
		}
		
		return positionToReturn;
	}
	
	private static int getRandomNumber(int max) {
		Random rand = new Random(); 
		int randomNum = rand.nextInt(max);
		return randomNum;
	}
	
	private static int getRandomEvenNumber (int max) {
		Random rand = new Random(); 
		int randomNum = rand.nextInt((max+1)/2) *2;
		return randomNum;
	}
	
}
