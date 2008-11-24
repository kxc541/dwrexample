package com.g4g.utils.properties;

import java.io.Serializable;
import java.util.Enumeration;

import com.cipl.services.properties.HTTPPropertyLoader;
import com.cipl.services.properties.Properties;
import com.cipl.services.properties.PropertyLoadException;

/**
 * @author Rakesh Ray
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class G4GProperties implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Properties myProp = null;
	protected static final String propertiesURL = "/g4g.properties";

	/**
	 * static initialization
	 */

	static {
		loadProperties();
	}

	public static String getAllProperties() {
		Enumeration<String> records = myProp.keys();
		StringBuffer s = new StringBuffer();
		try {
			while (records.hasMoreElements()) {
				String key = records.nextElement();
				String value = myProp.getProperty(key);
				s.append(key);
				s.append(" :");
				s.append(value);
				s.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public static String getProperty(String pty) {
		String rtsStr = "";
		rtsStr = myProp.getProperty(pty);
		if (rtsStr == null) {
			return "";
		} else {
			return rtsStr;
		}
	}

	static private void loadProperties() {
		try {
			myProp = HTTPPropertyLoader.getExpandedProperties(propertiesURL);
		} catch (PropertyLoadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void reloadProperties() {
		loadProperties();
	}

	/*
	 * public static ArrayList getProperties() { Enumeration records =
	 * myProp.keys(); ArrayList result = new ArrayList(); try { while
	 * (records.hasMoreElements()) { String key = (String)
	 * records.nextElement(); String value = (String) myProp.getProperty(key);
	 * KeyValuePair pair = new KeyValuePair(key,value); result.add(pair); } }
	 * catch (Exception e) { e.printStackTrace(); } return result; }
	 */

	// Disable the instantiation of the Class.
	// Should use the Static methods.
	private G4GProperties() {
	}
}
