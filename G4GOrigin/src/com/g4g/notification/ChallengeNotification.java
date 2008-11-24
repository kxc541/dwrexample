/**********************************************************
 * ChallengeNotification.java : 
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
 * ChallengeNotification contains information about attributes required for
 * challenge notification. When one player challenges another player for a match
 * for the game own by both, Challenge notification get generated. This class
 * extends BaseNotification.
 * 
 * @author Pratik
 */

public class ChallengeNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The match id. This attribute contains matchId of the match for which one
	 * player has challenged the other.
	 */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	private Date scheduleDate;

	/**
	 * Instantiates a new challenge notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param matchId -
	 *            the match id
	 * @param scheduleDate
	 */
	public ChallengeNotification(int to, int from, int matchId,
			Date scheduleDate) {
		super(to, from);
		this.matchId = matchId;
		this.subject = G4GConstants.CHALLENGE_SUBJECT;
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
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return this.scheduleDate;
	}

}
