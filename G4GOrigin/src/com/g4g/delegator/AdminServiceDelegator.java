/**********************************************************
 * AdminServiceDelegator.java : 
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

import com.g4g.dto.AdminDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.AdminService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class AdminServiceDelegator delegates action calls to
 * AdminServiceImplementation class via AdminService interface.
 * AdminServiceDelegator class is attached to AdminService interface for the
 * purpose of pre and post method interception.
 * 
 * @author Pratik
 */
public class AdminServiceDelegator {

	/** Gets the instance of AdminService via ServiceLocator. */
	private static AdminService service = ServiceLocator.getInstance()
			.getAdminService();

	/**
	 * @param dto
	 * @return dto
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#add(com.g4g.dto.AdminDTO)
	 */
	public static AdminDTO add(AdminDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(G4GConstants.DASHES)
								.append(G4GConstants.ADMINDTO).append(G4GConstants.ENDED).toString(), Level.INFO);

		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#delete(AdminDTO)
	 */
	public static void delete(AdminDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(G4GConstants.DASHES).append(
								G4GConstants.ENDED).toString(), Level.INFO);

	}

	/**
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#getList()
	 */
	public static List<AdminDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						AdminServiceDelegator.class.getName()).append(G4GConstants.DASHES
						).append(G4GConstants.CURRENTMETHOD
						).append(DataUtil.getCurrentMethod()
						).append(G4GConstants.CALLINGMETHOD
						).append(DataUtil.getCallingMethod()).append(G4GConstants.DASHES
						).append(G4GConstants.STARTED).toString(), Level.INFO);

		List<AdminDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
										G4GConstants.DASHES).append(
								G4GConstants.ENDED).append(
								G4GConstants.ADMINLIST_SIZE)
						.append(list.size()).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#getList(SearchCriteria)
	 */
	public static List<AdminDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(), Level.INFO);

		List<AdminDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AdminServiceDelegator.class.getName()).append(
						G4GConstants.DASHES).append(G4GConstants.CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
										G4GConstants.DASHES).append(
								G4GConstants.ENDED).append(
								G4GConstants.ADMINLIST_SIZE)
						.append(list.size()).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return AdminDTO
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#update(AdminDTO)
	 */
	public static AdminDTO update(AdminDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.update(dto);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

}
