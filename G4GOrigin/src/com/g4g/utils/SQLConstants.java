/**
 *
 */
package com.g4g.utils;

/**
 * @author jigartr
 *
 */
public interface SQLConstants {
	public final static String COMMA = ", ";

	public final static String SPACE = " ";

	public final static String EMPTY_STRING = "";
	public final static String AVG = "avg";
	public final static String REPUTATION = "reputation";
	public final static String FEEDBACKREPUTATION = "feedbackreputation";
	public final static String OPTIONSEQUENCEID="optionsequenceid";
	public final static String VALUESEQUENCEID= "valuesequenceid";
	public final static String CEILING = "ceiling";
	public final static String QUOTE = "'";
	public final static String N = " n ";
	public final static String DISTINCT = " distinct ";
	public final static String DOT= ".";
	public final static String SELECT = " Select ";

	public final static String UPDATE = " Update ";

	public final static String INSERT = " Insert into ";

	public final static String DELETE = " Delete ";

	public final static String VALUES = " values ";

	public final static String FROM = " From ";

	public final static String WHERE = " Where ";

	public final static String AND = " And ";

	public final static String OR = " Or ";

	public final static String EQUAL = " = ";

	public final static String GREATER = " > ";

	public final static String PERSENT = "%";

	public final static String NOT_EQUAL = " <> ";

	public final static String NOT = " Not ";

	public final static String IN = " In ";

	public final static String LIKE = " Like ";

	public final static String NOT_LIKE = " Not Like ";

	public final static String LIKE_SYMBOL = "%";

	public final static String UNION = " UNION ";

	public final static String SET = " Set ";

	public final static String OPEN_PAREN = " ( ";

	public final static String CLOSE_PAREN = " ) ";

	public final static String MODEDESC = " DESC ";

	public final static String MODEASC = " ASC ";

	public final static String SORT_BY = " sortBy ";

	public final static String SORT_MODE = " sortMode ";

	public final static String ORDERBY = " Order By ";

	public final static String AS = " AS ";

	public final static String IS = " IS ";

	public final static String NULL = " NULL ";

	public final static String DECODE = " decode ";

	public final static String ON = " ON "; // Used by DB2 Left Outer Join

	public final static String DELIMIT = " | "; // Used by DB2 Left Outer
	// Join

	public final static String DELIMIT_NO_SPACE = "|";

	public final static String MIN = " MIN ";

	public final static String MAX = " MAX ";

	public final static String UPPER = " UPPER ";

	public final static String LOWER = " LOWER ";

	public final static String ASTERISTIC = " * ";

	public final static String GREATER_EQUAL = " >= ";

	public final static String LESS_EQUAL = " <= ";

	public final static String LESS = " < ";

	public final static String MINUS = " - ";

	public final static String PLUS = " + ";

	public final static String CNT = " CNT ";

	public final static String LIMIT = " LIMIT ";

	public final static String INTERVAL = " INTERVAL ";

	public final static String COUNT = " COUNT ";

	public final static String GAMEIDEQUALS = "gameid= ";

	public final static String SCREENNAME_SQL = "screenname ";

	public final static String ISONLINE_SQL = "isonline ";

	public final static String TRUE = " true ";

	public final static String GROUPBY = "GROUP BY";


	// Used by DB2 Left Outer Join


	/* Query Constants - Starts*/

	public static final String TM = " tm";
	public static final String P = " p";
	public static final String TT = " tt";
	public static final String G = " g";
	public static final String PG = " pg";
	public static final String T = " t";
	public static final String M = " m";

	public static final String COMPLETEDDATE_SQL = "completeddate ";
	public static final String GAMECOMPLETED_SQL = "gamecompleted ";
	public static final String SCHEDULEDSTARTDATE_SQL = "scheduledstartdate ";
	public static final String PAYOUTAMOUNT_SQL = "payoutamount ";
	public static final String TWOPLAYERTOURNAMENT_SQL = "twoplayertournament ";
	public static final String TWOPLAYERMATCHCOMMENTS_SQL = " Twoplayermatchcomments ";
	public static final String PLAYER_SQL = "player ";
	public static final String TWOPLAYERMATCH_SQL = "twoplayermatch ";
	public static final String GAME_SQL = "game ";



