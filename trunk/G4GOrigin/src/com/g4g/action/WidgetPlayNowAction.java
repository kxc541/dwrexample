package com.g4g.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayNowOpenMatches;
import com.g4g.dto.PlayNowOpenTournaments;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;

public class WidgetPlayNowAction extends BaseAction {

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.DISPLAY_STARTS)
						.append(G4GConstants.DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		int playerId = Integer.parseInt(request
				.getParameter(G4GConstants.PLAYERID));
		String offset = request.getParameter(G4GConstants.USEROFFSET) != null ? request
				.getParameter(G4GConstants.USEROFFSET)
				: G4GConstants.ZEROSTRING;
		int offSet = Integer.parseInt(offset);
		List<PlayNowDTO> playNowList = new ArrayList<PlayNowDTO>();
		List<PlayNowOpenMatches> openMatchesList = new ArrayList<PlayNowOpenMatches>();
		if(WidgetsServiceDelegator.getPlayNowList(playerId).size() > 0){
			playNowList = WidgetsServiceDelegator.getPlayNowList(playerId);
			int totalOpenMatches = playNowList.size();
			for(int index=0;index < totalOpenMatches; index++){
				PlayNowDTO playNowDTO = playNowList.get(index);
				PlayNowOpenMatches playNowOpenMatches = new PlayNowOpenMatches();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(playNowDTO
						.getScheduleDate(), offSet),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				TwoplayermatchDTO twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getDTOFromId(playNowDTO.getMatchId());
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(twoplayermatchDTO.getTwoplayertournament().getId());
				String encryptedId = DataUtil.encrypt(String.valueOf(playNowDTO.getGameId()));

				playNowOpenMatches.setEncryptedId(encryptedId);
				playNowOpenMatches.setGameOptionsList(gameOptionsList);
				playNowOpenMatches.setPlayNowDTO(playNowDTO);
				playNowOpenMatches.setScheduledDate(scheduledDate);
				playNowOpenMatches.setTwoplayermatchDTO(twoplayermatchDTO);

				openMatchesList.add(playNowOpenMatches);
			}
			request.setAttribute(G4GConstants.PLAYNOWLIST, openMatchesList);
		}
		List<TournamentGameDTO> openTournamentList = new ArrayList<TournamentGameDTO>();
		List<PlayNowOpenTournaments> tournamentsList = new ArrayList<PlayNowOpenTournaments>();
		if(TwoPlayerTournamentServiceDelegator.getTournamentActions().size() > 0) {
			openTournamentList = TwoPlayerTournamentServiceDelegator.getTournamentActions();
			int totalOpenTournaments = openTournamentList.size();
			for(int index = 0; index< totalOpenTournaments; index++){
				TournamentGameDTO tournamentGameDTO = openTournamentList.get(index);
				PlayNowOpenTournaments playNowOpenTournaments = new PlayNowOpenTournaments();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(tournamentGameDTO
						.getScheduledstartdate(), offSet),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(tournamentGameDTO.getTwoplayertournamentDTO().getId());
				String encryptedId = DataUtil.encrypt(String.valueOf(tournamentGameDTO.getGameDTO().getId()));

				playNowOpenTournaments.setEncryptedId(encryptedId);
				playNowOpenTournaments.setTournamentGameDTO(tournamentGameDTO);
				playNowOpenTournaments.setScheduledDate(scheduledDate);
				playNowOpenTournaments.setGameOptionsList(gameOptionsList);

				tournamentsList.add(playNowOpenTournaments);
			}
			request.setAttribute(G4GConstants.OPENTOURNAMENTLIST, tournamentsList);
		}

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
