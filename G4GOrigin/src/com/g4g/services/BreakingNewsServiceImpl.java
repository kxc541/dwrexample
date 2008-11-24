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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.g4g.dto.BreakingNewsDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.MySQLHibernateUtil;

/**
 * Class to implement service for thread table.
 * @author ankur
 *
 */
public class BreakingNewsServiceImpl implements BreakingNewsServices {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$


	/**
	 * @see com.g4g.services.BreakingNewsServices#getList()
	 */
	@SuppressWarnings(UNCHECKED)
	public List<BreakingNewsDTO> getList() {
		
		return null;
	}
	

	/**
	 * @see com.g4g.services.BreakingNewsServices#add(com.g4g.dto.BreakingNewsDTO)
	 */
	public BreakingNewsDTO add(BreakingNewsDTO dto) throws HibernateException{
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.STARTED ).append( G4GConstants.DASHES
						).append( G4GConstants.PARAMETERS ).append( G4GConstants.BREAKINGNEWSDTO).toString(),
				Level.INFO);
		Session session = MySQLHibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(dto);
			transaction.commit();
			session.flush();
			MySQLHibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.RECORD_ADDED).toString(), Level.INFO);
		}catch (HibernateException e) {
			transaction.rollback();
			MySQLHibernateUtil.closeSession();
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
					).append( G4GConstants.CALLINGMETHOD
					).append( DataUtil.getCallingMethod()
					).append( G4GConstants.CURRENTMETHOD 
					).append( DataUtil.getCurrentMethod() 
					).append( G4GConstants.DASHES 
					).append( G4GConstants.RECORD_NOT_ADDED).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
							).append( G4GConstants.CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( G4GConstants.CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
							).append( G4GConstants.DASHES).toString()
							, Level.ERROR);

			throw e;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( G4GConstants.COLON_WITH_SPACES
						).append( G4GConstants.CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( G4GConstants.CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( G4GConstants.DASHES
						).append( G4GConstants.ENDED).toString(), Level.INFO);
		return dto;
	}
	
}
