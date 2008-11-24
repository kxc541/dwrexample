/**
 * 
 */
package com.g4g.challengecard;

import static com.g4g.utils.GameIdConstants.NBAStreetXBOX;
import static com.g4g.utils.GameIdConstants.Ranked;
import static com.g4g.utils.GameIdConstants.UnRanked;
import static com.g4g.utils.GameIdConstants.challengecard;
import static com.g4g.utils.GameIdConstants.option;
import static com.g4g.utils.GameIdConstants.valueid;
import gnu.trove.THashMap;

import java.util.Iterator;
import java.util.List;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.servlet.ChallengeCardGameOptionsServlet;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GameIdConstants;
import com.g4g.utils.ResourceBundleUtil;

/**
 * @author ankur
 * 
 */
public class NBAStreetXBOXOptions {

	/**
	 * @param map
	 * @param valuesequenceidinteger
	 * @param gameid
	 * @param optionsequenceid
	 * @return xml
	 */
	public static String getNBAStreetXBOX(THashMap<String, List<GameoptionsDTO>> map,Integer[] valuesequenceidinteger, int gameid, int optionsequenceid) {
		StringBuffer strResponse = new StringBuffer();
		if (optionsequenceid == G4GConstants.ONE_NUMBER) {
			strResponse.append(NBAStreetXBOXOptions(map, optionsequenceid,
					valuesequenceidinteger));
		} else if (optionsequenceid == G4GConstants.TWO_NUMBER) {

			strResponse.append(NBAStreetXBOXOptions(map, optionsequenceid,
					valuesequenceidinteger));
		}

		return ResourceBundleUtil.getValue(challengecard, strResponse
				.toString());
	}

	//here ! check for ranked and un-ranked works for equallity of ranked and unranked.
	/**
	 * @param map
	 * @param optionsequenceid
	 * @param valuesequenceidarray
	 * @return xml
	 */
	public static String NBAStreetXBOXOptions(THashMap<String, List<GameoptionsDTO>> map, int optionsequenceid,Integer... valuesequenceidarray) {
		if (optionsequenceid == G4GConstants.ONE_NUMBER) {
			int valuesequenceid = valuesequenceidarray[G4GConstants.ZERO];
			StringBuffer re=new StringBuffer();
			if (valuesequenceidarray.length == G4GConstants.ONE_NUMBER) {
				if (!ChallengeCardGameOptionsServlet.isRanked(valuesequenceid,optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.valuesequenceid, valuesequenceid);
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid+ G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB,NBAStreetXBOX);
					GameoptionsDTO dto = GameOptionServiceDelegator.getList(searchCriteria).get(G4GConstants.ZERO);
					dto = GameOptionServiceDelegator.getGame(dto);
					String options = ResourceBundleUtil.getValue(option, String.valueOf(dto.getOptionsequenceid()), dto.getValue());
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options,options));
					return re.toString();
				} else if (!ChallengeCardGameOptionsServlet.isUnRanked(valuesequenceid, optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid+ G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB,NBAStreetXBOX);
					List<GameoptionsDTO> list = map.get(NBAStreetXBOX+ G4GConstants.COMMA + (optionsequenceid + G4GConstants.ONE_NUMBER));
					if (list == null)
						list = GameOptionServiceDelegator.getList(searchCriteria);
					StringBuffer options = new StringBuffer();
					for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
						GameoptionsDTO dto = iterator.next();
						options.append(ResourceBundleUtil.getValue(option,String.valueOf(dto.getValuesequenceid()), dto.getValue()));
					}
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options,options.toString()));
					String get = String.valueOf(NBAStreetXBOX) + G4GConstants.COMMA+ String.valueOf(G4GConstants.TWO_NUMBER) + G4GConstants.COMMA+ G4GConstants.ONE_NUMBER;
					list = map.get(get);
					StringBuffer oo = new StringBuffer();
					if (list != null)
						for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
							GameoptionsDTO dto = iterator.next();
							oo.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()), dto.getValue()));
						}
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options, oo.toString()));
					return re.toString();
				}
			}
		}
		if (optionsequenceid == G4GConstants.TWO_NUMBER) {
			List<GameoptionsDTO> list = null;
			SearchCriteria searchCriteria = new SearchCriteria();
			if (!(valuesequenceidarray[G4GConstants.ONE_NUMBER] == Ranked) ){
				searchCriteria.setAttribute(valueid, G4GConstants.THREE_NUMBER);
				list = map.get(NBAStreetXBOX + G4GConstants.COMMA
						+ optionsequenceid + G4GConstants.COMMA
						+ G4GConstants.THREE_NUMBER);
				if (list == null) {
					list = GameOptionServiceDelegator.getGameOptions(
							NBAStreetXBOX, optionsequenceid, searchCriteria);
				}
			}
			if (!(valuesequenceidarray[G4GConstants.ONE_NUMBER] == UnRanked)) {
				searchCriteria.removeAllAttribute();
				String valueid = null;
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.ONE_NUMBER) {
					valueid = String.valueOf(G4GConstants.ONE_NUMBER);
				}
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.TWO_NUMBER) {
					valueid = String.valueOf(G4GConstants.TWO_NUMBER);
				}
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.THREE_NUMBER) {
					valueid = String.valueOf(G4GConstants.THREE_NUMBER);
				}
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.FOUR_NUMBER) {
					valueid = String.valueOf(G4GConstants.FOUR_NUMBER);
				}
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.FIVE_NUMBER) {
					valueid = String.valueOf(G4GConstants.ONE_NUMBER);
				}
				if (valuesequenceidarray[G4GConstants.ZERO] == G4GConstants.SIX_NUMBER) {
					valueid = String.valueOf(G4GConstants.FIVE_NUMBER);
				}
				String get = String.valueOf(NBAStreetXBOX) + G4GConstants.COMMA+ String.valueOf(optionsequenceid) + G4GConstants.COMMA+ valueid;
				list = map.get(get);
				if (list == null || list.size() < G4GConstants.ONE_NUMBER) {searchCriteria.setAttribute(GameIdConstants.valueid,valueid);
					list = GameOptionServiceDelegator.getGameOptions(
							NBAStreetXBOX, optionsequenceid, searchCriteria);
				}
			}

			StringBuffer options = new StringBuffer();
			if (list == null || list.size() < G4GConstants.ONE_NUMBER) {
				list = map.get(String.valueOf(NBAStreetXBOX).concat(String.valueOf(optionsequenceid)));
			}
			if (list != null) {
				for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
					GameoptionsDTO dto = iterator.next();
					options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()), dto.getValue()));
				}
			}
			return ResourceBundleUtil.getValue(GameIdConstants.options, options.toString());
		}
		return null;
	}

}
