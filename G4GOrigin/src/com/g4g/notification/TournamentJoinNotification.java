/**********************************************************
 * className.java : 
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
 * TournamentJoinNotification contains information about attributes required for
 * Tournament Join Notification. When admin creates new tournament, a
 * notification get generated and all players who own this game, get message to
 * join the tournament.
 * 
 * @author pratik
 */

public class TournamentJoinNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The tournament id. This attribute contains tournament id of the new
	 * tournament.
	 */
	private int tournamentId;

	/** The match id. */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	private Date scheduleDate;

	/**
	 * Instantiates a new tournament join notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param tournamentId -
	 *            the tournament id
	 * @param matchId -
	 *            the match id
	 * @param scheduleDate
	 */
	public TournamentJoinNotification(int to, int from, int tournamentId,
			int matchId, Date scheduleDate) {
		super(to, from);
		this.tournamentId = tournamentId;
		this.matchId = matchId;
		this.subject = G4GConstants.TOURNAMENT_JOIN_SUBJECT;
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
	 * Gets the value of tournament id.
	 * 
	 * @return the tournament id
	 */
	public int getTournamentId() {
		return tournamentId;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return this.scheduleDate;
	}
}
