/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.utils.properties;

import java.io.Serializable;
import java.util.Enumeration;

import com.cipl.services.properties.HTTPPropertyLoader;
import com.cipl.services.properties.Properties;
import com.cipl.services.properties.PropertyLoadException;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class G4GProperties loads project related properties file. It retrieves
 * all or given properties from properties file as needed, those are stored in
 * .properties file and changed externally without affecting the code.
 * Properties file contains Each key and its corresponding value in the property
 * list is a string. We can get the property value by retrieving it keyValue.
 * <p>The key contains all of the characters in the line starting with the first
 * non-white space character and up to, but not including, the first unescaped
 * '=', ':', or white space character other than a line terminator.Line
 * terminator characters can be included using \r and \n escape sequences. Any
 * white space after the key is skipped; if the first non-white space character
 * after the key is '=' or ':', then it is ignored and any white space
 * characters after it are also skipped. All remaining characters on the line
 * become part of the associated element string; if there are no remaining
 * characters, the element is the empty string "". Once the raw character
 * sequences constituting the key and element are identified, escape processing
 * is performed
 * 
 * @author Rakesh Ray
 */
public class G4GProperties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Properties class represents a persistent set of properties. */
	private static Properties myProp = null;

	/**
	 * The Constant propertiesURL the url of the properties file to get the
	 * value of given keys.
	 */
	protected static final String propertiesURL = String.valueOf(System.getProperty("APP_DOC_DIR")).concat("/properties").concat("/g4g.properties");

	/**
	 * static initialization
	 */

	static {
		loadProperties();
	}

	/**
	 * Gets all the properties of g4g.properties file.
	 * 
	 * @return all the property keys and it corresponding values.
	 */
	public static String getAllProperties() {
		Enumeration<String> records = myProp.keys();
		StringBuffer s = new StringBuffer();
		try {
			while (records.hasMoreElements()) {
				String key = records.nextElement();
				String value = myProp.getProperty(key);
				s.append(key);
				s.append(G4GConstants.COLON_WITH_BACK_SPACE);
				s.append(value);
				s.append(G4GConstants.NEW_LINE);
			}
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,e);
		}
		return s.toString();
	}

	/**
	 * Searches for the property with the specified key in this properties file.
	 * If the key is not found in this property list. The method returns blank
	 * if the property is not found.
	 * 
	 * @param pty
	 *            the pty is the key value to get the property value for the
	 *            given key value in the properties file.
	 * 
	 * @return the property value for the given key from properties file.
	 */
	public static String getProperty(String pty) {
		String rtsStr = G4GConstants.NONE;
		rtsStr = myProp.getProperty(pty);
		return ( rtsStr == null ) ? G4GConstants.NONE : rtsStr;
	}

	/**
	 * Reads a properties list from the given properties file to the myProp
	 * variable using HTTPPropertyLoader.
	 */
	static private void loadProperties() {
		try {
			myProp = HTTPPropertyLoader.getExpandedProperties(propertiesURL);
		} catch (PropertyLoadException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}
	}

	/**
	 * Reload properties file.
	 */
	public static void reloadProperties() {
		loadProperties();
	}

	/**
	 * Instantiates a new g4g properties. It calls static block for loading the
	 * properties. It loads the properties file by calling constructor.
	 */
	private G4GProperties() {
	}
}
