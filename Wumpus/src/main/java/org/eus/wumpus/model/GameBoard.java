package org.eus.wumpus.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eus.wumpus.App;
import org.eus.wumpus.constants.ActionType;
import org.eus.wumpus.constants.CharacterType;
import org.eus.wumpus.constants.Direction;
import org.eus.wumpus.constants.PerceptionType;
import org.eus.wumpus.utils.MathUtils;
import org.eus.wumpus.utils.Position;

public class GameBoard implements Board {
	
	private int numberOfRows;
	private int numberOfColumns;
	private int numberOfHoles; 
	private int numberOfArrows;
	
	private Gold gold;
	private ArrayList<Well> wells;
	private Hunter hunter;
	private Wumpus wumpus; 
	
	private static final Logger logger = LogManager.getLogger(App.class.getName());
	
	public GameBoard (int numberOfRows, int numberOfColumns, int numberOfHoles, int numberOfArrows) {
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.numberOfHoles = numberOfHoles;
		this.numberOfArrows = numberOfArrows;
		
		initComponents();
	}
	
	private void initComponents() {
		
		wells = new ArrayList<Well>();
		
		ArrayList<Position> positionsToAvoid = new ArrayList<>();
		
		Position hunterPosition = new Position(0, 0);
		hunter = new Hunter(numberOfArrows, Direction.SOUTH, hunterPosition);
		positionsToAvoid.add(hunterPosition);
		logger.debug("Gold: " + hunterPosition.getPositionX() + "," + hunterPosition.getPositionY());
		
		Position goldPosition = MathUtils.getRandomPosition(numberOfRows, numberOfColumns,positionsToAvoid);
		gold = (Gold) CharacterFactory.getInstance().createCharacter(CharacterType.GOLD, goldPosition);
		positionsToAvoid.add(goldPosition);
		logger.debug("Gold: " + goldPosition.getPositionX() + "," + goldPosition.getPositionY());
		
		Position wumpusPosition = MathUtils.getRandomPosition(numberOfRows, numberOfColumns,positionsToAvoid);
		wumpus = (Wumpus) CharacterFactory.getInstance().createCharacter(CharacterType.WUMPUS, wumpusPosition);
		positionsToAvoid.add(wumpusPosition);
		logger.debug("Wumpus: " + wumpusPosition.getPositionX() + "," + wumpusPosition.getPositionY());
		
		for (int i = 0; i < numberOfHoles; i++) {
			Position wellPosition = MathUtils.getRandomPositionInEvenColumns(numberOfRows, numberOfColumns,positionsToAvoid);
			wells.add((Well) CharacterFactory.getInstance().createCharacter(CharacterType.WELL, wellPosition));
			positionsToAvoid.add(wellPosition);
			logger.debug("Well: " + wellPosition.getPositionX() + "," + wellPosition.getPositionY());
		}	
	}

	private PerceptionType getPerception(int row, int column, Direction direction, ActionType actionType) {
		if (row == 0 && column < numberOfColumns ||
				row < numberOfRows  && column == numberOfColumns - 1 ||
				row == numberOfRows - 1 && column < numberOfColumns  ||
				row < numberOfRows  && column == 0 ) {
			return PerceptionType.COLLISION;
		}
		return PerceptionType.NO_PERCEPTION;
	}
	
	public ActionType isMovementAllowed() {
		int row = hunter.getPosition().getPositionX();
		int column = hunter.getPosition().getPositionY();
		Direction direction = hunter.getDirection();
		
		if (row == 0 && column < numberOfColumns && direction == Direction.NORTH ||
				row < numberOfRows  && column == numberOfColumns - 1  && direction == Direction.EAST ||
				row == numberOfRows - 1 && column < numberOfColumns && direction == Direction.SOUTH ||
				row < numberOfRows  && column == 0  && direction == Direction.WEST) {
			return ActionType.ACTION_NOT_ALLOWED;
		}
		return ActionType.ACTION_ALLOWED;
	}
	
	public ArrayList<PerceptionType> getPerceptions (ActionType actionType) {
		ArrayList<PerceptionType> perceptions = new ArrayList<>();
		
		int x = hunter.getPosition().getPositionX();
		int y = hunter.getPosition().getPositionY();
		Direction direction = hunter.getDirection();
		
		perceptions.add(gold.getPerception(x, y, direction, actionType));
		perceptions.add(getPerception(x, y, direction, actionType));
		perceptions.add(wumpus.getPerception(x, y, direction, actionType));
		
		for (Well well : wells) {
			perceptions.add(well.getPerception(x, y, direction, actionType));
		}
		
		return perceptions;
	}
	
	public MainCharacter getMainCharacter() {
		return hunter;
	}

}
