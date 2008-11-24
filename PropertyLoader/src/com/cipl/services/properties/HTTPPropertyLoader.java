package com.cipl.services.properties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

//import com.cipl.services.logging.DefaultLogging;
//import com.cipl.wasgso.util.WASConstants;

public class HTTPPropertyLoader
{

	private static Hashtable<String, String> crossReference = new Hashtable<String, String>();
	private static Hashtable<String, String> substitutionMap = new Hashtable<String, String>();

	public static final String COMMENT_SYMBOL = "#";
	public static final String EQUALITY_SYMBOL = "=";
	public static final String LIST_DELIMITER = ",";
	public static final String LINE_CONTINUATION_SYMBOL = "\\";
	public static final String LITERAL_SYMBOL = "\"";
	public static final String SUBSTITUTION_SYMBOL = "$";
	public static final String SUBSTITUTION_PREFIX = "cipl.variable.";
	private static String propertyServer = null;
	
	static{
		try
		{
			
			String logDir = System.getProperty("LOG_DIR");
			if (logDir == null)
				logDir = "";
			String workDir = System.getProperty("WORK_DIR");
			if (workDir == null)
				workDir = "";
			String docDir = System.getProperty("DOC_DIR");
			if (docDir == null)
				docDir = "";
			String appDocDir = System.getProperty("APP_DOC_DIR");
			if (appDocDir == null)
				appDocDir = workDir;
			System.out.println("--- Log Directory : " + logDir);
			System.out.println("--- Work Directory : " + workDir);
			System.out.println("--- Doc Root : " + docDir);
			System.out.println("--- App Doc Root : " + appDocDir);

			substitutionMap.put(SUBSTITUTION_SYMBOL + "LOG_DIR",logDir);
			substitutionMap.put(SUBSTITUTION_SYMBOL + "WORK_DIR",workDir);
			substitutionMap.put(SUBSTITUTION_SYMBOL + "DOC_DIR",docDir);
			substitutionMap.put(SUBSTITUTION_SYMBOL + "APPDOCDIR",appDocDir);
			
			propertyServer = String.valueOf(String.valueOf(appDocDir)).concat("/properties");
		}
		catch(Exception exception) { 
			exception.printStackTrace();
		}
	}
	
    private HTTPPropertyLoader(){}

    public static com.cipl.services.properties.Properties getExpandedProperties(String propertyUrl) throws PropertyLoadException {
		try {
			String url = propertyServer + propertyUrl;
			String propFile = getRawFile(url);
			Hashtable<String, String> appBySection = divideBySection(propFile);
			String environmentBase = CIPLEnvironmentType.getCurrentSystemEnvironment();
			Hashtable<String, String> hp = new Hashtable<String, String>();
			merge(hp,crossReference);
			merge(hp, (String)appBySection.get(CIPLEnvironmentType.ENV_COMMON));
			merge(hp, (String)appBySection.get(environmentBase));
			if (CIPLEnvironmentType.getServerNumber() != 0){
				merge(hp, (String)appBySection.get(environmentBase + CIPLEnvironmentType.getServerNumber()));
			}
			merge(hp,substitutionMap);
			CIPLProperties ugp = new CIPLProperties(hp);
			return ugp;
		} 
		catch (Exception e) {
//			DefaultLogging.getInstance().writeLog("Error getting Properties file ",e);
			e.printStackTrace();
			throw (PropertyLoadException)e;
		}
    }

