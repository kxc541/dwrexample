/**********************************************************
 * GameOptionServiceDelegator.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.GameOptionService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class GameOptionServiceDelegator delegates action calls to
 * GameOptionServiceImplementation class via GameOptionService interface.
 * GameOptionServiceDelegator class is attached to GameOptionService interface
 * for the purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class GameOptionServiceDelegator {

	/** Gets the instance of GameService via ServiceLocator. */
	private static GameOptionService service = ServiceLocator.getInstance()
			.getGameOptionService();

	/**
	 * @param dto
	 * @return GameoptionsDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#add(com.g4g.dto.GameoptionsDTO)
	 */
	public static GameoptionsDTO add(GameoptionsDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
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
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.GAME_OPTION_DTO)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#delete(GameoptionsDTO)
	 */
	public static void delete(GameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
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
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

	}

	/**
	 * @param dto
	 * @return GameoptionsDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#getGame(GameoptionsDTO)
	 */
	public static GameoptionsDTO getGame(GameoptionsDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		dto = service.getGame(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return dto;
	}

	/**
	 * @return List<GameoptionsDTO>
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#getList()
	 */
	public static List<GameoptionsDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<GameoptionsDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.GAME_OPTION_DTO).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List<GameoptionsDTO>
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#getList(SearchCriteria)
	 */
	public static List<GameoptionsDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<GameoptionsDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.GAME_OPTION_DTO).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return GameoptionsDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameOptionService#update(GameoptionsDTO)
	 */
	public static GameoptionsDTO update(GameoptionsDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return dto;
	}

	/**
	 * It sets game option list's in request object with names
	 * G4GConstants.TOTAL_LIST, (i - 1)
	 * 
	 * @param request
	 * @param gameid
	 * @throws HibernateException
	 */
	public static void setgameOptionListInRequest(HttpServletRequest request,
			int gameid) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		service.setgameOptionListInRequest(request, gameid);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.REQUEST)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

	}

	/**
	 * @param gameid
	 * @return HashMap<String, List<GameoptionsDTO>>
	 * @throws HibernateException
	 */
	public static HashMap<String, List<GameoptionsDTO>> getGameOptionListOfGame(
			int gameid) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.GAME_OPTION_DTO)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return service.getGameOptionListOfGame(gameid);
	}

	/**
	 * Returns gameoptions of a game for a given no of value id
	 * 
	 * @param gameid
	 * @param optionsequenceid
	 * @param searchCriteria
	 * @return List<GameoptionsDTO>
	 * @throws HibernateException
	 */
	public static List<GameoptionsDTO> getGameOptions(int gameid,int optionsequenceid, SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameOptionServiceDelegator.class.getName())
						.append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		return service.getGameOptions(gameid, optionsequenceid, searchCriteria);
	}

}
