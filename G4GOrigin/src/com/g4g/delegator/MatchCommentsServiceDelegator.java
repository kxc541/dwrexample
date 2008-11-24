/**********************************************************
 * MatchCommentsServiceDelegator.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;
import org.apache.log4j.Level;

import org.hibernate.HibernateException;

import com.g4g.dto.SearchCriteria;
import com.g4g.dto.Twoplayermatchcomments;
import com.g4g.services.MatchCommentService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class MatchCommentsServiceDelegator delegates action calls to
 * MatchCommentServiceImplementation class via MatchCommentService interface.
 * MatchCommentServiceDelegator class is attached to MatchCommentService
 * interface for the purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class MatchCommentsServiceDelegator {

	/** Gets the instance of MatchCommentService via ServiceLocator. */
	private static MatchCommentService service = ServiceLocator.getInstance()
			.getMatchCommentService();

	/**
	 * @param dto
	 * @return Twoplayermatchcomments
	 * @throws HibernateException
	 * @see com.g4g.services.MatchCommentService#add(com.g4g.dto.Twoplayermatchcomments)
	 */
	public static Twoplayermatchcomments add(Twoplayermatchcomments dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws Exception
	 * @see com.g4g.services.MatchCommentService#delete(Twoplayermatchcomments)
	 */
	public static void delete(Twoplayermatchcomments dto) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.MatchCommentService#getList()
	 */
	public static List<Twoplayermatchcomments> getList()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		List<Twoplayermatchcomments> list = service.getList();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.MatchCommentService#getList(SearchCriteria)
	 */
	public static List<Twoplayermatchcomments> getList(
			SearchCriteria searchCriteria) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		
		List<Twoplayermatchcomments> list = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return list;
	}

	/**
	 * @param dto
	 * @return Twoplayermatchcomments
	 * @throws HibernateException
	 * @see com.g4g.services.MatchCommentService#update(Twoplayermatchcomments)
	 */
	public static Twoplayermatchcomments update(Twoplayermatchcomments dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws Exception
	 * @see com.g4g.services.MatchCommentService#deleteMatchAllComments(Twoplayermatchcomments)
	 */
	public static void deleteMatchAllComments(Twoplayermatchcomments dto)
			throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		
		service.deleteMatchAllComments(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				MatchCommentsServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.ENDED, Level.INFO);
	}

}
