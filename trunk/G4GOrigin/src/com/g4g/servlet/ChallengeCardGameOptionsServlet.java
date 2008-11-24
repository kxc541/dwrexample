/**
 * 
 */
package com.g4g.servlet;

import static com.g4g.utils.GameIdConstants.FIFA08PS2;
import static com.g4g.utils.GameIdConstants.FIFA08PS3;
import static com.g4g.utils.GameIdConstants.FIFA08XBOX;
import static com.g4g.utils.GameIdConstants.FightNightRound3PS3;
import static com.g4g.utils.GameIdConstants.Halo2XBOX;
import static com.g4g.utils.GameIdConstants.Halo3XBOX;
import static com.g4g.utils.GameIdConstants.MLB08theShowPS2;
import static com.g4g.utils.GameIdConstants.Madden08PS2;
import static com.g4g.utils.GameIdConstants.Madden08PS3;
import static com.g4g.utils.GameIdConstants.Madden08XBOX;
import static com.g4g.utils.GameIdConstants.MotorStormPS3;
import static com.g4g.utils.GameIdConstants.NBALive08PS2;
import static com.g4g.utils.GameIdConstants.NBALive08PS3;
import static com.g4g.utils.GameIdConstants.NBALive08XBOX;
import static com.g4g.utils.GameIdConstants.NBAStreetPS3;
import static com.g4g.utils.GameIdConstants.NBAStreetXBOX;
import static com.g4g.utils.GameIdConstants.NCAABBL08PS3;
import static com.g4g.utils.GameIdConstants.NCAAFootball08PS2;
import static com.g4g.utils.GameIdConstants.NCAAFootball08PS3;
import static com.g4g.utils.GameIdConstants.NCAAFootball08XBOX;
import static com.g4g.utils.GameIdConstants.NCAAMarchMadness08PS2;
import static com.g4g.utils.GameIdConstants.NCAAMarchMadness08PS3;
import static com.g4g.utils.GameIdConstants.NCAAMarchMadness08XBOX;
import static com.g4g.utils.GameIdConstants.NHL08PS2;
import static com.g4g.utils.GameIdConstants.NHL08PS3;
import static com.g4g.utils.GameIdConstants.NHL08XBOX;
import static com.g4g.utils.GameIdConstants.Nascar08PS2;
import static com.g4g.utils.GameIdConstants.Nascar08PS3;
import static com.g4g.utils.GameIdConstants.Nascar08XBOX;
import static com.g4g.utils.GameIdConstants.ProjectGothamRacing4XBOX;
import static com.g4g.utils.GameIdConstants.ResistanceFallofManPS3;
import static com.g4g.utils.GameIdConstants.TigerWoods08PS3;
import static com.g4g.utils.GameIdConstants.TigerWoods08XBOX;
import gnu.trove.THashMap;

import java.util.List;

import com.g4g.challengecard.FIFA08PS2Options;
import com.g4g.challengecard.FIFA08PS3Options;
import com.g4g.challengecard.FIFA08XBOXOptions;
import com.g4g.challengecard.FightNightRound3PS3Options;
import com.g4g.challengecard.Halo2XBOXOptions;
import com.g4g.challengecard.Halo3XBOXOptions;
import com.g4g.challengecard.MLB08theShowPS2Options;
import com.g4g.challengecard.Madden08PS2Options;
import com.g4g.challengecard.Madden08PS3Options;
import com.g4g.challengecard.Madden08XBOXOptions;
import com.g4g.challengecard.MotorStormPS3Options;
import com.g4g.challengecard.NBALive08PS2Options;
import com.g4g.challengecard.NBALive08PS3Options;
import com.g4g.challengecard.NBALive08XBOXOptions;
import com.g4g.challengecard.NBAStreetPS3Options;
import com.g4g.challengecard.NBAStreetXBOXOptions;
import com.g4g.challengecard.NCAABBL08PS3Options;
import com.g4g.challengecard.NCAAFootball08PS2Options;
import com.g4g.challengecard.NCAAFootball08PS3Options;
import com.g4g.challengecard.NCAAFootball08XBOXOptions;
import com.g4g.challengecard.NCAAMarchMadness08PS2Options;
import com.g4g.challengecard.NCAAMarchMadness08PS3Options;
import com.g4g.challengecard.NCAAMarchMadness08XBOXOptions;
import com.g4g.challengecard.NHL08PS2Options;
import com.g4g.challengecard.NHL08PS3Options;
import com.g4g.challengecard.NHL08XBOXOptions;
import com.g4g.challengecard.Nascar08PS3Options;
import com.g4g.challengecard.ProjectGothamRacing4XBOXOptions;
import com.g4g.challengecard.ResistanceFallofManPS3Options;
import com.g4g.challengecard.TigerWoods08PS3Options;
import com.g4g.challengecard.TigerWoods08XBOXOptions;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.utils.G4GConstants;

/**
 * @author ankur
 */
public class ChallengeCardGameOptionsServlet {
	
	 

