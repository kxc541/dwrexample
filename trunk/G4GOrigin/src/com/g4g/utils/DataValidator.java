/**********************************************************
 * HibernateSessionFactory.java
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The class DataValidator have general validation methods those are used
 * through out project to validate data. It validates it given string returns
 * true for floats values, integer values, null values, or it contains dollar as
 * zero.
 *
 * @author Ankur
 */
public class DataValidator {

	/**
	 * The method isFloat returns false if string entered is not Float. It
	 * parses the string value float and checks if it is not true it will
	 * generate an exception and returns true.
	 *
	 * @param str
	 *            the string to check is float or not.
	 *
	 * @return true, if checks is float else false
	 */
	public static boolean isFloat(String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch (NumberFormatException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return false;
		}
	}

	/**
	 * Return false if given String value is not Integer value else returns
	 * true.
	 *
	 * @param str
	 *            the string value that is checked for integer value.
	 *
	 * @return true, if checks if is integer else if it throws exception it
	 *         returns false.
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return false;
		}
	}

	/**
	 * Return true if String entered is null and if it has some value stored in
	 * it it gives false.
	 *
	 * @param str
	 *            the string value that is checked for null value.
	 *
	 * @return true if string is null else false if it has value.
	 */
	public static boolean isNull(String str) {
		return str == null || str.length() == G4GConstants.ZERO;
	}

	/**
	 * dollar should be of type String with value of type Double This methods
	 * returns false if minimum value is 5$ and greater than 1000$ else if the
	 * value is not between 5$ - 100$ it will return true.
	 *
	 * @param dollar
	 *            the dollar value which is checked for zero.
	 *
	 * @return true, if checks if is dollar zero or <$5 || >$1000, if the dollar
	 *         value is between $5 to $1000 it returns true.
	 */
	public static boolean isDollarZero(String dollar) throws NumberFormatException {
		if (G4GConstants.$00.equals(dollar) || DataValidator.isNull(dollar)
				|| Double.parseDouble(dollar) == G4GConstants.ZERO) {
			return true;
		} else {
			Double money = Double.parseDouble(dollar);
			return !(money >= G4GConstants.FIVE_NUMBER && money <= G4GConstants.ONE_THOUSAND);
		}
	}

	/**
	 * Checks whether user had enter correct date that should be in that year
	 *
	 * @param date
	 * @param format
	 * @return true / false
	 */
	public static boolean isValidDateStr(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			sdf.parse(date);
		} catch (ParseException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return false;
		} catch (IllegalArgumentException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return false;
		}
		return true;
	}
}