	private static void merge(Hashtable<String, String> table, String file)
	{
		try
		{
			if(file != null)
			{
				BufferedReader br = new BufferedReader(new StringReader(file));
				String line = null;
				do
				{
					if((line = br.readLine()) == null)
						break;
					int pos = line.indexOf("=");
					if(pos > 0)
					{
						String key = line.substring(0, pos);
						String value = line.substring(pos + 1);
						addValue(table, key, value);
					}
				} while(true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void merge(Hashtable<String, String> table, Hashtable<String, String> file)
	{
		try
		{
			if(file != null)
			{
				Enumeration<String> keys = file.keys();
				while (keys.hasMoreElements()){
					String key = (String)keys.nextElement();
					String value = (String)file.get(key);
					addValue(table, key, value);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void addValue(Hashtable<String, String> table, String key, String value){
		if (table != null && key != null){
			table.put(key, doSubstitutions(value));
		}
	}
	
	private static String doSubstitutions(String value) {
	  for(Iterator<String> it = substitutionMap.keySet().iterator(); it.hasNext(); ) {
		String key = (String) it.next();
		String keyValue = (String) substitutionMap.get(key);

		int startIndex = 0;
		int index = 0;
		while((index = value.indexOf(key, startIndex)) != -1) {

		  value =
			value.substring(0, index) +
			  keyValue +
			value.substring(index + key.length());

		  startIndex = index + keyValue.length();
		  if(startIndex > value.length()) {
			break;
		  }
		}
	  }

	  return value;
	}

    private static Hashtable<String, String> divideBySection(String file)
    {
        Hashtable<String, String> result = new Hashtable<String, String>();
        try
        {
            BufferedReader br = new BufferedReader(new StringReader(file));
            String line = null;
            StringBuffer buffer = new StringBuffer();
            String sectionKey = CIPLEnvironmentType.ENV_COMMON;
            do
            {
                if((line = br.readLine()) == null)
                    break;
                line = line.trim();
                if(line.startsWith(CIPLEnvironmentType.ENV_PREFIX) && line.endsWith(CIPLEnvironmentType.ENV_SUFFIX))
                {
                    String nextSectionKey = line.substring(1, line.length() - 1);
                    if(buffer.length() > 0)
                    {
                        result.put(sectionKey, new String(buffer));
                        buffer.setLength(0);
                    }
                    sectionKey = nextSectionKey;
                } else
                if(!line.startsWith(CIPLEnvironmentType.COMMENT_INDICATOR))
                    buffer.append(String.valueOf(String.valueOf(line)).concat("\n"));
            } while(true);
            result.put(sectionKey, new String(buffer));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static String getRawFile(String url)
        throws PropertyLoadException
    {
        try
        {
			URL u = null;
			FileInputStream fis = null;
			try {
				u = new URL(url);
			}
			catch(MalformedURLException e)
			{
				fis = new FileInputStream(url);
			}
			BufferedReader server = null;
			if (u != null)
            	server = new BufferedReader(new InputStreamReader(u.openStream()));
            else
				server = new BufferedReader(new InputStreamReader(fis));
            	 
            StringBuffer file = new StringBuffer();
            String line;
            while((line = server.readLine()) != null) 
                file.append(String.valueOf(String.valueOf(line)).concat("\n"));
//			java.util.Properties systemProperties = System.getProperties();
//            Enumeration enum = systemProperties.keys();
//            do
//            {
//                if(!enum.hasMoreElements())
//                    break;
//                String key = (String)enum.nextElement();
////                if(key.startsWith(CIPLEnvironmentType.VARIABLE_INDICATOR))
////                    file.replace("$".concat(String.valueOf(String.valueOf(key.substring(12)))), (String)systemProperties.get(key), true);
//            } while(true);
//            String s = file.toString();
            return file.toString();
        }
        catch(Exception e)
        {
            throw new PropertyLoadException("Unable to load properties file at location ".concat(String.valueOf(String.valueOf(url))));
        }
    }

    public static long getLastModifiedTimeStamp(String propertiesUrl)
        throws PropertyLoadException
    {
        try
        {
            URL url = new URL(propertiesUrl);
            URLConnection urlConnection = url.openConnection();
            long l = urlConnection.getLastModified();
            return l;
        }
        catch(Exception ex)
        {
            throw new PropertyLoadException("Problem finding properties file: ".concat(String.valueOf(String.valueOf(ex.toString()))));
        }
    }
}