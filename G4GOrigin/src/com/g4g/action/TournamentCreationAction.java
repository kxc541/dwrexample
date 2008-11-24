/**********************************************************
 * TournamentCreationAction.java : 
 *
 * Created 04 Apr .08 by Ankur
 * Last modified Date: 
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.TournamentCreationDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.TournamentCreationForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;

/**
 * The action class TournamentCreationAction displays the details for tournament
 * Admin wants to create .TournamentCreationAction prepopulate all the games
 * with there network / console name On submmiting of the page it populates
 * tournamentDTO twoplayermatchDTO's TowplayertournamentgameoptionDTO's and add
 * them to table. It owns three methods display() : perform logic at the time of
 * displaying jsp submit() : performs logic at the time of submitting form add() :
 * for adding tournamentDTO twoplayermatchDTO's
 * TowplayertournamentgameoptionDTO's and populating few fields of these dto
 * 
 * @author ankur This Class is used to Creation date: 04-16-2008
 * 
 * XDoclet definition:
 * @struts.action path="/tounamentCreation" name="tounamentCreationForm"
 *                input="/tounamentCreation.jsp" scope="request" validate="true"
 */
public class TournamentCreationAction extends BaseAction {

	/**
	 * Populates gameList of TournamentCreationForm
	 * 
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @return ActionForward
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				this.getClass().getName() + G4GConstants.DISPLAY_STARTS);

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * task performed during Submission of TournamentCreationForm from
	 * tournamentCreation.jsp
	 * 
	 * @throws Exception
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				this.getClass().getName() + G4GConstants.DISPLAY_STARTS);
		TournamentCreationForm tournamentCreationForm = (TournamentCreationForm) form;

		if (G4GConstants.GO.equals(operation)) {
			TournamentCreationDTO dto = (TournamentCreationDTO) tournamentCreationForm
					.getDTO();
			try {
				if (dto.getTwoplayertournamentgameoptionsList() == null) {
					List<TwoplayertournamentgameoptionsDTO> twoplayertournamentgameoptionsList = new ArrayList<TwoplayertournamentgameoptionsDTO>();
					TwoplayertournamentgameoptionsDTO twoplayertournamentgameoptionsDTO = new TwoplayertournamentgameoptionsDTO();
					twoplayertournamentgameoptionsDTO.setOptionid(0);
					twoplayertournamentgameoptionsDTO.setValueid(0);
					dto
							.setTwoplayertournamentgameoptionsList(twoplayertournamentgameoptionsList);
				}
				add(dto);
			} catch (InternalException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
		}
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * Inserts data related to tournament in different tables It first creates a
	 * betId. If creation of bet throws any exception than stops further
	 * proceeding Inserts data in TwoplayertournamentDTO Updates
	 * TwoplayertournamentgameoptionsDTO : adds tournament id in that. Inserts
	 * data in TwoplayertournamentgameoptionsDTO Updates TwoplayermatchDTO :
	 * adds tournament id in that. Inserts data in TwoplayermatchDTO
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void add(TournamentCreationDTO dto) throws Exception {

		/** Creating betId and setting it to tournamentDTO */
		String betid = G4GFinancialDelegator.createBet();
		/** If i cann't create a bet than i will not go ahead */
		dto.getTwoplayertournamentDTO().setBetid(betid);

		/** Saving tournamentdto + updating same in TournamentCreationDTO */
		TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator
				.add(dto.getTwoplayertournamentDTO());

		/** Saving all game options while updating there tournamentid */
		for (TwoplayertournamentgameoptionsDTO twoplayertournamentgameoptionsDTO : dto
				.getTwoplayertournamentgameoptionsList()) {
			TwoplayertournamentgameoptionsDTO option = twoplayertournamentgameoptionsDTO;
			option.setTournamentid(twoplayertournamentDTO);
			TwoPlayerTournamentGameOptionServiceDelegator.add(option);
		}

		/** Saving twoplayermatchdto while updating tournamentid in them */
		for (TwoplayermatchDTO twoplayermatchDTO1 : dto.getTwoplayermatchList()) {
			TwoplayermatchDTO twoplayermatchDTO = twoplayermatchDTO1;
			twoplayermatchDTO.setTwoplayertournament(twoplayertournamentDTO);
			TwoPlayerMatchServiceDelegator.add(twoplayermatchDTO);
		}
	}
}