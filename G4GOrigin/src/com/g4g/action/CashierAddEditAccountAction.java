/**********************************************************
 * className.java : 
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.forms.BaseForm;
import com.g4g.forms.PaymentAccountForm;
import com.g4g.notification.CashConfirmNotification;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.AccessException_Exception;
import com.impessa.worldgaming.client.CountryType;
import com.impessa.worldgaming.client.CreditCardPaymentAccount;
import com.impessa.worldgaming.client.CreditCardPaymentRequest;
import com.impessa.worldgaming.client.CreditCardType;
import com.impessa.worldgaming.client.CurrencyType;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidPaymentException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.LimitExceededException_Exception;
import com.impessa.worldgaming.client.Money;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.PayPalPaymentAccount;
import com.impessa.worldgaming.client.PaymentAccount;
import com.impessa.worldgaming.client.PpecSetupRequest;
import com.impessa.worldgaming.client.PpecSetupResponse;
import com.impessa.worldgaming.client.WithdrawalMethod;

/**
 * Add or edit the paymentAccount.
 * 
 * @struts.action path="/displayCashierAddEdotAccount" name="paymentAccountForm"
 *                scope="request"
 * @struts.action-forward name="addsuccess"
 *                        path="/WebContent/casierPage_addAccount.jsp"
 * @struts.action-forward name="editsuccess"
 *                        path="/WebContent/casierPage_editAccount.jsp"
 * @author Pratik
 * 
 */

public class CashierAddEditAccountAction extends BaseAction {
	private static final String ADD_ACCOUNT = "account.created.message";

	private static final String UPDATE_ACCOUNT = "account.updated.message";

	private static final String DELETED_ACCOUNT = "account.deleted.message";

