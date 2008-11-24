/**********************************************************
 * MessageCenterForm.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/

package com.g4g.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.MessageDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * MessageCenterForm contains attributes for message CenterAction.It contains
 * attributes as playerFriend subject, body... etc.
 * 
 * @struts.form name="messageCenterForm"
 * @author Jigar Mistry
 */
public class MessageCenterForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The player friend. */
	private String playerFriend;

	/** The subject. */
	private String subject;

	/** The body. */
	private String body;

	/** The sent messages. */
	private int sentMessages = 0;

	/** The new messages. */
	private int newMessages = 0;

	/** The friends. */
	private List<PlayerDTO> friends;

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
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
	 * Populates messageDTO with messageForm attribute.
	 * 
	 * @param request
	 *            the request
	 * 
	 * @return the dTO
	 */
	public BaseDTO getDTO(HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		MessageDTO dto = new MessageDTO();

		dto.getSkin().setSkinid(
				request.getSession().getAttribute(
						G4GConstants.SITE_ID_ATTRIBUTE).toString());
		dto.getPlayerByFromplayerid().setId(
				((UserDTO) request.getSession().getAttribute(
						G4GConstants.USERDTO)).getPlayerDTO().getId());
		dto.getPlayerByToplayerid().setId(Integer.parseInt(playerFriend));
		dto.setSubject(this.getSubject());
		dto.setBody(this.getBody());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.append(G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return dto;
	}

	/**
	 * Gets the friends.
	 * 
	 * @return the friends
	 */
	public List<PlayerDTO> getFriends() {
		return friends;
	}

	/**
	 * Gets the new messages.
	 * 
	 * @return the new messages
	 */
	public int getNewMessages() {
		return newMessages;
	}

	/**
	 * Gets the player friend.
	 * 
	 * @return the player friend
	 */
	public String getPlayerFriend() {
		return playerFriend;
	}

	/**
	 * Gets the sent messages.
	 * 
	 * @return the sent messages
	 */
	public int getSentMessages() {
		return sentMessages;
	}

	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
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
	 * Sets the body.
	 * 
	 * @param body
	 *            the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Sets the friends.
	 * 
	 * @param friends
	 *            the new friends
	 */
	public void setFriends(List<PlayerDTO> friends) {
		this.friends = friends;
	}

	/**
	 * Sets the new messages.
	 * 
	 * @param newMessages
	 *            the new new messages
	 */
	public void setNewMessages(int newMessages) {
		this.newMessages = newMessages;
	}

	/**
	 * Sets the player friend.
	 * 
	 * @param playerFriend
	 *            the new player friend
	 */
	public void setPlayerFriend(String playerFriend) {
		this.playerFriend = playerFriend;
	}

	/**
	 * Sets the sent messages.
	 * 
	 * @param sentMessages
	 *            the new sent messages
	 */
	public void setSentMessages(int sentMessages) {
		this.sentMessages = sentMessages;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
}