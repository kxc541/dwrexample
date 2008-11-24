/**********************************************************
 * className.java :
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
/*
 *
 */
package com.g4g.action;

import static com.g4g.utils.G4GConstants.ACTION_COMMENT;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.COMMINGSOON;
import static com.g4g.utils.G4GConstants.DISPLAY;
import static com.g4g.utils.G4GConstants.ERROR;
import static com.g4g.utils.G4GConstants.EXECUTE_STARTED;
import static com.g4g.utils.G4GConstants.FORWARD_TO;
import static com.g4g.utils.G4GConstants.GETTING_SITEID;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.IS_DISPLAY_COMMENT;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.NO_ERROR_AVAILABLE;
import static com.g4g.utils.G4GConstants.OPERATION_COMMENT;
import static com.g4g.utils.G4GConstants.SESSIONSITEID;
import static com.g4g.utils.G4GConstants.SITE_ID_ATTRIBUTE;
import static com.g4g.utils.G4GConstants.WORLD_GAMING_SITE_ID;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Level;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.dto.SearchCriteria;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.ResourceBundleUtil;

/**
 * BaseAction class inherited by all the action classes.
 *
 * @author Ankur
 */
public abstract class BaseAction extends Action {

	/** Resource Bundle for displaying common messages and errors in BaseAction. */
	protected static ResourceBundle resource = null;
	/** Common Objects for all Child actions. */
	protected static SearchCriteria searchCriteria = null;
	/** Common Objects for all Child actions. */
	protected static ActionErrors error = new ActionErrors();
	/** Common Objects for all Child actions. */
	ActionMessage msg;

	/**
	 * Default Constructor
	 */
	public BaseAction() {
		searchCriteria = new SearchCriteria();
	}

	/**
	 * inherited methods of Struts Default Action class.
	 *
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 *
	 * @return the action forward
	 *
	 * @author Ankur
	 */
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(EXECUTE_STARTED).toString(), Level.INFO);
		init();

		BaseForm formBean = (BaseForm) form;
		ActionForward forward;
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(GETTING_SITEID).toString(), Level.INFO);
		String inSiteId = request.getParameter(SITE_ID_ATTRIBUTE);
		if (inSiteId == null || inSiteId.trim().equalsIgnoreCase(NONE)) {
			request.getSession().setAttribute(SITE_ID_ATTRIBUTE,
					WORLD_GAMING_SITE_ID);
		} else {
			request.getSession().setAttribute(SITE_ID_ATTRIBUTE,
					inSiteId.trim());
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(SESSIONSITEID).append(
								DataUtil.getSessionSiteId(request)).toString(),
				Level.INFO);
		// Call child
		if (mapping.getPath().toLowerCase().indexOf(DISPLAY) != G4GConstants.ONE_NEGATIVE) {
			formBean.setDisplay(true);
		} else {
			formBean.setDisplay(false);
		}
		// Debug statements
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(ACTION_COMMENT).append(
						this.getClass().getName()).toString());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(IS_DISPLAY_COMMENT).append(
						formBean.isDisplay()).toString());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(OPERATION_COMMENT).append(
						formBean.getOperation()).toString());
		try {
			if (formBean.isDisplay()) {
				forward = display(mapping, formBean, formBean.getOperation(),
						request, response);
			} else {
				forward = submit(mapping, formBean, formBean.getOperation(),request, response);
			}
		} catch (WebServiceException e) {
			try {
				ResourceBundleUtil.getApplicationProperties().getString(e.getMessage());
				msg = new ActionMessage(e.getMessage());
		} catch (MissingResourceException ex) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
							COLON_WITH_SPACES).append(DataUtil.getCallingMethod()).append(EXECUTE_STARTED).toString(),Level.ERROR);
			msg = new ActionMessage(NO_ERROR_AVAILABLE, new StringBuffer(e.getMessage()).append(e.getCause()).toString());
		}
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(DataUtil.getCallingMethod()).append(e.getMessage()).toString(), Level.ERROR);

			error.clear();
			error.add(ERROR, msg);
			this.saveErrors(request, error);
			if (DataUtil.getUserDTO(request) != null) {
				return mapping.findForward(HOMEPAGE);
			}
			return mapping.getInputForward();

		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(DataUtil.getCallingMethod()).append(
									e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			request.setAttribute(ERROR, e);
			try {
				ResourceBundleUtil.getApplicationProperties().getString(e.getMessage());
				msg = new ActionMessage(e.getMessage());
			} catch (MissingResourceException ex) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(DataUtil.getCallingMethod()).append(EXECUTE_STARTED).toString(),Level.ERROR);
				msg = new ActionMessage(NO_ERROR_AVAILABLE, new StringBuffer(e
						.getMessage()).append(e.getCause()).toString());
			} catch (NullPointerException eNullPoimterException) {
				msg = new ActionMessage(NO_ERROR_AVAILABLE, e.getMessage()+e.getCause());
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								eNullPoimterException.getMessage()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								eNullPoimterException.getMessage()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
			}
			error.clear();
			error.add(ERROR, msg);
			this.saveErrors(request, error);
			if (DataUtil.getUserDTO(request) != null) {
				return mapping.getInputForward();
			}
			return mapping.findForward(HOMEPAGE);
		}
		if (forward == null) {
			forward = mapping.findForward(COMMINGSOON);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FORWARD_TO).append(forward).toString(),
				Level.INFO);
		return forward;
	}

	/**
	 * This method will contain actual navigation logic for individual usecases.
	 * It must be overridden by child action classes
	 *
	 * @author Ankur
	 */
	public void init() {
		resource = ResourceBundleUtil.getApplicationProperties();
	}

	/**
	 * Logic to perform while submmiting a page.
	 *
	 * @param mapping
	 *            the mapping to forward submit result to page.
	 * @param form
	 *            the form defined in action definition.
	 * @param operation
	 *            the submit operation name.
	 * @param request
	 *            the request object.
	 * @param response
	 *            the response object.
	 *
	 * @return ActionForward
	 *
	 * @author Ankur
	 * @throws Exception
	 */

	public abstract ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * Logic to perform while displaying the page.
	 *
	 * @param mapping
	 *            the mapping to forward submit result to page.
	 * @param form
	 *            the form defined in action definition.
	 * @param operation
	 *            the submit operation name.
	 * @param request
	 *            the request object.
	 * @param response
	 *            the response object.
	 *
	 * @return the mapping action forward.
	 *
	 * @author Ankur
	 * @throws Exception
	 */
	public abstract ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}