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

import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;

/**
 * Class to implement service for Avatars table.
 * 
 * @author Pratik
 */
public class AvatarServiceImpl implements AvatarsService {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see com.g4g.services.AvatarsService#add(com.g4g.dto.AvatarsDTO)
	 */
	public AvatarsDTO add(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.AVATARSDTO).toString(),
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
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.RECORD_ADDED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( G4GConstants.DASHES 
					).append( G4GConstants.RECORD_NOT_ADDED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.DASHES).toString()
							, Level.ERROR);

			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.AvatarsService#delete(com.g4g.dto.AvatarsDTO)
	 */
	public void delete(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.AVATARSDTO).toString(),
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
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.RECORD_DELETED).toString(), Level.INFO);
		} catch (HibernateException e) {
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
			.writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES 
							).append( G4GConstants.RECORD_NOT_DELETED).toString(), Level.ERROR);
	AuditUtil.getInstance().writeLog(
			AuditUtil.FILE_TYPE_HIBERNATE,
			new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
					).append( e.getMessage()).toString(), Level.ERROR);
			
			throw e;
		}

	}

	/**
	 * Function returns Avatar's detail.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the avatars
	 * @throws HibernateException
	 */
	public AvatarsDTO getAvatars(int id) throws HibernateException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		AvatarsDTO dto = (AvatarsDTO) session.get(AvatarsDTO.class, id);
		transaction.commit();
		HibernateUtil.closeSession();
		return dto;
	}

	/**
	 * @see com.g4g.services.AvatarsService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<AvatarsDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<AvatarsDTO> list = null;
		try{
			Query query = session.createQuery(new StringBuffer (G4GConstants.FROM
			).append( G4GConstants.BLANK ).append( G4GConstants.AVATARDTO).toString());
			Transaction transaction = session.beginTransaction();
			list = query.list();
			transaction.commit();
			session.flush();
			
		}catch(Exception e){
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( G4GConstants.DASHES 
					).append( e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( e.getMessage()).toString(), Level.ERROR);
		
		}finally{
			HibernateUtil.closeSession();
		}
		
        AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
				).append( G4GConstants.CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( G4GConstants.CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( G4GConstants.DASHES
				
				).append( G4GConstants.AVTARLIST_SIZE ).append(list.size()).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.AvatarsService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<AvatarsDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.AVTAR_LIST_SEARCH_CRITERIA).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(AvatarsDTO.class);
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
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append(G4GConstants.DASHES ).append( key ).append( G4GConstants.COLON_WITH_SPACES ).append( value).toString(), Level.INFO);
		}
		Transaction transaction = session.beginTransaction();
		List<AvatarsDTO> list = null;
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
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
				).append( G4GConstants.CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( G4GConstants.CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( G4GConstants.DASHES
				).append( G4GConstants.AVTARLIST_SIZE ).append(list.size()).toString() , Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.AvatarsService#update(com.g4g.dto.AvatarsDTO)
	 */
	public AvatarsDTO update(AvatarsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.AVATARSDTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName()).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
					).append( G4GConstants.STARTED ).append( G4GConstants.DASHES ).append( G4GConstants.RECORD_UPDATED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
			.writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName()
							).append( G4GConstants.COLON_WITH_SPACES
				).append( G4GConstants.CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( G4GConstants.CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
				).append( G4GConstants.STARTED ).append( G4GConstants.DASHES).append( G4GConstants.RECORD_NOT_UPDATED).toString(), Level.ERROR);
	AuditUtil.getInstance().writeLog(
			AuditUtil.FILE_TYPE_HIBERNATE,
			new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
					).append( e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}

}
