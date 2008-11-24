/**********************************************************
 * DirectedChallengeForm.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.SkinServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.Twoplayermatchcomments;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.ChallengeCardUpdateUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;

/**
 * The DirectedChallengeForm Class contains attribute for directedchallenge
 * page.It is used in DirectedChallenge2Action class, DirectedChallenge3Action
 * class, DirectedChallengeAction class. It contains attribute for challenge. It
 * contains attributes as commentList, twoPlayermatchid.
 * 
 * @author Ankur
 */

public class DirectedChallengeForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5019681216491500136L;

	/** The comment list. */
	private List<Twoplayermatchcomments> commentList;

	/** The twoplayermatchid. */
	private String twoplayermatchid;

	/** The comments. */
	private String comments;

	/** The tournament date. */
	private String tournamentDate;

	/** The player dto. */
	private PlayerDTO playerDTO;

	/** The challenger user. */
	private User challengerUser;

	/** The opp user. */
	private User oppUser;

	/** The opp player dto. */
	private PlayerDTO oppPlayerDTO;

	/** The game dto. */
	private GameDTO gameDTO;

	/** The amount field. */
	private String amountField;

	/** The game console. */
	private String gameConsole;

	/** The game option selected. */
	private String[] gameOptionSelected;

	/** The game setting. */
	private String[] gameSetting;

	/** The gameoption dto. */
	List<GameoptionsDTO> gameoptionDTO = new ArrayList<GameoptionsDTO>();

	/** The twoplayermatch dto. */
	private TwoplayermatchDTO twoplayermatchDTO;

	/** The game options. */
	private List<Object> gameOptions;

	/** The schduled date. */
	Date schduledDate;

	/**
	 * Gets the amount field.
	 * 
	 * @return amountField
	 */
	public String getAmountField() {
		return amountField;
	}

	/**
	 * Gets the game console.
	 * 
	 * @return gameConsole
	 */
	public String getGameConsole() {
		return gameConsole;
	}

	/**
	 * Gets the game dto.
	 * 
	 * @return gameDTO
	 */
	public GameDTO getGameDTO() {
		return gameDTO;
	}

	/**
	 * Gets the gameoption dto.
	 * 
	 * @return gameoptionDTO
	 */
	public List<GameoptionsDTO> getGameoptionDTO() {
		return gameoptionDTO;
	}

	/**
	 * Gets the game options.
	 * 
	 * @return gameOptions
	 */
	public List<Object> getGameOptions() {
		return gameOptions;
	}

	/**
	 * Gets the game option selected.
	 * 
	 * @return gameOptionSelected
	 */
	public String[] getGameOptionSelected() {
		return gameOptionSelected;
	}

	/**
	 * Gets the game setting.
	 * 
	 * @return gameSetting
	 */
	public String[] getGameSetting() {
		return gameSetting;
	}

	/**
	 * Gets the opp player dto.
	 * 
	 * @return oppPlayerDTO
	 */
	public PlayerDTO getOppPlayerDTO() {
		return oppPlayerDTO;
	}

	/**
	 * Gets the player dto.
	 * 
	 * @return playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}

	/**
	 * Gets the tournament date.
	 * 
	 * @return tournamentDate
	 */
	public String getTournamentDate() {
		return tournamentDate;
	}

	/**
	 * Sets the amount field.
	 * 
	 * @param amountField
	 *            the amount field
	 */
	public void setAmountField(String amountField) {
		this.amountField = amountField;
	}

	/**
	 * Sets the game console.
	 * 
	 * @param gameConsole
	 *            the game console
	 */
	public void setGameConsole(String gameConsole) {
		this.gameConsole = gameConsole;
	}

	/**
	 * Sets the game dto.
	 * 
	 * @param gameDTO
	 *            the game dto
	 */
	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}

	/**
	 * Sets the gameoption dto.
	 * 
	 * @param gameoptionDTO
	 *            the gameoption dto
	 */
	public void setGameoptionDTO(List<GameoptionsDTO> gameoptionDTO) {
		this.gameoptionDTO = gameoptionDTO;
	}

	/**
	 * Sets the game options.
	 * 
	 * @param gameOptions
	 *            the game options
	 */
	public void setGameOptions(List<Object> gameOptions) {
		this.gameOptions = gameOptions;
	}

	/**
	 * Sets the game option selected.
	 * 
	 * @param gameOptionSelected
	 *            the game option selected
	 */
	public void setGameOptionSelected(String[] gameOptionSelected) {
		this.gameOptionSelected = gameOptionSelected;
	}

	/**
	 * Sets the game setting.
	 * 
	 * @param gameSetting
	 *            the game setting
	 */
	public void setGameSetting(String[] gameSetting) {
		this.gameSetting = gameSetting;
	}

	/**
	 * Sets the opp player dto.
	 * 
	 * @param oppPlayerDTO
	 *            the opp player dto
	 */
	public void setOppPlayerDTO(PlayerDTO oppPlayerDTO) {
		this.oppPlayerDTO = oppPlayerDTO;
	}

	/**
	 * Sets the player dto.
	 * 
	 * @param playerDTO
	 *            the player dto
	 */
	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	/**
	 * Sets the tournament date.
	 * 
	 * @param tournamentDate
	 *            the tournament date
	 */
	public void setTournamentDate(String tournamentDate) {
		this.tournamentDate = tournamentDate;
	}

	/**
	 * Gets the schduled date.
	 * 
	 * @return schduledDate
	 */
	public Date getSchduledDate() {
		return schduledDate;
	}

	/**
	 * Sets the schduled date.
	 * 
	 * @param schduledDate
	 *            the schduled date
	 */
	public void setSchduledDate(Date schduledDate) {
		this.schduledDate = schduledDate;
	}

	/**
	 * Populate.
	 * 
	 * @param baseDto
	 *            the base dto
	 * 
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		// get the challenging player from session
		PlayerDTO playerDTO = (PlayerDTO) baseDto;
		this.setPlayerDTO(playerDTO);

	}

	/**
	 * Gets the dto.
	 * 
	 * @return the DTO
	 * 
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
	 * Populate.
	 * 
	 * @param baseDto
	 *            the base dto
	 * @param twoplayermatchid
	 *            the twoplayermatchid
	 */
	public void populate(BaseDTO baseDto, int twoplayermatchid) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
		twoplayermatchDTO.setId(twoplayermatchid);
		twoplayermatchDTO = TwoPlayerMatchServiceDelegator
				.get(twoplayermatchDTO);
		this.setTwoplayermatchDTO(twoplayermatchDTO);
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
	 * Gets the twoplayertournament dto.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form
	 * 
	 * @return TwoplayertournamentDTO
	 * 
	 * @throws InternalException_Exception
	 *             No check for the presence of oppPlayer.
	 */
	public TwoplayertournamentDTO getTwoplayertournamentDTO(
			HttpServletRequest request, DirectedChallengeForm form)
			throws InternalException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
		TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
		PlayerDTO playerDTO = form.getPlayerDTO();
		PlayerDTO oppPlayerDTO = form.getOppPlayerDTO();

		// skinId

		if (oppPlayerDTO != null
				&& playerDTO.getSkin().getSkinid().equals(
						oppPlayerDTO.getSkin().getSkinid())) {
			twoplayertournamentDTO.setSkinDTO(SkinServiceDelegator
					.getSkinDTO(oppPlayerDTO.getSkin().getSkinid()));
		} else {
			twoplayertournamentDTO.setSkinDTO(SkinServiceDelegator
					.getSkinDTO(null));
		}
		// betid
		try {
			int twoplayermatchid = (Integer) request.getSession().getAttribute(
					G4GConstants.TWO_PLAYER_MATCH_ID);
			twoplayermatchDTO.setId(twoplayermatchid);
			twoplayermatchDTO = TwoPlayerMatchServiceDelegator
					.get(twoplayermatchDTO);
			twoplayertournamentDTO.setId(twoplayermatchDTO
					.getTwoplayertournament().getId());
		} catch (NumberFormatException e) {
			int twoplayermatchid = Integer.parseInt((String) request
					.getSession()
					.getAttribute(G4GConstants.TWO_PLAYER_MATCH_ID));
			twoplayermatchDTO.setId(twoplayermatchid);
			twoplayermatchDTO = TwoPlayerMatchServiceDelegator
					.get(twoplayermatchDTO);
			twoplayertournamentDTO.setId(twoplayermatchDTO
					.getTwoplayertournament().getId());
		} catch (Exception Tournament_Yet_TO_Create) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(G4GConstants.SESSIONID)
							.append(request.getSession().getId()).append(
									G4GConstants.DASHES).append(
									Tournament_Yet_TO_Create).toString(),
					Level.ERROR);
		}
		if (twoplayertournamentDTO != null
				&& twoplayertournamentDTO.getBetid() == null) {
			twoplayertournamentDTO.setBetid(G4GFinancialDelegator.createBet());
		}
		twoplayertournamentDTO.setLevels(1);
		// entryfee
		twoplayertournamentDTO.setEntryfee(Double.parseDouble(form
				.getAmountField()));
		// gameId
		twoplayertournamentDTO.setGameDTO(GameServiceDelegator.getGame(form
				.getGameDTO().getId()));
		// creationdate
		double houseFee = ChallengeCardUpdateUtil
				.getHouseFee(twoplayertournamentDTO.getEntryfee());

		twoplayertournamentDTO.setHousefeeperplayer(houseFee);
		twoplayertournamentDTO.setCreationdate(DataUtil.todayGMT());

		// created by
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return twoplayertournamentDTO;
	}

	/**
	 * Gets the twoplayermatch dto.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form
	 * @param twoplayertournamentDTO
	 *            the twoplayertournament dto
	 * 
	 * @return TwoplayermatchDTO
	 */

	public TwoplayermatchDTO getTwoplayermatchDTO(HttpServletRequest request,
			DirectedChallengeForm form,
			TwoplayertournamentDTO twoplayertournamentDTO) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
		twoplayermatchDTO.setTwoplayertournament(twoplayertournamentDTO);
		twoplayermatchDTO.setLevel(twoplayertournamentDTO.getLevels());
		twoplayermatchDTO.setPlayeroneid(form.getPlayerDTO());
		twoplayermatchDTO.setPlayeroneaccepteddate(twoplayertournamentDTO
				.getCreationdate());
		try {
			if (form.getOppPlayerDTO() != null) {
				twoplayermatchDTO.setPlayertwoid(form.getOppPlayerDTO());
			}
		} catch (NullPointerException nullPointerException) {
			// No need to show it user as user knows there will be no opposite
			// player for open match
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					nullPointerException);
		}
		twoplayermatchDTO.setPayoutamount(2
				* (twoplayertournamentDTO.getEntryfee()
				- twoplayertournamentDTO.getHousefeeperplayer()));
		// set scheduled start date
		twoplayermatchDTO.setScheduledstartdate(form.getSchduledDate());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.append(G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return twoplayermatchDTO;
	}

	/**
	 * Gets the twoplayermatch dto.
	 * 
	 * @return twoplayermatchDTO
	 */
	public TwoplayermatchDTO getTwoplayermatchDTO() {
		return twoplayermatchDTO;
	}

	/**
	 * Sets the twoplayermatch dto.
	 * 
	 * @param twoplayermatchDTO
	 *            the twoplayermatch dto
	 */
	public void setTwoplayermatchDTO(TwoplayermatchDTO twoplayermatchDTO) {
		this.twoplayermatchDTO = twoplayermatchDTO;
	}

	/**
	 * Gets the comments.
	 * 
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments
	 *            the comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the comment list.
	 * 
	 * @return commentList
	 */
	public List<Twoplayermatchcomments> getCommentList() {
		return commentList;
	}

	/**
	 * Sets the comment list.
	 * 
	 * @param commentList
	 *            the comment list
	 */
	public void setCommentList(List<Twoplayermatchcomments> commentList) {
		this.commentList = commentList;
	}

	/**
	 * Gets the twoplayermatchid.
	 * 
	 * @return twoplayermatchid
	 */
	public String getTwoplayermatchid() {
		return twoplayermatchid;
	}

	/**
	 * Sets the twoplayermatchid.
	 * 
	 * @param twoplayermatchid
	 *            the twoplayermatchid
	 */
	public void setTwoplayermatchid(String twoplayermatchid) {
		this.twoplayermatchid = twoplayermatchid;
	}

	/**
	 * Gets the challenger user.
	 * 
	 * @return challengerUser
	 */
	public User getChallengerUser() {
		return challengerUser;
	}

	/**
	 * Sets the challenger user.
	 * 
	 * @param challengerUser
	 *            the challenger user
	 */
	public void setChallengerUser(User challengerUser) {
		this.challengerUser = challengerUser;
	}

	/**
	 * Gets the opp user.
	 * 
	 * @return oppUser
	 */
	public User getOppUser() {
		return oppUser;
	}

	/**
	 * Sets the opp user.
	 * 
	 * @param oppUser
	 *            the opp user
	 */
	public void setOppUser(User oppUser) {
		this.oppUser = oppUser;
	}

}