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
import com.impessa.worldgaming.client.CreditCardPaymentResponse;
import com.impessa.worldgaming.client.DepositInput;
import com.impessa.worldgaming.client.DepositOutput;

// TODO: Auto-generated Javadoc
/**
 * The Class Deposits money to WG account.
 */
public class Deposit {
    
    /** The connection. */
    private Connection connection;

    /** The statement. */
    private Statement statement = null;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * Instantiates a new deposit.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public Deposit(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException e) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
	}
    }

    /**
     * Deposit.
     * 
     * @param sessionId the session id
     * @param depositInput the deposit input
     * 
     * @return the deposit output
     */
    public DepositOutput deposit(String sessionId, DepositInput depositInput) {
	CreditCardPaymentResponse creditCardPaymentResponse = new CreditCardPaymentResponse();

	try {
	    String username = (String) ApplicationLevelPlugin.getHashMap().get(
		    sessionId);
	    Long balance = depositInput.getOrderTotal().getAmount();

	    preparedStatement = connection
		    .prepareStatement("UPDATE g4guserfinancial set bankbalance = bankbalance + ? where email = ?");
	    preparedStatement.setLong(1, balance);
	    preparedStatement.setString(2, username);
	    preparedStatement.executeUpdate();

	    statement = connection.createStatement();
	    resultSet = statement
		    .executeQuery("SELECT bankbalance FROM g4guserfinancial where email = '"
			    + username + "'");

	    while (resultSet.next()) {
		balance = resultSet.getLong(1);
	    }

	    statement = connection.createStatement();
	    resultSet = statement
		    .executeQuery("SELECT max(id)+1 from statement");

	    int id = 1;
	    while (resultSet.next()) {
		id = resultSet.getInt(1);
	    }

	    preparedStatement = connection
		    .prepareStatement("INSERT INTO statement(id, userid, transactiontype, date, commnet, balance, amount) VALUES (?,?,?,?,?,?,?)");

	    preparedStatement.setInt(1, id);
	    preparedStatement.setString(2, username);
	    preparedStatement.setString(3, G4GConstants.DEPOSIT);
	    preparedStatement.setString(4, DataUtil.getDate(
		    DataUtil.todayGMT(), G4GConstants.DATEFORMAT));
	    preparedStatement.setString(5, "Deposit comment");
	    preparedStatement.setLong(6, balance);
	    preparedStatement.setLong(7, depositInput.getOrderTotal()
		    .getAmount());

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
	return creditCardPaymentResponse;
    }
}
