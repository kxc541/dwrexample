/**********************************************************
 * TwoPlayerTournamentServiceDelegator.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.OpenMatchDTO;
import com.g4g.dto.PastTournamentDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.services.ServiceLocator;
import com.g4g.services.TwoPlayerTournamentService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class TwoPlayerTournamentServiceDelegator delegates action calls to
 * TwoPlayerTournamentServiceImplementation class via TwoPlayerTournamentService
 * interface.TwoPlayerTournamentServiceDelegator class is attached to
 * TwoPlayerTournamentService interface for the purpose of pre and post method
 * interception.
 * 
 * @author Punam
 */
public class TwoPlayerTournamentServiceDelegator {

	/** Gets the instance of TwoPlayerTournamentService via ServiceLocator. */
	private static TwoPlayerTournamentService service = ServiceLocator
			.getInstance().getTwoPlayerTournamentService();

	/**
	 * @param dto
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerTournamentService#add(com.g4g.dto.TwoplayertournamentDTO)
	 */
	public static TwoplayertournamentDTO add(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		TwoplayertournamentDTO twoplayertournamentDTO = service.add(dto);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return twoplayertournamentDTO;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 */
	public static void delete(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

	}

	/**
	 * @return List<TwoplayertournamentDTO>
	 * @throws HibernateException
	 */
	public static List<TwoplayertournamentDTO> getList()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		List<TwoplayertournamentDTO> list = service.getList();

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.TWO_PLAYER_TOURNAMENT_DTO).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 */
	public static List<TwoplayertournamentDTO> getList(
			SearchCriteria searchCriteria) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<TwoplayertournamentDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.TWO_PLAYER_TOURNAMENT_DTO).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 */
	public static List<TwoplayertournamentDTO> getList(
			SearchListCriteria searchCriteria) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<TwoplayertournamentDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.TWO_PLAYER_TOURNAMENT_DTO).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param dto
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException
	 */
	public static TwoplayertournamentDTO update(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		TwoplayertournamentDTO twoplayertournamentDTO = service.update(dto);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return twoplayertournamentDTO;
	}

	/**
	 * @param id
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException
	 */
	public static TwoplayertournamentDTO get(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		TwoplayertournamentDTO dto = new TwoplayertournamentDTO();
		dto.setId(id);
		dto = service.get(dto);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				TwoPlayerTournamentServiceDelegator.class.getName()
						+ G4GConstants.DASHES + G4GConstants.CURRENTMETHOD
						+ DataUtil.getCurrentMethod()
						+ G4GConstants.CALLINGMETHOD
						+ DataUtil.getCallingMethod() + G4GConstants.DASHES
						+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param twoplayertournamentDTO
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException
	 */
	public static TwoplayertournamentDTO get(
			TwoplayertournamentDTO twoplayertournamentDTO)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		TwoplayertournamentDTO dto = new TwoplayertournamentDTO();
		dto = service.get(twoplayertournamentDTO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return dto;
	}

	/**
	 * @param gameId
	 * @param playerId
	 * @return List
	 * @throws HibernateException
	 */
	public static List<OpenMatchDTO> getOpenMatchesDetails(int gameId,
			int playerId) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<OpenMatchDTO> list = service.getOpenMatchesDetails(gameId,
				playerId);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.OPENMATCHESDETAILS).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @return List<TournamentGameDTO>
	 * @throws HibernateException
	 * 
	 */
	public static List<TournamentGameDTO> getTournamentActions()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<TournamentGameDTO> list = service.getTournamentActions();

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.TOURNAMENTACTION).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @return List<PastTournamentDTO>
	 * @throws HibernateException
	 */
	public static List<PastTournamentDTO> getPastTournaments()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<PastTournamentDTO> list = service.getPastTournaments();

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.PASTTOURNAMENTS).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param tournamentId
	 * @return List
	 * @throws HibernateException
	 */
	public static List<PlayerDTO> getPlayersOfTournament(int tournamentId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<PlayerDTO> list = service.getPlayersOfTournament(tournamentId);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.PLAYERSOFTOURNAMENT_SMALL).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}
	public static Date getScheduledstartDate(int tournamentId)
		throws HibernateException {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(
							TwoPlayerTournamentGameOptionServiceDelegator.class
									.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod())
							.append(G4GConstants.DASHES).append(
									G4GConstants.STARTED).toString(), Level.INFO);
		Date date = service.getScheduledstartDate(tournamentId);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return date;
	}
	public static List<GameoptionsDTO> getGameOptionsOfTournament(
			int tournamentId) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		List<GameoptionsDTO> list = service
				.getGameOptionsOfTournament(tournamentId);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(
						TwoPlayerTournamentGameOptionServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod())
						.append(G4GConstants.DASHES).append(
								G4GConstants.PLAYERSOFTOURNAMENT_SMALL).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}
	
}
