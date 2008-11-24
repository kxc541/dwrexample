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
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAME;
import static com.g4g.utils.G4GConstants.GAMECOMPLETED;
import static com.g4g.utils.G4GConstants.GAMEID;
import static com.g4g.utils.G4GConstants.GAME_WISE_PLAYER_LIST;
import static com.g4g.utils.G4GConstants.GIVES_PLAYER_FOR_GIVEN_ID;
import static com.g4g.utils.G4GConstants.ID;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.PLAYER;
import static com.g4g.utils.G4GConstants.PLAYERDTO;
import static com.g4g.utils.G4GConstants.PLAYERID;
import static com.g4g.utils.G4GConstants.PLAYERONEACCEPTEDDATE;
import static com.g4g.utils.G4GConstants.PLAYERONEID;
import static com.g4g.utils.G4GConstants.PLAYERTWOACCEPTEDDATE;
import static com.g4g.utils.G4GConstants.PLAYERTWOID;
import static com.g4g.utils.G4GConstants.PLAYER_FRIENDS_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PLAYER_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PLAYER_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.PLAYER_MANAGE_PLAYER_GAME_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PLAYER_RECENT_GAMES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PLAYER_SCHEDULED_GAMES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.PLAYER_VERIFYING_GAMES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_UPDATED;
import static com.g4g.utils.G4GConstants.RECORD_UPDATED;
import static com.g4g.utils.G4GConstants.RETRIEVED_PLAYER_GAMES_LIST;
import static com.g4g.utils.G4GConstants.RETRIEVED_PLAYER_RECENT_GAMES_LIST;
import static com.g4g.utils.G4GConstants.SCHEDULED_START_DATE;
import static com.g4g.utils.G4GConstants.SCREEN_NAME;
import static com.g4g.utils.G4GConstants.SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.SET_PLAYER_FRIENDS_LIST;
import static com.g4g.utils.G4GConstants.STARTED;

import java.util.ArrayList;
import java.util.Date;
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

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.FriendsDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayerStatus;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.forms.ManagePlayerGameForm;
import com.g4g.forms.PlayerGameScheduleForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class to implement service functions for Player table.
 *
 * @author Pratik
 *
 */
