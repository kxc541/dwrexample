/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * NotificationqueueDTO contains attribute for notoficationqueue entity.
 * 
 * @author Pratik
 */

public class NotificationqueueDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -418133862634870382L;

	/** The sourceplayer. */
	private PlayerDTO sourceplayer;
	
	/** The recipientplayer. */
	private PlayerDTO recipientplayer;
					  
	/** The notificationtype. */
	private NotificationtypeDTO notificationtype;
	
	/** The body. */
	private String body;
	
	/** The creationdate. */
	private Date creationdate;
	
	/** The expiredate. */
	private Date expiredate;
	
	/** The viewdate. */
	private Date viewdate;

	/**
	 * Gets the sourceplayer.
	 * 
	 * @return the sourceplayer
	 */
	public PlayerDTO getSourceplayer() {
		return sourceplayer;
	}

	/**
	 * Sets the sourceplayer.
	 * 
	 * @param sourceplayer the new sourceplayer
	 */
	public void setSourceplayer(PlayerDTO sourceplayer) {
		this.sourceplayer = sourceplayer;
	}

	/**
	 * Gets the recipientplayer.
	 * 
	 * @return the recipientplayer
	 */
	public PlayerDTO getRecipientplayer() {
		return recipientplayer;
	}

	/**
	 * Sets the recipientplayer.
	 * 
	 * @param recipientplayer the new recipientplayer
	 */
	public void setRecipientplayer(PlayerDTO recipientplayer) {
		this.recipientplayer = recipientplayer;
	}

	/**
	 * Gets the notificationtype.
	 * 
	 * @return the notificationtype
	 */
	public NotificationtypeDTO getNotificationtype() {
		return notificationtype;
	}

	/**
	 * Sets the notificationtype.
	 * 
	 * @param notificationtype the new notificationtype
	 */
	public void setNotificationtype(NotificationtypeDTO notificationtype) {
		this.notificationtype = notificationtype;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 * 
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the expiredate.
	 * 
	 * @return the expiredate
	 */
	public Date getExpiredate() {
		return expiredate;
	}

	/**
	 * Sets the expiredate.
	 * 
	 * @param expiredate the new expiredate
	 */
	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}

	/**
	 * Gets the viewdate.
	 * 
	 * @return the viewdate
	 */
	public Date getViewdate() {
		return viewdate;
	}

	/**
	 * Sets the viewdate.
	 * 
	 * @param viewdate the new viewdate
	 */
	public void setViewdate(Date viewdate) {
		this.viewdate = viewdate;
	}
}