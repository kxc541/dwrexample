/**********************************************************
 * WorldGamingNotification.java : 
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
 * WorldGamingNotification contains information about attributes required for
 * world gaming notification. This class extends BaseNotification.
 * 
 * @author Pratik
 */

public class WorldGamingNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The match id. */
	private int matchId;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * Instantiates a new world gaming notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param matchId -
	 *            the match id
	 */
	public WorldGamingNotification(int to, int from, int matchId) {
		super(to, from);
		this.matchId = matchId;
		this.subject = G4GConstants.WORLD_GAMING_SUBJECT;
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

}