public class PlayerServiceImpl implements PlayerService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see PlayerService#add(PlayerDTO)
	 */
	public PlayerDTO add(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(dto);
			transaction.commit();
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
	 * @see PlayerService#delete(PlayerDTO)
	 */
	public void delete(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERDTO).toString(), Level.INFO);
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
	 * @see PlayerService#getGameWisePlayersList(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerDTO> getGameWisePlayersList(int gameId)
			throws HibernateException {
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(GAMEID).toString(), Level.INFO);
		/* Getting players details game-wise */
		List<PlayerDTO> allPlayersList = new ArrayList<PlayerDTO>();
		List<PlayergameDTO> playersIdList = new ArrayList<PlayergameDTO>();

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.GAME, new GameDTO(gameId));
		playersIdList = PlayerGameServiceDelegator.getList(searchCriteria);

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(GAME, new GameDTO(gameId));
		PlayerGameServiceDelegator.getList(searchCriteria);
		searchCriteria.removeAllAttribute();
		/* Getting all players list */
		int totalIdList = playersIdList.size();
		for (int index = G4GConstants.ZERO; index < totalIdList; index++) {
			PlayergameDTO object = playersIdList.get(index);
			PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(object
					.getPlayer().getId());
			allPlayersList.add(playerDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(GAME_WISE_PLAYER_LIST).append(
								allPlayersList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return allPlayersList;
	}

	/**
	 * @see PlayerService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<PlayerDTO> list = null;
		try {
			Query query = session.createQuery(new StringBuffer(
					SQLConstants.FROM).append(SQLConstants.PLAYERDTO_SQL)
					.toString());
			Transaction transaction = session.beginTransaction();
			list = query.list();
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

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_LIST_SIZE).append(list.size())
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
	 * @param searchCriteria
	 * @return List<PlayerDTO>
	 * @see PlayerService#getList(SearchCriteria ) )
	 */
	public List<PlayerDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(SEARCH_CRITERIA).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerDTO.class);
		String orderBY = searchCriteria.getOrderBy();
		if (orderBY != null) {
			if (searchCriteria.isAsc()) {
				criteria.addOrder(Order.asc(orderBY));
			} else {
				criteria.addOrder(Order.desc(orderBY));
			}
		}
		Set<String> keys = searchCriteria.getAttributeNames();
		Iterator it = keys.iterator();
		String key = null;
		Object value = null;
		while (it.hasNext()) {
			key = (String) it.next();
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
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();

		try {
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			for (String keyexception : keys) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(keyexception).toString(), Level.ERROR);
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
						.append(PLAYER_LIST_SIZE_CRITERIA).append(list.size())
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
	 * @see PlayerService#getList(SearchCriteria, boolean)
	 */
	public List<PlayerDTO> getList(SearchCriteria searchCriteria,
			boolean caseinsensitive) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(SEARCH_CRITERIA).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerDTO.class);
		String orderBY = searchCriteria.getOrderBy();
		if (orderBY != null) {
			if (searchCriteria.isAsc()) {
				criteria.addOrder(Order.asc(orderBY));
			} else {
				criteria.addOrder(Order.desc(orderBY));
			}
		}
		Set<String> keys = searchCriteria.getAttributeNames();
		Iterator it = keys.iterator();
		String key = null;
		Object value = null;
		while (it.hasNext()) {
			key = (String) it.next();
			value = searchCriteria.getAttribute(key);
			if (caseinsensitive) {
				criteria.add(Restrictions.ilike(key, value));
			} else {
				criteria.add(Restrictions.eq(key, value));
			}
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
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();

		try {
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			for (String keyexception : keys) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(keyexception).toString(), Level.ERROR);
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
						.append(PLAYER_LIST_SIZE_CRITERIA).append(list.size())
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
	 * @see PlayerService#getPlayer(int)
	 */

	public PlayerDTO getPlayer(int id) throws HibernateException {
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
		Transaction transaction = session.beginTransaction();
		PlayerDTO dto = (PlayerDTO) session.get(PlayerDTO.class, id);
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(GIVES_PLAYER_FOR_GIVEN_ID).toString(),
				Level.INFO);
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
	 * @return
	 * @throws HibernateException
	 * @see PlayerService#isUserOnline(String)
	 */
	public List<PlayerStatus> usersOnline() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(SCREEN_NAME).toString(), Level.INFO);
		int count = G4GConstants.ZERO;
		StringBuffer query = new StringBuffer();
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		query.append(
				new StringBuffer(SQLConstants.SELECT)
						.append(G4GConstants.SCREENNAME_DB).append(SQLConstants.COMMA).append(G4GConstants.IS_ONLINE).append(
								SQLConstants.FROM).append(SQLConstants.PLAYER_SQL));

		Query q = session.createSQLQuery(query.toString());
		List list = q.list();
		int total = list.size();
		List<PlayerStatus> playerStatus = new ArrayList<PlayerStatus>();

		for (int index = 0; index < total; index++) {
			PlayerStatus player = new PlayerStatus();
			Object[] object = (Object[]) list.get(index);
			player.setScreenName(object[G4GConstants.ZERO] + G4GConstants.NONE);
			player.setOnline(Boolean.valueOf(object[G4GConstants.ONE_NUMBER] + G4GConstants.NONE));

			playerStatus.add(player);
		}


		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(ENDED).toString(), Level.INFO);
		return playerStatus;
	}

	/**
	 * @see PlayerService#getPlayerFriends(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerDTO> getPlayerFriends(int playerId)
			throws HibernateException {
		List<PlayerDTO> resultList = new ArrayList<PlayerDTO>();
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

		Query query = session.createQuery(new StringBuffer(SQLConstants.FROM)
				.append(SQLConstants.FRIENDSDTO_SQL).append(SQLConstants.WHERE)
				.append(SQLConstants.PLAYER2ACCEPTED_SQL).append(SQLConstants.IS)
				.append(SQLConstants.NOT).append(SQLConstants.NULL).append(
						SQLConstants.AND).append(SQLConstants.OPEN_PAREN)
				.append(SQLConstants.PLAYERID1_SQL).append(SQLConstants.EQUAL)
				.append(playerId).append(SQLConstants.OR).append(
						SQLConstants.PLAYERID2_SQL).append(SQLConstants.EQUAL)
				.append(playerId).append(SQLConstants.CLOSE_PAREN).toString());
		List<FriendsDTO> playerOneFriendsList = query.list();

		for (int index = G4GConstants.ZERO; index < playerOneFriendsList.size(); index++) {
			FriendsDTO friendsDTO = playerOneFriendsList.get(index);
			PlayerDTO playerDTO = PlayerServiceDelegator
					.getPlayer(playerId == friendsDTO.getPlayerid1() ? friendsDTO
							.getPlayerid2()
							: friendsDTO.getPlayerid1());
			resultList.add(playerDTO);
		}
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(SET_PLAYER_FRIENDS_LIST).toString(),
						Level.INFO);
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_FRIENDS_LIST_SIZE).append(
								resultList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return resultList;
	}

	/**
	 * @see PlayerService#getPlayerGame(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<ManagePlayerGameForm> getPlayerGame(int playerId)
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
		ArrayList<ManagePlayerGameForm> resultList = new ArrayList<ManagePlayerGameForm>();
		SearchCriteria playerGameSearchCriteria = new SearchCriteria();
		playerGameSearchCriteria.setAttribute(PLAYER, PlayerServiceDelegator
				.getPlayer(playerId));
		// Get list containing games of player
		List playerGameList = PlayerGameServiceDelegator
				.getList(playerGameSearchCriteria);
		// Loop to get game information by gameId in PlayerGameDTO in list
		for (int index = G4GConstants.ZERO; index < playerGameList.size(); index++) {
			PlayergameDTO playergameDTO = (PlayergameDTO) playerGameList
					.get(index);
			SearchCriteria gameSearchCriteria = new SearchCriteria();
			gameSearchCriteria
					.setAttribute(ID, playergameDTO.getGame().getId());
			// Get game according to gameId in PlayerGameDTO
			GameDTO gameDTO = GameServiceDelegator.getGame(playergameDTO
					.getGame().getId());
			// set information related to game in form
			ManagePlayerGameForm managePlayerGameForm = new ManagePlayerGameForm();
			managePlayerGameForm.setPlayerId(playergameDTO.getPlayer().getId());
			managePlayerGameForm.setGameId(gameDTO.getId());
			managePlayerGameForm.setId(String.valueOf(playergameDTO.getId()));
			managePlayerGameForm.setGameName(gameDTO.getGamename());
			resultList.add(managePlayerGameForm);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(RETRIEVED_PLAYER_GAMES_LIST).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_MANAGE_PLAYER_GAME_LIST_SIZE).append(
								resultList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return resultList;
	}

	/**
	 * @see PlayerService#getPlayerRecentGames(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerGameScheduleForm> getPlayerRecentGames(int playerId)
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
		List<PlayerGameScheduleForm> resultList = new ArrayList<PlayerGameScheduleForm>();

		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(TwoplayermatchDTO.class);
		criteria.add(Restrictions.eq(G4GConstants.GAMECOMPLETED, G4GConstants.GAME_STAUS_COMPLETED));
		criteria.add(Restrictions.or(Restrictions.eq(G4GConstants.PLAYERONEID, new PlayerDTO(playerId)), Restrictions.eq(G4GConstants.PLAYERTWOID, new PlayerDTO(playerId))));
		criteria.addOrder(Order.desc(G4GConstants.SCHEDULED_START_DATE));
		Transaction transaction = session.beginTransaction();
		List<TwoplayermatchDTO> recentMatches = criteria.list();
		transaction.commit();
		HibernateUtil.closeSession();

		for (TwoplayermatchDTO twoplayermatchDTO : recentMatches) {
			TwoplayertournamentDTO twoplayertournamentDTO = twoplayermatchDTO.getTwoplayertournament();
			if (twoplayertournamentDTO.getCancellationdate() == null) {
			GameDTO gameDTO = twoplayertournamentDTO.getGameDTO();
			PlayerDTO playerDTO;
			PlayerDTO oppositePlayerDTO;
			if(twoplayermatchDTO.getPlayeroneid().getId()==playerId) {
				playerDTO = twoplayermatchDTO.getPlayeroneid();
				oppositePlayerDTO= twoplayermatchDTO.getPlayertwoid();
			}else {
				playerDTO = twoplayermatchDTO.getPlayertwoid();
				oppositePlayerDTO= twoplayermatchDTO.getPlayeroneid();
			}
			PlayerGameScheduleForm playerGameScheduleForm = new PlayerGameScheduleForm();
			playerGameScheduleForm.setMatchId(twoplayermatchDTO.getId());
			playerGameScheduleForm.setTournamentId(twoplayermatchDTO.getTwoplayertournament().getId());
			playerGameScheduleForm.setPlayerId(playerDTO.getId());
			playerGameScheduleForm.setPlayerName(playerDTO.getScreenname());
			if (oppositePlayerDTO != null) {
				playerGameScheduleForm.setOppositePlayerId(oppositePlayerDTO.getId());
				playerGameScheduleForm.setOppositePlayerName(oppositePlayerDTO.getScreenname());
			}
			playerGameScheduleForm.setScheduledStartDate(twoplayermatchDTO.getScheduledstartdate());
			playerGameScheduleForm.setPayoutAmount(twoplayermatchDTO.getPayoutamount());
			playerGameScheduleForm.setGameDTO(gameDTO);
			playerGameScheduleForm.setGameId(gameDTO.getId());
			playerGameScheduleForm.setGameName(gameDTO.getGamename());
			// add playerGameScheduleForm to result list
			resultList.add(playerGameScheduleForm);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(RETRIEVED_PLAYER_RECENT_GAMES_LIST).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_RECENT_GAMES_LIST_SIZE).append(
								resultList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return resultList;
	}

	/**
	 *
	 * @see PlayerService#getPlayerSchedule(int)
	 *
	 *
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerGameScheduleForm> getPlayerSchedule(int playerId)
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
		// List search criteria here

		List<PlayerGameScheduleForm> resultList = new ArrayList<PlayerGameScheduleForm>();
		/*
		 * Player One Search criteria - PlayerOneId as playerId - Game is yet
		 * not completed - 0 - ScheduleStartDate is greater or equal to current
		 * date time.
		 */
		SearchListCriteria playerOneSearchCriteria = new SearchListCriteria();
		playerOneSearchCriteria.setAttribute(PLAYERONEID, new Object[] {
				new PlayerDTO(playerId), SearchListCriteria.EQ });
		playerOneSearchCriteria.setAttribute(GAMECOMPLETED, new Object[] {
				G4GConstants.ZERO, SearchListCriteria.EQ });
		/*Date newDate = DataUtil.todayGMT();
		long newTime = newDate.getTime();
		newTime = newTime - G4GConstants.TWO_NUMBER * G4GConstants.SIXTY
				* G4GConstants.SIXTY * G4GConstants.ONE_THOUSAND;
		newDate.setTime(newTime);
		playerOneSearchCriteria.setAttribute(SCHEDULED_START_DATE,
				new Object[] { newDate, SearchListCriteria.GE });*/
		playerOneSearchCriteria.setAttribute(PLAYERONEACCEPTEDDATE,
				new Object[] { null, SearchListCriteria.ISNOTNULL });

		List playerOneMatchList = TwoPlayerMatchServiceDelegator
				.getList(playerOneSearchCriteria);
		if (playerOneMatchList != null) {
			for (int index = G4GConstants.ZERO; index < playerOneMatchList
					.size(); index++) {
				TwoplayermatchDTO twoplayermatchDTO = (TwoplayermatchDTO) playerOneMatchList
						.get(index);
				SearchCriteria tournamentSearchCriteria = new SearchCriteria();
				tournamentSearchCriteria.setAttribute(ID, twoplayermatchDTO
						.getTwoplayertournament().getId());
				List tournamentList = TwoPlayerTournamentServiceDelegator
						.getList(tournamentSearchCriteria);
				if (tournamentList.size() > G4GConstants.ZERO) {
					TwoplayertournamentDTO twoplayertournamentDTO = (TwoplayertournamentDTO) tournamentList
							.get(G4GConstants.ZERO);
					GameDTO gameDTO = GameServiceDelegator
							.getGame(twoplayertournamentDTO.getGameDTO()
									.getId());

					PlayerDTO playerDTO;
					PlayerDTO oppositePlayerDTO;

					playerDTO = twoplayermatchDTO.getPlayeroneid();
					PlayerGameScheduleForm playerGameScheduleForm = new PlayerGameScheduleForm();

					if (twoplayermatchDTO.getPlayertwoid() != null) {
						oppositePlayerDTO = twoplayermatchDTO.getPlayertwoid();
						if (oppositePlayerDTO != null) {
							playerGameScheduleForm
									.setOppositePlayerId(oppositePlayerDTO
											.getId());
							playerGameScheduleForm
									.setOppositePlayerName(oppositePlayerDTO
											.getScreenname());
						}
					} else {
						oppositePlayerDTO = new PlayerDTO();
					}
					// setting information in playerGameScheduleForm
					playerGameScheduleForm
							.setMatchId(twoplayermatchDTO.getId());
					playerGameScheduleForm.setTournamentId(twoplayermatchDTO
							.getTwoplayertournament().getId());
					playerGameScheduleForm.setPlayerId(playerDTO.getId());
					playerGameScheduleForm.setPlayerName(playerDTO
							.getScreenname());
					playerGameScheduleForm.setGameDTO(gameDTO);
					playerGameScheduleForm
							.setScheduledStartDate(twoplayermatchDTO
									.getScheduledstartdate());
					playerGameScheduleForm.setPayoutAmount(twoplayermatchDTO
							.getPayoutamount());
					playerGameScheduleForm.setGameId(gameDTO.getId());
					playerGameScheduleForm.setGameName(gameDTO.getGamename());
					// add playerGameScheduleForm to result list
					resultList.add(playerGameScheduleForm);
				}
			}
		}

		/*
		 * Player One Search criteria - PlayerTwoId as playerId - Game is yet
		 * not completed - 0 - ScheduleStartDate is greater or equal to current
		 * date time.
		 */

		SearchListCriteria playerTwoSearchCriteria = new SearchListCriteria();
		playerTwoSearchCriteria.setAttribute(PLAYERTWOID, new Object[] {
				new PlayerDTO(playerId), SearchListCriteria.EQ });
		playerTwoSearchCriteria.setAttribute(GAMECOMPLETED, new Object[] {
				G4GConstants.ZERO, SearchListCriteria.EQ });
		/*playerTwoSearchCriteria.setAttribute(SCHEDULED_START_DATE,
				new Object[] { newDate, SearchListCriteria.GE });*/
		playerTwoSearchCriteria.setAttribute(PLAYERONEACCEPTEDDATE,
				new Object[] { null, SearchListCriteria.ISNOTNULL });
		playerTwoSearchCriteria.setAttribute(PLAYERTWOACCEPTEDDATE,
				new Object[] { null, SearchListCriteria.ISNOTNULL });
		List playerTwoMatchList = TwoPlayerMatchServiceDelegator
				.getList(playerTwoSearchCriteria);
		for (int index = G4GConstants.ZERO; index < playerTwoMatchList.size(); index++) {

			TwoplayermatchDTO twoplayermatchDTO = (TwoplayermatchDTO) playerTwoMatchList
					.get(index);

			SearchCriteria tournamentSearchCriteria = new SearchCriteria();
			tournamentSearchCriteria.setAttribute(ID, twoplayermatchDTO
					.getTwoplayertournament().getId());
			List tournamentList = TwoPlayerTournamentServiceDelegator
					.getList(tournamentSearchCriteria);
			if (tournamentList.size() > G4GConstants.ZERO) {
				TwoplayertournamentDTO twoplayertournamentDTO = (TwoplayertournamentDTO) tournamentList
						.get(G4GConstants.ZERO);
				GameDTO gameDTO = GameServiceDelegator
						.getGame(twoplayertournamentDTO.getGameDTO().getId());
				PlayerDTO playerDTO;
				PlayerDTO oppositePlayerDTO;

				playerDTO = twoplayermatchDTO.getPlayertwoid();
				oppositePlayerDTO = twoplayermatchDTO.getPlayeroneid();

				// setting information in playerGameScheduleForm
				PlayerGameScheduleForm playerGameScheduleForm = new PlayerGameScheduleForm();
				playerGameScheduleForm.setMatchId(twoplayermatchDTO.getId());
				playerGameScheduleForm.setTournamentId(twoplayermatchDTO
						.getTwoplayertournament().getId());
				playerGameScheduleForm.setPlayerId(playerDTO.getId());
				playerGameScheduleForm.setPlayerName(playerDTO.getScreenname());
				playerGameScheduleForm.setOppositePlayerId(oppositePlayerDTO
						.getId());
				playerGameScheduleForm.setOppositePlayerName(oppositePlayerDTO
						.getScreenname());
				playerGameScheduleForm.setScheduledStartDate(twoplayermatchDTO
						.getScheduledstartdate());
				playerGameScheduleForm.setPayoutAmount(twoplayermatchDTO
						.getPayoutamount());
				playerGameScheduleForm.setGameDTO(gameDTO);
				playerGameScheduleForm.setGameId(gameDTO.getId());
				playerGameScheduleForm.setGameName(gameDTO.getGamename());

				// add playerGameScheduleForm to result list
				resultList.add(playerGameScheduleForm);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(RETRIEVED_PLAYER_RECENT_GAMES_LIST).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_SCHEDULED_GAMES_LIST_SIZE).append(
								resultList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return resultList;
	}

	/**
	 * @see PlayerService#getPlayerVerifying(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<PlayerGameScheduleForm> getPlayerVerifying(int playerId)
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
		List<PlayerGameScheduleForm> resultList = new ArrayList();
		/*
		 * Player One Search criteria - PlayerOneId as playerId - Game is yet
		 * not completed - 0 - ScheduleStartDate is less or equal to current
		 * date time.
		 */

		SearchListCriteria playerOneSearchCriteria = new SearchListCriteria();
		playerOneSearchCriteria.setAttribute(PLAYERONEID, new Object[] {
				new PlayerDTO(playerId), SearchListCriteria.EQ });
		playerOneSearchCriteria.setAttribute(GAMECOMPLETED, new Object[] {
				G4GConstants.ZERO, SearchListCriteria.EQ });
		playerOneSearchCriteria.setAttribute(SCHEDULED_START_DATE,
				new Object[] { DataUtil.todayGMT(), SearchListCriteria.LE });
		List playerOneMatchList = TwoPlayerMatchServiceDelegator
				.getList(playerOneSearchCriteria);
		if (playerOneMatchList != null) {
			for (int index = G4GConstants.ZERO; index < playerOneMatchList
					.size(); index++) {
				TwoplayermatchDTO twoplayermatchDTO = (TwoplayermatchDTO) playerOneMatchList
						.get(index);

				SearchCriteria tournamentSearchCriteria = new SearchCriteria();
				tournamentSearchCriteria.setAttribute(ID, twoplayermatchDTO
						.getTwoplayertournament().getId());

				List tournamentList = TwoPlayerTournamentServiceDelegator
						.getList(tournamentSearchCriteria);

				if (tournamentList.size() > G4GConstants.ZERO) {
					TwoplayertournamentDTO twoplayertournamentDTO = (TwoplayertournamentDTO) tournamentList
							.get(G4GConstants.ZERO);
					GameDTO gameDTO = GameServiceDelegator
							.getGame(twoplayertournamentDTO.getGameDTO()
									.getId());

					PlayerDTO playerDTO;
					PlayerDTO oppositePlayerDTO;

					playerDTO = twoplayermatchDTO.getPlayeroneid();
					PlayerGameScheduleForm playerGameScheduleForm = new PlayerGameScheduleForm();

					if (twoplayermatchDTO.getPlayertwoid() != null) {
						oppositePlayerDTO = twoplayermatchDTO.getPlayertwoid();
						if (oppositePlayerDTO != null) {
							playerGameScheduleForm
									.setOppositePlayerId(oppositePlayerDTO
											.getId());
							playerGameScheduleForm
									.setOppositePlayerName(oppositePlayerDTO
											.getScreenname());
						}
					} else {
						oppositePlayerDTO = new PlayerDTO();
					}
					// setting information in playerGameScheduleForm
					playerGameScheduleForm
							.setMatchId(twoplayermatchDTO.getId());
					playerGameScheduleForm.setTournamentId(twoplayermatchDTO
							.getTwoplayertournament().getId());
					playerGameScheduleForm.setPlayerId(playerDTO.getId());
					playerGameScheduleForm.setPlayerName(playerDTO
							.getScreenname());
					playerGameScheduleForm.setGameDTO(gameDTO);
					playerGameScheduleForm
							.setScheduledStartDate(twoplayermatchDTO
									.getScheduledstartdate());
					playerGameScheduleForm.setPayoutAmount(twoplayermatchDTO
							.getPayoutamount());
					playerGameScheduleForm.setGameId(gameDTO.getId());
					playerGameScheduleForm.setGameName(gameDTO.getGamename());
					// add playerGameScheduleForm to result list
					resultList.add(playerGameScheduleForm);
				}
			}
		}

		/*
		 * Player One Search criteria - PlayerTwoId as playerId - Game is yet
		 * not completed - 0 - ScheduleStartDate is less or equal to current
		 * date time.
		 */
		SearchListCriteria playerTwoSearchCriteria = new SearchListCriteria();
		playerTwoSearchCriteria.setAttribute(PLAYERTWOID, new Object[] {
				new PlayerDTO(playerId), SearchListCriteria.EQ });
		playerTwoSearchCriteria.setAttribute(GAMECOMPLETED, new Object[] {
				G4GConstants.ZERO, SearchListCriteria.EQ });
		playerTwoSearchCriteria.setAttribute(SCHEDULED_START_DATE,
				new Object[] { DataUtil.todayGMT(), SearchListCriteria.LE });

		List playerTwoMatchList = TwoPlayerMatchServiceDelegator
				.getList(playerTwoSearchCriteria);
		for (int index = G4GConstants.ZERO; index < playerTwoMatchList.size()
				- G4GConstants.ONE_NUMBER; index++) {

			TwoplayermatchDTO twoplayermatchDTO = (TwoplayermatchDTO) playerTwoMatchList
					.get(index);

			SearchCriteria tournamentSearchCriteria = new SearchCriteria();
			tournamentSearchCriteria.setAttribute(ID, twoplayermatchDTO
					.getTwoplayertournament().getId());
			List tournamentList = TwoPlayerTournamentServiceDelegator
					.getList(tournamentSearchCriteria);
			if (tournamentList.size() > G4GConstants.ZERO) {
				TwoplayertournamentDTO twoplayertournamentDTO = (TwoplayertournamentDTO) tournamentList
						.get(G4GConstants.ZERO);
				GameDTO gameDTO = GameServiceDelegator
						.getGame(twoplayertournamentDTO.getGameDTO().getId());
				PlayerDTO playerDTO;
				PlayerDTO oppositePlayerDTO;

				playerDTO = twoplayermatchDTO.getPlayertwoid();
				oppositePlayerDTO = twoplayermatchDTO.getPlayeroneid();

				// setting information in playerGameScheduleForm
				PlayerGameScheduleForm playerGameScheduleForm = new PlayerGameScheduleForm();
				playerGameScheduleForm.setMatchId(twoplayermatchDTO.getId());
				playerGameScheduleForm.setTournamentId(twoplayermatchDTO
						.getTwoplayertournament().getId());
				playerGameScheduleForm.setPlayerId(playerDTO.getId());
				playerGameScheduleForm.setPlayerName(playerDTO.getScreenname());
				playerGameScheduleForm.setOppositePlayerId(oppositePlayerDTO
						.getId());
				playerGameScheduleForm.setOppositePlayerName(oppositePlayerDTO
						.getScreenname());
				playerGameScheduleForm.setScheduledStartDate(twoplayermatchDTO
						.getScheduledstartdate());
				playerGameScheduleForm.setPayoutAmount(twoplayermatchDTO
						.getPayoutamount());
				playerGameScheduleForm.setGameDTO(gameDTO);
				playerGameScheduleForm.setGameId(gameDTO.getId());
				playerGameScheduleForm.setGameName(gameDTO.getGamename());

				// add playerGameScheduleForm to result list
				resultList.add(playerGameScheduleForm);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_VERIFYING_GAMES_LIST_SIZE).append(
								resultList.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return resultList;
	}

	/**
	 * @see PlayerService#update(PlayerDTO)
	 */
	public PlayerDTO update(PlayerDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(PLAYERDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(dto);
			transaction.commit();
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

}
