/**********************************************************
 * PaymentAccountForm.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.NationalCodeDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;

/**
 * The Class PaymentAccountForm contains info about payment accounts and user
 * object, withdrawal from financial system.It contains attribute like
 * withdrawalType, cvv2 ..etc.
 * 
 * @author Pratik
 */
@SuppressWarnings(G4GConstants.UNCHECKED)
public class PaymentAccountForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The withdrawal type. */
	private String withdrawalType;

	/** The cvv2. */
	private String cvv2;

	/** The withdraw method. */
	private String withdrawMethod;

	/** The account id. */
	private long accountId;

	/** The account name. */
	private String accountName;

	/** The account type. */
	private String accountType;

	/** The account types. */
	private List accountTypes;

	/** The card type name. */
	private String cardTypeName;

	/** The card types. */
	private List cardTypes;

	/** The card number. */
	private String cardNumber;

	/** The expiry date month. */
	private String expiryDateMonth;

	/** The expiry date year. */
	private String expiryDateYear;

	/** The first. */
	private String first;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The street. */
	private String street = G4GConstants.ADDRESS_;

	/** The city. */
	private String city = G4GConstants.CITY_;

	/** The state. */
	private String state = G4GConstants.STATE_PROVINCE;

	/** The nation code. */
	private String nationCode;

	/** The issue number. */
	private String issueNumber;

	/** The start month. */
	private String startMonth;

	/** The start year. */
	private String startYear;

	/** The country. */
	private String country;

	/** The deposit amount. */
	private long depositAmount;

	/** The withdrawal amount. */
	private long withdrawalAmount;

	/** The country list. */
	private List<NationalCodeDTO> countryList;

	/** The postal. */
	private String postal = G4GConstants.POSTAL_ZIP_CODE;

	/**
	 * Gets the deposit amount.
	 * 
	 * @return the deposit amount
	 */
	public long getDepositAmount() {
		return depositAmount;
	}

	/**
	 * Sets the deposit amount.
	 * 
	 * @param depositAmount
	 *            the new deposit amount
	 */
	public void setDepositAmount(long depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * Gets the withdrawal amount.
	 * 
	 * @return the withdrawal amount
	 */
	public long getWithdrawalAmount() {
		return withdrawalAmount;
	}

	/**
	 * Sets the withdrawal amount.
	 * 
	 * @param withdrawalAmount
	 *            the new withdrawal amount
	 */
	public void setWithdrawalAmount(long withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	/**
	 * Gets the account name.
	 * 
	 * @return the account name
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Sets the account name.
	 * 
	 * @param accountName
	 *            the new account name
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Gets the account type.
	 * 
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 * 
	 * @param accountType
	 *            the new account type
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the account types.
	 * 
	 * @return the account types
	 */
	public List getAccountTypes() {
		return accountTypes;
	}

	/**
	 * Sets the account types.
	 * 
	 * @param accountTypes
	 *            the new account types
	 */
	public void setAccountTypes(List accountTypes) {
		this.accountTypes = accountTypes;
	}

	/**
	 * Gets the card types.
	 * 
	 * @return the card types
	 */
	public List getCardTypes() {
		return cardTypes;
	}

	/**
	 * Sets the card types.
	 * 
	 * @param cardTypes
	 *            the new card types
	 */
	public void setCardTypes(List cardTypes) {
		this.cardTypes = cardTypes;
	}

	/**
	 * Gets the card number.
	 * 
	 * @return the card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets the card number.
	 * 
	 * @param cardNumber
	 *            the new card number
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Gets the expiry date month.
	 * 
	 * @return the expiry date month
	 */
	public String getExpiryDateMonth() {
		return expiryDateMonth;
	}

	/**
	 * Sets the expiry date month.
	 * 
	 * @param expiryDateMonth
	 *            the new expiry date month
	 */
	public void setExpiryDateMonth(String expiryDateMonth) {
		this.expiryDateMonth = expiryDateMonth;
	}

	/**
	 * Gets the expiry date year.
	 * 
	 * @return the expiry date year
	 */
	public String getExpiryDateYear() {
		return expiryDateYear;
	}

	/**
	 * Sets the expiry date year.
	 * 
	 * @param expiryDateYear
	 *            the new expiry date year
	 */
	public void setExpiryDateYear(String expiryDateYear) {
		this.expiryDateYear = expiryDateYear;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the nation code.
	 * 
	 * @return the nation code
	 */
	public String getNationCode() {
		return nationCode;
	}

	/**
	 * Sets the nation code.
	 * 
	 * @param nationCode
	 *            the new nation code
	 */
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	/**
	 * Gets the country list.
	 * 
	 * @return the country list
	 */
	public List<NationalCodeDTO> getCountryList() {
		return countryList;
	}

	/**
	 * Sets the country list.
	 * 
	 * @param countryList
	 *            the new country list
	 */
	public void setCountryList(List<NationalCodeDTO> countryList) {
		this.countryList = countryList;
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
	 * Gets the issue number.
	 * 
	 * @return the issue number
	 */
	public String getIssueNumber() {
		return issueNumber;
	}

	/**
	 * Sets the issue number.
	 * 
	 * @param issueNumber
	 *            the new issue number
	 */
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	/**
	 * Gets the start month.
	 * 
	 * @return the start month
	 */
	public String getStartMonth() {
		return startMonth;
	}

	/**
	 * Sets the start month.
	 * 
	 * @param startMonth
	 *            the new start month
	 */
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	/**
	 * Gets the start year.
	 * 
	 * @return the start year
	 */
	public String getStartYear() {
		return startYear;
	}

	/**
	 * Sets the start year.
	 * 
	 * @param startYear
	 *            the new start year
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	/**
	 * Gets the postal.
	 * 
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}

	/**
	 * Sets the postal.
	 * 
	 * @param postal
	 *            the new postal
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}

	/**
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the card type name.
	 * 
	 * @return the card type name
	 */
	public String getCardTypeName() {
		return cardTypeName;
	}

	/**
	 * Sets the card type name.
	 * 
	 * @param cardTypeName
	 *            the new card type name
	 */
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	/**
	 * Gets the first.
	 * 
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first.
	 * 
	 * @param first
	 *            the new first
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Gets the street.
	 * 
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 * 
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the account id.
	 * 
	 * @return the account id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 * 
	 * @param accountId
	 *            the new account id
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * Gets the withdrawal type.
	 * 
	 * @return the withdrawal type
	 */
	public String getWithdrawalType() {
		return withdrawalType;
	}

	/**
	 * Sets the withdrawal type.
	 * 
	 * @param withdrawalType
	 *            the new withdrawal type
	 */
	public void setWithdrawalType(String withdrawalType) {
		this.withdrawalType = withdrawalType;
	}

	/**
	 * Gets the cvv2.
	 * 
	 * @return the cvv2
	 */
	public String getCvv2() {
		return cvv2;
	}

	/**
	 * Sets the cvv2.
	 * 
	 * @param cvv2
	 *            the new cvv2
	 */
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	/**
	 * Gets the withdraw method.
	 * 
	 * @return the withdraw method
	 */
	public String getWithdrawMethod() {
		return withdrawMethod;
	}

	/**
	 * Sets the withdraw method.
	 * 
	 * @param withdrawMethod
	 *            the new withdraw method
	 */
	public void setWithdrawMethod(String withdrawMethod) {
		this.withdrawMethod = withdrawMethod;
	}

	/**
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		ActionErrors errors = null;

		if (!G4GConstants.PAYPAL.equals(this.accountType)) {
			errors = super.validate(mapping, request);
		}

		if (DataValidator.isNull(this.accountName)) {
			ActionMessage msg = new ActionMessage(
					G4GConstants.PAYPALACCOUNTNAME);
			if (errors == null || errors.isEmpty()) {
				errors = new ActionErrors();
				errors.add(G4GConstants.ACCOUNTNAME, msg);
			} else {
				errors.add(G4GConstants.ACCOUNTNAME, msg);
			}

		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return errors;
	}
}
