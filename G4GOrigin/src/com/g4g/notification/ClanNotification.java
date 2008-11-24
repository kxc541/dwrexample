/**********************************************************
 * ClanNotification.java : 
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
 * ClanNotification contains information about attributes required for clan
 * notification. This class extends BaseNotification.
 * 
 * @author Pratik
 */

public class ClanNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subject. This attribute contains subject line of notification. */
	private String subject = G4GConstants.CLAN_SUBJECT;

	/**
	 * Instantiates a new clan notification.
	 * 
	 * @param to
	 *            the to
	 * @param from
	 *            the from
	 */
	public ClanNotification(int to, int from) {
		super(to, from);
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
