/**********************************************************
* BaseNotification.java : 
*
* Created by Pratik
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.notification;

import java.io.Serializable;

/**
 * BaseNotification class, this is the super class of all notification type
 * classes. This class contains basic attributes for all notifications, 
 * to and from and its getter/setter. to and from are the playerIds of player 
 * to identify the notification is from which player to which player.
 * 
 * @author Pratik
 */

public class BaseNotification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The to. This variable contains playerId of the player who will
	 * get the notification.
	 *  */
	private int to;

	/** The from. This variable contains playerId of the player who will
	 * send/generate the notification. */
	private int from;

	/**
	 * Gets the value of to.
	 * 
	 * @return the to
	 */
	public int getTo() {
		return to;
	}

	/**
	 * Sets the value of to.
	 * 
	 * @param to - the new to
	 */
	public void setTo(int to) {
		this.to = to;
	}

	/**
	 * Gets the value of from.
	 * 
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * Sets the value of from.
	 * 
	 * @param from - the new from
	 */
	public void setFrom(int from) {
		this.from = from;
	}

	public BaseNotification(int to, int from) {
		super();
		this.to = to;
		this.from = from;
	}

}
