/**********************************************************
 * FeedbackServiceDelegator.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.FeedbackreputationDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.services.FeedbackService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class FeedbackServiceDelegator delegates action calls to
 * FeedbackServiceImplementation class via FeedbackService interface.
 * FeedbackServiceDelegator class is attached to FeedbackService interface for
 * the purpose of pre and post method interception.
 * 
 * @author Punam
 */
public class FeedbackServiceDelegator {

	/** Gets the instance of FeedbackService via ServiceLocator. */
	private static FeedbackService service = ServiceLocator.getInstance()
			.getFeedbackService();

	/**
	 * @param dto
	 * @return FeedbackreputationDTO
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#add(com.g4g.dto.FeedbackreputationDTO)
	 */
	public static FeedbackreputationDTO add(FeedbackreputationDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.PARAMETERS).append(G4GConstants.FEEDBACKREPUTATIONDTO )
				.append(G4GConstants.ENDED).toString(),Level.INFO);
  
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#delete(FeedbackreputationDTO)
	 */
	public static void delete(FeedbackreputationDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
  
	}

	/**
	 * @param playerId
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#getReputationList(int)
	 */
	public static List<FeedbackreputationDTO> getList(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<FeedbackreputationDTO> list = service.getReputationList(playerId);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.FEEDBACKREPUTATIONDTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
  
		return list;
	}

	/**
	 * @param playerId
	 * @param matchId 
	 * @return FeedbackreputationDTO
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#getReputationList(int)
	 */
	public static FeedbackreputationDTO getReputation(int playerId, int matchId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		SearchListCriteria searchListCriteria = new SearchListCriteria();
		searchListCriteria.setAttribute(G4GConstants.PLAYER, new Object[] {new PlayerDTO(playerId), SearchListCriteria.EQ});
		searchListCriteria.setAttribute(G4GConstants.TWO_PLAYER_MATCH, new Object[] {new TwoplayermatchDTO(matchId), SearchListCriteria.EQ});
		FeedbackreputationDTO feedbackreputationDTO = service.getReputation(searchListCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.FEEDBACKREPUTATIONDTO).append(G4GConstants.EQUAL)
				 .append(G4GConstants.PARAMETERS).append(G4GConstants.PLAYERID ).append(G4GConstants.MATCHID)
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
  
		return feedbackreputationDTO;
	}
	
	/**
	 * @param playerId
	 * @param matchId 
	 * @return FeedbackreputationDTO
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#getReputationList(int)
	 */
	public static int getAverageReputation(int playerId)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		return service.getAverageReputation(playerId);
	}
	
	/**
	 * @param dto
	 * @return FeedbackreputationDTO
	 * @throws HibernateException
	 * @see com.g4g.services.FeedbackService#update(FeedbackreputationDTO)
	 */
	public static FeedbackreputationDTO update(FeedbackreputationDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(FeedbackServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}
}
