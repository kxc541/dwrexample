package com.cipl.util;

////////////////////////////////////////////////////////////////////////////////
// HibernateSessionFactory.java : $Source: 
// Hibernate session management.
//
// Created : 16 apr. 08 by ankur
// Last modified $Date: $ by $Author:  $
// Revision: $Revision:  $
// Version : $ID : 1$
// Copyright (c) 2008
////////////////////////////////////////////////////////////////////////////////


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
 * @version $Id: SessionManager.java,v 1.12 2005/06/02 12:53:53 jfsmart Exp $
 */
public final class HibernateSessionFactory {
 
    /**
     * Parameter used to define the XML Hibernate configuration file
     */
    private static final String HIBERNATE_CONFIGURATION_FILE_PROPERTY 
        = "hibernate.configuration.file";
    
    /**
     * Class logger
     */
    private static Log log = LogFactory.getLog(HibernateSessionFactory.class);

    /**
     * The one and only session manager instance
     */
    private static HibernateSessionFactory sessionManager = new HibernateSessionFactory();
    /**
     * The one and only Hibernate session factory.
     */
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
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
    public static Session currentSession() throws HibernateException {
    	log.debug("Hibernate Session Manager :Fetching a hibernate session");
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
    	log.debug("Hibernate Session Manager :Fetching a hibernate session done.");
        return s;
    }

    /**
     * Close the current Hibernate session
     * @throws HibernateException
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        log.debug("Hibernate Session Manager : closeSession");
        Session s = threadSession.get();
        threadSession.set(null);
        if (s != null && s.isOpen()) {
            log.debug("Hibernate Session Manager : closing session");
            s.close();
            log.debug("Hibernate Session Manager : session closed");
        }
        s = null;
    }
    
    /**
     * Connect this session to the current thread
     * @param session
     * @throws HibernateException
     */
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
    private HibernateSessionFactory() {
        super();
    }

    /**
     * Returns an instance of a session mananger used to obtain
     * a Hibernate Session object.
     * @return the Hibernate session manager.
     */
    public static HibernateSessionFactory getInstance() {
        return sessionManager;
    }

    /**
     * @return Session
     * @throws HibernateException
     */
    public static Session openSession() throws HibernateException {
        return HibernateSessionFactory.currentSession();
    }


}
