package com.g4g.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.g4g.helper.GameValidatorConstants;

public class NFL07 {
	static Properties props;
	
	static {
		props = new Properties();
		try {
			props.load(new FileInputStream(GameValidatorConstants.NFL07PROPERTIES));
		} catch (IOException ioException) {
			System.out.println("Error : " + ioException.getMessage());
		}
	}
	
	public static String[] fields = {
		props.getProperty(GameValidatorConstants.STATISTICS),
		props.getProperty(GameValidatorConstants.RUSHINGATTEMPTSFOR),
		props.getProperty(GameValidatorConstants.RUSHINGYARDSFOR),
		props.getProperty(GameValidatorConstants.PASSINGATTEMPTSFOR),
		props.getProperty(GameValidatorConstants.PASSINGYARDSFOR), 
		props.getProperty(GameValidatorConstants.PUNTS),
		props.getProperty(GameValidatorConstants.FIELDGOALSFOR),
		props.getProperty(GameValidatorConstants.LONGFIELDGOALS40PLUSFOR),
		props.getProperty(GameValidatorConstants.RUSHINGTOUCHDOWNSFOR),
		props.getProperty(GameValidatorConstants.PASSINGTOUCHDOWNSFOR),
		props.getProperty(GameValidatorConstants.RETURNSTOUCHDOWNSFOR),
		props.getProperty(GameValidatorConstants.DEFENSIVETOUCHDOWNSFOR),
		props.getProperty(GameValidatorConstants.TURNOVERSFOR),
		props.getProperty(GameValidatorConstants.INTERCEPTIONSFOR),
		props.getProperty(GameValidatorConstants.SACKSFOR),
		props.getProperty(GameValidatorConstants.NUMBEROFTHIRDDOWNS),
		props.getProperty(GameValidatorConstants.THIRDDOWNCONVERSIONS),
		props.getProperty(GameValidatorConstants.NOHUDDLEPLAYS),
		props.getProperty(GameValidatorConstants.AUDIBLESCALLED),
		props.getProperty(GameValidatorConstants.HOTROUTESUSED),
		props.getProperty(GameValidatorConstants.SHOTGUNPLAYS), 
		props.getProperty(GameValidatorConstants.BLITZES),
		props.getProperty(GameValidatorConstants.TIMEOFPOSSESSION),
		props.getProperty(GameValidatorConstants.TWOPOINTCONVERSIONSTRIED),
		props.getProperty(GameValidatorConstants.TWOPOINTCONVERSIONSSUCCEEDED),
		props.getProperty(GameValidatorConstants.REDZONEFIELDGOALS),
		props.getProperty(GameValidatorConstants.REDZONETOUCHDOWNS),
		props.getProperty(GameValidatorConstants.REDZONEOPPORTUNITIES),
		props.getProperty(GameValidatorConstants.FIRSTDOWNS),
		props.getProperty(GameValidatorConstants.FIRSTDOWNYARDS),
		props.getProperty(GameValidatorConstants.SPECIALTEAMSYARDS),
		props.getProperty(GameValidatorConstants.PUNTRETURNYARDS),
		props.getProperty(GameValidatorConstants.PUNTYARDS), 
		props.getProperty(GameValidatorConstants.PENALTIES),
		props.getProperty(GameValidatorConstants.PENALTYYARDS),
		props.getProperty(GameValidatorConstants.FIRSTDOWNRUSHINGPLAYS),
		props.getProperty(GameValidatorConstants.FIRSTDOWNPASSINGPLAYS),
		props.getProperty(GameValidatorConstants.SECONDDOWNRUSHINGPLAYS),
		props.getProperty(GameValidatorConstants.SECONDDOWNPASSINGPLAYS),
		props.getProperty(GameValidatorConstants.THIRDDOWNRUSHINGPLAYS),
		props.getProperty(GameValidatorConstants.THIRDDOWNPASSINGPLAYS),
		props.getProperty(GameValidatorConstants.FOURTHDOWNRUSHINGPLAYS),
		props.getProperty(GameValidatorConstants.FOURTHDOWNPASSINGPLAYS),
		props.getProperty(GameValidatorConstants.PASSESCOMPLETED), 
		props.getProperty(GameValidatorConstants.FUMBLES),
		props.getProperty(GameValidatorConstants.FUMBLESRECOVERED),
		props.getProperty(GameValidatorConstants.POSSESSIONS),
		props.getProperty(GameValidatorConstants.THIRDDOWNS3ORLESSYARDS),
		props.getProperty(GameValidatorConstants.SUCCESSES),
		props.getProperty(GameValidatorConstants.THIRDDOWNS4TO9YARD),
		props.getProperty(GameValidatorConstants.SUCCESSES),
		props.getProperty(GameValidatorConstants.THIRDDOWNS10ORMOREYARDS),
		props.getProperty(GameValidatorConstants.SUCCESSES), 
		props.getProperty(GameValidatorConstants.HURRIES),
		props.getProperty(GameValidatorConstants.KNOCKDOWNS),
		props.getProperty(GameValidatorConstants.PASSESDEFLECTED),
		props.getProperty(GameValidatorConstants.PASSESTIPPED),
		props.getProperty(GameValidatorConstants.OFFENSIVEYARDS),
		props.getProperty(GameValidatorConstants.TOTALYARDS), 
		props.getProperty(GameValidatorConstants.RETURNYARDS),
		props.getProperty(GameValidatorConstants.TOTALTOUCHDOWNS), "" };
}
