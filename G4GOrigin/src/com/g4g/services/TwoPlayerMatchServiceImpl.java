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

import static com.g4g.utils.G4GConstants.AVAILABLE_MATCH_LIST_SIZE;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CHECK_PLAYER_CHALLENGE_AVAILABILITY;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.COMMA;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DATE;
import static com.g4g.utils.G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A;
import static com.g4g.utils.G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS;
import static com.g4g.utils.G4GConstants.DIRECTED_CHALLENGE_FORM;
import static com.g4g.utils.G4GConstants.DTO;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAME_STAUS_PENDING;
import static com.g4g.utils.G4GConstants.GIVES_RANDOM_MATCHDTO;
import static com.g4g.utils.G4GConstants.ID;
import static com.g4g.utils.G4GConstants.LEVEL;
import static com.g4g.utils.G4GConstants.LEVELS_AVAILABLE;
import static com.g4g.utils.G4GConstants.LEVELS_NOT_AVAILABLE;
import static com.g4g.utils.G4GConstants.MATCHID;
import static com.g4g.utils.G4GConstants.MATCH_AVAILABLE;
import static com.g4g.utils.G4GConstants.NOLIST;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.PLAYERID;
import static com.g4g.utils.G4GConstants.PLAYERONEID;
import static com.g4g.utils.G4GConstants.PLAYERTWOID;
import static com.g4g.utils.G4GConstants.PLAYER_AT_SCHEDULED_DATE_LIST;
import static com.g4g.utils.G4GConstants.PLAYER_HAS_NOT_JOINED_THE_LEVEL;
import static com.g4g.utils.G4GConstants.PLAYER_JOINED_THE_LEVEL;
import static com.g4g.utils.G4GConstants.PLAYER_ONE_ID;
import static com.g4g.utils.G4GConstants.PLUS;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_UPDATED;
import static com.g4g.utils.G4GConstants.RECORD_UPDATED;
import static com.g4g.utils.G4GConstants.SCHEDULED_START_DATE;
import static com.g4g.utils.G4GConstants.SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.SEARCH_LIST_CRITERIA;
import static com.g4g.utils.G4GConstants.SESSIONID;
import static com.g4g.utils.G4GConstants.SET_MATCH_DTO;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TWOPLAYERMATCHDTO;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_MATCH_LIST_SIZE;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_MATCH_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT;
import static com.g4g.utils.G4GConstants.UPCOMING_MATCH_LIST;
import static com.g4g.utils.G4GConstants.UTC;
import static com.g4g.utils.SQLConstants.AND;
import static com.g4g.utils.SQLConstants.ASTERISTIC;
import static com.g4g.utils.SQLConstants.CURRENT_TIMESTAMP_AT_TIMEZONE;
import static com.g4g.utils.SQLConstants.DOT;
import static com.g4g.utils.SQLConstants.EQUAL;
import static com.g4g.utils.SQLConstants.FROM;
import static com.g4g.utils.SQLConstants.G;
import static com.g4g.utils.SQLConstants.GAMECOMPLETED_SQL;
import static com.g4g.utils.SQLConstants.GAMEID_SQL;
import static com.g4g.utils.SQLConstants.GAMENAME_SQL;
import static com.g4g.utils.SQLConstants.GAME_SQL;
import static com.g4g.utils.SQLConstants.GREATER;
import static com.g4g.utils.SQLConstants.GREATER_EQUAL;
import static com.g4g.utils.SQLConstants.IMGSRC_SQL;
import static com.g4g.utils.SQLConstants.INTERVAL_SQL;
import static com.g4g.utils.SQLConstants.IS;
import static com.g4g.utils.SQLConstants.LESS;
import static com.g4g.utils.SQLConstants.LESS_EQUAL;
import static com.g4g.utils.SQLConstants.M;
import static com.g4g.utils.SQLConstants.MATCHID_SQL;
import static com.g4g.utils.SQLConstants.N;
import static com.g4g.utils.SQLConstants.NEGATIVE_EIGHTY_MINUTES;
import static com.g4g.utils.SQLConstants.NEGETIVE_ONE_HOURS;
import static com.g4g.utils.SQLConstants.NETWORKID_SQL;
import static com.g4g.utils.SQLConstants.NETWORKNAME_SQL;
import static com.g4g.utils.SQLConstants.NETWORK_SQL;
import static com.g4g.utils.SQLConstants.NOT;
import static com.g4g.utils.SQLConstants.NULL;
import static com.g4g.utils.SQLConstants.PLAYERONEID_SQL;
import static com.g4g.utils.SQLConstants.PLAYERTWOID_SQL;
import static com.g4g.utils.SQLConstants.QUOTE;
import static com.g4g.utils.SQLConstants.SCHEDULEDSTARTDATE_SQL;
import static com.g4g.utils.SQLConstants.SELECT;
import static com.g4g.utils.SQLConstants.SPACE;
import static com.g4g.utils.SQLConstants.T;
import static com.g4g.utils.SQLConstants.TOURNAMENTID_SQL;
import static com.g4g.utils.SQLConstants.TOUR_SQL;
import static com.g4g.utils.SQLConstants.TWOPLAYERMATCH_SQL;
import static com.g4g.utils.SQLConstants.TWOPLAYERTOURNAMENT_SQL;
import static com.g4g.utils.SQLConstants.WHERE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.g4g.dto.MatchDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class contains method implementation related to twoplayermatch.
 *
 * @author ankur
 */
