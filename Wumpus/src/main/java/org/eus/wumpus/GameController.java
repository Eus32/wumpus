package org.eus.wumpus;

import java.util.ArrayList;

import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.GameStatus;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.model.GameBoard;
import org.eus.wumpus.utils.PropertiesHelper;

public class GameController {
	
	private View view;
	
	private GameBoard board;
	
	private int numberOfRows = 0;
	private int numberOfColumns = 0;
	private int numberOfHoles = 0;
	private int numberOfArrows = 0;
	
	private GameStatus gameStatus;
	
	public GameController() {
		initComponents();
	}

	private void initComponents () {
		view = new View ();
		gameStatus = GameStatus.SEARCHING_GOLD;
		
		this.numberOfRows = PropertiesHelper.getInstance().getNumberOfRows();
		this.numberOfColumns = PropertiesHelper.getInstance().getNumberOfColumns();
		this.numberOfHoles = PropertiesHelper.getInstance().getNumberOfHoles();
		this.numberOfArrows = PropertiesHelper.getInstance().getNumberOfArrows();
		
		board = new GameBoard(numberOfRows, numberOfColumns, numberOfHoles, numberOfArrows);
	}
	
	public void startGame() {
		
		view.printWelcomeMessages(board.getMainCharacter().getPosition(),
				board.getMainCharacter().getDirection(),
				board.getMainCharacter().getArrows());
		view.printMenu();
		
		updateStatus(ActionType.WALK);
		
		while(gameStatus != GameStatus.DIE &&
				gameStatus != GameStatus.WIN &&
				gameStatus != GameStatus.FINISHED) {
			
			ActionType action = view.getSelectedOption();
			
			switch(action) {
			case EXIT_GAME:
				exitGame();
				break;
			case TURN_LEFT:
				turnLeft();
				break;
			case TURN_RIGHT:
				turnRight();
				break;
			case THROUGH_ARROW:
				throwArrow();
				break;
			case WALK:
				walk();
				break;
			default:
				break;
			}
		}
	}
	
	private void updateStatus (ActionType actionType) {
		String message = "Perceptions: ";
		ArrayList<PerceptionType> perceptions = board.getPerceptions(actionType);
		
		for (PerceptionType perception : perceptions) {
			if (perception == PerceptionType.GOLD) {
				gameStatus = GameStatus.GOLD_FOUND;
			}
			
			if (perception == PerceptionType.HOLE) {
				gameStatus = GameStatus.DIE;
			}
			
			if (perception == PerceptionType.WUMPUS) {
				gameStatus = GameStatus.DIE;
			}
			
			message = message + getPerceptionString(perception);
		}
		
		view.printValue(message);
	}
	
	private String getPerceptionString (PerceptionType pt) {
		return pt != PerceptionType.NO_PERCEPTION ? String.valueOf(pt) + " " : "";
	}
	
	private void throwArrow () {
		ActionType actionType = board.getMainCharacter().throwArrow();
		if (actionType != ActionType.ACTION_NOT_ALLOWED) {
			updateStatus(ActionType.THROUGH_ARROW);
		} else {
			view.printValue("There are no arrows");
		}
	} 
	
	private void exitGame() {
		view.printValue("See you in the next Game!!!");
		gameStatus = GameStatus.FINISHED;
	}
	
	private void turnLeft() {
		board.getMainCharacter().turnLeft();
		view.printValue("You have turned left, now you are pointing: " + board.getMainCharacter().getDirection());
	}
	
	private void turnRight() {
		board.getMainCharacter().turnRight();
		view.printValue("You have turned right, now you are pointing: " + board.getMainCharacter().getDirection());
	}
	
	private void walk() {
		if (board.isMovementAllowed() != ActionType.ACTION_NOT_ALLOWED) {
			board.getMainCharacter().walk();
			
			updateStatus(ActionType.WALK);
			
			if (gameStatus == GameStatus.DIE) {
				view.printValue("You Die!!!");
			}
			
			if (gameStatus == GameStatus.GOLD_FOUND && 
					board.getMainCharacter().getPosition().getPositionX() == 0 &&
					board.getMainCharacter().getPosition().getPositionY() == 0) {
				gameStatus = GameStatus.WIN;
				view.printValue("Congratulations You Win!!!");
			}
			
		} else {
			view.printValue("Ooops, you can not walk in this direction");
		}
	}
}
