/**********************************************************
 * PlayerNetworkServiceDelegator.java : 
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

import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.PlayerNetworkService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class PlayerNetworkServiceDelegator delegates action calls to
 * PlayerNetworkServiceImplementation class via PlayerNetworkService interface.
 * PlayerNetworkServiceDelegator class is attached to PlayerNetworkService
 * interface for the purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class PlayerNetworkServiceDelegator {

	/** Gets the instance of PlayerNetworkService via ServiceLocator. */
	private static PlayerNetworkService service = ServiceLocator.getInstance()
			.getPlayerNetworkService();

	/**
	 * @param dto
	 * @return PlayernetworkDTO
	 * @throws HibernateException
	 * @see PlayerNetworkService#add(PlayernetworkDTO)
	 */
	public static PlayernetworkDTO add(PlayernetworkDTO dto){
	AuditUtil.getInstance().writeLog(
			AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
			.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
			.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
			.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
			.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see PlayerNetworkService#delete(PlayernetworkDTO)
	 */
	public static void delete(PlayernetworkDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		service.delete(dto);
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see PlayerNetworkService#getList()
	 */
	public static List<PlayernetworkDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		List<PlayernetworkDTO> list = service.getList();
		

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.PLAYER_NETWORK_DTO).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see PlayerNetworkService#getList(SearchCriteria)
	 */
	public static List<PlayernetworkDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<PlayernetworkDTO> list = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.PLAYER_NETWORK_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param dto
	 * @return PlayernetworkDTO
	 * @throws HibernateException
	 * @see PlayerNetworkService#update(PlayernetworkDTO)
	 */
	public static PlayernetworkDTO update(PlayernetworkDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(PlayerNetworkServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

}
