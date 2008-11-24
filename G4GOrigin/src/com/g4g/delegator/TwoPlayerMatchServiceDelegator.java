/**********************************************************
 * TwoPlayerMatchServiceDelegator.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.STARTED;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.MatchDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.services.ServiceLocator;
import com.g4g.services.TwoPlayerMatchService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class TwoPlayerMatchServiceDelegator delegates action calls to
 * TwoPlayerMatchServiceImplementation class via TwoPlayerMatchService
 * interface.TwoPlayerMatchServiceDelegator class is attached to
 * TwoPlayerMatchService interface for the purpose of pre and post method
 * interception.
 * 
 * @author Punam
 */
public class TwoPlayerMatchServiceDelegator {

	/** Gets the instance of TwoPlayerMatchService via ServiceLocator. */
	private static TwoPlayerMatchService service = ServiceLocator.getInstance()
			.getTwoPlayerMatchService();

	/**
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#add(com.g4g.dto.TwoplayermatchDTO)
	 */
	public static TwoplayermatchDTO add(TwoplayermatchDTO dto) {
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);
		dto = service.add(dto);

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(ENDED).toString(),
						Level.INFO);
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#delete(com.g4g.dto.TwoplayermatchDTO)
	 */
	public static void delete(TwoplayermatchDTO dto) throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);
		service.delete(dto);
	}

	/**
	 * 
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getList()
	 */
	public static List<TwoplayermatchDTO> getList() throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);
		List<TwoplayermatchDTO> list = service.getList();
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.PLAYERSOFTOURNAMENT).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getList(com.g4g.dto.SearchCriteria)
	 */
	public static List<TwoplayermatchDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		List<TwoplayermatchDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.PLAYERSOFTOURNAMENT).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getList(com.g4g.dto.SearchListCriteria)
	 */
	public static List<TwoplayermatchDTO> getList(
			SearchListCriteria searchCriteria) throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		List<TwoplayermatchDTO> list = service.getList(searchCriteria);
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.TWOPLAYERMATCHDTO_DB).append(
								G4GConstants.EQUAL).append(list.size()).append(
								G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#update(com.g4g.dto.TwoplayermatchDTO)
	 */
	public static TwoplayermatchDTO update(TwoplayermatchDTO dto)
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		dto = service.update(dto);

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(ENDED).toString(),
						Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#get(com.g4g.dto.TwoplayermatchDTO)
	 */
	public static TwoplayermatchDTO get(TwoplayermatchDTO dto)
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		dto = service.get(dto);

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(ENDED).toString(),
						Level.INFO);
		return dto;
	}

	/**
	 * @param id
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public static TwoplayermatchDTO getDTOFromId(int id)
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		TwoplayermatchDTO dto = service.getDTOFromId(id);

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
								.toString(), Level.INFO);
		return dto;
	}

	/**
	 * @param matchId
	 * @param request
	 * @return MatchDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getMatchInformation(int,HttpServletRequest)
	 */
	public static MatchDTO getMatchInformation(int matchId,
			HttpServletRequest request) throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		return service.getMatchInformation(matchId, request);
	}

	/**
	 * 
	 * @param tounamentId
	 * @param request
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getDtoForChallenge(int,
	 *      javax.servlet.http.HttpServletRequest) returns dto for player
	 *      specific to tournament level also checks its eligibility
	 */
	public static TwoplayermatchDTO getDtoForChallenge(int tounamentId,
			HttpServletRequest request) throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		TwoplayermatchDTO dto = service
				.getDtoForChallenge(tounamentId, request);
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).append(
								G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @param form
	 * @param dto
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#checkPlayerChallengeAvailability(com.g4g.forms.DirectedChallengeForm,PlayerDTO)
	 */
	public static List<TwoplayermatchDTO> checkPlayerChallengeAvailability(
			DirectedChallengeForm form, PlayerDTO dto)
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);

		return service.checkPlayerChallengeAvailability(form, dto);
	}

	/**
	 * 
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.TwoPlayerMatchService#getUpcomingMatches()
	 */
	public static List<TwoplayermatchDTO> getUpcomingMatches()
			throws HibernateException {

		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(TwoPlayerMatchServiceDelegator.class
								.getName()).append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(STARTED).toString(),
						Level.INFO);
		return service.getUpcomingMatches();
	}
}
