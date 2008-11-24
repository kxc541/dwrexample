package com.ankur.citystate;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

//com.myapp.struts.dto.*;

/**
 * CityStateForm.
 *
 * @author zoom
 */

public class CityStateForm extends org.apache.struts.action.ActionForm {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Class Constructor.
     */
    public CityStateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** The city. */
    public String   city;

    /** The state. */
    public String   state;

    /**
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    //Gets
    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    //Sets
    /**
     * Sets the city.
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state.
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}
