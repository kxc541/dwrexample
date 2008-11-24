/**********************************************************
 * GameCategoryServiceDelegator.java : 
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

import com.g4g.dto.GamecategoryDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.GameCategoryService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class GameCategoryServiceDelegator delegates action calls to
 * GameOptionServiceImplementation class via GameOptionService
 * interface.GameOptionServiceDelegator class is attached to GameOptionService
 * interface for the purpose of pre and post method interception.
 * 
 * @author Punam
 */
public class GameCategoryServiceDelegator {

	/** Gets the instance of GameService via ServiceLocator. */
	private static GameCategoryService service = ServiceLocator.getInstance()
			.getGameCategoryService();

	/**
	 * @param dto
	 * @return GamecategoryDTO
	 * @throws Exception
	 * @see com.g4g.services.GameCategoryService#add(com.g4g.dto.GamecategoryDTO)
	 */
	public static GamecategoryDTO add(GamecategoryDTO dto) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.GAME_CATEGORY_DTO)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @param dto
	 * @throws Exception
	 * @see com.g4g.services.GameCategoryService#delete(GamecategoryDTO)
	 */
	public static void delete(GamecategoryDTO dto) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
	}

	/**
	 * @param id
	 * @return GamecategoryDTO
	 * @throws HibernateException
	 * @see com.g4g.services.GameCategoryService#getGame(int)
	 */
	public static GamecategoryDTO getGame(int id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		GamecategoryDTO dto = service.getGame(id);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.PARAMETERS).append(G4GConstants.ID)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.GameCategoryService#getList()
	 */
	public static List<GamecategoryDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<GamecategoryDTO> list = service.getList();
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.GAME_CATEGORY_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.GameCategoryService#getList(SearchCriteria)
	 */
	public static List<GamecategoryDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		List<GamecategoryDTO> list = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.GAME_CATEGORY_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return GamecategoryDTO
	 * @throws Exception
	 * @see com.g4g.services.GameCategoryService#update(GamecategoryDTO)
	 */
	public static GamecategoryDTO update(GamecategoryDTO dto) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
		
		dto = service.update(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(GameCategoryServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

}
