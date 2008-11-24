/**********************************************************
* HibernateSessionFactory.java 
*
* Created : 16 apr. 08 by author
* Last modified $ Date: $ by $ Author:  $
* Revision: $ Revision:  $
* Version : $ ID : 1$
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.utils;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility for session opening and closing.
 * 
 * @author Ankur
 */
public class MySQLHibernateUtil {

	/** SessionFactory object. */
	public static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure(
					G4GConstants.HIBERNATE_MYSQL_CFGXML).buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					G4GConstants.HIBERNATE_MYSQL_CLASS + ex.getMessage(), Level.ERROR);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/** Session of return through sessionfactory. */
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	/**
	 * Gets the session.
	 * 
	 * @return Session
	 * 
	 * @throws HibernateException the hibernate exception
	 */
	public static Session getSession() throws HibernateException {
		Session s = session.get();
		// Open a new Session, if this thread has none yet
		if (s == null || s.isDirty() || !s.isConnected()) {
			s = sessionFactory.openSession();
			// Store it in the ThreadLocal variable
			session.set(s);
		}
		return s;
	}

	/**
	 * Close session.
	 * 
	 * @throws HibernateException the hibernate exception
	 */
	public static void closeSession() throws HibernateException {
		Session s = session.get();
		if (s != null) {
			s.close();
		}
		session.set(null);
	}

}
