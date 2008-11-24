/**********************************************************
 * CashierForm.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The CashierForm Class contains attribute for cashier page. CashierForm is
 * used in CahierAction. It contains the operation name as deposit, withdraw.
 * 
 * @author Jigar Mistry
 */
public class CashierForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The deposit. */
	private String deposit;

	/** The withdraw. */
	private String withdraw;

	/**
	 * Gets the deposit.
	 * 
	 * @return the deposit
	 */
	public String getDeposit() {
		return deposit;
	}

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return null;
	}

	/**
	 * Gets the withdraw.
	 * 
	 * @return the withdraw
	 */
	public String getWithdraw() {
		return withdraw;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * Sets the deposit.
	 * 
	 * @param deposit
	 *            the new deposit
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	/**
	 * Sets the withdraw.
	 * 
	 * @param withdraw
	 *            the new withdraw
	 */
	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

}
