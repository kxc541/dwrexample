/**********************************************************
 * UpcomingMatchNotification.java : 
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
 * UpcomingMatchNotification contains information about attributes required for
 * Upcoming match notification. When player has match scheduled, before one or
 * two days, he/she gets notification for upcoming match. This class extends
 * BaseNotification.
 * 
 * @author Pratik
 */

public class UpcomingMatchNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The match id. This attribute contains match id for the upcoming match. */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	private String URL;

	private Date scheduleDate;

	/**
	 * Instantiates a new upcoming match notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param matchId -
	 *            the match id
	 * @param URL
	 * @param scheduleDate
	 */
	public UpcomingMatchNotification(int to, int from, int matchId, String URL,
			Date scheduleDate) {
		super(to, from);
		this.matchId = matchId;
		this.subject = G4GConstants.UPCOMING_MATCH_SUBJECT;
		this.URL = URL;
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
	 * @return the uRL
	 */
	public String getURL() {
		return this.URL;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return this.scheduleDate;
	}

}
