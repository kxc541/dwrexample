/**********************************************************
 * MessageNotification.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.notification;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;

/**
 * MessageNotification contains information about attributes required for
 * Message Notification. When one player sends message from internal mail center
 * to another player, Message notification get generated. This class extends
 * BaseNotification.
 * 
 * @author Pratik
 */

public class MessageNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The body. This attribute contains body area or actual message of internal
	 * mail.
	 */
	private String body;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * Instantiates a new message notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param subject -
	 *            the subject
	 * @param body -
	 *            the body
	 */
	public MessageNotification(int to, int from, String subject, String body) {
		super(to, from);
		this.body = G4GConstants.BLANK;
		this.subject = G4GConstants.BLANK;

		try {
			this.body = URLEncoder.encode(body, G4GConstants.UTF8);
			this.subject = URLEncoder.encode(subject, G4GConstants.UTF8);
		} catch (UnsupportedEncodingException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}
	}

	/**
	 * Gets the value of body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
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
