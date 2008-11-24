/**
 *
 */
package com.g4g.plugin;

import gnu.trove.THashMap;

import java.util.List;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.G4GConstants;

/**
 * @author ankur
 *
 */
public class GameOptionsRuleEngine {

	public static THashMap<String, List<GameoptionsDTO>> getGameOptionsRuleEngine(){
		final THashMap<String, List<GameoptionsDTO>> OptionsCache = new THashMap<String, List<GameoptionsDTO>>(100,90F);
		// halo2 options for first selection
		// 1 = gameid 2=optionid
		setOptionCache(OptionsCache, 2, 1);

		// Setting for gameid=11 NBA Street
		setOptionCache(OptionsCache, 2, 11);
		setOptionCache(OptionsCache, 3, 11);
		// Setting for gameid=11 NBA Street  2=current optionseqid (the one which is selected and populates next) , valueid
		setOptionCache(OptionsCache, 1, 11,0);
		setOptionCache(OptionsCache, 2, 11,1);
		setOptionCache(OptionsCache, 2, 11,2);
		setOptionCache(OptionsCache, 2, 11,4);
		setOptionCache(OptionsCache, 2, 11,3);
		setOptionCache(OptionsCache, 2, 11,5);

		//NCAA Football - xbox gameid=14
		setOptionCache(OptionsCache, 1, 14);
		setOptionCache(OptionsCache, 2, 14);
		setOptionCache(OptionsCache, 3, 14);

		//NCAA Football - ps2 gameid=21
		setOptionCache(OptionsCache, 1, 21);
		setOptionCache(OptionsCache, 2, 21);
		setOptionCache(OptionsCache, 3, 21);

		//NCAA Football - ps3		gameid=30
		setOptionCache(OptionsCache, 1, 30);
		setOptionCache(OptionsCache, 2, 30);
		setOptionCache(OptionsCache, 3, 30);



		//Madden 08 - xbox
		setOptionCache(OptionsCache, 1, 16);
		setOptionCache(OptionsCache, 2, 16);
		setOptionCache(OptionsCache, 3, 16);

		//Madden 08 - ps2
		setOptionCache(OptionsCache, 1, 23);
		setOptionCache(OptionsCache, 2, 23);
		setOptionCache(OptionsCache, 3, 23);

		//Madden 08 - ps3
		setOptionCache(OptionsCache, 1, 31);
		setOptionCache(OptionsCache, 2, 31);
		setOptionCache(OptionsCache, 3, 31);


		//NBA Live 08 - ps2
		// unranked
		setOptionCache(OptionsCache, 2, 18);
		setOptionCache(OptionsCache, 2, 18,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 18,4);
		setOptionCache(OptionsCache, 2, 18,3);


		//NBA Live 08 - xbox
		// unranked
		setOptionCache(OptionsCache, 2, 36);
		setOptionCache(OptionsCache, 2, 36,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 36,4);
		setOptionCache(OptionsCache, 2, 36,3);

		//NBA Live 08 - ps3
		// unranked
		setOptionCache(OptionsCache, 2, 41);
		setOptionCache(OptionsCache, 2, 41,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 41,4);
		setOptionCache(OptionsCache, 2, 41,3);


		//NBA Street - ps3
		// unranked
		setOptionCache(OptionsCache, 2, 28);
		setOptionCache(OptionsCache, 2, 28,1);
		setOptionCache(OptionsCache, 2, 28,2);
		setOptionCache(OptionsCache, 2, 28,3);
		setOptionCache(OptionsCache, 2, 28,4);
		setOptionCache(OptionsCache, 2, 28,5);

		//NBA Street - XBOX
		// unranked
		setOptionCache(OptionsCache, 2, 11);
		setOptionCache(OptionsCache, 2, 11,1);
		setOptionCache(OptionsCache, 2, 11,2);
		setOptionCache(OptionsCache, 2, 11,3);
		setOptionCache(OptionsCache, 2, 11,4);
		setOptionCache(OptionsCache, 2, 11,5);

		//FIFA08 xbox
		setOptionCache(OptionsCache, 2, 37);
		setOptionCache(OptionsCache, 2, 37,4);
		setOptionCache(OptionsCache, 2, 37,0);

		//FIFA08 ps2
		setOptionCache(OptionsCache, 2, 38);
		setOptionCache(OptionsCache, 2, 38,4);
		setOptionCache(OptionsCache, 2, 38,0);

		//FIFA08 ps3
		setOptionCache(OptionsCache, 2, 42);
		setOptionCache(OptionsCache, 2, 42,4);
		setOptionCache(OptionsCache, 2, 42,0);


		// NCAABBL08
		setOptionCache(OptionsCache, 2, 44);
		setOptionCache(OptionsCache, 2, 44,2);
		setOptionCache(OptionsCache, 2, 44,0);

		// Fight Night Round 3
		setOptionCache(OptionsCache, 2, 46);
		setOptionCache(OptionsCache, 2, 46,0);



		// Halo 3
		setOptionCache(OptionsCache, 2, 32);
		setOptionCache(OptionsCache, 3, 32);

		//NHL 08 - ps2
		// unranked
		setOptionCache(OptionsCache, 2, 19);
		setOptionCache(OptionsCache, 4, 19);
		setOptionCache(OptionsCache, 2, 19,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 19,1);
		setOptionCache(OptionsCache, 2, 19,1);

		//NHL 08 - xbox
		// unranked
		setOptionCache(OptionsCache, 2, 33);
		setOptionCache(OptionsCache, 4, 33);
		setOptionCache(OptionsCache, 2, 33,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 33,1);
		setOptionCache(OptionsCache, 2, 33,1);

		//NHL 08 - ps3
		// unranked
		setOptionCache(OptionsCache, 2, 39);
		setOptionCache(OptionsCache, 4, 39);
		setOptionCache(OptionsCache, 2, 39,0);
		//Ranked
		setOptionCache(OptionsCache, 1, 39,1);
		setOptionCache(OptionsCache, 2, 39,1);

		return OptionsCache;
	}

	// key = "gameid,optionid";
	private static void setOptionCache(THashMap<String, List<GameoptionsDTO>> OptionsCache , int optionseqid , int gameid) {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.ID, optionseqid);
		searchCriteria.setAttribute(G4GConstants.GAMEID_DB, gameid);
		searchCriteria.setOrderBy(G4GConstants.VALUE_SEQUENCE_ID);
		searchCriteria.setAsc(true);
		List<GameoptionsDTO> list = GameOptionServiceDelegator.getList( searchCriteria);
		OptionsCache.put(String.valueOf(gameid)+G4GConstants.COMMA+ String.valueOf(optionseqid), list);
	}

	// key = "gameid,optionid,valueid";
	// key = "gameid,optionid";
	private static void setOptionCache(THashMap<String, List<GameoptionsDTO>> OptionsCache, int optionseqid , int gameid  ,Integer... valueid) {
		SearchCriteria searchCriteria = new SearchCriteria();
		for (int i = 0; i < valueid.length; i++) {
			if(valueid[i]!=0) {
				searchCriteria.setAttribute(G4GConstants.VALUEID, valueid[i]);
			}
			searchCriteria.setOrderBy(G4GConstants.VALUE_SEQUENCE_ID);
			searchCriteria.setAsc(true);
			OptionsCache.put(String.valueOf(gameid)+G4GConstants.COMMA+ String.valueOf(optionseqid)+G4GConstants.COMMA+String.valueOf(valueid[i]) , GameOptionServiceDelegator.getGameOptions(gameid, optionseqid, searchCriteria));
		}
	}
}
