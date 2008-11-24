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

import com.g4g.utils.G4GConstants;

/**
 * TournamentResultNotification contains information about attributes required
 * for Tournaments result notification. When any tournament get completes, all
 * players who have participated in tournament will get message about result of
 * the tournament. This class extends BaseNotification.
 * 
 * @author Pratik
 */

public class TournamentResultNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The tournament id. This attribute contains tournament id of the
	 * tournament which get completed and result is generated.
	 */
	private int tournamentId;

	/** The match id. This attribute contains last match of tournament's id. */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * Instantiates a new tournament result notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param tournamentId -
	 *            the tournament id
	 * @param matchId -
	 *            the match id
	 */
	public TournamentResultNotification(int to, int from, int tournamentId,
			int matchId) {
		super(to, from);
		this.tournamentId = tournamentId;
		this.matchId = matchId;
		this.subject = G4GConstants.TOURNAMENT_RESULT_SUBJECT;
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

}
