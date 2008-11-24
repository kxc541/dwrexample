package com.g4g.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

public class GameValidatorJob implements Job {
	private final static String DRIVER = G4GProperties.getProperty(PropertiesConstants.G4G_DB_DRIVER);

	private final static String URL = G4GProperties.getProperty(PropertiesConstants.G4G_DB_URL);

	private final static String USERNAME = G4GProperties.getProperty(PropertiesConstants.G4G_DB_USERNAME);

	private final static String PASSWORD = G4GProperties.getProperty(PropertiesConstants.G4G_DB_PASSWORD);

	private final static IMAPHelper imapHelper = new IMAPHelper(G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_IP), G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_USERNAME), G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_PASSWORD), G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_AUTH), G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_NAME), Integer.parseInt(G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_PORT)), G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_FOLDER));
	
	private Connection connection = null;
	
	private PreparedStatement preparedStatement = null;

	private Statement statement = null;

	private ResultSet resultSet = null;
	
	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			Class.forName(DRIVER);
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			imapHelper.connect();
			MailMessage[] message = imapHelper.getMessages();
			
			int winnerId = 0;
			
			for(int index = 0; index < message.length; index++) {
				StringBuffer query = new StringBuffer();
				query.append("SELECT DISTINCT t.*, (SELECT screenname FROM player WHERE playerid = t.playeroneid) as playeronename, (SELECT screenname FROM player WHERE playerid = t.playertwoid) as playertwoname, (SELECT gamename FROM game WHERE gameid = tpt.gameid) as gamename FROM twoplayermatch t, player p WHERE twoplayertournament tpt WHERE tpt.tournamentid = t.tournamentid And scheduledstartdate <= '").append(message[index].getRecievedDate()).append("' And scheduledstartdate + interval '2 hours' >= '").append(message[index].getRecievedDate()).append("' AND gamecompleted = 0");
					
				System.out.println("Query : " + query.toString());
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query.toString());
					
				while(resultSet.next()) {
					String subject = message[index].getSubject();
					String[] playerNames = null;
					if(subject.startsWith(resultSet.getString(18) + " - ")) {
						String tmpSubject = subject.substring((resultSet.getString(18) + " - ").length(), subject.length());
						playerNames = tmpSubject.split(" vs. ");
						
						String[] playerOne = playerNames[0].split(" ");
						String[] playerTwo = playerNames[1].split(" ");
						
						String playerOneName = getPlayerName(playerOne);
						String playerTwoName = getPlayerName(playerTwo);
						
						System.out.println("Player One " + playerOneName + " = " + resultSet.getString(16));
						System.out.println("Player Two " + playerOneName + " = " + resultSet.getString(17));
						
						if((playerOneName.equals(resultSet.getString(16)) || playerOneName.equals(resultSet.getString(17))) && (playerTwoName.equals(resultSet.getString(16)) || playerTwoName.equals(resultSet.getString(17)))) {
							if(Integer.parseInt(playerOne[playerOne.length - 1]) < Integer.parseInt(playerTwo[playerTwo.length - 1])) {
								winnerId = resultSet.getInt(6);
							} else if(Integer.parseInt(playerOne[playerOne.length - 1]) > Integer.parseInt(playerTwo[playerTwo.length - 1])) {
								winnerId = resultSet.getInt(4);
							} 
							
							preparedStatement = connection.prepareStatement("UPDATE twoplayermatch SET winnerid = ?, resultbody = ?, completeddate = ?, gamecompleted = 1 WHERE matchid = ?");
							preparedStatement.setInt(1, winnerId);
							preparedStatement.setString(2, message[index].getBody());
							preparedStatement.setDate(3, new java.sql.Date(message[index].getRecievedDate().getTime()));
							preparedStatement.setInt(4, resultSet.getInt(1));
							
							preparedStatement.execute();
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
}
