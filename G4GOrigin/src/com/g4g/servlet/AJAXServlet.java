/***********************************************************************************************************************
 * AJAXServlet.java : Created by Jigar Mistry Last modified Date: 18 Apr .08 by Punam Revision: 0.1 Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************************************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.*;
import gnu.trove.THashMap;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.PastTournamentDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.SubNationalCodeDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.dto.UserDTO;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.ImageUtils;
import com.g4g.utils.MailHelper;
import com.g4g.utils.SQLConstants;
import com.g4g.utils.SessionUtil;
import com.g4g.utils.TournamentsUtil;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The AJAXServlet class contains the path retrieved by ajax call and creates xml file to contain data and returns to
 * ajax method url is called from. Or calls the method accordingly to perform the operation. <br>
 * AJAXServlet to implement the AJAX functionality for following operations. <br>
 * 1. To retrieve Sub Nation Code on the basis of Nation Code. <br>
 * 2. To perform Message center operations. <br>
 * 3. To perform GameLobby realated ajax implementation. <br>
 * 4. To retrieve data for all the widgets. <br>
 * 5. To get the online/offline status of user. <br>
 * 6. To get the ajax implementation For gameDetails page. <br>
 * s 7. To Fill xml file for required attributes.
 *
 * @author Jigar Mistry
 */
