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
import java.util.ArrayList;
import java.util.List;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Class Statement contains details about transaction.
 * 
 * @author Jigar Mistry
 */
public class Statement {

	/** The connection. */
	private Connection connection;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private java.sql.Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * The Constructor initializes the connection.
	 * 
	 * @param url
	 *            the url
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
	public Statement(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + sqlException.getMessage());
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					sqlException);
		}
	}

	/**
	 * Statement returns the list of transactions.
	 * 
	 * @param sessionId
	 *            the session id
	 * @param transactionTypeList
	 *            the transaction type list
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * 
	 * @return the list< transaction>
	 */
	@SuppressWarnings(G4GConstants.UNCHECKED)
	public List<Transaction> statement(String sessionId,
			List transactionTypeList, long from, long to) {
		List<Transaction> transactionList = new ArrayList<Transaction>();

		try {
			String username = (String) ApplicationLevelPlugin.getHashMap().get(
					sessionId);
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT id, transactiontype, date, commnet, balance, amount FROM statement WHERE userid like '"
							+ username + "'");

			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getString(1));
				transaction.setType(resultSet.getString(2));
				transaction.setCalendar(DataUtil.getLongDate(resultSet
						.getString(3)));
				transaction.setComment(resultSet.getString(4));
				transaction.setBalance(resultSet.getLong(5));
				transaction.setAmount(resultSet.getLong(6));

				transactionList.add(transaction);
			}
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					sqlException);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}
		}
		return transactionList;
	}
}
