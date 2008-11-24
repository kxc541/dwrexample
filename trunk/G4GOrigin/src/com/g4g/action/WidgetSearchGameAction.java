package com.g4g.action;

import static com.g4g.utils.G4GConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.SearchedGame;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GamesUtil;

public class WidgetSearchGameAction extends BaseAction{

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/* Populate the div for Game */
		String pageStartsAt = request.getParameter(G4GConstants.GAMEPAGESTARTSAT)!= null?request.getParameter(G4GConstants.GAMEPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String searchString = request.getParameter(G4GConstants.SEARCHSTRING)!= null?request.getParameter(G4GConstants.SEARCHSTRING):G4GConstants.NONE;
		String prevPage = request.getParameter(PREVGAMEPAGE)!= null?request.getParameter(PREVGAMEPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(NEXTGAMEPAGE)!= null?request.getParameter(NEXTGAMEPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(SHOWALLGAME)!= null?request.getParameter(SHOWALLGAME):G4GConstants.ZEROSTRING;
		String playerid = request.getParameter(G4GConstants.PLAYERID)!= null?request.getParameter(G4GConstants.PLAYERID):G4GConstants.ZEROSTRING;
		int playerId = Integer.parseInt(playerid);
		String allGames = request.getParameter(ALLGAMES) != null ? request
				.getParameter(ALLGAMES)
				: G4GConstants.ZEROSTRING;
		List<GameDTO> gamesList = WidgetsServiceDelegator.getGamesList(searchString, Integer.parseInt(pageStartsAt));
		List<SearchedGame> searchedGamesList = new ArrayList<SearchedGame>();

		int totalGames = gamesList.size();
		if (gamesList.size() > G4GConstants.ZERO) {
			for(int index = G4GConstants.ZERO; index<totalGames; index++){
				GameDTO gameDTO = gamesList.get(index);
				SearchedGame searchedGame = new SearchedGame();
				boolean isGameAvailable = GamesUtil.isGameAvailable(playerId, gameDTO.getId());
				boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerId, gameDTO.getNetwork().getId());
				searchedGame.setGameDTO(gameDTO);
				searchedGame.setNetworkAvailable(isNetworkAvailable);
				searchedGame.setGameAvailable(isGameAvailable);
				SearchCriteria searchCriteria = new SearchCriteria();
				searchCriteria.setAttribute(G4GConstants.GAMEDTO, gameDTO);
				if(TwoPlayerTournamentServiceDelegator.getList(searchCriteria).size()>0){
					List<TwoplayertournamentDTO> tournamentList = TwoPlayerTournamentServiceDelegator.getList(searchCriteria);
					searchedGame.setTournamentList(tournamentList);
					searchedGame.setSize(tournamentList.size());
				}
				searchedGamesList.add(searchedGame);
			}
			request.setAttribute(GAMEPAGESTARTSAT, pageStartsAt);
			request.setAttribute(GAMESEARCH_LIST, searchedGamesList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchString);
			request.setAttribute(NEXTGAMEPAGE, nextPage);
			request.setAttribute(PREVGAMEPAGE, prevPage);
			if (request.getParameter(MAXGAMEPAGES) != null) {
				request.setAttribute(MAXGAMEPAGES, request
						.getParameter(MAXGAMEPAGES));
			}
			int total = G4GConstants.ZERO;
			if (showAll.equals(G4GConstants.TRUE)) {
				total = Integer.parseInt(allGames);
			} else {
				if (totalGames > G4GConstants.PAGESIZE) {
					total = G4GConstants.PAGESIZE;
				} else {
					total = totalGames;
				}
			}
			request.setAttribute(TOTALGAMES,String.valueOf(total));
			request.setAttribute(SHOWALLGAME, showAll);
			request.setAttribute(ALLGAMES, allGames);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName() ).append(
				COLON_WITH_SPACES ).append(GAMESLIST
				).append(DASHES ).append( searchedGamesList.size()).toString());

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
