/**********************************************************
 * AuditUtil.java 
 *
 * Created by Rakesh Ray
 * Last modified 17 apr .08 Author: Punam
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import static com.g4g.utils.G4GConstants.ERROR_LOGGERINTI;
import static com.g4g.utils.G4GConstants.ERROR_TXT;
import static com.g4g.utils.G4GConstants.File_Type;
import static com.g4g.utils.G4GConstants.FINANCIAL_LOGGERINTI;
import static com.g4g.utils.G4GConstants.FINANCIAL_TXT;
import static com.g4g.utils.G4GConstants.HIBERNET_LOGGERINTI;
import static com.g4g.utils.G4GConstants.Hibernet_txt;
import static com.g4g.utils.G4GConstants.LOG_DIR;
import static com.g4g.utils.G4GConstants.NOT_FOUND;
import static com.g4g.utils.G4GConstants.SECURITY_LOGGERINTI;
import static com.g4g.utils.G4GConstants.SESSION_LOGGERINTI;
import static com.g4g.utils.G4GConstants.SESSION_TXT;
import static com.g4g.utils.G4GConstants.Security_txt;
import static com.g4g.utils.G4GConstants.ZERO;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;

import com.cipl.services.logging.CIPLLogger;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * The AuditUtil Class to handle loggers. It sets the log level error-filename,
 * log filename dynamically. It provides a simple but sophisticated logging
 * utility that anyone can use
 * 
 * @author Rakesh Ray
 */
public class AuditUtil {

	private static final String CONSTANT_CONDITIONS = "ConstantConditions"; //$NON-NLS-1$

	private static final String FOR_G4G_SESSION_MESSAGES = "--- For G4G Session Messages - Check file: "; //$NON-NLS-1$

	private static final String FOR_G4G_SECURITY_MESSAGES = "--- For G4G Security Messages - Check file: "; //$NON-NLS-1$
	private static final String FOR_G4G_DEBUG_MESSAGES = "--- For G4G Debug Messages - Check file: "; //$NON-NLS-1$

	private static final String FOR_G4G_ERRORS = "--- For G4G Errors - Check file: "; //$NON-NLS-1$

	private static final String FOR_G4G_HIBERNATE_MESSAGES = "--- For G4G Hibernate Messages - Check file: "; //$NON-NLS-1$

	private static final String LOG_DIRECTORY = "--- Log Directory : "; //$NON-NLS-1$

	private static final String G4GStARTED = "----	G4G started 	---"; //$NON-NLS-1$

	/** The instance of AuditUtil is initialized to null. */
	private static AuditUtil instance = null;

	/** The filemaps HashMap is initialized by HashMap<String, CIPLLogger>. */
	private static HashMap<String, CIPLLogger> fileMaps = new HashMap<String, CIPLLogger>();

	/** The Constant FILE_TYPE_G4G is assigned FILE_G4G. */
	public final static String FILE_TYPE_G4G = "FILE_G4G";

	/** The Constant FILE_TYPE_G4G_FINANCIAL is assigned FILE_G4G_FINANCIAL. */
	public final static String FILE_TYPE_G4G_FINANCIAL = "FILE_G4G_FINANCIAL";

	/** The Constant FILE_TYPE_SESSION is assigned FILE_SESSION. */
	public final static String FILE_TYPE_SESSION = "FILE_SESSION";

	/** The Constant FILE_TYPE_HIBERNATE is assigned FILE_HIBERNATE. */
	public final static String FILE_TYPE_HIBERNATE = "FILE_HIBERNATE";

	/** The Constant FILE_TYPE_HIBERNATE is assigned FILE_HIBERNATE. */
	public final static String FILE_TYPE_SECURITY_AUDIT = "FILE_SECURITY_AUDIT";
	/** The Constant NONE. */
	public final static String NONE = "";

	/**
	 * Gets the single instance of AuditUtil.
	 * 
	 * @return single instance of AuditUtil
	 */
	public static AuditUtil getInstance() {
		if (instance == null) {
			instance = new AuditUtil();
			init();
		}
		return instance;
	}

