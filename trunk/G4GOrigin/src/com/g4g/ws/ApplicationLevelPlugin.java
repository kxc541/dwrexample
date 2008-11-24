/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

/**
 * Plug in used in struts for setting the value at Application level in HashMap.
 * This class is used to set the mapping for sessionId and userId. So, that by
 * passing the sessionId we can have a userId.
 * 
 * @author Jigar Mistry
 */

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
// TODO: Auto-generated Javadoc

/**
 * The Class ApplicationLevelPlugin.
 */
public class ApplicationLevelPlugin implements PlugIn {
    // Attribute to set <ket, value> for <sessionId, userId>
	//$ANALYSIS-IGNORE
	/** The hash map. */
	    private static HashMap<String, String> hashMap;

    // Attribute get Servlet Context at Application level
    /** The application. */
    private static ServletContext application = null;

    /**
     * It returns the application context.
     * 
     * @return ServletContext
     */
    public static ServletContext getApplication() {
	return application;
    }

    /**
     * It returns the HashMap maintained at application level.
     * 
     * @return HashMap
     */
    public static HashMap<String, String> getHashMap() {
	return hashMap;
    }

    /**
     * It sets the application level HashMap with <key, value> as
     * <sessionId, userId>.
     * 
     * @param hashMap the hash map
     */
    public static void setHashMap(HashMap<String, String> hashMap) {
	ApplicationLevelPlugin.hashMap = hashMap;
    }

    /**
     * Inherited method from class PlugIn.
     */
    public void destroy() {
    }

    /**
     * Inherited method from class PlugIn.
     * 
     * @param servlet the servlet
     * @param config the config
     * 
     * @throws ServletException the servlet exception
     */
    public void init(ActionServlet servlet, ModuleConfig config)
	    throws ServletException {
	hashMap = new HashMap<String, String>();
	application = servlet.getServletContext();
    }
}