	public static final String PLAYERGAME_SQL = "playergame ";
	public static final String LEVELS_SQL = "levels ";
	public static final String GAMENAME_SQL = "gamename ";
	public static final String COMPLETIONDATE_SQL = "completiondate ";
	public static final String CANCELLATIONDATE_SQL = "cancellationdate ";
	public static final String CURRENT_TIMESTAMP = " CURRENT_TIMESTAMP ";
	public static final String ENTRYFEE_SQL = "entryfee";
	public static final String IMGSRC_SQL = "imgsrc ";
	public static final String NOW_SQL = " now() ";
	public static final String CREATIONDATE_SQL = "creationdate ";
	public static final String PLAYERNETWORK_SQL = "playernetwork ";
	public static final String LOGINNAME_SQL = "loginname ";
	public static final String EMAILADDRESS_SQL = "emailaddress ";
	public static final String TOUR_SQL = "tour";
	public static final String CURRENT_TIMESTAMP_AT_TIMEZONE = "CURRENT_TIMESTAMP AT TIME ZONE";
	public static final String NEGETIVE_ONE_HOURS = "'-1 hours'";
	public static final String NEGATIVE_EIGHTY_MINUTES = "'-80 minutes'";
	public static final String PLAYERBYFROMPLAYERID_SQL = "playerbyfromplayerid ";
	public static final String PLAYERBYTOPLAYERID_SQL = "playerbytoplayerid ";
	public static final String TWOPLAYERMATCHCOMMENTS = "twoplayermatchcomments ";
	public static final String FRIENDS_SQL = "friends ";
	public static final String RANDOMID_SQL = "randomid";
	public static final String TOURNAMENTID_SQL = "tournamentid ";
	public static final String MATCHID_SQL = "matchid ";
	public static final String PLAYERID_SQL = "playerid ";
	public static final String ISRATED ="israted";
	public static final String GAMEID_SQL = "gameid ";

	public static final String MESSAGE_SQL = "message";

	//DTO - Starts
	public static final String MESSAGEDTO_SQL = "MessageDTO ";
	public static final String NATIONALCODEDTO_SQL = "NationalCodeDTO ";
	public static final String NETWORKDTO_SQL = "NetworkDTO ";
	public static final String NOTIFICATIONQUEUEDTO_SQL = "NotificationqueueDTO ";
	public static final String PLAYERIMDTO_SQL = " PlayerimDTO ";
	public static final String ADMINNOTIFICATIONDTO_SQL = "AdminnotificationDTO ";
	public static final String NOTIFICATIONTYPEDTO_SQL = "NotificationtypeDTO ";
	public static final String PLAYERDTO_SQL = "PlayerDTO ";
	public static final String SKINDTO_SQL = "SkinDTO ";
	public static final String TWOPLAYERMATCHDTO_SQL = "TwoplayermatchDTO ";
	public static final String ADMINDTO_SQL = "AdminDTO ";
	public static final String PICTURESDTO_SQL = "PicturesDTO ";
	public static final String USERDTO_SQL = "UserDTO ";
	public static final String PLAYERCOMMENTSDTO_SQL = "PlayercommentsDTO ";
	public static final String PLAYERGAMEDTO_SQL = "PlayergameDTO ";
	public static final String GAMEDTO_SQL = " GameDTO ";
	public static final String PLAYERNETWORKDTO_SQL = "PlayernetworkDTO ";
	public static final String FRIENDSDTO_SQL = "FriendsDTO ";
	public static final String TWOPLAYERTOURNAMENTDTO_SQL = "TwoplayertournamentDTO ";
	public static final String SUBNATIONALCODEDTO_SQL = "SubNationalCodeDTO ";
	public static final String TIMEZONEDTO_SQL = "TimeZoneDTO ";
	public static final String MATCHDTO_SQL = "MatchDTO ";
	public static final String TWOPLAYERTOURNAMENTGAMEOPTIONSDTO_SQL = "TwoplayertournamentgameoptionsDTO ";
	public static final String GAMEOPTIONSDTO_SQL = "GameoptionsDTO ";
	public static final String PLAYER2ACCEPTED_SQL = "player2accepted ";
	public static final String PLAYERID1_SQL = "playerid1";
	public static final String PLAYERID2_SQL = "playerid2";
	public static final String NETWORKNAME_SQL = "networkname";
	public static final String NETWORK_SQL = "network";
	public static final String NETWORKID_SQL = "networkid";
	public static final String PLAYERTWOID_SQL = "playertwoid ";
	public static final String INTERVAL_SQL = "interval";
	public static final String UTC = "'UTC'";
	public static final String PLAYERONEID_SQL = "playeroneid ";
	public static final String CURRENT_DATE_SQL = "current_date";
	public static final String INTEGER_SQL = "integer";
	public static final String PLAYER1ACCEPTED_SQL = "player1accepted ";

	//DTO - Ends

	/* Query Constants - Ends*/


}
