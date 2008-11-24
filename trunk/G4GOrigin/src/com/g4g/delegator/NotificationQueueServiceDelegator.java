/**********************************************************
 * NotificationQueueServiceDelegator.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.NotificationqueueDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.services.NotificationQueueService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationQueueServiceDelegator delegates action calls to
 * NotificationServiceImplementation class via NotificationService interface.
 * NotificationServiceDelegator class is attached to NotificationService
 * interface for the purpose of pre and post method interception.
 * 
 * @author Pratik
 */
public class NotificationQueueServiceDelegator {

	/** Gets the instance of NotificationQueueService via ServiceLocator. */
	private static NotificationQueueService service = ServiceLocator
			.getInstance().getNotificationQueueService();

	/**
	 * @param dto
	 * @return NotificationqueueDTO
	 * @throws HibernateException
	 * @see com.g4g.services.NotificationQueueService#add(com.g4g.dto.NotificationqueueDTO)
	 */
	public static NotificationqueueDTO add(NotificationqueueDTO dto)
			throws HibernateException {

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.add(dto);
		

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}

	/**
	 * @param dto 
	 * @throws HibernateException 
	 * @see com.g4g.services.NotificationQueueService#delete(NotificationqueueDTO)
	 */
	public static void delete(NotificationqueueDTO dto)
			throws HibernateException {

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.STARTED).toString(),Level.INFO);


		service.delete(dto);
		

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.ENDED).toString(),Level.INFO);

	}

	/**
	 * @return List<NotificationqueueDTO>
	 * @throws HibernateException 
	 * @see com.g4g.services.NotificationQueueService#getList()
	 */
	public static List<NotificationqueueDTO> getList()
			throws HibernateException {

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.STARTED).toString(),Level.INFO);

		List<NotificationqueueDTO> list = service.getList();
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.NOTIFICATION_QUEUE_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);


		return list;
	}

	/**
	 * @param searchListCriteria 
	 * @return List<NotificationqueueDTO>
	 * @throws HibernateException 
	 */
	public static List<NotificationqueueDTO> getList(
			SearchListCriteria searchListCriteria) throws HibernateException {

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.STARTED).toString(),Level.INFO);


		List<NotificationqueueDTO> list = service.getList(searchListCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.NOTIFICATION_QUEUE_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param dto 
	 * @return NotificationqueueDTO
	 * @throws HibernateException 
	 * @see com.g4g.services.NotificationQueueService#update(NotificationqueueDTO)
	 */
	public static NotificationqueueDTO update(NotificationqueueDTO dto)
			throws HibernateException {

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.STARTED).toString(),Level.INFO);


		dto = service.update(dto);
		

		AuditUtil.getInstance().writeLog(
		AuditUtil.FILE_TYPE_G4G,new StringBuffer(NotificationQueueServiceDelegator.class.getName())
		.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		.append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}

}
