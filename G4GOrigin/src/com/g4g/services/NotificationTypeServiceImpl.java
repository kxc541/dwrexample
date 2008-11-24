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

import com.g4g.dto.AdminDTO;
import com.g4g.dto.NotificationtypeDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class to implement service for admin table.
 * 
 * @author Pratik
 */
public class NotificationTypeServiceImpl implements NotificationTypeService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	private Session session;
	private List<AdminDTO> list;

	/**
	 * @param dto
	 * @return NotificationtypeDTO
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#add(com.g4g.dto.AdminDTO)
	 */
	public NotificationtypeDTO add(NotificationtypeDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.NOTIFICATIONTYPEDTO).toString(),
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
							G4GConstants.DASHES).append(G4GConstants.DASHES)
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
	 * @param dto
	 * @throws HibernateException
	 */
	public void delete(NotificationtypeDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.NOTIFICATIONTYPEDTO).toString(),
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
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<NotificationtypeDTO> getList() throws HibernateException {

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
					SQLConstants.FROM).append(SQLConstants.NOTIFICATIONTYPEDTO_SQL)
					.toString());
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

						).append(G4GConstants.NOTIFICATIONT_LIST_SIZE).append(
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
	 * @param searchCriteria
	 * @return list
	 * @throws HibernateException
	 * @see com.g4g.services.AdminService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<NotificationtypeDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {

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
		Criteria criteria = session.createCriteria(NotificationtypeDTO.class);
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
		List<NotificationtypeDTO> list = criteria.list();
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
								G4GConstants.DASHES

						).append(G4GConstants.NOTIFICATIONT_LIST_SIZE).append(
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
	 * @param dto
	 *            NotificationtypeDTO
	 * @return dto
	 * @throws HibernateException
	 */
	public NotificationtypeDTO update(NotificationtypeDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS).append(
								G4GConstants.NOTIFICATIONTYPEDTO).toString(),
				Level.INFO);
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
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_UPDATE).toString(), Level.INFO);
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
							G4GConstants.RECORD_NOT_UPDATE).toString(),
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
	 * @see com.g4g.services.NotificationTypeService#getNotificationType(int)
	 */
	public NotificationtypeDTO getNotificationType(int id)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.PARAMETERS)
						.append(G4GConstants.ID).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		NotificationtypeDTO dto = (NotificationtypeDTO) session.get(
				NotificationtypeDTO.class, id);
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.NOTIFICATIONT_LIST_SIZE).append(
								list.size()).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return dto;
	}

}
