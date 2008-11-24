/**********************************************************
 * AdminnotificationDTO.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.dto;

// TODO: Auto-generated Javadoc
/**
 * AdminnotificationDTO is the DTO class for AdminNotification table in
 * database. It contains notificationtypeid, email etc. attributes.
 * AdminNotification table contains information about, for perticular
 * notification type email, popup or internal message should send or not.
 * 
 * @author Pratik
 */
public class AdminnotificationDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The notificationtypeid. This attribute represents notificationtypeid from
	 * notificationtype table.
	 */
	private NotificationtypeDTO notificationtype;

	/**
	 * The email. This attribute contains true/false value. True - should send
	 * email for notification. False - should not send email for notification.
	 */
	private Boolean email;

	/**
	 * The popup. This attribute contains true/false value. True - should send
	 * popup for notification. False - should not send popup for notification.
	 */
	private Boolean popup;

	/**
	 * The message. This attribute contains true/false value. True - should send
	 * message for notification. False - should not send message for
	 * notification.
	 */
	private Boolean message;

	/**
	 * Checks if is email.
	 * 
	 * @return the boolean
	 */
	public Boolean isEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email the new email
	 */
	public void setEmail(Boolean email) {
		this.email = email;
	}

	/**
	 * Checks if is popup.
	 * 
	 * @return the boolean
	 */
	public Boolean isPopup() {
		return popup;
	}

	/**
	 * Sets the popup.
	 * 
	 * @param popup the new popup
	 */
	public void setPopup(Boolean popup) {
		this.popup = popup;
	}

	/**
	 * Checks if is message.
	 * 
	 * @return the boolean
	 */
	public Boolean isMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(Boolean message) {
		this.message = message;
	}

	/**
	 * @return the notificationtype
	 */
	public NotificationtypeDTO getNotificationtype() {
		return this.notificationtype;
	}

	/**
	 * @param notificationtype
	 *            the notificationtype to set
	 */
	public void setNotificationtype(NotificationtypeDTO notificationtype) {
		this.notificationtype = notificationtype;
	}

}
