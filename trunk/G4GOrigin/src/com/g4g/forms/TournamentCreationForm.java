/***********************************************************************************************************************
 * TournamentCreationForm.java : $ Source: Form for Tournament Creation. Created : 16 apr. 08 by ankur Last modified $
 * Date: $ by $ Author: $ Revision: $ Revision: $ Version : $ ID : 1$ Copyright (c) 2008 - 2008 Askdigi, All rights
 * reserved.
 **********************************************************************************************************************/

package com.g4g.forms;

import static com.g4g.utils.G4GConstants.AT;
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAMEID_DB;
import static com.g4g.utils.G4GConstants.INVALID_INTER_LEVEL2SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_INTER_LEVEL3SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_INTER_LEVEL4SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_INTER_LEVEL5SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_INTER_LEVEL6SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL1SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL2SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL3SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL4SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL5SCHEDULE;
import static com.g4g.utils.G4GConstants.INVALID_LEVEL6SCHEDULE;
import static com.g4g.utils.G4GConstants.OPTION;
import static com.g4g.utils.G4GConstants.PENDING;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.VALUE_SEQUENCE_ID;
import static com.g4g.utils.G4GConstants.WORLD_GAMING_SITE_ID;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.SkinServiceDelegator;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TournamentCreationDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;

/**
 * @author Ankur Creation date: 04-16-2008 This Class contains field that represents all the fields of
 *         tournamentCreation.jsp It has methods to populate various DTO's and list It also perform manual validation at
 *         the time of submission of the form For the sake of the one who had created jsp the name of the form in struts
 *         config is 'theForm' and not tournamentCreationForm
 * @struts.form name="theForm"
 */
