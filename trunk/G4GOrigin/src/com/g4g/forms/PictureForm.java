/**********************************************************
 * PictureForm.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import org.apache.log4j.Level;
import org.apache.struts.upload.FormFile;

import com.g4g.dto.BaseDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * Form bean for Struts File Upload.It is used for uploading picture and
 * generates error message if image is blank.It has attributes as FormFile,
 * PictureMessage.PictureForm is used by PictureUploadAction.
 * 
 * @author Jigar Mistry
 */

public class PictureForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The file. */
	private FormFile theFile;

	/** The picture message. */
	private String pictureMessage;

	/**
	 * Gets the picture message.
	 * 
	 * @return the picture message
	 */
	public String getPictureMessage() {
		return pictureMessage;
	}

	/**
	 * Sets the picture message.
	 * 
	 * @param pictureMessage
	 *            the new picture message
	 */
	public void setPictureMessage(String pictureMessage) {
		this.pictureMessage = pictureMessage;
	}

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return null;
	}

	/**
	 * Gets the the file.
	 * 
	 * @return Returns the theFile.
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * Sets the the file.
	 * 
	 * @param theFile
	 *            The FormFile to set.
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
}