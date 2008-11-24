/**********************************************************
 * className.java :
 *
 * Created by
 * Last modified Date: 6 Jun .08 by Punam
 * Revision: 0.1
 * Version : 0.3.4076
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.services;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.COMMA;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAMEID;
import static com.g4g.utils.G4GConstants.ID;
import static com.g4g.utils.G4GConstants.MIN_TIERS;
import static com.g4g.utils.G4GConstants.OPEN_MATCHES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.PASTTOURNAMENTLIST;
import static com.g4g.utils.G4GConstants.PLAYERID;
import static com.g4g.utils.G4GConstants.PLAYERS_OF_TOURNAMENT_LIST_SIZE;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_UPDATED;
import static com.g4g.utils.G4GConstants.RECORD_UPDATED;
import static com.g4g.utils.G4GConstants.SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.SEARCH_LIST_CRITERIA;
import static com.g4g.utils.G4GConstants.SET_OPEN_MATCH_DTO;
import static com.g4g.utils.G4GConstants.SET_PASTTOURNAMENT_DTO;
import static com.g4g.utils.G4GConstants.SET_PLAYER_LIST;
import static com.g4g.utils.G4GConstants.SET_TOURNAMENT_GAME_DTO;
import static com.g4g.utils.G4GConstants.SET_TWOPLAYERMATCH_DTO_WITH_WINNER_DETAILS;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TOURNAMENT_LIST_SIZE;
import static com.g4g.utils.G4GConstants.TOURNAMENT_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT_DTO;
import static com.g4g.utils.G4GConstants.UNPLAYED_TOURNAMENT_LIST;
import static com.g4g.utils.G4GConstants.*;
import static com.g4g.utils.SQLConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.OpenMatchDTO;
import com.g4g.dto.PastTournamentDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;
import com.g4g.utils.TournamentsUtil;

/**
 * Class contains method implementation related to twoplayertournament.
 *
 * @author Ankur
 *
 */
