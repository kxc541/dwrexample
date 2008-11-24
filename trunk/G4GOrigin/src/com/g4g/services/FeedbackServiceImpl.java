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
import static com.g4g.utils.SQLConstants.*;
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

import com.g4g.dto.FeedbackreputationDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;
import com.g4g.utils.SQLConstants;
/**
 * Class to implement service for Feedbackreputation table.
 *
 * @author punam
 */
public class FeedbackServiceImpl implements FeedbackService {

	/**
	 * @see com.g4g.services.FeedbackService#add(com.g4g.dto.FeedbackreputationDTO)
	 */
	public FeedbackreputationDTO add(FeedbackreputationDTO dto)throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FEEDBACKREPUTATIONDTO).toString(),
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
			e.getCause();
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
		// addReputationInPlayer(dto.getPlayerid());
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
	 * Adds the reputation in player.
	 *
	 * @param playerId the player id
	 * @throws HibernateException
	 */
	public void addReputationInPlayer(int playerId)throws HibernateException{
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
		Session session = HibernateUtil.getSession();
		PlayerDTO playerDTO = new PlayerDTO();
		int reputation = G4GConstants.ZERO;
		int totalReputation = G4GConstants.ZERO;
		Query query = session
				.createQuery ((new StringBuffer(SQLConstants.FROM).append(G4GConstants.FEEDBACKREPUTATIONDTO).append(SQLConstants.WHERE).append(G4GConstants.PLAYERIDEQUALS)
						).append( playerId ).append(G4GConstants.ISRATED).append( true).toString());
		Transaction transaction = session.beginTransaction();
		List<FeedbackreputationDTO> list = query.list();
		for (int index = G4GConstants.ZERO; index < list.size(); index++) {
			FeedbackreputationDTO feedbackreputationDTO = list
					.get(index);
			totalReputation += feedbackreputationDTO.getReputation();
		}
		reputation = totalReputation / list.size();
		playerDTO.setId(playerId);
		playerDTO.setReputation(reputation);
		try{
			session.saveOrUpdate(playerDTO);
			transaction.commit();
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
	}

	/**
	 * @see com.g4g.services.FeedbackService#delete(com.g4g.dto.FeedbackreputationDTO)
	 */
	public void delete(FeedbackreputationDTO dto)throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FEEDBACKREPUTATIONDTO).toString(),
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
	 * @see com.g4g.services.FeedbackService#getReputation(int, int)
	 */
	public FeedbackreputationDTO getReputation(SearchListCriteria searchCriteria) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<FeedbackreputationDTO> list = null ;

		try{
			Criteria criteria = session.createCriteria(FeedbackreputationDTO.class);
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
			list = criteria.list();
			transaction.commit();
			session.flush();
			HibernateUtil.closeSession();
		}catch(HibernateException e){
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

		return (list.size() > 0) ? list.get(0) : null;
	}

	/**
	 * @see com.g4g.services.FeedbackService#getReputationList(int)
	 */
	public List<FeedbackreputationDTO> getReputationList(int PlayerId) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED).toString(), Level.INFO);
		Session session = HibernateUtil.getSession();
		List<FeedbackreputationDTO> list = null ;
		try{
			Query query = session
					.createQuery((new StringBuffer(SQLConstants.FROM).append(G4GConstants.FEEDBACKREPUTATIONDTO).append(G4GConstants.PLAYERIDEQUALS
							).append( PlayerId ).append( G4GConstants.ISRATED ).append( true)).toString());
			Transaction transaction = session.beginTransaction();
			list = query.list();
			transaction.commit();
		}catch(HibernateException e){
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
	 * @see com.g4g.services.FeedbackService#update(com.g4g.dto.FeedbackreputationDTO)
	 */
	public FeedbackreputationDTO update(FeedbackreputationDTO dto)throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.FEEDBACKREPUTATIONDTO).toString(),
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
	 * @see com.g4g.services.FeedbackService#getAverageReputation(com.g4g.dto.FeedbackreputationDTO)
	 */
	public int getAverageReputation(int playerId) throws HibernateException {
		int averageReputation = G4GConstants.ZERO;

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		try{
			  StringBuffer queryString = new StringBuffer();
			  queryString.append(SELECT).append(CEILING).append(OPEN_PAREN)
					.append(AVG).append(OPEN_PAREN).append(OPEN_PAREN).append(
							REPUTATION).append(CLOSE_PAREN).append(CLOSE_PAREN)
					.append(FROM).append(FEEDBACKREPUTATION).append(WHERE)
					.append(PLAYERID_SQL).append(EQUAL).append(playerId)
					.append(AND).append(ISRATED).append(EQUAL).append(TRUE);

		      Query query = session.createSQLQuery(queryString.toString());
		      averageReputation = Integer.parseInt(query.uniqueResult() + G4GConstants.NONE);
		      transaction.commit();
		} catch (HibernateException e) {
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
		return averageReputation;
	}

}
