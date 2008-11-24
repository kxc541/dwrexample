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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.notification.CashConfirmNotification;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.SessionUtil;
import com.impessa.worldgaming.client.Balance;
import com.impessa.worldgaming.client.CurrencyType;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidPaymentException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.LimitExceededException_Exception;
import com.impessa.worldgaming.client.Money;
import com.impessa.worldgaming.client.PaymentAccount;
import com.impessa.worldgaming.client.PpecDetailsRequest;
import com.impessa.worldgaming.client.PpecDetailsResponse;
import com.impessa.worldgaming.client.PpecPayRequest;
import com.impessa.worldgaming.client.PpecPayResponse;
import com.impessa.worldgaming.client.Transaction;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;
import com.impessa.worldgaming.client.WithdrawalMethod;

/**
 * XDoclet definition:
 * 
 * @struts.action path="/displayCashier" name="cashierForm" scope="request"
 * @struts.action-forward name="success" path="/WebContent/cashierPage.jsp"
 * @author Punam
 */
public class CashierAction extends BaseAction {
	/**
	 * @author Punam Used For Logics to perform while displaying cashierPage.jsp
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
								request.getSession().getId()).toString(),
				Level.INFO);
		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.COLON_WITH_SPACES).append(G4GConstants.FORPLAYER)
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getEmailaddress()).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).append(
									G4GConstants.USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		try {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(G4GConstants.UPDATE_BALANCE).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.INFO);
			SessionUtil.updateUserbalance(request);
		} catch (InternalException_Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(e.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		} catch (InvalidSessionException_Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(exception.getMessage())
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(exception.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		} catch (UserNotFoundException_Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(exception.getMessage())
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(exception.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		}
		if (request.getParameter(G4GConstants.PAYMENT_CONFIRM) == null
				&& request.getParameter(G4GConstants.TOKEN) != null
				&& request.getParameter(G4GConstants.PAYMENT_PAYERID) != null) {
			String message = G4GConstants.NONE;
			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).append(
								G4GConstants.CHECK_FOR).append(
								G4GConstants.PAYMENT_CONFIRM).toString(),
						Level.ERROR);
				PpecDetailsRequest ppecDetailsRequest = new PpecDetailsRequest();
				ppecDetailsRequest.setAccountId(Long.valueOf(request
						.getSession().getAttribute(
								G4GConstants.PAYMENTACCOUNTID).toString()));
				Money money = new Money();
				money.setAmount(DataUtil.setImpassaMoney(request.getSession()
						.getAttribute(G4GConstants.AMOUNT1).toString()));
				money.setCurrency(CurrencyType.USD);
				ppecDetailsRequest.setOrderTotal(money);
				ppecDetailsRequest.setToken(request
						.getParameter(G4GConstants.TOKEN));

				PpecDetailsResponse ppecDetailsResponse;
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
								G4GConstants.DEPOSITING).append(
								request.getSession().getId()).toString(),
						Level.INFO);

				ppecDetailsResponse = (PpecDetailsResponse) G4GFinancialDelegator
						.deposit(DataUtil.getSessionId(request),
								ppecDetailsRequest);

				request.getSession().setAttribute(G4GConstants.PAYMENT_TOKEN,
						request.getParameter(G4GConstants.TOKEN));
				request.getSession().setAttribute(G4GConstants.PAYMENT_PAYERID,
						request.getParameter(G4GConstants.PAYMENT_PAYERID));
				request.getSession().setAttribute(
						G4GConstants.PAYPAL_DETAIL_RESPONSE,
						ppecDetailsResponse);
			} catch (InternalException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (InvalidPaymentException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (InvalidSessionException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (LimitExceededException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			}

			request.getSession().setAttribute(G4GConstants.NOTIFICATIONMESSAGE,
					message);
		}
		if (request.getParameter(G4GConstants.PAYMENT_CONFIRM) != null) {
			String message = G4GConstants.NONE;
			try {
				PpecPayRequest ppecPayRequest = new PpecPayRequest();
				ppecPayRequest.setAccountId(Long
						.valueOf(request.getSession().getAttribute(
								G4GConstants.PAYMENTACCOUNTID).toString()));
				Money money = new Money();
				money.setAmount(DataUtil.setImpassaMoney(request.getSession()
						.getAttribute(G4GConstants.AMOUNT1).toString()));
				money.setCurrency(CurrencyType.USD);
				ppecPayRequest.setOrderTotal(money);
				ppecPayRequest.setPayerId(request
						.getParameter(G4GConstants.PAYMENT_PAYERID));
				ppecPayRequest.setToken(request
						.getParameter(G4GConstants.TOKEN));
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
								G4GConstants.DEPOSITING).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				PpecPayResponse ppecPayResponse = (PpecPayResponse) G4GFinancialDelegator
						.deposit(DataUtil.getSessionId(request), ppecPayRequest);

				request.getSession().removeAttribute(G4GConstants.AMOUNT1);
				request.getSession().removeAttribute(
						G4GConstants.PAYMENTACCOUNTID);
				request.getSession().setAttribute(G4GConstants.PAYMENT_STATUS,
						ppecPayResponse.getPaymentStatus());

				SessionUtil.updateUserbalance(request);

				CashConfirmNotification cashConfirmNotification = new CashConfirmNotification(
						Integer.parseInt(DataUtil.getUserId(request)),
						G4GConstants.ZERO, G4GConstants.CREDIT, money
								.getAmount() / 100);
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
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (InvalidPaymentException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (InvalidSessionException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (LimitExceededException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
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
				message = e.getMessage();
			} catch (UserNotFoundException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
			} catch (Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
			}

			request.getSession().setAttribute(G4GConstants.NOTIFICATIONMESSAGE,
					message);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.DISPLAY_STARTS).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(
				G4GConstants.USERDTO);
		String sessionId = G4GConstants.BLANK;
		if (userDTO != null) {
			sessionId = userDTO.getSessionId();
		} else {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		Double totalBalance = G4GConstants.ZERO_DOUBLE;
		List<PaymentAccount> depositAccountList = null;
		List<WithdrawalMethod> withdrawalAccountList = null;
		List<Transaction> transactionList = null;

		try {
			/* Total balance of WG User. */
			Balance balance = G4GFinancialDelegator.getBalance(sessionId);
			totalBalance = DataUtil.getImpassaMoney(balance.getBankBalance()
					.getAmount())
					+ DataUtil.getImpassaMoney(balance.getWinningsBalance()
							.getAmount());

			/* List of paymentAccount and withdrawal methods */
			depositAccountList = G4GFinancialDelegator.getPaymentAccounts(
					sessionId, true);
			withdrawalAccountList = G4GFinancialDelegator
					.getWithdrawalMethods(sessionId);
			Calendar calendar = new GregorianCalendar();
			Calendar now = new GregorianCalendar();
			calendar.setTime(DataUtil.getUserDTO(request).getPlayerDTO()
					.getCreationdate());

			transactionList = G4GFinancialDelegator.statement(sessionId, null,
					calendar.getTimeInMillis(), now.getTimeInMillis());
		} catch (InternalException_Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(e.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();

		} catch (InvalidSessionException_Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(e.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
		}

		request.setAttribute(G4GConstants.BALANCE, totalBalance);
		request.setAttribute(G4GConstants.DEPOSITACCOUNTLIST,
				depositAccountList);
		request.setAttribute(G4GConstants.WITHDRAWALACCOUNTLIST,
				withdrawalAccountList);
		request.setAttribute(G4GConstants.TRANSACTIONLIST, transactionList);
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * @struts.form-field name="submit"
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.SUBMIT_STARTS).toString());
		return mapping.findForward(G4GConstants.SAVE);
	}

}
