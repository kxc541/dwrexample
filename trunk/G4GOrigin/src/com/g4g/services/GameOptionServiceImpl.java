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
import static com.g4g.utils.G4GConstants.GAME_OPTIONS_LIST_SIZE;
import static com.g4g.utils.G4GConstants.GAME_OPTIONS_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.GAME_OPTION_DTO;
import static com.g4g.utils.G4GConstants.GAMEID_DB;
import static com.g4g.utils.G4GConstants.GET_GAME_OF_ID;
import static com.g4g.utils.G4GConstants.PARAMETERS;
import static com.g4g.utils.G4GConstants.RECORD_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_DELETED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_ADDED;
import static com.g4g.utils.G4GConstants.RECORD_NOT_DELETED;
import static com.g4g.utils.G4GConstants.STARTED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GameIdConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;

/**
 * Class to implement service for gameoptions table.
 * 
 * @author Ankur
 * 
 */
public class GameOptionServiceImpl implements GameOptionService {
	private static final String UNCHECKED = "unchecked"; 

	/**
	 * @see com.g4g.services.GameOptionService#add(com.g4g.dto.GameoptionsDTO)
	 */
	public GameoptionsDTO add(GameoptionsDTO dto) throws HibernateException {
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
	 * @see com.g4g.services.GameOptionService#delete(com.g4g.dto.GameoptionsDTO)
	 */
	public void delete(GameoptionsDTO dto) throws HibernateException {
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
	 * @see com.g4g.services.GameOptionService#getGame(com.g4g.dto.GameoptionsDTO)
	 */
	public GameoptionsDTO getGame(GameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED ).append( DASHES ).append( PARAMETERS ).append( GAME_OPTION_DTO).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		dto = (GameoptionsDTO) session.get(GameoptionsDTO.class, dto);
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
	 * @see com.g4g.services.GameOptionService#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<GameoptionsDTO> getList() throws HibernateException {
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
		Query query = session.createQuery (new StringBuffer (G4GConstants.FROM
				).append( G4GConstants.BLANK ).append( G4GConstants.GAME_OPTION_DTO).toString());
		Transaction transaction = session.beginTransaction();
		List<GameoptionsDTO> list = query.list();
		transaction.commit();
		HibernateUtil.closeSession();	
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
				).append( CALLINGMETHOD
				).append( DataUtil.getCallingMethod()
				).append( CURRENTMETHOD
				).append( DataUtil.getCurrentMethod() 
				).append( DASHES
				).append( GAME_OPTIONS_LIST_SIZE ).append(list.size()).toString() , Level.INFO);
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
	 * @see com.g4g.services.GameOptionService#getList(com.g4g.dto.SearchCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public List<GameoptionsDTO> getList(SearchCriteria searchCriteria)
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
		Criteria criteria = session.createCriteria(GameoptionsDTO.class);
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
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( key
							).append( COLON ).append( value).toString(), Level.INFO);
		}
		Transaction transaction = session.beginTransaction();
		List<GameoptionsDTO> list = new ArrayList<GameoptionsDTO>();
		
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
					).append( GAME_OPTIONS_LIST_SIZE_CRITERIA ).append(list.size()).toString() , Level.INFO);
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
	 * @see com.g4g.services.GameOptionService#update(com.g4g.dto.GameoptionsDTO)
	 */
	public GameoptionsDTO update(GameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.ADMINDTO).toString(),
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

	/**
	 * @param request
	 * @param gameid
	 * @see com.g4g.services.GameOptionService#setgameOptionListInRequest(HttpServletRequest
	 *      request , int gameid)
	 */
	public void setgameOptionListInRequest(HttpServletRequest request,
			int gameid) throws HibernateException {
		List<GameoptionsDTO> list = new ArrayList<GameoptionsDTO>();
		SearchCriteria searchCriteria = new SearchCriteria();
		int i = G4GConstants.ONE_NUMBER;
		do {
			searchCriteria.removeAllAttribute();
			searchCriteria.setAsc(true);
			searchCriteria.setOrderBy(SQLConstants.OPTIONSEQUENCEID);
			searchCriteria.setOrderBy(SQLConstants.VALUESEQUENCEID);
			searchCriteria.setAttribute(G4GConstants.GAMEID_DB, gameid);
			searchCriteria.setAttribute(SQLConstants.OPTIONSEQUENCEID, i);
			list = GameOptionServiceDelegator.getList(searchCriteria);
			request.getSession().setAttribute(G4GConstants.LIST + String.valueOf(i), list);
			i++;
		} while (list.size() != G4GConstants.ZERO);
		request.setAttribute(G4GConstants.TOTAL_LIST, (i - G4GConstants.ONE_NUMBER));

	}

	/**
	 * Return a HashMap of all game option with key as list).append(i where i = element
	 * no of Map
	 * 
	 * @see com.g4g.services.GameOptionService#getGameOptionListOfGame(int)
	 */

	public HashMap<String, List<GameoptionsDTO>> getGameOptionListOfGame(
			int gameid) throws HibernateException {
		HashMap<String, List<GameoptionsDTO>> request = new HashMap<String, List<GameoptionsDTO>>();
		List<GameoptionsDTO> list = new ArrayList<GameoptionsDTO>();
		SearchCriteria searchCriteria = new SearchCriteria();
		int i = G4GConstants.ONE_NUMBER;
		do {
			searchCriteria.removeAllAttribute();
			searchCriteria.setAsc(true);
			searchCriteria.setOrderBy(SQLConstants.OPTIONSEQUENCEID);
			searchCriteria.setOrderBy(SQLConstants.VALUESEQUENCEID);
			searchCriteria.setAttribute(G4GConstants.GAMEID_DB, gameid);
			// here G4GConstants.ID is option id
			searchCriteria.setAttribute(G4GConstants.ID, i);
			list = GameOptionServiceDelegator.getList(searchCriteria);
			Iterator<GameoptionsDTO> it = list.iterator();

			if (list != null && list.size() > G4GConstants.ZERO) {
				request.put(it.next().getOption(), list);
			}
			i++;
		} while (list.size() != G4GConstants.ZERO);
		return request;
	}

	/**
	 * @see com.g4g.services.GameOptionService#getGameOptions(int, int, com.g4g.dto.SearchCriteria)
	 */
	public List<GameoptionsDTO> getGameOptions(int gameid, int optionsequenceid, SearchCriteria searchCriteria)throws HibernateException {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES).append( CALLINGMETHOD).append( DataUtil.getCallingMethod()).append( CURRENTMETHOD).append( DataUtil.getCurrentMethod() ).append( DASHES).append( STARTED).toString(),Level.INFO);
					StringBuffer query = new StringBuffer(GameIdConstants.gameoptionquery)
					.append(gameid).append(G4GConstants.BLANK);
					Set<String> keys = searchCriteria.getAttributeNames();
					Iterator<String> it = keys.iterator();
					String key = null;
					Object value = null;
					if(keys.size()>0) {
						query.append(GameIdConstants.gameoptionqueryapp);
					}
					while (it.hasNext()) {
						key = it.next();
						value = searchCriteria.getAttribute(key);
						query.append(key).append(SQLConstants.EQUAL).append(value).append(G4GConstants.BLANK);
					}
					if(keys.size()>0) {
						query.append(SQLConstants.CLOSE_PAREN);
					}
					query.append(GameIdConstants.middelquery).append((optionsequenceid+G4GConstants.ONE_NUMBER)).
					append(GameIdConstants.gameoptionquerylast);
					Session session = HibernateUtil.getSession();
					Query q = session.createQuery(query.toString());
					Transaction transaction = session.beginTransaction();
					List<GameoptionsDTO> list =null;
					try {		
					list = q.list();
					transaction.commit();
					}catch(HibernateException e) {
						throw e;
					}finally {
					session.flush();
					HibernateUtil.closeSession();
					}
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD).append( DataUtil.getCallingMethod()).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES).append( GAME_OPTIONS_LIST_SIZE_CRITERIA ).append(list.size()).toString() , Level.INFO);
				return list;
	}

}