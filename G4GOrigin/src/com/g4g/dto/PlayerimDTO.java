/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * PlayerimDTO contains attribute for entity.
 * 
 * @author Ankur
 */

public class PlayerimDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3135659478949321722L;

	private PlayerDTO player;
	private ImnetworkDTO imnetwork;
	private String playerimtag;
	private Date creationdate;
	private Date lastupdatedate;

	/**
	 * Gets the creationdate.
	 * 
	 * @return creationdate
	 */
	public Date getCreationdate() {
		return this.creationdate;
	}

	/**
	 * Gets the lastupdatedate.
	 * 
	 * @return lastupdatedate
	 */
	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	/**
	 * Gets the playerimtag.
	 * 
	 * @return playerimtag
	 */
	public String getPlayerimtag() {
		return this.playerimtag;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate
	 *            the creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Sets the lastupdatedate.
	 * 
	 * @param lastupdatedate
	 *            the lastupdatedate
	 */
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	/**
	 * @return the player
	 */
	public PlayerDTO getPlayer() {
		return this.player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}

	/**
	 * @return the imnetwork
	 */
	public ImnetworkDTO getImnetwork() {
		return this.imnetwork;
	}

	/**
	 * @param imnetwork
	 *            the imnetwork to set
	 */
	public void setImnetwork(ImnetworkDTO imnetwork) {
		this.imnetwork = imnetwork;
	}

	/**
	 * Sets the playerimtag.
	 * 
	 * @param playerimtag
	 *            the playerimtag
	 */
	public void setPlayerimtag(String playerimtag) {
		this.playerimtag = playerimtag;
	}

}