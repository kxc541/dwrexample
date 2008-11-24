/**********************************************************
 * SearchAction.java :
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.SearchedGame;
import com.g4g.dto.SearchedPeople;
import com.g4g.dto.SearchedTournament;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.GameLobbyPageForm;
import com.g4g.forms.SearchForm;
import com.g4g.servlet.WidgetsAjaxImpl;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.FriendsUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GamesUtil;
import com.g4g.utils.TournamentsUtil;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The action class SearchAction gets the searchString from the searchForm finds
 * the related players, games, tournaments, openchallenges for it and displays
 * the result. If no results are available message is displayed.Search Action
 * forwards to searchResult page.<br>
 * XDoclet definition:.
 *
 * @struts.action action path="/search" name="searchForm" scope="request"
 * @struts.action-forward name="success" path="page.searchResult"
 * @author Punam
 */
public class SearchAction extends BaseAction {

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
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append(G4GConstants.CALLINGMETHOD) .append( DataUtil.getCallingMethod()) .append( G4GConstants.DISPLAY_STARTS ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString(),Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(
							request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		return mapping.findForward(SUCCESS);
	}

	/**
	 * The method submit gets the searchString from searchForm, finds the record
	 * for given searchString and displays the result.It checks the result is
	 * for which div and fills out the appropriate DIV.For Ex: if results are
	 * found for people, people div will fill out.
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
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName() ).append( CURRENTMETHOD ).append( DataUtil.getCurrentMethod()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS ).append( DASHES ).append( request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(DASHES ).append( request.getSession().getId()).toString());

		UserDTO userDTO = DataUtil.getUserDTO(request);
		PlayerDTO playerDTO = userDTO.getPlayerDTO();
		SearchForm searchForm = (SearchForm) form;
		request.setAttribute(G4GConstants.SEARCHSTRING, searchForm.getSearch());
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(BLANK
						).append(PLAYERSLIST
						).append(DASHES ).append( request.getSession().getId()).toString());

		/* Populate the div for people */
		int pageStartsAt = G4GConstants.ZERO;
		int maxPages = G4GConstants.ZERO;
		List<PlayerDTO> playersList = new ArrayList<PlayerDTO>();
		List<SearchedPeople> searchedPeopleList = new ArrayList<SearchedPeople>();
 		if (WidgetsServiceDelegator.getPlayersList(searchForm.getSearch(), pageStartsAt).size() > 0) {
 			playersList = WidgetsServiceDelegator.getPlayersList(searchForm.getSearch(), pageStartsAt);
 			int totalPlayers =  playersList.size();

 			for(int index = ZERO; index<totalPlayers; index++){
 				PlayerDTO player = playersList.get(index);
 				SearchedPeople searchedPeople = new SearchedPeople();
 				searchedPeople.setPlayerDTO(player);
 				boolean isFriend = FriendsUtil.isFriend(player.getId(), DataUtil.getUserIdInteger(request));
 				searchedPeople.setFriend(isFriend);
 				searchedPeople.setUser(null);
 				try {
 					User user = G4GFinancialDelegator.getUserInfo(player.getEmailaddress());
 					searchedPeople.setUser(user);
 				} catch (InternalException_Exception e) {
 	 				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e.getMessage(),Level.ERROR);
 	 			}
 	 			catch (UserNotFoundException_Exception e) {
 	 				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e.getMessage(),Level.ERROR);
 	 			}
 				searchedPeopleList.add(searchedPeople);
 			}

