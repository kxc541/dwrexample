/**********************************************************
 * className.java : 
 *
 * Created by 			
 * Last modified Date: 6 Jun .08 by Punam
 * Revision: 0.1
 * Version : 0.3.4076
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.services;

import javax.servlet.http.HttpServletRequest;

import com.g4g.notification.BaseNotification;

/**
 * NotificationService - contains signature for sendNotification method which
 * checks adminnotification table and accordingly send message, mail or popup
 * for that notification.
 * 
 * @author pratik
 */

public interface NotificationService {
	/**
	 * Method which checks adminnotification table and accordingly send message, mail or popup
	 * for that notification.
	 * @param notification - contains instance of type of notfication to be sent. 
	 * @param request
	 */
	public void sendNotification(BaseNotification notification, HttpServletRequest request) throws Exception;
	
}
