/**********************************************************
 * MatchConfirmNotification.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.notification;

import java.util.Date;

import com.g4g.utils.G4GConstants;

/**
 * The Class MatchConfirmNotification contains information about attributes
 * required for match confirm notification. When one player has challenged
 * another player for a match and the another player accept/reject challenge,
 * MatchConfirm notification get generated. This class extends BaseNotification.
 * 
 * @author Pratik
 */
public class MatchConfirmNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The match id. This attribute contains match id of the match for which
	 * player has accept/reject the challenge.
	 */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/** Match Confirmation id 1=Accept 2=Reject */
	private int confirmationId;

	private Date scheduleDate;

	/**
	 * Instantiates a new match confirm notification.
	 * 
	 * @param to
	 *            the to
	 * @param from
	 *            the from
	 * @param matchId
	 *            the match id
	 * @param confirmationId
	 * @param scheduleDate
	 */
	public MatchConfirmNotification(int to, int from, int matchId,
			int confirmationId, Date scheduleDate) {
		super(to, from);
		this.matchId = matchId;
		this.confirmationId = confirmationId;
		this.subject = G4GConstants.CHALLENGE_CONFIRMCANCEL_SUBJECT;
		this.scheduleDate = scheduleDate;
	}

	/**
	 * Gets the value of match id.
	 * 
	 * @return the match id
	 */
	public int getMatchId() {
		return matchId;
	}

	/**
	 * Gets the value of subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the confirmationId
	 */
	public int getConfirmationId() {
		return this.confirmationId;
	}

	/**
	 * @param confirmationId
	 *            the confirmationId to set
	 */
	public void setConfirmationId(int confirmationId) {
		this.confirmationId = confirmationId;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return this.scheduleDate;
	}

}
