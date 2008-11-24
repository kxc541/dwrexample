/**********************************************************
 * WidgetsServiceImpl.java :
 *
 * Created by Jigar Mistry
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
import static com.g4g.utils.G4GConstants.ERRORS_TOURNAMENTS;
import static com.g4g.utils.G4GConstants.FOUND_GAME_LIST_SIZE;
import static com.g4g.utils.G4GConstants.FOUND_OPEN_CHALLENGES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.FOUND_OPEN_TOURNAMENTS_LIST_SIZE;
import static com.g4g.utils.G4GConstants.FOUND_PEOPLE_LIST_SIZE;
import static com.g4g.utils.G4GConstants.GAME;
import static com.g4g.utils.G4GConstants.GAMEID;
import static com.g4g.utils.G4GConstants.GAMENAME;
import static com.g4g.utils.G4GConstants.GAMEID_DB;
import static com.g4g.utils.G4GConstants.ISONLINE;
import static com.g4g.utils.G4GConstants.MATCHID;
import static com.g4g.utils.G4GConstants.NETWORK;
import static com.g4g.utils.G4GConstants.NETWORKNAME;
import static com.g4g.utils.G4GConstants.NETWORKID_DB;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.ONE;
import static com.g4g.utils.G4GConstants.PAGESIZE;
import static com.g4g.utils.G4GConstants.PAGESTARTSAT;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.PLAYERID;
import static com.g4g.utils.G4GConstants.PLAYERONEID;
import static com.g4g.utils.G4GConstants.PLAYERTWOID;
import static com.g4g.utils.G4GConstants.PLAYER_ONE_ID;
import static com.g4g.utils.G4GConstants.SCREENNAME_DB;
import static com.g4g.utils.G4GConstants.SEARCHING_FOR_GAME;
import static com.g4g.utils.G4GConstants.SEARCHING_FOR_NETWORK;
import static com.g4g.utils.G4GConstants.SEARCHSTRING;
import static com.g4g.utils.G4GConstants.SESSIONID;
import static com.g4g.utils.G4GConstants.SET_ACTIVE_PLAYERS_DTO;
import static com.g4g.utils.G4GConstants.SET_GAME_TOURNAMENT_CHALLENGE_DTO;
import static com.g4g.utils.G4GConstants.SET_OPEN_CHALLENGE_DTO;
import static com.g4g.utils.G4GConstants.SET_PLAYNOW_DTO;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.THREE;
import static com.g4g.utils.G4GConstants.TOURNAMENTID;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT;
import static com.g4g.utils.G4GConstants.ZERO;
import static com.g4g.utils.SQLConstants.AS;
import static com.g4g.utils.SQLConstants.ASTERISTIC;
import static com.g4g.utils.SQLConstants.CLOSE_PAREN;
import static com.g4g.utils.SQLConstants.CNT;
import static com.g4g.utils.SQLConstants.COUNT;
import static com.g4g.utils.SQLConstants.CURRENT_TIMESTAMP;
import static com.g4g.utils.SQLConstants.DISTINCT;
import static com.g4g.utils.SQLConstants.DOT;
import static com.g4g.utils.SQLConstants.GREATER;
import static com.g4g.utils.SQLConstants.GREATER_EQUAL;
import static com.g4g.utils.SQLConstants.GROUPBY;
import static com.g4g.utils.SQLConstants.IS;
import static com.g4g.utils.SQLConstants.LIKE;
import static com.g4g.utils.SQLConstants.LIMIT;
import static com.g4g.utils.SQLConstants.MIN;
import static com.g4g.utils.SQLConstants.MINUS;
import static com.g4g.utils.SQLConstants.MODEASC;
import static com.g4g.utils.SQLConstants.MODEDESC;
import static com.g4g.utils.SQLConstants.NOT;
import static com.g4g.utils.SQLConstants.NULL;
import static com.g4g.utils.SQLConstants.OPEN_PAREN;
import static com.g4g.utils.SQLConstants.OR;
import static com.g4g.utils.SQLConstants.ORDERBY;
import static com.g4g.utils.SQLConstants.QUOTE;
import static com.g4g.utils.SQLConstants.SELECT;
import static com.g4g.utils.SQLConstants.UPPER;
import static com.g4g.utils.SQLConstants.WHERE;
import static com.g4g.utils.SQLConstants.COMPLETEDDATE_SQL;
import static com.g4g.utils.SQLConstants.CREATIONDATE_SQL;
import static com.g4g.utils.SQLConstants.CURRENT_DATE_SQL;
import static com.g4g.utils.SQLConstants.ENTRYFEE_SQL;
import static com.g4g.utils.SQLConstants.G;
import static com.g4g.utils.SQLConstants.GAME_SQL;
import static com.g4g.utils.SQLConstants.GAMEID_SQL;
import static com.g4g.utils.SQLConstants.GAMENAME_SQL;
import static com.g4g.utils.SQLConstants.IMGSRC_SQL;
import static com.g4g.utils.SQLConstants.INTEGER_SQL;
import static com.g4g.utils.SQLConstants.LEVELS_SQL;
import static com.g4g.utils.SQLConstants.M;
import static com.g4g.utils.SQLConstants.MATCHID_SQL;
import static com.g4g.utils.SQLConstants.N;
import static com.g4g.utils.SQLConstants.NETWORK_SQL;
import static com.g4g.utils.SQLConstants.NETWORKID_SQL;
import static com.g4g.utils.SQLConstants.NETWORKNAME_SQL;
import static com.g4g.utils.SQLConstants.NOW_SQL;
import static com.g4g.utils.SQLConstants.P;
import static com.g4g.utils.SQLConstants.PAYOUTAMOUNT_SQL;
import static com.g4g.utils.SQLConstants.PG;
import static com.g4g.utils.SQLConstants.PLAYER_SQL;
import static com.g4g.utils.SQLConstants.PLAYERGAME_SQL;
import static com.g4g.utils.SQLConstants.PLAYERID_SQL;
import static com.g4g.utils.SQLConstants.PLAYERONEID_SQL;
import static com.g4g.utils.SQLConstants.PLAYERTWOID_SQL;
import static com.g4g.utils.SQLConstants.SCHEDULEDSTARTDATE_SQL;
import static com.g4g.utils.SQLConstants.SCREENNAME_SQL;
import static com.g4g.utils.SQLConstants.T;
import static com.g4g.utils.SQLConstants.TM;
import static com.g4g.utils.SQLConstants.TOURNAMENTID_SQL;
import static com.g4g.utils.SQLConstants.TT;
import static com.g4g.utils.SQLConstants.TWOPLAYERMATCH_SQL;
import static com.g4g.utils.SQLConstants.TWOPLAYERTOURNAMENT_SQL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.g4g.delegator.AvatarsServiceDelegator;
import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The class WidgetsServiceImpl contains implementation of all the methods for
 * which the action calls are delegated through WidgetServiceDelegator via
 * widgetServiceInterface. The method description is defined in class with all
 * business and database logic.
 *
 * @author Jigar Mistry
 */
