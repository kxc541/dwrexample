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

import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserValidationException_Exception;

// TODO: Auto-generated Javadoc
/**
 * The Class SetRegistrationDetails sets registration details.
 * @author Jigar Mistry
 * 
 */
public class SetRegistrationDetails {
    
    /** The connection. */
    private Connection connection = null;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The statement. */
    private Statement statement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * The Constructor.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public SetRegistrationDetails(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException sqlException) {
	    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
		    this.getClass().getName() + sqlException.getMessage());
	    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	}
    }

    /**
     * Sets the registration details.
     * 
     * @param sessionId the session id
     * @param user the user
     * 
     * @throws InternalException_Exception the internal exception_ exception
     * @throws InvalidSessionException_Exception the invalid session exception_ exception
     * @throws UserValidationException_Exception the user validation exception_ exception
     */
    public void setRegistrationDetails(String sessionId, User user)
	    throws InternalException_Exception,
	    InvalidSessionException_Exception,
	    UserValidationException_Exception {
	String username = (String) ApplicationLevelPlugin.getHashMap().get(
		sessionId);

	try {
	    statement = connection.createStatement();
	    resultSet = statement
		    .executeQuery("SELECT userfinancialid FROM g4guserfinancial WHERE email like '"
			    + username + "'");

	    while (resultSet.next()) {
		int id = resultSet.getInt(1);

		preparedStatement = connection
			.prepareStatement("UPDATE g4guserfinancial SET firstname = ?, lastname = ?, address = ?, city = ?, country = ?, state = ?, postalcode = ?, phone = ?, birthdate = ?, gender = ? WHERE userfinancialid = ?");

		preparedStatement.setString(1, user.getFirstname());
		preparedStatement.setString(2, user.getLastname());
		preparedStatement.setString(3, user.getAddress());
		preparedStatement.setString(4, user.getCity());
		preparedStatement.setString(5, user.getCountry());
		preparedStatement.setString(6, user.getState());
		preparedStatement.setString(7, user.getPostalcode());
		preparedStatement.setString(8, user.getPhone());
		preparedStatement.setString(9, DataUtil.getDate(user
			.getBirthdate(), G4GConstants.DATE_DD_MM_YYYY));
		preparedStatement.setString(10, user.getGender());
		preparedStatement.setInt(11, id);

		preparedStatement.executeUpdate();
	    }
	} catch (SQLException sqlException) {
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
    }
}
