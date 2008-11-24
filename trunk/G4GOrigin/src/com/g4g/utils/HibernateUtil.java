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

/**********************************************************
* HibernateSessionFactory.java : $Source:
* Hibernate session management.
*
* Created : 16 apr. 08 by ankur
* Last modified $Date: $ by $Author:  $
* Revision: $Revision:  $
* Version : $ID : 1$
* Copyright (c) 2008
**********************************************************/


import org.apache.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.g4g.utils.properties.HibernateProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * Hibernate session management.
 * Sets up a session factory and provides functions to provide
 * Hibernate sessions to client classes.
 * This class works using a singleton pattern : references to
 * the unique instance of this class are fetched using the
 * <code>getInstance()</code> method.
 * The hibernate configuration is set up using the XML hibernate configuration
 * file. The configuration file is expected to be in the root of your CLASSPATH.
 * By default, it is called 'hibernate.cfg.xml', but its name may also be
 * specified in the parameters.properties (or 'local-parameters.properites') file,
 * using the 'hibernate.configuration.file' property, ex :
 * <pre>
 *   hibernate.configuration.file = hibernate-test.cfg.xml
 * </pre>
 *
 * The session is automatically initialized when necessary by the
 * getConnection() method in the HibernateConnectionFactory class.
 *
 * @author jfsmart
 *
 * Copyright (c) 2003
 * @version $Id: SessionManager.java,v 1.12 2005/06/02 12:53:53 ankur Exp $
 */
public final class HibernateUtil {
 
    /**
     * Parameter used to define the XML Hibernate configuration file
     */
   
    private static final String DEPRECATION = "deprecation"; //$NON-NLS-1$
	/**
     * The one and only session manager instance
     */
    private static HibernateUtil sessionManager = new HibernateUtil();
    /**
     * The one and only Hibernate session factory.
     */
    private static final SessionFactory sessionFactory;
   
    private static final String CONNECTION_URL = HibernateProperties.getProperty(PropertiesConstants.CONNECTION_URL).trim();
   
    private static final String CONNECTION_USER_NAME =HibernateProperties.getProperty(PropertiesConstants.CONNECTION_USERNAME).trim();
       
    private static final String CONNECTION_PASSWORD = HibernateProperties.getProperty(PropertiesConstants.CONNECTION_PASSWORD).trim(); 
   
   
    static {
        try {
           
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,new StringBuffer ( G4GConstants.HIBERNATE_USING).append(G4GConstants.HIBERNATE_USINGURL ).append( CONNECTION_URL).toString(), Level.DEBUG);
           
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, new StringBuffer (G4GConstants.HIBERNATE_USING).append(G4GConstants.HIBERNATE_USINGNAME ).append( CONNECTION_USER_NAME).toString(), Level.DEBUG);
           
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, new StringBuffer (G4GConstants.HIBERNATE_USING).append(G4GConstants.HIBERNATE_USINGPASSWORD ).append( CONNECTION_PASSWORD).toString(), Level.DEBUG);
           
           
            // Create the SessionFactory
            sessionFactory = new Configuration().configure(G4GConstants.HIBERNATE_CFGXML).setProperty(G4GConstants.HIBERNATE_CONNECTION+G4GConstants.HIBERNATE_CONN_URL, CONNECTION_URL).setProperty(G4GConstants.HIBERNATE_CONNECTION+G4GConstants.HIBERNATE_CONN_USERNAME, CONNECTION_USER_NAME).setProperty(G4GConstants.HIBERNATE_CONNECTION+G4GConstants.HIBERNATE_CONN_PASSWORD, CONNECTION_PASSWORD).buildSessionFactory();
        } catch (HibernateException ex) {
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.SESSIONFACTORY_EXCEPTION + ex.getCause(), Level.ERROR);
            throw new RuntimeException(G4GConstants.CONFIGURATION_PROBLEM + ex.getMessage(), ex);
        }
    }
   
    /**
     * The thread-local Hibernate session object
     */
    private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
   
    /**
     * @return Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        return currentSession();
    }
   
    /**
     * @return Session
     * @throws HibernateException
     */
    @SuppressWarnings(DEPRECATION)
	public static Session currentSession() throws HibernateException {
        AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.FETCHING_HIBERNATE_CLASS+G4GConstants.FETCHING_HIBERNATE_SESSION, Level.DEBUG);
        Session s = threadSession.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
            threadSession.set(s);
        }
        if (!s.isOpen()) {
            s = null;
            s = sessionFactory.openSession();
            threadSession.set(s);
        }
       
        if (!s.isConnected()){
            s.reconnect();
        }
        AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.FETCHING_HIBERNATE_CLASS+G4GConstants.FETCHING_HIBERNATE_SESSIONDONE , Level.DEBUG);
        return s;
    }
    /**
     * Close the current Hibernate session
     * @throws HibernateException
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.FETCHING_HIBERNATE_CLASS+G4GConstants.FETCHING_HIBERNATE_SESSIONCLOSE , Level.DEBUG);
        Session s = threadSession.get();
        threadSession.set(null);
        if (s != null && s.isOpen()) {
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.FETCHING_HIBERNATE_CLASS+G4GConstants.FETCHING_HIBERNATE_SESSIONCLOSING , Level.DEBUG);
            s.close();
            AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, G4GConstants.FETCHING_HIBERNATE_CLASS+G4GConstants.FETCHING_HIBERNATE_SESSIONCLOSED , Level.DEBUG);
        }
        s = null;
    }
   
    /**
     * Connect this session to the current thread
     * @param session
     * @throws HibernateException
     */
    @SuppressWarnings(DEPRECATION)
	public static void reconnect(Session session) throws HibernateException {
            if (!session.isConnected()) {
                session.reconnect();
            }
            threadSession.set(session);
    }
   
    /**
     * Disconnect the current session from the current thread
     * @return Session
     * @throws HibernateException
     * @throws HibernateException
     */
    public static Session disconnectSession() throws HibernateException {
        Session session = currentSession();
        if (session.isConnected() && session.isOpen()) {
            session.disconnect();
        }
        return session;
    }

    /**
     * Default constructor
     */
    private HibernateUtil() {
        super();
    }

    /**
     * Returns an instance of a session mananger used to obtain
     * a Hibernate Session object.
     * @return the Hibernate session manager.
     */
    public static HibernateUtil getInstance() {
        return sessionManager;
    }

    /**
     * @return Session
     * @throws HibernateException
     */
    public static Session openSession() throws HibernateException {
        return HibernateUtil.currentSession();
    }
}

