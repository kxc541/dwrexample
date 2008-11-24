/**********************************************************
 * className.java :
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.CAPTCHA;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.EMAILADDRESS;
import static com.g4g.utils.G4GConstants.ERROR;
import static com.g4g.utils.G4GConstants.ERRORS_EMAIL;
import static com.g4g.utils.G4GConstants.ERRORS_NO_EMAIL;
import static com.g4g.utils.G4GConstants.ERROR_CAPTCHA;
import static com.g4g.utils.G4GConstants.EXCE_IMPASSA;
import static com.g4g.utils.G4GConstants.GAMING_WORLD;
import static com.g4g.utils.G4GConstants.KEEP_SAFE;
import static com.g4g.utils.G4GConstants.NEW_LINE;
import static com.g4g.utils.G4GConstants.NOT_REG_IMPASSA;
import static com.g4g.utils.G4GConstants.PASSWORD;
import static com.g4g.utils.G4GConstants.RESET_PASSWORD;
import static com.g4g.utils.G4GConstants.SUBMIT;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.SOMETHING_WRONG;
import static com.g4g.utils.G4GConstants.USERNAME;
import static com.g4g.utils.G4GConstants.USER_NAME;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.forms.BaseForm;
import com.g4g.forms.ForgotPasswordForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.MailHelper;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The action class ForgotPasswordAction processes the forgot password
 * request.The forgot password action controller redirects the form to forgot
 * password page.Where user is asked for email address and sends the forgot
 * password to that email address.<br>
 * XDoclet definition:
 *
 * @struts.action path="/forgotPassword" name="forgotPasswordForm"
 *                input="/WebContent/forgotPassword.jsp" scope="request"
 * @struts.action-forward name="success" path="/displayLogin.do" The action path
 *                        displays forgot password page.
 * @struts.action path="/displayForgotPassword" name="displayForgotPasswordForm"
 *                attribute="forgotPasswordForm" scope="request"
 * @struts.action-forward name="success" path="/WebContent/forgotPassword.jsp"
 * @author Ankur
 */
public class ForgotPasswordAction extends BaseAction {

	/**
	 * The display action displays the forgotpassword page.
	 *
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DISPLAY_STARTS).append(DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return mapping.findForward(SUCCESS);
	}

	/**
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(SUBMIT_STARTS).toString());

		ForgotPasswordForm forgotPasswordForm = (ForgotPasswordForm) form;

		if (SUBMIT.equals(operation)) {
			String captchaId = request.getSession().getAttribute(
					nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY)
					.toString();
			String captchaResponse = request.getParameter(CAPTCHA);
			if (!captchaId.equals(captchaResponse)) {
				ActionErrors error = new ActionErrors();
				ActionMessage msg = new ActionMessage(ERROR_CAPTCHA);
				error.add(CAPTCHA, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(EMAILADDRESS, forgotPasswordForm
					.getUsername());
			if (PlayerServiceDelegator.getList(searchCriteria).size() == G4GConstants.ZERO) {
				msg = new ActionMessage(ERRORS_EMAIL);
				error.clear();
				error.add(USERNAME, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			StringBuffer message = new StringBuffer();
			message.append(USER_NAME).append(forgotPasswordForm.getUsername());
			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
								.append(DASHES).append(SUBMIT_STARTS).append(
										RESET_PASSWORD).toString());
				message.append(NEW_LINE).append(PASSWORD).append(
						G4GFinancialDelegator.resetPassword(forgotPasswordForm
								.getUsername()));
			} catch (InternalException_Exception e) {
				AuditUtil.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
				AuditUtil.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								DataUtil.getCurrentMethod()).append(
								EXCE_IMPASSA).append(e.getMessage()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserNotFoundException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								BLANK).append(DataUtil.getCurrentMethod())
								.append(BLANK).append(NOT_REG_IMPASSA).append(
										forgotPasswordForm.getUsername())
								.append(BLANK).append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								BLANK).append(DataUtil.getCurrentMethod())
								.append(BLANK).append(NOT_REG_IMPASSA).append(
										forgotPasswordForm.getUsername())
								.append(BLANK).append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			MailHelper mailHeper = new MailHelper();
			try {
				mailHeper.sendMail(GAMING_WORLD, forgotPasswordForm
						.getUsername(), KEEP_SAFE, message.toString());
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								SOMETHING_WRONG).append(e.getMessage())
								.toString(), Level.ERROR);
				msg = new ActionMessage(ERRORS_NO_EMAIL);
				error.clear();
				error.add(USERNAME, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
		}
		request.getSession().invalidate();
		return mapping.findForward(SUCCESS);
	}
}