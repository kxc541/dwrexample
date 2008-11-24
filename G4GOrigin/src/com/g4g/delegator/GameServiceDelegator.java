/**********************************************************
 * GameServiceDelegator.java : 
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

import com.g4g.dto.GameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.GameService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class GameServiceDelegator delegates action calls to
 * GameServiceImplementation class via GameService interface.
 * GameServiceDelegator class is attached to GameService interface for the
 * purpose of pre and post method interception.
 * 
 * @author Punam
 */
public class GameServiceDelegator {

	/** Gets the instance of GameService via ServiceLocator. */
	private static GameService service = ServiceLocator.getInstance()
			.getGameService();

	/**
	 * @param dto
	 * @return GameDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#add(com.g4g.dto.GameDTO)
	 */

	public static GameDTO add(GameDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#delete(GameDTO)
	 */
	public static void delete(GameDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
	}

	/**
	 * @param id
	 * @return GameDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#getGame(int)
	 */
	public static GameDTO getGame(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		GameDTO dto = service.getGame(id);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param playerGameId
	 * @param networkId
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#getList(String[], int)
	 */
	public static List<GameDTO> getGameList(String[] playerGameId, int networkId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return service.getList(playerGameId, networkId);
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#getList()
	 */
	public static List<GameDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		List<GameDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#getList(SearchCriteria)
	 */
	public static List<GameDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		List<GameDTO> list = service.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return list;
	}

	/**
	 * @param dto
	 * @return GameDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameService#update(GameDTO)
	 */
	public static GameDTO update(GameDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				GameServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}
}
