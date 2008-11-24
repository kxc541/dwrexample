/**********************************************************
 * DataUtil.java
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

import com.g4g.dto.PlayerDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.Money;

/**
 * The Class DataUtil contains general data utilities.It contains all the common
 * utilities used through out the project. The class defines the required
 * dateformats. <br>
 * Formats the amount with decimal amount.For Ex.10.00<br>
 * Gets the siteId from current session.<br>
 * Splits the string by comma. <br>
 * Gets the id for challengeCardLink. <br>
 * Concatenates given string by comma. <br>
 * Decrypts the given data. <br>
 * Displays the given String. <br>
 * Encrypts the given dataValue. <br>
 * Gets base path of the application using request object. <br>
 * Various Date utility methods are defined. <br>
 * Matches the String with given pattern. <br>
 * Fives today's gmt format. <br>
 * Converts the date to GMT format. <br>
 * Gets the userDTO. <br>
 * Gives the playerId of the user for session is not null. <br>
 * Gives SessionId for current not null session. <br>
 * Converts the double amount to money object. Checks if the Url is valid or
 * not.<br>
 * returns string <br>
 * Checks if the given URL is available or not returns boolean <br>
 * Converts InputStram to string value. <br>
 * Formats the playForMoney amount to string format.
 *
 * @author Ankur
 */
public class DataUtil {

