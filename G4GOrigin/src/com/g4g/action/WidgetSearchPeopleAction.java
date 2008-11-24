package com.g4g.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import static com.g4g.utils.G4GConstants.*;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchedPeople;
import com.g4g.forms.BaseForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.FriendsUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

public class WidgetSearchPeopleAction extends BaseAction {

	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pageStartsAt = request.getParameter(G4GConstants.PEOPLEPAGESTARTSAT) != null ? request
				.getParameter(G4GConstants.PEOPLEPAGESTARTSAT)
				: G4GConstants.ZEROSTRING;
		String searchString = request.getParameter(G4GConstants.SEARCHSTRING) != null ? request
				.getParameter(G4GConstants.SEARCHSTRING)
				: G4GConstants.NONE;
		String prevPage = request.getParameter(PREVPEOPLEPAGE) != null ? request
				.getParameter(PREVPEOPLEPAGE) : G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(NEXTPEOPLEPAGE) != null ? request
				.getParameter(NEXTPEOPLEPAGE) : G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(SHOWALLPEOPLE) != null ? request
				.getParameter(SHOWALLPEOPLE) : G4GConstants.FALSE;
		String allPlayers = request.getParameter(ALLPLAYERS) != null ? request
				.getParameter(ALLPLAYERS)
				: G4GConstants.ZEROSTRING;
		int pagestartsAt = Integer.parseInt(pageStartsAt);
		/* Populate the div for people */
		List<PlayerDTO> playersList = WidgetsServiceDelegator.getPlayersList(
				searchString, pagestartsAt);
		List<SearchedPeople> searchedPeopleList = new ArrayList<SearchedPeople>();
		int totalPlayers = playersList.size();
		int userid = G4GConstants.ZERO;
		if (request.getParameter(G4GConstants.USER_ID) != null) {
			String userId = request.getParameter(G4GConstants.USER_ID);
			userid = Integer.parseInt(userId);
		}
		for (int index = 0; index < totalPlayers; index++) {
			PlayerDTO playerDTO = playersList.get(index);
			SearchedPeople searchedPeople = new SearchedPeople();
			searchedPeople.setPlayerDTO(playerDTO);
			boolean isFriend = FriendsUtil.isFriend(playerDTO.getId(), userid);
			searchedPeople.setFriend(isFriend);
			try {
				User user = G4GFinancialDelegator.getUserInfo(playerDTO
						.getEmailaddress());
				searchedPeople.setUser(user);
			} catch (InternalException_Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						e.getMessage(), Level.ERROR);
			} catch (UserNotFoundException_Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						e.getMessage(), Level.ERROR);
			}
			searchedPeopleList.add(searchedPeople);
		}
		if (playersList.size() > 0) {
			request.setAttribute(PEOPLESEARCH_LIST, searchedPeopleList);
			request.setAttribute(G4GConstants.SEARCHSTRING, searchString);
			request.setAttribute(NEXTPEOPLEPAGE, nextPage);
			request.setAttribute(PREVPEOPLEPAGE, prevPage);
			if (request.getParameter(MAXPEOPLEPAGES) != null) {
				request.setAttribute(MAXPEOPLEPAGES, request
						.getParameter(MAXPEOPLEPAGES));
			}
			int total = G4GConstants.ZERO;
			if (showAll.equals(G4GConstants.TRUE)) {
				total = Integer.parseInt(allPlayers);
			} else {
				if (totalPlayers > G4GConstants.PAGESIZE) {
					total = G4GConstants.PAGESIZE;
				} else {
					total = totalPlayers;
				}
			}
			request.setAttribute(TOTALPLAYERS, String.valueOf(total));
			request.setAttribute(ALLPLAYERS, allPlayers);
			request.setAttribute(PEOPLEPAGESTARTSAT, pageStartsAt);
			request.setAttribute(SHOWALLPEOPLE, showAll);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(DataUtil.getCurrentMethod())
						.append(COLON_WITH_SPACES).append(FORPLAYER).append(
								DataUtil.getCallingMethod()).append(BLANK)
						.append(PLAYERSLIST).append(DASHES).append(
								playersList.size()).toString(), Level.ERROR);

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
