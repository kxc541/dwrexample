/**
 * 
 */
package com.g4g.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.g4g.helper.GameValidatorConstants;

/**
 * @author jigartr
 * 
 */
public class FightNight3 {
	static Properties props;

	static {
		props = new Properties();
		try {
			props.load(new FileInputStream(
					GameValidatorConstants.FIGHTNIGHTROUND3PROPERTIES));
		} catch (IOException ioException) {
			System.out.println("Error : " + ioException.getMessage());
		}
	}

	public static String[] fields = {
			props.getProperty(GameValidatorConstants.WEIGHTDIVISION),
			props.getProperty(GameValidatorConstants.GAMEMODE),
			props.getProperty(GameValidatorConstants.GAMEWASCOMPLETED), "" };

	public static boolean isValid(String ranked, String body) {
		return (body.indexOf(ranked) != -1) ? true : false;
	}
}
