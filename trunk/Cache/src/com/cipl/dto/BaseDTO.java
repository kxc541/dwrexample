package com.cipl.dto;

import java.io.Serializable;
import java.lang.reflect.Method;


/**
 * The Class BaseDTO.
 * 
 * @author ankur
 */
public class BaseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3997253853882245429L;
    
    /** The id. */
    protected int id;

    /**
     * Gets the id.
     * 
     * @return id
     */
    public int getId() {
	return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * To string.
     * 
     * @return the string
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	Method[] ms = this.getClass().getMethods();
	String mName = null;
	for (Method element : ms) {
	    mName = element.getName();
	    if (mName.startsWith("get")) {
		try {
		    buffer.append("\n\t" + mName + " = "
			    + element.invoke(this, (Object[]) null));
		} catch (Exception e) {
			/**NO Need for showing this error to user*/
		}
	    }
	}
	return buffer.toString();
    }

}