	/** The date format. */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			G4GConstants.DATE_DD_MM_YYYY);

	/** The day month format. */
	private static SimpleDateFormat dayMonthFormat = new SimpleDateFormat(
			G4GConstants.DATE_FORMAT);

	/**
	 * Formats the given double amount by .00 decimal points .
	 *
	 * @param amount
	 *            the amount to be formatted by decimals.
	 *
	 * @return the formatted string
	 *
	 * @author Jigar Mistry
	 * @author ankur
	 */
	public static String format(double amount) {
		DecimalFormat format = new DecimalFormat(G4GConstants.DECIMAL_FORMAT);
		FieldPosition f = new FieldPosition(G4GConstants.ZERO);
		StringBuffer s = new StringBuffer();
		return format.format(amount, s, f).toString();
	}

	/**
	 * the method getSiteId will give siteId from the session if it set else it
	 * will return default siteId of the WORLDGAMEING i.e. 'wg'.
	 *
	 * @param request
	 *            the request object to get session id from.
	 *
	 * @return the siteid
	 * @author Ankur
	 */
	public static String getSiteId(HttpServletRequest request) {
		if (request.getSession().getAttribute(G4GConstants.SITE_ID_ATTRIBUTE)
				.toString() != null) {
			return G4GConstants.DEFAULT_SITE_ID;
		} else {
			return G4GConstants.DEFAULT_SITE_ID;
		}
	}

	/**
	 * Splits the String by comma the string having comma with them will be
	 * seprated and add to list as list element.
	 *
	 * @param stringwithcomma
	 *            the stringwithcomma
	 *
	 * @return the list<string> sepreted by comma.
	 * @author Ankur
	 */
	public static List<String> breakString(String stringwithcomma) {
		StringTokenizer st = new StringTokenizer(stringwithcomma,
				G4GConstants.COMMA);
		List<String> list = new ArrayList<String>();
		while (st.hasMoreElements()) {
			list.add((String) st.nextElement());

		}
		return list;
	}

	/**
	 * Gets the twoPlayerMatchId from directedChallengeForm for challenge link.
	 *
	 * @param directedChallengeForm
	 *            the directedchallengeform to get the id from.
	 *
	 * @return the twoPlayerMatchId from directedChallengeForm
	 * @author Ankur
	 */
	public static String getChallengeLink(
			DirectedChallengeForm directedChallengeForm) {
		String challenge1 = null;
		try {
			challenge1 = String.valueOf(directedChallengeForm
					.getTwoplayermatchDTO().getId());
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
		return challenge1;
	}

	/**
	 * Splits the String by given symbol the string having given symbol with
	 * them will be seprated and add to list as list element.
	 *
	 * @param stringwithcomma
	 *            the stringwithcomma
	 * @param symbol
	 *            the symbol which is used as split characters.
	 *
	 * @return the list<string> sepreted by split character.
	 * @author Ankur
	 */
	public static List<String> breakString(String stringwithcomma, String symbol) {
		StringTokenizer st = new StringTokenizer(stringwithcomma, symbol);
		List<String> list = new ArrayList<String>();
		while (st.hasMoreElements()) {
			list.add((String) st.nextElement());
		}
		return list;
	}

	/**
	 * Concatanate strings in the string array with comma.
	 *
	 * @param stringArray
	 *            the string array of strings to be concatenated with comma.
	 *
	 * @return the concatenated string
	 * @author Ankur
	 */
	public static String concatanateStrings(String[] stringArray) {
		StringBuffer a = new StringBuffer();
		for (String element : stringArray) {
			a = a.append(G4GConstants.COMMA).append(element);
		}
		return a.toString();
	}

	/**
	 * Concatanate the two given strings with comma.
	 *
	 * @param string1
	 *            the string1
	 * @param string2
	 *            the string2
	 *
	 * @return the string - string1, string2 concatenated with comma.
	 * @author Ankur
	 */
	public static String concatanateTwoStrings(String string1, String string2) {
		return new StringBuffer(string1).append(G4GConstants.COMMA).append(
				string2).toString();
	}

	/**
	 * Decrypts the given data by basic text encryptor.
	 *
	 * @param data
	 *            the data to encrypt.
	 *
	 * @return the decrypted string.
	 * @author Jigar Mistry
	 */
	public static String decrypt(String data) throws EncryptionOperationNotPossibleException {
		String decrypted = G4GConstants.NONE;
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(G4GConstants.KEY);
		data = patternReplace(data, G4GConstants.GPSG, G4GConstants.PLUS);
		data = patternReplace(data, G4GConstants.GELG, G4GConstants.EQUALS);
		data = patternReplace(data, G4GConstants.GSLG, G4GConstants.SLASH);
		decrypted = encryptor.decrypt(data);
		return decrypted;
	}

	/**
	 * Gives String value if it is not null else gives blank.
	 *
	 * @param str
	 *            the string value to check for null.
	 *
	 * @return the string
	 * @author Ankur
	 */
	public static String displayString(String str) {
		return (str == null) ? G4GConstants.NONE : str;
	}

	/**
	 * Encrypts the given data by basic text encryptor.
	 *
	 * @param data
	 *            the data to encrypt.
	 *
	 * @return the encrypted string.
	 * @author Jigar Mistry
	 */
	public static String encrypt(String data) {
		String encrypted = G4GConstants.BLANK;
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(G4GConstants.KEY);
		encrypted = encryptor.encrypt(data);
		encrypted = patternReplace(encrypted,
				G4GConstants.ENCRYPTION_PATTERN_GPSG, G4GConstants.GPSG);
		encrypted = patternReplace(encrypted,
				G4GConstants.ENCRYPTION_PATTERN_GELG, G4GConstants.GELG);
		encrypted = patternReplace(encrypted,
				G4GConstants.ENCRYPTION_PATTERN_GSLG, G4GConstants.GSLG);
		return encrypted;
	}

	/**
	 * Gives the base path of the application.
	 *
	 * @param request
	 *            the request object to get path from the session.
	 *
	 * @return the base path of application.
	 * @author Ankur
	 */
	public static String getBasePath(HttpServletRequest request) {

		return new StringBuffer(request.getScheme()).append(G4GConstants.COLON)
				.append(G4GConstants.SLASH).append(G4GConstants.SLASH).append(
						request.getServerName()).append(G4GConstants.COLON)
				.append(request.getServerPort()).append(
						request.getContextPath()).append(G4GConstants.SLASH)
				.toString();
	}

	/**
	 * Gives the local base path of the application.
	 *
	 * @param request
	 *            the request object to get path from the session.
	 *
	 * @return the base path of application.
	 * @author Ankur
	 */
	public static String getLocalBasePath(HttpServletRequest request) {
		return new StringBuffer(G4GProperties.getProperty(PropertiesConstants.G4G_INTERNAL_BASE_URL)).append(
						request.getContextPath()).append(G4GConstants.SLASH)
				.toString();
	}

	/**
	 * The method getDate converts the given date to String in the 'dd/MM/yyyy'.
	 *
	 * @param d
	 *            the date value.
	 *
	 * @return the date in the string format.
	 * @author Ankur
	 */
	public static String getDate(Date d) {
		try {
			return dateFormat.format(d);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * The method addDate adds the number of days into given date.
	 *
	 * @param d
	 *            the date to which the days will days will be added.
	 * @param i
	 *            number of days to add.
	 *
	 * @return the date after adding the given days.
	 * @author Ankur
	 */
	public static Date addDate(Date d, int i) {
		long now = d.getTime();
		long oneday = G4GConstants.SIXTY * G4GConstants.SIXTY
				* G4GConstants.ONE_THOUSAND * G4GConstants.TWENTYFOUR_NUMBER
				* i;
		return new Date(now + oneday);

	}

	/**
	 * The method getDate gives the date from the Date date to given datFormat.
	 *
	 * @param date
	 *            the Date date to convert into given format.
	 * @param dateFormat
	 *            the format in which the given date will be converted.
	 *
	 * @return the date converted to dateFormat.
	 * @author Ankur
	 */
	public static String getDate(Date date, String dateFormat) {
		Format formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * The method getDate gives the date from the long date to given datFormat.
	 *
	 * @param lDate
	 *            the long date to convert into given format.
	 * @param dateFormat
	 *            the format in which the given date will be converted.
	 *
	 * @return the date converted to dateFormat.
	 * @author Ankur
	 */
	public static String getDate(long lDate, String dateFormat) {
		Date date = new Date(lDate);
		Format formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * The method getDate gives the date from the string in the 'dd/MM/yyyy'
	 * format
	 *
	 * @param dStr
	 *            the String to convert to date.
	 *
	 * @return the date in 'dd/MM/yyyy' format.
	 * @author Ankur
	 */
	public static Date getDate(String dStr) {
		Date date = null;
		try {
			date = dateFormat.parse(dStr);
		} catch (ParseException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}
		return date;
	}

	/**
	 * The method getDate gives the date from the string given in the given
	 * format specified in format parameter.
	 *
	 * @param dStr
	 *            the date in the String format.
	 * @param format
	 *            the format to convert the date to given format.
	 *
	 * @return the date in the given format.
	 * @author Ankur
	 */
	public static Date getDate(String dStr, String format) {
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormatLocal.parse(dStr);
		} catch (ParseException e) {
			date = null;
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}
		return date;
	}

	/**
	 * This method returns the string of the 'dd/MM' format
	 *
	 * @param date
	 *            the date value to get the month day
	 *
	 * @return the day month in String
	 * @author Ankur
	 */
	public static String getDayMonth(Date date) {
		try {
			return dayMonthFormat.format(date);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * The method getDouble converts String value to double value.
	 *
	 * @return the double value
	 * @author Ankur
	 * @param d
	 */
	public static double getDouble(String d) {
		try {
			return Double.valueOf(d);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return G4GConstants.ZERO;
		}
	}

	/**
	 * The method getInteger converts String value to Integer value.
	 *
	 * @return the Integer
	 * @author Ankur
	 * @param str
	 */
	public static int getInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return G4GConstants.ZERO;
		}
	}

	/**
	 * The method getLong converts String value to long value.
	 *
	 * @param l
	 *            the String value to convert to long value.
	 *
	 * @return the long
	 * @author Ankur
	 */
	public static long getLong(String l) {
		try {
			return Long.valueOf(l);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return G4GConstants.ZERO;
		}
	}

	/**
	 * The method getLongDate converts the given String date to date returns the
	 * long format date.
	 *
	 * @param d
	 *            the string date to convert to long.
	 *
	 * @return the long date
	 * @author Ankur
	 */
	public static Long getLongDate(String d) {
		return (d != null) ? getDate(d).getTime() : null;
	}

	/**
	 * The method getRandomPassword generates random password from its length.
	 *
	 * @param length -
	 *            the length of the password to generate random.
	 *
	 * @return the random password generated.
	 * @author Jigar Mistry
	 */
	public static String getRandomPassword(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		char[] chars = G4GConstants.GETRANDOMPASSWORD_CHAR.toCharArray();
		for (int i = G4GConstants.ZERO; i < length; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	/**
	 * Gets the session site id from the current session to string format.
	 *
	 * @param request
	 *            the request object to get current session.
	 *
	 * @return the site id for the current not null session.
	 * @author Ankur
	 */
	public static String getSessionSiteId(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(
				G4GConstants.SITE_ID_ATTRIBUTE);
	}

	/**
	 * Converts the given date to String with format of 'dd/MM/yyyy' value if
	 * not converted than returns null.
	 *
	 * @return the string - date converted to string.
	 * @author Ankur
	 * @param date
	 */
	public static String getString(Date date) {
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * Converts the given double value to String value if not converted than
	 * returns null.
	 *
	 * @return the string - double value converted to string.
	 * @author Ankur
	 * @param d
	 */
	public static String getString(double d) {
		try {
			return Double.toString(d);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * Converts the given int value to String value if not converted than
	 * returns null.
	 *
	 * @return the string - int value converted to string.
	 * @author Ankur
	 * @param i
	 */
	public static String getString(int i) {
		try {
			return Integer.toString(i);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * Converts the given long value to String value if not converted than
	 * returns null.
	 *
	 * @param l
	 *            the long value.
	 *
	 * @return the string - long value converted to string.
	 * @author Ankur
	 */
	public static String getString(long l) {
		try {
			return Long.toString(l);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
	}

	/**
	 * Gets the string.
	 *
	 * @param str
	 *            the str
	 *
	 * @return the string
	 */
	public static String getString(String str) {
		if (str == null || str.trim().length() == G4GConstants.ZERO) {
			return G4GConstants.NONE;
		} else {
			return str.trim();
		}
	}

	/**
	 * The method patternReplace replaces the text, matches the text for regular
	 * expression regex if matches than replaces by replace string.
	 *
	 * @param text
	 *            the text to be matched.
	 * @param regex
	 *            the regex is regular expression for matching with text.
	 * @param replaceString
	 *            the replacestring replaces the text if it matches the pattern.
	 *
	 * @return the string replaced by replacestring if matched else returns
	 *         text.
	 */
	private static String patternReplace(String text, String regex,
			String replaceString) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll(replaceString);
	}

	/**
	 * The method patternReplace replaces the text, matches the text for regular
	 * expression regex if matches than replaces by replace string for first
	 * occurance.
	 *
	 * @param text
	 *            the text to be matched.
	 * @param regex
	 *            the regex is regular expression for matching with text.
	 * @param replaceString
	 *            the replacestring replaces the text if it matches the pattern.
	 *
	 * @return the string replaced by replacestring if matched else returns
	 *         text.
	 */
	public static String patternReplaceFirst(String text, String regex,
			String replaceString) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceFirst(replaceString);
	}
	/**
	 * Gives today's date in gmt format.
	 *
	 * @return date today's GMT format date.
	 * @author Ankur
	 */
	public static Date todayGMT() {
		DateFormat format = new SimpleDateFormat();
		format.setTimeZone(TimeZone.getTimeZone(G4GConstants.TIMEZONE_FORMAT));
		Calendar today = Calendar.getInstance();
		int gmtoffset = today.get(Calendar.DST_OFFSET)
				+ today.get(Calendar.ZONE_OFFSET);

		// to convert to GMT time
		Date GMTDate = new Date(System.currentTimeMillis() - gmtoffset);

		try {
			return format.parse(format.format(GMTDate));
		} catch (ParseException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return GMTDate;
		}
	}

	/**
	 * Gives the date converted to gmt format as per the GMT selected by the
	 * current user.
	 *
	 * @param date
	 *            the date to be converted to GMT format.
	 *
	 * @return the date as per the GMT as per selected timezone by user.
	 * @author Ankur
	 */
	public static Date convertDateToGMT(Date date) {
		DateFormat df = DateFormat.getInstance();
		df.setTimeZone(TimeZone
				.getTimeZone(G4GConstants.COUNTRY_TIMEZONE_FORMAT));
		try {
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return null;
		}
		return date;
	}

	/**
	 * Return's userdto stored in session Where userDTO is set in the session,
	 * is not null.
	 *
	 * @param request
	 *            the request object to get the userDTO from current session.
	 *
	 * @return UserDTO from the session
	 *
	 * @author Ankur
	 */
	public static UserDTO getUserDTO(HttpServletRequest request) {
		if (request.getSession().getAttribute(G4GConstants.USERDTO) != null) {
			return (UserDTO) request.getSession().getAttribute(
					G4GConstants.USERDTO);
		}
		return null;
	}

	/**
	 * The method Gets the playerid of the user who is currently logged-in.
	 *
	 * @param request
	 *            the request object for getting userDTO.
	 *
	 * @return the player id of logged-in user.
	 * @author Ankur
	 */
	public static String getUserId(HttpServletRequest request) {
		UserDTO userDTO = getUserDTO(request);
		if (userDTO != null) {
			PlayerDTO playerDTO = userDTO.getPlayerDTO();
			return String.valueOf(playerDTO.getId());
		}
		return null;
	}

	/**
	 * @param request
	 * @return UserId Integer
	 */
	public static Integer getUserIdInteger(HttpServletRequest request) {
		UserDTO userDTO = getUserDTO(request);
		if (userDTO != null) {
			PlayerDTO playerDTO = userDTO.getPlayerDTO();
			return playerDTO.getId();
		}
		return null;
	}

	/**
	 * Returns sessionId of user set in session for current session which is not
	 * null.
	 *
	 * @param request
	 *            the request object of the current session.
	 *
	 * @return the session id of the current session.
	 *
	 * @author ankur
	 */
	public static String getSessionId(HttpServletRequest request) {
		UserDTO userDTO = getUserDTO(request);
		if (userDTO != null) {
			return userDTO.getSessionId();
		}
		return null;
	}

	/**
	 * The method getMoney returns money object of the impessa financial world
	 * gaming money object by converting the given double value to Money class
	 * object and setting amount attribute.
	 *
	 * @param money -
	 *            Double amount value to convert into the money object.
	 *
	 * @return the Money object
	 *
	 * @author Ankur
	 */
	public static Money getMoney(Double money) {
		Money moneymoney = new Money();
		moneymoney.setAmount(setImpassaMoney(money.longValue()));
		return moneymoney;
	}

	/**
	 * The method getURLContent returns String path if the given path is valid.
	 *
	 * @param path
	 *            the string value for path is checked for available location
	 *
	 * @return the uRL content gives string path if location is valid else
	 *         returns blank.
	 * @author Jigar Mistry
	 */
	public static String getURLContent(String path) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				DataUtil.class.getName() + G4GConstants.URL + path);
		HostnameVerifier hv = new HostnameVerifier()
	    {
			public boolean verify(String hostname, SSLSession session) {
	            return true;
			}
	    };

		InputStream inputStream = null;
		try {
			URL url = new URL(path);

			if(path.startsWith(G4GConstants.HTTPS)) {
				trustAllHttpsCertificates();
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				inputStream = conn.getInputStream();
			} else {
				URLConnection conn = url.openConnection();
				inputStream = conn.getInputStream();
			}
		} catch (MalformedURLException malformedURLException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					malformedURLException);
		} catch (IOException ioException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					ioException);
		} catch (Exception exception) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					exception);
		}

		return inputStream != null ? convertInputStreamToString(inputStream)
				: G4GConstants.NONE;
	}

	/**
	 * This method validates all the HTTPS certificates.
	 *
	 * @throws Exception
	 */
	private static void trustAllHttpsCertificates() throws Exception
    {

        //  Create a trust manager that does not validate certificate chains:

        javax.net.ssl.TrustManager[] trustAllCerts =

        new javax.net.ssl.TrustManager[1];

        javax.net.ssl.TrustManager tm = new miTM();

        trustAllCerts[0] = tm;

        javax.net.ssl.SSLContext sc =

        javax.net.ssl.SSLContext.getInstance(G4GConstants.SSL);

        sc.init(null, trustAllCerts, null);

        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(

        sc.getSocketFactory());

    }

    public static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager
    {
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs)
        {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs)
        {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException
        {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException
        {
            return;
        }
    }

	/**
	 * The method isURLAvailable Checks if the given path contains the pfile
	 * located at the path given in url. if it has the file it returns true else
	 * returns false.
	 *
	 * @param path
	 *            the path which contains the url to be checked for the file
	 *            location given.
	 *
	 * @return true, if is given url contains the file given in the url.
	 * @author Pratik
	 */
	public static boolean isURLAvailable(String path) {
		try {
			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			return conn != null;
		} catch (MalformedURLException malformedURLException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					malformedURLException);
			return false;
		} catch (IOException ioException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					ioException);
			return false;
		}
	}

	/**
	 * The method convertInputStreamToString Converts inputStream to string.
	 *
	 * @param inputStream
	 *            the inputstream that is to be converted in string.
	 *
	 * @return the string of the inputstream.
	 * @author Jigar Mistry
	 */
	private static String convertInputStreamToString(InputStream inputStream) {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		StringBuffer buffer = new StringBuffer();

		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException ioException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					ioException);
		}
		return buffer.toString();
	}

	/**
	 * Gives the formatted amount string user has selected to play for Gets the
	 * play for amount.
	 *
	 * @param averageamount
	 *            the averageamount is the value which will be formatted as
	 *            condition gets satisfied.
	 *
	 * @return the play for amount in string format
	 * @author Jigar Mistry
	 */
	public static String getPlayForAmount(int averageamount) {
		String playForRange = G4GConstants.NONE;
		if (averageamount <= G4GConstants.FIVE_NUMBER) {
			playForRange = G4GConstants.PLAYFOR_05_10;
		} else if (averageamount <= G4GConstants.FIFTEEN_NUMBER) {
			playForRange = G4GConstants.PLAYFOR_10_15;
		} else if (averageamount <= G4GConstants.FOURTY_NUMBER) {
			playForRange = G4GConstants.PLAYFOR_25_50;
		} else if (averageamount <= G4GConstants.SIXTY) {
			playForRange = G4GConstants.PLAYFOR_50_100;
		} else if (averageamount >= G4GConstants.HUNDRED) {
			playForRange = G4GConstants.PLAYFOR_100_PLUS;
		}
		return playForRange;
	}

	/**
	 * @param money
	 * @return Long.valueOf(money) * 100
	 */
	public static Long setImpassaMoney(String money) {
		return Long.valueOf(money) * G4GConstants.HUNDRED;
	}

	/**
	 * @param money
	 * @return Long money * 100
	 */
	public static Long setImpassaMoney(Long money) {
		return money * G4GConstants.HUNDRED;
	}

	/**
	 * @param money
	 * @return Double money / 100D
	 */
	public static Double getImpassaMoney(Long money) {
		return money / G4GConstants.HUNDRED_DOUBLE;
	}

	/**
	 * @return current method name
	 */
	public static String getCurrentMethod() {
		// $ANALYSIS-IGNORE
		return new Throwable().fillInStackTrace().getStackTrace()[G4GConstants.ONE_NUMBER]
				.getMethodName();
	}

	/**
	 * @return name of calling method
	 */
	public static String getCallingMethod() {
		// $ANALYSIS-IGNORE
		return new Throwable().fillInStackTrace().getStackTrace()[G4GConstants.TWO_NUMBER]
				.getMethodName();
	}

	/**
	 * @return name of calling method
	 */
	public static String getCallingMethodWithClassForSecurityTrap() {
		// $ANALYSIS-IGNORE
		Throwable th = new Throwable().fillInStackTrace();
		 StackTraceElement toReturn =  th.getStackTrace()[G4GConstants.TWO_NUMBER];
		 return toReturn.getFileName()+G4GConstants.COLON_WITH_SPACES+toReturn.getMethodName() ;
	}
	/**
	 * Get the content of the file by passing the path of the file.
	 *
	 * @param filePath
	 * 			Path of the file from where method reads the content.
	 *
	 * @return String
	 * 			Returns the content in the file in String.
	 */
	public static String readFileContent(String filePath) {
		int readChar;
		StringBuffer strContent = new StringBuffer();

		DataInputStream dataInputStream = null;

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			dataInputStream = new DataInputStream(bis);

			while ((readChar = fis.read()) != -1) {
				strContent.append((char) readChar);
			}

		} catch (IOException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					e, Level.ERROR);

		} finally {
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
							e, Level.ERROR);
				}
			}
		}

		return strContent.toString();
	}
}
