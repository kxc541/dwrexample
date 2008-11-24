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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * The Class DateUtil for getting date according to GMT timezone of the user who
 * is currently logged in. For Ex: if the User has selected 'La Pazas' he his
 * timezone in his profile the date will be displayed according to GMT of 'La
 * Pazas'.
 *
 * @author Jigar Mistry
 */
public class DateUtil {

	/**
	 * The getDateOfTimeZone method returns the date according to GMT timezone
	 * of the user who is currently logged in.
	 *
	 * @param date
	 *            the date which is needed to converted in GMT format.
	 * @param offset
	 *            the offset for the timezone user has selected in his profile.
	 *
	 * @return the date of GMT format timezone where the user belongs to.
	 * @throws NullPointerException
	 */
	public static Date getDateOfTimeZone(Date date, int offset) throws NullPointerException {
		long longDate = date.getTime() + offset * G4GConstants.SIXTY_NUMBER * G4GConstants.ONE_THOUSAND;
		return new Date(longDate);
	}

	/**
	 * @param date
	 * @return Date
	 */
	@SuppressWarnings(G4GConstants.DEPRECATION)
	public static Date getOneHourEarlyDate(Date date) {
		Date newDate = new Date(date.getTime());
		int time;
		try {
		time = Integer.parseInt(G4GProperties.getProperty(PropertiesConstants.G4G_CANCEL_MATCH_TIME));
		}catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			time = G4GConstants.CANCEL_MATCH_TIME;
		}
		if(time<G4GConstants.TEN_NUMBER || time>G4GConstants.ONE_HUNDREAD_TWENTY){
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, G4GConstants.CANCEL_MATCH_TIME_RANGE);
			time=G4GConstants.CANCEL_MATCH_TIME;
		}
		newDate.setMinutes(date.getMinutes()-time);
		return newDate;
	}

	/**
	 * Converts EST date to GMT
	 *
	 * @param date
	 * @return date
	 */
	public static Date ConvertESTtoGMT(Date date) {
		if (date == null) {
			return null;
		} else //$ANALYSIS-IGNORE
		{

				}
		final int ESToffset = G4GConstants.THREEHUNDRED_NEGATIVE;
		return getDateOfTimeZone(date, ESToffset);
	}

	/**
	 * Fri, May 09, 2008 at 01:00 AM 09/05/2008 at 1:00 AM This is the sample
	 * date to use it Check "at" in the format
	 *
	 * @param date
	 *
	 * @param offset
	 * @return Date
	 * @throws NullPointerException
	 */
	public static Date getDateOfTimeZone(String date, int offset) throws NullPointerException {
		long longDate = G4GConstants.ZERO_LONG;
		try {
			longDate = DataUtil.getDate(date,
					G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A).getTime()
					+ offset * G4GConstants.SIXTY * G4GConstants.ONE_THOUSAND;
		} catch (NullPointerException e) {
			longDate = DataUtil.getDate(date,
					G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A).getTime()
					+ offset * G4GConstants.SIXTY * G4GConstants.ONE_THOUSAND;
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}
		return new Date(longDate);
	}

	/**
	 * This method returns the age on the basis of parameter passed to the method.
	 *
	 * @param year
	 * @param month
	 * @param date
	 * @return age
	 */
	public static int age(int year, int month, int date) {
		Calendar cal = new GregorianCalendar(year, month, date);
		Calendar now = new GregorianCalendar();
		int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
				|| (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal
						.get(Calendar.DAY_OF_MONTH) > now
						.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}
		return age;
	}
}
