/**********************************************************
 * FriendRequestNotification.java : 
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
 * The Class FriendRequestNotification contains information about attributes
 * required for Friends request notification. When one player requests another
 * player to be his/her friend, FriendRequest notification get generated. This
 * class extends BaseNotification.
 * 
 * @author Pratik
 */

public class FriendRequestNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * Instantiates a new friend request notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 */
	public FriendRequestNotification(int to, int from) {
		super(to, from);
		this.subject = G4GConstants.FRIEND_REQUEST_SUBJECT;
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
