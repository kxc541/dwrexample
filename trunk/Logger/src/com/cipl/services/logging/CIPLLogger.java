package com.cipl.services.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * @author Rakesh Ray
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CIPLLogger {

	private Logger logger;
	private String fileName;
	private String loggerType;
	private Level level;
	private String fileLocation;
	private boolean rollingFile = true;
	private long rollingFileSize = 10;

	private void initLogger() {
		String logDir = System.getProperty("LOG_DIR");
		if (logDir == null)
			logDir = "";
		String logFileName = logDir + "/" + fileName;
		if (isRollingFile()){
			RollingFileAppender rollingFileAppender = null;

			logger = Logger.getLogger(fileName);

			logger.setAdditivity(false);
			try
			{
				PatternLayout filePattern = new PatternLayout("%d{ISO8601}. [%5p]: %m%n");
				rollingFileAppender = new RollingFileAppender(filePattern, logFileName);
				rollingFileAppender.setMaxBackupIndex(2);
				rollingFileAppender.setMaxFileSize(rollingFileSize + "MB");
				logger.setLevel(level);
				logger.addAppender(rollingFileAppender);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		write("---------------------------",Level.INFO);
		write(loggerType + " VITNALogger initililzed at level: " + logger.getLevel(),Level.INFO);

		// Announce in stdout.txt where logging info is going.
		System.out.println("");
		System.out.println("--- For " + loggerType + " Messages - Check file: " + logFileName);

	}

	@SuppressWarnings("unused")
	private CIPLLogger() {
	}

	public CIPLLogger(String loggerType, String fileName, Level level) {
		this.loggerType = loggerType;
		this.fileName = fileName;
		this.level = level;
		initLogger();
	}

	public CIPLLogger(String loggerType, String fileName) {
		this.loggerType = loggerType;
		this.fileName = fileName;
		this.level = Level.DEBUG;
		initLogger();
	}

	/**
	 * @return
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param logger
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @return
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @return
	 */
	public String getLoggerType() {
		return loggerType;
	}

	/**
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
		if (logger != null && level != null){
			logger.setLevel(level);
			logger.info("Level Changed to " + level);
		}
	}

	public void write(String message){
		write(message,Level.DEBUG);
	}

	public void write(String message , Level level){
		if (level == null || level == Level.DEBUG)
			logger.debug(message);
		else if (level == Level.ERROR)
			logger.error(message);
		else if (level == Level.FATAL)
			logger.fatal(message);
		else if (level == Level.INFO)
			logger.info(message);
		else if (level == Level.WARN)
			logger.warn(message);
		else
			logger.debug(message);
	}

	public void write(String message , int level){
		if (level == Level.DEBUG_INT)
			logger.debug(message);
		else if (level == Level.ERROR_INT)
			logger.error(message);
		else if (level == Level.FATAL_INT)
			logger.fatal(message);
		else if (level == Level.INFO_INT)
			logger.info(message);
		else if (level == Level.WARN_INT)
			logger.warn(message);
		else
			logger.debug(message);
	}

	public void write(String message,Exception e){
		logger.error(message,e);
	}
	/**
	 * @return
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @return
	 */
	public boolean isRollingFile() {
		return rollingFile;
	}

	/**
	 * @return
	 */
	public long getRollingFileSize() {
		return rollingFileSize;
	}

	/**
	 * @param b
	 */
	public void setRollingFile(boolean b) {
		rollingFile = b;
	}

	/**
	 * @param l
	 */
	public void setRollingFileSize(long l) {
		rollingFileSize = l;
	}

}
