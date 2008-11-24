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

import com.g4g.dto.UserDTO;
import com.impessa.worldgaming.client.CountryType;
import com.impessa.worldgaming.client.CreditCardType;

/**
 * This interface defines the methods to work with Financial Service's payment system.
 * 
 * @author pratik
 */

public interface PaymentService {
	/**
	 * Function creates a new payment account for user.
	 * @param userDTO - pass the logged in user's UserDTO.
	 * @param accountTypeId
	 * @param name
	 * @param creditCardType
	 * @param cardNumber
	 * @param expiryMonth
	 * @param expiryYear
	 * @param firstName
	 * @param lastName
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param countryType
	 * @param zip
	 */
	public void createNewPaymentAccount(UserDTO userDTO, int accountTypeId,
			String name, CreditCardType creditCardType, String cardNumber,
			int expiryMonth, int expiryYear, String firstName, String lastName,
			String streetAddress, String city, String state,
			CountryType countryType, String zip);
	
	/**
	 * Method to deposit money in user's PaymentAccount via CreditCard. Returns
	 * transactionId
	 * @param userDTO - pass the logged in user's UserDTO.
	 * @param amount - pass amount to deposit in user's account.
	 * @param ip - pass the IP user come from. 
	 * @param creditCardAccountId - pass the credit card account id to deposit with.
	 * @return
	 */
	public String depositViaCreditCard(UserDTO userDTO, long amount, String ip, long creditCardAccountId);
	/**
	 * Method to deposit money in user's PaymentAccount via paypal.
	 * @param userDTO - pass the logged in user's UserDTO.
	 * @param amount - pass amount to deposit in user's account.
	 */
	public void depositViaPaypalExpress(UserDTO userDTO, long amount);
	
}
