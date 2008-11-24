/**********************************************************
 * Register2Action.java : 
 *
 * Created by Ankur
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.ADDING_USER_GAMES;
import static com.g4g.utils.G4GConstants.ATTR_SCREEN_NAME;
import static com.g4g.utils.G4GConstants.AVATAR_IMAGEID;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.GETTING_AVATAR_FROM_DB;
import static com.g4g.utils.G4GConstants.INTERRUPTED_PICTURE_UPLOAD;
import static com.g4g.utils.G4GConstants.INVALID_IMAGE_UPLOADED;
import static com.g4g.utils.G4GConstants.IOEXCEPTION_PICTURE_UPLOAD;
import static com.g4g.utils.G4GConstants.PICTURE;
import static com.g4g.utils.G4GConstants.REGISTER_NOIMAGE;
import static com.g4g.utils.G4GConstants.RESIZE_AND_PNG_CONVERSION;
import static com.g4g.utils.G4GConstants.REVIEW;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.TRUE;
import static com.g4g.utils.G4GConstants.UPDATE;
import static com.g4g.utils.G4GConstants.USERDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.Register2Form;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.ImageUtils;

/**
 * The Register2Action Class will display all the gaming information.Those
 * information will be redirected to register3 page. User have to fill all the
 * required information as gamer tag, console selection, game selection, avatar
 * selection, For the amount user wants to play for, phone number When all the
 * information is filled, the user will be redirected to next step of
 * registration.<br>
 * XDoclet definition:.
 * 
 * @struts.action path="/displayRegister2" name="register2Form" scope="session"
 * @struts.action-forward name="success" path="page.register2"
 * 
 * @struts.action path="/register2" name="register2Form"
 *                input="/displayRegister2.do" scope="session"
 * @struts.action-forward name="review" path="/displayRegister3.do"
 * @author Ankur
 */
public class Register2Action extends BaseAction {

