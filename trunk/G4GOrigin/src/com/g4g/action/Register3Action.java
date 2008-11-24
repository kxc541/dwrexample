package com.g4g.action;

import static com.g4g.utils.G4GConstants.ADD_USER_TONETWORK;
import static com.g4g.utils.G4GConstants.AMBERSAND;
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.BRACKETLEFT;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CAPTCHA;
import static com.g4g.utils.G4GConstants.CHECKING_CAPTCHA_RESPONSE;
import static com.g4g.utils.G4GConstants.CHECKING_EMAIL_FIANCIALSYSTEM;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CONVERTING_COUNTRY_ISO;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DEFAULT_SITE_ID;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.EDIT;
import static com.g4g.utils.G4GConstants.EQUALS;
import static com.g4g.utils.G4GConstants.ERROR;
import static com.g4g.utils.G4GConstants.ERROR_CAPTCHA;
import static com.g4g.utils.G4GConstants.FORPLAYER;
import static com.g4g.utils.G4GConstants.GET_USERINFO;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.ID;
import static com.g4g.utils.G4GConstants.MAIL_REGISTER_PATH;
import static com.g4g.utils.G4GConstants.MAIL_REGISTER_SUBJECT;
import static com.g4g.utils.G4GConstants.PASSWORD;
import static com.g4g.utils.G4GConstants.PICTURE;
import static com.g4g.utils.G4GConstants.REGISTERINGUSER;
import static com.g4g.utils.G4GConstants.REGISTERINGUSER_FINANCIAL;
import static com.g4g.utils.G4GConstants.REGISTERINGUSER_LOCAL;
import static com.g4g.utils.G4GConstants.SAVE;
import static com.g4g.utils.G4GConstants.SCREENNAME_DB;
import static com.g4g.utils.G4GConstants.SENDING_MAIL;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.TRUE;
import static com.g4g.utils.G4GConstants.TRYING_TO_GET_USERINFO;
import static com.g4g.utils.G4GConstants.TRYING_TO_LOGIN;
import static com.g4g.utils.G4GConstants.UPDATE;
import static com.g4g.utils.G4GConstants.UPLOADING_PICTURE;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USERIPSTRINGFAILS;
import static com.g4g.utils.G4GConstants.USER_ID;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;
import static com.g4g.utils.G4GConstants.USER_RESPONSE;
import static com.g4g.utils.G4GConstants.edit;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.NationalCodeServiceDelegator;
import com.g4g.delegator.PicturesServiceDelegator;
import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.dto.PicturesDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.MailHelper;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.AuthenticationException_Exception;
import com.impessa.worldgaming.client.Balance;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.PlayerStatusException_Exception;
import com.impessa.worldgaming.client.SiteNotFoundException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;
import com.impessa.worldgaming.client.UserValidationException_Exception;

/**
 * The Class Register3Action.
 *
 * @author ankur
 */