public class TournamentCreationForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1637021561186844087L;

	ActionMessage msg;

	String level1startdate;

	String level1enddate;

	String level2startdate;

	String level2enddate;

	String level3startdate;

	String level3enddate;

	String level4startdate;

	String level4enddate;

	String level5startdate;

	String level5enddate;

	String level6startdate;

	String level6enddate;

	private String tournamentGame;

	private String tournamentLevels;

	private String entryfee;

	private String tournamentFee;

	private String levels;

	private String houseFeePerPlayer;

	private String[] gameOptions;

	private String[] gameOptionSelected;

	private List<GameDTO> gameList = GameServiceDelegator.getList();

	private String totalpot;

	private String level1Amount;

	private String level1StartDate;

	private String level1StartTime;

	private String level1EndDate;

	private String level1EndTime;

	private String level2Amount;

	private String level2StartDate;

	private String level2StartTime;

	private String level2EndDate;

	private String level2EndTime;

	private String level3Amount;

	private String level3StartDate;

	private String level3StartTime;

	private String level3EndDate;

	private String level3EndTime;

	private String level4Amount;

	private String level4StartDate;

	private String level4StartTime;

	private String level4EndDate;

	private String level4EndTime;

	private String level5Amount;

	private String level5StartDate;

	private String level5StartTime;

	private String level5EndDate;

	private String level5EndTime;

	private String level6Amount;

	private String level6StartDate;

	private String level6StartTime;

	private String level6EndDate;

	private String level6EndTime;

	/**
	 * @return the gameList
	 */
	public List<GameDTO> getGameList() {
		return this.gameList;
	}

	/**
	 * @return the level1Amount
	 */
	public String getLevel1Amount() {
		return this.level1Amount;
	}

	/**
	 * @param level1Amount
	 *            the level1Amount to set
	 */
	public void setLevel1Amount(String level1Amount) {
		this.level1Amount = level1Amount;
	}

	/**
	 * @return the level1StartDate
	 */
	public String getLevel1StartDate() {
		return this.level1StartDate;
	}

	/**
	 * @param level1StartDate
	 *            the level1StartDate to set
	 */
	public void setLevel1StartDate(String level1StartDate) {
		this.level1StartDate = level1StartDate;
	}

	/**
	 * @return the level1StartTime
	 */
	public String getLevel1StartTime() {
		return this.level1StartTime;
	}

	/**
	 * @param level1StartTime
	 *            the level1StartTime to set
	 */
	public void setLevel1StartTime(String level1StartTime) {
		this.level1StartTime = level1StartTime;
	}

	/**
	 * @return the level1EndDate
	 */
	public String getLevel1EndDate() {
		return this.level1EndDate;
	}

	/**
	 * @param level1EndDate
	 *            the level1EndDate to set
	 */
	public void setLevel1EndDate(String level1EndDate) {
		this.level1EndDate = level1EndDate;
	}

	/**
	 * @return the level1EndTime
	 */
	public String getLevel1EndTime() {
		return this.level1EndTime;
	}

	/**
	 * @param level1EndTime
	 *            the level1EndTime to set
	 */
	public void setLevel1EndTime(String level1EndTime) {
		this.level1EndTime = level1EndTime;
	}

	/**
	 * @return the level2Amount
	 */
	public String getLevel2Amount() {
		return this.level2Amount;
	}

	/**
	 * @param level2Amount
	 *            the level2Amount to set
	 */
	public void setLevel2Amount(String level2Amount) {
		this.level2Amount = level2Amount;
	}

	/**
	 * @return the level2StartDate
	 */
	public String getLevel2StartDate() {
		return this.level2StartDate;
	}

	/**
	 * @param level2StartDate
	 *            the level2StartDate to set
	 */
	public void setLevel2StartDate(String level2StartDate) {
		this.level2StartDate = level2StartDate;
	}

	/**
	 * @return the level2StartTime
	 */
	public String getLevel2StartTime() {
		return this.level2StartTime;
	}

	/**
	 * @param level2StartTime
	 *            the level2StartTime to set
	 */
	public void setLevel2StartTime(String level2StartTime) {
		this.level2StartTime = level2StartTime;
	}

	/**
	 * @return the level2EndDate
	 */
	public String getLevel2EndDate() {
		return this.level2EndDate;
	}

	/**
	 * @param level2EndDate
	 *            the level2EndDate to set
	 */
	public void setLevel2EndDate(String level2EndDate) {
		this.level2EndDate = level2EndDate;
	}

	/**
	 * @return the level2EndTime
	 */
	public String getLevel2EndTime() {
		return this.level2EndTime;
	}

	/**
	 * @param level2EndTime
	 *            the level2EndTime to set
	 */
	public void setLevel2EndTime(String level2EndTime) {
		this.level2EndTime = level2EndTime;
	}

	/**
	 * @return the level3Amount
	 */
	public String getLevel3Amount() {
		return this.level3Amount;
	}

	/**
	 * @param level3Amount
	 *            the level3Amount to set
	 */
	public void setLevel3Amount(String level3Amount) {
		this.level3Amount = level3Amount;
	}

	/**
	 * @return the level3StartDate
	 */
	public String getLevel3StartDate() {
		return this.level3StartDate;
	}

	/**
	 * @param level3StartDate
	 *            the level3StartDate to set
	 */
	public void setLevel3StartDate(String level3StartDate) {
		this.level3StartDate = level3StartDate;
	}

	/**
	 * @return the level3StartTime
	 */
	public String getLevel3StartTime() {
		return this.level3StartTime;
	}

	/**
	 * @param level3StartTime
	 *            the level3StartTime to set
	 */
	public void setLevel3StartTime(String level3StartTime) {
		this.level3StartTime = level3StartTime;
	}

	/**
	 * @return the level3EndDate
	 */
	public String getLevel3EndDate() {
		return this.level3EndDate;
	}

	/**
	 * @param level3EndDate
	 *            the level3EndDate to set
	 */
	public void setLevel3EndDate(String level3EndDate) {
		this.level3EndDate = level3EndDate;
	}

	/**
	 * @return the level6EndDate
	 */
	public String getLevel6EndDate() {
		return this.level6EndDate;
	}

	/**
	 * @param level6EndDate
	 *            the level6EndDate to set
	 */
	public void setLevel6EndDate(String level6EndDate) {
		this.level6EndDate = level6EndDate;
	}

	/**
	 * @return the level6EndTime
	 */
	public String getLevel6EndTime() {
		return this.level6EndTime;
	}

	/**
	 * @param level6EndTime
	 *            the level6EndTime to set
	 */
	public void setLevel6EndTime(String level6EndTime) {
		this.level6EndTime = level6EndTime;
	}

	/**
	 * @return the level3EndTime
	 */
	public String getLevel3EndTime() {
		return this.level3EndTime;
	}

	/**
	 * @param level3EndTime
	 *            the level3EndTime to set
	 */
	public void setLevel3EndTime(String level3EndTime) {
		this.level3EndTime = level3EndTime;
	}

	/**
	 * @return the level4Amount
	 */
	public String getLevel4Amount() {
		return this.level4Amount;
	}

	/**
	 * @param level4Amount
	 *            the level4Amount to set
	 */
	public void setLevel4Amount(String level4Amount) {
		this.level4Amount = level4Amount;
	}

	/**
	 * @return the level4StartDate
	 */
	public String getLevel4StartDate() {
		return this.level4StartDate;
	}

	/**
	 * @param level4StartDate
	 *            the level4StartDate to set
	 */
	public void setLevel4StartDate(String level4StartDate) {
		this.level4StartDate = level4StartDate;
	}

	/**
	 * @return the level4StartTime
	 */
	public String getLevel4StartTime() {
		return this.level4StartTime;
	}

	/**
	 * @param level4StartTime
	 *            the level4StartTime to set
	 */
	public void setLevel4StartTime(String level4StartTime) {
		this.level4StartTime = level4StartTime;
	}

	/**
	 * @return the level4EndDate
	 */
	public String getLevel4EndDate() {
		return this.level4EndDate;
	}

	/**
	 * @param level4EndDate
	 *            the level4EndDate to set
	 */
	public void setLevel4EndDate(String level4EndDate) {
		this.level4EndDate = level4EndDate;
	}

	/**
	 * @return the level4EndTime
	 */
	public String getLevel4EndTime() {
		return this.level4EndTime;
	}

	/**
	 * @param level4EndTime
	 *            the level4EndTime to set
	 */
	public void setLevel4EndTime(String level4EndTime) {
		this.level4EndTime = level4EndTime;
	}

	/**
	 * @return the level5Amount
	 */
	public String getLevel5Amount() {
		return this.level5Amount;
	}

	/**
	 * @param level5Amount
	 *            the level5Amount to set
	 */
	public void setLevel5Amount(String level5Amount) {
		this.level5Amount = level5Amount;
	}

	/**
	 * @return the level5StartDate
	 */
	public String getLevel5StartDate() {
		return this.level5StartDate;
	}

	/**
	 * @param level5StartDate
	 *            the level5StartDate to set
	 */
	public void setLevel5StartDate(String level5StartDate) {
		this.level5StartDate = level5StartDate;
	}

	/**
	 * @return the level5StartTime
	 */
	public String getLevel5StartTime() {
		return this.level5StartTime;
	}

	/**
	 * @param level5StartTime
	 *            the level5StartTime to set
	 */
	public void setLevel5StartTime(String level5StartTime) {
		this.level5StartTime = level5StartTime;
	}

	/**
	 * @return the level5EndDate
	 */
	public String getLevel5EndDate() {
		return this.level5EndDate;
	}

	/**
	 * @param level5EndDate
	 *            the level5EndDate to set
	 */
	public void setLevel5EndDate(String level5EndDate) {
		this.level5EndDate = level5EndDate;
	}

	/**
	 * @return the level5EndTime
	 */
	public String getLevel5EndTime() {
		return this.level5EndTime;
	}

	/**
	 * @param level5EndTime
	 *            the level5EndTime to set
	 */
	public void setLevel5EndTime(String level5EndTime) {
		this.level5EndTime = level5EndTime;
	}

	/**
	 * @return the level6Amount
	 */
	public String getLevel6Amount() {
		return this.level6Amount;
	}

	/**
	 * @param level6Amount
	 *            the level6Amount to set
	 */
	public void setLevel6Amount(String level6Amount) {
		this.level6Amount = level6Amount;
	}

	/**
	 * @return the level6StartDate
	 */
	public String getLevel6StartDate() {
		return this.level6StartDate;
	}

	/**
	 * @param level6StartDate
	 *            the level6StartDate to set
	 */
	public void setLevel6StartDate(String level6StartDate) {
		this.level6StartDate = level6StartDate;
	}

	/**
	 * @return the level6StartTime
	 */
	public String getLevel6StartTime() {
		return this.level6StartTime;
	}

	/**
	 * @param level6StartTime
	 *            the level6StartTime to set
	 */
	public void setLevel6StartTime(String level6StartTime) {
		this.level6StartTime = level6StartTime;
	}

	/**
	 * @return the levels
	 */
	public String getLevels() {
		return this.levels;
	}

	/**
	 * @param levels
	 *            the levels to set
	 */
	public void setLevels(String levels) {
		this.levels = levels;
	}

	/**
	 * @return the houseFeePerPlayer
	 */
	public String getHouseFeePerPlayer() {
		return this.houseFeePerPlayer;
	}

	/**
	 * @param houseFeePerPlayer
	 *            the houseFeePerPlayer to set
	 */
	public void setHouseFeePerPlayer(String houseFeePerPlayer) {
		this.houseFeePerPlayer = houseFeePerPlayer;
	}

	/**
	 * @return the entryfee
	 */
	public String getEntryfee() {
		return this.entryfee;
	}

	/**
	 * @param entryfee
	 *            the entryfee to set
	 */
	public void setEntryfee(String entryfee) {
		this.entryfee = entryfee;
	}

	/**
	 * @return the tournamentFee
	 */
	public String getTournamentFee() {
		return this.tournamentFee;
	}

	/**
	 * @param tournamentFee
	 *            the tournamentFee to set
	 */
	public void setTournamentFee(String tournamentFee) {
		this.tournamentFee = tournamentFee;
	}

	/**
	 * @param gameList
	 *            the gameList to set
	 */
	public void setGameList(List<GameDTO> gameList) {
		this.gameList = gameList;
	}

	/**
	 * @return the tournamentGame
	 */
	public String getTournamentGame() {
		return this.tournamentGame;
	}

	/**
	 * @param tournamentGame
	 *            the tournamentGame to set
	 */
	public void setTournamentGame(String tournamentGame) {
		this.tournamentGame = tournamentGame;
	}

	/**
	 * @return the tournamentLevels
	 */
	public String getTournamentLevels() {
		return this.tournamentLevels;
	}

	/**
	 * @param tournamentLevels
	 *            the tournamentLevels to set
	 */
	public void setTournamentLevels(String tournamentLevels) {
		this.tournamentLevels = tournamentLevels;
	}

	/**
	 * Will set all attributes in TournamentCreationDTO TwoplayertournamentDTO List<TwoplayermatchDTO> List<TwoplayertournamentgameoptionsDTO>
	 * 
	 * @see com.g4g.forms.BaseForm#getDTO()
	 * @return TournamentCreationDTO
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(),
						Level.INFO);

		TournamentCreationDTO dto = new TournamentCreationDTO();
		TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
		twoplayertournamentDTO.setSkinDTO(SkinServiceDelegator.getSkinDTO(WORLD_GAMING_SITE_ID));
		twoplayertournamentDTO.setLevels(Integer.parseInt(this.getLevels()));
		twoplayertournamentDTO.setEntryfee(Double.parseDouble(this.getEntryfee()));
		twoplayertournamentDTO.setGameDTO(GameServiceDelegator.getGame(DataUtil.getInteger(this.getTournamentGame())));
		twoplayertournamentDTO.setHousefeeperplayer(DataUtil.getDouble(this.getHouseFeePerPlayer()));
		twoplayertournamentDTO.setCreationdate(DataUtil.todayGMT());

		// Have to set which will be decided later

		// twoplayertournamentDTO.setCreatedby();

		List<GameoptionsDTO> list = null;

		try {
			list = getSelectedGameOptionList();
		}
		catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
		}

		dto.setTwoplayertournamentDTO(twoplayertournamentDTO);
		dto.setTwoplayermatchList(getTwoPlayerMatchListAsPerLevel());
		dto.setTwoplayertournamentgameoptionsList(list != null ? setTwoPlayerGameOption(list) : null);

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(),
						Level.INFO);
		return dto;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(),
						Level.INFO);
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(),
						Level.INFO);
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(),
						Level.INFO);

		ActionErrors errors = super.validate(mapping, request);

		level1startdate = new StringBuffer(this.getLevel1StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel1StartTime()).toString();
		level1enddate = new StringBuffer(this.getLevel1EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel1EndTime()).toString();
		level2startdate = new StringBuffer(this.getLevel2StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel2StartTime()).toString();
		level2enddate = new StringBuffer(this.getLevel2EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel2EndTime()).toString();
		level3startdate = new StringBuffer(this.getLevel3StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel3StartTime()).toString();
		level3enddate = new StringBuffer(this.getLevel3EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel3EndTime()).toString();
		level4startdate = new StringBuffer(this.getLevel4StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel4StartTime()).toString();
		level4enddate = new StringBuffer(this.getLevel4EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel4EndTime()).toString();
		level5startdate = new StringBuffer(this.getLevel5StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel5StartTime()).toString();
		level5enddate = new StringBuffer(this.getLevel5EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel5EndTime()).toString();
		level6startdate = new StringBuffer(this.getLevel6StartDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel6StartTime()).toString();
		level6enddate = new StringBuffer(this.getLevel6EndDate()).append(BLANK).append(AT).append(BLANK).append(
						this.getLevel6EndTime()).toString();

		Date level1StartDate = DataUtil.getDate(level1startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level1Enddate = DataUtil.getDate(level1enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level2StartDate = DataUtil.getDate(level2startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level2Enddate = DataUtil.getDate(level2enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level3StartDate = DataUtil.getDate(level3startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level3Enddate = DataUtil.getDate(level3enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level4StartDate = DataUtil.getDate(level4startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level4Enddate = DataUtil.getDate(level4enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level5StartDate = DataUtil.getDate(level5startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level5Enddate = DataUtil.getDate(level5enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level6StartDate = DataUtil.getDate(level6startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level6Enddate = DataUtil.getDate(level6enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);

		int level = Integer.parseInt(levels);
		if (level > G4GConstants.ZERO) {
			if (level1StartDate.after(level1Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL1SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}
		if (level > G4GConstants.ONE_NUMBER) {
			if (level2StartDate.after(level2Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL2SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
			if (level1Enddate.after(level2StartDate)) {
				msg = new ActionMessage(INVALID_INTER_LEVEL2SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}
		if (level > G4GConstants.TWO_NUMBER ) {
			if (level3StartDate.after(level3Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL3SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
			if (level2Enddate.after(level3StartDate)) {
				msg = new ActionMessage(INVALID_INTER_LEVEL3SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}
		if (level > G4GConstants.THREE_NUMBER) {
			if (level4StartDate.after(level4Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL4SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
			if (level3Enddate.after(level4StartDate)) {
				msg = new ActionMessage(INVALID_INTER_LEVEL4SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}
		if (level > G4GConstants.FOUR_NUMBER) {
			if (level5StartDate.after(level5Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL5SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
			if (level4Enddate.after(level5StartDate)) {
				msg = new ActionMessage(INVALID_INTER_LEVEL5SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}
		if (level > G4GConstants.FIVE_NUMBER) {
			if (level6StartDate.after(level6Enddate)) {
				msg = new ActionMessage(INVALID_LEVEL6SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
			if (level5Enddate.after(level6StartDate)) {
				msg = new ActionMessage(INVALID_INTER_LEVEL6SCHEDULE);
				errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
			}
		}

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(),
						Level.INFO);
		return errors;
	}

	/**
	 * @return the gameOptions
	 */
	public String[] getGameOptions() {
		return this.gameOptions;
	}

	/**
	 * @param gameOptions
	 *            the gameOptions to set
	 */
	public void setGameOptions(String[] gameOptions) {
		this.gameOptions = gameOptions;
	}

	/**
	 * @return the gameOptionSelected
	 */
	public String[] getGameOptionSelected() {
		return this.gameOptionSelected;
	}

	/**
	 * @param gameOptionSelected
	 *            the gameOptionSelected to set
	 */
	public void setGameOptionSelected(String[] gameOptionSelected) {
		this.gameOptionSelected = gameOptionSelected;
	}

	/**
	 * Will retrive from db and return list of gameoptionDTO for options user had selected for a tournament
	 * 
	 * @return List<GameoptionsDTO>
	 */
	public List<GameoptionsDTO> getSelectedGameOptionList() {
		String[] valuesequence = this.getGameOptionSelected();
		int index = valuesequence.length;
		List<GameoptionsDTO> list = new ArrayList<GameoptionsDTO>();
		List<GameoptionsDTO> listtemp = new ArrayList<GameoptionsDTO>();
		SearchCriteria searchCriteria = new SearchCriteria();
		for (int i = G4GConstants.ZERO ; i < index; i++) {
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(VALUE_SEQUENCE_ID, DataUtil.getInteger(valuesequence[i]));
			searchCriteria.setAttribute(OPTION, this.getGameOptions()[i]);
			searchCriteria.setAttribute(GAMEID_DB, DataUtil.getInteger(this.getTournamentGame()));
			list = GameOptionServiceDelegator.getList(searchCriteria);
			if (list.size() != G4GConstants.ZERO) {
				Iterator<GameoptionsDTO> it = list.iterator();
				GameoptionsDTO dto = it.next();
				listtemp.add(dto);
			}
		}
		return listtemp;
	}

	/**
	 * Set a List for a given list of gameoption These gameoption are selected by user/ Employee during creation of
	 * tournament and set in getSelectedGameOptionList() method This list will be Iterate for adding data to tournament
	 * game option This method will not add tournamentId to each dto That had to be set after we insert tournament in db
	 * 
	 * @param gameOptionList
	 * @return List<TwoplayertournamentgameoptionsDTO>
	 */
	public List<TwoplayertournamentgameoptionsDTO> setTwoPlayerGameOption(List<GameoptionsDTO> gameOptionList) {
		List<TwoplayertournamentgameoptionsDTO> tptgolist = new ArrayList<TwoplayertournamentgameoptionsDTO>();
		Iterator<GameoptionsDTO> gameoptionit = gameOptionList.iterator();
		while (gameoptionit.hasNext()) {
			TwoplayertournamentgameoptionsDTO tptgo = new TwoplayertournamentgameoptionsDTO();
			GameoptionsDTO gameoptionsDTO = gameoptionit.next();
			tptgo.setOptionid(gameoptionsDTO.getId());
			tptgo.setValueid(gameoptionsDTO.getValueid());
			tptgolist.add(tptgo);
		}
		return tptgolist;
	}

	/**
	 * Returns a complete list of players for a given level set in form Assumes that date validation is already over
	 * 
	 * @return List<TwoplayermatchDTO>
	 */
	public List<TwoplayermatchDTO> getTwoPlayerMatchListAsPerLevel() {
		int level = Integer.parseInt(this.getTournamentLevels());
		List<TwoplayermatchDTO> list = new ArrayList<TwoplayermatchDTO>();
		Date level1StartDate = DataUtil.getDate(level1startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level1Enddate = DataUtil.getDate(level1enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level2StartDate = DataUtil.getDate(level2startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level2Enddate = DataUtil.getDate(level2enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level3StartDate = DataUtil.getDate(level3startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level3Enddate = DataUtil.getDate(level3enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level4StartDate = DataUtil.getDate(level4startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level4Enddate = DataUtil.getDate(level4enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level5StartDate = DataUtil.getDate(level5startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level5Enddate = DataUtil.getDate(level5enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level6StartDate = DataUtil.getDate(level6startdate, DATE_DD_MM_YYYY_AT_HH_MM_A);
		Date level6Enddate = DataUtil.getDate(level6enddate, DATE_DD_MM_YYYY_AT_HH_MM_A);

		level1StartDate = DateUtil.ConvertESTtoGMT(level1StartDate);
		level1Enddate = DateUtil.ConvertESTtoGMT(level1Enddate);
		level2StartDate = DateUtil.ConvertESTtoGMT(level2StartDate);
		level2Enddate = DateUtil.ConvertESTtoGMT(level2Enddate);
		level3StartDate = DateUtil.ConvertESTtoGMT(level3StartDate);
		level3Enddate = DateUtil.ConvertESTtoGMT(level3Enddate);
		level4StartDate = DateUtil.ConvertESTtoGMT(level4StartDate);
		level4Enddate = DateUtil.ConvertESTtoGMT(level4Enddate);
		level5StartDate = DateUtil.ConvertESTtoGMT(level5StartDate);
		level5Enddate = DateUtil.ConvertESTtoGMT(level5Enddate);
		level6StartDate = DateUtil.ConvertESTtoGMT(level6StartDate);
		level6Enddate = DateUtil.ConvertESTtoGMT(level6Enddate);

		if (level == G4GConstants.ONE_NUMBER) {
			TwoplayermatchDTO dto = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel1Amount()),
							level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
			list.add(dto);
		}
		else if (level == 2) {
			TwoplayermatchDTO dto1 = new TwoplayermatchDTO(1, Double.parseDouble(this.getLevel1Amount()),
							level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
			TwoplayermatchDTO dto2 = new TwoplayermatchDTO(1, Double.parseDouble(this.getLevel1Amount()),
							level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
			TwoplayermatchDTO dto3 = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel2Amount()),
							level2StartDate, level2Enddate, G4GConstants.ZERO, PENDING);
			/** level 1 matches for level 3 tournament */
			list.add(dto1);
			list.add(dto2);
			/** level 2 matches for level 3 tournament */
			list.add(dto3);
		}
		else if (level == G4GConstants.THREE_NUMBER) {
			/** level 1 matches for level 3 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.FOUR_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(1, Double.parseDouble(this.getLevel1Amount()),
								level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 2 matches for level 3 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.TWO_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.TWO_NUMBER, Double.parseDouble(this.getLevel2Amount()),
								level2StartDate, level2Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 3 matches for level 3 tournament that will be only one */
			TwoplayermatchDTO dto = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel3Amount()),
							level3StartDate, level3Enddate, G4GConstants.ZERO, PENDING);
			list.add(dto);
		}
		else if (level == G4GConstants.FOUR_NUMBER) {
			/** level 1 matches for level 4 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.EIGHT_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.ONE_NUMBER, Double.parseDouble(this.getLevel1Amount()),
								level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 2 matches for level 4 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.FOUR_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.TWO_NUMBER, Double.parseDouble(this.getLevel2Amount()),
								level2StartDate, level2Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 3 matches for level 4 */
			for (int i = G4GConstants.ZERO; i < G4GConstants.TWO_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.THREE_NUMBER, Double.parseDouble(this.getLevel3Amount()),
								level3StartDate, level3Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 4 matches for level 4 tournament that will be only one */
			TwoplayermatchDTO dto = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel4Amount()),
							level4StartDate, level4Enddate, G4GConstants.ZERO, PENDING);
			list.add(dto);

		}
		else if (level == G4GConstants.FIVE_NUMBER) {
			/** level 1 matches for level 5 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.SIXTEEN; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.ONE_NUMBER, Double.parseDouble(this.getLevel1Amount()),
								level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 2 matches for level 4 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.EIGHT_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.TWO_NUMBER, Double.parseDouble(this.getLevel2Amount()),
								level2StartDate, level2Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 3 matches for level 4 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.FOUR_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.THREE_NUMBER, Double.parseDouble(this.getLevel3Amount()),
								level3StartDate, level3Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 4 matches for level 5 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.TWO_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.FOUR_NUMBER, Double.parseDouble(this.getLevel4Amount()),
								level4StartDate, level4Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 5 matches for level 5 tournament that will be only one */
			TwoplayermatchDTO dto = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel5Amount()),
							level5StartDate, level5Enddate, G4GConstants.ZERO, PENDING);
			list.add(dto);

		}
		else if (level == G4GConstants.SIX_NUMBER) {
			/** level 1 matches for level 6 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.THIRTYTWO; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(1, Double.parseDouble(this.getLevel1Amount()),
								level1StartDate, level1Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 2 matches for level 6 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.SIXTEEN; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(2, Double.parseDouble(this.getLevel2Amount()),
								level2StartDate, level2Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 3 matches for level 6 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.EIGHT_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.THREE_NUMBER, Double.parseDouble(this.getLevel3Amount()),
								level3StartDate, level3Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 4 matches for level 6 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.FOUR_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.FOUR_NUMBER, Double.parseDouble(this.getLevel4Amount()),
								level4StartDate, level4Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 5 matches for level 6 tournament */
			for (int i = G4GConstants.ZERO; i < G4GConstants.TWO_NUMBER; i++) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO(G4GConstants.FOUR_NUMBER, Double.parseDouble(this.getLevel5Amount()),
								level5StartDate, level5Enddate, G4GConstants.ZERO, PENDING);
				list.add(dto);
			}
			/** level 6 matches for level 6 tournament that will be only one */
			TwoplayermatchDTO dto = new TwoplayermatchDTO(level, Double.parseDouble(this.getLevel6Amount()),
							level6StartDate, level6Enddate, G4GConstants.ZERO, PENDING);
			list.add(dto);
		}
		return list;
	}

	/**
	 * @return the totalpot
	 */
	public String getTotalpot() {
		return this.totalpot;
	}

	/**
	 * @param totalpot
	 *            the totalpot to set
	 */
	public void setTotalpot(String totalpot) {
		this.totalpot = totalpot;
	}

}