	/**
	 * The display method will display information need to display on
	 * registration 2 page for registration to proceed.Pre-condition must be
	 * satisfied before displaying register2.jsp
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

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
				CALLINGMETHOD).append(DataUtil.getCallingMethod()).
						append(DISPLAY_STARTS).append(DASHES).append(
			
											request.getSession().getId()).toString());

		Register2Form register2Form = (Register2Form) form;

		UserDTO userRequest = DataUtil.getUserDTO(request);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DASHES).append(GETTING_AVATAR_FROM_DB).append(
								request.getSession().getId()).toString());
		

		// its set list for all games as per network		register2Form.setAvatarList((List<AvatarsDTO>)request.getSession().getServletContext().getAttribute(G4GOriginSession.AVATAR_LIST));
		
		register2Form.setNetworkList(register2Form, searchCriteria);
		UserDTO dto = DataUtil.getUserDTO(request);
		String update = (String) request.getAttribute(UPDATE);
		if (TRUE.equalsIgnoreCase(update)) {
			register2Form.populate(dto , request);
		}
		userRequest = (UserDTO) register2Form.getDTO(userRequest, request);
		if (!TRUE.equalsIgnoreCase(update)) {
			userRequest.setPlayernetworkDTO3ps2(new PlayernetworkDTO());
			userRequest.setPlayernetworkDTO4ps3(new PlayernetworkDTO());
			userRequest.setPlayernetworkDTOxbox360(new PlayernetworkDTO());
			userRequest.setPlayerGame(new HashSet<PlayergameDTO>());
		}
		
		
		return mapping.findForward(SUCCESS);
	}

	/**
	 * The method submit will redirect registration2page to registration3page.
	 * If all the information is correct and valid then it will be redirected to
	 * registration 3 page else it will be redirected to error page.
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse) Performs check when
	 *      register2 forms submits
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

		Register2Form register2Form = (Register2Form) form;

		FormFile pictureuploaded = register2Form.getPicture();
		byte[] userAvatarPicture;

		if (pictureuploaded != null && pictureuploaded.getFileSize() > 0) {
			try {
				userAvatarPicture = pictureuploaded.getFileData();
				if (userAvatarPicture != null) {
					if (!ImageUtils.isImage(userAvatarPicture)) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(CALLINGMETHOD).append(
												DataUtil.getCallingMethod())
										.append(DASHES).append(
												INVALID_IMAGE_UPLOADED).append(
												request.getSession().getId())
										.toString());
						msg = new ActionMessage(REGISTER_NOIMAGE);
						error.clear();
						error.add(AVATAR_IMAGEID, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					}
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(RESIZE_AND_PNG_CONVERSION).append(
											request.getSession().getId())
									.toString());
					userAvatarPicture = ImageUtils
							.getPngFormatImageByteArrayAfterResize(
									userAvatarPicture, 90, 90);
					request.getSession().setAttribute(PICTURE,
							userAvatarPicture);
				}
			} catch (FileNotFoundException exception) {

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
				msg = new ActionMessage(REGISTER_NOIMAGE);
				error.clear();
				error.add(AVATAR_IMAGEID, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (IOException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(IOEXCEPTION_PICTURE_UPLOAD).append(
										request.getSession().getId()).append(
										exception).toString());
				msg = new ActionMessage(REGISTER_NOIMAGE);
				error.clear();
				error.add(AVATAR_IMAGEID, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InterruptedException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(INTERRUPTED_PICTURE_UPLOAD).append(
										request.getSession().getId()).append(
										exception).toString());
				msg = new ActionMessage(REGISTER_NOIMAGE);
				error.clear();
				error.add(AVATAR_IMAGEID, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();

			}
		}

		UserDTO userRequest = DataUtil.getUserDTO(request);

		userRequest = (UserDTO) register2Form.getDTO(userRequest, request);

		/* Insertion of games */
		userRequest.getPlayerGame().clear();
		Set<PlayergameDTO> set = new HashSet<PlayergameDTO>();
		userRequest.setPlayerGame(set);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DASHES).append(ADDING_USER_GAMES).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		String[] xbox = register2Form.getSelectedxboxId();
		if (register2Form.getXbox() != null
				&& register2Form.getXboxLiveTag() != null) {
			if (xbox != null) {
				for (String element : xbox) {
					PlayergameDTO pgdto = new PlayergameDTO();
					pgdto.setGame(GameServiceDelegator.getGame(Integer
							.parseInt(element)));
					pgdto.setCreationdate(new Date());
					userRequest.getPlayerGame().add(pgdto);
				}
			}
		}

		String[] xbox360 = register2Form.getSelectedxbox360Id();
		if (xbox360 != null && register2Form.getXboxLiveTag() != null) {
			for (String element : xbox360) {
				PlayergameDTO pgdto = new PlayergameDTO();
				pgdto.setGame(GameServiceDelegator.getGame(Integer
						.parseInt(element)));
				pgdto.setCreationdate(new Date());
				userRequest.getPlayerGame().add(pgdto);
			}
		}

		String[] ps2 = register2Form.getSelectedps2Id();
		if (ps2 != null && register2Form.getPsNetTag()!=null) {
			for (String element : ps2) {
				PlayergameDTO pgdto = new PlayergameDTO();
				pgdto.setGame(GameServiceDelegator.getGame(Integer
						.parseInt(element)));
				pgdto.setCreationdate(new Date());
				userRequest.getPlayerGame().add(pgdto);
			}
		}

		String[] ps3 = register2Form.getSelectedps3Id();
		if (ps3 != null  && register2Form.getPsNetTag()!=null) {
			for (String element : ps3) {
				PlayergameDTO pgdto = new PlayergameDTO();
				pgdto.setGame(GameServiceDelegator.getGame(Integer
						.parseInt(element)));
				pgdto.setCreationdate(new Date());
				userRequest.getPlayerGame().add(pgdto);
			}
		}

		/* inserted all games */
		request.removeAttribute(USERDTO);
		request.getSession().setAttribute(USERDTO, userRequest);
		request.getSession().removeAttribute(ATTR_SCREEN_NAME);

		return mapping.findForward(REVIEW);
	}
}