	/**
	 * The method will see in which file to put log information. If no file is
	 * specified it will create an error.txt and gets the level of the error
	 * displays the messages accordingly in the errorfile. If the logging
	 * information are for financial then all the information is stored with in
	 * financial.txt file with specifed leve. else it is stored in sessionLog
	 * for specified level. Levels are get from g4gproperties file.
	 */
	@SuppressWarnings( { CONSTANT_CONDITIONS })
	private static void init() {

		String securityFileName = G4GProperties
		.getProperty(PropertiesConstants.LOG_G4G_SECURITY);
		if (securityFileName == null
						|| securityFileName.trim().length() == ZERO) {
			securityFileName = Security_txt;
				}
		{
			Level securityAuditLogLevel = Level.ERROR;
			try {
				String securityLogLevelStr = G4GProperties
						.getProperty(PropertiesConstants.LOG_G4G_SECURITY_LEVEL);
				securityAuditLogLevel = Level.toLevel(securityLogLevelStr);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}CIPLLogger error = new CIPLLogger(FILE_TYPE_HIBERNATE,
							securityFileName, securityAuditLogLevel);
			fileMaps.put(FILE_TYPE_SECURITY_AUDIT, error);
			instance.writeLog(FILE_TYPE_SECURITY_AUDIT, NONE);
			instance.writeLog(FILE_TYPE_SECURITY_AUDIT, SECURITY_LOGGERINTI
					+ error.getLevel());
		}
		String hibernateFileName = G4GProperties
				.getProperty(PropertiesConstants.LOG_G4G_HIBERNATE);
		if (hibernateFileName == null
				|| hibernateFileName.trim().length() == ZERO) {
			hibernateFileName = Hibernet_txt;
		}
		{
			Level hibernateLogLevel = Level.INFO;
			try {
				String hibernateLogLevelStr = G4GProperties
						.getProperty(PropertiesConstants.LOG_G4G_HIBERNATE_LEVEL);
				hibernateLogLevel = Level.toLevel(hibernateLogLevelStr);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}
			CIPLLogger error = new CIPLLogger(FILE_TYPE_HIBERNATE,
					hibernateFileName, hibernateLogLevel);
			fileMaps.put(FILE_TYPE_HIBERNATE, error);
			instance.writeLog(FILE_TYPE_HIBERNATE, NONE);
			instance.writeLog(FILE_TYPE_HIBERNATE, HIBERNET_LOGGERINTI
					+ error.getLevel());
		}

		String errorFileName = G4GProperties
				.getProperty(PropertiesConstants.LOG_G4G);
		if (errorFileName == null || errorFileName.trim().length() == ZERO) {
			errorFileName = ERROR_TXT;
		}
		{
			Level errorLevel = Level.ERROR;
			try {
				String errorLevelStr = G4GProperties
						.getProperty(PropertiesConstants.LOG_G4G_LEVEL);
				errorLevel = Level.toLevel(errorLevelStr);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}
			CIPLLogger error = new CIPLLogger(FILE_TYPE_G4G, errorFileName,
					errorLevel);
			fileMaps.put(FILE_TYPE_G4G, error);
			instance.writeLog(FILE_TYPE_G4G, NONE);
			instance.writeLog(FILE_TYPE_G4G, ERROR_LOGGERINTI
					+ error.getLevel());
		}

		String financialFileName = G4GProperties
				.getProperty(PropertiesConstants.LOG_G4G_FINANCIALS);
		if (financialFileName == null
				|| financialFileName.trim().length() == ZERO) {
			financialFileName = FINANCIAL_TXT;
		}
		{
			Level debugLevel = Level.WARN;
			try {
				String levelStr = G4GProperties
						.getProperty(PropertiesConstants.LOG_G4G_FINANCIALS_LEVEL);
				debugLevel = Level.toLevel(levelStr);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}
			CIPLLogger debug = new CIPLLogger(FILE_TYPE_G4G_FINANCIAL,
					financialFileName, debugLevel);
			fileMaps.put(FILE_TYPE_G4G_FINANCIAL, debug);
			instance.writeLog(FILE_TYPE_G4G_FINANCIAL, NONE);
			instance.writeLog(FILE_TYPE_G4G_FINANCIAL, FINANCIAL_LOGGERINTI
					+ debug.getLevel());
		}

		String sessionFileName = G4GProperties
				.getProperty(PropertiesConstants.LOG_SESSION);
		if (sessionFileName == null || sessionFileName.trim().length() == ZERO) {
			sessionFileName = SESSION_TXT;
		}
		{
			Level sessionLevel = Level.WARN;
			try {
				String level = G4GProperties
						.getProperty(PropertiesConstants.LOG_SESSION_LEVEL);
				sessionLevel = Level.toLevel(level);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}
			CIPLLogger sessionLog = new CIPLLogger(FILE_TYPE_SESSION,
					sessionFileName, sessionLevel);
			fileMaps.put(FILE_TYPE_SESSION, sessionLog);
			instance.writeLog(FILE_TYPE_SESSION, NONE);
			instance.writeLog(FILE_TYPE_SESSION, SESSION_LOGGERINTI
					+ sessionLog.getLevel());
		}

		String logDir = System.getProperty(LOG_DIR);
		if (logDir == null) {
			logDir = NONE;
		}
		System.out.println(NONE);
		System.out.println(G4GStARTED);
		System.out.println(LOG_DIRECTORY + logDir);
		System.out.println(FOR_G4G_HIBERNATE_MESSAGES + hibernateFileName);
		System.out.println(FOR_G4G_ERRORS + errorFileName);
		System.out.println(FOR_G4G_DEBUG_MESSAGES + financialFileName);
		System.out.println(FOR_G4G_SESSION_MESSAGES + sessionFileName);
		System.out.println(FOR_G4G_SECURITY_MESSAGES + securityFileName);
	}

