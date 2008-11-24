package com.g4g.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.BetDetails;
import com.impessa.worldgaming.client.LiquidPayment;
import com.impessa.worldgaming.client.LiquidPaymentService;
import com.impessa.worldgaming.client.PlayerTransactionType;

public class GameValidatorJob implements Job {
	private final static String METHOD_ISRANKED = "isRanked";

	private final static String METHOD_GETMODESQL = "getModeSQL";

	private final static String DRIVER = G4GProperties
			.getProperty(PropertiesConstants.G4G_DB_DRIVER);

	private final static String URL = G4GProperties
			.getProperty(PropertiesConstants.G4G_DB_URL);

	private final static String USERNAME = G4GProperties
			.getProperty(PropertiesConstants.G4G_DB_USERNAME);

	private final static String PASSWORD = G4GProperties
			.getProperty(PropertiesConstants.G4G_DB_PASSWORD);

	private final static IMAPHelper imapHelper = new IMAPHelper(G4GProperties
			.getProperty(PropertiesConstants.G4G_IMAP_IP), G4GProperties
			.getProperty(PropertiesConstants.G4G_IMAP_USERNAME), G4GProperties
			.getProperty(PropertiesConstants.G4G_IMAP_PASSWORD), G4GProperties
			.getProperty(PropertiesConstants.G4G_IMAP_AUTH), G4GProperties
			.getProperty(PropertiesConstants.G4G_IMAP_NAME), Integer
			.parseInt(G4GProperties
					.getProperty(PropertiesConstants.G4G_IMAP_PORT)),
			G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_FOLDER));

	private Connection connection = null;

	private PreparedStatement preparedStatement = null;

	private Statement statement = null;

	private ResultSet resultSet = null;

	private String HALO2 = "Halo 2";

	private String HALO3 = "Halo 3";

	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			Class.forName(DRIVER);
			this.connection = DriverManager.getConnection(URL, USERNAME,
					PASSWORD);

			int winnerId = 0;
			int losserId = 0;
			boolean isTournament = false;

			imapHelper.connect();
			MailMessage[] message = imapHelper.getMessages();

			StringBuffer query = new StringBuffer();

			// Collect all the records waiting for validation
			query.append("SELECT DISTINCT t.*, ");
			query.append("	(SELECT playernetworktag ");
			query.append("		FROM playernetwork ");
			query
					.append("		WHERE playerid = t.playeroneid AND networkid = (SELECT networkid FROM game WHERE gameid = tpt.gameid)) as playeronename, ");
			query.append("	(SELECT playernetworktag ");
			query.append("		FROM playernetwork ");
			query
					.append("		WHERE playerid = t.playertwoid AND networkid = (SELECT networkid FROM game WHERE gameid = tpt.gameid)) as playertwoname, ");
			query.append("	(SELECT gamename ");
			query.append("		FROM game ");
			query.append("		WHERE gameid = tpt.gameid) as gamename ");
			query
					.append("FROM twoplayermatch t, player p, twoplayertournament tpt ");
			query
					.append("WHERE tpt.tournamentid = t.tournamentid And ((scheduledstartdate <= CURRENT_TIMESTAMP And scheduledstartdate + interval '2 hours' >= CURRENT_TIMESTAMP And tpt.levels = 1) OR ((scheduledstartdate <= CURRENT_TIMESTAMP And enddate >= CURRENT_TIMESTAMP And tpt.levels > 1))) AND gamecompleted = 0");

			System.out.println("Query : " + query.toString());
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			while (resultSet.next()) {
				for (int index = 0; index < message.length; index++) {

					String subject = message[index].getSubject();
					String gameName = message[index].getGameName();

					String[] playerNames = null;
					if (resultSet.getString(18).equals(gameName)) {
						String tmpSubject = subject.substring(subject
								.indexOf("-") + 2, subject.length());
						playerNames = tmpSubject.split(" vs. ");

						String[] playerOne = playerNames[0].split(" ");
						String[] playerTwo = playerNames[1].split(" ");

						String playerOneName = getPlayerName(playerOne);
						String playerTwoName = getPlayerName(playerTwo);

						System.out.println("Player One " + playerOneName
								+ " = " + resultSet.getString(16));
						System.out.println("Player Two " + playerTwoName
								+ " = " + resultSet.getString(17));

						if ((playerOneName.equals(resultSet.getString(16)) || playerOneName
								.equals(resultSet.getString(17)))
								&& (playerTwoName.equals(resultSet
										.getString(16)) || playerTwoName
										.equals(resultSet.getString(17)))) {
							MailParser mailParser = null;
							if (gameName
									.equals(G4GProperties
											.getProperty(PropertiesConstants.G4G_DB_GAME_NBALIVE08)))
								mailParser = new MailParser(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_GAME_NBALIVE08_CLASS));
							else if (gameName
									.equals(G4GProperties
											.getProperty(PropertiesConstants.G4G_DB_GAME_NBASTREET)))
								mailParser = new MailParser(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_GAME_NBASTREET_CLASS));
							else if (gameName
									.equals(G4GProperties
											.getProperty(PropertiesConstants.G4G_DB_GAME_NCAAFOOTBALL08)))
								mailParser = new MailParser(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_GAME_NCAAFOOTBALL08_CLASS));
							else if (gameName
									.equals(G4GProperties
											.getProperty(PropertiesConstants.G4G_DB_GAME_FIFA08)))
								mailParser = new MailParser(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_GAME_FIFA08_CLASS));

							if (mailParser == null)
								continue;

							Statement rankStatement = connection
									.createStatement();
							ResultSet rankResultSet = rankStatement
									.executeQuery(mailParser
											.getRankedSQL(resultSet.getInt(2)));
							int valueId = 0;
							while (rankResultSet.next()) {
								valueId = rankResultSet.getInt(1);
							}

							boolean mailRanked = mailParser
									.isMailRanked(message[index].getBody());
							boolean dbRanked = mailParser.isDBRanked(valueId);

							if (mailRanked != dbRanked)
								continue;

							if (Integer
									.parseInt(playerOne[playerOne.length - 1]) < Integer
									.parseInt(playerTwo[playerTwo.length - 1])) {
								winnerId = resultSet.getInt(6);
								losserId = resultSet.getInt(4);
							} else if (Integer
									.parseInt(playerOne[playerOne.length - 1]) > Integer
									.parseInt(playerTwo[playerTwo.length - 1])) {
								winnerId = resultSet.getInt(4);
								losserId = resultSet.getInt(6);
							}

							preparedStatement = connection
									.prepareStatement("SELECT levels FROM twoplayertournament WHERE tournamentid = ?");
							preparedStatement.setInt(1, resultSet.getInt(2));

							int levels = 0;
							ResultSet resultSetLevel = preparedStatement
									.executeQuery();

							while (resultSetLevel.next()) {
								levels = resultSetLevel.getInt(1);
								isTournament = (levels > 1) ? true : false;
							}

							preparedStatement = connection
									.prepareStatement("UPDATE twoplayermatch SET winnerid = ?, resultbody = ?, completeddate = ?, gamecompleted = 1 WHERE matchid = ? AND gamecompleted == 0");
							preparedStatement.setInt(1, winnerId);
							preparedStatement.setString(2, message[index]
									.getBody());
							preparedStatement
									.setDate(3, new java.sql.Date(
											message[index].getRecievedDate()
													.getTime()));
							preparedStatement.setInt(4, resultSet.getInt(1));

							preparedStatement.execute();

							if (winnerId != 0) {
								preparedStatement = connection
										.prepareStatement("UPDATE player SET recordwins = recordwins + 1 WHERE playerid = ?");
								preparedStatement.setInt(1, winnerId);
								preparedStatement.execute();

								preparedStatement = connection
										.prepareStatement("UPDATE player SET recordlosses = recordlosses + 1 WHERE playerid = ?");
								preparedStatement.setInt(1, losserId);
								preparedStatement.execute();

								if (isTournament) {
									updateTournamentDetails(winnerId, resultSet
											.getInt(9));
								}
							}

							if (levels == resultSet.getInt(3)) {
								LiquidPaymentService liquidPaymentService = new LiquidPaymentService();
								LiquidPayment liquidPayment = liquidPaymentService
										.getLiquidPaymentPort();

								statement = connection.createStatement();
								resultSet = statement
										.executeQuery("select tt.betid, (SELECT emailaddress from player where playerid = tm.winnerid), (SELECT skinid from player where playerid = tm.winnerid) from twoplayermatch tm, twoplayertournament tt WHERE tm.tournamentid = tt.tournamentid");

								if (winnerId != 0) {
									BetDetails betDetails = liquidPayment
											.getBetDetails(resultSet
													.getString(1));
									liquidPayment.moveToPlayerAccount(
											betDetails.getId(), resultSet
													.getString(2), betDetails
													.getAmount(),
											PlayerTransactionType.PW);
									liquidPayment.moveToHouseAccount(betDetails
											.getId(), resultSet.getString(3),
											betDetails.getAmount());
								} else {
									BetDetails betDetails = liquidPayment
											.getBetDetails(resultSet
													.getString(1));
									liquidPayment.moveToPlayerAccount(
											betDetails.getId(), resultSet
													.getString(2), betDetails
													.getAmount(),
											PlayerTransactionType.PC);
								}
							}

							try {
								if (resultSetLevel != null)
									resultSetLevel.close();
							} catch (SQLException sqlException) {
								sqlException.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	private void validateRSS() {
		int winnerId = 0;
		int losserId = 0;
		boolean isTournament = false;

		try {
			StringBuffer query = new StringBuffer();
			query
					.append(
							"SELECT DISTINCT t.*, (SELECT screenname FROM player WHERE playerid = t.playeroneid) as playeronename, (SELECT screenname FROM player WHERE playerid = t.playertwoid) as playertwoname, (SELECT gamename FROM game WHERE gameid = tpt.gameid) as gamename FROM twoplayermatch t, player p, twoplayertournament tpt, game g WHERE tpt.gameid = g.gameid AND (g.gamename like 'Halo 2' OR g.gamename like 'Halo 3') AND tpt.tournamentid = t.tournamentid And scheduledstartdate <= '")
					.append(new Date())
					.append(
							"' And scheduledstartdate + interval '2 hours' >= '")
					.append(new Date()).append("' AND gamecompleted = 0");

			System.out.println("Query : " + query.toString());
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			while (resultSet.next()) {
				System.out.println("Test : " + resultSet.getString(19));
				if (resultSet.getString(19).equals(HALO2)
						|| resultSet.getString(19).equals(HALO3)) {
					RSSParser rssParser = new RSSParser();

					if (resultSet.getString(19).equals(HALO2))
						rssParser
								.connect(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_BUNGIE_HALO2_URL),
										resultSet.getString(17));
					else
						rssParser
								.connect(
										G4GProperties
												.getProperty(PropertiesConstants.G4G_BUNGIE_HALO3_URL),
										resultSet.getString(17));

					List<RSSContent> rssContentList = rssParser.getRSSContent();
					int totalRSSContent = rssContentList.size();

					for (int rssIndex = 0; rssIndex < totalRSSContent; rssIndex++) {
						RSSContent rssContent = (RSSContent) rssContentList
								.get(rssIndex);
						String[][] players = rssContent.getPlayers();

						if ((players[0][0].equals(resultSet.getString(17)) || players[1][0]
								.equals(resultSet.getString(17)))
								&& (players[0][0].equals(resultSet
										.getString(18)) || players[1][0]
										.equals(resultSet.getString(18)))) {

							if ("1st".equals( players[0][0] )
									&& players[0][0].equals(resultSet
											.getString(17))) {
								winnerId = resultSet.getInt(4);
								losserId = resultSet.getInt(6);
							} else if (players[0][0].equals("1st")
									&& players[0][0].equals(resultSet
											.getString(18))) {
								winnerId = resultSet.getInt(6);
								losserId = resultSet.getInt(4);
							}

							preparedStatement = connection
									.prepareStatement("SELECT levels FROM twoplayertournament WHERE tournamentid = ?");
							preparedStatement.setInt(1, resultSet.getInt(2));

							int levels = 0;
							ResultSet resultSetLevel = preparedStatement
									.executeQuery();

							while (resultSetLevel.next()) {
								levels = resultSetLevel.getInt(1);
								isTournament = (levels > 1) ? true : false;
							}

							preparedStatement = connection
									.prepareStatement("UPDATE twoplayermatch SET winnerid = ?, resultbody = ?, completeddate = ?, gamecompleted = 1 WHERE matchid = ?");
							preparedStatement.setInt(1, winnerId);
							preparedStatement.setString(2, "Body");
							preparedStatement.setDate(3, new java.sql.Date(
									rssContent.getDate().getTime()));
							preparedStatement.setInt(4, resultSet.getInt(1));

							preparedStatement.execute();

							if (winnerId != 0) {
								preparedStatement = connection
										.prepareStatement("UPDATE player SET recordwins = recordwins + 1 WHERE playerid = ?");
								preparedStatement.setInt(1, winnerId);
								preparedStatement.execute();

								preparedStatement = connection
										.prepareStatement("UPDATE player SET recordlosses = recordlosses + 1 WHERE playerid = ?");
								preparedStatement.setInt(1, losserId);
								preparedStatement.execute();

								if (isTournament) {
									updateTournamentDetails(winnerId, resultSet
											.getInt(9));
								}
							}

							if (levels == resultSet.getInt(3)) {
								LiquidPaymentService liquidPaymentService = new LiquidPaymentService();
								LiquidPayment liquidPayment = liquidPaymentService
										.getLiquidPaymentPort();

								statement = connection.createStatement();
								resultSet = statement
										.executeQuery("select tt.betid, (SELECT emailaddress from player where playerid = tm.winnerid), (SELECT skinid from player where playerid = tm.winnerid) from twoplayermatch tm, twoplayertournament tt WHERE tm.tournamentid = tt.tournamentid");

								if (winnerId != 0) {
									BetDetails betDetails = liquidPayment
											.getBetDetails(resultSet
													.getString(1));
									liquidPayment.moveToPlayerAccount(
											betDetails.getId(), resultSet
													.getString(2), betDetails
													.getAmount(),
											PlayerTransactionType.PW);
									liquidPayment.moveToHouseAccount(betDetails
											.getId(), resultSet.getString(3),
											betDetails.getAmount());
								} else {
									BetDetails betDetails = liquidPayment
											.getBetDetails(resultSet
													.getString(1));
									liquidPayment.moveToPlayerAccount(
											betDetails.getId(), resultSet
													.getString(2), betDetails
													.getAmount(),
											PlayerTransactionType.PC);
								}
							}

							try {
								if (resultSetLevel != null)
									resultSetLevel.close();
							} catch (SQLException sqlException) {
								sqlException.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String getPlayerName(String[] strArray) {
		StringBuffer strName = new StringBuffer();
		strName.append(strArray[0]);
		for (int index = 1; index < strArray.length - 1; index++) {
			strName.append(" ").append(strArray[index]);
		}
		return strName.toString();
	}

	private static String getDate(Date date, String dateFormat) {
		Format formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * Update tournament details of TwoPlayerTournament table.
	 */
	private void updateTournamentDetails(int winnerId, int nextMatchId) {
		ResultSet matchResult = null;
		try {
			preparedStatement = connection
					.prepareStatement("SELECT matchid FROM twoplayermatch WHERE playeroneid is null AND matchid = ?");
			preparedStatement.setInt(1, nextMatchId);
			matchResult = preparedStatement.executeQuery();

			if (matchResult.next()) {
				preparedStatement = connection
						.prepareStatement("UPDATE twoplayermatch SET playeroneid = ?, playeroneaccepteddate = ? WHERE matchid = ?");
				preparedStatement.setInt(1, winnerId);
				preparedStatement.setDate(2, new java.sql.Date(todayGMT()
						.getTime()));
				preparedStatement.setInt(3, nextMatchId);

				preparedStatement.executeUpdate();
			} else {
				preparedStatement = connection
						.prepareStatement("SELECT matchid FROM twoplayermatch WHERE playertwoid is null AND matchid = ?");
				preparedStatement.setInt(1, nextMatchId);
				matchResult = preparedStatement.executeQuery();

				if (matchResult.next()) {
					preparedStatement = connection
							.prepareStatement("UPDATE twoplayermatch SET playertwoid = ?, playertwoaccepteddate = ? WHERE matchid = ?");
					preparedStatement.setInt(1, winnerId);
					preparedStatement.setDate(2, new java.sql.Date(todayGMT()
							.getTime()));
					preparedStatement.setInt(3, nextMatchId);

					preparedStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Date todayGMT() {
		DateFormat format = new SimpleDateFormat();
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Calendar today = Calendar.getInstance();
		int gmtoffset = today.get(Calendar.DST_OFFSET)
				+ today.get(Calendar.ZONE_OFFSET);

		// to convert to GMT time
		Date GMTDate = new Date(System.currentTimeMillis() - gmtoffset);

		try {
			return format.parse(format.format(GMTDate));
		} catch (Exception e) {
			return GMTDate;
		}
	}
}
