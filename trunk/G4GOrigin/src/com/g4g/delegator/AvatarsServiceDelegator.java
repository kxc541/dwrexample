/**********************************************************
 * AvatarsServiceDelegator.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.AvatarsService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class AvatarsServiceDelegator delegates action calls to
 * AvatarsServiceImplementation class via AvatarsService interface.
 * AvatarsServiceDelegator class is attached to AvatarsService interface for the
 * purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class AvatarsServiceDelegator {

	/** Gets the instance of AvatarsService via ServiceLocator. */
	private static AvatarsService service = ServiceLocator.getInstance()
			.getAvatarsServiceService();

	/**
	 * @param dto
	 * @return dto
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#add(com.g4g.dto.AvatarsDTO)
	 */
	public static AvatarsDTO add(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS ).append(G4GConstants.AVATARDTO ).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#delete(AvatarsDTO)
	 */
	public static void delete(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.AVATARSDTO ).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param id
	 * @return dto
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#getAvatars(int)
	 */
	public static AvatarsDTO getAvatars(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AvatarsDTO dto = service.getAvatars(id);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
							.append(G4GConstants.PARAMETERS).append(G4GConstants.ID).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#getList()
	 */
	public static List<AvatarsDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		List<AvatarsDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(AvatarsServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.ENDED).append(G4GConstants.AVATARDTO)
								.append(list.size()).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#getList(SearchCriteria)
	 */
	public static List<AvatarsDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(AvatarsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(), Level.INFO);
		List<AvatarsDTO> list = service.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(AvatarsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).append(G4GConstants.AVATARSDTO)
				.append(list.size()).toString(), Level.INFO);
				
		return list;
	}

	/**
	 * @param dto
	 * @return dto
	 * @throws HibernateException
	 * @see com.g4g.services.AvatarsService#update(AvatarsDTO)
	 */
	public static AvatarsDTO update(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(AvatarsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(), Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(AvatarsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.AVATARSDTO ).append(G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

}
