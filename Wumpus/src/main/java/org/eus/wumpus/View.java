package org.eus.wumpus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.utils.Position;

public class View {

	private static final Logger logger = LogManager.getLogger(App.class.getName());
	
	private Scanner scanner;
	
	public View () {
		scanner = new Scanner(System.in);
	}
	
	public void printValues(ArrayList<String> values) {
		for (String value : values) {
			System.out.println(value);
		}
	} 
	
	public void printValue(String value) {
		System.out.println(value);
	}
	
	public ActionType getSelectedOption() {
		try {
			int action = 0;
			
			if (scanner != null) {
				action = scanner.nextInt();
		    } else {
		    	action = System.in.read();
		    }
			
			switch (action) {
			case 1: return ActionType.WALK;
			case 2: return ActionType.TURN_RIGHT;
			case 3: return ActionType.TURN_LEFT;
			case 4: return ActionType.THROUGH_ARROW;
			case 5: return ActionType.EXIT_GAME;
			}
			
		} catch(NumberFormatException nfe) {
			logger.error("Only numbers 1, 2, 3 or 4 are allowed.");
		} catch (IOException e) {
			logger.error("Console not working properly. Only numbers 1, 2, 3 or 4 are allowed.");
		}
		
		return ActionType.ACTION_NOT_ALLOWED;
	}
	
	public void printWelcomeMessages(Position position, Direction direction, int arrows) {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Starting the Wumpus game!!!");
		messages.add("Find the GOLD and return to the exit.");
		messages.add("You are in the position: " 
				+ position.getPositionX() + "," + position.getPositionY() +
				" and direction " + direction);
		messages.add("You got only " + arrows+ " arrows, take care and enjoy!!!");

		printValues(messages);
	}
	
	public void printMenu() {
		ArrayList<String> options = new ArrayList<String>();
		options.add("1. WALK");
		options.add("2. TURN RIGHT");
		options.add("3. TURN LEFT");
		options.add("4. THROUGH ARROW");
		options.add("5. QUIT GAME");
		printValues(options);
	}
	
}
