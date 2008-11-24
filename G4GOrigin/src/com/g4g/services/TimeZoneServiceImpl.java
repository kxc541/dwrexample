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

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
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
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TIMEZONEDTO;
import static com.g4g.utils.G4GConstants.TIME_ZONE_LIST_SIZE;
import static com.g4g.utils.G4GConstants.TIME_ZONE_LIST_SIZE_CRITERIA;

import java.util.ArrayList;
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
import com.g4g.dto.TimeZoneDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class contains method implementation related to timezone.
 *
 * @author ankur
 */
public class TimeZoneServiceImpl implements TimeZoneService {
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see TimeZoneService#add(TimeZoneDTO)
	 */
	public TimeZoneDTO add(TimeZoneDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(dto);
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

	public void delete(TimeZoneDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
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

	@SuppressWarnings(UNCHECKED)
	public List<TimeZoneDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<TimeZoneDTO> list = null;
		try {
			Query query = session.createQuery(new StringBuffer(
					SQLConstants.FROM).append(SQLConstants.TIMEZONEDTO_SQL)
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
						.append(TIME_ZONE_LIST_SIZE).append(list.size())
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

	@SuppressWarnings(UNCHECKED)
	public List<TimeZoneDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(TimeZoneDTO.class);
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
		List<TimeZoneDTO> list = new ArrayList<TimeZoneDTO>();

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
						.append(TIME_ZONE_LIST_SIZE_CRITERIA).append(
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

	public TimeZoneDTO update(TimeZoneDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
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

	public TimeZoneDTO getTimeZone(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(PARAMETERS)
						.append(TIMEZONEDTO).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		TimeZoneDTO dto = (TimeZoneDTO) session.get(TimeZoneDTO.class, id);
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
		return dto;
	}

}
