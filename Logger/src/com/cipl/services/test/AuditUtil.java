/**********************************************************
 * AuditUtil.java
 *
 * Created by Rakesh Ray
 * Last modified 17 apr .08 Author: Punam
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.cipl.services.test;



import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;

import com.cipl.services.logging.CIPLLogger;


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


		String sessionFileName = null;
		if (sessionFileName == null || sessionFileName.trim().length() == 0) {
			sessionFileName = "sessionlog.txt";
		}
		{
			Level sessionLevel = Level.WARN;
			try {
				String level = "ALL";
				sessionLevel = Level.toLevel(level);
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e,
						Level.ERROR);
			}
			CIPLLogger sessionLog = new CIPLLogger(FILE_TYPE_SESSION,
					sessionFileName, sessionLevel);
			fileMaps.put(FILE_TYPE_SESSION, sessionLog);
			instance.writeLog(FILE_TYPE_SESSION, NONE);
			instance.writeLog(FILE_TYPE_SESSION, "'SESSION Logger' initililzed at level: "
					+ sessionLog.getLevel());
		}

		String logDir = System.getProperty("LOG_DIR");
		if (logDir == null) {
			logDir = NONE;
		}
		System.out.println(NONE);
		System.out.println(G4GStARTED);
		System.out.println(LOG_DIRECTORY + logDir);

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
			logger.write(new StringBuffer("File Type :").append(fileType).append(
							" Not Found").toString(), Priority.INFO_INT);
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
			logger.write(new StringBuffer("File Type :").append(fileType).append(
							" Not Found").toString(), Priority.INFO_INT);
		} else {
			logger = fileMaps.get(fileType);
		}
		logger.write(message, level);
	}

}
