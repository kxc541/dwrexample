/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.User;

// TODO: Auto-generated Javadoc
/**
 * The Class GetRegistrationDetails gives user info stored at financial web services.
 * @author Jigar Mistry
 */
public class GetRegistrationDetails {
    
    /** The date format. */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
	    G4GConstants.DATE_YYYY_DD_MM_HH_MM_SS);

    /**
     * Gets the date.
     * 
     * @param dStr 
     * 
     * @return the date
     */
    public static Date getDate(String dStr) {
	Date date = null;
	try {
	    date = dateFormat.parse(dStr);
	} catch (Exception sqlException) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	}
	return date;

    }

    // Database related attributes
    /** The connection. */
    private Connection connection = null;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The statement. */
    private Statement statement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * Constructor which will initialize the local connection variable.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public GetRegistrationDetails(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException e) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
	}
    }

    /**
     * It returns the User's object on the basis of sessionId.
     * 
     * @param sessionId the session id
     * 
     * @return User
     * 
     * @throws InternalException_Exception the internal exception_ exception
     * @throws InvalidSessionException_Exception the invalid session exception_ exception
     */
    public User getRegistrationDetails(String sessionId)
	    throws InternalException_Exception,
	    InvalidSessionException_Exception {
	User user = new User();

	String username = (String) ApplicationLevelPlugin.getHashMap().get(sessionId);

	StringBuffer query = new StringBuffer();
	query.append("SELECT * FROM g4guserfinancial WHERE email like '")
		.append(username).append("'");

	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(query.toString());

        if ((resultSet.next())) {
            user.setFirstname(resultSet.getString(G4GConstants.FIRSTNAME));
            user.setLastname(resultSet.getString(G4GConstants.LASTNAME));
            user.setAddress(resultSet.getString(G4GConstants.ADDRESS));
            user.setCity(resultSet.getString(G4GConstants.CITY));
            user.setCountry(resultSet.getString(G4GConstants.COUNTRY));
            user.setState(resultSet.getString(G4GConstants.STATE));
            user.setPostalcode(resultSet.getString(G4GConstants.POSTALCODE));
            user.setPhone(resultSet.getString(G4GConstants.PHONE));
            user.setBirthdate(Long.valueOf(resultSet.getString(G4GConstants.BIRTHDATE)));
            user.setGender(resultSet.getString(G4GConstants.GENDER));
	    }
        return user;
	} catch (Exception sqlException) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	} finally {
	    try {
		if (resultSet != null) {
			resultSet.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }

	    try {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }

	    try {
		if (statement != null) {
			statement.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }

	    try {
		if (connection != null) {
			connection.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }
	}
	return null;
    }

}
