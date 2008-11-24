/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

/**
 * Twoplayertournamentgameoptions contains attribute of
 * twoplayertournamentgameoptions table.
 * 
 * @author Ankur
 */

public class TwoplayertournamentgameoptionsDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5155241752834095396L;

	private TwoplayertournamentDTO tournamentid;

	/** The optionid. */
	private Integer optionid;

	/** The valueid. */
	private Integer valueid;

	/** The comments. */
	private String comments;

	/**
	 * Gets the tournamentid.
	 * 
	 * @return the tournamentid
	 */
	public TwoplayertournamentDTO getTournamentid() {
		return tournamentid;
	}

	/**
	 * Sets the tournamentid.
	 * 
	 * @param tournamentid
	 *            the tournamentid to set
	 */
	public void setTournamentid(TwoplayertournamentDTO tournamentid) {
		this.tournamentid = tournamentid;
	}

	/**
	 * Gets the optionid.
	 * 
	 * @return the optionid
	 */
	public Integer getOptionid() {
		return optionid;
	}

	/**
	 * Sets the optionid.
	 * 
	 * @param optionid
	 *            the optionid to set
	 */
	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}

	/**
	 * Gets the valueid.
	 * 
	 * @return the valueid
	 */
	public Integer getValueid() {
		return valueid;
	}

	/**
	 * Sets the valueid.
	 * 
	 * @param valueid
	 *            the valueid to set
	 */
	public void setValueid(Integer valueid) {
		this.valueid = valueid;
	}

	/**
	 * Gets the comments.
	 * 
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	// Property accessors

}