public class TwoPlayerMatchServiceImpl implements TwoPlayerMatchService {

	private static final String JAVADOCREFERENCE = "JavadocReference"; //$NON-NLS-1$
	private static final String DEPRECATION = "deprecation"; //$NON-NLS-1$
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	private static TwoPlayerMatchServiceImpl i = new TwoPlayerMatchServiceImpl();
	private static SearchCriteria searchCriteria = new SearchCriteria();

	/**
	 * @see com.g4g.services.TwoPlayerMatchService#add(com.g4g.dto.TwoplayermatchDTO)
	 */
	public TwoplayermatchDTO add(TwoplayermatchDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERMATCHDTO).toString(), Level.INFO);
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
	 *
	 * @see com.g4g.services.TwoPlayerMatchService#delete(com.g4g.dto.TwoplayermatchDTO)
	 */
	public void delete(TwoplayermatchDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERMATCHDTO).toString(), Level.INFO);
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
	 *
	 * @see com.g4g.services.TwoPlayerMatchService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayermatchDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERMATCHDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<TwoplayermatchDTO> list = null;
		try {
			Query query = session.createQuery(new StringBuffer(
					SQLConstants.FROM).append(SQLConstants.TWOPLAYERMATCHDTO_SQL)
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
						.append(TWO_PLAYER_MATCH_LIST_SIZE).append(list.size())
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
	 * @see com.g4g.services.TwoPlayerMatchService#getList(com.g4g.dto.SearchCriteria)
	 */

	@SuppressWarnings(UNCHECKED)
	public List<TwoplayermatchDTO> getList(SearchCriteria searchCriteria)
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
		Criteria criteria = session.createCriteria(TwoplayermatchDTO.class);
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
		List<TwoplayermatchDTO> list = new ArrayList<TwoplayermatchDTO>();

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
						.append(TWO_PLAYER_MATCH_LIST_SIZE_CRITERIA).append(
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
	 * @see com.g4g.services.TwoPlayerMatchService#getList(com.g4g.dto.SearchListCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayermatchDTO> getList(SearchListCriteria searchCriteria)
			throws HibernateException {
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
		Criteria criteria = session.createCriteria(TwoplayermatchDTO.class);
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
		Object[] value = null;
		while (it.hasNext()) {
			key = it.next();
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
			} else if (((String) value[G4GConstants.ONE_NUMBER])
					.equals(SearchListCriteria.IN)) {
				criteria.add(Restrictions.in(key,
						(Object[]) value[G4GConstants.ZERO]));
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
		List<TwoplayermatchDTO> list = new ArrayList<TwoplayermatchDTO>();
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
						.append(TWO_PLAYER_MATCH_LIST_SIZE_CRITERIA).append(
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
	 *
	 * @throws Exception
	 * @see com.g4g.services.TwoPlayerMatchService#update(com.g4g.dto.TwoplayermatchDTO)
	 */
	public TwoplayermatchDTO update(TwoplayermatchDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERMATCHDTO).toString(), Level.INFO);
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
	 * @see com.g4g.services.TwoPlayerMatchService#get(com.g4g.dto.TwoplayermatchDTO)
	 */
	public TwoplayermatchDTO get(TwoplayermatchDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERMATCHDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		dto = (TwoplayermatchDTO) session.get(TwoplayermatchDTO.class, dto.getId());
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
						.append(ENDED).toString(), Level.INFO);
		return dto;
	}

	public TwoplayermatchDTO getDTOFromId(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(ID).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		TwoplayermatchDTO dto = (TwoplayermatchDTO) session.get(
				TwoplayermatchDTO.class, id);
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
						.append(ENDED).toString(), Level.INFO);
		return dto;
	}

	public Date getMaxCompletedDate() {
		// session.createSQLQuery("")
		return null;
	}

	/**
	 *
	 * @see com.g4g.services.TwoPlayerMatchService#CheckPlayerAtScheduleDate(int,
	 *      java.util.Date)
	 */
	public List<TwoplayermatchDTO> CheckPlayerAtScheduleDate(int id, Date date)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(ID).append(COMMA).append(DATE).toString(),
				Level.INFO);
		TwoPlayerMatchServiceImpl i = new TwoPlayerMatchServiceImpl();
		List<TwoplayermatchDTO> list = new ArrayList<TwoplayermatchDTO>();
		SimpleDateFormat format = new SimpleDateFormat(
				DATE_DD_MM_YYYY_AT_HH_MM_A);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(PLAYERONEID, id);
		try {
			searchCriteria.setAttribute(SCHEDULED_START_DATE, format
					.parseObject(date.toString()));
		} catch (ParseException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, e);
		}
		list = i.getList(searchCriteria);
		if (list.size() != G4GConstants.ZERO) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(
									list.get(G4GConstants.ZERO)
											.getPayoutamount()).toString(),
					Level.ERROR);
		} else {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(NOLIST).toString(), Level.INFO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_AT_SCHEDULED_DATE_LIST).append(
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
	 * @see com.g4g.services.TwoPlayerMatchService#getMatchInformation(int,HttpServletRequest)
	 */
	@SuppressWarnings( { UNCHECKED, JAVADOCREFERENCE })
	public MatchDTO getMatchInformation(int matchId, HttpServletRequest request)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(MATCHID).append(COMMA).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		UserDTO userDTO = new UserDTO();
		if (DataUtil.getUserDTO(request) != null) {
			userDTO = DataUtil.getUserDTO(request);
		}
		Session session = HibernateUtil.getSession();
		StringBuffer query = new StringBuffer();
		query.append(SELECT).append(M).append(DOT).append(SCHEDULEDSTARTDATE_SQL)
				.append(COMMA).append(G).append(DOT).append(GAMENAME_SQL).append(
						COMMA).append(G).append(DOT).append(IMGSRC_SQL).append(
						COMMA).append(G).append(DOT).append(GAMEID_SQL).append(
						COMMA).append(N).append(DOT).append(NETWORKNAME_SQL)
				.append(FROM).append(TWOPLAYERMATCH_SQL).append(M).append(COMMA)
				.append(TWOPLAYERTOURNAMENT_SQL).append(T).append(COMMA).append(
						GAME_SQL).append(G).append(COMMA).append(NETWORK_SQL).append(N)
				.append(WHERE).append(G).append(DOT).append(NETWORKID_SQL).append(
						EQUAL).append(N).append(DOT).append(NETWORKID_SQL).append(
						AND).append(T).append(DOT).append(GAMEID_SQL).append(EQUAL)
				.append(G).append(DOT).append(GAMEID_SQL).append(AND).append(T)
				.append(DOT).append(TOURNAMENTID_SQL).append(EQUAL).append(M)
				.append(DOT).append(TOURNAMENTID_SQL).append(AND).append(M).append(
						DOT).append(MATCHID_SQL).append(EQUAL).append(matchId);

		List<Object> matchList = session.createSQLQuery(query.toString())
				.list();

		MatchDTO matchDTO = null;
		if (matchList.size() != G4GConstants.ZERO) {
			matchDTO = new MatchDTO();
			Object[] object = (Object[]) matchList.get(G4GConstants.ZERO);
			matchDTO.setScheduleDateTime(object[G4GConstants.ZERO] + NONE);
			matchDTO.setGameName(object[G4GConstants.ONE_NUMBER] + NONE);
			matchDTO.setGameImageSrc(object[G4GConstants.TWO_NUMBER] + NONE);
			matchDTO.setGameId(Integer
					.parseInt(object[G4GConstants.THREE_NUMBER] + NONE));
			matchDTO.setConsole(object[4] + NONE);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(SET_MATCH_DTO).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		session.flush();
		HibernateUtil.closeSession();
		return matchDTO;
	}

	/**
	 *
	 * @see com.g4g.services.TwoPlayerMatchService#getDtoForChallenge(int,
	 *      javax.servlet.http.HttpServletRequest) returns dto for player
	 *      specific to tournament level also checks its eligibility
	 */
	public TwoplayermatchDTO getDtoForChallenge(int tournamentId,
			HttpServletRequest request) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).append(COMMA).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		int level = G4GConstants.ONE_NUMBER;
		TwoplayermatchDTO dto = null;
		// check there is any empty slot for level one
		if (i.checkLevelAvailibility(tournamentId, level)) {
			// check player joined this level or not
			if (!i.checkPlayerJoinedThisLevel(tournamentId, level, DataUtil
					.getUserDTO(request).getPlayerDTO().getId())) {
				// get the available list
				List<TwoplayermatchDTO> matchDTO = i.getAvailableDTO(
						tournamentId, level);
				Random random = new Random();
				dto = matchDTO.get(random.nextInt(matchDTO.size()));
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(GIVES_RANDOM_MATCHDTO).toString(), Level.INFO);
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
	 * @see com.g4g.services.TwoPlayerMatchService#checkLevelAvailibility(int,
	 *      int)
	 */
	public boolean checkLevelAvailibility(int tournamentid, int level)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).append(COMMA).append(LEVEL)
						.toString(), Level.INFO);
		List<TwoplayermatchDTO> availblelist = null;
		if (level == G4GConstants.ONE_NUMBER) {
			availblelist = i.getAvailableDTO(tournamentid, level);
			if (availblelist.size() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(LEVELS_AVAILABLE).toString(),
						Level.INFO);

				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(ENDED).toString(), Level.INFO);
				return true;
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(LEVELS_NOT_AVAILABLE).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return false;
	}

	/**
	 * @param tournamentId
	 * @param level
	 * @return boolean
	 */
	public boolean levelAvailable(int tournamentId, int level)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).append(COMMA).append(LEVEL)
						.toString(), Level.INFO);
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(LEVEL, level);
		searchCriteria.setAttribute(TOURNAMENT_ID, tournamentId);
		if (i.getList(searchCriteria).size() > G4GConstants.ZERO) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(ENDED).toString(), Level.INFO);
			return true;
		} else {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(ENDED).toString(), Level.INFO);
			return false;
		}
	}

	/**
	 * @param tournamentid
	 * @param level
	 * @return List<TwoplayermatchDTO>
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayermatchDTO> getAvailableDTO(int tournamentid, int level)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).append(COMMA).append(LEVEL)
						.toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(TwoplayermatchDTO.class);
		crit.add(Restrictions.and(Restrictions.and(Restrictions
				.eq(TWO_PLAYER_TOURNAMENT, new TwoplayertournamentDTO(
						tournamentid)), Restrictions.eq(LEVEL, level)),
				Restrictions.or(Restrictions.isNull(PLAYERONEID), Restrictions
						.isNull(PLAYERTWOID))));
		List<TwoplayermatchDTO> availblelist = crit.list();
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(MATCH_AVAILABLE).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(AVAILABLE_MATCH_LIST_SIZE).append(
								availblelist.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return availblelist;
	}

	/**
	 * @param tournamentid
	 * @param level
	 * @param playerId
	 * @return boolean
	 */
	@SuppressWarnings(UNCHECKED)
	public boolean checkPlayerJoinedThisLevel(int tournamentid, int level,
			int playerId) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).append(COMMA).append(LEVEL)
						.append(COMMA).append(PLAYERID).toString(), Level.INFO);

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Criteria crit = session.createCriteria(TwoplayermatchDTO.class);
		crit.add(Restrictions.and(Restrictions.and(Restrictions
				.eq(TWO_PLAYER_TOURNAMENT, new TwoplayertournamentDTO(
						tournamentid)), Restrictions.eq(LEVEL, level)),
				Restrictions.or(Restrictions.eq(PLAYERONEID, new PlayerDTO(
						playerId)), Restrictions.eq(PLAYERTWOID, new PlayerDTO(
						playerId)))));
		List<TwoplayermatchDTO> availblelist = crit.list();
		transaction.commit();
		HibernateUtil.closeSession();
		if (availblelist.size() > G4GConstants.ZERO) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(PLAYER_JOINED_THE_LEVEL).toString(),
					Level.INFO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(ENDED).toString(), Level.INFO);
			return true;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_HAS_NOT_JOINED_THE_LEVEL).toString(),
				Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return false;
	}

	/**
	 * @param playerId
	 * @return Double
	 */
	public Double getPlayersTotalChallengeMoney(int playerId) {

		return null;
	}

	/**
	 * @param form
	 * @param dto
	 * @return List<TwoplayermatchDTO>
	 * @see com.g4g.services.TwoPlayerMatchService#checkPlayerChallengeAvailability(com.g4g.forms.DirectedChallengeForm,PlayerDTO)
	 */
	@SuppressWarnings( { DEPRECATION, UNCHECKED, JAVADOCREFERENCE })
	public List<TwoplayermatchDTO> checkPlayerChallengeAvailability(
			DirectedChallengeForm form, PlayerDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(DIRECTED_CHALLENGE_FORM).append(COMMA).append(
								DTO).toString(), Level.INFO);
		if (dto == null) {
			dto = form.getPlayerDTO();
		}
		long dd = form.getSchduledDate().getTime();
		Date previousdate = new Date(dd);
		Date nextdate = new Date(dd);
		previousdate
				.setHours(previousdate.getHours() - G4GConstants.ONE_NUMBER);
		nextdate.setHours(nextdate.getHours() + G4GConstants.ONE_NUMBER);
		String prev = DataUtil.getDate(previousdate, DATE_YYYY_MM_DD_HH_MM_SS);
		String after = DataUtil.getDate(nextdate, DATE_YYYY_MM_DD_HH_MM_SS);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		StringBuffer SQL_QUERY = new StringBuffer(SELECT).append(T).append(DOT)
				.append(ASTERISTIC).append(FROM).append(TWOPLAYERMATCH_SQL).append(
						T).append(COMMA).append(TWOPLAYERTOURNAMENT_SQL).append(
						TOUR_SQL).append(WHERE).append(T).append(DOT).append(
						TOURNAMENTID_SQL).append(EQUAL).append(TOUR_SQL).append(DOT)
				.append(TOURNAMENTID_SQL).append(AND);
		SQL_QUERY.append(T).append(DOT).append(GAMECOMPLETED_SQL).append(EQUAL)
				.append(GAME_STAUS_PENDING);
		SQL_QUERY.append(AND).append(T).append(DOT)
				.append(SCHEDULED_START_DATE).append(GREATER).append(QUOTE)
				.append(prev).append(QUOTE);
		SQL_QUERY.append(AND).append(T).append(DOT)
				.append(SCHEDULED_START_DATE).append(LESS).append(QUOTE)
				.append(after).append(QUOTE);
		SQL_QUERY.append(AND).append(T).append(DOT).append(PLAYER_ONE_ID)
				.append(EQUAL).append(dto.getId());
		SQL_QUERY.append(AND).append(TOUR_SQL).append(DOT).append(GAMEID_SQL).append(
				EQUAL).append(form.getGameDTO().getId());
		if (form.getOppPlayerDTO() != null
				&& form.getOppPlayerDTO().getId() > G4GConstants.ZERO) {
			SQL_QUERY.append(AND).append(T).append(DOT).append(PLAYERTWOID_SQL)
					.append(EQUAL).append(form.getOppPlayerDTO().getId());
		}

		Query q = session.createSQLQuery(SQL_QUERY.toString());
		List<TwoplayermatchDTO> availblelist = q.list();

		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(CHECK_PLAYER_CHALLENGE_AVAILABILITY).append(
								availblelist.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return availblelist;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.g4g.services.TwoPlayerMatchService#getUpcomingMatches()
	 */
	public List<TwoplayermatchDTO> getUpcomingMatches()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		StringBuffer query = new StringBuffer();
		query.append(SELECT).append(ASTERISTIC).append(FROM).append(
				TWOPLAYERMATCH_SQL).append(WHERE).append(SCHEDULEDSTARTDATE_SQL)
				.append(PLUS).append(INTERVAL_SQL).append(NEGETIVE_ONE_HOURS)
				.append(GREATER_EQUAL).append(SPACE).append(
						CURRENT_TIMESTAMP_AT_TIMEZONE).append(SPACE)
				.append(UTC).append(AND).append(SCHEDULEDSTARTDATE_SQL)
				.append(PLUS).append(SPACE).append(INTERVAL_SQL).append(SPACE)
				.append(NEGATIVE_EIGHTY_MINUTES).append(LESS_EQUAL).append(
						SPACE).append(CURRENT_TIMESTAMP_AT_TIMEZONE).append(
						SPACE).append(UTC).append(AND).append(GAMECOMPLETED_SQL)
				.append(EQUAL).append(G4GConstants.ZERO).append(AND).append(
						PLAYERONEID_SQL).append(IS).append(NOT).append(NULL)
				.append(AND).append(PLAYERTWOID_SQL).append(IS).append(NOT).append(
						NULL);

		Transaction transaction = session.beginTransaction();

		List<TwoplayermatchDTO> matchList = session.createSQLQuery(
				query.toString()).addEntity(TwoplayermatchDTO.class).list();

		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(UPCOMING_MATCH_LIST).append(matchList.size())
						.toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return matchList;
	}

	/* (non-Javadoc)
	 * @see com.g4g.services.TwoPlayerMatchService#getConsoleMatches(com.g4g.dto.PlayerDTO, int[])
	 */
	public List<TwoplayermatchDTO> getConsoleMatches(PlayerDTO playerDTO, int... networkid) throws HibernateException {

		return null;
	}
}
