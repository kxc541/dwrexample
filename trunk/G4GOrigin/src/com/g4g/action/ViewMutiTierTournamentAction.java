/**********************************************************
 * ViewMutiTierTournamentAction.java : 
 *
 * Created 15 Feb .08 by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.FORPLAYER;
import static com.g4g.utils.G4GConstants.GAMELIST;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.PLAYER;
import static com.g4g.utils.G4GConstants.PLAYERS_IN_TOURNAMENT;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.TOURNAMENTDETAILS;
import static com.g4g.utils.G4GConstants.TOURNAMENTID;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT;
import static com.g4g.utils.G4GConstants.UNPLAYED_TOURNAMENT_LIST;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.TournamentsUtil;

/**
 * The action class ViewMutiTierTournamentAction displays the details for
 * tournament user wants to view.ViewMutiTierTournamentAction displays all the
 * matches for the tournament displayed. User can join the tournament match if
 * match does not have both the players. ViewMutiTierTournamentAction also
 * displays the avatars for the number of players who has joined the tournament.
 * User can visit game details page through the displayed viewMutiTierTournament
 * page. ViewMutiTierTournamentAction sets attribute to display all the
 * information of the selected tournament.
 * 
 * XDoclet definition:.
 * 
 * @struts.action path="/displayViewMutiTierTournament"
 *                name="viewMultiTierTournamentForm" scope="request"
 * @struts.action-forward name="success" path="page.viewMultiTierTournament"
 */
public class ViewMutiTierTournamentAction extends BaseAction {

	/**
	 * The method display sets all the attributes to display the selected
	 * tournament.
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
						.append(request.getSession().getId()).toString());
		UserDTO userDTO = DataUtil.getUserDTO(request);
		PlayerDTO playerDTO = userDTO.getPlayerDTO();
		int tournamentId = Integer.parseInt(request.getParameter(TOURNAMENTID));
		List<TournamentGameDTO> tournamentGamesList;
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(UNPLAYED_TOURNAMENT_LIST).append(DASHES)
						.append(request.getSession().getId()).toString());
		TournamentGameDTO tournamentGameDTO = new TournamentGameDTO();
		TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator.get(tournamentId);
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(TWO_PLAYER_TOURNAMENT,
				twoplayertournamentDTO);
		tournamentGameDTO
				.setTwoPlayerMatchList(TwoPlayerMatchServiceDelegator
						.getList(searchCriteria));
		List<GameoptionsDTO> GameOptionsList =  TwoPlayerTournamentServiceDelegator
		.getGameOptionsOfTournament(tournamentId);
		tournamentGameDTO.setGameDTO(twoplayertournamentDTO.getGameDTO());
		tournamentGameDTO.setTwoplayertournamentDTO(twoplayertournamentDTO);
		tournamentGameDTO
				.setGameOptionsList(new HashSet<GameoptionsDTO>(GameOptionsList));
		tournamentGameDTO
				.setScheduledstartdate(TwoPlayerTournamentServiceDelegator
						.getScheduledstartDate(tournamentId));
		tournamentGameDTO.setNo_of_players(TournamentsUtil
				.getNoOfPlayers(twoplayertournamentDTO.getLevels()));

		/* Getting all the games of current user */
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(PLAYER, playerDTO);
		List<PlayergameDTO> gameList = PlayerGameServiceDelegator
				.getList(searchCriteria);
		// Sets all the attributes if tournaments are available
		request.setAttribute(TOURNAMENTDETAILS, tournamentGameDTO);
		request.setAttribute(PLAYERS_IN_TOURNAMENT,
				TwoPlayerTournamentServiceDelegator
						.getPlayersOfTournament(tournamentId));
		request.setAttribute(GAMELIST, gameList);
		return mapping.findForward(SUCCESS);
	}

	/**
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

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(DataUtil.getCurrentMethod()).append(COLON_WITH_SPACES)
						.append(FORPLAYER).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getEmailaddress()).append(DASHES)
						.append(request.getSession().getId()).toString());

		return mapping.findForward(SUCCESS);
	}
}