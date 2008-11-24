package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.AJAX_XML_VERSION;
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CLOSE_GAMEOPTIONS;
import static com.g4g.utils.G4GConstants.CLOSE_LABELVALUE;
import static com.g4g.utils.G4GConstants.CLOSE_OPTIONFORGAME;
import static com.g4g.utils.G4GConstants.CLOSE_VALUESEQUENCEID;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GREATERTHAN;
import static com.g4g.utils.G4GConstants.GAMEOPTIONS;
import static com.g4g.utils.G4GConstants.LABELVALUE;
import static com.g4g.utils.G4GConstants.OPTIONFORGAME;
import static com.g4g.utils.G4GConstants.SLASH;
import static com.g4g.utils.G4GConstants.SMALLERTHAN;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.VALUESEQUENCEID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class TournamentCreationAjaxImpl.
 */
public class TournamentCreationAjaxImpl {

	/**
	 * Instantiates a new TournamentCreationAjaxImpl.
	 */
	private TournamentCreationAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned
	 */
	@Override
	public TournamentCreationAjaxImpl clone() throws CloneNotSupportedException {
		super.clone();
		throw new CloneNotSupportedException();
	}

	/**
	 * Used
	 * 
	 * @param gameId
	 * @return XML in the format of String
	 */
	public static String getGameOptionsXML(int gameId) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(TournamentCreationAjaxImpl.class.getName())
						.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);

		HashMap<String, List<GameoptionsDTO>> map = GameOptionServiceDelegator
				.getGameOptionListOfGame(gameId);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		StringBuffer strResponse = new StringBuffer(AJAX_XML_VERSION);
		strResponse.append(GAMEOPTIONS);
		while (it.hasNext()) {
			String key = it.next();
			List<GameoptionsDTO> gameOptionList = map.get(key);
			Iterator<GameoptionsDTO> gameOptionIt = gameOptionList.iterator();
			// have to create select list box with property gameSelected[]
			key = key.replaceAll(BLANK, G4GConstants.EIGHT_EIGHT);
			strResponse.append(SMALLERTHAN).append(key).append(GREATERTHAN);
			while (gameOptionIt.hasNext()) {
				GameoptionsDTO gameoptionsDTO = gameOptionIt.next();
				strResponse.append(OPTIONFORGAME);
				strResponse.append(VALUESEQUENCEID).append(
						gameoptionsDTO.getValuesequenceid()).append(
						CLOSE_VALUESEQUENCEID);
				strResponse.append(LABELVALUE)
						.append(gameoptionsDTO.getValue()).append(
								CLOSE_LABELVALUE);
				strResponse.append(CLOSE_OPTIONFORGAME);
			}
			strResponse.append(SMALLERTHAN).append(SLASH).append(key).append(
					GREATERTHAN);
		}
		strResponse.append(CLOSE_GAMEOPTIONS);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(TournamentCreationAjaxImpl.class.getName())
						.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return strResponse.toString();
	}

}
