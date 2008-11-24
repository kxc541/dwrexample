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

import static com.g4g.utils.G4GConstants.ALL_RECORDS_DELETED;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_UPDATED;
import static com.g4g.utils.G4GConstants.RECORD_UPDATED;
import static com.g4g.utils.G4GConstants.SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TWOPLAYERTOURNAMENTGAMEOPTIONSDTO;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT_GAME_OPTIONS_LIST_SIZE;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT_GAME_OPTIONS_LIST_SIZE_CRITERIA;

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

import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class contains method implementation related to
 * twoplayertournamentgameoption.
 * 
 * @author ankur
 */
public class TwoPlayerTournamentGameOptionServiceImpl implements
		TwoPlayerTournamentGameOptionService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#add(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public TwoplayertournamentgameoptionsDTO add(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).toString(),
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
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#delete(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public void delete(TwoplayertournamentgameoptionsDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).toString(),
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
	}

	/**
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayertournamentgameoptionsDTO> getList()
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
		List<TwoplayertournamentgameoptionsDTO> list = null;
		try {
			Query query = session.createQuery(new StringBuffer(
					SQLConstants.FROM).append(
					SQLConstants.TWOPLAYERTOURNAMENTGAMEOPTIONSDTO_SQL).toString());
			Transaction transaction = session.beginTransaction();
			list = query.list();
			transaction.commit();
			session.flush();
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
						.append(TWO_PLAYER_TOURNAMENT_GAME_OPTIONS_LIST_SIZE)
						.append(list.size()).toString(), Level.INFO);
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
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<TwoplayertournamentgameoptionsDTO> getList(
			SearchCriteria searchCriteria) throws HibernateException {
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
		Criteria criteria = session
				.createCriteria(TwoplayertournamentgameoptionsDTO.class);
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
		List<TwoplayertournamentgameoptionsDTO> list = criteria.list();
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName())
								.append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD)
								.append(DataUtil.getCallingMethod())
								.append(CURRENTMETHOD)
								.append(DataUtil.getCurrentMethod())
								.append(DASHES)
								.append(
										TWO_PLAYER_TOURNAMENT_GAME_OPTIONS_LIST_SIZE_CRITERIA)
								.append(list.size()).toString(), Level.INFO);
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
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#update(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public TwoplayertournamentgameoptionsDTO update(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).toString(),
				Level.INFO);
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
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#get(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public TwoplayertournamentgameoptionsDTO get(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		dto = (TwoplayertournamentgameoptionsDTO) session.get(
				TwoplayertournamentgameoptionsDTO.class, dto.getId());
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

	/**
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#deleteAll(int)
	 */
	public void deleteAll(int tournamentid) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TOURNAMENT_ID).toString(), Level.INFO);
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(TOURNAMENT_ID, new TwoplayertournamentDTO(
				tournamentid));
		List<TwoplayertournamentgameoptionsDTO> temp = getList(searchCriteria);
		Iterator<TwoplayertournamentgameoptionsDTO> it = temp.iterator();
		while (it.hasNext()) {
			try {
				delete(it.next());
			} catch (HibernateException e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
								.append(RECORD_NOT_DELETED).toString(),
						Level.ERROR);
				AuditUtil.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(COLON_WITH_SPACES).append(CALLINGMETHOD)
										.append(DataUtil.getCallingMethod())
										.append(CURRENTMETHOD).append(
												DataUtil.getCurrentMethod())
										.append(DASHES).append(e.getMessage())
										.toString(), Level.ERROR);
				throw e;
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_HIBERNATE,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ALL_RECORDS_DELETED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
	}

}
