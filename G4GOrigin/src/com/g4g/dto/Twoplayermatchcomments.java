/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * Twoplayertournamentcomments contains attribute of twoplayertournamentcomments
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Twoplayermatchcomments extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5220270833537361497L;

	

	/** The playerid. */
	private Integer playerid;

	/** The commentseq. */
	private Integer commentseq;

	/** The creationdate. */
	private Date creationdate;

	/** The comment. */
	private String comment;

	/** The matchid. */
	private String matchid;

	/**
	

	/**
	 * Gets the playerid.
	 * 
	 * @return the playerid
	 */
	public Integer getPlayerid() {
		return playerid;
	}

	/**
	 * Sets the playerid.
	 * 
	 * @param playerid
	 *            the playerid to set
	 */
	public void setPlayerid(Integer playerid) {
		this.playerid = playerid;
	}

	/**
	 * Gets the commentseq.
	 * 
	 * @return the commentseq
	 */
	public Integer getCommentseq() {
		return commentseq;
	}

	/**
	 * Sets the commentseq.
	 * 
	 * @param commentseq
	 *            the commentseq to set
	 */
	public void setCommentseq(Integer commentseq) {
		this.commentseq = commentseq;
	}

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
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the matchid.
	 * 
	 * @return the matchid
	 */
	public String getMatchid() {
		return matchid;
	}

	/**
	 * Sets the matchid.
	 * 
	 * @param matchid
	 *            the matchid to set
	 */
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	// Property accessors

}