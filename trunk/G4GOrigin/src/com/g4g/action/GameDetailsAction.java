/**********************************************************
 * GameDetailsAction.java :
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.OpenMatchDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.GameDetailsForm;
import com.g4g.forms.GameLobbyPageForm;
import com.g4g.servlet.WidgetsAjaxImpl;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The action class GameDetailsAction displays the details of the game that user
 * has selected.The gameDetails page also has widgets as search widget,
 * MyLobbies widget, playnow widget,Banners, active players widget. The user is
 * not able to ass the game if he does not have that console. User has to
 * purchase console to add the game in it. Or to challenge to play with any
 * user. <br>
 * XDoclet definition:.
 *
 * @struts.action path="/displayGameDetails" name="gameDetailsForm"
 *                scope="request"
 * @struts.action-forward name="success" path="/WebContent/gameDetails.jsp"
 * @author Punam
 */
public class GameDetailsAction extends BaseAction {

	/**
	 * The display method displays all the details about that game. Total No of
	 * players for the selected game, Number of players online for the game,
	 * what open matches are there for the game. Help topics, Multilayer
	 * support, Head to head FAQ is provided.All the widgets information is
	 * stored.
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
							G4GConstants.COLON_WITH_SPACES).append(G4GConstants.DASHES)
							.append(request.getSession().getId()).append(
									G4GConstants.USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		List<OpenMatchDTO> matchList = new ArrayList<OpenMatchDTO>();
		UserDTO userDTO;
		GameDTO gameDTO = null;
		int playerId = G4GConstants.ZERO;
		GameDetailsForm gameDetailsForm = new GameDetailsForm();
		int gameId =G4GConstants.ZERO;
		try {
			gameId = request.getParameter(G4GConstants.GAMEID) != null ? Integer
					.parseInt(request.getParameter(G4GConstants.GAMEID))
					: G4GConstants.ZERO;
			userDTO = DataUtil.getUserDTO(request);
			playerId = userDTO.getPlayerDTO().getId();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DISPLAY_STARTS).append(DASHES).append(
									G4GConstants.GETTING_DETAILS).append(
									request.getSession().getId()).toString());
			/* Getting game details as per the gameId */
			gameDTO = GameServiceDelegator.getGame(gameId);
			if(gameDTO == null){
				return mapping.findForward(G4GConstants.GAMELOBBY);
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DISPLAY_STARTS).append(DASHES).append(
									G4GConstants.THIS_IS_GAME).append(
									gameDTO.getGamename()).append(
									request.getSession().getId()).toString());

			/* Getting match details for openMatches tab */
			matchList = TwoPlayerTournamentServiceDelegator
					.getOpenMatchesDetails(gameId, playerId);
			/*
			 * Form field allPlayers id populated with players having this
			 * gameId
			 */
			gameDetailsForm.setAllPlayers(PlayerServiceDelegator
					.getGameWisePlayersList(gameId));
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.GAME_ID_IS).append(gameId).toString());

			request.setAttribute(G4GConstants.GAMEDETAILSFORM, gameDetailsForm);
		}catch(NumberFormatException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.COLON_WITH_SPACES).append(G4GConstants.DASHES)
							.append(request.getSession().getId()).append(
									"No Game with this id").append(
									e.getMessage()).toString(),
					Level.ERROR);
			return mapping.findForward(G4GConstants.GAMELOBBY);
		} catch (NullPointerException exception) {
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

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(DASHES).append(G4GConstants.SET_FORM_FIELDS)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		request.setAttribute(G4GConstants.ALLPLAYERSLIST,
				PlayerServiceDelegator.getGameWisePlayersList(gameId));
		request.setAttribute(G4GConstants.MATCHLIST, matchList);
		request.setAttribute(G4GConstants.GAMEDTO, gameDTO);
		/*if (WidgetsServiceDelegator.getActivePlayers(G4GConstants.MAX_RECORDS,
				G4GConstants.MAX_PLAYED_IN_DAYS).size() > G4GConstants.ZERO) {
			request.setAttribute(G4GConstants.ACTIVEPLAYERS, WidgetsAjaxImpl
					.getActivePlayers(G4GConstants.MAX_RECORDS,
							G4GConstants.MAX_PLAYED_IN_DAYS, request));
		} else {
			request.setAttribute(G4GConstants.ACTIVEPLAYERS, null);
		}*/
		GameLobbyPageForm gameLobbyForm = new GameLobbyPageForm();
		List<ActivePlayersDTO> activePlayersList = WidgetsServiceDelegator
		.getActivePlayers(G4GConstants.MAX_RECORDS, G4GConstants.MAX_PLAYED_IN_DAYS);
		gameLobbyForm.setActivePlayers(activePlayersList);

		List<GameDTO> xbox360GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_XBOX360_LIST_KEY);
		gameLobbyForm.setXbox360Games(xbox360GamesList);

		List<GameDTO> ps2GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS2_ID_LIST_KEY);
		gameLobbyForm.setPs2Games(ps2GamesList);

		List<GameDTO> ps3GamesList = WidgetsServiceDelegator.getMyLobby(
				playerId, G4GConstants.NETWORK_PS3_ID_LIST_KEY);
		gameLobbyForm.setPs3Games(ps3GamesList);

		request.setAttribute(G4GConstants.GAME_LOBBY_PAGE_FORM, gameLobbyForm);
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method redirects to home page.
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
						G4GConstants.COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.SUBMIT_STARTS).toString());
		return mapping.findForward(G4GConstants.HOMEPAGE);
	}

}