public class WidgetsServiceImpl implements WidgetsService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	private static final String UNUSED = "unused"; //$NON-NLS-1$

	/**
	 * @see WidgetsService#getPlayNowList(int)
	 *
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayNowDTO> getPlayNowList(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERID).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();

		StringBuffer query = new StringBuffer();
		query.append(SELECT).append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL);
		query.append(SQLConstants.COMMA).append(G).append(DOT).append(GAMENAME_SQL);
		query.append(SQLConstants.COMMA).append(TT).append(DOT)
				.append(ENTRYFEE_SQL);
		query.append(SQLConstants.COMMA).append(G).append(DOT).append(IMGSRC_SQL);
		query.append(SQLConstants.COMMA).append(OPEN_PAREN).append(SELECT);
		query.append(SCREENNAME_DB).append(SQLConstants.FROM).append(PLAYER_SQL);
		query.append(WHERE).append(PLAYERID).append(SQLConstants.EQUAL);
		query.append(TM).append(DOT).append(PLAYER_ONE_ID).append(CLOSE_PAREN);
		query.append(AS).append(SCREENNAME_DB);
		query.append(SQLConstants.COMMA).append(G).append(DOT).append(GAMEID_DB);
		query.append(SQLConstants.COMMA).append(TM).append(DOT).append(
				PLAYER_ONE_ID);
		query.append(SQLConstants.COMMA).append(TM).append(DOT).append(MATCHID);
		query.append(SQLConstants.FROM).append(TWOPLAYERMATCH_SQL).append(TM);
		query.append(SQLConstants.COMMA).append(TWOPLAYERTOURNAMENT_SQL).append(TT);
		query.append(SQLConstants.COMMA).append(PLAYER_SQL).append(P);
		query.append(SQLConstants.COMMA).append(GAME_SQL).append(G);
		query.append(WHERE).append(TM).append(DOT).append(PLAYERTWOID).append(
				IS).append(NULL);
		query.append(SQLConstants.AND).append(TM).append(DOT).append(
				PLAYERONEID);
		query.append(IS).append(NOT).append(NULL).append(SQLConstants.AND);
		query.append(TM).append(DOT).append(COMPLETEDDATE_SQL).append(IS).append(
				NULL);
		query.append(SQLConstants.AND).append(TT).append(DOT).append(
				TOURNAMENTID);
		query.append(SQLConstants.EQUAL).append(TM).append(DOT).append(
				TOURNAMENTID);
		query.append(SQLConstants.AND).append(TT).append(DOT).append(LEVELS_SQL);
		query.append(SQLConstants.EQUAL).append(ONE).append(SQLConstants.AND);
		query.append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL).append(GREATER);
		query.append(NOW_SQL).append(SQLConstants.AND).append(TM).append(DOT);
		query.append(PLAYERONEID).append(SQLConstants.EQUAL);
		query.append(P).append(DOT).append(PLAYERID).append(SQLConstants.AND);
		query.append(P).append(DOT).append(ISONLINE).append(SQLConstants.EQUAL);
		query.append(SQLConstants.TRUE).append(SQLConstants.AND);
		query.append(G).append(DOT).append(GAMEID).append(SQLConstants.EQUAL);
		query.append(TT).append(DOT).append(GAMEID).append(ORDERBY);
		query.append(P).append(DOT).append(ISONLINE).append(MODEASC);
		query.append(SQLConstants.COMMA).append(TT).append(DOT).append(
				CREATIONDATE_SQL);
		query.append(MODEDESC).append(LIMIT).append(THREE);

		List<Object> playNowList = session.createSQLQuery(query.toString())
				.list();

		int totalPlayNow = G4GConstants.ZERO;
		totalPlayNow = playNowList.size();
		List<PlayNowDTO> playNow = new ArrayList<PlayNowDTO>();
		/* Inserting data into playNowDTO */
		for (int index = G4GConstants.ZERO; index < totalPlayNow; index++) {
			PlayNowDTO playNowDTO = new PlayNowDTO();
			Object[] object = (Object[]) playNowList.get(index);
			playNowDTO.setScheduleDate((Date) object[G4GConstants.ZERO]);
			playNowDTO.setGameName(object[G4GConstants.ONE_NUMBER] + NONE);
			Double s = Double
					.parseDouble((object[G4GConstants.TWO_NUMBER] + NONE));
			playNowDTO.setEntryFee(s.longValue());
			playNowDTO.setGameImgSrc(object[G4GConstants.THREE_NUMBER] + NONE);
			playNowDTO.setPlayerScreenName(object[G4GConstants.FOUR_NUMBER]
					+ NONE);
			playNowDTO.setGameId(Integer
					.parseInt(object[G4GConstants.FIVE_NUMBER] + NONE));
			playNowDTO.setPlayerId(Integer
					.parseInt(object[G4GConstants.SIX_NUMBER] + NONE));
			playNowDTO.setMatchId(Integer
					.parseInt(object[G4GConstants.SEVEN_NUMBER] + NONE));
			playNow.add(playNowDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SET_PLAYNOW_DTO)
						.toString(), Level.INFO);
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return playNow;
	}

	/**
	 * @see WidgetsService#getPlayersList(String, HttpServletRequest)
	 */

	@SuppressWarnings(UNCHECKED)
	public List<PlayerDTO> getPlayersList(String searchString,
			int pageStartsAt) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(SEARCHSTRING).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		StringBuffer stringQuery = new StringBuffer();
		stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
		stringQuery.append(SELECT).append(ASTERISTIC).append(SQLConstants.FROM);
		stringQuery.append(NETWORK).append(WHERE).append(UPPER);
		stringQuery.append(OPEN_PAREN).append(NETWORKNAME).append(CLOSE_PAREN)
				.append(LIKE);
		stringQuery.append(QUOTE).append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
		stringQuery.append(QUOTE);

		List<NetworkDTO> networkList = session.createSQLQuery(
				stringQuery.toString()).addEntity(NetworkDTO.class).list();

		stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
		stringQuery.append(SELECT).append(ASTERISTIC).append(SQLConstants.FROM);
		stringQuery.append(GAME_SQL).append(SQLConstants.WHERE).append(UPPER);
		stringQuery.append(OPEN_PAREN).append(GAMENAME_SQL).append(CLOSE_PAREN)
				.append(LIKE);
		stringQuery.append(QUOTE).append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
		stringQuery.append(QUOTE);
		List<GameDTO> gameList = session.createSQLQuery(stringQuery.toString())
				.addEntity(GameDTO.class).list();
		List<PlayerDTO> playerList = new ArrayList<PlayerDTO>();
		Query query;

		try {
			/* Players as per console-name */
			if (networkList.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_NETWORK).toString(),
						Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(DISTINCT).append(
						G4GConstants.PLAYER).append(DOT).append(ASTERISTIC)
						.append(SQLConstants.FROM).append(G4GConstants.PLAYER)
						.append(G4GConstants.BLANK).append(G4GConstants.PLAYER)
						.append(COMMA);

				stringQuery.append(SQLConstants.PLAYERNETWORK_SQL).append(
						SQLConstants.PLAYERNETWORK_SQL).append(SQLConstants.COMMA)
						.append(G4GConstants.NETWORK)
						.append(G4GConstants.BLANK)
						.append(G4GConstants.NETWORK)
						.append(SQLConstants.WHERE);

				stringQuery.append(G4GConstants.PLAYER).append(DOT).append(
						G4GConstants.PLAYERID_DB).append(SQLConstants.EQUAL)
						.append(SQLConstants.PLAYERNETWORK_SQL).append(DOT).append(
								G4GConstants.PLAYERID_DB).append(SQLConstants.AND)
						.append(SQLConstants.PLAYERNETWORK_SQL).append(DOT).append(
								G4GConstants.NETWORKID_DB).append(
								SQLConstants.EQUAL)
						.append(G4GConstants.NETWORK).append(DOT).append(
								G4GConstants.NETWORKID_DB);

				stringQuery.append(SQLConstants.AND).append(SQLConstants.UPPER)
						.append(SQLConstants.OPEN_PAREN).append(
								G4GConstants.NETWORK).append(SQLConstants.DOT)
						.append(G4GConstants.NETWORKNAME).append(
								SQLConstants.CLOSE_PAREN);
				stringQuery.append(SQLConstants.LIKE)
						.append(SQLConstants.QUOTE);
				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(SQLConstants.QUOTE);
				query = session.createSQLQuery(stringQuery.toString())
						.addEntity(PlayerDTO.class);
				if(pageStartsAt > 0 ){
					query.setFirstResult(pageStartsAt);
					query.setMaxResults(PAGESIZE);
				}

				playerList = query.list();
				session = null;
				HibernateUtil.closeSession();
			}
			/* Players as per Game-name */
			else if (gameList.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G + SEARCHING_FOR_GAME
								+ Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(DISTINCT).append(
						G4GConstants.PLAYER).append(DOT).append(ASTERISTIC)
						.append(SQLConstants.FROM).append(G4GConstants.PLAYER)
						.append(G4GConstants.BLANK).append(G4GConstants.PLAYER)
						.append(COMMA);

				stringQuery.append(SQLConstants.PLAYERGAME_SQL).append(
						SQLConstants.PLAYERGAME_SQL).append(SQLConstants.COMMA)
						.append(SQLConstants.GAME_SQL).append(SQLConstants.GAME_SQL)
						.append(SQLConstants.WHERE);

				stringQuery.append(G4GConstants.PLAYER)
						.append(SQLConstants.DOT).append(G4GConstants.PLAYERID_DB)
						.append(SQLConstants.EQUAL).append(
								SQLConstants.PLAYERGAME_SQL).append(
								SQLConstants.DOT).append(G4GConstants.PLAYERID_DB)
						.append(SQLConstants.AND).append(G4GConstants.GAME)
						.append(SQLConstants.DOT).append(G4GConstants.GAMEID_DB)
						.append(SQLConstants.EQUAL).append(
								SQLConstants.PLAYERGAME_SQL).append(
								SQLConstants.DOT).append(G4GConstants.GAMEID_DB);

				stringQuery.append(SQLConstants.AND).append(SQLConstants.UPPER)
						.append(SQLConstants.OPEN_PAREN).append(
								G4GConstants.GAME).append(SQLConstants.DOT)
						.append(G4GConstants.GAMENAME).append(
								SQLConstants.CLOSE_PAREN);

				stringQuery.append(SQLConstants.LIKE)
						.append(SQLConstants.QUOTE);

				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(SQLConstants.QUOTE);

				query = session.createSQLQuery(stringQuery.toString())
						.addEntity(PlayerDTO.class);
				if(pageStartsAt > 0 ){
					query.setFirstResult(pageStartsAt);
					query.setMaxResults(PAGESIZE);
				}

				playerList = query.list();
				session = null;
				HibernateUtil.closeSession();
			}
			/* Players as per screen-name, login-name, email-address */
			else {
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(ASTERISTIC).append(
						SQLConstants.FROM).append(G4GConstants.PLAYER).append(
						WHERE).append(SQLConstants.UPPER).append(
						SQLConstants.OPEN_PAREN)
						.append(G4GConstants.SCREENNAME_DB).append(
								SQLConstants.CLOSE_PAREN).append(LIKE).append(
								QUOTE);

				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(QUOTE).append(OR).append(SQLConstants.UPPER)
						.append(SQLConstants.OPEN_PAREN).append(
								G4GConstants.LOGINNAME).append(
								SQLConstants.CLOSE_PAREN).append(LIKE).append(
								QUOTE);

				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(QUOTE).append(OR).append(SQLConstants.UPPER)
						.append(SQLConstants.OPEN_PAREN).append(
								G4GConstants.EMAILADDRESS).append(
								SQLConstants.CLOSE_PAREN).append(LIKE).append(
								QUOTE);

				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(QUOTE);

				query = session.createSQLQuery(stringQuery.toString())
						.addEntity(PlayerDTO.class);
				if(pageStartsAt > 0 ){
					query.setFirstResult(pageStartsAt);
					query.setMaxResults(PAGESIZE);
				}

				playerList = query.list();
				session = null;
				HibernateUtil.closeSession();
			}
		} catch (HibernateException exception) {
			session = null;
			HibernateUtil.closeSession();
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
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(FOUND_PEOPLE_LIST_SIZE).append(
								playerList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return playerList;
	}

	/**
	 *
	 * @see WidgetsService#getGamesList(String, HttpServletRequest)
	 */

	@SuppressWarnings(UNCHECKED)
	public List<GameDTO> getGamesList(String searchString,
			int pageStartsAt) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PARAMETERS).append(SEARCHSTRING)
						.toString(), Level.INFO);
		List<GameDTO> gameList = new ArrayList<GameDTO>();
		StringBuffer queryString = new StringBuffer();
		Query query;
		Session session = HibernateUtil.getSession();
		try {
			queryString.delete(ZERO, queryString.length());
			queryString.append(SELECT).append(ASTERISTIC).append(
					SQLConstants.FROM);
			queryString.append(NETWORK).append(WHERE).append(UPPER);
			queryString.append(OPEN_PAREN).append(NETWORKNAME).append(
					CLOSE_PAREN).append(LIKE);
			queryString.append(QUOTE).append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
			queryString.append(QUOTE);

			List<NetworkDTO> networkList = session.createSQLQuery(
					queryString.toString()).addEntity(NetworkDTO.class).list();
			/* Games as per network-name */
			if (networkList.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_NETWORK).toString(),
						Level.INFO);
				queryString.delete(ZERO, queryString.length());
				queryString.append(SELECT).append(G4GConstants.GAME)
						.append(DOT).append(ASTERISTIC).append(
								SQLConstants.FROM).append(G4GConstants.GAME)
						.append(G4GConstants.BLANK).append(G4GConstants.GAME)
						.append(SQLConstants.COMMA)
						.append(G4GConstants.NETWORK)
						.append(G4GConstants.BLANK)
						.append(G4GConstants.NETWORK).append(WHERE);

				queryString.append(G4GConstants.NETWORK).append(DOT).append(
						G4GConstants.NETWORKID_DB).append(SQLConstants.EQUAL)
						.append(G4GConstants.GAME).append(DOT).append(
								G4GConstants.NETWORKID_DB)
						.append(SQLConstants.AND).append(SQLConstants.UPPER)
						.append(SQLConstants.OPEN_PAREN).append(
								G4GConstants.NETWORK).append(DOT).append(
								G4GConstants.NETWORKNAME).append(
								SQLConstants.CLOSE_PAREN).append(LIKE).append(
								QUOTE);

				queryString.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				queryString.append(QUOTE);
				query = session.createSQLQuery(queryString.toString())
						.addEntity(GameDTO.class);
				if(pageStartsAt > 0 ){
					query.setFirstResult(pageStartsAt);
					query.setMaxResults(PAGESIZE);
				}
				gameList = query.list();
				session = null;
				HibernateUtil.closeSession();
			}
			/* Games as per game-name */
			else {

				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_GAME).toString(),
						Level.INFO);
				queryString.delete(G4GConstants.ZERO, queryString.length());
				queryString.append(SELECT).append(ASTERISTIC).append(
						SQLConstants.FROM);
				queryString.append(GAME).append(WHERE).append(UPPER);
				queryString.append(OPEN_PAREN).append(GAMENAME).append(
						CLOSE_PAREN).append(LIKE);
				queryString.append(QUOTE).append(SQLConstants.PERSENT).append(
						searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				queryString.append(QUOTE);

				query = session.createSQLQuery(queryString.toString())
						.addEntity(GameDTO.class);
				if(pageStartsAt > 0 ){
					query.setFirstResult(pageStartsAt);
					query.setMaxResults(PAGESIZE);
				}
				gameList = query.list();
				session = null;
				HibernateUtil.closeSession();
			}
		} catch (HibernateException exception) {
			session = null;
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(exception.getMessage()).toString(),
					Level.ERROR);

			throw exception;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(FOUND_GAME_LIST_SIZE).append(gameList.size())
						.toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return gameList;
	}

	/**
	 * @param searchString
	 * @param request
	 * @return List<GameTournamentChallengeDTO>
	 * @throws HibernateException
	 * @see WidgetsService#getGamesList(String, HttpServletRequest)
	 *
	 */
	@SuppressWarnings( { UNUSED, UNCHECKED })
	public List<GameTournamentChallengeDTO> getTournamentList(
			String searchString, int pageStartsAt)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERID).append(COMMA).toString(),
				Level.INFO);
		List<GameTournamentChallengeDTO> tournamentList = new ArrayList<GameTournamentChallengeDTO>();
		List<Object> tournaments = new ArrayList<Object>();
		int totalTournaments;
		StringBuffer stringQuery = new StringBuffer();

		Query query;
		Session session = HibernateUtil.getSession();

		try {
			stringQuery.delete(G4GConstants.ZERO, stringQuery.length());

			stringQuery.append(SELECT).append(ASTERISTIC).append(
					SQLConstants.FROM);
			stringQuery.append(NETWORK).append(WHERE).append(UPPER);
			stringQuery.append(OPEN_PAREN).append(NETWORKNAME).append(
					CLOSE_PAREN).append(LIKE);
			stringQuery.append(QUOTE).append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
			stringQuery.append(QUOTE);

			List<NetworkDTO> networkList = session.createSQLQuery(
					stringQuery.toString()).addEntity(NetworkDTO.class).list();
			stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
			stringQuery.append(SELECT).append(ASTERISTIC).append(
					SQLConstants.FROM);
			stringQuery.append(GAME).append(WHERE).append(UPPER);
			stringQuery.append(OPEN_PAREN).append(GAMENAME).append(CLOSE_PAREN)
					.append(LIKE);
			stringQuery.append(QUOTE).append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
			stringQuery.append(QUOTE);

			List<GameDTO> gameList = session.createSQLQuery(
					stringQuery.toString()).addEntity(GameDTO.class).list();
			/* Tournaments as per network-name */
			if (networkList.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_NETWORK).toString(),
						Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(DISTINCT).append(
						SQLConstants.ON).append(OPEN_PAREN).append(TT).append(
						DOT).append(TOURNAMENT_ID).append(CLOSE_PAREN)
						.append(G).append(DOT).append(GAMEID_DB).append(COMMA)
						.append(G).append(DOT).append(GAMENAME).append(COMMA)
						.append(G).append(DOT).append(SQLConstants.IMGSRC_SQL)
						.append(COMMA).append(TM).append(DOT).append(
								SCHEDULEDSTARTDATE_SQL).append(COMMA).append(TM)
						.append(DOT).append(PAYOUTAMOUNT_SQL).append(COMMA);

				stringQuery.append(TT).append(DOT).append(ENTRYFEE_SQL).append(
						COMMA).append(TT).append(DOT).append(TOURNAMENT_ID)
						.append(SQLConstants.FROM).append(GAME).append(G)
						.append(COMMA).append(TWOPLAYERMATCH_SQL).append(TM)
						.append(COMMA).append(TWO_PLAYER_TOURNAMENT).append(TT)
						.append(COMMA);

				stringQuery.append(NETWORK).append(N).append(WHERE).append(G)
						.append(DOT).append(GAMEID_DB).append(SQLConstants.EQUAL)
						.append(TT).append(DOT).append(GAMEID_DB).append(
								SQLConstants.AND).append(TT).append(DOT)
						.append(TOURNAMENT_ID).append(SQLConstants.EQUAL)
						.append(TT).append(DOT).append(TOURNAMENT_ID);
				stringQuery.append(SQLConstants.AND).append(TT).append(DOT)
						.append(G4GConstants.LEVELS).append(
								SQLConstants.GREATER).append(
								G4GConstants.ONE_NUMBER).append(
								SQLConstants.AND).append(G).append(DOT).append(
								NETWORKID_DB).append(SQLConstants.EQUAL).append(N)
						.append(DOT).append(NETWORKID_DB).append(SQLConstants.AND)
						.append(UPPER);
				stringQuery.append(OPEN_PAREN).append(N).append(DOT).append(
						NETWORKNAME).append(CLOSE_PAREN).append(LIKE).append(
						QUOTE);
				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(QUOTE);

			}
			/* Tournaments as per game-name */
			else {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_GAME).toString(),
						Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(DISTINCT).append(
						SQLConstants.ON);
				stringQuery.append(OPEN_PAREN).append(TT).append(DOT).append(
						TOURNAMENTID_SQL);
				stringQuery.append(CLOSE_PAREN).append(SQLConstants.SPACE);
				stringQuery.append(G).append(DOT).append(GAMEID_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(G).append(DOT).append(GAMENAME_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(G).append(DOT).append(IMGSRC_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL)
						.append(SQLConstants.COMMA);
				stringQuery.append(TM).append(DOT).append(PAYOUTAMOUNT_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TT).append(DOT).append(ENTRYFEE_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TT).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.FROM);
				stringQuery.append(GAME_SQL).append(G).append(SQLConstants.COMMA);
				stringQuery.append(TWOPLAYERMATCH_SQL).append(TM).append(
						SQLConstants.COMMA);
				stringQuery.append(TWOPLAYERTOURNAMENT_SQL).append(TT).append(
						SQLConstants.COMMA);
				stringQuery.append(NETWORK_SQL).append(N)
						.append(SQLConstants.WHERE);
				stringQuery.append(G).append(DOT).append(GAMEID_SQL).append(
						SQLConstants.EQUAL);
				stringQuery.append(TT).append(DOT).append(GAMEID_SQL).append(
						SQLConstants.AND);
				stringQuery.append(TM).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.EQUAL);
				stringQuery.append(TT).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.AND);
				stringQuery.append(TT).append(DOT).append(LEVELS_SQL).append(
						GREATER);
				stringQuery.append(G4GConstants.ONE_NUMBER).append(
						SQLConstants.AND);
				stringQuery.append(UPPER).append(OPEN_PAREN).append(G).append(
						DOT).append(GAMENAME_SQL);
				stringQuery.append(CLOSE_PAREN).append(LIKE).append(QUOTE);
				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(SQLConstants.QUOTE);

			}
			query = session.createSQLQuery(stringQuery.toString());
			if(pageStartsAt > 0 ){
				query.setFirstResult(pageStartsAt);
				query.setMaxResults(PAGESIZE);
			}
			tournaments = query.list();
			session = null;
			HibernateUtil.closeSession();
		} catch (HibernateException exception) {
			session = null;
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(exception.getMessage()).toString(),
							Level.ERROR);
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
									.append(exception.getMessage()).toString(),
							Level.ERROR);

			throw exception;
		}
		totalTournaments = tournaments.size();
		/* Inserting data into GameTournamentChallengeDTO */
		for (int index = G4GConstants.ZERO; index < totalTournaments; index++) {
			Object[] object = (Object[]) tournaments.get(index);
			GameTournamentChallengeDTO gameTournamentChallengeDTO = new GameTournamentChallengeDTO();
			gameTournamentChallengeDTO.setGameid(Integer
					.parseInt(object[G4GConstants.ZERO].toString()));
			gameTournamentChallengeDTO
					.setGamename(object[G4GConstants.ONE_NUMBER].toString());
			gameTournamentChallengeDTO
					.setImgsrc(object[G4GConstants.TWO_NUMBER].toString());
			gameTournamentChallengeDTO
					.setScheduledstartdate(DataUtil
							.getDate(object[G4GConstants.THREE_NUMBER]
									.toString(), DATE_YYYY_MM_DD_HH_MM_SS_sss)
							);
			Double amt = Double.parseDouble(object[G4GConstants.FOUR_NUMBER]
					.toString());
			gameTournamentChallengeDTO.setPayoutamount(amt.longValue());
			Double fees = Double.parseDouble(object[G4GConstants.FIVE_NUMBER]
					.toString());
			gameTournamentChallengeDTO.setEntryfee(fees.longValue());
			gameTournamentChallengeDTO.setTournamentid(Integer
					.parseInt(object[G4GConstants.SIX_NUMBER].toString()));
			gameTournamentChallengeDTO.setMatchid(G4GConstants.ZERO);
			tournamentList.add(gameTournamentChallengeDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_GAME_TOURNAMENT_CHALLENGE_DTO).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(FOUND_OPEN_TOURNAMENTS_LIST_SIZE).append(
								tournamentList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).toString(),
				Level.INFO);
		return tournamentList;
	}

	/**
	 * @see WidgetsService#getOpenChallengeList(String, HttpServletRequest)
	 *
	 */

	@SuppressWarnings(UNCHECKED)
	public List<GameTournamentChallengeDTO> getOpenChallengeList(
			String searchString, int pageStartsAt)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERID).append(COMMA).toString(),
				Level.INFO);
		List<GameTournamentChallengeDTO> openChallengeList = new ArrayList<GameTournamentChallengeDTO>();
		List<Object> openChallenges = new ArrayList<Object>();
		StringBuffer stringQuery = new StringBuffer();
		int totalChallenges;

		Query query;
		Session session = HibernateUtil.getSession();
		try {
			stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
			stringQuery.append(SELECT).append(ASTERISTIC).append(
					SQLConstants.FROM);
			stringQuery.append(NETWORK_SQL).append(SQLConstants.WHERE)
					.append(UPPER);
			stringQuery.append(OPEN_PAREN).append(NETWORKNAME_SQL).append(
					CLOSE_PAREN);
			stringQuery.append(LIKE).append(QUOTE);
			stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT).append(QUOTE);

			List<NetworkDTO> networkList = session.createSQLQuery(
					stringQuery.toString()).addEntity(NetworkDTO.class).list();
			/* Games as per network-name */
			if (networkList.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_NETWORK).toString(),
						Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());

				stringQuery.append(SELECT).append(DISTINCT).append(G).append(
						DOT).append(GAMEID_SQL).append(SQLConstants.COMMA);
				stringQuery.append(G).append(DOT).append(GAMENAME_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(G).append(DOT).append(IMGSRC_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL)
						.append(SQLConstants.COMMA);
				stringQuery.append(TM).append(DOT).append(PAYOUTAMOUNT_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TT).append(DOT).append(ENTRYFEE_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TT).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.COMMA);
				stringQuery.append(TM).append(DOT).append(MATCHID_SQL).append(
						SQLConstants.FROM);
				stringQuery.append(GAME_SQL).append(G).append(SQLConstants.COMMA);
				stringQuery.append(TWOPLAYERMATCH_SQL).append(TM).append(
						SQLConstants.COMMA);
				stringQuery.append(TWOPLAYERTOURNAMENT_SQL).append(TT).append(
						SQLConstants.COMMA);
				stringQuery.append(NETWORK_SQL).append(N)
						.append(SQLConstants.WHERE);
				stringQuery.append(G).append(DOT).append(GAMEID_SQL).append(
						SQLConstants.EQUAL);
				stringQuery.append(TT).append(DOT).append(GAMEID_SQL).append(
						SQLConstants.AND);
				stringQuery.append(TM).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.EQUAL);
				stringQuery.append(TT).append(DOT).append(TOURNAMENTID_SQL).append(
						SQLConstants.AND);
				stringQuery.append(G).append(DOT).append(NETWORKID_SQL).append(
						SQLConstants.EQUAL);
				stringQuery.append(N).append(DOT).append(NETWORKID_SQL).append(
						SQLConstants.AND);
				stringQuery.append(TM).append(DOT).append(PLAYERTWOID_SQL).append(
						IS);
				stringQuery.append(NULL).append(SQLConstants.AND).append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL)
						.append(GREATER_EQUAL).append(
						CURRENT_TIMESTAMP);
				stringQuery.append(SQLConstants.AND)
						.append(UPPER);
				stringQuery.append(OPEN_PAREN).append(N).append(DOT).append(
						NETWORKNAME_SQL);
				stringQuery.append(CLOSE_PAREN).append(LIKE).append(QUOTE);
				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT).append(
						QUOTE);

			}
			/* Games as per game-name */
			else {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SEARCHING_FOR_GAME).toString(),
						Level.INFO);
				stringQuery.delete(G4GConstants.ZERO, stringQuery.length());
				stringQuery.append(SELECT).append(DISTINCT).append(G).append(
						DOT).append(GAMEID_SQL).append(COMMA).append(G).append(DOT)
						.append(GAMENAME_SQL).append(COMMA).append(G).append(DOT)
						.append(IMGSRC_SQL).append(COMMA);
				// .append("select distinct g.gameid, g.gamename, g.imgsrc,");
				stringQuery.append(TM).append(DOT).append(SCHEDULEDSTARTDATE_SQL)
						.append(COMMA).append(TM).append(DOT).append(
								PAYOUTAMOUNT_SQL).append(COMMA).append(TT).append(
								DOT).append(ENTRYFEE_SQL).append(COMMA).append(TT)
						.append(DOT).append(TOURNAMENTID_SQL).append(COMMA).append(
								TM).append(DOT).append(MATCHID_SQL).append(
								SQLConstants.FROM).append(GAME_SQL).append(G)
						.append(COMMA);

				stringQuery.append(TWOPLAYERMATCH_SQL).append(TM).append(COMMA)
						.append(TWOPLAYERTOURNAMENT_SQL).append(TT).append(WHERE)
						.append(G).append(DOT).append(GAMEID_SQL).append(
								SQLConstants.EQUAL).append(TT).append(DOT)
						.append(GAMEID_SQL);

				stringQuery.append(SQLConstants.AND).append(TM).append(DOT)
						.append(TOURNAMENTID_SQL).append(SQLConstants.EQUAL)
						.append(TT).append(DOT).append(TOURNAMENTID_SQL).append(
								SQLConstants.AND).append(TM).append(DOT)
						.append(PLAYERTWOID_SQL);

				stringQuery.append(IS).append(NULL).append(SQLConstants.AND)
						.append(TM).append(DOT)
						.append(SCHEDULEDSTARTDATE_SQL).append(GREATER_EQUAL).append(
								CURRENT_TIMESTAMP).append(
								SQLConstants.AND).append(UPPER).append(
								OPEN_PAREN).append(G).append(DOT).append(
								GAMENAME_SQL).append(CLOSE_PAREN).append(LIKE)
						.append(QUOTE);

				stringQuery.append(SQLConstants.PERSENT).append(searchString.trim().toUpperCase()).append(SQLConstants.PERSENT);
				stringQuery.append(QUOTE);
			}
			query = session.createSQLQuery(stringQuery.toString());
			if(pageStartsAt > 0 ){
				query.setFirstResult(pageStartsAt);
				query.setMaxResults(PAGESIZE);
			}
			openChallenges = query.list();
			session = null;
			HibernateUtil.closeSession();
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
			throw exception;
		}
		totalChallenges = openChallenges.size();
		/* Inserting data into GameTournamentChallengeDTO */
		for (int index = G4GConstants.ZERO; index < totalChallenges; index++) {
			Object[] object = (Object[]) openChallenges.get(index);
			GameTournamentChallengeDTO gameTournamentChallengeDTO = new GameTournamentChallengeDTO();
			gameTournamentChallengeDTO.setGameid(Integer
					.parseInt(object[G4GConstants.ZERO].toString()));
			gameTournamentChallengeDTO
					.setGamename(object[G4GConstants.ONE_NUMBER].toString());
			gameTournamentChallengeDTO
					.setImgsrc(object[G4GConstants.TWO_NUMBER].toString());
			gameTournamentChallengeDTO
					.setScheduledstartdate(DataUtil
							.getDate(object[G4GConstants.THREE_NUMBER]
									.toString(), DATE_YYYY_MM_DD_HH_MM_SS_sss));
			Double amt = Double.parseDouble(object[G4GConstants.FOUR_NUMBER]
					.toString());
			gameTournamentChallengeDTO.setPayoutamount(amt.longValue());
			Double fees = Double.parseDouble(object[G4GConstants.FIVE_NUMBER]
					.toString());
			gameTournamentChallengeDTO.setEntryfee(fees.longValue());
			gameTournamentChallengeDTO.setTournamentid(Integer
					.parseInt(object[G4GConstants.SIX_NUMBER].toString()));
			gameTournamentChallengeDTO.setMatchid(Integer
					.parseInt(object[G4GConstants.SEVEN_NUMBER].toString()));
			openChallengeList.add(gameTournamentChallengeDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_OPEN_CHALLENGE_DTO).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(FOUND_OPEN_CHALLENGES_LIST_SIZE).append(
								openChallengeList.size()).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(PARAMETERS)
						.append(PLAYERID).toString(),
				Level.INFO);
		return openChallengeList;
	}

	/**
	 * @see WidgetsService#getMyLobby(int)
	 */
	@SuppressWarnings( { UNUSED, UNCHECKED })
	public List<GameDTO> getMyLobby(int playerId, int networkid) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		StringBuffer sbquery = new StringBuffer();
		sbquery.append(SELECT).append(DISTINCT).append(G).append(DOT).append(
				ASTERISTIC);
		sbquery.append(SQLConstants.FROM).append(GAME_SQL).append(G).append(COMMA)
				.append(PLAYERGAME_SQL).append(PG);
		sbquery.append(SQLConstants.WHERE).append(G).append(DOT).append(GAMEID_SQL);
		sbquery.append(SQLConstants.EQUAL).append(PG).append(DOT)
				.append(GAMEID_SQL);
		sbquery.append(SQLConstants.AND).append(PG).append(DOT)
				.append(PLAYERID_SQL).append(SQLConstants.EQUAL);
		sbquery.append(playerId).append(SQLConstants.AND).append(G).append(DOT)
				.append(NETWORKID_DB).append(SQLConstants.EQUAL).append(networkid);

		List<GameDTO> myLobbyList = session.createSQLQuery(sbquery.toString())
				.addEntity(GameDTO.class).list();
		session.flush();
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
		return myLobbyList;
	}

	/**
	 * @param maxRecords
	 * @param maxPlayedInDays
	 * @return List<ActivePlayersDTO>
	 * @throws HibernateException
	 * @see WidgetsService#getMyLobby(int)
	 *
	 */
	@SuppressWarnings(UNCHECKED)
	public List<ActivePlayersDTO> getActivePlayers(int maxRecords,
			int maxPlayedInDays) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		StringBuffer activePlayersQuery = new StringBuffer();
		activePlayersQuery.append(SELECT).append(DISTINCT).append(P)
				.append(DOT).append(PLAYERID_SQL).append(COMMA).append(P).append(
						DOT).append(SCREENNAME_SQL).append(COMMA).append(COUNT)
				.append(OPEN_PAREN).append(T).append(DOT).append(ASTERISTIC)
				.append(CLOSE_PAREN).append(AS).append(CNT).append(
						SQLConstants.FROM).append(PLAYER_SQL).append(P).append(
						COMMA).append(TWOPLAYERMATCH_SQL).append(T).append(WHERE)
				.append(T).append(DOT).append(COMPLETEDDATE_SQL).append(
						GREATER_EQUAL).append(OPEN_PAREN).append(CURRENT_DATE_SQL)
				.append(MINUS).append(INTEGER_SQL).append(QUOTE);

		activePlayersQuery.append(maxPlayedInDays);
		activePlayersQuery.append(QUOTE).append(CLOSE_PAREN).append(
				SQLConstants.AND).append(OPEN_PAREN).append(P).append(DOT)
				.append(PLAYERID_SQL).append(SQLConstants.EQUAL).append(T).append(
						DOT).append(PLAYERONEID_SQL).append(OR).append(P).append(
						DOT).append(PLAYERID_SQL).append(SQLConstants.EQUAL)
				.append(T).append(DOT).append(PLAYERTWOID_SQL).append(CLOSE_PAREN)
				.append(GROUPBY).append(P).append(DOT).append(PLAYERID_SQL).append(
						COMMA).append(P).append(DOT).append(SCREENNAME_SQL).append(
						ORDERBY).append(CNT).append(MODEDESC).append(LIMIT);
		activePlayersQuery.append(maxRecords);
		int totalPlayers = 0;
		List<ActivePlayersDTO> activePlayers = new ArrayList<ActivePlayersDTO>();
		HibernateUtil.closeSession();

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		List activePlayersList = new ArrayList();

		try {
			Query query = session.createSQLQuery(activePlayersQuery.toString());

			activePlayersList = query.list();

			session.flush();
			transaction.commit();
		} catch (Exception e) {

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
		} finally {
			HibernateUtil.closeSession();
		}

		if (activePlayersList != null) {
			totalPlayers = activePlayersList.size();
		}
		/* Inserting data into ActivePlayersDTO */
		for (int index = G4GConstants.ZERO; index < totalPlayers; index++) {
			ActivePlayersDTO activePlayersDTO = new ActivePlayersDTO();
			Object[] activePlayersObject = (Object[]) activePlayersList
					.get(index);
			activePlayersDTO
					.setPlayerid(Integer
							.parseInt(activePlayersObject[G4GConstants.ZERO]
									.toString()));
			activePlayersDTO
					.setScreenname(activePlayersObject[G4GConstants.ONE_NUMBER]
							.toString());
			activePlayersDTO.setMaxMatchcount(Integer
					.parseInt(activePlayersObject[G4GConstants.TWO_NUMBER]
							.toString()));
			PlayerDTO playerDTO = PlayerServiceDelegator
					.getPlayer(activePlayersDTO.getPlayerid());
			User user = new User();
			try {
				user = G4GFinancialDelegator.getUserByUserId(playerDTO
						.getEmailaddress());
			} catch (InternalException_Exception e1) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
						e1);
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e1);
			} catch (UserNotFoundException_Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
						e);
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			}
			int defaultAvatarId = ((List<AvatarsDTO>) AvatarsServiceDelegator
					.getList()).get(ZERO).getId();
			int avatarId = ZERO;
			if (playerDTO.getAvatars() == null) {
				if (playerDTO.getPictureId() == null) {
					avatarId = defaultAvatarId;
				}
			} else {
				avatarId = playerDTO.getAvatars().getId();
			}
			activePlayersDTO.setLocation(user.getCity());
			activePlayersDTO.setAvatarid(avatarId);
			if (playerDTO.getPictureId() != null) {
				activePlayersDTO.setPictureid(playerDTO.getPictureId());
			}
			activePlayersDTO.setOnline(playerDTO.isIsonline());
			activePlayers.add(activePlayersDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_ACTIVE_PLAYERS_DTO).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return activePlayers;
	}
}
