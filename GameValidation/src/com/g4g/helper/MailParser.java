package com.g4g.helper;

/**
 * Parsing the mail from IMAP server.
 * 
 * @author Jigar Mistry
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MailParser {
	// private static org.apache.log4j.Logger LOGGER =
	// org.apache.log4j.Logger.getLogger( MailParser.class );

	// static FileInputStream in = new FileInputStream("");
	static int playerIndex = 0;

	static int noOfPlayers = 2;

	static String[][] players = null;

	static String[] fields = null;

	private Method isValidMethod = null;

	private Method getModeSQLMethod = null;

	@SuppressWarnings("unchecked")
	public MailParser(String className) {
		try {
			Class game = Class.forName(className);
			Object theObject = game.newInstance();
			Field field = game.getDeclaredField("fields");
			Class[] args_class = { Class.forName("java.lang.String"),
					Class.forName("java.lang.String") };
			isValidMethod = game.getMethod("isValid", args_class);
			getModeSQLMethod = game.getMethod("getModeSQL", args_class);
			fields = (String[]) field.get(theObject);
		} catch (ClassNotFoundException classNotFoundException) {
			System.out
					.println("Error : " + classNotFoundException.getMessage());
		} catch (InstantiationException instantiationException) {
			instantiationException.printStackTrace();
		} catch (IllegalAccessException illegalAccessException) {
			illegalAccessException.printStackTrace();
		} catch (SecurityException securityException) {
			securityException.printStackTrace();
		} catch (NoSuchFieldException noSuchFieldException) {
			noSuchFieldException.printStackTrace();
		} catch (NoSuchMethodException exception) {
			exception.printStackTrace();
		}
	}

	/** Template method that calls {@link #processLine(String)}. */

	public final String processLineByLine(String mail) {
		// LOGGER.debug("Processes the mail line by line.");
		StringBuffer resultString = new StringBuffer();
		try {
			// first use a Scanner to get each line
			Scanner scanner = new Scanner(mail);

			while (scanner.hasNextLine()) {
				processLine(scanner.nextLine());
			}
			System.out.println("--- I am here ---");
			int index = 0;
			for (int col = 0; col < fields.length - 1; col++) {
				resultString.append("  " + fields[col] + "\t Player 1: "
						+ players[index][col] + "\t Player2: "
						+ players[index + 1][col] + "\n");
			}
			scanner.close();
		} catch (Exception ex) {
			// LOGGER.debug("Problem in scanning line.");
			System.out.println(ex.getMessage());
		}
		return resultString.toString();
	}

	protected void processLine(String aLine) {
		// LOGGER.debug("Processes the line passed from scanner.");
		// use a second Scanner to parse the content of each line
		aLine = (String) removeDuplicateWhitespace(aLine);
		System.out.println("Input from E-mail: " + aLine);
		// aLine.trim()
		StringBuffer tempField = new StringBuffer();
		String[] words = aLine.split(" ");
		int len = words.length;
		if (len > 2) {
			for (int index = len; index > 2; index--) {
				tempField.append(words[len - index] + " ");
			}
			System.out.println(fields[playerIndex] + " : Jigar : "
					+ tempField.toString() + " = "
					+ (tempField.toString()).equals(fields[playerIndex]));
			String[] playerValue = { words[len - 2], words[len - 1], "" };
			// if ((tempField.toString()).equals(fields[playerIndex])) {
			// addPlayersValue(playerValue);
			// }
			if ((tempField.toString()).startsWith(fields[playerIndex])) {
				playerIndex++;
			}
		}
	}

	public void addPlayersValue(String[] playerValue) {
		// LOGGER.debug("Assigns values dynamically to players.");

		for (int index = 0; index < noOfPlayers; index++) {
			players[index][playerIndex] = playerValue[index];
		}
		playerIndex++;
	}

	public CharSequence removeDuplicateWhitespace(CharSequence inputStr) {
		String patternStr = "\\s+";
		String replaceStr = " ";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.replaceAll(replaceStr);
	}

	public boolean isMailRanked(String body) {
		Object[] args_value = { body };
		try {
			return Boolean.parseBoolean(isValidMethod.invoke(this, args_value)
					.toString());
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (InvocationTargetException exception) {
			exception.printStackTrace();
		}

		return false;
	}

	public boolean isDBRanked(int valueId) {
		Object[] args_value = { valueId };
		try {
			return Boolean.parseBoolean(isValidMethod.invoke(this, args_value)
					.toString());
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (InvocationTargetException exception) {
			exception.printStackTrace();
		}

		return false;
	}

	public String getRankedSQL(int tournamentId) {
		Object[] args_value = { tournamentId };
		try {
			return getModeSQLMethod.invoke(this, args_value).toString();
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (InvocationTargetException exception) {
			exception.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		try {
			MailParser mailParser = new MailParser("com.g4g.game.FightNight3");
			// System.out.print(MailParser.processLineByLine("Offensive
			// yards :
			// 0 5"));
			StringBuffer str = new StringBuffer();
			BufferedReader reader = new BufferedReader(
					new FileReader(
							"C:\\Tools\\workspace-G4GOrigin\\GameValidation\\Mails\\fightnight3.txt"));

			String inputLine;

			while ((inputLine = reader.readLine()) != null) {
				str.append(inputLine).append("\n");
			}
			System.out.println(mailParser.isMailRanked(str.toString()));
			System.out.println(mailParser.processLineByLine(str.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
