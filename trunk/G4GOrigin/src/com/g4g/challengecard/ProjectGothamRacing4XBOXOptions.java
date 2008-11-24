/**
 * 
 */
package com.g4g.challengecard;

import static com.g4g.utils.GameIdConstants.ProjectGothamRacing4XBOX;
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
public class ProjectGothamRacing4XBOXOptions {
	
	
	public static String getProjectGothamRacing4XBOX(THashMap<String, List<GameoptionsDTO>> map ,Integer[] valuesequenceidinteger, int gameid , int optionsequenceid) {
		StringBuffer strResponse = new StringBuffer();
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			strResponse.append(ProjectGothamRacing4XBOXOptions( map ,optionsequenceid , valuesequenceidinteger  ));
		}else if(optionsequenceid==G4GConstants.TWO_NUMBER) {
			strResponse.append(ProjectGothamRacing4XBOXOptions( map ,optionsequenceid ,valuesequenceidinteger ));
		}
		return ResourceBundleUtil.getValue(challengecard, strResponse.toString());
	}
	
	public static String ProjectGothamRacing4XBOXOptions(THashMap<String, List<GameoptionsDTO>> map ,int  optionsequenceid , Integer... valuesequenceidarray) {
		if(optionsequenceid==G4GConstants.ONE_NUMBER) {
			int valuesequenceid=valuesequenceidarray[G4GConstants.ZERO];
			if(valuesequenceidarray.length == G4GConstants.ONE_NUMBER) {
				if(ChallengeCardGameOptionsServlet.isRanked(valuesequenceid, optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.valuesequenceid, valuesequenceid);
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid + G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB, ProjectGothamRacing4XBOX);
					GameoptionsDTO dto = GameOptionServiceDelegator.getList(searchCriteria).get(G4GConstants.ZERO);
					dto = GameOptionServiceDelegator.getGame(dto);
					String options= ResourceBundleUtil.getValue(option, String.valueOf(dto.getOptionsequenceid()),dto.getValue());
					return ResourceBundleUtil.getValue(GameIdConstants.options,options); 
				}else if(ChallengeCardGameOptionsServlet.isUnRanked(valuesequenceid, optionsequenceid)) {
					SearchCriteria searchCriteria = new SearchCriteria();
					searchCriteria.setAttribute(GameIdConstants.optionsequenceid, optionsequenceid + G4GConstants.ONE_NUMBER);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB, ProjectGothamRacing4XBOX);
					List<GameoptionsDTO> list =GameOptionServiceDelegator.getList(searchCriteria);
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
				searchCriteria.setAttribute(valueid, G4GConstants.ONE_NUMBER);
				list =GameOptionServiceDelegator.getGameOptions(ProjectGothamRacing4XBOX, optionsequenceid, searchCriteria);	
			}
			if(valuesequenceidarray[G4GConstants.ONE_NUMBER]==UnRanked) {
				list =GameOptionServiceDelegator.getGameOptions(ProjectGothamRacing4XBOX, optionsequenceid, searchCriteria);	
			}
			StringBuffer options = new StringBuffer(); 
			if(list==null || list.size()<G4GConstants.ONE_NUMBER) {
				list =GameOptionServiceDelegator.getGameOptions(ProjectGothamRacing4XBOX, optionsequenceid, searchCriteria);
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
