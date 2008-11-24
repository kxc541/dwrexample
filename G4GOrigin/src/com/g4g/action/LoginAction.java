/**********************************************************
 * LoginAction.java : 
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.MessageServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.delegator.TimeZoneServiceDelegator;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TimeZoneDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.LoginForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.SessionUtil;
import com.impessa.worldgaming.client.AuthenticationException_Exception;
import com.impessa.worldgaming.client.Balance;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.PlayerStatusException_Exception;

/**
 * The action class LoginAction to serve the login request.It displays login
 * page to all the users.Redirects the user to profile page registration page if
 * the login information provided by user is correct. Else message is displayed
 * for entering correct login information. <br>
 * XDoclet definition:. <br>
 * The user is taken to home page initially.
 * 
 * @struts.action name="loginForm" path="/displayLogin" scope="request"
 * @struts.action-forward name="success" path="/WebContent/homePage.jsp" He/She
 *                        is redirected to profile page/registration page if
 *                        login information is correct.
 * @struts.action input="/WebContent/homePage.jsp" name="loginForm"
 *                path="/login"
 * @struts.action-forward name="firstLogin" path="/displayRegister2.do"
 * @struts.action-forward name="login" path="/displayProfile.do"
 * @author Jigar Mistry
 */
public class LoginAction extends BaseAction {

	/**
	 * The display method displays home page with the given siteId
	 * information.If siteId is not available it will take inbuilt world gaming
	 * siteId.
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
		if (request.getSession().getAttribute(G4GConstants.SITE_ID_ATTRIBUTE) == null) {
			if (request.getParameter(G4GConstants.SITE_ID_ATTRIBUTE) == null) {
				request.getSession().setAttribute(
						G4GConstants.SITE_ID_ATTRIBUTE,
						G4GConstants.WORLD_GAMING_SITE_ID);
			} else {
				request.getSession().setAttribute(
						G4GConstants.SITE_ID_ATTRIBUTE,
						request.getParameter(G4GConstants.SITE_ID_ATTRIBUTE));
			}
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.SETTINGSITEID).append(
						request.getSession().getAttribute(
								G4GConstants.SITE_ID_ATTRIBUTE)).append(
						G4GConstants.BLANK)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		G4GConstants.BASEURL = DataUtil.getBasePath(request);
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method will caheck for the login details.If the entered login
	 * information is correct and user is registered by this site only then
	 * he/she will be redirected to profile page. If the user is visiting
	 * worldgaming site from another site and not registered with World Gaming
	 * then he will redirected to registration page.This method will check for
	 * required validations and displays appropriate message accordingly.
	 * 
	 * @throws Exception
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings(G4GConstants.UNCHECKED)
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.SUBMIT_STARTS).append(G4GConstants.DASHES)
						.append(request.getSession().getId()).toString());

		String forward = null;
		LoginForm loginForm = (LoginForm) form;
		UserDTO userDTO = new UserDTO();
		if (G4GConstants.LOGIN.equalsIgnoreCase(operation)) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(G4GConstants.USER_NAME)
							.append(loginForm.getUsername()).append(
									G4GConstants.DASHES).append(
									G4GConstants.USER_SESSION_ID).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			String sessionId = null;

			try {
				// SiteId and IP address given statically as currently we don't
				// have implemented it.
				String userIPString = G4GConstants.USERIPSTRINGFAILS;
				try {
					InetAddress userIPAddress = InetAddress.getLocalHost();
					if (userIPAddress != null) {
						userIPString = userIPAddress.getHostAddress();
					}
				} catch (UnknownHostException e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(e.getMessage())
									.append(G4GConstants.DASHES).append(
											request.getSession().getId())
									.toString());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.DASHES).append(
									request.getSession().getId()).append(e)
									.toString(), Level.ERROR);
				}
				sessionId = G4GFinancialDelegator.login(
						loginForm.getUsername(), loginForm.getPassword(),
						SessionUtil.getSiteId(request), userIPString);
				userDTO.setUser(G4GFinancialDelegator
						.getRegistrationDetails(sessionId));
				userDTO.setSessionId(sessionId);
				Balance balance = G4GFinancialDelegator.getBalance(sessionId);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(G4GConstants.GETTINGUSERBALANCE)
								.append(G4GConstants.DASHES).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				userDTO.setBalance(DataUtil.getImpassaMoney(balance
						.getBankBalance().getAmount())
						+ DataUtil.getImpassaMoney(balance.getWinningsBalance()
								.getAmount()));
			} catch (NullPointerException e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,e, Level.ERROR);
				msg = new ActionMessage(G4GConstants.JAVAX_XML_WS_WEBSERVICEEXCEPTION);
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.saveErrors(request, error);
				if (DataUtil.getUserDTO(request) != null) {
					return mapping.getInputForward();
				}
				return mapping.findForward(G4GConstants.HOMEPAGE);
			}

			catch (AuthenticationException_Exception authenticationException_Exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						authenticationException_Exception, Level.ERROR);
				msg = new ActionMessage(authenticationException_Exception
						.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();

			} catch (PlayerStatusException_Exception playerStatusException_Exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						playerStatusException_Exception, Level.ERROR);
				msg = new ActionMessage(playerStatusException_Exception
						.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();

			} catch (InternalException_Exception internalException_Exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						internalException_Exception, Level.ERROR);
				msg = new ActionMessage(internalException_Exception
						.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception invalidSessionException_Exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						invalidSessionException_Exception, Level.ERROR);
				msg = new ActionMessage(invalidSessionException_Exception
						.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} 

			/*
			 * Above will check whether user is in UserFinancial database or not
			 * If not goes back with errors
			 * 
			 * Below will bring Player object using email as provided by user in
			 * the form bean
			 */

