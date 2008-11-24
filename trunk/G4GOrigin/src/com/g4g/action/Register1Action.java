/**********************************************************
 * Register1Action.java : 
 *
 * Created by Ankur
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.NationalCodeServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SubNationalCodeDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.Register1Form;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class Register1Action will redirect to registration 1 page on display and
 * on registration 2 page when user submits it. <br>
 * XDoclet definition:. <br>
 * Action path to display register1 page:.
 * 
 * @struts.action path="/displayRegister1" name="registerForm" validate="false"
 *                scope="request"
 * @struts.action-forward name="success" path="page.register1"<br>
 *                        Action class path when the page is submitted:.
 * @struts.action path="/register1" name="registerForm" input="page.register1"
 *                scope="request"
 * @struts.action-forward name="next" path="/displayRegister2.do"
 * 
 * @author Ankur
 */
public class Register1Action extends BaseAction {

	/**
	 * The display method will fill up the combo boxes and contains other
	 * information which required to be displayed when the page is displayed.
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
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.DISPLAY_STARTS)
						.append(G4GConstants.DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		Register1Form register1Form = (Register1Form) form;

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.IS_LEGAL, G4GConstants.TRUE);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.GETTING_NATIONS_LIST).append(
						G4GConstants.DASHES).append(
						request.getSession().getId()).toString());
		register1Form.setCountryList(NationalCodeServiceDelegator
				.getList(searchCriteria));
		searchCriteria.removeAllAttribute();
		try {
			if (register1Form.getNationCodes() != null) {
				searchCriteria.setAttribute(G4GConstants.NATCODEID, Integer
						.parseInt(register1Form.getNationCodes()));
				searchCriteria.setAttribute(G4GConstants.IS_LEGAL,
						G4GConstants.TRUE);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.GETTING_STATES_LIST).append(
								G4GConstants.DASHES).append(
								request.getSession().getId()).toString());
				register1Form.setStateList(SubNationalCodeServiceDelegator
						.getList(searchCriteria));
				request.setAttribute(G4GConstants.STATE_LIST, register1Form
						.getStateList());
				searchCriteria.removeAllAttribute();
			}
		} catch (Exception e) {
			// don't have to display user about this exception
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage()).append(
							DataUtil.getUserDTO(request).getPlayerDTO()
									.getScreenname()).append(
							request.getSession().getId()).toString(),
					Level.ERROR);
		}
		try {
			UserDTO dto = DataUtil.getUserDTO(request);
			String update = (String) request.getAttribute(G4GConstants.UPDATE);
			if (dto.getPlayerDTO().getScreenname() != null
					&& G4GConstants.TRUE.equalsIgnoreCase(update)) {
				register1Form.populate(dto);
				// set state list

				if (register1Form.getNationCodes() != null) {
					searchCriteria.removeAllAttribute();
					searchCriteria.setAttribute(G4GConstants.NATCODEID, Integer
							.parseInt(register1Form.getNationCodes()));
					searchCriteria.setAttribute(G4GConstants.IS_LEGAL,
							G4GConstants.TRUE);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									G4GConstants.GETTING_STATES_LIST).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).toString());
					List<SubNationalCodeDTO> subNationList = SubNationalCodeServiceDelegator
							.getList(searchCriteria);
					request
							.setAttribute(G4GConstants.STATE_LIST,
									subNationList);
					searchCriteria.removeAllAttribute();
				}
			}
		} catch (Exception NotingSetAsDefaut) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.EXCE_GETTING_NATIONS_LIST).append(
							NotingSetAsDefaut.getMessage()).toString(),
					Level.ERROR);

		}

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method will validate all the fields on registration 1 page, if
	 * all the fields are validated then it will redirect the registration 1
	 * page to registration 2 page.
	 * 
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
				new StringBuffer(this.getClass().getName()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod())
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								SUBMIT_STARTS).append(DASHES).append(
								request.getSession().getId()).toString());
		if (request.getSession().getAttribute(G4GConstants.BRACKETLEFT) != null) {
			request.getSession().removeAttribute(G4GConstants.BRACKETLEFT);
			return mapping.findForward(G4GConstants.NEXT);
		}

		Register1Form register1Form = (Register1Form) form;

		/**
		 * check wheather user is already registered
		 */
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.EMAILADDRESS, register1Form
				.getEmailOne());
		error.clear();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(G4GConstants.SEARCH_USER_BYEMAIL)
						.append(DASHES).append(request.getSession().getId())
						.toString());
		List<PlayerDTO> list = PlayerServiceDelegator.getList(searchCriteria,
				true);
		if (list.size() > 0) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(G4GConstants.USER_INLOCAL_DB)
							.append(DASHES)
							.append(request.getSession().getId()).toString());
			msg = new ActionMessage(G4GConstants.ERROR_USEREXISTS);
			error.add(G4GConstants.EMAILONE, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		}

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.SCREENNAME_DB, register1Form
				.getScreenName());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(G4GConstants.SEARCH_USER_BYSCREENNAME).append(
								DASHES).append(request.getSession().getId())
						.toString());
		list = PlayerServiceDelegator.getList(searchCriteria, true);
		if (list.size() > 0) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(G4GConstants.USER_INLOCAL_DB)
							.append(DASHES)
							.append(request.getSession().getId()).toString());
			msg = new ActionMessage(G4GConstants.ERROR_SCREENNAME);
			error.add(G4GConstants.SCREEN_NAME, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		}
		UserDTO dto;
		String forward = null;

		if (G4GConstants.NEXT.equalsIgnoreCase(operation)) {
			dto = (UserDTO) register1Form.getDTO();
			request.getSession().removeAttribute(G4GConstants.USERDTO);
			request.getSession().setAttribute(G4GConstants.USERDTO, dto);
			request.getSession().setAttribute(G4GConstants.REGISTER,G4GConstants.REGISTER);
			if(dto.getUser().getState()!=null) {
				if(SubNationalCodeServiceDelegator.get(DataUtil.getInteger(dto.getUser().getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.FALSE.trim())){
					request.setAttribute("unskilled", "unskilled");
				}
			}
			forward = G4GConstants.NEXT;
		}
		return mapping.findForward(forward);
	}
}
