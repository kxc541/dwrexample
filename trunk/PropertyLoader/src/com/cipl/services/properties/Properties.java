package com.cipl.services.properties;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;

public interface Properties
{

    public abstract String get(String s);

    public abstract String getProperty(String s);

    public abstract String getProperty(String s, String s1);

    public abstract void list(PrintWriter printwriter);

    public abstract void list(PrintStream printstream);

    public abstract void load(InputStream inputstream)
        throws IOException;

    public abstract void save(OutputStream outputstream, String s)
        throws IOException;

    public abstract Enumeration<String> propertyNames();

    public abstract Enumeration<String> keys();

    public abstract Date lastModified();

    public abstract String getString(String s);

    public abstract String[] getStrings(String s);

    public abstract double getDouble(String s);

    public abstract double[] getDoubles(String s);

    public abstract float getFloat(String s);

    public abstract float[] getFloats(String s);

    public abstract long getLong(String s);

    public abstract long[] getLongs(String s);

    public abstract int getInt(String s);

    public abstract int[] getInts(String s);

    public abstract short getShort(String s);

    public abstract short[] getShorts(String s);
}