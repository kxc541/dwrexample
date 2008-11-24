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

import static com.g4g.utils.G4GConstants.ARCHIVED_MESSAGES_COUNT;
import static com.g4g.utils.G4GConstants.ARCHIVED_MESSAGES_LIST_SIZE;
import static com.g4g.utils.G4GConstants.COLON;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.COUNT_MESSAGED_ACCORDING_TO_CRITERIA;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GET_MESSAGE_OF_ID;
import static com.g4g.utils.G4GConstants.IS_ARCHIEVED_BY_RECIEVER;
import static com.g4g.utils.G4GConstants.IS_ARCHIEVED_BY_SENDER;
import static com.g4g.utils.G4GConstants.IS_DELETED_BY_RECIEVER;
import static com.g4g.utils.G4GConstants.IS_DELETED_BY_SENDER;
import static com.g4g.utils.G4GConstants.MESSAGE_LIST_SIZE;
import static com.g4g.utils.G4GConstants.MESSAGE_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.VAR1;
import static com.g4g.utils.G4GConstants.VAR2;
import static com.g4g.utils.G4GConstants.VAR3;
import static com.g4g.utils.G4GConstants.VAR4;
import static com.g4g.utils.G4GConstants.VAR5;
import static com.g4g.utils.G4GConstants.VAR6;
import static com.g4g.utils.G4GConstants.WHERE;
import static com.g4g.utils.SQLConstants.CLOSE_PAREN;
import static com.g4g.utils.SQLConstants.DOT;
import static com.g4g.utils.SQLConstants.MESSAGEDTO_SQL;
import static com.g4g.utils.SQLConstants.OPEN_PAREN;
import static com.g4g.utils.SQLConstants.OR;
import static com.g4g.utils.SQLConstants.M;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.g4g.dto.MessageDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class to implement service for message table.
 * 
 * @author Jigar Mistry
 */
