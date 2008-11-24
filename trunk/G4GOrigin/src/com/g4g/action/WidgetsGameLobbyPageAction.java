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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.GameLobbyPageForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The action class GameLobbyPageAction displays all the games available at
 * World gaming. These games are seprated by the console names and the games
 * user ahs picked up. Game lobby is also called GamePicker. User can add/remove
 * the games to his tab. Game Lobby page also contains widgets as search widget,
 * MyLobbies widget, playnow widget,Banners, active players widget.
 *
 * XDoclet definition:.
 *
 * @struts.action path="/gameLobbyPageAction" name="gameLobbyPageForm"
 *                scope="request"
 * @struts.action-forward name="success" path="/WebContent/gameLobbyPage.jsp"
 */
public class WidgetsGameLobbyPageAction extends BaseAction {

	/**
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
		GameLobbyPageForm gameLobbyPageForm = (GameLobbyPageForm) form;
		int playerId = Integer.parseInt(request.getParameter(G4GConstants.PLAYERID));

		List<GameDTO> xbox360GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_XBOX360_LIST_KEY);
		gameLobbyPageForm.setXbox360Game(xbox360GamesList);

		List<GameDTO> ps2GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS2_ID_LIST_KEY);
		gameLobbyPageForm.setPs2Game(ps2GamesList);

		List<GameDTO> ps3GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS3_ID_LIST_KEY);
		gameLobbyPageForm.setPs3Game(ps3GamesList);

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method forwards to success mapping.
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
		return null;
	}
}