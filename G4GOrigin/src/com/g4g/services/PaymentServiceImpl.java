/**********************************************************
 * className.java : 
 *
 * Created by 			
 * Last modified Date: 6 Jun .08 by Punam
 * Revision: 0.1
 * Version : 0.3.4076
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.services;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.MONEY_DEPOSITED_VIA_CREDITCARD;
import static com.g4g.utils.G4GConstants.PAYMENT_ACCOUNT_CREATED;
import static com.g4g.utils.G4GConstants.STARTED;

import org.apache.log4j.Level;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.CountryType;
import com.impessa.worldgaming.client.CreditCardPaymentAccount;
import com.impessa.worldgaming.client.CreditCardPaymentRequest;
import com.impessa.worldgaming.client.CreditCardPaymentResponse;
import com.impessa.worldgaming.client.CreditCardType;
import com.impessa.worldgaming.client.CurrencyType;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidPaymentException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.LimitExceededException_Exception;
import com.impessa.worldgaming.client.LiquidPayment;
import com.impessa.worldgaming.client.LiquidPaymentService;
import com.impessa.worldgaming.client.Money;

/**
 * Class to implement methods of PaymentService interface.
 * 
 * @author pratik
 * 
 */

public class PaymentServiceImpl implements PaymentService {

	/**
	 * @see com.g4g.services.PaymentService#createNewPaymentAccount(com.g4g.dto.UserDTO,
	 *      int, java.lang.String,
	 *      com.impessa.worldgaming.client.CreditCardType, java.lang.String,
	 *      int, int, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      com.impessa.worldgaming.client.CountryType, java.lang.String)
	 */
	public void createNewPaymentAccount(UserDTO userDTO, int accountTypeId,
			String name, CreditCardType creditCardType, String cardNumber,
			int expiryMonth, int expiryYear, String firstName, String lastName,
			String streetAddress, String city, String state,
			CountryType countryType, String zip) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		LiquidPaymentService liquidPaymentService = new LiquidPaymentService();
		LiquidPayment liquidPayment = liquidPaymentService
				.getLiquidPaymentPort();
		String sessionId = null;

		sessionId = userDTO.getSessionId();
		// creditcard = 1 , paypal = 2
		if (accountTypeId == G4GConstants.ONE_NUMBER) {
			CreditCardPaymentAccount creditCardPaymentAccount = new CreditCardPaymentAccount();
			creditCardPaymentAccount.setName(name);
			creditCardPaymentAccount.setType(creditCardType);
			creditCardPaymentAccount.setNumber(cardNumber);
			creditCardPaymentAccount.setExpiryMonth(expiryMonth);
			creditCardPaymentAccount.setExpiryYear(expiryYear);
			creditCardPaymentAccount.setOwnerName(firstName);
			creditCardPaymentAccount.setOwnerSurname(lastName);
			creditCardPaymentAccount.setOwnerStreet(streetAddress);
			creditCardPaymentAccount.setOwnerState(state);
			creditCardPaymentAccount.setOwnerCountry(countryType);
			creditCardPaymentAccount.setOwnerZip(zip);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( PAYMENT_ACCOUNT_CREATED).toString(), Level.INFO);
			try {
				G4GFinancialDelegator.registerPaymentAccount(sessionId,
						creditCardPaymentAccount);
			} catch (InternalException_Exception iee) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
								).append( CALLINGMETHOD
								).append( DataUtil.getCallingMethod()
								).append( CURRENTMETHOD
								).append( DataUtil.getCurrentMethod() ).append( DASHES
								).append( iee.getMessage()).toString(), Level.ERROR);
			} catch (InvalidPaymentException_Exception ipee) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
								).append( CALLINGMETHOD
								).append( DataUtil.getCallingMethod()
								).append( CURRENTMETHOD
								).append( DataUtil.getCurrentMethod() ).append( DASHES
								).append( ipee.getMessage()).toString(), Level.ERROR);
			} catch (InvalidSessionException_Exception isee) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
								).append( CALLINGMETHOD
								).append( DataUtil.getCallingMethod()
								).append( CURRENTMETHOD
								).append( DataUtil.getCurrentMethod() ).append( DASHES
								).append( isee.getMessage()).toString(), Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
	}

	/**
	 * @see com.g4g.services.PaymentService#depositViaCreditCard(com.g4g.dto.UserDTO,
	 *      long, java.lang.String, long)
	 */
	public String depositViaCreditCard(UserDTO userDTO, long amount, String ip,
			long creditCardAccountId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( STARTED).toString(),
				Level.INFO);
		LiquidPaymentService liquidPaymentService = new LiquidPaymentService();
		LiquidPayment liquidPayment = liquidPaymentService
				.getLiquidPaymentPort();

		String sessionId = null;
		sessionId = userDTO.getSessionId();

		Money money = new Money();
		money.setAmount(DataUtil.setImpassaMoney(amount));
		money.setCurrency(CurrencyType.USD);

		CreditCardPaymentRequest paymentRequest = new CreditCardPaymentRequest();
		paymentRequest.setIpAddress(ip);
		paymentRequest.setOrderTotal(money);
		paymentRequest.setAccountId(creditCardAccountId);

		CreditCardPaymentResponse paymentResponse = null;

		try {
			paymentResponse = (CreditCardPaymentResponse) liquidPayment
					.deposit(sessionId, paymentRequest);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (this.getClass().getName() ).append( DASHES ).append( MONEY_DEPOSITED_VIA_CREDITCARD).toString(), Level.INFO);
		} catch (InternalException_Exception iee) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( iee.getMessage()).toString(), Level.ERROR);
		} catch (InvalidPaymentException_Exception ipee) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( ipee.getMessage()).toString(), Level.ERROR);
		} catch (LimitExceededException_Exception leee) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( leee.getMessage()).toString(), Level.ERROR);
		} catch (InvalidSessionException_Exception isee) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
							).append( CALLINGMETHOD
							).append( DataUtil.getCallingMethod()
							).append( CURRENTMETHOD
							).append( DataUtil.getCurrentMethod() ).append( DASHES
							).append( isee.getMessage()).toString(), Level.ERROR);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (this.getClass().getName() ).append( COLON_WITH_SPACES
						).append( CALLINGMETHOD
						).append( DataUtil.getCallingMethod()
						).append( CURRENTMETHOD
						).append( DataUtil.getCurrentMethod() ).append( DASHES
						).append( ENDED).toString(), Level.INFO);
		return paymentResponse.getTransactionId();
	}

	/**
	 * @see com.g4g.services.PaymentService#depositViaPaypalExpress(com.g4g.dto.UserDTO,
	 *      long)
	 */
	public void depositViaPaypalExpress(UserDTO userDTO, long amount) {

	}

}