			if (sessionId != null) {
				HttpSession session = request.getSession();
				SearchCriteria criteria = new SearchCriteria();
				criteria.setAttribute(G4GConstants.ATTR_EMAIL_ADDR, loginForm
						.getUsername());
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.SEARCHING_USER_AVAILABILTIY)
								.append(request.getSession().getId())
								.toString());
				List<PlayerDTO> list = PlayerServiceDelegator.getList(criteria);
				if (list.size() != G4GConstants.ZERO) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.LIST_IS_NOT_NULL).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).toString());
					Iterator<PlayerDTO> it = list.iterator();
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.UPDATING_SESSION).toString(),
							Level.INFO);
					userDTO.setPlayerDTO(it.next());
					PlayerDTO playerDTO = userDTO.getPlayerDTO();
					playerDTO.setLastlogintime(DataUtil.todayGMT());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.UPDATING).append(
									playerDTO.getEmailaddress()).append(
									G4GConstants.ONLINE_STATUS).toString(),
							Level.INFO);
					PlayerServiceDelegator.update(playerDTO);
					criteria.removeAllAttribute();
					criteria.setAttribute(G4GConstants.ID, playerDTO
							.getTimezone().getId());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.GETTING).append(
									G4GConstants.TIMEZONE).append(
									playerDTO.getEmailaddress()).append(
									G4GConstants.BLANK).append(
									request.getSession().getId()).toString(),
							Level.INFO);
					TimeZoneDTO timeZoneDTO = TimeZoneServiceDelegator.getList(
							criteria).size() > G4GConstants.ZERO ? TimeZoneServiceDelegator
							.getList(criteria).get(G4GConstants.ZERO)
							: null;
					criteria.removeAllAttribute();
					userDTO.setOffset(timeZoneDTO.getOffset());
					SearchListCriteria searchListCriteria = new SearchListCriteria();
					searchListCriteria.setAttribute(G4GConstants.IS_READ,
							new Object[] { false, SearchListCriteria.EQ });
					searchListCriteria.setAttribute(
							G4GConstants.IS_DELETED_BY_RECIEVER, new Object[] {
									false, SearchListCriteria.EQ });
					searchListCriteria.setAttribute(
							G4GConstants.IS_ARCHIEVED_BY_RECIEVER,
							new Object[] { false, SearchListCriteria.EQ });
					searchListCriteria.setAttribute(
							G4GConstants.PLAYERBYTOPLAYERID, new Object[] {
									userDTO.getPlayerDTO(),
									SearchListCriteria.EQ });
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.UPDATING).append(
									G4GConstants.MESSAGE_STATUS).append(
									G4GConstants.OF).append(
									playerDTO.getEmailaddress()).append(
									G4GConstants.BLANK).append(
									request.getSession().getId()).toString(),
							Level.INFO);
					userDTO.setMessageCount(MessageServiceDelegator
							.getCount(searchListCriteria));
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.UPDATING).append(
									G4GConstants.G4GSESSIONLISTENER).append(
									G4GConstants.ADDING_USERDTO).append(
									G4GConstants.OF).append(
									playerDTO.getEmailaddress()).append(
									G4GConstants.BLANK).append(
									request.getSession().getId()).toString(),
							Level.INFO);
					G4GSessionListener.addSession(session, userDTO);
					if(SubNationalCodeServiceDelegator.get(DataUtil.getInteger(userDTO.getUser().getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.FALSE)) {
						userDTO.setStateIllegal(true);
					}
					request.getSession().setAttribute(G4GConstants.USERDTO,
							userDTO);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									G4GConstants.SETTING_USERDTO_IN_REQUEST)
									.toString(), Level.INFO);
					forward = G4GConstants.LOGIN;
					// Control goes to Profile.jsp
				} else {
					AuditUtil
							.getInstance()
							.writeLog(
									AuditUtil.FILE_TYPE_G4G,
									new StringBuffer(this.getClass().getName())
											.append(G4GConstants.COLON_WITH_SPACES)
											.append(DataUtil.getCurrentMethod())
											.append(G4GConstants.COLON_WITH_SPACES)
											.append(
													G4GConstants.GOING_TO_REGISTER2)
											.toString());
					PlayerDTO dto = new PlayerDTO();
					dto.setEmailaddress(loginForm.getUsername());
					userDTO.setPlayerDTO(dto);
					request.getSession().setAttribute(G4GConstants.USERDTO,
							userDTO);
					msg = new ActionMessage(G4GConstants.ERRORS_LOGIN);
					error.clear();
					error.add(G4GConstants.FIRSTLOGIN, msg);
					request.getSession().setAttribute(
							G4GConstants.ATTR_SCREEN_NAME,
							G4GConstants.ATTR_SCREEN_NAME_VALUE);
					this.addErrors(request, error);
					return mapping.findForward(G4GConstants.FIRSTLOGIN);
				}
			} else {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.GOT_ERRORS).toString());
				msg = new ActionMessage(G4GConstants.ERRORS_LOGIN_FAILURE);
				error.clear();
				error.add(G4GConstants.USERNAME, msg);
				this.addErrors(request, error);
				loginForm.reset(mapping, request);
				return mapping.getInputForward();
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return mapping.findForward(forward);
	}
}
