package com.ankur.citystate;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Handles Request for the City State page.
 *
 * @author zoom
 */

public class CityStateAction extends Action {


    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     *
     * @return ActionForward
     *
     * @throws java.lang.Exception java.lang.Exception
     * @throws Exception the exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	setLocale(request, Locale.FRANCE);

        //Line 1
        CityStateDAO cityStateDAO = new CityStateDAO();
        //Line 2
        State[] stateList = cityStateDAO.getStates();
        //Line 3
        request.setAttribute("stateList", stateList);

        return mapping.getInputForward();

    }

}
