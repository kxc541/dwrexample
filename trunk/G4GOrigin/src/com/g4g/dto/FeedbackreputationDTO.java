/**********************************************************
* FeedbackreputationDTO.java : 
*
* Created by Punam
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * AdminDTO is the DTO class for Admin table in database. It contains reputation,
 * feedbackcomment etc. FeedbackReputation table is used to store player's reputation 
 * and feedback comments from other players to perticular player.
 * 
 * @author Punam
 * @see BaseDTO
 * @hibernate.mapping FeedbackreputationDTO.hbm.xml
 */
public class FeedbackreputationDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The twoplayermatch. */
	private TwoplayermatchDTO twoplayermatch;
	
	/** The player. */
	private PlayerDTO player;
	
	/** The reputation. */
	private Integer reputation;
	
	/** The israted. */
	private Boolean israted;
	
	/** The feedbackcomment. */
	private String feedbackcomment;
	
	/** The feedbackdate. */
	private Date feedbackdate;

	/**
	 * Gets the feedbackcomment.
	 * 
	 * @return the feedbackcomment
	 */
	public String getFeedbackcomment() {
		return feedbackcomment;
	}

	/**
	 * Gets the feedbackdate.
	 * 
	 * @return the feedbackdate
	 */
	public Date getFeedbackdate() {
		return feedbackdate;
	}

		
	/**
	 * Gets the reputation.
	 * 
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}

	/**
	 * Checks if is israted.
	 * 
	 * @return true, if is israted
	 */
	public boolean isIsrated() {
		return israted;
	}

	/**
	 * Sets the feedbackcomment.
	 * 
	 * @param feedbackcomment
	 *            the new feedbackcomment
	 */
	public void setFeedbackcomment(String feedbackcomment) {
		this.feedbackcomment = feedbackcomment;
	}

	/**
	 * Sets the feedbackdate.
	 * 
	 * @param feedbackdate
	 *            the new feedbackdate
	 */
	public void setFeedbackdate(Date feedbackdate) {
		this.feedbackdate = feedbackdate;
	}

	/**
	 * Sets the israted.
	 * 
	 * @param israted
	 *            the new israted
	 */
	public void setIsrated(boolean israted) {
		this.israted = israted;
	}

	/**
	 * Sets the reputation.
	 * 
	 * @param reputation
	 *            the new reputation
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	/**
	 * Gets the twoplayermatch.
	 * 
	 * @return the twoplayermatch
	 */
	public TwoplayermatchDTO getTwoplayermatch() {
		return twoplayermatch;
	}

	/**
	 * Sets the twoplayermatch.
	 * 
	 * @param twoplayermatch the new twoplayermatch
	 */
	public void setTwoplayermatch(TwoplayermatchDTO twoplayermatch) {
		this.twoplayermatch = twoplayermatch;
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
	 * Gets the israted.
	 * 
	 * @return the israted
	 */
	public Boolean getIsrated() {
		return israted;
	}

	/**
	 * Sets the israted.
	 * 
	 * @param israted the new israted
	 */
	public void setIsrated(Boolean israted) {
		this.israted = israted;
	}

	/**
	 * Sets the reputation.
	 * 
	 * @param reputation the new reputation
	 */
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
}