 			maxPages = (int) Math.ceil((double) playersList.size()
					/ G4GConstants.PAGESIZE);
 			request.setAttribute(G4GConstants.SEARCHSTRING, searchForm.getSearch());
			request.setAttribute(NEXTPEOPLEPAGE, ONESTRING);
			request.setAttribute(PREVPEOPLEPAGE, ONESTRING);
			int total = G4GConstants.ZERO;
			if(totalPlayers > G4GConstants.PAGESIZE ){
				total = G4GConstants.PAGESIZE;
			}else {
				total = totalPlayers;
			}
			request.setAttribute(TOTALPLAYERS, String.valueOf(total));
			request.setAttribute(ALLPLAYERS, String.valueOf(totalPlayers));
 			request.setAttribute(MAXPEOPLEPAGES,String.valueOf(maxPages));
			request.setAttribute(PEOPLESEARCH_LIST, searchedPeopleList);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(BLANK
						).append(GAMESLIST
						).append(DASHES ).append( request.getSession().getId()).toString());
		/* Populate the div for Game */
		List<GameDTO> gamesList = new ArrayList<GameDTO>();
		List<SearchedGame> searchedGamesList = new ArrayList<SearchedGame>();
		int playerId = DataUtil.getUserIdInteger(request);
		int totalGames = G4GConstants.ZERO;
		if (WidgetsServiceDelegator.getGamesList(searchForm.getSearch(), pageStartsAt).size() > G4GConstants.ZERO) {
			gamesList = WidgetsServiceDelegator.getGamesList(searchForm.getSearch(), pageStartsAt);
			totalGames = gamesList.size();
			for(int index = G4GConstants.ZERO; index<totalGames; index++){
				GameDTO gameDTO = gamesList.get(index);
				SearchedGame searchedGame = new SearchedGame();
				boolean isGameAvailable = GamesUtil.isGameAvailable(playerId, gameDTO.getId());
				boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerId, gameDTO.getNetwork().getId());
				searchedGame.setGameDTO(gameDTO);
				searchedGame.setNetworkAvailable(isNetworkAvailable);
				searchedGame.setGameAvailable(isGameAvailable);
				SearchCriteria searchCriteria = new SearchCriteria();
				searchCriteria.setAttribute(G4GConstants.GAMEDTO,gameDTO);
				if(TwoPlayerTournamentServiceDelegator.getList(searchCriteria).size()>0){
					List<TwoplayertournamentDTO> tournamentList = TwoPlayerTournamentServiceDelegator.getList(searchCriteria);
					searchedGame.setTournamentList(tournamentList);
					searchedGame.setSize(tournamentList.size());
				}
				searchedGamesList.add(searchedGame);
			}
			int total = G4GConstants.ZERO;
			if(totalGames > G4GConstants.PAGESIZE ){
				total = G4GConstants.PAGESIZE;
			}else{
				total = totalGames;
			}
			maxPages = (int) Math.ceil((double) totalGames
					/ G4GConstants.PAGESIZE);
			request.setAttribute(GAMESEARCH_LIST, searchedGamesList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchForm.getSearch());
			request.setAttribute(NEXTGAMEPAGE, ONESTRING);
			request.setAttribute(PREVGAMEPAGE, ONESTRING);
			request.setAttribute(MAXGAMEPAGES, String.valueOf(maxPages));
			request.setAttribute(ALLGAMES, String.valueOf(totalGames));
			request.setAttribute(TOTALGAMES,String.valueOf(total));
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(BLANK
						).append(TOURNAMENTLIST
						).append(DASHES ).append( request.getSession().getId()).toString());

		/* Populate the div for tournament */
		List<GameTournamentChallengeDTO> tournamentsList = new ArrayList<GameTournamentChallengeDTO>();
		List<SearchedTournament> searchedTournamentsList = new ArrayList<SearchedTournament>();
		if (WidgetsServiceDelegator.getTournamentList(searchForm.getSearch(), pageStartsAt).size() > 0) {
			tournamentsList = WidgetsServiceDelegator.getTournamentList(searchForm.getSearch(), pageStartsAt);
			int totalTournaments = tournamentsList.size();
			for(int index=0; index < totalTournaments; index++){
				GameTournamentChallengeDTO gameTournamentChallengeDTO = tournamentsList.get(index);

				SearchedTournament searchedTournament = new SearchedTournament();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(gameTournamentChallengeDTO
						.getScheduledstartdate(), userDTO.getOffset() ),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				GameDTO gameDTO = GameServiceDelegator.getGame(gameTournamentChallengeDTO.getGameid());
				boolean isGameAvailable = GamesUtil.isGameAvailable(playerId, gameTournamentChallengeDTO.getGameid());
				boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerId, gameDTO.getNetwork().getId());
				TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator.get(gameTournamentChallengeDTO.getTournamentid());
				int noOfPlayers = TournamentsUtil.getNoOfPlayers(twoplayertournamentDTO.getLevels());
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(twoplayertournamentDTO.getId());

				searchedTournament.setGameAvailable(isGameAvailable);
				searchedTournament.setNetworkAvailable(isNetworkAvailable);
				searchedTournament.setGameTournamentChallengeDTO(gameTournamentChallengeDTO);
				searchedTournament.setNoOfPlayers(noOfPlayers);
				searchedTournament.setGameOptionsList(gameOptionsList);
				searchedTournament.setScheduledStartDate(scheduledDate);
				searchedTournament.setTwoplayertournamentDTO(twoplayertournamentDTO);

				searchedTournamentsList.add(searchedTournament);
			}
			maxPages = (int) Math.ceil((double) totalTournaments
					/ G4GConstants.PAGESIZE);
			request.setAttribute(TOURNAMENTSEARCH_LIST,
					searchedTournamentsList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchForm.getSearch());
			request.setAttribute(NEXTTOURNAMENTPAGE, ONESTRING);
			request.setAttribute(PREVTOURNAMENTPAGE, ONESTRING);
			request.setAttribute(MAXTOURNAMENTPAGES, String.valueOf(maxPages));

			int total = G4GConstants.ZERO;
			if(totalTournaments > G4GConstants.PAGESIZE ){
				total = G4GConstants.PAGESIZE;
			}else{
				total = totalTournaments;
			}
			request.setAttribute(TOTALTOURNAMENTS,String.valueOf(total));
			request.setAttribute(ALLTOURNAMENTS, String.valueOf(totalTournaments));
			request.setAttribute(USEROFFSET, String.valueOf(userDTO.getOffset()));
		}


		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						COLON_WITH_SPACES ).append(
						FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(BLANK
						).append(OPENCHALLENGELIST
						).append(DASHES ).append( request.getSession().getId()).toString());

		/* Populate the div for openChallenge */
		List<GameTournamentChallengeDTO> openChallengesList = new ArrayList<GameTournamentChallengeDTO>();
		List<SearchedTournament> searchedOpenChallengesList = new ArrayList<SearchedTournament>();
		if (WidgetsServiceDelegator.getOpenChallengeList(searchForm.getSearch(), pageStartsAt).size() > 0) {
			openChallengesList = WidgetsServiceDelegator.getOpenChallengeList(searchForm.getSearch(), pageStartsAt);
			int totalOpenChallenges = openChallengesList.size();
			for(int index=0; index < totalOpenChallenges; index++){
				GameTournamentChallengeDTO gameTournamentChallengeDTO = openChallengesList.get(index);

				SearchedTournament searchedOpenChallenge = new SearchedTournament();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(gameTournamentChallengeDTO
						.getScheduledstartdate(), userDTO.getOffset() ),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				GameDTO gameDTO = GameServiceDelegator.getGame(gameTournamentChallengeDTO.getGameid());
				boolean isGameAvailable = GamesUtil.isGameAvailable(playerId, gameTournamentChallengeDTO.getGameid());
				boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerId, gameDTO.getNetwork().getId());
				TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator.get(gameTournamentChallengeDTO.getTournamentid());
				int noOfPlayers = TournamentsUtil.getNoOfPlayers(twoplayertournamentDTO.getLevels());
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(twoplayertournamentDTO.getId());

				searchedOpenChallenge.setGameAvailable(isGameAvailable);
				searchedOpenChallenge.setNetworkAvailable(isNetworkAvailable);
				searchedOpenChallenge.setGameTournamentChallengeDTO(gameTournamentChallengeDTO);
				searchedOpenChallenge.setNoOfPlayers(noOfPlayers);
				searchedOpenChallenge.setGameOptionsList(gameOptionsList);
				searchedOpenChallenge.setScheduledStartDate(scheduledDate);
				searchedOpenChallenge.setTwoplayertournamentDTO(twoplayertournamentDTO);

				searchedOpenChallengesList.add(searchedOpenChallenge);
			}
			maxPages = (int) Math.ceil((double) totalOpenChallenges
					/ G4GConstants.PAGESIZE);
			request.setAttribute(OPENCHALLENGESEARCH_LIST,
					searchedOpenChallengesList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchForm.getSearch());
			request.setAttribute(NEXTOPENCHALLENGEPAGE, ONESTRING);
			request.setAttribute(PREVOPENCHALLENGEPAGE, ONESTRING);
			request.setAttribute(MAXOPENCHALLENGEPAGES, String.valueOf(maxPages));
			int total = G4GConstants.ZERO;
			if(totalOpenChallenges > G4GConstants.PAGESIZE ){
				total = G4GConstants.PAGESIZE;
			}else{
				total = totalOpenChallenges;
			}
			request.setAttribute(TOTALOPENCHALLENGES,String.valueOf(total));
			request.setAttribute(ALLOPENCHALLENGES, String.valueOf(totalOpenChallenges));
			request.setAttribute(USEROFFSET, String.valueOf(userDTO.getOffset()));

		}
		 /** Active Players */
		GameLobbyPageForm gameLobbyForm = new GameLobbyPageForm();
		List<ActivePlayersDTO> activePlayersList = WidgetsServiceDelegator
		.getActivePlayers(G4GConstants.MAX_RECORDS, G4GConstants.MAX_PLAYED_IN_DAYS);
		gameLobbyForm.setActivePlayers(activePlayersList);

		List<GameDTO> xbox360GamesList = WidgetsServiceDelegator.getMyLobby(
				playerDTO.getId(), G4GConstants.NETWORK_XBOX360_LIST_KEY);
		gameLobbyForm.setXbox360Games(xbox360GamesList);

		List<GameDTO> ps2GamesList = WidgetsServiceDelegator.getMyLobby(
				playerDTO.getId(), G4GConstants.NETWORK_PS2_ID_LIST_KEY);
		gameLobbyForm.setPs2Games(ps2GamesList);

		List<GameDTO> ps3GamesList = WidgetsServiceDelegator.getMyLobby(
				playerDTO.getId(), G4GConstants.NETWORK_PS3_ID_LIST_KEY);
		gameLobbyForm.setPs3Games(ps3GamesList);

		request.setAttribute(G4GConstants.GAME_LOBBY_PAGE_FORM, gameLobbyForm);return mapping.findForward(SUCCESS);
	}
}

