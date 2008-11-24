/**********************************************************
 * PlayerServiceDelegator.java : 
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

import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayerStatus;
import com.g4g.dto.SearchCriteria;
import com.g4g.forms.ManagePlayerGameForm;
import com.g4g.forms.PlayerGameScheduleForm;
import com.g4g.services.PlayerService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The class PlayerServiceDelegator delegates action calls to
 * PlayerServiceImplementation class via PlayerService
 * interface.PlayerServiceDelegator class is attached to PlayerService interface
 * for the purpose of pre and post method interception.
 * 
 * @author Pratik
 */
public class PlayerServiceDelegator {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	/** Gets the instance of PlayerService via ServiceLocator. */
	private static PlayerService service = ServiceLocator.getInstance()
			.getPlayerService();

	/**
	 * @param dto
	 * @return PlayerDTO
	 * @throws HibernateException
	 * @see PlayerService#add(PlayerDTO)
	 */
	public static PlayerDTO add(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see PlayerService#delete(PlayerDTO)
	 */
	public static void delete(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		service.delete(dto);
	}
		
		

	/**
	 * @param id
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getGameWisePlayersList(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PlayerDTO> getGameWisePlayersList(int id)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<PlayerDTO> list = service.getGameWisePlayersList(id);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.GAMEWISEPLAYERSLIST).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);


		return list;
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PlayerDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERDTO_CAPS).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getList(SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PlayerDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerDTO> list = service.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERDTO_CAPS).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @param caseinsensitive
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PlayerDTO> getList(SearchCriteria searchCriteria,
			boolean caseinsensitive) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerDTO> list = service.getList(searchCriteria, caseinsensitive);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERDTO_CAPS).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param id
	 * @return PlayerDTO
	 * @throws HibernateException
	 * @see PlayerService#getPlayer(int)
	 */
	public static PlayerDTO getPlayer(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		PlayerDTO dto = service.getPlayer(id);
		 AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					PlayerServiceDelegator.class.getName() + G4GConstants.DASHES
					+ G4GConstants.CURRENTMETHOD
					+ DataUtil.getCurrentMethod()
					+ G4GConstants.CALLINGMETHOD
					+ DataUtil.getCallingMethod() + G4GConstants.DASHES
					+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param playerId
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getPlayerFriends(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<PlayerDTO> getPlayerFriends(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<PlayerDTO> list = service.getPlayerFriends(playerId);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERDTO_CAPS).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param playerId
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getPlayerGame(int)
	 */
	public static List<ManagePlayerGameForm> getPlayerGame(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<ManagePlayerGameForm> list = service.getPlayerGame(playerId);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERDTO_CAPS).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param id
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getPlayerRecentGames(int)
	 */
	public static List<PlayerGameScheduleForm> getPlayerRecentGames(int id)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerGameScheduleForm> list = service.getPlayerRecentGames(id);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYERGAME_SCHEDUDLE_FORM).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param id
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getPlayerSchedule(int)
	 */
	public static List<PlayerGameScheduleForm> getPlayerSchedule(int id)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerGameScheduleForm> list = service.getPlayerSchedule(id);

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.PLAYER_GAME_SCHEDULE).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param playerId
	 * @return List
	 * @throws HibernateException
	 * @see PlayerService#getPlayerVerifying(int)
	 */
	public static List<PlayerGameScheduleForm> getPlayerVerifying(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayerGameScheduleForm> list = service.getPlayerVerifying(playerId);

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.PLAYER_VERIFYING_GAMES_LIST_SIZE).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return PlayerDTO
	 * @throws HibernateException
	 * @see PlayerService#update(PlayerDTO)
	 */
	public static PlayerDTO update(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.update(dto);

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}

	/**
	 * @param screenName
	 * @return boolean
	 * @throws HibernateException
	 * @see PlayerService#isUserOnline(String)
	 */
	public static List<PlayerStatus> usersOnline()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		 
		return service.usersOnline();
	}

}
