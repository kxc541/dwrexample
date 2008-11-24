/**********************************************************
 * ProfileAction.java :
 *
 * Created by Pratik
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.PicturesServiceDelegator;
import com.g4g.delegator.PlayerCommentsServiceDelegator;
import com.g4g.delegator.PlayerImServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.ImnetworkDTO;
import com.g4g.dto.PicturesDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayNowOpenMatches;
import com.g4g.dto.PlayNowOpenTournaments;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayercommentsDTO;
import com.g4g.dto.PlayerimDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.ProfileForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;

/**
 * The ProfileAction Class displays all the tabs after login.The information on
 * MySchedule tab, Recent Games, Cashier, Friends, Games, Aliases the relevant
 * information is displayed.If the user deletes comment it is redirected to
 * profile page. User posts comment the submit will display the comments to user
 * profile page.
 *
 * @struts.action path="/displayProfile" name="profileForm" scope="request"
 * @struts.action-forward name="success" path="/WebContent/profile.jsp"
 * @author Pratik
 */

public class ProfileAction extends BaseAction {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * The method display will display the information on MySchedule tab, Recent
	 * Games, Cashier, Friends, Games, Aliases the relevant information is
	 * displayed.Deletes the comment of user, Loads the new picture.
	 *
	 * @throws Exception
	 *
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append(G4GConstants.CALLINGMETHOD) .append( DataUtil.getCallingMethod()) .append( G4GConstants.DISPLAY_STARTS ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString(),Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(
							request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.DISPLAY_STARTS).toString());


		/* code for remove image starts */
		if (request.getParameter(G4GConstants.DELETE) != null) {
			if (request.getParameter(G4GConstants.OBJECT) != null) {
				if (G4GConstants.PICTURE.equals( request.getParameter(G4GConstants.OBJECT) )) {
					request.getSession().setAttribute(G4GConstants.LOAD_PICTURE, true);
					String strPicId = request.getParameter(G4GConstants.ID);
					SearchCriteria delSearchCriteria = new SearchCriteria();
					delSearchCriteria.setAttribute(G4GConstants.ID, Integer.parseInt(strPicId));
					List<PicturesDTO> delList = PicturesServiceDelegator.getList(delSearchCriteria);
					if (delList.size() > G4GConstants.ZERO) {
						AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(	this.getClass().getName() ).append(
										G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
										G4GConstants.COLON_WITH_SPACES ).append(
										G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
										).append(G4GConstants.DASHES ).append( request.getSession().getId()).append(  G4GConstants.DELETING_PICTURE).toString() ,Level.INFO);
						PicturesDTO delPicturesDTO = delList.get(G4GConstants.ZERO);
						PicturesServiceDelegator.delete(delPicturesDTO);
					}
				}// Deletes the comment
				else if (G4GConstants.COMMENT.equals( request.getParameter(G4GConstants.OBJECT) )) {
					String strCommentId = request.getParameter(G4GConstants.ID);
					SearchCriteria delSearchCriteria = new SearchCriteria();
					delSearchCriteria.setAttribute(G4GConstants.ID, Integer
.parseInt(strCommentId));
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
									new StringBuffer(this.getClass().getName() ).append(
									G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
									G4GConstants.COLON_WITH_SPACES ).append(
									G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
									).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTING_COMMENTS ).toString(),Level.INFO);
					List<PlayercommentsDTO> delList = PlayerCommentsServiceDelegator
.getList(delSearchCriteria);
					if (delList.size() > G4GConstants.ZERO) {
						PlayercommentsDTO delPlayercommentsDTO = delList.get(G4GConstants.ZERO);
						AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
										new StringBuffer(this.getClass().getName() ).append(
										G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
										G4GConstants.COLON_WITH_SPACES ).append(
										G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
										).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.DELETING_COMMENTS ).append( delPlayercommentsDTO.getComment()).toString(),Level.INFO);
						PlayerCommentsServiceDelegator
