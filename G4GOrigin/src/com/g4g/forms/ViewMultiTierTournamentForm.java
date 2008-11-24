/**********************************************************
 * ViewMultiTierTournamentForm.java : 
 *
 * Created 15 Feb .o8 by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/

package com.g4g.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * Form contains properties for viewMultiTireTournament page.It is passed as
 * Form in ViewMultiTierTournament action.
 * 
 * @author Punam
 */
public class ViewMultiTierTournamentForm extends BaseForm {

	TournamentGameDTO tournamentGameDTO = new TournamentGameDTO();
	TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
	List<TwoplayermatchDTO> twoPlayerMatchList = new ArrayList<TwoplayermatchDTO>();
	List<PlayerDTO> playesList = new ArrayList<PlayerDTO>();
	GameDTO gameDTO = new GameDTO();

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4058595384743360873L;

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return null;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @return the tournamentGameDTO
	 */
	public TournamentGameDTO getTournamentGameDTO() {
		return this.tournamentGameDTO;
	}

	/**
	 * @param tournamentGameDTO
	 *            the tournamentGameDTO to set
	 */
	public void setTournamentGameDTO(TournamentGameDTO tournamentGameDTO) {
		this.tournamentGameDTO = tournamentGameDTO;
	}

	/**
	 * @return the twoplayertournamentDTO
	 */
	public TwoplayertournamentDTO getTwoplayertournamentDTO() {
		return this.twoplayertournamentDTO;
	}

	/**
	 * @param twoplayertournamentDTO
	 *            the twoplayertournamentDTO to set
	 */
	public void setTwoplayertournamentDTO(
			TwoplayertournamentDTO twoplayertournamentDTO) {
		this.twoplayertournamentDTO = twoplayertournamentDTO;
	}

	/**
	 * @return the twoPlayerMatchList
	 */
	public List<TwoplayermatchDTO> getTwoPlayerMatchList() {
		return this.twoPlayerMatchList;
	}

	/**
	 * @param twoPlayerMatchList
	 *            the twoPlayerMatchList to set
	 */
	public void setTwoPlayerMatchList(List<TwoplayermatchDTO> twoPlayerMatchList) {
		this.twoPlayerMatchList = twoPlayerMatchList;
	}

	/**
	 * @return the playesList
	 */
	public List<PlayerDTO> getPlayesList() {
		return this.playesList;
	}

	/**
	 * @param playesList
	 *            the playesList to set
	 */
	public void setPlayesList(List<PlayerDTO> playesList) {
		this.playesList = playesList;
	}

	/**
	 * @return the gameDTO
	 */
	public GameDTO getGameDTO() {
		return this.gameDTO;
	}

	/**
	 * @param gameDTO
	 *            the gameDTO to set
	 */
	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}
}