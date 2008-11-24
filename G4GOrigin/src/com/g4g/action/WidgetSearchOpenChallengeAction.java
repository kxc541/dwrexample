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
import com.g4g.forms.SearchForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GamesUtil;
import com.g4g.utils.TournamentsUtil;

public class WidgetSearchOpenChallengeAction extends BaseAction{

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchForm searchForm = (SearchForm)form;
		/* Populate the div for openChallenge */
		String pageStartsAt = request.getParameter(G4GConstants.OPENCHALLENGEPAGESTARTSAT)!= null?request.getParameter(G4GConstants.OPENCHALLENGEPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String searchString = request.getParameter(G4GConstants.SEARCHSTRING)!= null?request.getParameter(G4GConstants.SEARCHSTRING):G4GConstants.NONE;
		String prevPage = request.getParameter(PREVOPENCHALLENGEPAGE)!= null?request.getParameter(PREVOPENCHALLENGEPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(NEXTOPENCHALLENGEPAGE)!= null?request.getParameter(NEXTOPENCHALLENGEPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(SHOWALLOPENCHALLENGE)!= null?request.getParameter(SHOWALLOPENCHALLENGE):G4GConstants.ZEROSTRING;
		String playerid = request.getParameter(G4GConstants.PLAYERID)!= null?request.getParameter(G4GConstants.PLAYERID):G4GConstants.ZEROSTRING;
		int playerId = Integer.parseInt(playerid);
		String allOpenChallenges = request.getParameter(ALLOPENCHALLENGES) != null ? request
				.getParameter(ALLOPENCHALLENGES)
				: G4GConstants.ZEROSTRING;
		String offset = request.getParameter(USEROFFSET)!= null?request.getParameter(USEROFFSET):G4GConstants.ZEROSTRING;
		/* Populate the div for tournament */
		List<GameTournamentChallengeDTO> openChallengesList = new ArrayList<GameTournamentChallengeDTO>();
		List<SearchedTournament> searchedOpenChallengesList = new ArrayList<SearchedTournament>();
		if (WidgetsServiceDelegator.getOpenChallengeList(searchString, Integer.parseInt(pageStartsAt)).size() > 0) {
			openChallengesList = WidgetsServiceDelegator.getOpenChallengeList(searchString, Integer.parseInt(pageStartsAt));
			int totalOpenChallenges = openChallengesList.size();
			for(int index=0; index < totalOpenChallenges; index++){
				GameTournamentChallengeDTO gameTournamentChallengeDTO = openChallengesList.get(index);

				SearchedTournament searchedOpenChallenge = new SearchedTournament();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(gameTournamentChallengeDTO
						.getScheduledstartdate(), Integer.parseInt(offset)),
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
			request.setAttribute(OPENCHALLENGESEARCH_LIST,
					searchedOpenChallengesList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchString);
			request.setAttribute(NEXTOPENCHALLENGEPAGE, nextPage);
			request.setAttribute(PREVOPENCHALLENGEPAGE, prevPage);
			request.setAttribute(OPENCHALLENGEPAGESTARTSAT, pageStartsAt);
			if(request.getParameter(MAXOPENCHALLENGEPAGES) != null) {
				request.setAttribute(MAXOPENCHALLENGEPAGES, request.getParameter(MAXOPENCHALLENGEPAGES));
			}
			int total = G4GConstants.ZERO;
			if(totalOpenChallenges > G4GConstants.PAGESIZE ){
				total = G4GConstants.PAGESIZE;
			}else{
				total = totalOpenChallenges;
			}
			request.setAttribute(TOTALTOURNAMENTS,String.valueOf(total));
			request.setAttribute(SHOWALLOPENCHALLENGE, showAll);
			request.setAttribute(ALLOPENCHALLENGES, allOpenChallenges);
			request.setAttribute(USEROFFSET, offset);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName() ).append(
				COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
				COLON_WITH_SPACES ).append(OPENCHALLENGELIST
				).append(DASHES ).append(searchedOpenChallengesList.size()).toString());

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
