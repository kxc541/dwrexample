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

import static com.g4g.utils.G4GConstants.EQUAL;
import static com.g4g.utils.G4GConstants.NEW_LINE;
import static com.g4g.utils.G4GConstants.NO_USER;
import static com.g4g.utils.G4GConstants.REFERER;
import static com.g4g.utils.G4GConstants.SECURITY_DESTURL;
import static com.g4g.utils.G4GConstants.SECURITY_ENDTRAP;
import static com.g4g.utils.G4GConstants.SECURITY_HEADER;
import static com.g4g.utils.G4GConstants.SECURITY_HEADER_VALUE;
import static com.g4g.utils.G4GConstants.SECURITY_HEADER_ZERO;
import static com.g4g.utils.G4GConstants.SECURITY_PARAMETER_ZERO;
import static com.g4g.utils.G4GConstants.SECURITY_POSTDATA;
import static com.g4g.utils.G4GConstants.SECURITY_POSTDATA_VALUE;
import static com.g4g.utils.G4GConstants.SECURITY_REFERURL;
import static com.g4g.utils.G4GConstants.SECURITY_REFFER_URL;
import static com.g4g.utils.G4GConstants.SECURITY_TRAP;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
/**
 * The ResourceBundleUtil class is Singleton class used to get all resource
 * bundels used in the project. Resource bundles contain locale-specific
 * objects. When your program needs a locale-specific resource, a String for
 * example, your program can load it from the resource bundle that is
 * appropriate for the current user's locale. It can be used to be easily
 * localized, or translated, into different languages, handle multiple locales
 * at once.
 * 
 * @author ankur
 */
public class ResourceBundleUtil {

	private static final ResourceBundle resource = ResourceBundleUtil.getApplicationProperties();
	
	/**
	 * Instantiates a new resource bundle util.
	 */
	private ResourceBundleUtil() {
	}

	/**
	 * Gets a resource bundle using the specified base name, the default locale,
	 * and the caller's class loader. Calling this method is equivalent to
	 * calling
	 * 
	 * getBundle(baseName, Locale.getDefault(),
	 * this.getClass().getClassLoader()),
	 * 
	 * except that getClassLoader() is run with the security privileges of
	 * ResourceBundle.
	 * 
	 * @return a resource bundle for the given base name and the default locale
	 */
	public static ResourceBundle getApplicationProperties() {
		return ResourceBundle
				.getBundle(G4GConstants.APPLICATIONRESOURCE);
	}
	
	/**
	 * @param key
	 * @param replace
	 * @return replaced string
	 */
	public static String getValue(String key , String... replace ) {
		StringBuilder aa =new StringBuilder(resource.getString(key));
		for (int i = 0; i < replace.length; i++) {
			int index=aa.indexOf(GameIdConstants.replacement);
			aa.replace(index,index+3 ,replace[i]);	
		}
		return aa.toString();
	}

	public static String getSecurityTrapAudit(HttpServletRequest request, String srcnFunc,
					String description) {
		String username = NO_USER;
		if (DataUtil.getUserDTO(request) != null && DataUtil.getUserDTO(request).getPlayerDTO() != null) {
			username = String.valueOf(DataUtil.getUserDTO(request).getPlayerDTO().getId());
		}
		StringBuilder audit = new StringBuilder(getValue(SECURITY_TRAP,  request
						.getRemoteAddr(), username, srcnFunc ,  description));
		audit.append(NEW_LINE);
		String refer = null;

			 refer = request.getHeader(REFERER);
		if(refer ==null)
			refer = getValue(SECURITY_REFFER_URL);
		audit.append(getValue(SECURITY_REFERURL, refer));
		audit.append(NEW_LINE);
		audit.append(getValue(SECURITY_DESTURL, request.getRequestURL().toString()));
		audit.append(NEW_LINE);
		audit.append(getValue(SECURITY_HEADER));
		audit.append(NEW_LINE);
		Enumeration headers = request.getHeaderNames();
		if(!headers.hasMoreElements()) {
			audit.append(getValue(SECURITY_HEADER_ZERO));
		}
		while (headers.hasMoreElements()) {
			Object header = (Object) headers.nextElement();
			audit.append(header.toString() + G4GConstants.BLANK);
			audit.append(EQUAL);
			audit.append(getValue(SECURITY_HEADER_VALUE,request.getHeader(header.toString()))+ G4GConstants.BLANK);
			audit.append(NEW_LINE);
		}
		audit.append(NEW_LINE);
		audit.append(getValue(SECURITY_POSTDATA));
		audit.append(NEW_LINE);
		headers = request.getParameterNames();
		if(!headers.hasMoreElements()) {
			audit.append(getValue(SECURITY_PARAMETER_ZERO));
		}
		while (headers.hasMoreElements()) {
			Object header = (Object) headers.nextElement();
			audit.append(header.toString()+ G4GConstants.BLANK);
			audit.append(EQUAL);
			audit.append(getValue(SECURITY_POSTDATA_VALUE,request.getParameterValues(header.toString()))+ G4GConstants.BLANK);
			audit.append(NEW_LINE);
		}
		audit.append(NEW_LINE);
		audit.append(getValue(SECURITY_ENDTRAP));
		audit.append(NEW_LINE);
		return audit.toString();
	}
}
