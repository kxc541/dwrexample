/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

/**
 * To get the user's Bank balance as well as his/her Winning balance.
 * 
 * @author Jigar Mistry
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.g4g.utils.AuditUtil;
import com.impessa.worldgaming.client.Balance;
import com.impessa.worldgaming.client.CurrencyType;
import com.impessa.worldgaming.client.Money;

// TODO: Auto-generated Javadoc
/**
 * The Class GetBalance.
 */
public class GetBalance {
    // Database related attributes
    /** The connection. */
    private Connection connection;

    /** The statement. */
    private Statement statement;

    /** The result set. */
    private ResultSet resultSet;

    /**
     * Constructor which will initialize local connection variable.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public GetBalance(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException sqlException) {
	    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
		    this.getClass().getName() + sqlException.getMessage());
	}
    }

    /**
     * It will find the Bank balance as well as Winning balance of user on the
     * basis of sessionId.
     * 
     * @param sessionId the session id
     * 
     * @return Balance
     */
    @SuppressWarnings({"LoopStatementThatDoesntLoop"})
    public Balance getBalance(String sessionId) {
	Balance balance = new Balance();

	String username = ApplicationLevelPlugin.getHashMap().get(sessionId);
	StringBuffer query = new StringBuffer();

	query
		.append(
			"SELECT bankbalance, winningbalance FROM g4guserfinancial WHERE email like '")
		.append(username).append("'");

	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery(query.toString());

        if ((resultSet.next())) {
            Money bankMoney = new Money();
            bankMoney.setAmount(resultSet.getLong(1));
            bankMoney.setCurrency(CurrencyType.USD);
            balance.setBankBalance(bankMoney);

            Money winningMoney = new Money();
            winningMoney.setAmount(resultSet.getLong(2));
            winningMoney.setCurrency(CurrencyType.USD);
            balance.setWinningsBalance(winningMoney);
	    }
        return balance;
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
