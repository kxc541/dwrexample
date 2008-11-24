
package com.cipl.services.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class CIPLProperties implements Properties
{

    public CIPLProperties() {
        properties = new Hashtable<String, String>();
    }

    public CIPLProperties(Hashtable<String, String> properties) {
        this.properties = new Hashtable<String, String>();
        this.properties = properties;
    }

    public String getProperty(String key) {
        return (String)properties.get(key);
    }

    public String get(String key) {
        return (String)properties.get(key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = (String)properties.get(key);
        if(value == null)
            value = defaultValue;
        return value;
    }

    public void list(PrintWriter printwriter){}

    public void list(PrintStream printstream){}

    public void load(InputStream inputstream) throws IOException {}

    public void save(OutputStream outputstream, String s) throws IOException {}

    public Enumeration<String> propertyNames() {
        return properties.keys();
    }

    public Enumeration<String> keys() {
        return properties.keys();
    }

    public Date lastModified() {
        return new Date();
    }

    public String getString(String key) {
        return (String)properties.get(key);
    }

    public String[] getStrings(String key) {
        String value = (String)properties.get(key);
        String values[] = null;
        if(value != null) {
            StringTokenizer st = new StringTokenizer(value, ",");
            values = new String[st.countTokens()];
            for(int i = 0; st.hasMoreTokens(); i++)
                values[i] = st.nextToken();

            return values;
        } 
        else
            return values;
        
    }

    public double getDouble(String key)
    {
        String value = (String)properties.get(key);
        if(value != null)
            return (new Double(value)).doubleValue();
        else
            return 0.0D;
    }

    public double[] getDoubles(String key)
    {
        String values[] = getStrings(key);
        double dvalues[] = new double[values.length];
        for(int i = 0; i < values.length; i++)
            dvalues[i] = (new Double(values[i])).doubleValue();

        return dvalues;
    }

    public float getFloat(String key)
    {
        String value = (String)properties.get(key);
        if(value != null)
            return (new Float(value)).floatValue();
        else
            return 0.0F;
    }

    public float[] getFloats(String key)
    {
        String values[] = getStrings(key);
        float fvalues[] = new float[values.length];
        for(int i = 0; i < values.length; i++)
            fvalues[i] = (new Float(values[i])).floatValue();

        return fvalues;
    }

    public long getLong(String key)
    {
        String value = (String)properties.get(key);
        if(value != null)
            return (new Long(value)).longValue();
        else
            return 0L;
    }

    public long[] getLongs(String key)
    {
        String values[] = getStrings(key);
        long lvalues[] = new long[values.length];
        for(int i = 0; i < values.length; i++)
            lvalues[i] = (new Long(values[i])).longValue();

        return lvalues;
    }

    public int getInt(String key)
    {
        String value = (String)properties.get(key);
        if(value != null)
            return Integer.parseInt(value);
        else
            return 0;
    }

    public int[] getInts(String key)
    {
        String values[] = getStrings(key);
        int ivalues[] = new int[values.length];
        for(int i = 0; i < values.length; i++)
            ivalues[i] = Integer.parseInt(values[i]);

        return ivalues;
    }

    public short getShort(String key)
    {
        String value = (String)properties.get(key);
        if(value != null)
            return (new Short(value)).shortValue();
        else
            return 0;
    }

    public short[] getShorts(String key)
    {
        String values[] = getStrings(key);
        short svalues[] = new short[values.length];
        for(int i = 0; i < values.length; i++)
            svalues[i] = (new Short(values[i])).shortValue();

        return svalues;
    }

    private Hashtable<String, String> properties;
}