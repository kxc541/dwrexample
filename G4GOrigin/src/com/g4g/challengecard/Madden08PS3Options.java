/**
 * 
 */
package com.g4g.challengecard;

import static com.g4g.utils.G4GConstants.COMMA;
import static com.g4g.utils.GameIdConstants.Madden08PS3;
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
public class Madden08PS3Options {
	
	
	public static String getMadden08PS3(THashMap<String, List<GameoptionsDTO>> map ,Integer[] valuesequenceidinteger, int gameid , int optionsequenceid) {
		StringBuffer strResponse = new StringBuffer();
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			strResponse.append(Madden08PS3Options( map ,optionsequenceid , valuesequenceidinteger  ));
		}else if(optionsequenceid==G4GConstants.TWO_NUMBER) {
			strResponse.append(Madden08PS3Options( map ,optionsequenceid ,valuesequenceidinteger ));
		}
		return ResourceBundleUtil.getValue(challengecard, strResponse.toString());
	}
	
	public static String Madden08PS3Options(THashMap<String, List<GameoptionsDTO>> map ,int  optionsequenceid , Integer... valuesequenceidarray) {
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			StringBuffer re = new StringBuffer();
			int valuesequenceid=valuesequenceidarray[G4GConstants.ZERO];
			if(valuesequenceidarray.length == G4GConstants.ONE_NUMBER) {
				if(ChallengeCardGameOptionsServlet.isRanked(valuesequenceid, optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.valuesequenceid, valuesequenceid);
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid + G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB, Madden08PS3);
					List<GameoptionsDTO> list = map.get(String.valueOf(Madden08PS3)+COMMA+String.valueOf(optionsequenceid+G4GConstants.ONE_NUMBER));
					if(list==null) {
						list = GameOptionServiceDelegator.getList(searchCriteria);
					}
					GameoptionsDTO dto = list.get(G4GConstants.ZERO);
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options,ResourceBundleUtil.getValue(option, String.valueOf(dto.getOptionsequenceid()),dto.getValue())));
					 list = map.get(String.valueOf(Madden08PS3)+COMMA+String.valueOf(G4GConstants.THREE_NUMBER));
					 StringBuffer options = new StringBuffer();
					 for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
							GameoptionsDTO dto1 =  iterator.next();
							options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto1.getValuesequenceid()),dto1.getValue()));
						}
					 re.append(ResourceBundleUtil.getValue(GameIdConstants.options,options.toString()));
					return re.toString(); 
				}else if(ChallengeCardGameOptionsServlet.isUnRanked(valuesequenceid, optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid + G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB, Madden08PS3);
					List<GameoptionsDTO> list =GameOptionServiceDelegator.getList(searchCriteria);
					StringBuffer options = new StringBuffer(); 
					for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
						GameoptionsDTO dto =  iterator.next();
						options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()),dto.getValue()));
					}
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options,options.toString()));
					list = map.get(String.valueOf(Madden08PS3)+COMMA+String.valueOf(G4GConstants.THREE_NUMBER));
					options.delete(G4GConstants.ZERO, options.length());
					for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
						GameoptionsDTO dto =  iterator.next();
						options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()),dto.getValue()));
					}
					re.append(ResourceBundleUtil.getValue(GameIdConstants.options,options.toString()));
					return re.toString();
				}
			}
		}if(optionsequenceid==G4GConstants.TWO_NUMBER) {
			List<GameoptionsDTO> list =null;
			SearchCriteria searchCriteria = new SearchCriteria();
			if(valuesequenceidarray[G4GConstants.ONE_NUMBER]==Ranked) {
				searchCriteria.setAttribute(valueid, G4GConstants.ONE_NUMBER);
				 list = map.get(String.valueOf(Madden08PS3)+COMMA+String.valueOf(optionsequenceid+G4GConstants.ONE_NUMBER));
				if(list==null) {
					list =GameOptionServiceDelegator.getGameOptions(Madden08PS3, optionsequenceid, searchCriteria);
				}	
			}
			if(valuesequenceidarray[G4GConstants.ONE_NUMBER]==UnRanked) {
				list = map.get(String.valueOf(Madden08PS3)+COMMA+String.valueOf(optionsequenceid+G4GConstants.ONE_NUMBER));
				if(list==null) {
					list =GameOptionServiceDelegator.getGameOptions(Madden08PS3, optionsequenceid, searchCriteria);
				}	
			}
			StringBuffer options = new StringBuffer(); 
			if(list==null || list.size()<G4GConstants.ONE_NUMBER) {
				list =GameOptionServiceDelegator.getGameOptions(Madden08PS3, optionsequenceid, searchCriteria);
			}
			for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
				GameoptionsDTO dto =  iterator.next();
				options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()),dto.getValue()));
			}
			return ResourceBundleUtil.getValue(GameIdConstants.options,options.toString());
		}
		return null;
	}

}
