
package com.cipl.services.logging;

import org.apache.log4j.Level;

/**
 * @author Rakesh Ray
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DefaultLogging {

	private static DefaultLogging instance = null;
	private static CIPLLogger error = null;
	/**
	 * 
	 */
	private static void init(){
		
		String errorFileName = "default_logging.txt";
		error = new CIPLLogger("Default Logging",errorFileName,Level.ERROR);
	}
	private DefaultLogging() {}
	
	public static DefaultLogging getInstance(){
		if (instance == null){
			instance = new DefaultLogging();
			init();
		}
	  	return instance;
	}

//	public void setFileType(String fileType, String fileLocation){
//		if (fileType != null){
//			try {
//				File logFile = new File(fileLocation);
//				if (logFile.exists() == false){
//					if (logFile.createNewFile() == false) {
//						logFile = null;
//					}
//				}
//				PrintWriter logStream = new PrintWriter(new BufferedWriter(new FileWriter(logFile.getAbsolutePath(), true)));
//				fileMaps.put(fileType,logStream);
//			} catch (IOException e) {
//				e.printStackTrace(System.err);
//			}
//		}
//	}
//
	public void writeLog(String fileType,String message){
		writeLog(fileType,message,Level.DEBUG);
  	}

	public void writeLog(String fileType,String message,Level level){
		if (error != null)
			error.write(message,level);			
  	}

	public void writeLog(String fileType,Exception e){
		writeLog(fileType,e,Level.ERROR);
	}

	public void writeLog(String fileType,Exception e,Level level){
		if (error != null)
			error.write("",e);			
	}

	public void writeError(String fileType,String message){
		writeError(fileType,message,Level.ERROR);
	}

	public void writeError(String fileType,String message,Level level){
		if (error != null)
			error.write(message,level);			
  	}

	public void writeError(String fileType,Exception e){
		writeError(fileType,e,Level.ERROR);
	}

	public void writeError(String fileType,Exception e,Level level){
		if (error != null)
			error.write("",e);			
	}

//	private String getDateTimeStamp() {
//		Date now = new Date();
//		return now.toString();
//	}

}
