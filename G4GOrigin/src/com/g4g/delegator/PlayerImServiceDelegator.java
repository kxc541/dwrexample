/**********************************************************
 * PlayerImServiceDelegator.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.PlayerimDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.PlayerImService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class PlayerImServiceDelegator delegates action calls to
 * PlayerImServiceImplementation class via PlayerImService interface.
 * PlayerImServiceDelegator class is attached to PlayerImService interface for
 * the purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class PlayerImServiceDelegator {

	private static final String UNCHEKED = "unchecked"; //$NON-NLS-1$
	/** Gets the instance of PlayerImService via ServiceLocator. */
	private static PlayerImService service = ServiceLocator.getInstance()
			.getPlayerImService();

	/**
	 * @param dto
	 * @return PlayerimDTO
	 * @throws HibernateException
	 * @see PlayerImService#add(PlayerimDTO)
	 */
	public static PlayerimDTO add(PlayerimDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see PlayerImService#delete(PlayerimDTO)
	 */
	public static void delete(PlayerimDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		service.delete(dto);
		
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see PlayerImService#getList()
	 */
	@SuppressWarnings(UNCHEKED)
	public static List getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List list = service.getList();
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.LIST_SMALL).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);


		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see PlayerImService#getList(SearchCriteria)
	 */
	public static List<PlayerimDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerimDTO> list = service.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERSIMDTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);


		return list;
	}

	/**
	 * @param dto
	 * @return PlayerimDTO
	 * @throws HibernateException
	 * @see PlayerImService#update(PlayerimDTO)
	 */
	public static PlayerimDTO update(PlayerimDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerImServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

}
