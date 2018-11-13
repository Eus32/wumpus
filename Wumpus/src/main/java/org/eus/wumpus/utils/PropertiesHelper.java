package org.eus.wumpus.utils;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;

public class PropertiesHelper {
	
	public static final Logger logger = LogManager.getLogger(PropertiesHelper.class.getName());

	private static PropertiesHelper instance;
	
	@Getter 
	private int numberOfRows = 5;
	@Getter 
	private int numberOfColumns = 5;
	@Getter 
	private int numberOfArrows = 3;
	@Getter 
	private int numberOfHoles = 4;

	public static PropertiesHelper getInstance () {
		if (instance == null) {
			instance = new PropertiesHelper ();
		}
		return instance;
	}
	
	private PropertiesHelper () {
		loadProperties();
	}
	
	private void loadProperties () {
		Configurations configs = new Configurations();
		
		try {
		    Configuration config = configs.properties(new File("config.properties"));
		    numberOfRows = config.getInt("number.of.rows");
		    numberOfColumns = config.getInt("number.of.columns");
		    numberOfArrows = config.getInt("number.of.arrows");
		    numberOfHoles = config.getInt("number.of.holes");
		    
		    logger.debug("Prop numberOfRows: " + numberOfRows);
		    logger.debug("Prop numberOfColumns: " + numberOfColumns);
		    logger.debug("Prop numberOfArrows: " + numberOfArrows);
		    logger.debug("Prop numberOfHoles: " + numberOfHoles);
		}
		catch (ConfigurationException cex) {
			logger.error("Using default property values", cex);
		}
	}

	
}
