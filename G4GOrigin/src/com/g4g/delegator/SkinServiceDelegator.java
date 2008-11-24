/**********************************************************
 * TimeZoneServiceDelegator.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.SkinDTO;
import com.g4g.services.ServiceLocator;
import com.g4g.services.SkinService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class TimeZoneServiceDelegator delegates action calls to
 * TimeZoneServiceImplementation via TimeZoneService.TimeZoneServiceDelegator
 * class is attached to TimeZoneService interface for the purpose of pre and
 * post method interception.
 * 
 * @author Ankur
 */
public class SkinServiceDelegator {

	/** Gets the instance of TimeZoneService via ServiceLocator. */
	private static SkinService service = ServiceLocator.getInstance()
			.getSkinService();

	/**
	 * @param id
	 * @return SkinDTO
	 * @throws HibernateException
	 * @see SkinServiceDelegator#getSkinDTO(String)
	 */
	public static SkinDTO getSkinDTO(String id) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(SkinServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.STARTED).toString(),Level.INFO);

		SkinDTO dto = service.getSkin(id);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,new StringBuffer(SkinServiceDelegator.class.getName())
				.append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
				.append(DataUtil.getCallingMethod()).append(G4GConstants.CURRENTMETHOD)
				.append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
				.append(G4GConstants.ENDED).toString(),Level.INFO);
		return dto;
	}

}
