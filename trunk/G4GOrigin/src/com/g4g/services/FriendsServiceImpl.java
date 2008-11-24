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

import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.FriendsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.SQLConstants;
import com.g4g.utils.HibernateUtil;

/**
 * Class to implement service for friends table.
 * 
 * @author Jigar Mistry
 */
public class FriendsServiceImpl implements FriendsService {

	/**
	 * @see com.g4g.services.FriendsService#add(com.g4g.dto.FriendsDTO)
	 */
	public FriendsDTO add(FriendsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FRIENDSDTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
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
		//}
		
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
	 * @see com.g4g.services.FriendsService#delete(com.g4g.dto.FriendsDTO)
	 */
	public void delete(FriendsDTO dto) throws HibernateException{
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FRIENDSDTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
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
		}catch (HibernateException e) {
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
	 * @see com.g4g.services.FriendsService#getList()
	 */
	public List getList()throws HibernateException {
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List list = null;
		try {
		Query query = session.createQuery(new StringBuffer(SQLConstants.FROM).append(G4GConstants.FRIENDSDTO).toString());
		Transaction transaction = session.beginTransaction();
		 list = query.list();
		transaction.commit();
		}catch (Exception e) {
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
	 * @see com.g4g.services.FriendsService#getList(com.g4g.dto.SearchListCriteria)
	 */
	public List getList(SearchListCriteria searchCriteria) throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(FriendsDTO.class);
		String orderBY = searchCriteria.getOrderBy();
		if (orderBY != null) {
			if (searchCriteria.isAsc()) {
				criteria.addOrder(Order.asc(orderBY));
			} else {
				criteria.addOrder(Order.desc(orderBY));
			}
		}
		Set keys = searchCriteria.getAttributeNames();
		Iterator it = keys.iterator();
		String key = null;
		Object[] value = null;
		while (it.hasNext()) {
			key = (String) it.next();
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
		}
		Transaction transaction = session.beginTransaction();
		List list = criteria.list();
		transaction.commit();
		session.flush();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
				).append( G4GConstants.CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( G4GConstants.CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( G4GConstants.DASHES
				
				).append( G4GConstants.FRIENDS_LIST_SIZE  ).append(list.size()).toString(), Level.INFO);
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
	 * @see com.g4g.services.FriendsService#update(com.g4g.dto.FriendsDTO)
	 */
	public FriendsDTO update(FriendsDTO dto) throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FRIENDSDTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.update(dto);
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
							).append( G4GConstants.RECORD_UPDATE).toString(), Level.INFO);
		}catch (HibernateException e) {
			transaction.rollback();
			HibernateUtil.closeSession();
			AuditUtil.getInstance()
			.writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD
					).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES 
							).append( G4GConstants.RECORD_NOT_UPDATE).toString(), Level.ERROR);
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

	/**
	 * @see com.g4g.services.FriendsService#getFriends(int)
	 */
	public List<PlayerDTO> getFriends(int playerId)throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.PLAYERID).toString(),
				Level.INFO);
		List<PlayerDTO> friendsList = new ArrayList<PlayerDTO>();
		Session session = HibernateUtil.getSession();
		List<Object> list = new ArrayList<Object>();
		try{
		String queryString =new StringBuffer (SQLConstants.SELECT).append(G4GConstants.PLAYERID1ASPLAYERID).append(SQLConstants.FROM).append(SQLConstants.FRIENDS_SQL).append(SQLConstants.WHERE).append(G4GConstants.PLAYERIDTWOEQUALS).append(playerId).append(SQLConstants.AND).append(G4GConstants.PLAYER2_ACCEPTED).append(SQLConstants.IS).append(SQLConstants.NOT).append(SQLConstants.NULL).append(SQLConstants.UNION).append(SQLConstants.SELECT).append(G4GConstants.PLAYER_ID2).append(SQLConstants.FROM).append(G4GConstants.FRIENDS).append(SQLConstants.WHERE).append(G4GConstants.PLAYERIDONEEQUALS).append(playerId).append(SQLConstants.AND).append(G4GConstants.PLAYER2_ACCEPTED).append(SQLConstants.IS).append(SQLConstants.NOT).append(SQLConstants.NULL).toString();
		list = session.createSQLQuery(queryString).list();
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
		
		for(int index = G4GConstants.ZERO; index < list.size(); index++){
			int id = Integer.parseInt(list.get(index).toString());
			PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(id);
			friendsList.add(playerDTO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.FRIENDS_LIST_SIZE ).append( friendsList.size()
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return friendsList;
	}
}