public class AJAXServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = ONE_LONG;
	private static THashMap<String, List<GameoptionsDTO>> map = null;
	/**
	 * The doGet method is Called by the server (via the service method) to allow a servlet to handle a GET request.
	 * Overriding this method to support a GET request also automatically supports an HTTP HEAD request. A HEAD request
	 * is a GET request that returns no body in the response, only the request header fields. When overriding this
	 * method, read the request data, write the response headers, get the response's writer or output stream object, and
	 * finally, write the response data. It's best to include content type and encoding. When using a PrintWriter object
	 * to return the response, set the content type before accessing the PrintWriter object. The servlet container must
	 * write the headers before committing the response, because in HTTP the headers must be sent before the response
	 * body.
	 *
	 * @param request
	 *            object that contains the request the client has made of the servlet
	 * @param response
	 *            object that contains the response the servlet sends to the client
	 * @throws ServletException
	 *             the servlet exception if the request for the GET could not be handled
	 * @throws IOException
	 *             if an input or output error is detected when the servlet handles the GET request
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The method doPost called by the server (via the service method) to allow a servlet to handle a POST request. The
	 * HTTP POST method allows the client to send data of unlimited length to the Web server a single time and is useful
	 * when posting information such as credit card numbers. When overriding this method, read the request data, write
	 * the response headers, get the response's writer or output stream object, and finally, write the response data.
	 * It's best to include content type and encoding. When using a PrintWriter object to return the response, set the
	 * content type before accessing the PrintWriter object. The servlet container must write the headers before
	 * committing the response, because in HTTP the headers must be sent before the response body.<br>
	 * <br>
	 * It gets all the calls made by ajax, processes the request of ajax call.
	 *
	 * @param request
	 *            object that contains the request the client has made of the servlet
	 * @param response
	 *            object that contains the response the servlet sends to the client
	 * @throws ServletException
	 *             if the request for the POST could not be handled.
	 * @throws IOException
	 *             if an input or output error is detected when the servlet handles the request.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).append(DASHES)
										.append(SESSIONID).append(request.getSession().getId()).toString(), Level.INFO);

		PrintWriter out = response.getWriter();

		String path = request.getPathInfo();

		path = path.substring(ONE_NUMBER, path.length());

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).append(DASHES)
										.append(PATH_EQUALS).append(path).toString(), Level.INFO);

		/**
		 * Used to populate challenge card game options returns the xml to callback function
		 * Constant path Path_getNextGameOptionsXML
		 */
		if (DataUtil.getUserDTO(request) != null && G4GConstants.PATH_GETNEXTGAMEOPTIONSXML.equals(path)) {
			response.setContentType(TEXT_XML);
			String[] valuesequenceid = request.getParameterValues(VALUE_SEQUENCE_ID);
			Integer[] valuesequenceidinteger = new Integer[valuesequenceid.length];
			for (int i = 0; i < valuesequenceid.length; i++) {
				valuesequenceidinteger[i] = DataUtil.getInteger(valuesequenceid[i]);
			}
			int gameid = DataUtil.getInteger(request.getParameter(GAMEID_DB));
			int optionsequenceid = DataUtil.getInteger(request.getParameter(OPTIONSEQUENCEID));
			if (gameid > 0 && optionsequenceid > 0) {
				if(map==null) {
				 map = (THashMap<String, List<GameoptionsDTO>>)request.getSession().getServletContext().getAttribute(G4GOriginSession.GAME_OPTION_RULE_ENGINE);
				}
				out.print(ChallengeCardGameOptionsServlet.getNextGameOptionsXML(map, valuesequenceidinteger, gameid,
								optionsequenceid));
			} else {
				out.print(G4GConstants.BLANK);
			}

		}else if (FILEUPLOAD.equals(path)) {
			DiskFileUpload upload = new DiskFileUpload();
			File file = null;
			List items = null;
			try {
				items = upload.parseRequest(request);
			}
			catch (FileUploadException exception) {
				AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD)
												.append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
																DataUtil.getCurrentMethod()).append(DASHES).append(
																SESSIONID).append(request.getSession().getId()).append(
																DASHES).append(exception).toString(), Level.ERROR);
			}
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				out.println(item.getFieldName());
				if (!item.isFormField()) {
					String Screename = DataUtil.getUserDTO(request).getPlayerDTO().getScreenname();
					file = new File(Screename + DOTPNG);
					request.getSession().setAttribute(UPLOADEDFILE, file.getAbsolutePath());
					try {
						item.write(file);
					}
					catch (Exception exception) {
						AuditUtil.getInstance().writeLog(
										AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
														.append(CALLINGMETHOD).append(DataUtil.getCallingMethod())
														.append(CURRENTMETHOD).append(DataUtil.getCurrentMethod())
														.append(DASHES).append(SESSIONID).append(
																		request.getSession().getId()).append(DASHES)
														.append(exception).toString(), Level.ERROR);
					}
				}
			}
			if (!ImageUtils.isImage(ImageUtils.getBytesFromFile(file))) {
				AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD)
												.append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
																DataUtil.getCurrentMethod()).append(DASHES).append(
																FILE_NOT_IMAGE).toString(), Level.INFO);
				request.getSession().removeAttribute(UPLOADEDFILE);
				file.delete();
				response.setContentType(TEXT_XML);
				out.print(AJAX_XML_VERSION);
				out.print(AJAX_XML_MSG);
			}
		}


		else if (COMPOSEMESSAGE.equals(path)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();
			MailCenterAjaxImpl.sendMessage(playerId, request);
		}
		else if (UPDATEMESSAGE.equals(path)) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, TO_UPDATE_MESSAGE_INFO);
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null && DataUtil.getUserDTO(request).getPlayerDTO() != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			try {
				int playerId = userDTO.getPlayerDTO().getId();
				out.print(MailCenterAjaxImpl.updateMailCenter(playerId));
			}
			catch (NullPointerException userNotLogIn) {
				AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD)
												.append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
																DataUtil.getCurrentMethod()).append(DASHES).append(
																SESSIONID).append(request.getSession().getId()).append(
																DASHES).append(userNotLogIn).toString(), Level.ERROR);
			}
		}
		else if (SHOWMESSAGES.equals(path)) {
			AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
											DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod()).append(DASHES).append(TOSHOWMESSAGES)
											.toString(), Level.INFO);
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();

			String messageStatus = request.getParameter(MESSAGE_STATUS);
			String messageType = request.getParameter(MESSAGE_TYPE);
			String isSent = request.getParameter(IS_SENT);
			out.print(MailCenterAjaxImpl.showMessage(playerId, messageStatus, messageType, isSent, request));
		}
		else if (REPLYTOMESSAGE.equals(path)) {
			MailCenterAjaxImpl.replyToMessage(request.getParameter(ID), request.getParameter(REPLY_TEXT), request);
		}
		else if (CHANGE_MESSAGE_STATUS.equals(path)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
				int playerId = userDTO.getPlayerDTO().getId();

				MailCenterAjaxImpl.changeMessageStatus(playerId, request.getParameter(ID), request
								.getParameter(MESSAGE_STATUS), request);
			}
		}
		else if (SEND_FRIEND_REQUEST.equals(path)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();

			out.print(FriendsAjaxImpl.addFriendRequest(playerId, Integer.parseInt(DataUtil.decrypt(request
							.getParameter(ID))), request));
		}
		else if (REMOVE_FRIEND_FROM_CURRENT_USER.equals(path)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();

			FriendsAjaxImpl.removeFriendFromCurrentUser(playerId, Integer.parseInt(DataUtil.decrypt(request
							.getParameter(ID))));
		}
		else if (PLATFORMGAMES.equals(path)) {
			String inNetworkID = request.getParameter(PLATFORM_ID);
			String userId = BLANK;
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);

				PlayerDTO playerDTO = userDTO.getPlayerDTO();
				userId = String.valueOf(playerDTO.getId());
			}

			StringBuffer responseHTML = new StringBuffer();
			responseHTML.append(GameLobbyAjaxImpl.getPlatformSpecificGamesHTMLFormat(inNetworkID, userId, request));

			AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
											DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod()).append(DASHES).append(
											GET_PLATFORM_SPECIFIC_GAMES_RESPONSE).append(responseHTML.toString())
											.toString(), Level.DEBUG);
			out.print(responseHTML.toString());
		}
		else if (path.equals(USERGAMES)) {
			String inPlayerID = request.getParameter(USERID_TAG);

			StringBuffer responseHTML = new StringBuffer();
			responseHTML.append(GameLobbyAjaxImpl.getPlayerSpecificGamesHTMLFormat(inPlayerID, request));
			AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
											DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod()).append(DASHES).append(
											GET_PLATFORM_SPECIFIC_GAMES_RESPONSE).append(responseHTML.toString())
											.toString(), Level.DEBUG);
			out.print(responseHTML.toString());
		}
		else if (path.equals(ADD_GAME_TO_CURRENT_USER_TAG)) {
			String gameID = request.getParameter(ID);
			String playerID = DataUtil.getUserId(request);
			out.print(GameLobbyAjaxImpl.addGamesToPlayer(playerID, gameID, request));
		}
		else if (path.equals(REMOVE_GAME_FROM_CURRENT_USER)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();

			out.print(GameLobbyAjaxImpl.removeGameFromCurrentUser(playerId, request.getParameter(ID_PRE_TAG), request
							.getParameter(ID), request));
		}
		else if (path.equals(IS_NAME_AVAILABLE)) {
			AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
											DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod()).append(DASHES).append(GETTING_SCREENNAME)
											.append(request.getParameter(SCREEN_NAME)).toString(), Level.INFO);
			out.print(RegisterAjaxImpl.isNameAvailable(request.getParameter(SCREEN_NAME)));
		}
		else if (path.equals(IS_LOGIN_AVAILABLE)) {
			out.print(RegisterAjaxImpl.isLoginAvailable(request.getParameter(LOGIN_NAME)));
		}
		else if (path.equals(PLAY_NOW_REFRESH)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
				int playerId = userDTO.getPlayerDTO().getId();
				out.print(WidgetsAjaxImpl.playNowRefresh(playerId, request));
			}
		}
		else if (path.equals(GAME_DETAILED_DISPLAY)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			int playerId = userDTO.getPlayerDTO().getId();

			out.print(WidgetsAjaxImpl.gameDetailedDisplay(request));
		}
		else if (path.equals(ADD_USER_AS_FRIEND)) {
			String fromPlayer = URLDecoder.decode(request.getParameter(ID), UTF8);
			out.print(FriendsAjaxImpl.addUserAsFriend(Integer.parseInt(DataUtil.getUserId(request)), fromPlayer));
		}
		else if (path.equals(ONLINE_STATUS)) {
			out.print(AvatarAjaxImpl.getAvatarStatus(URLDecoder.decode(request.getParameter(ID), UTF8), request));
		}
		else if (path.equals(ONLINE_USERS_FOR_GAME) && DataUtil.getUserId(request) != null) {
			out.print(GameLobbyAjaxImpl.getOnlineUserForGame(Integer.parseInt(DataUtil.getUserId(request)), Integer
							.parseInt(request.getParameter(ID)), request));
		}
		else if (path.equals(GETSEARCHPEOPLERESULTS)) {
			response.setContentType(TEXT_XML);
			if (request.getParameter(PAGESTARTSAT) != null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundPeopleHTMLContent(searchString, request));
			}
			if (request.getParameter(PAGESTARTSAT) == null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundPeopleHTMLContent(searchString, request));
			}
		}
		else if (path.equals(GETSEARCHGAMERESULTS)) {
			response.setContentType(TEXT_XML);
			if (request.getParameter(PAGESTARTSAT) != null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundGameHTMLContent(searchString, request));
			}
			if (request.getParameter(PAGESTARTSAT) == null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundGameHTMLContent(searchString, request));
			}
		}
		else if (path.equals(GETSEARCHTOURNAMENTRESULTS)) {
			response.setContentType(TEXT_XML);
			if (request.getParameter(PAGESTARTSAT) != null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundTournamentHTMLContent(searchString, request));
			}
			if (request.getParameter(PAGESTARTSAT) == null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundTournamentHTMLContent(searchString, request));
			}
		}
		else if (path.equals(GETSEARCHCHALLENGERESULTS)) {
			response.setContentType(TEXT_XML);
			if (request.getParameter(PAGESTARTSAT) != null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundOpenChallengeHTMLContent(searchString, request));
			}
			if (request.getParameter(PAGESTARTSAT) == null && request.getParameter(SEARCHSTRING) != null) {
				String searchString = request.getParameter(SEARCHSTRING);
				out.print(WidgetsAjaxImpl.foundOpenChallengeHTMLContent(searchString, request));
			}
		}
		else if (path.equals(GETPASTTOURNAMENT)) {
			response.setContentType(TEXT_XML);
			out.println(getPastTournament(request));
		}
		else if (path.equals(SEND_MY_FEEDBACK)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			MailHelper mailHelper = new MailHelper();
			try {
				mailHelper.sendMail(userDTO.getPlayerDTO().getEmailaddress(), G4GProperties
								.getProperty(PropertiesConstants.FEEDBACK_EMAIL_SUPPORT), G4GProperties
								.getProperty(PropertiesConstants.FEEDBACK_EMAIL_SUBJECT), request
								.getParameter(FEEDBACK));
				out.print(TRUE);
			}
			catch (MessagingException e) {
				AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD)
												.append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
																DataUtil.getCurrentMethod()).append(DASHES).append(
																SESSIONID).append(request.getSession().getId()).append(
																DASHES).append(e).toString(), Level.ERROR);
				out.print(FALSE);
			}
			catch (Exception e) {
				AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD)
												.append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
																DataUtil.getCurrentMethod()).append(DASHES).append(
																SESSIONID).append(request.getSession().getId()).append(
																DASHES).append(e).toString(), Level.ERROR);
				out.print(FALSE);
			}
		}
		else if (path.equals(GAME_LOBBY_REFRESH)) {
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null) {
				userDTO = DataUtil.getUserDTO(request);
			}
			out.print(WidgetsAjaxImpl.gameDetailedDisplay(request));
		}
		else if (path.equals(BREAKING_NEWS_REFRESH)) {
			// out.print(WidgetsAjaxImpl.getBreakingNews(request));
			out.print(BLANK);
		}
		else if (path.equals(ALERT_FOR_USER)) {
			if (DataUtil.getUserDTO(request) != null) {
				out.print(UserAlertAjaxImpl.getAlert(Integer.parseInt(DataUtil.getUserId(request))));
			}
		}
		else if (GETGAMEOPTIONSXML.equals(path)) {
			response.setContentType(TEXT_XML);
			int gameid = DataUtil.getInteger(request.getParameter(GAMEID));
			if (gameid > ZERO) {
				out.print(TournamentCreationAjaxImpl.getGameOptionsXML(gameid));
			}
		}
		else if (path.equals(GET_SUBNATION_CODE)) {
			response.setContentType(TEXT_XML);
			int nationCodeId = ZERO;
			if (request.getParameter(NATIONCODEID) != null) {
				if (!request.getParameter(NATIONCODEID).trim().equals(NONE)) {
					nationCodeId = Integer.parseInt(request.getParameter(NATIONCODEID));
				}
			}

			out.print(getSubNationCode(nationCodeId));
		}
		else if (path.equals(OPEN_MATCHES) && DataUtil.getUserDTO(request) != null) {
			out.print(WidgetsAjaxImpl.getOpenMatches(request));
		}

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).append(DASHES)
										.append(SESSIONID).append(request.getSession().getId()).toString(), Level.INFO);
	}

	/**
	 * The method getSubNationCode is called when server request is made by the ajax call is made to fill up the
	 * subNation code. It will create an xml file that will hold the values for subnation and will be appended by the
	 * ajax method from serverside. It will give the string containing all the subnations value.
	 *
	 * @param nationCodeId
	 *            Nation code for which all the subnation request is made.
	 * @return String containing all the subnationvalues in xml file.
	 */
	@SuppressWarnings(UNCHECKED)
	private String getSubNationCode(int nationCodeId) {
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(),
						Level.INFO);

		StringBuffer strResponse = new StringBuffer(AJAX_XML_VERSION);
		strResponse.append(SMALLERTHAN).append(SUBNATIONS).append(GREATERTHAN);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(NATCODEID, nationCodeId);
		List<SubNationalCodeDTO> subNationList = SubNationalCodeServiceDelegator.getList(searchCriteria);

		int nationCount = subNationList.size();

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(SUBNATIONAL_LIST_SIZE)
										.append(subNationList.size()).toString(), Level.INFO);
		for (int index = ZERO; index < nationCount; index++) {
			SubNationalCodeDTO subNationalCodeDTO = subNationList.get(index);

			strResponse.append(SMALLERTHAN).append(SUBNATION).append(GREATERTHAN).append(SMALLERTHAN).append(ID)
							.append(GREATERTHAN).append(subNationalCodeDTO.getId()).append(CLOSE_SMALLERTHAN)
							.append(ID).append(GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(XML_NAME).append(GREATERTHAN).append(
							subNationalCodeDTO.getNationname().replaceAll(AMBERSAND, HTML_AMPERSAND))
							.append(CLOSE_SMALLERTHAN).append(XML_NAME).append(GREATERTHAN).append(CLOSE_SMALLERTHAN)
							.append(SUBNATION).append(GREATERTHAN);
		}
		strResponse.append(CLOSE_SMALLERTHAN).append(SUBNATIONS).append(GREATERTHAN);

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(),
						Level.INFO);
		return strResponse.toString();
	}

	/* Create xml file for Past Tournaments */
	/**
	 * The method getPastTournament is called when viewall request is made for pastTournaments in
	 * viewmultiTiertournament Page. It will contain all the past multitier tournaments in xmlfile. With details those
	 * are displayed in view multiTier Tournament page. It will give xml file with all past tournaments.
	 *
	 * @param request
	 *            the request object to get the values from session.
	 * @return xml file containing the past tournaments.
	 */
	private String getPastTournament(HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).append(DASHES)
										.append(SESSIONID).append(request.getSession().getId()).toString(), Level.INFO);

		List<PastTournamentDTO> pastTournamentList = TwoPlayerTournamentServiceDelegator.getPastTournaments();

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(
										PAST_TOURNAMENT_LIST_SIZE_CRITERIA).append(pastTournamentList.size())
										.toString(), Level.INFO);
		int totalPastTournaments = pastTournamentList.size();
		StringBuffer strResponse = new StringBuffer(AJAX_XML_VERSION);
		UserDTO userDTO = DataUtil.getUserDTO(request);
		strResponse.append(SMALLERTHAN).append(XML_PASTTOURNAMENTS).append(GREATERTHAN);
		for (int index = ZERO; index < totalPastTournaments; index++) {
			PastTournamentDTO pastTournamentDTO = pastTournamentList.get(index);
			GameDTO gameDTO = pastTournamentDTO.getGameDTO();
			NetworkDTO networkDTO = pastTournamentDTO.getNetworkDTO();
			List<PlayerDTO> playersList = pastTournamentDTO.getPlayersList();
			PlayerDTO winnerDTO = pastTournamentDTO.getWinnerDTO();
			TwoplayertournamentDTO twoplayertournamentDTO = pastTournamentDTO.getTwoplayertournamentDTO();
			strResponse.append(SMALLERTHAN).append(XML_PASTTOURNAMENT).append(GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(NETWORKNAME).append(GREATERTHAN).append(networkDTO.getNetworkname())
							.append(CLOSE_SMALLERTHAN).append(NETWORKNAME).append(GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(GAMENAME).append(GREATERTHAN).append(gameDTO.getGamename()).append(
							CLOSE_SMALLERTHAN).append(GAMENAME).append(GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(TOURNAMENT_ID).append(GREATERTHAN).append(
							twoplayertournamentDTO.getId()).append(CLOSE_SMALLERTHAN).append(TOURNAMENT_ID).append(
							GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(COMPLETIONDATE).append(GREATERTHAN).append(
							DataUtil.getDate(DateUtil.getDateOfTimeZone(pastTournamentDTO.getCompletiondate(), userDTO
											.getOffset()), DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A)).append(CLOSE_SMALLERTHAN)
							.append(COMPLETIONDATE).append(GREATERTHAN);
			Iterator<PlayerDTO> iterator = playersList.iterator();
			strResponse.append(SMALLERTHAN).append(XML_PLAYER).append(GREATERTHAN);
			for (int indexplayer = ZERO; iterator.hasNext(); indexplayer++) {
				PlayerDTO playerDTO = iterator.next();
				strResponse.append(SMALLERTHAN).append(playerDTO.getScreenname()).append(GREATERTHAN).append(
								playerDTO.getScreenname()).append(CLOSE_SMALLERTHAN).append(playerDTO.getScreenname())
								.append(GREATERTHAN);
			}
			strResponse.append(CLOSE_SMALLERTHAN).append(XML_PLAYER).append(GREATERTHAN);
			strResponse.append(SMALLERTHAN).append(XML_WINNERNAME).append(GREATERTHAN)
							.append(winnerDTO.getScreenname()).append(SMALLERTHAN).append(XML_WINNERNAME).append(
											GREATERTHAN);
			strResponse.append(CLOSE_SMALLERTHAN).append(XML_PASTTOURNAMENT).append(GREATERTHAN);
		}
		strResponse.append(CLOSE_SMALLERTHAN).append(XML_PASTTOURNAMENTS).append(GREATERTHAN);

		AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).append(DASHES)
										.append(SESSIONID).append(request.getSession().getId()).toString(), Level.INFO);
		return strResponse.toString();
	}

}
