/**********************************************************
 * MatchResultNotification.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.notification;

import com.g4g.utils.G4GConstants;

/**
 * MatchResultNotification contains information about attributes required for
 * Match result notification. When match between two player get completed,
 * result of match get generated, and for that MatchResult notification get
 * generated. This class extends BaseNotification.
 * 
 * @author Pratik
 */

public class MatchResultNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The tournament id.This attribute contains tournament id of the match for
	 * which result get generated.
	 */
	private int tournamentId;

	/**
	 * The match id. This attribute contains match id of the match for which
	 * result get generated.
	 */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * Instantiates a new match result notification.
	 * 
	 * @param to
	 *            the to
	 * @param from
	 *            the from
	 * @param tournamentId
	 *            the tournament id
	 * @param matchId
	 *            the match id
	 */
	public MatchResultNotification(int to, int from, int tournamentId,
			int matchId) {
		super(to, from);
		this.tournamentId = tournamentId;
		this.matchId = matchId;
		this.subject = G4GConstants.MATCH_RESULT_SUBJECT;
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
	 * Gets the tournament id.
	 * 
	 * @return the value of tournament id
	 */
	public int getTournamentId() {
		return tournamentId;
	}

}