	/**
	 * Instantiates a new audit util.
	 */
	private AuditUtil() {
	}

	/**
	 * Gets the audited files.
	 * 
	 * @return the audited files
	 */
	public HashMap<String, CIPLLogger> getAuditedFiles() {
		return fileMaps;
	}

	/**
	 * The method getLevel returns the level for writing log file. The level is
	 * get based on the filetype passed. Levels may be:<br>
	 * 1. ALL: The ALL has the lowest possible rank and is intended to turn on
	 * all logging.<br>
	 * 2. DEBUG: The DEBUG Level designates fine-grained informational events
	 * that are most useful to debug an application.<br>
	 * 3. ERROR: The ERROR level designates error events that might still allow
	 * the application to continue running.<br>
	 * 4. FATAL: The FATAL level designates very severe error events that will
	 * presumably lead the application to abort.<br>
	 * 5. INFO: The INFO level designates informational messages that highlight
	 * the progress of the application at coarse-grained level.<br>
	 * 6. OFF: The OFF has the highest possible rank and is intended to turn off
	 * logging. <br>
	 * 7. TRACE: The TRACE Level designates finer-grained informational events
	 * than the DEBUG.<br>
	 * 8. WARN: The WARN level designates potentially harmful situations.
	 * 
	 * @param fileType
	 *            The fileType decides for which file the level to get. It may
	 *            be error log file, financial log file, or session log file
	 * 
	 * @return the log level for the given type of file
	 */
	public Level getLogLevel(String fileType) {
		Level result = null;
		if (fileType != null && fileMaps.get(fileType) != null) {
			CIPLLogger logger = fileMaps.get(fileType);
			result = logger.getLevel();
		}
		return result;
	}

	/**
	 * Sets the log level for which file the level to get. It may be error log
	 * file, financial log file, or session log file. Levels may be:<br>
	 * 1. ALL: The ALL has the lowest possible rank and is intended to turn on
	 * all logging.<br>
	 * 2. DEBUG: The DEBUG Level designates fine-grained informational events
	 * that are most useful to debug an application.<br>
	 * 3. ERROR: The ERROR level designates error events that might still allow
	 * the application to continue running.<br>
	 * 4. FATAL: The FATAL level designates very severe error events that will
	 * presumably lead the application to abort.<br>
	 * 5. INFO: The INFO level designates informational messages that highlight
	 * the progress of the application at coarse-grained level.<br>
	 * 6. OFF: The OFF has the highest possible rank and is intended to turn off
	 * logging. <br>
	 * 7. TRACE: The TRACE Level designates finer-grained informational events
	 * than the DEBUG.<br>
	 * 8. WARN: The WARN level designates potentially harmful situations.
	 * 
	 * @param fileType
	 *            The fileType decides for which file the level to get. It may
	 *            be error log file, financial log file, or session log file.
	 * 
	 * @param level
	 *            log level for the given type of file
	 */
	public void setLogLevel(String fileType, Level level) {
		if (level != null && fileType != null && fileMaps.get(fileType) != null) {
			CIPLLogger logger = fileMaps.get(fileType);
			Level currLevel = logger.getLevel();
			if (currLevel != null && !currLevel.equals(level)) {
				synchronized (logger) {
					logger.setLevel(level);
				}
			}
		}
	}

