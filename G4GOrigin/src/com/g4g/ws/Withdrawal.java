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
import com.impessa.worldgaming.client.Money;
import com.impessa.worldgaming.client.WithdrawalMethod;

// TODO: Auto-generated Javadoc
/**
 * The Withdrawal class withdraws world gaming balance using given withdrawal
 * methods.<br>
 * 1. Aldrapay Debit Card <br>
 * 2. Bank Transfer <br>
 * 3. Cheque <br>
 * 4. Citadel <br>
 * 5. Click2Pay <br>
 * 6. Gold Exchange <br>
 * 7. Lateral Payment Solutions Debit Card <br>
 * 8. LPS CC Payout <br>
 * 9. LPS Debit Card Payout <br>
 * 10. Money Bookers <br>
 * 11. My Citadel <br>
 * 12. Navaho -900Pay <br>
 * 13. Nerex EWallet <br>
 * 14. NETeller Direct <br>
 * 15. On Demand Funds <br>
 * 16. Payment Trust Debit Card <br>
 * 17. PrePaidATM <br>
 * 18. SafeCharge Debit Card <br>
 * 19. Ukash <br>
 * 20. Vepos
 * 
 * @author Jigar Mistry
 */
public class Withdrawal {

	/** The connection establishes connection with specific database. */
	private Connection connection;

	/**
	 * The statement The object used for executing a static SQL statement and
	 * returning the results it produces.
	 */
	private Statement statement = null;

	/** The result set A table of data representing a database result set */
	private ResultSet resultSet = null;

	/**
	 * The prepared statement An object that represents a precompiled SQL
	 * statement.
	 */
	private PreparedStatement preparedStatement = null;

	/**
	 * Establishes a connection with world gaming database to withdraw money.
	 * 
	 * @param url
	 *            a database url of the form jdbc:subprotocol:subname.
	 * @param username
	 *            the username of the user who wants to withdraw money.
	 * @param password
	 *            the password to authenticate the given Username.
	 */
	public Withdrawal(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
		}
	}

	/**
	 * Withdrawal method performs withdrawal transaction for the current session
	 * of the user, using the given withdrawal method, withdraws the amount
	 * specified by the user.
	 * 
	 * @param sessionId
	 *            the sessionid of the current session of the user. Using the
	 *            session id username can be known.
	 * @param amount
	 *            the amount user wants to withdraw from world gaming, the
	 *            amount should be available in his world gaming balance.
	 * @param withdrawalMethod
	 *            Specifies withdrawal method by which user wants to withdraw
	 *            money. Withdrawal methods are specified above.
	 * 
	 * @return the transactionId is id of transaction by which withdrawal is
	 *         performed so if the transaction fails any how user can confirm
	 *         with administrator that with respective transaction id the
	 *         transaction failed.
	 */
	public String withdrawal(String sessionId, Money amount,
			WithdrawalMethod withdrawalMethod) {
		String transactionId = G4GConstants.NONE;
		try {
			String username = (String) ApplicationLevelPlugin.getHashMap().get(
					sessionId);
			Long balance = amount.getAmount();
			preparedStatement = connection
					.prepareStatement("UPDATE g4guserfinancial set bankbalance = bankbalance - ? where email = ?");
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
			preparedStatement.setString(3, G4GConstants.WITHDRAWAL);
			preparedStatement.setString(4, DataUtil.getDate(
					DataUtil.todayGMT(), G4GConstants.DATEFORMAT));
			preparedStatement.setString(5, "Withdrawal comment");
			preparedStatement.setLong(6, balance);
			preparedStatement.setLong(7, amount.getAmount());

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
		return transactionId;
	}
}
