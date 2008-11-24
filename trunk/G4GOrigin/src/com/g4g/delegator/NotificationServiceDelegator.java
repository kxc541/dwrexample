/**********************************************************
 * NotificationServiceDelegator.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.notification.BaseNotification;
import com.g4g.services.NotificationService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class NotificationServiceDelegator delegates action calls to
 * NotificationServiceImplementation class via NotificationService interface.
 * NotificationServiceDelegator class is attached to NotificationService
 * interface for the purpose of pre and post method interception.
 * 
 * @author Pratik
 */
public class NotificationServiceDelegator {

	/** Gets the instance of NotificationService via ServiceLocator. */
	private static NotificationService service = ServiceLocator.getInstance()
			.getNotificationService();

	/**
	 * @param notification
	 * @param request
	 * @throws Exception 
	 * @throws HibernateException
	 * @see com.g4g.services.NotificationService#sendNotification(com.g4g.notification.BaseNotification,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public static void sendNotification(BaseNotification notification,
			HttpServletRequest request) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		service.sendNotification(notification, request);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.SESSIONID).append(request.getSession().getId())
				.append(G4GConstants.ENDED).toString(),
				Level.INFO);

	}

}
