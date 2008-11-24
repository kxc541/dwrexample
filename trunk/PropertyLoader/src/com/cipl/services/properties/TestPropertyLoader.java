package com.cipl.services.properties;

import java.io.Serializable;
import java.util.Enumeration;

public class TestPropertyLoader implements Serializable {

	  /**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private static com.cipl.services.properties.Properties myProp =null;
		protected static final String propertiesURL = "/g4g.properties";

	  /**
	   *  static initialization
	   */

	  static
	  {
	    loadProperties();
	  }

	  static  private void loadProperties()
	  {
	    try
	    {
	      myProp = HTTPPropertyLoader.getExpandedProperties(propertiesURL);
	    }
	    catch (PropertyLoadException e) {
	     e.printStackTrace();
	    }
	    catch (Exception e) {
			e.printStackTrace();
	    }
	  }


	  public void reloadProperties(){
	    loadProperties();
	  }

	  public TestPropertyLoader()
	  {
	  }

	  public String getAllProperties(){
	    Enumeration<String> records = myProp.keys();
	    StringBuffer s = new StringBuffer();
	    try{
	      while (records.hasMoreElements())
	      {
	        String key = (String)records.nextElement();
	        String value = (String)myProp.getProperty(key);
	        s.append(key);
	        s.append(" :");
	        s.append(value);
	        s.append("\n");
	      }
	    }
	    catch (Exception e){
	      e.printStackTrace();
	    }
	    return s.toString();
	  }

	  public String getProperty(String pty)
	  {
	    String rtsStr = "";
	    rtsStr= myProp.getProperty (pty);
	    if ( rtsStr == null)
	    {
	      return "";
	    }
	    else
	    {
	      return rtsStr;
	    }
	  }
	  public static void main (String argc[])
	  {
		  TestPropertyLoader rep = new TestPropertyLoader();
		  System.out.println(rep.getAllProperties());

	  }
}


