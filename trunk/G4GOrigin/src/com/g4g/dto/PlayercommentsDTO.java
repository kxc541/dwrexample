/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * PlayercommentsDTO contains attribute for playercomments entity.
 * 
 * @author Pratik
 */

public class PlayercommentsDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1509194863845757461L;

	/** The player. */
	private PlayerDTO player;
	
	/** The targetplayer. */
	private PlayerDTO targetplayer;
	
	/** The creationdate. */
	private Date creationdate;
	
	/** The comment. */
	private String comment;

	/**
	 * @return creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
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

	/**
	 * Gets the targetplayer.
	 * 
	 * @return the targetplayer
	 */
	public PlayerDTO getTargetplayer() {
		return targetplayer;
	}

	/**
	 * Sets the targetplayer.
	 * 
	 * @param targetplayer the new targetplayer
	 */
	public void setTargetplayer(PlayerDTO targetplayer) {
		this.targetplayer = targetplayer;
	}

}