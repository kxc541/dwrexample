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

import static com.g4g.utils.G4GConstants.COLON;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAME_CATEGORY_LIST_SIZE;
import static com.g4g.utils.G4GConstants.GAMEID_DB;
import static com.g4g.utils.G4GConstants.GET_GAME_OF_ID;
import static com.g4g.utils.G4GConstants.MATCH_COMMENTS_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.STARTED;

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

import com.g4g.dto.GamecategoryDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * For future use.
 * Class to implement service for gamecategory table.
 * 
 * @author Punam
 */
public class GameCategoryServiceImpl implements GameCategoryService {

	/**
	 * @see com.g4g.services.GameCategoryService#add(com.g4g.dto.GamecategoryDTO)
	 */
	public GamecategoryDTO add(GamecategoryDTO dto) throws Exception{
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
	 * @see com.g4g.services.GameCategoryService#delete(com.g4g.dto.GamecategoryDTO)
	 */
	public void delete(GamecategoryDTO dto) throws Exception{
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
	 * @see com.g4g.services.GameCategoryService#getGame(int)
	 */
	public GamecategoryDTO getGame(int id) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED ).append( DASHES ).append( PARAMETERS ).append( GAMEID_DB).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(new StringBuffer (SQLConstants.FROM).append(G4GConstants.GAMECATEGORYDTO).append(SQLConstants.WHERE).append(G4GConstants.IDEQUALS
				).append( id).toString());
		Transaction transaction = session.beginTransaction();
		List<GamecategoryDTO> list = query.list();
		GamecategoryDTO dto = list.get(G4GConstants.ZERO);
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( DASHES ).append( GET_GAME_OF_ID).toString(), Level.INFO);
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
	 * @see com.g4g.services.GameCategoryService#getList()
	 */
	public List getList() {
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
		Query query = session.createQuery(new StringBuffer(SQLConstants.FROM).append(G4GConstants.GAMECATEGORYDTO).toString());
		Transaction transaction = session.beginTransaction();
		List<GamecategoryDTO> list = query.list();
		transaction.commit();
		HibernateUtil.closeSession();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
				).append( CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( DASHES
				).append( GAME_CATEGORY_LIST_SIZE ).append(list.size()).toString() , Level.INFO);
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
	 * @see com.g4g.services.GameCategoryService#getList(com.g4g.dto.SearchCriteria)
	 */
	public List<GamecategoryDTO> getList(SearchCriteria searchCriteria) {
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
		Criteria criteria = session.createCriteria(GamecategoryDTO.class);
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
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( key
							).append( COLON ).append( value).toString(), Level.INFO);
		}
		Transaction transaction = session.beginTransaction();
		List<GamecategoryDTO> list = new ArrayList<GamecategoryDTO>();
		
		try {
			list = criteria.list();
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
					).append( MATCH_COMMENTS_LIST_SIZE_CRITERIA ).append(list.size()).toString() , Level.INFO);
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
	 * @see com.g4g.services.GameCategoryService#update(com.g4g.dto.GamecategoryDTO)
	 */
	public GamecategoryDTO update(GamecategoryDTO dto) throws Exception{
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
