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

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;
import static com.g4g.utils.G4GConstants.ZERO;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.GameLobbyPageForm;
import com.g4g.servlet.WidgetsAjaxImpl;
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
public class GameLobbyPageAction extends BaseAction {

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
		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.COLON_WITH_SPACES).append(G4GConstants.FORPLAYER)
							.append(
									DataUtil.getUserDTO(request).getPlayerDTO()
											.getEmailaddress()).append(
									G4GConstants.DASHES).append(
									request.getSession().getId()).append(
									G4GConstants.USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		GameLobbyPageForm gameLobbyform = (GameLobbyPageForm) form;
		SearchCriteria searchCriteria = new SearchCriteria();
		UserDTO userDTO = DataUtil.getUserDTO(request);
		int playerId = userDTO.getPlayerDTO().getId();
		// searchCriteria.setAttribute("playerid", 1);
		searchCriteria.setAttribute(G4GConstants.PLAYER,
				new PlayerDTO(playerId));

		// passsing playerDTO id to PlayerGame to bring playerGame
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DISPLAY_STARTS).append(DASHES).append(
								G4GConstants.GETTING_PLAYERGAME).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		List<PlayergameDTO> playerGame = PlayerGameServiceDelegator
				.getList(searchCriteria);

		// Initialize a array of player game size
		String[] playerGameId = new String[playerGame.size()];

		// store playerId in that array

		Iterator<PlayergameDTO> it = playerGame.iterator();
		int i = ZERO;
		while (it.hasNext()) {
			playerGameId[i] = String.valueOf(it.next().getGame().getId());
			i++;
		}

		// Storing all gameId from playerGame to this Formbean array
		gameLobbyform.setPlayerGameId(playerGameId);

		GameDTO dto = new GameDTO();

		// utilize playergameid array to bring list from the gamedto and
		// populate mygameList
		for (i = ZERO; i < playerGameId.length; i++) {
			dto = GameServiceDelegator.getGame(Integer
					.parseInt(playerGameId[i]));
			gameLobbyform.getMyGame().add(dto);
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DISPLAY_STARTS).append(DASHES).append(
								G4GConstants.SET_PLAYERWIDGETS).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		/*
		 * if
		 * (WidgetsServiceDelegator.getActivePlayers(G4GConstants.MAX_RECORDS,G4GConstants.MAX_PLAYED_IN_DAYS).size() >
		 * ZERO) { request.setAttribute(G4GConstants.ACTIVEPLAYERS,
		 * WidgetsAjaxImpl.getActivePlayers(G4GConstants.MAX_RECORDS,G4GConstants.MAX_PLAYED_IN_DAYS,
		 * request)); } else { request.setAttribute(G4GConstants.ACTIVEPLAYERS,
		 * null); }
		 */
		List<ActivePlayersDTO> activePlayersList = WidgetsServiceDelegator
		.getActivePlayers(G4GConstants.MAX_RECORDS, G4GConstants.MAX_PLAYED_IN_DAYS);
		gameLobbyform.setActivePlayers(activePlayersList);
		List<GameDTO> xbox360GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_XBOX360_LIST_KEY);
		gameLobbyform.setXbox360Game(xbox360GamesList);
		
		List<GameDTO> ps2GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS2_ID_LIST_KEY);
		gameLobbyform.setPs2Game(ps2GamesList);
		
		List<GameDTO> ps3GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS3_ID_LIST_KEY);
		gameLobbyform.setPs3Game(ps3GamesList);
		
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
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod())
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								SUBMIT_STARTS).append(DASHES).append(
								request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(SUBMIT_STARTS).append(DASHES).append(
									USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		return mapping.findForward(G4GConstants.SUCCESS);
	}
}