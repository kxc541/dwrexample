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

import com.g4g.utils.AuditUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Deletes Payment Account.
 */
public class DeletePaymentAccount {
    // Database related attributes
    /** The connection. */
    private Connection connection = null;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * Instantiates a new delete payment account.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public DeletePaymentAccount(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException e) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
	}
    }

    /**
     * Delete payment account.
     * 
     * @param sessionId the session id
     * @param accountId the account id
     */
    public void deletePaymentAccount(String sessionId, long accountId) {
	String username = (String) ApplicationLevelPlugin.getHashMap().get(
		sessionId);

	try {
	    
	    preparedStatement = connection
		    .prepareStatement("DELETE FROM paymentaccounts WHERE userfinancialid = ? and id = ?");
	    preparedStatement.setString(1, username);
	    preparedStatement.setLong(2, accountId);

	    preparedStatement.executeUpdate();
	    
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
		if (connection != null) {
			connection.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }
	}
    }
}
