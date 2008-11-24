/**********************************************************
 * TwoPlayerTournamentGameOptionServiceDelegator.java : 
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
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.services.ServiceLocator;
import com.g4g.services.TwoPlayerTournamentGameOptionService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class TwoPlayerTournamentGameOptionServiceDelegator delegates action
 * calls to TwoPlayerTournamentGameOptionServiceImplementation class via
 * TwoPlayerTournamentServiceGameOption interface.
 * TwoPlayerTournamentGameOptionServiceDelegator class is attached to
 * TwoPlayerTournamentGameOptionService interface for the purpose of pre and
 * post method interception.
 * 
 * @author Ankur
 */
public class TwoPlayerTournamentGameOptionServiceDelegator {

	/**
	 * Gets the instance of TwoPlayerTournamentGameOptionService via
	 * ServiceLocator.
	 */
	private static TwoPlayerTournamentGameOptionService service = ServiceLocator
			.getInstance().getTwoPlayerTournamentGameOptionService();

	/**
	 * @param dto 
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#add(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public static TwoplayertournamentgameoptionsDTO add(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto 
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#delete(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public static void delete(TwoplayertournamentgameoptionsDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		service.delete(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
	}

	/**
	 * @return List<TwoplayertournamentgameoptionsDTO>
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#getList()
	 */
	public static List<TwoplayertournamentgameoptionsDTO> getList()
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<TwoplayertournamentgameoptionsDTO> list = service.getList();
		 
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria 
	 * @return List<TwoplayertournamentgameoptionsDTO>
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#getList(com.g4g.dto.SearchCriteria)
	 */
	public static List<TwoplayertournamentgameoptionsDTO> getList(
			SearchCriteria searchCriteria) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<TwoplayertournamentgameoptionsDTO> list = service
				.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.TWOPLAYERTOURNAMENTGAMEOPTIONSDTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param dto 
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#update(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public static TwoplayertournamentgameoptionsDTO update(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		dto = service.update(dto);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto 
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#get(com.g4g.dto.TwoplayertournamentgameoptionsDTO)
	 */
	public static TwoplayertournamentgameoptionsDTO get(
			TwoplayertournamentgameoptionsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		dto = service.get(dto);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param tournamentid 
	 * @throws HibernateException 
	 * @see com.g4g.services.TwoPlayerTournamentGameOptionService#deleteAll(int)
	 */
	public static void deleteAll(int tournamentid) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		service.deleteAll(tournamentid);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(TwoPlayerTournamentGameOptionServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
	}
}
