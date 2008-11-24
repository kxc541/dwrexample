/**********************************************************
 * GamecategoryDTO.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * GamecategoryDTO is the DTO class for GameCategory table in database. It
 * contains name attribute. GameCategory table contains information about
 * different categories of games.
 * 
 * 
 * @author Pratik
 * @see BaseDTO
 * @hibernate.mapping GamecategoryDTO.hbm.xml
 */

public class GamecategoryDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1532396972245908933L;

	/** The name. */
	private String name;

	private Set<GameDTO> game = new HashSet<GameDTO>(0);

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the game
	 */
	public Set<GameDTO> getGame() {
		return this.game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	public void setGame(Set<GameDTO> game) {
		this.game = game;
	}

	// Property accessors
}