	/**
	 * The method writeLog writes log entry to the given fileType, Exception is
	 * passed if it exist. It writes the entry to error level for given
	 * exception.
	 * 
	 * @param fileType
	 *            the filetype to write the log entry. It may be error log file,
	 *            financial log file, or session log file.
	 * @param e
	 *            Exception passed.
	 */
	public void writeLog(String fileType, Exception e) {
		writeLog(fileType, e, Level.ERROR);
	}

	/**
	 * The method writeLog writes the log entry for the message for debug level
	 * for FILE_TYPE_G4G.
	 * 
	 * @param message
	 *            the message to write in the log file.
	 */
	public void writeLog(String message) {
		writeLog(FILE_TYPE_G4G, message, Level.DEBUG);
	}

	/**
	 * The writeLog method writes the log for exceptions at error level. If
	 * filetype is null or file not found in defined file types then it will
	 * give error for fileNotFound. For correct file type the exceptions will be
	 * printed to log file.
	 * 
	 * @param fileType
	 *            The filetype to write the log entry. It may be error log file,
	 *            financial log file, or session log file.
	 * @param e
	 *            the exception that will be write to log file.
	 * @param level
	 *            log level for the given type of file
	 */
	public void writeLog(String fileType, Exception e, Level level) {
		CIPLLogger logger = null;
		if (fileType == null || fileMaps.get(fileType) == null) {
			logger = fileMaps.get(FILE_TYPE_G4G);
			logger.write(new StringBuffer(File_Type).append(fileType).append(
					NOT_FOUND).toString(), Priority.INFO_INT);
		} else {
			logger = fileMaps.get(fileType);
		}
		logger.write(NONE, e);
	}

	/**
	 * The writeLog method writes the given message for the given filetype for
	 * level debug.
	 * 
	 * @param fileType
	 *            the file type to write the log entry. It may be error log
	 *            file, financial log file, or session log file.
	 * @param message
	 *            the message to write to log file.
	 */
	public void writeLog(String fileType, String message) {
		writeLog(fileType, message, Level.DEBUG);
	}

	/**
	 * The writeLog method writes the given message for the given filetype for
	 * level passed as parameter.
	 * 
	 * @param fileType
	 *            the file type to write the log entry. It may be error log
	 *            file, financial log file, or session log file.
	 * @param message
	 *            the message to write to log file.
	 * @param level
	 *            log level for the given type of file
	 */
	public void writeLog(String fileType, String message, Level level) {
		CIPLLogger logger = null;
		if (fileType == null || fileMaps.get(fileType) == null) {
			logger = fileMaps.get(FILE_TYPE_G4G);
			logger.write(new StringBuffer(File_Type).append(fileType).append(
					NOT_FOUND).toString(), Priority.INFO_INT);
		} else {
			logger = fileMaps.get(fileType);
		}
		logger.write(message, level);
	}
	public synchronized void  writeSecurityLog(HttpServletRequest request , HttpServletResponse response, String srcnFunc , String message) {
		writeLog(FILE_TYPE_SECURITY_AUDIT, ResourceBundleUtil.getSecurityTrapAudit(request, srcnFunc, message), Level.INFO);
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher(G4GConstants.INVALIDATE_JSP).forward(request, response);
			response.sendRedirect(G4GConstants.INVALIDATE_JSP);
		}
		catch (IOException exception) {
			writeLog(FILE_TYPE_G4G,exception);
		}
		catch (ServletException exception) {
			writeLog(FILE_TYPE_G4G,exception);
		}
	}
}
