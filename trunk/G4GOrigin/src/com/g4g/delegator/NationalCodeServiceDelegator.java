/**********************************************************
 * NationalCodeServiceDelegator.java : 
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

import com.g4g.dto.NationalCodeDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.services.NationalCodeService;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class NationalCodeServiceDelegator delegates action calls to
 * NationalCodeServiceImplementation class via NationalCodeService
 * interface.NationalCodeServiceDelegator class is attached to
 * NationalCodeService interface for the purpose of pre and post method
 * interception.
 * 
 * @author Ankur
 */
public class NationalCodeServiceDelegator {

	/** Gets the instance of NationalCodeService via ServiceLocator. */
	private static NationalCodeService service = ServiceLocator.getInstance()
			.getNationalCodeService();

	/**
	 * @param dto
	 * @return NationalCodeDTO
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#add(com.g4g.dto.NationalCodeDTO)
	 */
	public static NationalCodeDTO add(NationalCodeDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);


				
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#delete(NationalCodeDTO)
	 */
	public static void delete(NationalCodeDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		service.delete(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);


				
	}

	/**
	 * @return List<NationalCodeDTO>
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#getList()
	 */
	public static List<NationalCodeDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);


		List<NationalCodeDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.NATIONAL_CODE_DTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);
		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#getList(SearchCriteria)
	 */
	public static List<NationalCodeDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		List<NationalCodeDTO> list = service.getList(searchCriteria);

		AuditUtil.getInstance().writeLog(
		  AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
		 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
		 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
		 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
		 .append(G4GConstants.NATIONAL_CODE_DTO).append(G4GConstants.EQUAL).append(list.size())
		 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

	/**
	 * @param dto
	 * @return NationalCodeDTO
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#update(NationalCodeDTO)
	 */
	public static NationalCodeDTO update(NationalCodeDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.update(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);


				
		return dto;
	}

	/**
	 * @param code
	 * @return String
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#getNationName(int)
	 */
	public static String getNationName(int code) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);
			
		try {
			return service.getNationName(code);
		} catch (NumberFormatException e) {
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(G4GConstants.NATIONCODE, code);
			return NationalCodeServiceDelegator.getList(searchCriteria).get(0)
					.getNationname();
		}
		

	}

	/**
	 * @param code
	 * @return String
	 * @throws HibernateException
	 * @see com.g4g.services.NationalCodeService#getNationName(int)
	 */
	public static String getNationName(String code) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

	
		String name = null;
		try {
			name= NationalCodeServiceDelegator.getNationName(Integer
					.parseInt(code)); 
		} catch (Exception e) {
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(G4GConstants.NATIONCODE, code);
			name =  NationalCodeServiceDelegator.getList(searchCriteria).get(0)
					.getNationname();
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(NationalCodeServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);


				
  
		
		return name;
		
	}
}
