package org.eus.wumpus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	logger.debug("WUMPUS: Starting game!!!");
    	
    	GameController gameController = new GameController();
    	gameController.startGame();
    	
    	logger.debug("Game Over!!!");
    }
    
    
}