.delete(delPlayercommentsDTO);
					}
				}
			}
		}

		/* code for remove image starts */
		UserDTO userDTO = new UserDTO();

		PlayerDTO playerDTO = null;

		int userId = G4GConstants.ONE_NEGATIVE;
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.WHOSEEUSERPROFILE ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString()
						, Level.INFO);
		if (request.getParameter(G4GConstants.PROFILEUSERID) != null) {
			try {
				userId = Integer.parseInt(request.getParameter(G4GConstants.PROFILEUSERID));
				if(PlayerServiceDelegator.getPlayer(userId) == null)
					throw new NullPointerException();
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						e, Level.ERROR);
				userId = DataUtil.getUserIdInteger(request);
				request.setAttribute(G4GConstants.ERROR, resource.getString(G4GConstants.ERROR_INVALID_PROFILE));
			}

			request.getSession().setAttribute(G4GConstants.PROFILEUSERID,
userId);
		} /*
		 * else if (request.getSession()
		 * .getAttribute(G4GConstants.PROFILEUSERID) != null) { userId =
		 * Integer.parseInt(request.getSession().getAttribute(
		 * G4GConstants.PROFILEUSERID).toString()); }
		 */
		else if (request.getSession().getAttribute(G4GConstants.USERDTO) != null) {
			userId = DataUtil.getUserDTO(request).getPlayerDTO().getId();
			request.getSession().setAttribute(G4GConstants.PROFILEUSERID,
userId);
		}

		if (userId == G4GConstants.ONE_NEGATIVE) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName() ).append(
							G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
							G4GConstants.COLON_WITH_SPACES ).append(
							G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
							).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.USER_NOT_RECOGNIZE).toString() ,Level.INFO);
			return mapping.findForward(G4GConstants.LOGOUT);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.CURRENTUSER_WHOSEEPROFILE).toString() ,Level.INFO);

		PlayerDTO currPlayerDTO = PlayerServiceDelegator.getPlayer(userId);

		request.setAttribute(G4GConstants.PLAYERDTO, currPlayerDTO);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTINGPLAYER_GEAMELIST).toString() ,Level.INFO);

		List playerGameList = PlayerServiceDelegator.getPlayerGame(userId);

		request.setAttribute(G4GConstants.PLAYERGAMELIST, playerGameList);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append( G4GConstants.COLON_WITH_SPACES ).append(
				G4GConstants.PGLIST_SIZE ).append( playerGameList.size()
		).append(G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString());

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GET_PLAYERFRIENDS_LIST).toString() ,Level.INFO);
		List playerFriendsList = PlayerServiceDelegator
.getPlayerFriends(userId);

		request.setAttribute(G4GConstants.PLAYERFRIENDSLIST, playerFriendsList);

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
				G4GConstants.PFLIST_SIZE ).append( playerFriendsList.size()
		).append(G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString());

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTINGPLAYER_SCHEDULE ).toString(),Level.INFO);

List playerScheduleList = PlayerServiceDelegator
.getPlayerSchedule(userId);

		request.setAttribute(G4GConstants.PLAYERSCHEDULELIST,
playerScheduleList);

		if (playerScheduleList != null) {
request.setAttribute(G4GConstants.PENDING, playerScheduleList
.size());
		} else {
			request.setAttribute(G4GConstants.PENDING, G4GConstants.ZERO);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTINGPLAYER_VERIFYINGLIST ).toString(),Level.INFO);

	List playerVerifyingList = PlayerServiceDelegator
.getPlayerVerifying(userId);

		if (playerVerifyingList != null) {
			request.setAttribute(G4GConstants.VERIFYING, playerVerifyingList
.size());
		} else {
			request.setAttribute(G4GConstants.VERIFYING, G4GConstants.ZERO);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.VERIFYINGLIST_SIZE ).append( request.getAttribute(G4GConstants.VERIFYING)).toString(),Level.INFO);

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(	this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
				G4GConstants.PSLIST_SIZE ).append( playerScheduleList.size()).append(
				G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
				).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString());

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTING_RECENTGAMELIST ).toString(),Level.INFO);
		List playerRecentGamesList = PlayerServiceDelegator.getPlayerRecentGames(userId);
		request.setAttribute(G4GConstants.PLAYERRECENTGAMESLIST,
				playerRecentGamesList);

		SearchCriteria playerCommentSearchCriteria = new SearchCriteria();

		playerCommentSearchCriteria.setAttribute(G4GConstants.TARGETPLAYER,
				PlayerServiceDelegator.getPlayer(userId));
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTING_PLAYERCOMMENTS ).toString(),Level.INFO);

		List playerCommentsList = PlayerCommentsServiceDelegator
				.getList(playerCommentSearchCriteria);
		request.setAttribute(G4GConstants.PLAYERCOMMENTSLIST,
				playerCommentsList);

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
				G4GConstants.PLAYER_COMMENT_SIZE ).append( playerCommentsList.size()).append(
				G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
				).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString());

		request.setAttribute(G4GConstants.PICUSERID, userId);

		SearchCriteria searchCriteria = new SearchCriteria();
		if (playerDTO != null) {
			searchCriteria.setAttribute(G4GConstants.PLAYER, playerDTO);
		} else {
			searchCriteria.setAttribute(G4GConstants.PLAYER, currPlayerDTO);
		}

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTING_PLAYERNETWORK_LIST).toString() ,Level.INFO);


		List<PlayernetworkDTO> networkPlayernetworkDTO = PlayerNetworkServiceDelegator
