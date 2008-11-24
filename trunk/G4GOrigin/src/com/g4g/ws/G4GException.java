/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

// TODO: Auto-generated Javadoc
/**
 * To catch all the exception thrown by LiquidPayment system and convert it into
 * single exception called G4GException.
 * 
 * This class will be removed once we implement all the exception.
 * 
 * @author Jigar Mistry
 */

public class G4GException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which calls the super class.
	 * 
	 * @param exception
	 *            the exception
	 */
	public G4GException(Exception exception) {
		super(exception);
	}

}
