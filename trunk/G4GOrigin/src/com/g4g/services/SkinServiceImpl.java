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

import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.g4g.dto.SkinDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.HibernateUtil;

/**
 * Class contains method implementation related to timezone.
 * 
 * @author ankur
 */
public class SkinServiceImpl implements SkinService {
	public SkinDTO getSkin(String id) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.SKINID_DB).toString(),
				Level.INFO);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(SkinDTO.class);
		criteria.add(Restrictions.eq(G4GConstants.SKINID_DB, id));
		List<SkinDTO> dto = criteria.list();
		transaction.commit();
		HibernateUtil.closeSession();
		if (dto != null && dto.size() > G4GConstants.ZERO){
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.ENDED).toString(), Level.INFO);
			return dto.get(G4GConstants.ZERO);
		}
		else {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.ENDED).toString(), Level.INFO);
			return new SkinDTO(G4GConstants.WORLD_GAMING_SITE_ID);
		}
	}

}