.getList(searchCriteria);

		if (networkPlayernetworkDTO != null
&& networkPlayernetworkDTO.size() > G4GConstants.ZERO) {

            for (PlayernetworkDTO aNetworkPlayernetworkDTO : networkPlayernetworkDTO) {
                 if (aNetworkPlayernetworkDTO.getNetwork() != null && userDTO != null) {
                    if (aNetworkPlayernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_XBOX_LIST_KEY) {
                        userDTO.setPlayernetworkDTOxbox(aNetworkPlayernetworkDTO);
                    }
                    if (aNetworkPlayernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_XBOX360_LIST_KEY) {
                        AuditUtil.getInstance().writeLog(
                                AuditUtil.FILE_TYPE_G4G,
                                new StringBuffer( this.getClass().getName() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append(
                                        G4GConstants.SETTING_XBOX360 ).append(
                                        G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
                                        ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString());
                        userDTO.setPlayernetworkDTOxbox360(aNetworkPlayernetworkDTO);
                    }
                    if (aNetworkPlayernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_PS2_ID_LIST_KEY) {
                        AuditUtil.getInstance().writeLog(
                                AuditUtil.FILE_TYPE_G4G,
                                new StringBuffer(this.getClass().getName() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append(
                                        G4GConstants.PS2
                                        ).append( G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
                                        ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString());
                        userDTO.setPlayernetworkDTO3ps2(aNetworkPlayernetworkDTO);
                    }
                    if (aNetworkPlayernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_PS3_ID_LIST_KEY) {
                        AuditUtil.getInstance().writeLog(
                                AuditUtil.FILE_TYPE_G4G,
                                new StringBuffer( this.getClass().getName() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod() ).append(
                                        G4GConstants.COLON_WITH_SPACES ).append(
                                        G4GConstants.PS3 ).append(
                                        G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
                                        ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString());
                        userDTO.setPlayernetworkDTO4ps3(aNetworkPlayernetworkDTO);
                    }
                }
            }
		}

		// Code to get aliases from playeim table - starts
		SearchCriteria aimSearchCriteria = new SearchCriteria();

		aimSearchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(
				userId));
		aimSearchCriteria.setAttribute(G4GConstants.IMNETWORK,
				new ImnetworkDTO(G4GConstants.AIM_ID));
		List<PlayerimDTO> aimList = PlayerImServiceDelegator
				.getList(aimSearchCriteria);

		if (aimList != null && aimList.size() > G4GConstants.ZERO) {
			request.setAttribute(G4GConstants.AIM_ALIAS, aimList.get(G4GConstants.ZERO)
					.getPlayerimtag());
		} else {
			request.setAttribute(G4GConstants.AIM_ALIAS, G4GConstants.NONE);
		}

		SearchCriteria yahooSearchCriteria = new SearchCriteria();

		yahooSearchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(
				userId));
		yahooSearchCriteria.setAttribute(G4GConstants.IMNETWORK,
				new ImnetworkDTO(G4GConstants.YAHOO_ID));
		List<PlayerimDTO> yahooList = PlayerImServiceDelegator
				.getList(yahooSearchCriteria);

		if (yahooList != null && yahooList.size() > G4GConstants.ZERO) {
			request.setAttribute(G4GConstants.YAHOO_ALIAS, yahooList.get(G4GConstants.ZERO)
					.getPlayerimtag());
		} else {
			request.setAttribute(G4GConstants.YAHOO_ALIAS, G4GConstants.NONE);
		}

		SearchCriteria msnSearchCriteria = new SearchCriteria();

		msnSearchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(
				userId));
		msnSearchCriteria.setAttribute(G4GConstants.IMNETWORK,
				new ImnetworkDTO(G4GConstants.MSN_ID));

		List<PlayerimDTO> msnList = PlayerImServiceDelegator
				.getList(msnSearchCriteria);
		if (msnList != null && msnList.size() > G4GConstants.ZERO) {
			request.setAttribute(G4GConstants.MSN_ALIAS, msnList.get(G4GConstants.ZERO)
					.getPlayerimtag());
		} else {
			request.setAttribute(G4GConstants.MSN_ALIAS, G4GConstants.NONE);
		}

		SearchCriteria skypeSearchCriteria = new SearchCriteria();

		skypeSearchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(
				userId));
		skypeSearchCriteria.setAttribute(G4GConstants.IMNETWORK,
				new ImnetworkDTO(G4GConstants.SKYPE_ID));
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(	this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append(
						G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
						).append(G4GConstants.DASHES ).append( request.getSession().getId()).append( G4GConstants.GETTING_PLAYERIM_LIST).toString() ,Level.INFO);

		List<PlayerimDTO> skypeList = PlayerImServiceDelegator
				.getList(skypeSearchCriteria);
		if (skypeList != null && skypeList.size() > G4GConstants.ZERO) {
			request.setAttribute(G4GConstants.SKYPE_ALIAS, skypeList.get(G4GConstants.ZERO)
					.getPlayerimtag());
		} else {
			request.setAttribute(G4GConstants.SKYPE_ALIAS, G4GConstants.NONE);
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append( G4GConstants.AFTERSETTINGPARAMETERS ).append(
				G4GConstants.FORPLAYER ).append( DataUtil.getUserDTO(request).getPlayerDTO().getEmailaddress()
				).append(G4GConstants.DASHES ).append( request.getSession().getId()).toString(), Level.INFO);
		int playerId = Integer.parseInt(DataUtil.getUserId(request));
		List<PlayNowDTO> playNowList = new ArrayList<PlayNowDTO>();
		List<PlayNowOpenMatches> openMatchesList = new ArrayList<PlayNowOpenMatches>();
		if(WidgetsServiceDelegator.getPlayNowList(playerId).size() > 0){
			playNowList = WidgetsServiceDelegator.getPlayNowList(playerId);
			int totalOpenMatches = playNowList.size();
			for(int index=0;index < totalOpenMatches; index++){
				PlayNowDTO playNowDTO = playNowList.get(index);
				PlayNowOpenMatches playNowOpenMatches = new PlayNowOpenMatches();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(playNowDTO
						.getScheduleDate(), userDTO.getOffset()),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				TwoplayermatchDTO twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getDTOFromId(playNowDTO.getMatchId());
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(twoplayermatchDTO.getTwoplayertournament().getId());
				String encryptedId = DataUtil.encrypt(String.valueOf(playNowDTO.getGameId()));

				playNowOpenMatches.setEncryptedId(encryptedId);
				playNowOpenMatches.setGameOptionsList(gameOptionsList);
				playNowOpenMatches.setPlayNowDTO(playNowDTO);
				playNowOpenMatches.setScheduledDate(scheduledDate);
				playNowOpenMatches.setTwoplayermatchDTO(twoplayermatchDTO);

				openMatchesList.add(playNowOpenMatches);
			}
			request.setAttribute(G4GConstants.PLAYNOWLIST, openMatchesList);
		}
		List<TournamentGameDTO> openTournamentList = new ArrayList<TournamentGameDTO>();
		List<PlayNowOpenTournaments> tournamentsList = new ArrayList<PlayNowOpenTournaments>();
		if(TwoPlayerTournamentServiceDelegator.getTournamentActions().size() > 0) {
			openTournamentList = TwoPlayerTournamentServiceDelegator.getTournamentActions();
			int totalOpenTournaments = openTournamentList.size();
			for(int index = 0; index< totalOpenTournaments; index++){
				TournamentGameDTO tournamentGameDTO = openTournamentList.get(index);
				PlayNowOpenTournaments playNowOpenTournaments = new PlayNowOpenTournaments();
				String scheduledDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(tournamentGameDTO
						.getScheduledstartdate(), userDTO.getOffset()),
						G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A);
				List<GameoptionsDTO> gameOptionsList = TwoPlayerTournamentServiceDelegator.getGameOptionsOfTournament(tournamentGameDTO.getTwoplayertournamentDTO().getId());
				String encryptedId = DataUtil.encrypt(String.valueOf(tournamentGameDTO.getGameDTO().getId()));

				playNowOpenTournaments.setEncryptedId(encryptedId);
				playNowOpenTournaments.setTournamentGameDTO(tournamentGameDTO);
				playNowOpenTournaments.setScheduledDate(scheduledDate);
				playNowOpenTournaments.setGameOptionsList(gameOptionsList);

				tournamentsList.add(playNowOpenTournaments);
			}
			request.setAttribute(G4GConstants.OPENTOURNAMENTLIST, tournamentsList);
		}
		request.setAttribute(G4GConstants.USERDTO, userDTO);

		if(request.getParameter(G4GConstants.PROFILEUSERID) != null)
			return mapping.findForward(G4GConstants.REVIEW);

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * @throws Exception
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append( G4GConstants.SUBMIT_STARTS).toString());
		if (request.getSession().getAttribute(G4GConstants.USERDTO) == null) {
			return mapping.findForward(G4GConstants.HOMEPAGE);
		}
		ProfileForm profileForm = (ProfileForm) form;

		UserDTO userDTO  ;

		PlayerDTO playerDTO  ;
		int userId = G4GConstants.ONE_NUMBER;
		if (request.getParameter(G4GConstants.PROFILEUSERID) != null) {
			userId = Integer.parseInt(request
					.getParameter(G4GConstants.PROFILEUSERID));
			request.getSession().setAttribute(G4GConstants.PROFILEUSERID,
					userId);
		} else if (request.getSession()
				.getAttribute(G4GConstants.PROFILEUSERID) != null) {
			userId = Integer.parseInt(request.getSession().getAttribute(
					G4GConstants.PROFILEUSERID).toString());
		} else if (request.getSession().getAttribute(G4GConstants.USERDTO) != null) {
			userDTO = (UserDTO) request.getSession().getAttribute(
					G4GConstants.USERDTO);
			playerDTO = userDTO.getPlayerDTO();
			userId = playerDTO.getId();
			request.getSession().setAttribute(G4GConstants.PROFILEUSERID,
					userId);
		}

		if (G4GConstants.POSTACOMMENT.equalsIgnoreCase(operation)) {
			if (profileForm.getComment().length() > G4GConstants.ZERO) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName() ).append(
								G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
								G4GConstants.COLON_WITH_SPACES ).append( G4GConstants.POSTACOMMENT).toString() ,Level.INFO);
				PlayercommentsDTO playercommentsDTO = new PlayercommentsDTO();
				playercommentsDTO.setPlayer(DataUtil.getUserDTO(request)
						.getPlayerDTO());
				playercommentsDTO.setTargetplayer(PlayerServiceDelegator
						.getPlayer(userId));
				playercommentsDTO.setComment(profileForm.getComment());
				playercommentsDTO.setCreationdate(DataUtil.todayGMT());
				PlayerCommentsServiceDelegator.add(playercommentsDTO);
			}
			profileForm.setComment(G4GConstants.NONE);
		}

		if (request.getParameter(G4GConstants.PROFILEUSERID) != null) {
			ActionForward actionForward = mapping
					.findForward(G4GConstants.SUCCESS);
			ActionForward newActionForward = new ActionForward(actionForward);
			newActionForward.setPath(new StringBuffer(G4GConstants.DISPLAYPROFILE
					).append( G4GConstants.PROFILEUSERID ).append( G4GConstants.EQUALS
					).append( request.getParameter(G4GConstants.PROFILEUSERID)).toString());
			return newActionForward;
		}
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName() ).append(
						G4GConstants.COLON_WITH_SPACES ).append( DataUtil.getCurrentMethod()).append(
						G4GConstants.COLON_WITH_SPACES ).append( G4GConstants.AFTERCHANGIN).append(G4GConstants.PLAYERS).append(G4GConstants.PROFILE).toString(), Level.INFO);


		return mapping.findForward(G4GConstants.SUCCESS);
	}
}
