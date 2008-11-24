package com.cipl.services.properties;


public class CIPLEnvironmentType {
	public static final String SERVER_INSTANCE 		= "SERVER_INSTANCE";
	public static final String SERVER_NUMBER 		= "SERVER_NUMBER";
	public static final String ENV_COMMON       	= "COMMON";
	public static final String ENV_DEVELOPMENT  	= "DEVELOPMENT";
	public static final String ENV_PREFIX			= "[";
	public static final String ENV_SUFFIX       	= "]";
	public static final String COMMENT_INDICATOR	= "#";
	public static final String ENV_KEY_NAME 		= "WASEnvironment";
	public static final String VARIABLE_INDICATOR 	= "cipl.variable.";
	public static final String SANDBOX 				= "SANDBOX";
	public static final String TEST 				= "TEST";
	public static final String STAGING 				= "STAGING";
	public static final String VALIDATION 			= "VALIDATION";
	public static final String PRODUCTION 			= "PRODUCTION";
	
	private static String instanceName = null;
	private static int serverNumber = 0;
	
	private CIPLEnvironmentType(){}
	
	static{
		// Get the Instance Numebr for the server. This is to handle Load balancing based server setup
		String temp = System.getProperty(SERVER_NUMBER);
		if (temp == null || temp.trim().equalsIgnoreCase("")){
			serverNumber = 1;
		}
		else{
			try {
				serverNumber = Integer.parseInt(temp);
			} catch (Exception e) {
				serverNumber = 1;
			}
		}

		instanceName = System.getProperty(SERVER_INSTANCE);
		if (instanceName == null || instanceName.trim().equalsIgnoreCase("")){
			System.out.println("System Environment not set! Using default Sandbox environment");
			instanceName = SANDBOX;
		}
		
	}
	
	public static String getCurrentSystemEnvironment() throws Exception{
		if (instanceName == null)
			throw new Exception("Could not get the System Environment.");
		else 
			return instanceName;
	}
	
	public static int getServerNumber(){
		return serverNumber;
	}
}