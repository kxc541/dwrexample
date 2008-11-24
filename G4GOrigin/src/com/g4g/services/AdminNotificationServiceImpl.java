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

import static com.g4g.utils.G4GConstants.ADMIN_NOTIFICATION_DTO;

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

import com.g4g.dto.AdminnotificationDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class to implement service for AdminNotification table.
 * 
 * @author Pratik
 */
public class AdminNotificationServiceImpl implements AdminNotificationService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see com.g4g.services.AdminNotificationService#add(com.g4g.dto.AdminnotificationDTO)
	 */
	public AdminnotificationDTO add(AdminnotificationDTO dto)
			throws HibernateException {
		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.ADMIN_NOTIFICATION_DTO).toString(),
						Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_ADDED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_NOT_ADDED).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage())
							.toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.AdminNotificationService#delete(com.g4g.dto.AdminnotificationDTO)
	 */
	public void delete(AdminnotificationDTO dto) throws HibernateException {

		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.ADMIN_NOTIFICATION_DTO).toString(),
						Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									G4GConstants.CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(
									G4GConstants.RECORD_DELETED).toString(),
							Level.INFO);
		} catch (HibernateException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_NOT_DELETED).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage())
							.toString(), Level.ERROR);
			throw e;
		}
	}

	/**
	 * @see com.g4g.services.AdminNotificationService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<AdminnotificationDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List list = null;
		try {
			Query query = session.createQuery(new StringBuffer(
					SQLConstants.FROM).append(ADMIN_NOTIFICATION_DTO)
					.toString());
			Transaction transaction = session.beginTransaction();
			list = query.list();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
					Level.ERROR);
			throw new HibernateException(e);
		} finally {
			HibernateUtil.closeSession();
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES

						).append(G4GConstants.ADMINLIST_SIZE).append(
								list.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.AdminNotificationService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<AdminnotificationDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName())
								.append(G4GConstants.COLON_WITH_SPACES)
								.append(G4GConstants.CALLINGMETHOD)
								.append(DataUtil.getCallingMethod())
								.append(G4GConstants.CURRENTMETHOD)
								.append(DataUtil.getCurrentMethod())
								.append(G4GConstants.DASHES)
								.append(G4GConstants.STARTED)
								.append(G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS)
								.append(
										G4GConstants.ADMIN_NOTIFICATION_LIST_SEARCH_CRITERIA)
								.toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(AdminnotificationDTO.class);
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
		}
		Transaction transaction = session.beginTransaction();
		List<AdminnotificationDTO> list = criteria.list();
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.ADMINLIST_SIZE)
						.append(list.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.AdminNotificationService#update(com.g4g.dto.AdminnotificationDTO)
	 */
	public AdminnotificationDTO update(AdminnotificationDTO dto)
			throws HibernateException {
		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.ADMIN_NOTIFICATION_DTO).toString(),
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
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(G4GConstants.STARTED)
							.append(G4GConstants.DASHES).append(
									G4GConstants.RECORD_UPDATED).toString(),
					Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil
					.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									G4GConstants.CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(
									G4GConstants.STARTED).append(
									G4GConstants.DASHES).append(
									G4GConstants.RECORD_NOT_UPDATED).toString(),
							Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage())
							.toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return dto;
	}
}
