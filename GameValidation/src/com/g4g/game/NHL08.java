package com.g4g.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.g4g.helper.GameValidatorConstants;

public class NHL08 {
	final static int OPTIONID_PS2 = 1;
	final static int OPTIONID_PS3 = 1;
	final static int OPTIONID_XBOX360 = 1;

	final static int DB_RANKED_ID = 1;

	final static String EMAIL_RANKED = "[Ranked]";

	static Properties props;

	static {
		props = new Properties();
		try {
			props.load(new FileInputStream(
					GameValidatorConstants.NHL08PROPERTIES));
		} catch (IOException ioException) {
			System.out.println("Error : " + ioException.getMessage());
		}
	}

	public static String[] fields = {
			props.getProperty(GameValidatorConstants.SCORE),
			props.getProperty(GameValidatorConstants.SHOTS),
			props.getProperty(GameValidatorConstants.HITS),
			props.getProperty(GameValidatorConstants.PENALTYMINUTES),
			props.getProperty(GameValidatorConstants.POWERPLAYGOALS),
			props.getProperty(GameValidatorConstants.POWERPLAYOPPORT),
			props.getProperty(GameValidatorConstants.FACEOFFSWON),
			props.getProperty(GameValidatorConstants.BREAKAWAYS),
			props.getProperty(GameValidatorConstants.ONETIMERS),
			props.getProperty(GameValidatorConstants.PASSING),
			props.getProperty(GameValidatorConstants.PENALTYSHOTS),
			props.getProperty(GameValidatorConstants.BLOCKEDSHOTS), "" };

	public static boolean isRanked(String body) {
		return (body.indexOf(EMAIL_RANKED) != -1) ? true : false;
	}

	public static boolean isDBRanked(int valueId) {
		return (DB_RANKED_ID == valueId) ? true : false;
	}

	/**
	 * 
	 * @param tournamentId
	 * @return
	 */
	public static String getModeSQL(int tournamentId) {
		// We will need to pass Console ID for distinguishing between
		// the different options ids in different console.
		StringBuffer str = new StringBuffer();
		str
				.append("SELECT valueID FROM twoplayertournamentgameoptions WHERE tournamentid =  ");
		str.append(tournamentId);
		str.append(" AND (");
		str.append(" optionId = " + OPTIONID_PS2);
		str.append(" OR ");
		str.append(" optionId = " + OPTIONID_PS3);
		str.append(" OR ");
		str.append(" optionId = " + OPTIONID_XBOX360);
		str.append(" ) ");
		return str.toString();
	}
}
