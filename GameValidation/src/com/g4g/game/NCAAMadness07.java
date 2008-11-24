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
public class NCAAMadness07 {
	static Properties props;

	static {
		props = new Properties();
		try {
			props.load(new FileInputStream(
					GameValidatorConstants.NCAAMADNESSPROPERTIES));
		} catch (IOException ioException) {
			System.out.println("Error : " + ioException.getMessage());
		}
	}

	public static String[] fields = {
			props.getProperty(GameValidatorConstants.GAMEMODE),
			props.getProperty(GameValidatorConstants.SKILLLEVEL),
			props.getProperty(GameValidatorConstants.GAMEWASCOMPLETED), "" };

}
