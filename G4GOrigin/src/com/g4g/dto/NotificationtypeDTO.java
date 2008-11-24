/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * NotificationtypeDTO contains attribute for notification entity.
 * 
 * @author Jigar Mistry
 */
public class NotificationtypeDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1232185369236275626L;
	
	/** The name. */
	private String name;
	
	/** The notificationqueues. */
	private Set notificationqueues = new HashSet(0);
	
	/** The adminnotifications. */
	private Set adminnotifications = new HashSet(0);

	/**
	 * @param id 
	 * 
	 */
	public NotificationtypeDTO(int id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	public NotificationtypeDTO() {
		super();
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the notificationqueues.
	 * 
	 * @return the notificationqueues
	 */
	public Set getNotificationqueues() {
		return notificationqueues;
	}

	/**
	 * Sets the notificationqueues.
	 * 
	 * @param notificationqueues the new notificationqueues
	 */
	public void setNotificationqueues(Set notificationqueues) {
		this.notificationqueues = notificationqueues;
	}

	/**
	 * Gets the adminnotifications.
	 * 
	 * @return the adminnotifications
	 */
	public Set getAdminnotifications() {
		return adminnotifications;
	}

	/**
	 * Sets the adminnotifications.
	 * 
	 * @param adminnotifications the new adminnotifications
	 */
	public void setAdminnotifications(Set adminnotifications) {
		this.adminnotifications = adminnotifications;
	}

}