	/**
	 * @author Pratik
	 * 
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.DISPLAY_STARTS)
						.append(G4GConstants.DASHES).append(
								request.getSession().getId()).toString());

		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		return null;
	}

	/**
	 * @struts.form-field name="submit" If operation equals add forwarded to
	 *                    casierPage_addAccount.jsp If operation equals edit
	 *                    forwarded to casierPage_editAccount.jsp
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.SUBMIT_STARTS).append(G4GConstants.DASHES)
						.append(request.getSession().getId()).toString());
		PaymentAccountForm paymentAccountForm = (PaymentAccountForm) form;
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.SUBMIT_STARTS).toString());
		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		if (operation != null) {
			if (G4GConstants.ADD.equals(operation)) {
				return mapping.findForward(G4GConstants.ADDSUCCESS);
			} else if (operation.equalsIgnoreCase(G4GConstants.edit)) {
				String message = resource.getString(UPDATE_ACCOUNT);
				try {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(

							G4GConstants.PAYMENT_LIST).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).toString());
					List<PaymentAccount> paymentAccountList = G4GFinancialDelegator
							.getPaymentAccounts(DataUtil.getUserDTO(request)
									.getSessionId(), true);
					int totalAccounts = paymentAccountList.size();

					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									G4GConstants.LIST_SIZE).append(
									totalAccounts).append(G4GConstants.DASHES)
									.append(request.getSession().getId())
									.toString());

					if (totalAccounts > G4GConstants.ZERO) {
						for (int index = G4GConstants.ZERO; index < totalAccounts; index++) {
							if (paymentAccountList.get(index).getAccountId() == Long
									.valueOf(request
											.getParameter(G4GConstants.PAYMENT_ID))) {
								if (G4GConstants.AC_PAYPAL
										.equals(paymentAccountList.get(index)
												.getAccountType().toString())) {
									PayPalPaymentAccount paymentAccount = (PayPalPaymentAccount) paymentAccountList
											.get(index);
									paymentAccountForm
											.setAccountId(paymentAccount
													.getAccountId());
									paymentAccountForm
											.setAccountName(paymentAccount
													.getName());
									paymentAccountForm
											.setAccountType(G4GConstants.PAYPAL);
								} else {
									CreditCardPaymentAccount paymentAccount = (CreditCardPaymentAccount) paymentAccountList
											.get(index);
									paymentAccountForm
											.setAccountId(paymentAccount
													.getAccountId());
									paymentAccountForm
											.setAccountName(paymentAccount
													.getName());
									paymentAccountForm.setFirst(paymentAccount
											.getOwnerName());
									paymentAccountForm
											.setAccountType(G4GConstants.CREDIT_DEBIT);
									paymentAccountForm
											.setCardTypeName(paymentAccount
													.getType().name());
									paymentAccountForm
											.setCardNumber(paymentAccount
													.getNumber());
									paymentAccountForm.setCity(paymentAccount
											.getOwnerCity());
									paymentAccountForm.setState(paymentAccount
											.getOwnerState());
									paymentAccountForm.setStreet(paymentAccount
											.getOwnerStreet());
									paymentAccountForm.setPostal(paymentAccount
											.getOwnerZip());
									paymentAccountForm
											.setCountry(paymentAccount
													.getOwnerCountry().name());
									paymentAccountForm
											.setExpiryDateMonth((paymentAccount
													.getExpiryMonth() < G4GConstants.TEN_NUMBER ? G4GConstants.ZEROSTRING
													: G4GConstants.NONE)
													+ paymentAccount
															.getExpiryMonth());
									paymentAccountForm.setExpiryDateYear(String
											.valueOf(paymentAccount
													.getExpiryYear()));
									if (CreditCardType.SOLO
											.equals(paymentAccount.getType())
											|| CreditCardType.SWITCH
													.equals(paymentAccount
															.getType())) {
										paymentAccountForm
												.setIssueNumber(paymentAccount
														.getIssueNumber());
										paymentAccountForm
												.setStartMonth((paymentAccount
														.getStartMonth() < G4GConstants.TEN_NUMBER ? G4GConstants.ZEROSTRING
														: G4GConstants.NONE)
														+ paymentAccount
																.getStartMonth());
										paymentAccountForm.setStartYear(String
												.valueOf(paymentAccount
														.getStartYear()));
									}
								}
							}
						}
					}
				} catch (InternalException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod())
									.append(message).append(G4GConstants.BLANK)
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (InvalidSessionException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.NO_ERROR_AVAILABLE)
									.append(e.getMessage())
									.append(e.getCause()).append(
											G4GConstants.BLANK).append(
											request.getSession().getId())
									.toString(), Level.INFO);
				}
				request.getSession().setAttribute(
						G4GConstants.NOTIFICATIONMESSAGE, message);
				return mapping.findForward(G4GConstants.EDITSUCCESS);
			} else if (G4GConstants.DELETE.equals(operation)) {
				String message = resource.getString(DELETED_ACCOUNT);
				try {
					G4GFinancialDelegator.deletePaymentAccount(DataUtil
							.getUserDTO(request).getSessionId(), Long
							.valueOf(request
									.getParameter(G4GConstants.PAYMENT_ID)));
				} catch (AccessException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod())
									.append(message).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (InternalException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									message).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
				} catch (InvalidSessionException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									message).toString(), Level.ERROR);
				}
				request.getSession().setAttribute(
						G4GConstants.NOTIFICATIONMESSAGE, message);
				return mapping.findForward(G4GConstants.DELETESUCCESS);
			} else if (G4GConstants.ADD_ACCOUNT.equals(operation)) {
				String message = resource.getString(ADD_ACCOUNT);
				try {
					if (G4GConstants.CREDIT_DEBIT.equals(paymentAccountForm
							.getAccountType())) {
						CreditCardPaymentAccount creditCardPaymentAccount = new CreditCardPaymentAccount();
						creditCardPaymentAccount.setExpiryMonth(Integer
								.parseInt(paymentAccountForm
										.getExpiryDateMonth()));
						creditCardPaymentAccount.setExpiryYear(Integer
								.parseInt(paymentAccountForm
										.getExpiryDateYear()));
						creditCardPaymentAccount.setName(paymentAccountForm
								.getAccountName());
						creditCardPaymentAccount.setNumber(paymentAccountForm
								.getCardNumber());
						creditCardPaymentAccount
								.setOwnerCity(paymentAccountForm.getCity());
						creditCardPaymentAccount.setOwnerCountry(CountryType
								.fromValue(paymentAccountForm.getCountry()));
						creditCardPaymentAccount
								.setOwnerName(paymentAccountForm.getFirst());
						creditCardPaymentAccount
								.setOwnerSurname(paymentAccountForm.getFirst());
						creditCardPaymentAccount
								.setOwnerState(paymentAccountForm.getState());
						creditCardPaymentAccount
								.setOwnerStreet(paymentAccountForm.getStreet());
						creditCardPaymentAccount.setOwnerZip(paymentAccountForm
								.getPostal());
						creditCardPaymentAccount.setType(CreditCardType
								.valueOf(paymentAccountForm.getCardTypeName()));

						if (CreditCardType.SOLO.equals( CreditCardType.valueOf(
														paymentAccountForm.getCardTypeName()) )
								|| CreditCardType.SWITCH.equals( CreditCardType.valueOf(
																		paymentAccountForm.getCardTypeName()) )) {
							creditCardPaymentAccount
									.setIssueNumber(paymentAccountForm
											.getIssueNumber());
							creditCardPaymentAccount.setStartMonth(Integer
									.parseInt(paymentAccountForm
											.getStartMonth()));
							creditCardPaymentAccount
									.setStartYear(Integer
											.parseInt(paymentAccountForm
													.getStartYear()));
						}

						AuditUtil
								.getInstance()
								.writeLog(
										AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass()
												.getName())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(
														DataUtil
																.getCurrentMethod())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(G4GConstants.FORPLAYER)
												.append(
														DataUtil
																.getUserDTO(
																		request)
																.getPlayerDTO()
																.getEmailaddress())
												.append(G4GConstants.DASHES)
												.append(
														request.getSession()
																.getId())
												.append(
														G4GConstants.REG_PAYMENT_ACCOUNT)
												.toString(), Level.INFO);
						G4GFinancialDelegator.registerPaymentAccount(DataUtil
								.getSessionId(request),
								creditCardPaymentAccount);
						request.setAttribute(G4GConstants.ADD_ACCOUNT,
								G4GConstants.TRUE);
					} else if (G4GConstants.PAYPAL.equals( paymentAccountForm.getAccountType() )) {
						PayPalPaymentAccount payPalPaymentAccount = new PayPalPaymentAccount();
						payPalPaymentAccount.setName(paymentAccountForm
								.getAccountName());
						G4GFinancialDelegator.registerPaymentAccount(DataUtil
								.getSessionId(request), payPalPaymentAccount);
						request.setAttribute(G4GConstants.ADD_ACCOUNT,
								G4GConstants.TRUE);
					}
				} catch (InternalException_Exception e) {
					message = e.getMessage();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									message).toString(), Level.ERROR);
				} catch (InvalidPaymentException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (InvalidSessionException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				}

				request.getSession().setAttribute(
						G4GConstants.NOTIFICATIONMESSAGE, message);
			} else if (G4GConstants.UPDATE_ACCOUNT.equals( operation )) {
				try {
					if (G4GConstants.CREDIT_DEBIT.equals( paymentAccountForm.getAccountType() )) {
						CreditCardPaymentAccount creditCardPaymentAccount = new CreditCardPaymentAccount();
						creditCardPaymentAccount
								.setAccountId(paymentAccountForm.getAccountId());
						creditCardPaymentAccount.setExpiryMonth(Integer
								.parseInt(paymentAccountForm
										.getExpiryDateMonth()));
						creditCardPaymentAccount.setExpiryYear(Integer
								.parseInt(paymentAccountForm
										.getExpiryDateYear()));
						creditCardPaymentAccount.setName(paymentAccountForm
								.getAccountName());
						creditCardPaymentAccount.setNumber(paymentAccountForm
								.getCardNumber());
						creditCardPaymentAccount
								.setOwnerCity(paymentAccountForm.getCity());
						creditCardPaymentAccount.setOwnerCountry(CountryType
								.fromValue(paymentAccountForm.getCountry()));
						creditCardPaymentAccount
								.setOwnerName(paymentAccountForm.getFirst());
						creditCardPaymentAccount
								.setOwnerSurname(paymentAccountForm.getFirst());
						creditCardPaymentAccount
								.setOwnerState(paymentAccountForm.getState());
						creditCardPaymentAccount
								.setOwnerStreet(paymentAccountForm.getStreet());
						creditCardPaymentAccount.setOwnerZip(paymentAccountForm
								.getPostal());
						creditCardPaymentAccount.setType(CreditCardType
								.valueOf(paymentAccountForm.getCardTypeName()));
						if (CreditCardType.SOLO.equals( CreditCardType.valueOf(
														paymentAccountForm.getCardTypeName()) )
								|| CreditCardType.SWITCH
										.equals(CreditCardType.valueOf(
												paymentAccountForm.getCardTypeName()))) {
							creditCardPaymentAccount
									.setIssueNumber(paymentAccountForm
											.getIssueNumber());
							creditCardPaymentAccount.setStartMonth(Integer
									.parseInt(paymentAccountForm
											.getStartMonth()));
							creditCardPaymentAccount
									.setStartYear(Integer
											.parseInt(paymentAccountForm
													.getStartYear()));
						}
						AuditUtil
								.getInstance()
								.writeLog(
										AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass()
												.getName())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(
														DataUtil
																.getCurrentMethod())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(G4GConstants.FORPLAYER)
												.append(
														DataUtil
																.getUserDTO(
																		request)
																.getPlayerDTO()
																.getEmailaddress())
												.append(G4GConstants.DASHES)
												.append(
														request.getSession()
																.getId())
												.append(
														G4GConstants.UPDATE_PAYMENT_ACCOUNT)
												.toString(), Level.INFO);
						G4GFinancialDelegator.updatePaymentAccount(DataUtil
								.getUserDTO(request).getSessionId(),
								creditCardPaymentAccount);
					} else if (G4GConstants.PAYPAL.equals(
							paymentAccountForm.getAccountType())) {
						PayPalPaymentAccount payPalPaymentAccount = new PayPalPaymentAccount();
						payPalPaymentAccount.setAccountId(paymentAccountForm
								.getAccountId());
						payPalPaymentAccount.setName(paymentAccountForm
								.getAccountName());
						AuditUtil
								.getInstance()
								.writeLog(
										AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass()
												.getName())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(
														DataUtil
																.getCurrentMethod())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(G4GConstants.FORPLAYER)
												.append(
														DataUtil
																.getUserDTO(
																		request)
																.getPlayerDTO()
																.getEmailaddress())
												.append(G4GConstants.DASHES)
												.append(
														request.getSession()
																.getId())
												.append(
														G4GConstants.UPDATE_PAYMENT_ACCOUNT)
												.toString(), Level.INFO);
						G4GFinancialDelegator.updatePaymentAccount(DataUtil
								.getSessionId(request), payPalPaymentAccount);
					}
				} catch (InternalException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (InvalidPaymentException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (InvalidSessionException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				} catch (AccessException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
				}
			} else if (G4GConstants.MAKE_DEPOSIT.equals(operation)) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.FORPLAYER).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getEmailaddress()).append(
								G4GConstants.DASHES).append(
								request.getSession().getId()).append(
								G4GConstants.DEPOSITING_MONEY).toString(),
						Level.INFO);
				String message = resource
						.getString(G4GConstants.MONEY_DEPOSIT_MESSAGE);
				if (request.getParameter(G4GConstants.PAYMENT_TYPE) == null) {
					return mapping.findForward(G4GConstants.HOMEPAGE);
				}
				if (G4GConstants.CREDIT_CARD.equals(
						request.getParameter(G4GConstants.PAYMENT_TYPE))) {
					CreditCardPaymentRequest payment = new CreditCardPaymentRequest();
					payment.setIpAddress(request.getLocalAddr());
					Money m = new Money();
					m.setAmount(DataUtil.setImpassaMoney(paymentAccountForm
							.getDepositAmount()));
					m.setCurrency(CurrencyType.USD);
					payment.setOrderTotal(m);
					payment.setAccountId(Long.valueOf(paymentAccountForm
							.getAccountType())); // NOTE
					// THAT
					// ACCOUNT
					// ID
					// is
					// VALID
					// Credit
					// Card
					// Account
					// Id.
					payment.setCvv(paymentAccountForm.getCvv2());
					// CreditCardPaymentResponse creditCardPaymentResponse =
					// null;
					try {
						// creditCardPaymentResponse =
						// (CreditCardPaymentResponse)
						G4GFinancialDelegator.deposit(DataUtil
								.getSessionId(request), payment);
						CashConfirmNotification cashConfirmNotification = new CashConfirmNotification(
								Integer.parseInt(DataUtil.getUserId(request)),
								G4GConstants.ZERO, G4GConstants.CREDIT,
								paymentAccountForm.getDepositAmount());
						AuditUtil
								.getInstance()
								.writeLog(
										AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass()
												.getName())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(
														DataUtil
																.getCurrentMethod())
												.append(G4GConstants.COLON_WITH_SPACES)
												.append(G4GConstants.FORPLAYER)
												.append(
														DataUtil
																.getUserDTO(
																		request)
																.getPlayerDTO()
																.getEmailaddress())
												.append(G4GConstants.DASHES)
												.append(
														request.getSession()
																.getId())
												.append(
														G4GConstants.SENDING_CASH_NOTIFICATION)
												.toString(), Level.INFO);
						NotificationServiceDelegator.sendNotification(
								cashConfirmNotification, request);
					} catch (InternalException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (InvalidPaymentException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (InvalidSessionException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (LimitExceededException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					}
				} else if (G4GConstants.PAYPAL
						.equals(request.getParameter(G4GConstants.PAYMENT_TYPE))) {
					PpecSetupRequest pps = new PpecSetupRequest();
					pps.setAccountId(Long.valueOf(paymentAccountForm
							.getAccountType()));
					Money money = new Money();
					money.setAmount(DataUtil.setImpassaMoney(paymentAccountForm
							.getDepositAmount()));
					money.setCurrency(CurrencyType.USD);
					pps.setOrderTotal(money);

					pps
							.setCancelUrl(G4GProperties
									.getProperty(PropertiesConstants.G4G_PAYPAL_CANCEL_URL));

					pps
							.setReturnUrl(G4GProperties
									.getProperty(PropertiesConstants.G4G_PAYPAL_RETURN_URL));

					try {
						PpecSetupResponse ppecSetupResponse;

						ppecSetupResponse = (PpecSetupResponse) G4GFinancialDelegator
								.deposit(DataUtil.getSessionId(request), pps);
						request.getSession().setAttribute(G4GConstants.AMOUNT1,
								paymentAccountForm.getDepositAmount());
						request.getSession().setAttribute(G4GConstants.TOKEN1,
								ppecSetupResponse.getToken());
						request.getSession().setAttribute(
								G4GConstants.PAYMENTACCOUNTID,
								paymentAccountForm.getAccountType());
					} catch (InternalException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (InvalidPaymentException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (InvalidSessionException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
					} catch (LimitExceededException_Exception e) {
						message = e.getMessage();
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(this.getClass().getName())
										.append(e.getMessage()).toString(),
								Level.ERROR);
					} catch (Exception e) {
						message = e.getMessage();
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(this.getClass().getName())
										.append(e.getMessage()).toString(),
								Level.ERROR);
					}
				}
				request.getSession().setAttribute(
						G4GConstants.NOTIFICATIONMESSAGE, message);
			} else if (G4GConstants.MAKE_WITHDRAWAL.equals(operation)) {
				String message = resource
						.getString(G4GConstants.MONEY_WITHDRAW_MESSAGE);
				Money amount = new Money();
				amount.setAmount(DataUtil.setImpassaMoney(paymentAccountForm
						.getWithdrawalAmount()));
				amount.setCurrency(CurrencyType.USD);
				try {

					/*
					 * Double balance = DataUtil
					 * .getImpassaMoney(G4GFinancialDelegator.getBalance(DataUtil.getUserDTO(request)
					 * 
					 * .getSessionId()).getBankBalance().getAmount()) +
					 * DataUtil.getImpassaMoney(G4GFinancialDelegator.getBalance(DataUtil.getUserDTO(request).getSessionId())
					 * .getWinningsBalance().getAmount());
					 * 
					 * G4GFinancialDelegator.withdrawal(DataUtil.getUserDTO(request).getSessionId(),
					 * amount, WithdrawalMethod
					 * 
					 */
					G4GFinancialDelegator.withdrawal(DataUtil.getUserDTO(
							request).getSessionId(), amount, WithdrawalMethod
							.fromValue(paymentAccountForm.getWithdrawMethod()));
					DataUtil.getUserDTO(request).setBalance(
							DataUtil.getUserDTO(request).getBalance()
									- paymentAccountForm.getWithdrawalAmount());
					CashConfirmNotification cashConfirmNotification = new CashConfirmNotification(
							Integer.parseInt(DataUtil.getUserId(request)),
							G4GConstants.ZERO, G4GConstants.DEBIT,
							paymentAccountForm.getWithdrawalAmount());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.FORPLAYER).append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getEmailaddress()).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).append(
									G4GConstants.SENDING_CASH_NOTIFICATION)
									.toString(), Level.INFO);
					NotificationServiceDelegator.sendNotification(
							cashConfirmNotification, request);
				} catch (InternalException_Exception e) {
					message = resource.getString(e.getMessage());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									e.getMessage()).toString(), Level.ERROR);
				} catch (InvalidSessionException_Exception e) {
					message = resource.getString(e.getMessage());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									e.getMessage()).toString(), Level.ERROR);
				} catch (LimitExceededException_Exception e) {
					message = resource.getString(e.getMessage());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									e.getMessage()).toString(), Level.ERROR);
				} catch (MoneyException_Exception e) {
					message = resource.getString(e.getMessage());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									e.getMessage()).toString(), Level.ERROR);
				} catch (Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									e.getMessage()).toString(), Level.ERROR);
				}
				request.getSession().setAttribute(
						G4GConstants.NOTIFICATIONMESSAGE, message);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.FORPLAYER).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getEmailaddress()).append(
								G4GConstants.DASHES).append(
								request.getSession().getId()).append(
								G4GConstants.FORWARD_SUCCESS).toString(),
				Level.INFO);
		return mapping.findForward(G4GConstants.SUCCESS);
	}
}
