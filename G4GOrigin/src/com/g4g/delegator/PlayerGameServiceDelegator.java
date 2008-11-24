/**********************************************************
 * PlayerGameServiceDelegator.java : 
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

import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.PlayerGameService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class PlayerGameServiceDelegator delegates action calls to
 * PlayerGameServiceImplementation class via PlayerGameService interface.
 * PlayerGameServiceDelegator class is attached to PlayerGameService interface
 * for the purpose of pre and post method interception.
 * 
 * @author Punam
 */
public class PlayerGameServiceDelegator {

	/** Gets the instance of PlayerGameService via ServiceLocator. */
	private static PlayerGameService service = ServiceLocator.getInstance()
			.getPlayerGameService();

	/**
	 * @param dto
	 * @return PlayergameDTO
	 * @throws HibernateException
	 * @see com.g4g.services.PlayerGameService#add(com.g4g.dto.PlayergameDTO)
	 */
	public static PlayergameDTO add(PlayergameDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.PlayerGameService#delete(PlayergameDTO)
	 */
	public static void delete(PlayergameDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		service.delete(dto);
		 
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.PlayerGameService#getList()
	 */
	public static List<PlayergameDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayergameDTO> list = service.getList();

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.PLAYER_GAME_DTO).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.PlayerGameService#getList(SearchCriteria)
	 */
	public static List<PlayergameDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayergameDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.PLAYER_GAME_DTO).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param dto
	 * @return PlayergameDTO
	 * @throws HibernateException
	 * @see com.g4g.services.PlayerGameService#update(PlayergameDTO)
	 */
	public static PlayergameDTO update(PlayergameDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerGameServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

}