public class TwoPlayerTournamentServiceImpl implements
		TwoPlayerTournamentService {

	private static final String UNUSED = "unused"; //$NON-NLS-1$
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#add(com.g4g.dto.TwoplayertournamentDTO)
	 */
	public TwoplayertournamentDTO add(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWO_PLAYER_TOURNAMENT_DTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(RECORD_ADDED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(RECORD_NOT_ADDED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).append(DASHES).append(
									RECORD_NOT_ADDED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#delete(com.g4g.dto.TwoplayertournamentDTO)
	 */
	public void delete(TwoplayertournamentDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWO_PLAYER_TOURNAMENT_DTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(RECORD_DELETED).toString(), Level.INFO);
		} catch (HibernateException e) {
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(RECORD_NOT_DELETED).toString(),
							Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(RECORD_NOT_DELETED).toString(),
							Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayertournamentDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(new StringBuffer(SQLConstants.FROM
				+ TWOPLAYERTOURNAMENTDTO_SQL).toString());
		Transaction transaction = session.beginTransaction();
		List<TwoplayertournamentDTO> list = query.list();
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(TOURNAMENT_LIST_SIZE).append(list.size())
						.toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayertournamentDTO> getList(SearchCriteria searchCriteria) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PARAMETERS).append(SEARCH_CRITERIA).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session
				.createCriteria(TwoplayertournamentDTO.class);
		String orderBY = searchCriteria.getOrderBy();
		if (orderBY != null) {
			if (searchCriteria.isAsc()) {
				criteria.addOrder(Order.asc(orderBY));
			} else {
				criteria.addOrder(Order.desc(orderBY));
			}
		}
		Set<String> keys = searchCriteria.getAttributeNames();
		Iterator<String> it = keys.iterator();
		String key = null;
		Object value = null;
		while (it.hasNext()) {
			key = it.next();
			value = searchCriteria.getAttribute(key);
			criteria.add(Restrictions.eq(key, value));
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(key).append(DASHES).append(value)
							.toString(), Level.INFO);
		}
		Transaction transaction = session.beginTransaction();
		List<TwoplayertournamentDTO> list = new ArrayList<TwoplayertournamentDTO>();
		try {
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			for (String keyexception : keys) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
						keyexception, Level.ERROR);
			}
			throw e;
		} finally {
			session.flush();
			HibernateUtil.closeSession();
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(TOURNAMENT_LIST_SIZE_CRITERIA).append(
								list.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#update(com.g4g.dto.TwoplayertournamentDTO)
	 */
	public TwoplayertournamentDTO update(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PARAMETERS).append(TWO_PLAYER_TOURNAMENT_DTO)
						.toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(RECORD_UPDATED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(RECORD_NOT_UPDATED).toString(),
							Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).toString(),
					Level.ERROR);
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(RECORD_NOT_UPDATED).toString(),
							Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).toString(),
					Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#get(com.g4g.dto.TwoplayertournamentDTO)
	 */
	public TwoplayertournamentDTO get(TwoplayertournamentDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PARAMETERS).append(TWO_PLAYER_TOURNAMENT_DTO)
						.toString(), Level.INFO);

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		dto = (TwoplayertournamentDTO) session.get(
				TwoplayertournamentDTO.class, dto.getId());
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(TOURNAMENT_ID).append(COLON_WITH_SPACES).append(
								dto.getId()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getOpenMatchesDetails(int,
	 *      int)
	 */
	@SuppressWarnings( { UNUSED, UNCHECKED })
	public List<OpenMatchDTO> getOpenMatchesDetails(int gameId, int playerId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERID).append(COLON_WITH_SPACES).append(playerId)
						.append(COMMA).append(GAMEID).append(COLON_WITH_SPACES).append(
								gameId).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		SearchCriteria searchCriteria = new SearchCriteria();
		StringBuffer stringQuery = new StringBuffer();
		List<OpenMatchDTO> openMatches = new ArrayList<OpenMatchDTO>();
		try {
			stringQuery.append(SELECT).append(DISTINCT).append(TM).append(DOT)
					.append(PLAYERONEID);
			stringQuery.append(SQLConstants.COMMA).append(TT).append(DOT)
					.append(TOURNAMENTID_SQL);
			stringQuery.append(SQLConstants.COMMA).append(TM).append(DOT)
					.append(PAYOUTAMOUNT_SQL);
			stringQuery.append(SQLConstants.COMMA).append(TM).append(DOT)
					.append(MATCHID_SQL);
			stringQuery.append(SQLConstants.FROM).append(PLAYER_SQL).append(P);
			stringQuery.append(SQLConstants.COMMA).append(TWOPLAYERTOURNAMENT_SQL)
					.append(TT);
			stringQuery.append(SQLConstants.COMMA).append(TWOPLAYERMATCH_SQL)
					.append(TM);
			stringQuery.append(SQLConstants.COMMA).append(PLAYERGAME_SQL)
					.append(SQLConstants.PG);
			stringQuery.append(SQLConstants.WHERE).append(TM).append(DOT).append(
					TOURNAMENTID_SQL);
			stringQuery.append(SQLConstants.EQUAL).append(TT).append(DOT)
					.append(TOURNAMENTID_SQL);
			stringQuery.append(SQLConstants.AND).append(TT).append(DOT).append(
					LEVELS_SQL);
			stringQuery.append(SQLConstants.EQUAL).append(ONE).append(
					SQLConstants.AND);
			stringQuery.append(TM).append(DOT).append(PLAYERONEID).append(IS)
					.append(NOT).append(NULL);
			stringQuery.append(SQLConstants.AND).append(P).append(DOT).append(
					PLAYERID_SQL);
			stringQuery.append(SQLConstants.EQUAL).append(playerId);
			stringQuery.append(SQLConstants.AND).append(TM).append(DOT).append(
					PLAYERTWOID);
			stringQuery.append(IS).append(NULL).append(SQLConstants.AND);
			stringQuery.append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL);
			stringQuery.append(GREATER).append(NOW_SQL).append(SQLConstants.AND);
			stringQuery.append(TT).append(DOT).append(GAMEID_SQL).append(
					SQLConstants.EQUAL);
			stringQuery.append(gameId).append(SQLConstants.AND);
			stringQuery.append(PG).append(DOT).append(PLAYERID_SQL).append(
					SQLConstants.EQUAL);
			stringQuery.append(P).append(DOT).append(PLAYERID_SQL).append(
					SQLConstants.AND);
			stringQuery.append(PG).append(DOT).append(GAMEID_SQL).append(
					SQLConstants.EQUAL);
			stringQuery.append(TT).append(DOT).append(GAMEID_SQL).append(
					SQLConstants.AND);
			stringQuery.append(TM).append(DOT).append(COMPLETEDDATE_SQL).append(IS)
					.append(NULL);
			stringQuery.append(SQLConstants.AND).append(TM).append(DOT).append(
					GAMECOMPLETED_SQL);
			stringQuery.append(SQLConstants.EQUAL).append(ZEROSTRING);

			Transaction transaction = session.beginTransaction();
			List<Object> openMatchList = session.createSQLQuery(
					stringQuery.toString()).list();

			for (int index = G4GConstants.ZERO; index < openMatchList.size(); index++) {
				Object[] obj = (Object[]) openMatchList.get(index);
				OpenMatchDTO openMatchDTO = new OpenMatchDTO();
				searchCriteria.removeAllAttribute();
				TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
				if (obj[G4GConstants.THREE_NUMBER] != null) {
					searchCriteria.setAttribute(ID, Integer
							.parseInt(obj[G4GConstants.ONE_NUMBER].toString()));
					twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator
							.getList(searchCriteria).get(G4GConstants.ZERO);
				}
				openMatchDTO.setTwoplayertournamentDTO(twoplayertournamentDTO);
				Double payoutamount = Double
						.parseDouble(obj[G4GConstants.TWO_NUMBER].toString());
				openMatchDTO.setPayoutamount(payoutamount.longValue());
				openMatchDTO.setMatchid(Integer
						.parseInt(obj[G4GConstants.THREE_NUMBER].toString()));
				openMatchDTO.setPlayerDTO(PlayerServiceDelegator
						.getPlayer(Integer.parseInt(obj[G4GConstants.ZERO]
								.toString())));
				openMatches.add(openMatchDTO);
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SET_OPEN_MATCH_DTO).toString(), Level.INFO);
		} catch (HibernateException exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(OPEN_MATCHES_LIST_SIZE).append(
								openMatches.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);

		return openMatches;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getTournamentActions()
	 *
	 */

	@SuppressWarnings(UNCHECKED)
	public List<TournamentGameDTO> getTournamentActions() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		List<TournamentGameDTO> tournamentGameList = new ArrayList<TournamentGameDTO>();
		Session session = HibernateUtil.getSession();
		StringBuffer stringQuery = new StringBuffer();
		try {
			Transaction transaction = session.beginTransaction();
			SearchCriteria searchCriteria = new SearchCriteria();
			/* List of tournaments to be played */

			stringQuery.append(SELECT).append(T).append(DOT).append(ASTERISTIC)
					.append(SQLConstants.FROM);
			stringQuery.append(TWOPLAYERTOURNAMENT_SQL).append(T).append(
					SQLConstants.WHERE);
			stringQuery.append(T).append(DOT).append(LEVELS_SQL).append(GREATER)
					.append(MIN_TIERS);
			stringQuery.append(SQLConstants.AND).append(T).append(DOT).append(
					COMPLETIONDATE_SQL);
			stringQuery.append(IS).append(NULL).append(SQLConstants.AND);
			stringQuery.append(T).append(DOT).append(CANCELLATIONDATE_SQL);
			stringQuery.append(IS).append(NULL).append(SQLConstants.AND);
			stringQuery.append(OPEN_PAREN).append(OPEN_PAREN).append(SELECT);
			stringQuery.append(MIN).append(OPEN_PAREN).append(SQLConstants.M).append(DOT);
			stringQuery.append(SCHEDULEDSTARTDATE_SQL).append(CLOSE_PAREN).append(
					SQLConstants.FROM);
			stringQuery.append(TWOPLAYERMATCH_SQL).append(SQLConstants.M).append(
					SQLConstants.WHERE);
			stringQuery.append(SQLConstants.M).append(DOT).append(TOURNAMENTID_SQL).append(
					SQLConstants.EQUAL);
			stringQuery.append(T).append(DOT).append(TOURNAMENTID_SQL).append(
					CLOSE_PAREN);
			stringQuery.append(GREATER_EQUAL).append(CURRENT_TIMESTAMP).append(
					CLOSE_PAREN);

			List<TwoplayertournamentDTO> tournamentGames = session
					.createSQLQuery(stringQuery.toString()).addEntity(
							TwoplayertournamentDTO.class).list();
			Date scheculedstartdate;
			transaction.commit();
			HibernateUtil.closeSession();
			int totaltournaments = tournamentGames.size();
			if (totaltournaments > G4GConstants.ZERO) {
				for (int index = G4GConstants.ZERO; index < totaltournaments; index++) {
					TwoplayertournamentDTO twoplayertournamentDTO = tournamentGames
							.get(index);
					TournamentGameDTO tournamentGameDTO = new TournamentGameDTO();
					// setting values in tournamentGameDTO
					tournamentGameDTO
							.setTwoplayertournamentDTO(twoplayertournamentDTO);
					tournamentGameDTO
							.setGameOptionsList(new HashSet<GameoptionsDTO>());
					searchCriteria.removeAllAttribute();
					tournamentGameDTO.setGameDTO(GameServiceDelegator
							.getGame(twoplayertournamentDTO.getGameDTO()
									.getId()));

					tournamentGameDTO.setNo_of_players(TournamentsUtil
							.getNoOfPlayers(tournamentGameDTO
									.getTwoplayertournamentDTO().getLevels()));
					Double entryfee = tournamentGameDTO
							.getTwoplayertournamentDTO().getEntryfee();
					Double housefeeperplayer = tournamentGameDTO
							.getTwoplayertournamentDTO().getHousefeeperplayer();
					scheculedstartdate = getScheduledstartDate(tournamentGameDTO
							.getTwoplayertournamentDTO().getId());
					tournamentGameDTO.setScheduledstartdate(scheculedstartdate);

					searchCriteria.removeAllAttribute();
					searchCriteria.setAttribute(TWO_PLAYER_TOURNAMENT,
							tournamentGameDTO.getTwoplayertournamentDTO());
					tournamentGameDTO
							.setTwoPlayerMatchList(TwoPlayerMatchServiceDelegator
									.getList(searchCriteria));
					// GameOptions List
					searchCriteria.removeAllAttribute();
					searchCriteria.setAttribute(TOURNAMENT_ID,
							tournamentGameDTO.getTwoplayertournamentDTO());
					List<TwoplayertournamentgameoptionsDTO> list = TwoPlayerTournamentGameOptionServiceDelegator
							.getList(searchCriteria);
					int totalTounamentGameOptions = list.size();
					// Retriving GameoptionsDTO to display gameoptions
					for (int tournamentIndex = G4GConstants.ZERO; tournamentIndex < totalTounamentGameOptions; tournamentIndex++) {
						TwoplayertournamentgameoptionsDTO twoplayertournamentgameoptionsDTO = list
								.get(tournamentIndex);

						// three pk optionId , gameId , valueId
						GameoptionsDTO gameoptionsDTO = new GameoptionsDTO();
						gameoptionsDTO.setGameid(tournamentGameDTO.getGameDTO()
								.getId());
						gameoptionsDTO.setId(twoplayertournamentgameoptionsDTO
								.getOptionid());
						gameoptionsDTO
								.setValueid(twoplayertournamentgameoptionsDTO
										.getValueid());
						gameoptionsDTO = GameOptionServiceDelegator
								.getGame(gameoptionsDTO);

						// set the gameoptionsDTO to gameOption list
						tournamentGameDTO.getGameOptionsList().add(
								gameoptionsDTO);
					}
					tournamentGameList.add(tournamentGameDTO);
				}
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SET_TOURNAMENT_GAME_DTO).toString(),
					Level.INFO);
		} catch (HibernateException exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(UNPLAYED_TOURNAMENT_LIST).append(
								tournamentGameList.size()).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return tournamentGameList;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getPastTournaments()
	 */

	@SuppressWarnings(UNCHECKED)
	public List<PastTournamentDTO> getPastTournaments()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		List<PastTournamentDTO> pastTournamentList = new ArrayList<PastTournamentDTO>();
		SearchCriteria searchCriteria = new SearchCriteria();
		Session session = HibernateUtil.getSession();
		try {
			Transaction transaction = session.beginTransaction();
			// All past tournaments retrived
			StringBuffer query = new StringBuffer();
			query.append(SQLConstants.FROM).append(TWOPLAYERTOURNAMENTDTO_SQL)
					.append(SQLConstants.WHERE);
			query.append(COMPLETIONDATE_SQL).append(IS).append(NOT).append(NULL)
					.append(SQLConstants.AND);
			query.append(LEVELS_SQL).append(GREATER).append(MIN_TIERS);
			List<TwoplayertournamentDTO> tournamentList = session.createQuery(
					query.toString()).list();
			transaction.commit();
			HibernateUtil.closeSession();
			int totalTournaments = tournamentList.size();
			if (totalTournaments > G4GConstants.ZERO) {
				for (int index = G4GConstants.ZERO; index < totalTournaments; index++) {
					PastTournamentDTO pastTournamentDTO = new PastTournamentDTO();
					TwoplayertournamentDTO twoplayertournamentDTO = tournamentList
							.get(index);
					pastTournamentDTO
							.setTwoplayertournamentDTO(twoplayertournamentDTO);

					/* Get Game Details */
					GameDTO gameDTO = GameServiceDelegator
							.getGame(pastTournamentDTO
									.getTwoplayertournamentDTO().getGameDTO()
									.getId());
					pastTournamentDTO.setGameDTO(gameDTO);

					/* Get Network Details */
					searchCriteria.setAttribute(ID, gameDTO.getNetwork()
							.getId());
					NetworkDTO networkDTO = NetworkServiceDelegator.getList(
							searchCriteria).get(G4GConstants.ZERO);
					pastTournamentDTO.setNetworkDTO(networkDTO);

					/* Get Completiondate Details */
					TwoplayermatchDTO twoplayermatchDTO = getWinnerdetails(pastTournamentDTO
							.getTwoplayertournamentDTO().getId());
					if (twoplayermatchDTO != null) {
						pastTournamentDTO.setCompletiondate(twoplayermatchDTO
								.getCompleteddate());
					}
					List<PlayerDTO> playersList = getPlayersOfTournament(pastTournamentDTO
							.getTwoplayertournamentDTO().getId());

					pastTournamentDTO.setPlayersList(playersList);
					PlayerDTO winnerDTO = twoplayermatchDTO.getWinnerid();
					pastTournamentDTO.setWinnerDTO(winnerDTO);
					pastTournamentList.add(pastTournamentDTO);
				}

			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SET_PASTTOURNAMENT_DTO).toString(),
					Level.INFO);
		} catch (HibernateException exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PASTTOURNAMENTLIST).append(
								pastTournamentList.size()).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return pastTournamentList;
	}

	/**
	 * Function returns tournament schedule started date.
	 *
	 * @param tournamentId
	 * @return date
	 */
	@SuppressWarnings(UNCHECKED)
	public Date getScheduledstartDate(int tournamentId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).toString(), Level.INFO);
		List<Object> date = new ArrayList<Object>();
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			StringBuffer sbquery = new StringBuffer();
			sbquery.append(SELECT).append(MIN).append(OPEN_PAREN).append(
					SCHEDULEDSTARTDATE_SQL);
			sbquery.append(CLOSE_PAREN).append(SQLConstants.FROM).append(
					TWOPLAYERMATCH_SQL).append(SQLConstants.WHERE);
			sbquery.append(TOURNAMENTID_SQL).append(SQLConstants.EQUAL).append(
					tournamentId);

			Query query = session.createSQLQuery(sbquery.toString());
			date = query.list();
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (HibernateException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
		}
		String dateString = date.get(G4GConstants.ZERO).toString();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		if (date != null) {
			return DataUtil.getDate(dateString, DATE_YYYY_MM_DD_HH_MM_SS_sss);
		} else {
			return null;
		}
	}

	/**
	 * Function returns Winner details.
	 *
	 * @param TOURNAMENTID_SQL
	 * @return TwoplayermatchDTO
	 */
	@SuppressWarnings(UNCHECKED)
	public TwoplayermatchDTO getWinnerdetails(int tournamentId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).toString(), Level.INFO);
		List<TwoplayermatchDTO> twoPlayerMatchList = new ArrayList<TwoplayermatchDTO>();
		StringBuffer sqlQuery = new StringBuffer();
		Session session = HibernateUtil.getSession();
		// Winner of the give tournament retrived
		Transaction transaction = session.beginTransaction();

		sqlQuery.append(SQLConstants.FROM).append(TWOPLAYERMATCHDTO_SQL).append(
				SQLConstants.WHERE).append(TOURNAMENTID_SQL).append(
				SQLConstants.EQUAL).append(tournamentId).append(
				SQLConstants.AND).append(COMPLETEDDATE_SQL).append(
				SQLConstants.EQUAL).append(OPEN_PAREN);
		sqlQuery.append(SELECT).append(MAX).append(OPEN_PAREN).append(
				COMPLETEDDATE_SQL).append(CLOSE_PAREN);
		sqlQuery.append(SQLConstants.FROM).append(
				SQLConstants.TWOPLAYERMATCHDTO_SQL).append(SQLConstants.WHERE).append(
				TOURNAMENTID_SQL).append(SQLConstants.EQUAL).append(tournamentId)
				.append(CLOSE_PAREN);

		twoPlayerMatchList = session.createQuery(sqlQuery.toString()).list();
		TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();

		if (twoPlayerMatchList != null
				&& twoPlayerMatchList.size() > G4GConstants.ZERO) {
			twoplayermatchDTO = twoPlayerMatchList.get(G4GConstants.ZERO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_TWOPLAYERMATCH_DTO_WITH_WINNER_DETAILS)
						.toString(), Level.INFO);
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return twoplayermatchDTO;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getPlayersOfTournament(int)
	 */

	@SuppressWarnings(UNCHECKED)
	public List<PlayerDTO> getPlayersOfTournament(int tournamentId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).toString(), Level.INFO);
		List<PlayerDTO> playersList = new ArrayList<PlayerDTO>();
		StringBuffer playersQuery = new StringBuffer();
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		// Players of tournament who are in

		playersQuery.append(SELECT).append(PLAYERONEID).append(AS).append(
				PLAYERID_SQL);
		playersQuery.append(SQLConstants.FROM).append(TWOPLAYERMATCH_SQL).append(
				SQLConstants.WHERE);
		playersQuery.append(TOURNAMENTID_SQL).append(SQLConstants.EQUAL).append(
				tournamentId);
		playersQuery.append(SQLConstants.AND).append(PLAYERONEID).append(IS)
				.append(NOT);
		playersQuery.append(NULL).append(UNION).append(SELECT).append(
				PLAYERTWOID);
		playersQuery.append(SQLConstants.FROM).append(TWOPLAYERMATCH_SQL);
		playersQuery.append(SQLConstants.WHERE).append(TOURNAMENTID_SQL).append(
				SQLConstants.EQUAL);
		playersQuery.append(tournamentId).append(SQLConstants.AND).append(
				PLAYERTWOID);
		playersQuery.append(IS).append(NOT).append(NULL);

		List<Object> players = session.createSQLQuery(playersQuery.toString())
				.list();
		transaction.commit();
		HibernateUtil.closeSession();
		int total = players.size();
		for (int index = G4GConstants.ZERO; index < total; index++) {
			int id = Integer.parseInt(players.get(index).toString());
			PlayerDTO playerDTO = new PlayerDTO();
			playerDTO = PlayerServiceDelegator.getPlayer(id);
			playersList.add(playerDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_PLAYER_LIST).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYERS_OF_TOURNAMENT_LIST_SIZE).append(
								playersList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return playersList;
	}

	public List<GameoptionsDTO> getGameOptionsOfTournament(int tournamentid) {

		List<GameoptionsDTO> gameOptionsList = new ArrayList<GameoptionsDTO>();
		SearchCriteria searchCriteria = new SearchCriteria();
		TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator
				.get(tournamentid);
		// GameOptions List
		try {
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(TOURNAMENT_ID, twoplayertournamentDTO);
			List<TwoplayertournamentgameoptionsDTO> list = TwoPlayerTournamentGameOptionServiceDelegator
					.getList(searchCriteria);
			int totalTounamentGameOptions = list.size();
			// Retriving GameoptionsDTO to display gameoptions
			for (int tournamentIndex = G4GConstants.ZERO; tournamentIndex < totalTounamentGameOptions; tournamentIndex++) {
				TwoplayertournamentgameoptionsDTO twoplayertournamentgameoptionsDTO = list
						.get(tournamentIndex);

				// three pk optionId , gameId , valueId
				GameoptionsDTO gameoptionsDTO = new GameoptionsDTO();
				gameoptionsDTO.setGameid(twoplayertournamentDTO.getGameDTO()
						.getId());
				gameoptionsDTO.setId(twoplayertournamentgameoptionsDTO
						.getOptionid());
				gameoptionsDTO.setValueid(twoplayertournamentgameoptionsDTO
						.getValueid());
				gameoptionsDTO = GameOptionServiceDelegator
						.getGame(gameoptionsDTO);

				// set the gameoptionsDTO to gameOption list
				gameOptionsList.add(gameoptionsDTO);
			}
		} catch (NullPointerException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
		}
		return gameOptionsList;
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentService#getList(com.g4g.dto.SearchListCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayertournamentDTO> getList(
			SearchListCriteria searchCriteria) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(SEARCH_LIST_CRITERIA).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session
				.createCriteria(TwoplayertournamentDTO.class);
		String orderBY = searchCriteria.getOrderBy();
		if (orderBY != null) {
			if (searchCriteria.isAsc()) {
				criteria.addOrder(Order.asc(orderBY));
			} else {
				criteria.addOrder(Order.desc(orderBY));
			}
		}
		Set<?> keys = searchCriteria.getAttributeNames();
		Iterator<?> it = keys.iterator();
		String key = null;
		Object[] value = null;
		while (it.hasNext()) {
			key = (String) it.next();
			value = searchCriteria.getAttribute(key);
			if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.EQ)) {
				criteria.add(Restrictions.eq(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.GE)) {
				criteria.add(Restrictions.ge(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.GT)) {
				criteria.add(Restrictions.gt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.ILIKE)) {
				criteria.add(Restrictions.ilike(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.ISNOTNULL)) {
				criteria.add(Restrictions.isNotNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.ISNULL)) {
				criteria.add(Restrictions.isNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.LE)) {
				criteria.add(Restrictions.le(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.LIKE)) {
				criteria.add(Restrictions.like(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.LT)) {
				criteria.add(Restrictions.lt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.NE)) {
				criteria.add(Restrictions.ne(key, value[G4GConstants.ZERO]));
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(key)
							.append(COLON_WITH_SPACES).append(value).toString(),
					Level.INFO);
		}
		Transaction transaction = session.beginTransaction();
		List<TwoplayertournamentDTO> list = new ArrayList<TwoplayertournamentDTO>();
		try {
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException exception) {
			for (Object keyexception : keys) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
						keyexception.toString(), Level.ERROR);
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			throw exception;
		}
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(TOURNAMENT_LIST_SIZE_CRITERIA).append(
								list.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return list;
	}

}
