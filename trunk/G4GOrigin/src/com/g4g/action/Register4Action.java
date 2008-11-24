/**********************************************************
* Register4Action.java : 
*
* Created by Ankur
* Last modified Date: 18 Apr .08 by Punam
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.FORPLAYER;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.SAVE;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;

/**
 * The action class Register4Action displays register4page.
 * XDoclet definition:
 * @struts.action path="/displayRegister4" name="register4Form" scope="request"
 * @struts.action-forward name="success" path="page.register4"
 * 
 * @author Ankur
 */
public class Register4Action extends BaseAction {

	/**
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD) .append( DataUtil.getCallingMethod()) .append( DISPLAY_STARTS ).append( DASHES ).append( request.getSession().getId()).toString(),Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(
							request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(  
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append( 
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress() 
						).append(DASHES ).append( request.getSession().getId()).toString());

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
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName() ).append( CURRENTMETHOD ).append( DataUtil.getCurrentMethod()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS ).append( DASHES ).append( request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(
						this.getClass().getName() ).append(  
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append( 
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress() 
						).append(DASHES ).append( request.getSession().getId()).toString(),Level.INFO);

		return mapping.findForward(SAVE);
	}

}