public class Register3Action extends BaseAction {

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
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DISPLAY_STARTS).append(DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DISPLAY_STARTS).append(DASHES).append(
									USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(FORPLAYER).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getEmailaddress()).append(DASHES)
						.append(request.getSession().getId()).append(
								DISPLAY_STARTS).toString());

		return mapping.findForward(SUCCESS);
	}

	/**
	 * @throws Exception
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(
								SUBMIT_STARTS).append(DASHES).append(request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(SUBMIT_STARTS).append(DASHES).append(
									USER_NOT_RECOGNIZE).append(request.getSession().getId()).toString(),Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES).append(FORPLAYER).append(
								DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()).append(DASHES)
						.append(request.getSession().getId()).append(SUBMIT_STARTS).toString());

		// remember that we need an id to validate!

		String captchaId = request.getSession().getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY).toString();
		// retrieve the response
		String captchaResponse = request.getParameter(CAPTCHA);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES).append(CHECKING_CAPTCHA_RESPONSE).append(captchaId).append(USER_RESPONSE).append(captchaResponse).append(
								FORPLAYER).append(DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()).append(DASHES).append(request.getSession().getId()).append(SUBMIT_STARTS).toString());

		// Call the Service method
		UserDTO userRequest = DataUtil.getUserDTO(request);
		if (SAVE.equalsIgnoreCase(operation)) {
			if (!captchaId.equals(captchaResponse)) {
				ActionErrors error = new ActionErrors();
				ActionMessage msg = new ActionMessage(ERROR_CAPTCHA);
				error.add(CAPTCHA, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
							.append(REGISTERINGUSER).append(FORPLAYER).append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getEmailaddress()).append(DASHES)
							.append(request.getSession().getId()).toString());
			userRequest.getPlayerDTO().setRecordwins(0);
			userRequest.getPlayerDTO().setRecordlosses(0);
			userRequest.getPlayerDTO().setReputation(0);
			userRequest.getPlayerDTO().setLastlogintime(new Date());
			userRequest.getPlayerDTO().setIsonline(true);
			// userRequest.getPlayerDTO().setSkinid()
			try {
				userRequest.getUser().setSiteId(
						userRequest.getPlayerDTO().getSkin().getSkinid());
				User user = userRequest.getUser();

				user = Register3Action.setUserCountry(user);

				String username = null;
				try {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
									.append(COLON_WITH_SPACES).append(
											CHECKING_EMAIL_FIANCIALSYSTEM)
									.append(FORPLAYER).append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getEmailaddress()).append(
											DASHES).append(
											request.getSession().getId())
									.toString());
					username = G4GFinancialDelegator.getUserInfo(
							user.getEmail()).getEmail();
				} catch (UserNotFoundException_Exception exception) {
					// no need to show to user as this is for check that user is
					// already registered with financial services
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(
									exception.getMessage()).append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(
									exception.getMessage()).append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
				}
				if (username == null) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
									.append(COLON_WITH_SPACES).append(
											REGISTERINGUSER_FINANCIAL).append(
											FORPLAYER).append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getEmailaddress()).append(
											DASHES).append(
											request.getSession().getId())
									.toString());
					//Temporary changed to uppercase
					user.setPostalcode(user.getPostalcode().toUpperCase());
					user.setAddress((user.getAddress().replace(G4GConstants.DOT_TEMP, G4GConstants.BLANK).replace(G4GConstants.DASH_TEMP, G4GConstants.BLANK)));

					G4GFinancialDelegator.registerUser(user);
				}
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
								.append(COLON_WITH_SPACES).append(REGISTERINGUSER_LOCAL)
								.append(FORPLAYER).append(
										DataUtil.getUserDTO(request)
												.getPlayerDTO()
												.getEmailaddress()).append(
										DASHES).append(
										request.getSession().getId())
								.toString());
				PlayerServiceDelegator.add(userRequest.getPlayerDTO());
				userRequest.setPlayerDTO(PlayerServiceDelegator.getPlayer(userRequest.getPlayerDTO().getId()));
				byte[] userAvatarPicture = null;
				if (request.getSession().getAttribute(PICTURE) != null) {
					userAvatarPicture = (byte[]) request.getSession().getAttribute(PICTURE);
				}
				if (userAvatarPicture != null && userAvatarPicture.length > 0) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES).append(UPLOADING_PICTURE).append(FORPLAYER).append(
											DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()).append(DASHES).append(request.getSession().getId()).toString());
					PicturesDTO picturesDTO = new PicturesDTO();
					picturesDTO.setImage(userAvatarPicture);
					picturesDTO.setPlayerDTO(userRequest.getPlayerDTO());
					PicturesServiceDelegator.add(picturesDTO);
					userRequest.getPlayerDTO().setPictureId(picturesDTO.getId());
					userRequest.getPlayerDTO().setAvatars(null);
					PlayerServiceDelegator.update(userRequest.getPlayerDTO());
				}

				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
								.append(COLON_WITH_SPACES).append(SENDING_MAIL).append(
										FORPLAYER).append(
										DataUtil.getUserDTO(request)
												.getPlayerDTO()
												.getEmailaddress()).append(
										DASHES).append(
										request.getSession().getId())
								.toString());

				MailHelper mailHeper = new MailHelper();
				StringBuffer URL = new StringBuffer();
				URL.append(DataUtil.getLocalBasePath(request));
				URL.append(MAIL_REGISTER_PATH);
				URL.append(SCREENNAME_DB).append(EQUALS).append(
						userRequest.getPlayerDTO().getScreenname());
				URL.append(AMBERSAND).append(USER_ID).append(EQUALS).append(
						user.getEmail());
				URL.append(AMBERSAND).append(PASSWORD).append(EQUALS).append(
						user.getPassword());
				String message = DataUtil.getURLContent(URL.toString());
				mailHeper.sendMail(G4GProperties
						.getProperty(PropertiesConstants.G4G_MAIL_FROM),
						userRequest.getUser().getEmail(), resource
								.getString(MAIL_REGISTER_SUBJECT), message);
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (SiteNotFoundException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserValidationException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (MessagingException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);

			} catch (IOException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
			}

			userRequest.getPlayernetworkDTOxbox().setPlayer(
					userRequest.getPlayerDTO());
			userRequest.getPlayernetworkDTOxbox360().setPlayer(
					userRequest.getPlayerDTO());
			userRequest.getPlayernetworkDTO3ps2().setPlayer(
					userRequest.getPlayerDTO());
			userRequest.getPlayernetworkDTO4ps3().setPlayer(
					userRequest.getPlayerDTO());

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
							.append(ADD_USER_TONETWORK).append(BLANK).append(
									FORPLAYER).append(DASHES).append(
									request.getSession().getId()).toString(),
					Level.INFO);

			if (!DataValidator.isNull(userRequest.getPlayernetworkDTOxbox()
					.getPlayernetworktag())) {
				PlayerNetworkServiceDelegator.add(userRequest
						.getPlayernetworkDTOxbox());
			}

			if (!DataValidator.isNull(userRequest.getPlayernetworkDTOxbox360()
					.getPlayernetworktag())) {
				PlayerNetworkServiceDelegator.add(userRequest
						.getPlayernetworkDTOxbox360());
			}

			if (!DataValidator.isNull(userRequest.getPlayernetworkDTO3ps2()
					.getPlayernetworktag())) {
				PlayerNetworkServiceDelegator.add(userRequest
						.getPlayernetworkDTO3ps2());
			}

			if (!DataValidator.isNull(userRequest.getPlayernetworkDTO4ps3()
					.getPlayernetworktag())) {
				PlayerNetworkServiceDelegator.add(userRequest
						.getPlayernetworkDTO4ps3());
			}

			// add playergames

			for (PlayergameDTO playergameDTO1 : userRequest.getPlayerGame()) {
				PlayergameDTO playergameDTO = playergameDTO1;
				playergameDTO.setPlayer(PlayerServiceDelegator
						.getPlayer(userRequest.getPlayerDTO().getId()));
				PlayerGameServiceDelegator.add(playergameDTO);
			}

			String sessionId;
			try {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						TRYING_TO_GET_USERINFO, Level.INFO);
				userRequest.setUser(G4GFinancialDelegator
						.getUserInfo(userRequest.getPlayerDTO()
								.getEmailaddress()));
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						GET_USERINFO, Level.INFO);
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						TRYING_TO_LOGIN, Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						userRequest.getUser().getEmail()
								+ userRequest.getUser().getPassword(),
						Level.INFO);

				// SiteId and IP address given statically as currently we
				// don't
				// have implemented it.
				String userIPString = USERIPSTRINGFAILS;
				try {
					InetAddress userIPAddress = InetAddress.getLocalHost();
					if (userIPAddress != null) {
						userIPString = userIPAddress.getHostAddress();
					}
				} catch (Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
									.append(COLON_WITH_SPACES).append(e.getMessage())
									.append(DASHES).append(
											request.getSession().getId())
									.toString());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(DASHES).append(
									request.getSession().getId()).append(e)
									.toString(), Level.INFO);
				}
				sessionId = G4GFinancialDelegator.login(userRequest.getUser()
						.getEmail(), userRequest.getUser().getPassword(),
						DEFAULT_SITE_ID, userIPString);
			} catch (UserNotFoundException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (AuthenticationException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (PlayerStatusException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			userRequest.setSessionId(sessionId);
			Balance balance;
			try {

				balance = G4GFinancialDelegator.getBalance(sessionId);
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception e) {
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
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			userRequest.setBalance(DataUtil.getImpassaMoney(balance
					.getBankBalance().getAmount())
					+ DataUtil.getImpassaMoney(balance.getWinningsBalance()
							.getAmount()));
			userRequest.setStateIllegal(SubNationalCodeServiceDelegator.get(Integer.parseInt(userRequest.getUser().getState())).getIslegal().equalsIgnoreCase(G4GConstants.FALSE));
			// clear session's mapping attribute;
			request.getSession().removeAttribute(mapping.getAttribute());
			return mapping.findForward(SAVE);
		}

		if (edit.equals(operation)) {
			request.setAttribute(UPDATE, TRUE);
			return mapping.findForward(edit);
		}
		if (EDIT.equals(operation)) {
			request.getSession().setAttribute(BRACKETLEFT, TRUE);
			return mapping.findForward(EDIT);
		}
		return null;
	}

	/**
	 * Sets the user country.
	 *
	 * @param user
	 *            the user
	 *
	 * @return the user
	 */
	public static User setUserCountry(User user) {
		try {

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(Register3Action.class.getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DASHES).append(CONVERTING_COUNTRY_ISO)
							.toString(), Level.INFO);
			int countryId = Integer.parseInt(user.getCountry());
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(ID, countryId);
			user.setCountry(NationalCodeServiceDelegator
					.getList(searchCriteria).get(0).getNationcode());
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, e);
		}
		return user;
	}

}
