/**********************************************************
 * GameDetailsForm.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class GameDetailsForm for gameDetails Page. GameDetailsAction uses this
 * Form.It contains detailed description for particular game wise.
 * 
 * @author Punam
 */
public class GameDetailsForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The all players. */
	private List<PlayerDTO> allPlayers = new ArrayList<PlayerDTO>();

	/** The chat owner id. */
	private String chatOwnerId;

	/** The txt message. */
	private String txtMessage;

	/**
	 * Gets the chat owner id.
	 * 
	 * @return the chat owner id
	 */
	public String getChatOwnerId() {
		return chatOwnerId;
	}

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		return null;
	}

	/**
	 * Gets the txt message.
	 * 
	 * @return the txt message
	 */
	public String getTxtMessage() {
		return txtMessage;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
	}

	/**
	 * Sets the chat owner id.
	 * 
	 * @param chatOwnerId
	 *            the new chat owner id
	 */
	public void setChatOwnerId(String chatOwnerId) {
		this.chatOwnerId = chatOwnerId;
	}

	/**
	 * Sets the txt message.
	 * 
	 * @param txtMessage
	 *            the new txt message
	 */
	public void setTxtMessage(String txtMessage) {
		this.txtMessage = txtMessage;
	}

	/**
	 * Gets the all players.
	 * 
	 * @return the all players
	 */
	public List<PlayerDTO> getAllPlayers() {
		return allPlayers;
	}

	/**
	 * Sets the all players.
	 * 
	 * @param allPlayers
	 *            the new all players
	 */
	public void setAllPlayers(List<PlayerDTO> allPlayers) {
		this.allPlayers = allPlayers;
	}

}
