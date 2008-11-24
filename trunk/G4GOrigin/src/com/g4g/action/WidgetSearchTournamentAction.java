package com.g4g.action;

import static com.g4g.utils.G4GConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchedTournament;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GamesUtil;
import com.g4g.utils.TournamentsUtil;

public class WidgetSearchTournamentAction extends BaseAction{

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageStartsAt = request.getParameter(G4GConstants.TOURNAMENTPAGESTARTSAT)!= null?request.getParameter(G4GConstants.TOURNAMENTPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String searchString = request.getParameter(G4GConstants.SEARCHSTRING)!= null?request.getParameter(G4GConstants.SEARCHSTRING):G4GConstants.NONE;
		String prevPage = request.getParameter(PREVTOURNAMENTPAGE)!= null?request.getParameter(PREVTOURNAMENTPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(NEXTTOURNAMENTPAGE)!= null?request.getParameter(NEXTTOURNAMENTPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(SHOWALLTOURNAMENT)!= null?request.getParameter(SHOWALLTOURNAMENT):G4GConstants.ZEROSTRING;
		String playerid = request.getParameter(G4GConstants.PLAYERID)!= null?request.getParameter(G4GConstants.PLAYERID):G4GConstants.ZEROSTRING;
		int playerId = Integer.parseInt(playerid);
		String allTournaments = request.getParameter(ALLTOURNAMENTS) != null ? request
				.getParameter(ALLTOURNAMENTS)
				: G4GConstants.ZEROSTRING;
		String offset = request.getParameter(USEROFFSET)!= null?request.getParameter(USEROFFSET):G4GConstants.ZEROSTRING;
		/* Populate the div for tournament */
		List<GameTournamentChallengeDTO> tournamentsList = new ArrayList<GameTournamentChallengeDTO>();
		List<SearchedTournament> searchedTournamentsList = new ArrayList<SearchedTournament>();
		if (WidgetsServiceDelegator.getTournamentList(searchString, Integer.parseInt(pageStartsAt)).size() > 0) {
			tournamentsList = WidgetsServiceDelegator.getTournamentList(searchString, Integer.parseInt(pageStartsAt));
			int totalTournaments = tournamentsList.size();
			for(int index=0; index < totalTournaments; index++){
				GameTournamentChallengeDTO gameTournamentChallengeDTO = tournamentsList.get(index);

				SearchedTournament searchedTournament = new SearchedTournament();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(gameTournamentChallengeDTO
						.getScheduledstartdate(), Integer.parseInt(offset) ),
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
			request.setAttribute(TOURNAMENTSEARCH_LIST,
					searchedTournamentsList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchString);
			request.setAttribute(NEXTTOURNAMENTPAGE, nextPage);
			request.setAttribute(PREVTOURNAMENTPAGE, prevPage);
			if(request.getParameter(MAXTOURNAMENTPAGES) != null) {
				request.setAttribute(MAXTOURNAMENTPAGES, request.getParameter(MAXTOURNAMENTPAGES));
			}
			int total = G4GConstants.ZERO;
			if (showAll.equals(G4GConstants.TRUE)) {
				total = Integer.parseInt(allTournaments);
			} else {
				if (totalTournaments > G4GConstants.PAGESIZE) {
					total = G4GConstants.PAGESIZE;
				} else {
					total = totalTournaments;
				}
			}
			request.setAttribute(TOTALTOURNAMENTS,String.valueOf(total));
			request.setAttribute(SHOWALLTOURNAMENT, showAll);
			request.setAttribute(OPENCHALLENGEPAGESTARTSAT, pageStartsAt);
			request.setAttribute(ALLTOURNAMENTS, allTournaments);
			request.setAttribute(USEROFFSET, offset);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName() ).append(
				COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
				COLON_WITH_SPACES ).append(TOURNAMENTLIST
				).append(DASHES ).append(searchedTournamentsList.size()).toString());


		return mapping.findForward(G4GConstants.SUCCESS);
	}

	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