public class MessageServiceImpl implements MessageService {
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * @see com.g4g.services.MessageService#add(com.g4g.dto.MessageDTO)
	 */
	public MessageDTO add(MessageDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
					).append( CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() 
					).append( DASHES ).append( RECORD_ADDED).toString(), Level.INFO);
		} catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
					).append( CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( DASHES 
					).append( RECORD_NOT_ADDED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.MessageService#delete(com.g4g.dto.MessageDTO)
	 */
	public void delete(MessageDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
					).append( CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() 
					).append( DASHES ).append( RECORD_DELETED).toString(), Level.INFO);
		} catch (HibernateException e) {
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
					).append( CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( DASHES 
					).append( RECORD_NOT_DELETED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
	}

	/**
	 * @see com.g4g.services.MessageService#getArchivedList(int)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<MessageDTO> getArchivedList(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		
		StringBuffer sbquery = new StringBuffer();
		sbquery.delete(G4GConstants.ZERO, sbquery.length());
		sbquery.append(SQLConstants.FROM).append(MESSAGEDTO_SQL).append(M).append(
				WHERE).append(OPEN_PAREN);
		sbquery.append(M).append(DOT).append(G4GConstants.PLAYERBYFROMPLAYERID).append(
				SQLConstants.EQUAL).append(COLON);
		sbquery.append(VAR1).append(SQLConstants.AND).append(M).append(DOT)
				.append(IS_ARCHIEVED_BY_SENDER);
		sbquery.append(SQLConstants.EQUAL).append(COLON).append(VAR2).append(
				SQLConstants.AND);
		sbquery.append(M).append(DOT).append(IS_DELETED_BY_SENDER).append(
				SQLConstants.EQUAL).append(COLON).append(VAR3);
		sbquery.append(CLOSE_PAREN).append(OR).append(OPEN_PAREN).append(M)
				.append(DOT).append(G4GConstants.PLAYERBYTOPLAYERID);
		sbquery.append(SQLConstants.EQUAL).append(COLON).append(VAR4).append(
				SQLConstants.AND).append(M).append(DOT).append(
				IS_ARCHIEVED_BY_RECIEVER);
		sbquery.append(SQLConstants.EQUAL).append(COLON).append(VAR5).append(
				SQLConstants.AND).append(M).append(DOT).append(
				IS_DELETED_BY_RECIEVER).append(SQLConstants.EQUAL);
		sbquery.append(COLON).append(VAR6).append(CLOSE_PAREN).append(SQLConstants.ORDERBY).append(M).append(DOT).append(G4GConstants.CREATED_DATE);
		
		Query q = session.createQuery(sbquery.toString());
		
		q.setEntity(G4GConstants.VAR1, new PlayerDTO(playerId));
		q.setBoolean(G4GConstants.VAR2, true);
		q.setBoolean(G4GConstants.VAR3, false);
		q.setEntity(G4GConstants.VAR4, new PlayerDTO(playerId));
		q.setBoolean(G4GConstants.VAR5, true);
		q.setBoolean(G4GConstants.VAR6, false);

		Transaction transaction = session.beginTransaction();
		List<MessageDTO> list = q.list();
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
				).append( CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( DASHES
				).append( ARCHIVED_MESSAGES_LIST_SIZE ).append(list.size()).toString() , Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.MessageService#getCount(SearchListCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public int getCount(SearchListCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( SEARCH_CRITERIA).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		// from foo Foo as foo where foo.name=:name and foo.size=:size
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(MessageDTO.class);

		Set<String> keys = searchCriteria.getAttributeNames();
		Iterator<String> it = keys.iterator();

		String key = null;
		Object[] value = null;

		while (it.hasNext()) {
			key = it.next();
			value = searchCriteria.getAttribute(key);
			if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.EQ)) {
				criteria.add(Restrictions.eq(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.GE)) {
				criteria.add(Restrictions.ge(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.GT)) {
				criteria.add(Restrictions.gt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ILIKE)) {
				criteria.add(Restrictions.ilike(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ISNOTNULL)) {
				criteria.add(Restrictions.isNotNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ISNULL)) {
				criteria.add(Restrictions.isNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LE)) {
				criteria.add(Restrictions.le(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LIKE)) {
				criteria.add(Restrictions.like(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LT)) {
				criteria.add(Restrictions.lt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.NE)) {
				criteria.add(Restrictions.ne(key, value[G4GConstants.ZERO]));
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( key
							).append( COLON ).append( value).toString(), Level.INFO);
		}

		Object obj = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		tx.commit();
		HibernateUtil.closeSession();
		String result = new StringBuffer (G4GConstants.NONE ).append( obj).toString();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( DASHES ).append( COUNT_MESSAGED_ACCORDING_TO_CRITERIA).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return Integer.parseInt(result);
	}

	/**
	 * @see com.g4g.services.MessageService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<MessageDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		
		StringBuffer q = new StringBuffer();
		List<MessageDTO> list = null;
		try{
		q.append(SQLConstants.FROM).append(MESSAGEDTO_SQL);
		Query query = session.createQuery(q.toString());

		
	
		
		Transaction transaction = session.beginTransaction();
		list = query.list();
		transaction.commit();
		session.flush();
		}catch(Exception e){
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
			).append( CALLINGMETHOD
			).append( DataUtil.getCallingMethod()
			).append( CURRENTMETHOD
			).append( DataUtil.getCurrentMethod() 
			).append( DASHES
			).append( e.getMessage()).toString() , Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
			).append( CALLINGMETHOD
			).append( DataUtil.getCallingMethod()
			).append( CURRENTMETHOD
			).append( DataUtil.getCurrentMethod() 
			).append( DASHES
			).append( e.getMessage()).toString() , Level.ERROR);
		}finally{
			HibernateUtil.closeSession();
		}
		
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
				).append( CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( DASHES
				).append( MESSAGE_LIST_SIZE ).append(list.size()).toString() , Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return list;
	}

	/**
	 * @see com.g4g.services.MessageService#getArchivedCount(int)
	 */
	public int getArchivedCount(int playerId) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		int count = G4GConstants.ZERO;
		UserDTO userDTO = new UserDTO();

		Session session = HibernateUtil.getSession();
		StringBuffer query = new StringBuffer();
		query.append(SQLConstants.SELECT).append(SQLConstants.COUNT)
				.append(OPEN_PAREN).append(SQLConstants.ASTERISTIC);
		query.append(CLOSE_PAREN).append(SQLConstants.FROM).append(
				SQLConstants.MESSAGE_SQL);
		query.append(WHERE).append(OPEN_PAREN).append(G4GConstants.FROM_PLAYER)
				.append(SQLConstants.EQUAL);
		query.append(playerId).append(SQLConstants.AND);
		query.append(IS_ARCHIEVED_BY_SENDER).append(SQLConstants.EQUAL).append(
				G4GConstants.TRUE).append(SQLConstants.AND);
		query.append(IS_DELETED_BY_SENDER).append(SQLConstants.EQUAL).append(
				G4GConstants.FALSE).append(CLOSE_PAREN).append(OR).append(
				OPEN_PAREN).append(G4GConstants.TO_PLAYER).append(
				SQLConstants.EQUAL);
		query.append(playerId);
		query.append(SQLConstants.AND).append(
				IS_ARCHIEVED_BY_RECIEVER).append(SQLConstants.EQUAL);
		query.append(true).append(SQLConstants.AND).append(
				IS_DELETED_BY_RECIEVER).append(SQLConstants.EQUAL).append(
				G4GConstants.FALSE).append(CLOSE_PAREN);
		try {
		
			Query q = session.createSQLQuery(query.toString());
		count = Integer.parseInt(q.uniqueResult() + G4GConstants.NONE);
		
		}catch (HibernateException e) {
			HibernateUtil.closeSession();
			throw e;
		}
		
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( DASHES ).append( ARCHIVED_MESSAGES_COUNT).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return count;
	}

	/**
	 * @see com.g4g.services.MessageService#getList(com.g4g.dto.SearchListCriteria)
	 */
	@SuppressWarnings( { UNCHECKED, UNCHECKED })
	public List<MessageDTO> getList(SearchListCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( SEARCH_CRITERIA).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(MessageDTO.class);
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
			if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.EQ)) {
				criteria.add(Restrictions.eq(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.GE)) {
				criteria.add(Restrictions.ge(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.GT)) {
				criteria.add(Restrictions.gt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ILIKE)) {
				criteria.add(Restrictions.ilike(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ISNOTNULL)) {
				criteria.add(Restrictions.isNotNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.ISNULL)) {
				criteria.add(Restrictions.isNull(key));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LE)) {
				criteria.add(Restrictions.le(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LIKE)) {
				criteria.add(Restrictions.like(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.LT)) {
				criteria.add(Restrictions.lt(key, value[G4GConstants.ZERO]));
			} else if (((String) value[G4GConstants.ONE_NUMBER]).equals(SearchListCriteria.NE)) {
				criteria.add(Restrictions.ne(key, value[G4GConstants.ZERO]));
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( key
							).append( COLON ).append( value).toString(), Level.INFO);
		}

		Transaction transaction = session.beginTransaction();
		List<MessageDTO> messageList = new ArrayList<MessageDTO>();
		
		try {
			messageList = criteria.list();
			transaction.commit();
			}catch(HibernateException e) {
				for (String keyexception: keys) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer (this.getClass().getName() ).append( keyexception).toString(), Level.ERROR);		
				}
				throw e;
			}finally {
			session.flush();
			HibernateUtil.closeSession();
			}
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
					).append( CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() 
					).append( DASHES
					).append( MESSAGE_LIST_SIZE_CRITERIA ).append(messageList.size()).toString() , Level.INFO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( ENDED).toString(), Level.INFO);

		return messageList;
	}

	/**
	 * @see com.g4g.services.MessageService#getMessage(java.lang.String)
	 */
	@SuppressWarnings(UNCHECKED)
	public MessageDTO getMessage(String id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).append( G4GConstants.PARAMETERS ).append( G4GConstants.COLON_WITH_SPACES ).append( G4GConstants.ID).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		StringBuffer queryString = new StringBuffer();
		
		queryString.delete(G4GConstants.ZERO, queryString.length());

		queryString.append(SQLConstants.FROM).append(MESSAGEDTO_SQL).append(
				SQLConstants.WHERE).append(SQLConstants.RANDOMID_SQL).append(
				SQLConstants.EQUAL).append(SQLConstants.QUOTE)
				.append(id.trim()).append(SQLConstants.QUOTE);
		/* queryString.append(" from MessageDTO where randomid='").append(
				id.trim()).append("'"); */

		
		Query query = session.createQuery(queryString.toString());
		Transaction transaction = session.beginTransaction();
		List<MessageDTO> list = query.list();
		MessageDTO dto = null;
		if (list != null && list.size() > G4GConstants.ZERO) {
			dto = list.iterator().next();
		}
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( DASHES ).append( GET_MESSAGE_OF_ID ).append( id).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.services.MessageService#update(com.g4g.dto.MessageDTO)
	 */
	public MessageDTO update(MessageDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(dto);
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() 
					).append( G4GConstants.DASHES 
					).append( G4GConstants.RECORD_UPDATED).toString(), Level.INFO);
		}catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( G4GConstants.DASHES 
					).append( G4GConstants.RECORD_NOT_UPDATED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( e.getMessage()).toString(), Level.ERROR);
			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return dto;
	}
}