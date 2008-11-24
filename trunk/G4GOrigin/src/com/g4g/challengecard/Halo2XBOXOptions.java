/**
 * 
 */
package com.g4g.challengecard;

import static com.g4g.utils.G4GConstants.COMMA;
import static com.g4g.utils.GameIdConstants.Halo2XBOX;
import static com.g4g.utils.GameIdConstants.Ranked;
import static com.g4g.utils.GameIdConstants.UnRanked;
import static com.g4g.utils.GameIdConstants.challengecard;
import static com.g4g.utils.GameIdConstants.option;
import gnu.trove.THashMap;

import java.util.Iterator;
import java.util.List;

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
public class Halo2XBOXOptions {
	
	
	public static String getHalo2XBOX(THashMap<String, List<GameoptionsDTO>> map ,Integer[] valuesequenceidinteger, int gameid , int optionsequenceid) {
		StringBuffer strResponse = new StringBuffer();
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			strResponse.append(Halo2XBOXOptions(map,optionsequenceid , valuesequenceidinteger  ));
		}else if(optionsequenceid==G4GConstants.TWO_NUMBER) {
			strResponse.append(Halo2XBOXOptions(map,optionsequenceid ,valuesequenceidinteger ));
		}
		return ResourceBundleUtil.getValue(challengecard, strResponse.toString());
	}
	
	public static String Halo2XBOXOptions(THashMap<String, List<GameoptionsDTO>> map, int  optionsequenceid , Integer... valuesequenceidarray) {
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			int valuesequenceid=valuesequenceidarray[G4GConstants.ZERO];
			if(valuesequenceidarray.length == G4GConstants.ONE_NUMBER) {
				if(ChallengeCardGameOptionsServlet.isRanked(valuesequenceid, optionsequenceid)) {
					List<GameoptionsDTO> list = map.get((String.valueOf(Halo2XBOX)+COMMA+String.valueOf((optionsequenceid+G4GConstants.ONE_NUMBER)))); 
					StringBuffer options = new StringBuffer(); 
					for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
						GameoptionsDTO dto =  iterator.next();
						options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValueid()),dto.getValue()));
					}
					return ResourceBundleUtil.getValue(GameIdConstants.options,options.toString());
				}else if(ChallengeCardGameOptionsServlet.isUnRanked(valuesequenceid, optionsequenceid)) {
					List<GameoptionsDTO> list = map.get((String.valueOf(Halo2XBOX)+COMMA+String.valueOf((optionsequenceid+G4GConstants.ONE_NUMBER)))); 
					StringBuffer options = new StringBuffer(); 
					for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
						GameoptionsDTO dto =  iterator.next();
						options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()),dto.getValue()));
				}
					return ResourceBundleUtil.getValue(GameIdConstants.options,options.toString());
				}
			}
		}if(optionsequenceid==G4GConstants.TWO_NUMBER) {
			List<GameoptionsDTO> list =null;
			SearchCriteria searchCriteria = new SearchCriteria();
			if(valuesequenceidarray[G4GConstants.ONE_NUMBER]==Ranked) {
				//list =GameOptionServiceDelegator.getGameOptions(Halo2XBOX, optionsequenceid, searchCriteria);	
			}
			if(valuesequenceidarray[G4GConstants.ONE_NUMBER]==UnRanked) {
				//list =GameOptionServiceDelegator.getGameOptions(Halo2XBOX, optionsequenceid, searchCriteria);	
			}
			StringBuffer options = new StringBuffer(); 
			if(list==null || list.size()<G4GConstants.ONE_NUMBER) {
				list = map.get((String.valueOf(Halo2XBOX)+COMMA+String.valueOf((optionsequenceid+G4GConstants.ONE_NUMBER))));
			}
			if(list!=null) {
				for (Iterator<GameoptionsDTO> iterator = list.iterator(); iterator.hasNext();) {
					GameoptionsDTO dto =  iterator.next();
					options.append(ResourceBundleUtil.getValue(option, String.valueOf(dto.getValuesequenceid()),dto.getValue()));
				}
			}
			
			return ResourceBundleUtil.getValue(GameIdConstants.options,options.toString());
		}
		return null;
	}

}
