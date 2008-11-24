/**********************************************************
 * SubNationalCodeServiceDelegator.java : 
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
import com.g4g.dto.SubNationalCodeDTO;
import com.g4g.services.ServiceLocator;
import com.g4g.services.SubNationalCodeService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class SubNationalCodeServiceDelegator delegates action calls to
 * SubNationalCodeServiceImplementation class via SubNationalCodeService
 * interface. SubNationalCodeServiceDelegator class is attached to
 * SubNationalCodeService interface for the purpose of pre and post method
 * interception.
 * 
 * @author Ankur
 */
public class SubNationalCodeServiceDelegator {

	/** Gets the instance of SubNationalCodeService via ServiceLocator. */
	private static SubNationalCodeService service = ServiceLocator
			.getInstance().getSubNationalCodeService();

	public static SubNationalCodeDTO get(int id)throws HibernateException {
		SubNationalCodeDTO dto = service.get(id);
		return dto;
	}

	/**
	 * @param dto
	 * @return SubNationalCodeDTO
	 * @throws HibernateException
	 * @see SubNationalCodeService#add(SubNationalCodeDTO)
	 */
	public static SubNationalCodeDTO add(SubNationalCodeDTO dto)
			throws HibernateException {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);

		dto = service.add(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.ENDED).toString(), Level.INFO);

				 
		
		return dto;
	}

	/**
	 * @param dto
	 * @throws HibernateException
	 * @see SubNationalCodeService#delete(SubNationalCodeDTO)
	 */
	public static void delete(SubNationalCodeDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);

		service.delete(dto);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.ENDED).toString(), Level.INFO);

	}

	/**
	 * @return List
	 * @throws HibernateException
	 * @see SubNationalCodeService#getList()
	 */
	public static List<SubNationalCodeDTO> getList() throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);

		List<SubNationalCodeDTO> list = service.getList();
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES)
				 .append(G4GConstants.SUBNATIONALCODEDTO).append(G4GConstants.EQUAL).append(list.size())
				.append(G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param searchCriteria
	 * @return List
	 * @throws HibernateException
	 * @see SubNationalCodeService#getList(SearchCriteria)
	 */
	public static List<SubNationalCodeDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);

		List<SubNationalCodeDTO> list = service.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES)
				 .append(G4GConstants.SUBNATIONALCODEDTO).append(G4GConstants.EQUAL).append(list.size())
				.append(G4GConstants.ENDED).toString(), Level.INFO);

		return list;
	}

	/**
	 * @param subNationCode
	 * @return subNationCode
	 * @throws HibernateException
	 * @see SubNationalCodeService#getSubNationName(int)
	 */
	public static String getSubNationName(String subNationCode)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(SubNationalCodeServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.SUB_NATION_NAME).toString(), Level.INFO);

		

		try {
			return service.getSubNationName(Integer.parseInt(subNationCode));
		} catch (NumberFormatException e) {
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(G4GConstants.NATIONNAME, subNationCode);
			List<SubNationalCodeDTO> list = SubNationalCodeServiceDelegator
					.getList(searchCriteria);
			if (list == null) {
				searchCriteria.removeAllAttribute();
				searchCriteria.setAttribute(G4GConstants.NATCODEID,
						subNationCode);
				list = SubNationalCodeServiceDelegator.getList(searchCriteria);
			}
			if (list == null) {
				searchCriteria.removeAllAttribute();
				searchCriteria.setAttribute(G4GConstants.NATIONCODE,
						subNationCode);
				list = SubNationalCodeServiceDelegator.getList(searchCriteria);
			}
			if (list != null && list.size() > 0) {
				return list.iterator().next().getNationname();
			} else {
				return subNationCode;
			}
		}
		
		 
	}

	/**
	 * @param dto
	 * @return SubNationalCodeDTO
	 * @throws HibernateException
	 * @see SubNationalCodeService#update(SubNationalCodeDTO)
	 */
	public static SubNationalCodeDTO update(SubNationalCodeDTO dto)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				SubNationalCodeServiceDelegator.class.getName() + G4GConstants.DASHES
				+ G4GConstants.CURRENTMETHOD
				+ DataUtil.getCurrentMethod()
				+ G4GConstants.CALLINGMETHOD
				+ DataUtil.getCallingMethod() + G4GConstants.DASHES
				+ G4GConstants.STARTED, Level.INFO);
		dto = service.update(dto);
		 AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					SubNationalCodeServiceDelegator.class.getName() + G4GConstants.DASHES
					+ G4GConstants.CURRENTMETHOD
					+ DataUtil.getCurrentMethod()
					+ G4GConstants.CALLINGMETHOD
					+ DataUtil.getCallingMethod() + G4GConstants.DASHES
					+ G4GConstants.ENDED, Level.INFO);
		return dto;
	}
}
