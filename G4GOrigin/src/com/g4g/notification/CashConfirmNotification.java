/**********************************************************
 * CashConfirmNotification.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.notification;

import com.g4g.utils.G4GConstants;

/**
 * CashConfirmNotification contains information about attributes required for
 * CashConfirmNotification. When player deposits or withdraws money to/from his
 * account, CashConfirm notification get generated. This class extends
 * BaseNotification.
 * 
 * @author Pratik
 */

public class CashConfirmNotification extends BaseNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subject. This attribute contains subject line of notification. */
	private String subject;

	/**
	 * The transaction type. This attribute contains transactionType -
	 * deposit/withdraw for the cash transaction.
	 */
	private String transactionType;

	/** The amount. This attribute contains money amount of the transaction. */
	private long amount;

	/**
	 * Instantiates a new cash confirm notification.
	 * 
	 * @param to -
	 *            the to
	 * @param from -
	 *            the from
	 * @param transactionType -
	 *            the transaction type
	 * @param amount -
	 *            the amount
	 */
	public CashConfirmNotification(int to, int from, String transactionType,
			long amount) {
		super(to, from);
		this.subject = G4GConstants.CASH_CONFIRM_SUBJECT;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	/**
	 * Gets the value of transactionType.
	 * 
	 * @return the transaction type
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Gets the value of amount.
	 * 
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}

	/**
	 * Gets the value of subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

}
