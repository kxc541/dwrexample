/**********************************************************
 * RegisterAjaxImpl.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.FALSE;
import static com.g4g.utils.G4GConstants.LOGINNAME;
import static com.g4g.utils.G4GConstants.SCREENNAME_DB;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TRUE;
import static com.g4g.utils.G4GConstants.ZERO;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Level;

import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;

/**
 * The Class RegisterAjaxImpl is called by ajax method when the user enters his
 * screename and emailaddress, this class checks for whether this screenname and
 * email is available or not for the user who is registering. If available tahn
 * user can proceed with registration.
 * 
 * @author Jigar Mistry
 */
@SuppressWarnings({G4GConstants.CLONE_DOES_NOT_CALL_SUPER_CLONE})
public class RegisterAjaxImpl implements Cloneable {

	private static final String JAVADOC_REFERENCE = "JavadocReference"; //$NON-NLS-1$
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * Instantiates a new register ajax impl.
	 */
	private RegisterAjaxImpl() {
	}

	//$ANALYSIS-IGNORE
	/**
		 * This class should not be instantiated and cloned
		 */
		@Override
		public Object clone() throws CloneNotSupportedException {
			throw new CloneNotSupportedException();
		}

	/**
	 * The method isNameAvailable Checks for if the screenname given by
	 * registering user is not taken by any other user registered till now. It
	 * returns true if screenname is available else it returs false.
	 * 
	 * @param screenName
	 *            the screenname for which check is made.
	 * 
	 * @return the string - true if name is available, else false.
	 */
	@SuppressWarnings(UNCHECKED)
	public static String isNameAvailable(String screenName) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(RegisterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);

		if (DataValidator.isNull(screenName.trim())) {
			return FALSE;
		}

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(SCREENNAME_DB, screenName);
		List<PlayerDTO> list = PlayerServiceDelegator.getList(searchCriteria,
				true);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(RegisterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
		return ( list.size() != ZERO ) ? FALSE : TRUE;
	}

	/**
	 * The method isLoginAvailable Checks for if the email-address given by
	 * registering user is not taken by any other user registered till now. It
	 * returns true if email-address is available else it returs false.
	 * 
	 * @param loginName
	 *            the email-address for which check is made.
	 * 
	 * @return the string - true if email-address is available, else false.
	 */
	@SuppressWarnings( { UNCHECKED, JAVADOC_REFERENCE })
	public static String isLoginAvailable(String loginName) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(RegisterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);
		EmailValidator email = EmailValidator.getInstance();
		if (email.isValid(loginName)) {
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(LOGINNAME, loginName);
			List<PlayerDTO> list = PlayerServiceDelegator
					.getList(searchCriteria);

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(RegisterAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(
							CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							DASHES).append(ENDED)
							.toString(), Level.INFO);

			return ( list.size() != ZERO ) ? FALSE : TRUE;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(RegisterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
		return null;

	}
}
