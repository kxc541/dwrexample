/**
 * 
 */
package com.g4g.utils;

/**
 * @author ankur
 * 
 */
public interface GameIdConstants {

	int NHL08XBOX = 33;
	int NHL08PS2 = 19;
	int NHL08PS3 = 39;

	int FIFA08XBOX = 37;
	int FIFA08PS2 = 38;
	int FIFA08PS3 = 42;

	int NBALive08XBOX = 36;
	int NBALive08PS2 = 18;
	int NBALive08PS3 = 41;
	
	int Nascar08XBOX = 13;
	int Nascar08PS2 = 20;
	int Nascar08PS3 = 45;
	
	int NCAAFootball08XBOX = 14;
	int NCAAFootball08PS2 = 21;
	int NCAAFootball08PS3 = 30;

	int NCAAMarchMadness08XBOX = 8;
	int NCAAMarchMadness08PS2 = 6;
	int NCAAMarchMadness08PS3 = 3;
	
	int Madden08XBOX = 16;
	int Madden08PS2 = 23;
	int Madden08PS3 = 31;

	int NBAStreetXBOX = 11;
	int NBAStreetPS3 = 28;

	int TigerWoods08XBOX = 27;
	int TigerWoods08PS3 = 2;

	int Halo2XBOX = 1;
	int Halo3XBOX = 32;

	int MotorStormPS3 = 4;
	
	int ResistanceFallofManPS3 = 5;

	int NCAABBL08PS3 = 44;

	int FightNightRound3PS3 = 46;

	int MLB08theShowPS2 = 7;
	
	int ProjectGothamRacing4XBOX = 9;
	

	int Ranked = 1;
	int UnRanked = 2;

	String replacement = "{0}";
	String valuesequenceid = "valuesequenceid";
	String optionsequenceid = "optionsequenceid";

	String option = "ajax.xml.option";
	String options = "ajax.xml.gameoption";
	String challengecard = "ajax.xml.challengecard";

	String gameoptionquery = "from GameoptionsDTO where gameid="; 
	String  middelquery =" and (optionsequenceid=";
	String gameoptionquerylast = ") order by valuesequenceid asc ";

	String gameoptionqueryapp = "and ( ";
	String valueid = "valueid";

}
