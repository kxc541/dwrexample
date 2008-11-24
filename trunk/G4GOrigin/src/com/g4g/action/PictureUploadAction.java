/**********************************************************
 * PictureUploadAction.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.g4g.delegator.AdminServiceDelegator;
import com.g4g.delegator.PicturesServiceDelegator;
import com.g4g.dto.AdminDTO;
import com.g4g.dto.PicturesDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.PictureForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The PictureUploadAction Class stores the picture selected by user in database
 * and displays the uploaded picture to user's picture tab.
 * 
 * @author Jigar Mistry
 */
public class PictureUploadAction extends BaseAction {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * All the information related to picture tab is displayed from profile
	 * action.
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
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				this.getClass().getName() + G4GConstants.DISPLAY_STARTS);

		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		return null;
	}

	/**
	 * The submit method will store the uploaded picture to database. It will
	 * display appropriate error messages. If picture is of large size it will
	 * display the error message, Picture should be of pictuer(.jpg/.png) format
	 * else the error message is displayed.
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				this.getClass().getName() + G4GConstants.SUBMIT_STARTS);

		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		PictureForm pictureForm = (PictureForm) form;
		request.getSession().setAttribute(G4GConstants.LOAD_PICTURE, true);
		try {
			// Process the FormFile
			FormFile myFile = pictureForm.getTheFile();
			String contentType = myFile.getContentType();
			String fileName = myFile.getFileName();
			int fileSize = myFile.getFileSize();

			byte[] fileData = myFile.getFileData();
			if (contentType != null
					&& contentType.startsWith(G4GConstants.IMAGE)) {
				if (fileData.length > G4GConstants.ZERO) {
					AdminDTO adminDTO = AdminServiceDelegator.getList().get(
							G4GConstants.ZERO);

					if (fileSize < adminDTO.getMaxpicsize()
							* G4GConstants.ONE_THOUSAND_TWENTY_FOUR
							* G4GConstants.ONE_THOUSAND_TWENTY_FOUR) {
						UserDTO userDTO = (UserDTO) request.getSession()
								.getAttribute(G4GConstants.USERDTO);
						PlayerDTO playerDTO = userDTO.getPlayerDTO();

						SearchCriteria userPicturesSearchCriteria = new SearchCriteria();
						userPicturesSearchCriteria.setAttribute(
								G4GConstants.USER_ID, playerDTO.getId());
						List<PicturesDTO> userPictureList = PicturesServiceDelegator
								.getList(userPicturesSearchCriteria);
						if (userPictureList.size() < adminDTO.getMaxpics()) {
							PicturesDTO picturesDTO = new PicturesDTO();
							picturesDTO.setPlayerDTO(playerDTO);
							picturesDTO.setImage(fileData);
							PicturesServiceDelegator.add(picturesDTO);
						} else {
							error.clear();
							msg = new ActionMessage(
									G4GConstants.ERRORS_PICTURE_SIZE);
							error.add(G4GConstants.PICTURE_MESSAGE, msg);
							this.addErrors(request, error);
							return mapping.getInputForward();
						}
					} else {
						error.clear();
						msg = new ActionMessage(
								G4GConstants.ERRORS_PICTURE_FILESIZE);
						error.add(G4GConstants.PICTURE_MESSAGE, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					}

					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.CONTENTTYPE).append(
									contentType).toString());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.FILENAME).append(fileName)
									.toString());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.FILESIZE).append(fileSize)
									.toString());
				}
			} else {
				error.clear();
				msg = new ActionMessage(G4GConstants.ERRORS_PICTURE_FORMAT);
				error.add(G4GConstants.PICTURE_MESSAGE, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
		} catch (Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(exception.getMessage())
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(exception.getMessage())
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getScreenname()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
		}
		return mapping.findForward(G4GConstants.SUCCESS);
	}
}