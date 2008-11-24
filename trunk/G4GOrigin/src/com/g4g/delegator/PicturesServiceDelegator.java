/**********************************************************
 * PictureServiceDelegator.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.PicturesDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.PicturesService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The class PictureServiceDelegator delegates action calls to
 * PicturesServiceImplementation class via PicturesService interface.
 * PicturesServiceDelegator class is attached to PicturesService interface for
 * the purpose of pre and post method interception.
 * 
 * @author Jigar Mistry
 */
public class PicturesServiceDelegator {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	/** Gets the instance of PicturesService via ServiceLocator. */
	private static PicturesService service = ServiceLocator.getInstance()
			.getPicturesService();

	/**
	 * @param dto
	 * @return PicturesDTO
	 * @throws HibernateException
	 * @see com.g4g.services.PicturesService#add(com.g4g.dto.PicturesDTO)
	 */
	public static PicturesDTO add(PicturesDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.add(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.PicturesService#delete(PicturesDTO)
	 */
	public static void delete(PicturesDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);

		service.delete(dto);
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.PicturesService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PicturesDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		List<PicturesDTO> picturesList = service.getList();
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PICTUREDTO) .append(G4GConstants.ENDED).toString(),Level.INFO);

		return picturesList;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.PicturesService#getList(SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PicturesDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		
		List<PicturesDTO> picturesList = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PICTUREDTO) .append(G4GConstants.ENDED).toString(),Level.INFO);

		return picturesList;
	}

	/**
	 * @param dto
	 * @return PicturesDTO
	 * @throws HibernateException
	 * @see com.g4g.services.PicturesService#update(PicturesDTO)
	 */
	public static PicturesDTO update(PicturesDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PicturesServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		
		dto = service.update(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				PicturesServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}
}
