/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * Twoplayertournamentadmincomments contains attribute of
 * twoplayertournamentadmincomments entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Twoplayertournamentadmincomments extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7180770036214328185L;

	/** The tournamentid. */
	private Integer tournamentid;

	/** The employeeid. */
	private Integer employeeid;

	/** The commentseq. */
	private Integer commentseq;

	/** The creationdate. */
	private Date creationdate;

	/** The comment. */
	private String comment;

	// Property accessors

	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Gets the commentseq.
	 * 
	 * @return the commentseq
	 */
	public Integer getCommentseq() {
		return this.commentseq;
	}

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return this.creationdate;
	}

	/**
	 * Gets the employeeid.
	 * 
	 * @return the employeeid
	 */
	public Integer getEmployeeid() {
		return this.employeeid;
	}

	/**
	 * Gets the tournamentid.
	 * 
	 * @return the tournamentid
	 */
	public Integer getTournamentid() {
		return this.tournamentid;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the commentseq.
	 * 
	 * @param commentseq
	 *            the new commentseq
	 */
	public void setCommentseq(Integer commentseq) {
		this.commentseq = commentseq;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Sets the employeeid.
	 * 
	 * @param employeeid
	 *            the new employeeid
	 */
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	/**
	 * Sets the tournamentid.
	 * 
	 * @param tournamentid
	 *            the new tournamentid
	 */
	public void setTournamentid(Integer tournamentid) {
		this.tournamentid = tournamentid;
	}

}