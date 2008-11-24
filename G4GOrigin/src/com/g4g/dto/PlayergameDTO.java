/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Playergame contains attribute for playergame entity.
 * 
 * @author Punam
 */

public class PlayergameDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5507087642661340120L;

	/** The game. */
	private GameDTO game;
	
	/** The player. */
	private PlayerDTO player;
	
	/** The creationdate. */
	private Date creationdate;
	
	/** The lastupdatedate. */
	private Date lastupdatedate;

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate
	 *            the creationdate to set
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the lastupdatedate.
	 * 
	 * @return the lastupdatedate
	 */
	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	/**
	 * Sets the lastupdatedate.
	 * 
	 * @param lastupdatedate
	 *            the lastupdatedate to set
	 */
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	/**
	 * Gets the game.
	 * 
	 * @return the game
	 */
	public GameDTO getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 * 
	 * @param game the new game
	 */
	public void setGame(GameDTO game) {
		this.game = game;
	}

	/**
	 * Gets the player.
	 * 
	 * @return the player
	 */
	public PlayerDTO getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 * 
	 * @param player the new player
	 */
	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}

	// Property accessors

}