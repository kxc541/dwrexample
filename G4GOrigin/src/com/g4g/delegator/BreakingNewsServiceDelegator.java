/**********************************************************
 * BreakingNewsServiceDelegator.java : 
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

import com.g4g.dto.BreakingNewsDTO;
import com.g4g.services.BreakingNewsServices;
import com.g4g.services.ServiceLocator;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class BreakingNewsServiceDelegator delegates action calls to
 * BreakingNewsServicesImplementation class via BreakingNewsServices interface.
 * BreakingNewsServicesDelegator class is attached to BreakingNewsServices
 * interface for the purpose of pre and post method interception.
 * 
 * @author Ankur
 */
public class BreakingNewsServiceDelegator {

	/** Gets the instance of BreakingNewsServices via ServiceLocator. */
	private static BreakingNewsServices service = ServiceLocator.getInstance()
			.getBreakingNewsServices();

	/**
	 * @param dto
	 * @return BreakingNewsDTO
	 * @throws Exception
	 * @see com.g4g.services.BreakingNewsServices#add(BreakingNewsDTO)
	 */
	public static BreakingNewsDTO add(BreakingNewsDTO dto) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(BreakingNewsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		dto = service.add(dto);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(BreakingNewsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.PARAMETERS ).append(G4GConstants.BREAKINGNEWSDTO )
				.append(G4GConstants.ENDED).toString(),Level.INFO);

		return dto;
	}

	/**
	 * @return List
	 * @see com.g4g.services.BreakingNewsServices#getList()
	 */
	public static List<BreakingNewsDTO> getList() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(BreakingNewsServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		List<BreakingNewsDTO> list = service.getList();
		AuditUtil.getInstance().writeLog(
				  AuditUtil.FILE_TYPE_G4G,new StringBuffer(BreakingNewsServiceDelegator.class.getName())
				 .append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				 .append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				 .append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				 .append(G4GConstants.BREAKINGNEWSDTO).append(G4GConstants.EQUAL).append(list.size())
				 .append(G4GConstants.ENDED).toString(),Level.INFO);

		return list;
	}

}
