/**********************************************************
 * AdminNotificationServiceDelegator.java : 
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

import com.g4g.dto.AdminnotificationDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.AdminNotificationService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class AdminNotificationServiceDelegator delegates action calls to
 * AdminNotificationServiceImplementation class via AdminNotificationService
 * interface. AdminNotificationServiceDelegator class is attached to
 * AdminNotificationService interface for the purpose of pre and post method
 * interception.
 * 
 * @author Pratik
 */
public class AdminNotificationServiceDelegator {

	/** Gets the instance of AdminNotificationService via ServiceLocator. */
	private static AdminNotificationService service = ServiceLocator
			.getInstance().getAdminNotificationService();

	/**
	 * @param dto
     *          AdminDTO
	 * @return AdminnotificationDTO
	 * @throws HibernateException
	 * @see com.g4g.services.AdminNotificationService#add(com.g4g.dto.AdminnotificationDTO)
	 */
	public static AdminnotificationDTO add(AdminnotificationDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FeedbackServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(G4GConstants.ADMIN_NOTIFICATION_DTO ).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.AdminNotificationService#delete(AdminnotificationDTO)
	 */
	public static void delete(AdminnotificationDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FeedbackServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(G4GConstants.DELETE).append(
								G4GConstants.ENDED).toString(), Level.INFO);
	}

	/**
	 * @return List<AdminnotificationDTO>
	 * @throws HibernateException
	 * @see com.g4g.services.AdminNotificationService#getList()
	 */
	public static List<AdminnotificationDTO> getList()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<AdminnotificationDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ADMIN_LIST_SIZE).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List<AdminnotificationDTO>
	 * @throws HibernateException
	 * @see com.g4g.services.AdminNotificationService#getList(SearchCriteria)
	 */
	public static List<AdminnotificationDTO> getList(
			SearchCriteria searchCriteria) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<AdminnotificationDTO> list = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ADMIN_NOTIFICATION_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		
		return list;
	}

	/**
	 * @param dto
	 * @return AdminnotificationDTO
	 * @throws Exception
	 * @see com.g4g.services.AdminNotificationService#update(AdminnotificationDTO)
	 */
	public static AdminnotificationDTO update(AdminnotificationDTO dto)
			throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		return service.update(dto);
	}
}