	/**
	 * This method calls methods that return gameoptions xml for next select boxes
	 * @param map 
	 * @param valuesequenceidinteger
	 * @param gameid
	 * @param optionsequenceid
	 * @return String(xml)
	 */
	public static String getNextGameOptionsXML(THashMap<String, List<GameoptionsDTO>> map, Integer[] valuesequenceidinteger, int gameid, int optionsequenceid) {
		if (gameid == NHL08XBOX) {
			return NHL08XBOXOptions.getNHL08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NHL08PS2) {
			return NHL08PS2Options.getNHL08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NHL08PS3) {
			return NHL08PS3Options.getNHL08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == FIFA08XBOX) {
			return FIFA08XBOXOptions.getFIFA08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == FIFA08PS2) {
			return FIFA08PS2Options.getFIFA08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == FIFA08PS3) {
			return FIFA08PS3Options.getFIFA08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NBALive08XBOX) {
			return NBALive08XBOXOptions.getNBALive08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NBALive08PS2) {
			return NBALive08PS2Options.getNBALive08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NBALive08PS3) {
			return NBALive08PS3Options.getNBALive08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Nascar08XBOX) {
			return Nascar08PS3Options.getNascar08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Nascar08PS2) {
			return Nascar08PS3Options.getNascar08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Nascar08PS3) {
			return Nascar08PS3Options.getNascar08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAFootball08PS2) {
			return NCAAFootball08PS2Options.getNCAAFootball08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAFootball08XBOX) {
			return NCAAFootball08XBOXOptions.getNCAAFootball08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAFootball08PS3) {
			return NCAAFootball08PS3Options.getNCAAFootball08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAMarchMadness08XBOX) {
			return NCAAMarchMadness08XBOXOptions.getNCAAMarchMadness08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAMarchMadness08PS2) {
			return NCAAMarchMadness08PS2Options.getNCAAMarchMadness08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAAMarchMadness08PS3) {
			return NCAAMarchMadness08PS3Options.getNCAAMarchMadness08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Madden08XBOX) {
			return Madden08XBOXOptions.getMadden08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Madden08PS2) {
			return Madden08PS2Options.getMadden08PS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Madden08PS3) {
			return Madden08PS3Options.getMadden08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NBAStreetXBOX) {
			return NBAStreetXBOXOptions.getNBAStreetXBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NBAStreetPS3) {
			return NBAStreetPS3Options.getNBAStreetPS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == TigerWoods08XBOX) {
			return TigerWoods08XBOXOptions.getTigerWoods08XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == TigerWoods08PS3) {
			return TigerWoods08PS3Options.getTigerWoods08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Halo2XBOX) {
			return Halo2XBOXOptions.getHalo2XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == Halo3XBOX) {
			return Halo3XBOXOptions.getHalo3XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == MotorStormPS3) {
			return MotorStormPS3Options.getMotorStormPS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == ResistanceFallofManPS3) {
			return ResistanceFallofManPS3Options.getResistanceFallofManPS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == NCAABBL08PS3) {
			return NCAABBL08PS3Options.getNCAABBL08PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == FightNightRound3PS3) {
			return FightNightRound3PS3Options.getFightNightRound3PS3(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == MLB08theShowPS2) {
			return MLB08theShowPS2Options.getMLB08theShowPS2(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		if (gameid == ProjectGothamRacing4XBOX) {
			return ProjectGothamRacing4XBOXOptions.getProjectGothamRacing4XBOX(map,valuesequenceidinteger, gameid, optionsequenceid);
		}
		

		return null;

	}

	/**
	 * Used for all games to check is it ranked
	 * @param valuesequenceid
	 * @param optionsequenceid
	 * @return boolean
	 */
	public static boolean isRanked(int valuesequenceid, int optionsequenceid) {
		if (valuesequenceid == G4GConstants.ONE_NUMBER && optionsequenceid == G4GConstants.ONE_NUMBER) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Used for all games to check is it unranked
	 * @param valuesequenceid
	 * @param optionsequenceid
	 * @return boolean
	 */
	public static boolean isUnRanked(int valuesequenceid, int optionsequenceid) {
		return !isRanked(valuesequenceid, optionsequenceid);
	}
	
	
	/**
	 * Used for NHL to check is it shootout
	 * @param valuesequenceid
	 * @param optionsequenceid
	 * @return boolean
	 */
	public static boolean isShootout(int valuesequenceid, int optionsequenceid) {
		if (valuesequenceid == G4GConstants.ONE_NUMBER && optionsequenceid == G4GConstants.ONE_NUMBER) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Used for NHL to check is it ranked in normal mode
	 * @param valuesequenceid
	 * @param optionsequenceid
	 * @return boolean
	 */
	public static boolean isNHLRanked(int valuesequenceid, int optionsequenceid) {
		if (valuesequenceid == G4GConstants.ONE_NUMBER && optionsequenceid == G4GConstants.TWO_NUMBER) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Used for NHL to check is it ranked in shootout mode
	 * @param valuesequenceidarray
	 * @return boolean
	 */
	public static boolean isShootoutRanked(Integer[] valuesequenceidarray) {
		if (valuesequenceidarray[0] == G4GConstants.ONE_NUMBER && valuesequenceidarray[1] == G4GConstants.TWO_NUMBER) {
			return true;
		}
		else {
			return false;
		}
	}

}
