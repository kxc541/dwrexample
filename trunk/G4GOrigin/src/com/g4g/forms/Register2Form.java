/**********************************************************

 * Register2Form.java :

 *

 * Created by Ankur

 * Last modified Date: 18 Apr .08 by Punam

 * Revision: 0.1

 * Version : 0.0.8

 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.

 **********************************************************/

package com.g4g.forms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.SkinServiceDelegator;
import com.g4g.delegator.TimeZoneServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.ImnetworkDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayerimDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;

/**
 *
 * The Class Register2Form contains properties for registration2 page. It is
 *
 * used by Register2Action and registration 2 page.It contains attribute like
 *
 * xbox, screenname, ..etc.
 *
 *
 *
 * @struts.form name="register2Page"
 *
 *
 *
 * @author Ankur
 *
 */

public class Register2Form extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7498903129223563998L;

	/** The xbox. */

	private String xbox;

	private FormFile picture;

	/** The screen name. */

	private String screenName;

	/** The xbox360. */

	private String xbox360;

	/** The ps2. */

	private String ps2;

	/** The ps3. */

	private String ps3;

	/** The avatar image id. */

	private String avatarImageId;

	/** The pictureId. */

	private String pictureId;

	/** The your messageTemp. */

	private String yourMessageTemp;

	/** The your storyTemp. */

	private String yourStoryTemp;

	/** The availabilityTemp. */

	private String availabilityTemp;

	/** The your message. */

	private String yourMessage;

	/** The your story. */

	private String yourStory;

	/** The availability. */

	private String availability;

	/** The playfor$. */

	private String playfor$;

	/** The skype. */

	private String skype;

	/** The aim. */

	private String aim;

	/** The msn. */

	private String msn;

	/** The yahoo. */

	private String yahoo;

	/** The pref contact info. */

	private String prefContactInfo = G4GConstants.NONE;

	/** The contact me abt world gaming events. */

	private String contactMeAbtWorldGamingEvents = G4GConstants.NONE;

	/** The email my system alerts. */

	private String emailMySystemAlerts = G4GConstants.NONE;

	/** The show my win losspublically. */

	private String showMyWinLosspublically = G4GConstants.NONE;

	/** The xbox live tag. */

	private String xboxLiveTag;

	/** The ps net tag. */

	private String psNetTag;

	/** The pref time zone. */

	private String prefTimeZone = G4GConstants.NONE;

	/** The old password. */

	private String oldPassword;

	/** The new password. */

	private String newPassword;

	/** The aliases. */

	private String aliases;

	/** The avatar list. */

	private List<AvatarsDTO> avatarList;

	/** The first login. */

	private String firstLogin;

	/** The first picturetemp. */

	private String picturetemp;

	/** The xbox id. */

	private String[] xboxId = null;

	/** The selectedxbox id. */

	private String[] selectedxboxId = null;

	/** The xbox list. */

	private List<GameDTO> xboxList = null;

	/** The xbox360 id. */

	private String[] xbox360Id = null;

	/** The selectedxbox360 id. */

	private String[] selectedxbox360Id = null;

	/** The xbox360 list. */

	private List<GameDTO> xbox360List = null;

	/** The ps2 id. */

	private String[] ps2Id = null;

	/** The selectedps2 id. */

	private String[] selectedps2Id = null;

	/** The ps2 list. */

	private List<GameDTO> ps2List = null;

	/** The ps3 id. */

	private String[] ps3Id = null;

	/** The selectedps3 id. */

	private String[] selectedps3Id = null;

	/** The ps3 list. */

	private List<GameDTO> ps3List = null;

	/**
	 *
	 * Gets the selectedxbox id.
	 *
	 *
	 *
	 * @return selectedxboxId
	 *
	 */

	public String[] getSelectedxboxId() {

		return selectedxboxId;

	}

	/**
	 *
	 * Gets the screen name.
	 *
	 *
	 *
	 * @return screenName
	 *
	 */

	public String getScreenName() {

		return screenName;

	}

	/**
	 *
	 * Sets the screen name.
	 *
	 *
	 *
	 * @param screenName
	 *
	 * the screen name
	 *
	 */

	public void setScreenName(String screenName) {

		this.screenName = screenName;

	}

	/**
	 *
	 * Sets the selectedxbox id.
	 *
	 *
	 *
	 * @param selectedxboxId
	 *
	 * the selectedxbox id
	 *
	 */

	public void setSelectedxboxId(String[] selectedxboxId) {

		this.selectedxboxId = selectedxboxId;

	}

	/**
	 *
	 * Gets the selectedxbox360 id.
	 *
	 *
	 *
	 * @return selectedxbox360Id
	 *
	 */

	public String[] getSelectedxbox360Id() {

		return selectedxbox360Id;

	}

	/**
	 *
	 * Sets the selectedxbox360 id.
	 *
	 *
	 *
	 * @param selectedxbox360Id
	 *
	 * the selectedxbox360 id
	 *
	 */

	public void setSelectedxbox360Id(String[] selectedxbox360Id) {

		this.selectedxbox360Id = selectedxbox360Id;

	}

	/**
	 *
	 * Gets the selectedps2 id.
	 *
	 *
	 *
	 * @return selectedps2Id
	 *
	 */

	public String[] getSelectedps2Id() {

		return selectedps2Id;

	}

	/**
	 *
	 * Sets the selectedps2 id.
	 *
	 *
	 *
	 * @param selectedps2Id
	 *
	 * the selectedps2 id
	 *
	 */

	public void setSelectedps2Id(String[] selectedps2Id) {

		this.selectedps2Id = selectedps2Id;

	}

	/**
	 *
	 * Gets the selectedps3 id.
	 *
	 *
	 *
	 * @return selectedps3Id
	 *
	 */

	public String[] getSelectedps3Id() {

		return selectedps3Id;

	}

	/**
	 *
	 * Sets the selectedps3 id.
	 *
	 *
	 *
	 * @param selectedps3Id
	 *
	 * the selectedps3 id
	 *
	 */

	public void setSelectedps3Id(String[] selectedps3Id) {

		this.selectedps3Id = selectedps3Id;

	}

	/**
	 *
	 * Gets the aim.
	 *
	 *
	 *
	 * @return aim
	 *
	 */

	public String getAim() {

		return aim;

	}

	/**
	 *
	 * Gets the availability.
	 *
	 *
	 *
	 * @return availability
	 *
	 */

	public String getAvailability() {

		return availability;

	}

	/**
	 *
	 * Gets the avatar image id.
	 *
	 *
	 *
	 * @return avatarImageId
	 *
	 */

	public String getAvatarImageId() {

		return avatarImageId;

	}

	/**
	 *
	 * Gets the avatar list.
	 *
	 *
	 *
	 * @return avatarList
	 *
	 */

	public List<AvatarsDTO> getAvatarList() {

		return avatarList;

	}

	/**
	 *
	 * Gets the contact me abt world gaming events.
	 *
	 *
	 *
	 * @return contactMeAbtWorldGamingEvents
	 *
	 */

	public String getContactMeAbtWorldGamingEvents() {

		return contactMeAbtWorldGamingEvents;

	}

	/**
	 *
	 * Gets the email my system alerts.
	 *
	 *
	 *
	 * @return emailMySystemAlerts
	 *
	 */

	public String getEmailMySystemAlerts() {

		return emailMySystemAlerts;

	}

	/**
	 *
	 * Gets the first login.
	 *
	 *
	 *
	 * @return firstLogin
	 *
	 */

	public String getFirstLogin() {

		return firstLogin;

	}

	/**
	 *
	 * Gets the msn.
	 *
	 *
	 *
	 * @return msn
	 *
	 */

	public String getMsn() {

		return msn;

	}

	/**
	 *
	 * Gets the playfor$.
	 *
	 *
	 *
	 * @return playfor$
	 *
	 */

	public String getPlayfor$() {

		return playfor$;

	}

	/**
	 *
	 * Gets the pref contact info.
	 *
	 *
	 *
	 * @return prefContactInfo
	 *
	 */

	public String getPrefContactInfo() {

		return prefContactInfo;

	}

	/**
	 *
	 * Gets the pref time zone.
	 *
	 *
	 *
	 * @return prefTimeZone
	 *
	 */

	public String getPrefTimeZone() {

		return prefTimeZone;

	}

	/**
	 *
	 * Gets the ps2.
	 *
	 *
	 *
	 * @return ps2
	 *
	 */

	public String getPs2() {

		return ps2;

	}

	/**
	 *
	 * Gets the ps2 id.
	 *
	 *
	 *
	 * @return ps2Id
	 *
	 */

	public String[] getPs2Id() {

		return ps2Id;

	}

	/**
	 *
	 * Gets the ps2 list.
	 *
	 *
	 *
	 * @return ps2List
	 *
	 */

	public List<GameDTO> getPs2List() {

		return ps2List;

	}

	/**
	 *
	 * Gets the ps3.
	 *
	 *
	 *
	 * @return ps3
	 *
	 */

	public String getPs3() {

		return ps3;

	}

	/**
	 *
	 * Gets the ps3 id.
	 *
	 *
	 *
	 * @return ps3Id
	 *
	 */

	public String[] getPs3Id() {

		return ps3Id;

	}

	/**
	 *
	 * Gets the ps3 list.
	 *
	 *
	 *
	 * @return ps3List
	 *
	 */

	public List<GameDTO> getPs3List() {

		return ps3List;

	}

	/**
	 *
	 * Gets the ps net tag.
	 *
	 *
	 *
	 * @return psNetTag
	 *
	 */

	public String getPsNetTag() {

		return psNetTag;

	}

	/**
	 *
	 * Gets the show my win losspublically.
	 *
	 *
	 *
	 * @return showMyWinLosspublically
	 *
	 */

	public String getShowMyWinLosspublically() {

		return showMyWinLosspublically;

	}

	/**
	 *
	 * Gets the skype.
	 *
	 *
	 *
	 * @return skype
	 *
	 */

	public String getSkype() {

		return skype;

	}

	/**
	 *
	 * Gets the xbox.
	 *
	 *
	 *
	 * @return xbox
	 *
	 */

	public String getXbox() {

		return xbox;

	}

	/**
	 *
	 * Gets the xbox360.
	 *
	 *
	 *
	 * @return xbox360
	 *
	 */

	public String getXbox360() {

		return xbox360;

	}

	/**
	 *
	 * Gets the xbox360 id.
	 *
	 *
	 *
	 * @return xbox360Id
	 *
	 */

	public String[] getXbox360Id() {

		return xbox360Id;

	}

	/**
	 *
	 * Gets the xbox360 list.
	 *
	 *
	 *
	 * @return xbox360List
	 *
	 */

	public List<GameDTO> getXbox360List() {

		return xbox360List;

	}

	/**
	 *
	 * Gets the xbox id.
	 *
	 *
	 *
	 * @return xboxId
	 *
	 */

	public String[] getXboxId() {

		return xboxId;

	}

	/**
	 *
	 * Gets the xbox list.
	 *
	 *
	 *
	 * @return xboxList
	 *
	 */

	public List<GameDTO> getXboxList() {

		return xboxList;

	}

	/**
	 *
	 * Gets the xbox live tag.
	 *
	 *
	 *
	 * @return xboxLiveTag
	 *
	 */

	public String getXboxLiveTag() {

		return xboxLiveTag;

	}

	/**
	 *
	 * Gets the yahoo.
	 *
	 *
	 *
	 * @return yahoo
	 *
	 */

	public String getYahoo() {

		return yahoo;

	}

	/**
	 *
	 * Gets the your message.
	 *
	 *
	 *
	 * @return yourMessage
	 *
	 */

	public String getYourMessage() {

		return yourMessage;

	}

	/**
	 *
	 * Gets the your story.
	 *
	 *
	 *
	 * @return yourStory
	 *
	 */

	public String getYourStory() {

		return yourStory;

	}

	/**
	 *
	 * Sets the aim.
	 *
	 *
	 *
	 * @param aim
	 *
	 * the aim
	 *
	 */

	public void setAim(String aim) {

		this.aim = aim;

	}

	/**
	 *
	 * Sets the availability.
	 *
	 *
	 *
	 * @param availability
	 *
	 * the availability
	 *
	 */

	public void setAvailability(String availability) {

		this.availability = availability;

	}

	/**
	 *
	 * Sets the avatar image id.
	 *
	 *
	 *
	 * @param avatarImageId
	 *
	 * the avatar image id
	 *
	 */

	public void setAvatarImageId(String avatarImageId) {

		this.avatarImageId = avatarImageId;

	}

	/**
	 *
	 * Sets the avatar list.
	 *
	 *
	 *
	 * @param avatarList
	 *
	 * the avatar list
	 *
	 */

	public void setAvatarList(List<AvatarsDTO> avatarList) {

		this.avatarList = avatarList;

	}

	/**
	 *
	 * Sets the contact me abt world gaming events.
	 *
	 *
	 *
	 * @param contactMeAbtWorldGamingEvents
	 *
	 * the contact me abt world gaming events
	 *
	 */

	public void setContactMeAbtWorldGamingEvents(

	String contactMeAbtWorldGamingEvents) {

		this.contactMeAbtWorldGamingEvents = contactMeAbtWorldGamingEvents;

	}

	/**
	 *
	 * Sets the email my system alerts.
	 *
	 *
	 *
	 * @param emailMySystemAlerts
	 *
	 * the email my system alerts
	 *
	 */

	public void setEmailMySystemAlerts(String emailMySystemAlerts) {

		this.emailMySystemAlerts = emailMySystemAlerts;

	}

	/**
	 *
	 * Sets the first login.
	 *
	 *
	 *
	 * @param firstLogin
	 *
	 * the first login
	 *
	 */

	public void setFirstLogin(String firstLogin) {

		this.firstLogin = firstLogin;

	}

	/**
	 *
	 * Sets the msn.
	 *
	 *
	 *
	 * @param msn
	 *
	 * the msn
	 *
	 */

	public void setMsn(String msn) {

		this.msn = msn;

	}

	/**
	 *
	 * Sets the playfor$.
	 *
	 *
	 *
	 * @param playfor$
	 *
	 * the playfor$
	 *
	 */

	public void setPlayfor$(String playfor$) {

		this.playfor$ = playfor$;

	}

	/**
	 *
	 * Sets the pref contact info.
	 *
	 *
	 *
	 * @param prefContactInfo
	 *
	 * the pref contact info
	 *
	 */

	public void setPrefContactInfo(String prefContactInfo) {

		this.prefContactInfo = prefContactInfo;

	}

	/**
	 *
	 * Sets the pref time zone.
	 *
	 *
	 *
	 * @param prefTimeZone
	 *
	 * the pref time zone
	 *
	 */

	public void setPrefTimeZone(String prefTimeZone) {

		this.prefTimeZone = prefTimeZone;

	}

	/**
	 *
	 * Sets the ps2.
	 *
	 *
	 *
	 * @param ps2
	 *
	 * the ps2
	 *
	 */

	public void setPs2(String ps2) {

		this.ps2 = ps2;

	}

	/**
	 *
	 * Sets the ps2 id.
	 *
	 *
	 *
	 * @param ps2Id
	 *
	 * the ps2 id
	 *
	 */

	public void setPs2Id(String[] ps2Id) {

		this.ps2Id = ps2Id;

	}

	/**
	 *
	 * Sets the ps3.
	 *
	 *
	 *
	 * @param ps3
	 *
	 * the ps3
	 *
	 */

	public void setPs3(String ps3) {

		this.ps3 = ps3;

	}

	/**
	 *
	 * Sets the ps3 id.
	 *
	 *
	 *
	 * @param ps3Id
	 *
	 * the ps3 id
	 *
	 */

	public void setPs3Id(String[] ps3Id) {

		this.ps3Id = ps3Id;

	}

	/**
	 *
	 * Sets the ps net tag.
	 *
	 *
	 *
	 * @param psNetTag
	 *
	 * the ps net tag
	 *
	 */

	public void setPsNetTag(String psNetTag) {

		this.psNetTag = psNetTag;

	}

	/**
	 *
	 * Sets the show my win losspublically.
	 *
	 *
	 *
	 * @param showMyWinLosspublically
	 *
	 * the show my win losspublically
	 *
	 */

	public void setShowMyWinLosspublically(String showMyWinLosspublically) {

		this.showMyWinLosspublically = showMyWinLosspublically;

	}

	/**
	 *
	 * Sets the skype.
	 *
	 *
	 *
	 * @param skype
	 *
	 * the skype
	 *
	 */

	public void setSkype(String skype) {

		this.skype = skype;

	}

	/**
	 *
	 * Sets the xbox.
	 *
	 *
	 *
	 * @param xbox
	 *
	 * the xbox
	 *
	 */

	public void setXbox(String xbox) {

		this.xbox = xbox;

	}

	/**
	 *
	 * Sets the xbox360.
	 *
	 *
	 *
	 * @param xbox360
	 *
	 * the xbox360
	 *
	 */

	public void setXbox360(String xbox360) {

		this.xbox360 = xbox360;

	}

	/**
	 *
	 * Sets the xbox360 id.
	 *
	 *
	 *
	 * @param xbox360Id
	 *
	 * the xbox360 id
	 *
	 */

	public void setXbox360Id(String[] xbox360Id) {

		this.xbox360Id = xbox360Id;

	}

	/**
	 *
	 * Sets the xbox id.
	 *
	 *
	 *
	 * @param xboxId
	 *
	 * the xbox id
	 *
	 */

	public void setXboxId(String[] xboxId) {

		this.xboxId = xboxId;

	}

	/**
	 *
	 * Sets the xbox live tag.
	 *
	 *
	 *
	 * @param xboxLiveTag
	 *
	 * the xbox live tag
	 *
	 */

	public void setXboxLiveTag(String xboxLiveTag) {

		this.xboxLiveTag = xboxLiveTag;

	}

	/**
	 *
	 * Sets the yahoo.
	 *
	 *
	 *
	 * @param yahoo
	 *
	 * the yahoo
	 *
	 */

	public void setYahoo(String yahoo) {

		this.yahoo = yahoo;

	}

	/**
	 *
	 * Sets the your message.
	 *
	 *
	 *
	 * @param yourMessage
	 *
	 * the your message
	 *
	 */

	public void setYourMessage(String yourMessage) {

		this.yourMessage = yourMessage;

	}

	/**
	 *
	 * Sets the your story.
	 *
	 *
	 *
	 * @param yourStory
	 *
	 * the your story
	 *
	 */

	public void setYourStory(String yourStory) {

		this.yourStory = yourStory;

	}

	/**
	 *
	 * Populates form of Register2form.
	 *
	 *
	 *
	 * @param baseDto
	 *
	 * the base dto
	 *
	 *
	 *
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 *
	 */

	public void populate(BaseDTO baseDto , HttpServletRequest  request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		UserDTO dto = (UserDTO) baseDto;

		PlayerDTO playerDTO = dto.getPlayerDTO();

		SearchCriteria searchCriteria = new SearchCriteria();

		searchCriteria.setAttribute(G4GConstants.PLAYERID_DB, playerDTO.getId());

		List<PlayernetworkDTO> networkPlayernetworkDTO = PlayerNetworkServiceDelegator
				.getList(searchCriteria);

		Iterator<PlayernetworkDTO> it = networkPlayernetworkDTO.iterator();

		/**
		 *
		 * Sets player network id
		 *
		 *
		 *
		 */

		while (it.hasNext()) {

			PlayernetworkDTO playernetworkDTO = it.next();

			if (playernetworkDTO.getNetwork() != null) {

				if (playernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_XBOX_LIST_KEY) {

					this.setXbox(String.valueOf(playernetworkDTO.getNetwork()
							.getId()));

					this.setXboxLiveTag(playernetworkDTO.getPlayernetworktag());

				}

				if (playernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_XBOX360_LIST_KEY) {

					this.setXbox360(String.valueOf(playernetworkDTO

					.getNetwork().getId()));

					this.setXboxLiveTag(playernetworkDTO.getPlayernetworktag());

				}

				if (playernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_PS2_ID_LIST_KEY) {

					this.setPs2(String.valueOf(playernetworkDTO

					.getNetwork().getId()));

					this.setPsNetTag(playernetworkDTO.getPlayernetworktag());

				}

				if (playernetworkDTO.getNetwork().getId() == G4GConstants.NETWORK_PS3_ID_LIST_KEY) {

					this.setPs3(String.valueOf(playernetworkDTO.getNetwork()
							.getId()));

					this.setPsNetTag(playernetworkDTO.getPlayernetworktag());

				}

			}

		}

		this.setYourMessage(playerDTO.getMessage());

		this.setAvailability(playerDTO.getAvailability());

		this.setPlayfor$(DataUtil.getString(playerDTO.getAverageamount()));

		this.setAvatarImageId(DataUtil
				.getString(playerDTO.getAvatars().getId()));

		this.setYourStory(playerDTO.getStory());

		Set<PlayerimDTO> playerimset = playerDTO.getPlayerims();

		for (PlayerimDTO element : playerimset) {

			if (G4GConstants.AIM_ID == element.getImnetwork().getId()) {

				this.setAim(element.getPlayerimtag());

			}

			if (G4GConstants.SKYPE_ID == element.getImnetwork().getId()) {

				this.setSkype(element.getPlayerimtag());

			}

			if (G4GConstants.YAHOO_ID == element.getImnetwork().getId()) {

				this.setYahoo(element.getPlayerimtag());

			}

			if (G4GConstants.MSN_ID == element.getImnetwork().getId()) {

				this.setMsn(element.getPlayerimtag());

			}

		}

		this.avatarList = ((List<AvatarsDTO>)request.getSession().getServletContext().getAttribute(G4GOriginSession.AVATAR_LIST));

		if (G4GConstants.TRUE.equals(playerDTO.getPrefdisplaycontacts())) {

			this.setContactMeAbtWorldGamingEvents(G4GConstants.TRUE);

		}

		if (G4GConstants.TRUE.equals(playerDTO.getPrefsysmail())) {

			this.setEmailMySystemAlerts(G4GConstants.TRUE);

		}

		if (G4GConstants.TRUE.equals(playerDTO.getPrefdisplayrecord())) {

			this.setShowMyWinLosspublically(G4GConstants.TRUE);

		}

		if (G4GConstants.TRUE.equals(playerDTO.getPrefaliases())) {

			this.setAliases(G4GConstants.TRUE);

		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

	}

	/**
	 *
	 * Gets the dto.
	 *
	 *
	 *
	 * @return the DTO
	 *
	 *
	 *
	 * @see com.g4g.forms.BaseForm#getDTO()
	 *
	 */

	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return null;

	}

	/**
	 *
	 * Gets the dto.
	 *
	 *
	 *
	 * @param dto
	 *
	 * the UserDTO to set user and player value.
	 *
	 * @param request
	 *
	 * the request
	 *
	 *
	 *
	 * @return baseDTO (UserDTO)Populated UserDTO by all form attributes.
	 *
	 */

	public BaseDTO getDTO(UserDTO dto, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		PlayerDTO playerDTO = dto.getPlayerDTO();

		com.impessa.worldgaming.client.User user = dto.getUser();

		PlayernetworkDTO playernetworkDTO = new PlayernetworkDTO();

		playerDTO.setMessage(this.getYourMessage());

		playerDTO.setAvailability(this.getAvailability());

		playerDTO.setAverageamount(DataUtil.getInteger(this.getPlayfor$()));

		playerDTO.setAvatars((AvatarsDTO)request.getSession().getServletContext().getAttribute(G4GConstants.AVATAR+this.getAvatarImageId()));


		playerDTO.setStory(this.getYourStory());

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		Set<PlayerimDTO> playerim = new HashSet<PlayerimDTO>();

		map.put(G4GConstants.AIM_ID, this.aim);

		map.put(G4GConstants.YAHOO_ID, this.yahoo);

		map.put(G4GConstants.MSN_ID, this.msn);

		map.put(G4GConstants.SKYPE_ID, this.skype);

		/**
		 * If u want to add more im network than add only above set integer as
		 * defined in imnetwork table.
		 */

		Set<Integer> temp = map.keySet();

		Iterator<Integer> it = temp.iterator();

		while (it.hasNext()) {

			int k = it.next();

			PlayerimDTO playerims = new PlayerimDTO();

			playerims.setCreationdate(DataUtil.todayGMT());

			playerims.setImnetwork(new ImnetworkDTO(k));

			playerims.setPlayerimtag(map.get(k));

			playerim.add(playerims);

		}

		playerDTO.setPlayerims(playerim);

		// playerDTO.setPhone(this.getPhone());
		playerDTO.setSkin(SkinServiceDelegator.getSkinDTO(DataUtil
				.getSessionSiteId(request)));
		if (!DataValidator.isNull(this.getPrefTimeZone())) {
			playerDTO.setTimezone(TimeZoneServiceDelegator.getTimeZone(Integer
					.parseInt(this.getPrefTimeZone())));
		}

		if (G4GConstants.ONE.equals(this.getXbox())) {

			if (DataValidator.isNull(this.getXboxLiveTag())) {

				dto.getPlayernetworkDTOxbox().setCreationdate(null);

				dto.getPlayernetworkDTOxbox().setNetwork(null);

				dto.getPlayernetworkDTOxbox().setPlayernetworktag(null);

			} else {

				dto.getPlayernetworkDTOxbox().setCreationdate(
						DataUtil.todayGMT());

				dto
						.getPlayernetworkDTOxbox()
						.setNetwork(
								NetworkServiceDelegator
										.getNetworkDTO(G4GConstants.NETWORK_XBOX_LIST_KEY));

				dto.getPlayernetworkDTOxbox().setPlayernetworktag(
						this.getXboxLiveTag());

			}

		} else {
			this.setXbox(null);
		}

		if (G4GConstants.TWO.equals(this.getXbox360())) {

			if (DataValidator.isNull(this.getXboxLiveTag())) {

				dto.getPlayernetworkDTOxbox360().setCreationdate(null);

				dto.getPlayernetworkDTOxbox360().setNetwork(null);

				dto.getPlayernetworkDTOxbox360().setPlayernetworktag(null);

			} else {

				dto.getPlayernetworkDTOxbox360().setCreationdate(

				DataUtil.todayGMT());

				dto.getPlayernetworkDTOxbox360().setNetwork(
						NetworkServiceDelegator.getNetworkDTO(

						G4GConstants.NETWORK_XBOX360_LIST_KEY));

				dto.getPlayernetworkDTOxbox360().setPlayernetworktag(

				this.getXboxLiveTag());

			}

		} else {
			this.setXbox360(null);
		}

		if (G4GConstants.THREE.equals(this.getPs2())) {

			if (DataValidator.isNull(this.getPsNetTag())) {

				dto.getPlayernetworkDTO3ps2().setCreationdate(null);

				dto.getPlayernetworkDTO3ps2().setNetwork(null);

				dto.getPlayernetworkDTO3ps2().setPlayernetworktag(null);

			} else {

				dto.getPlayernetworkDTO3ps2().setCreationdate(

				DataUtil.todayGMT());

				dto.getPlayernetworkDTO3ps2().setNetwork(
						NetworkServiceDelegator.getNetworkDTO(

						G4GConstants.NETWORK_PS2_ID_LIST_KEY));

				dto.getPlayernetworkDTO3ps2().setPlayernetworktag(

				this.getPsNetTag());

			}

		} else {
			this.setPs2(null);
		}

		if (G4GConstants.FOUR.equals(this.getPs3())) {

			if (DataValidator.isNull(this.getPsNetTag())) {

				dto.getPlayernetworkDTO4ps3().setCreationdate(null);

				dto.getPlayernetworkDTO4ps3().setNetwork(null);

				dto.getPlayernetworkDTO4ps3().setPlayernetworktag(null);

			} else {

				dto.getPlayernetworkDTO4ps3().setCreationdate(
						DataUtil.todayGMT());

				dto
						.getPlayernetworkDTO4ps3()
						.setNetwork(
								NetworkServiceDelegator
										.getNetworkDTO(G4GConstants.NETWORK_PS3_ID_LIST_KEY));

				dto.getPlayernetworkDTO4ps3().setPlayernetworktag(
						this.getPsNetTag());

			}

		} else {
			this.setPs3(null);
		}

		// if (G4GConstants.TRUE.equals(this.getPrefContactInfo())) {
		playerDTO.setPrefContactInfo(true);
		// } else {
		// playerDTO.setPrefContactInfo(false);
		// }

		if (G4GConstants.TRUE.equals(this.getAliases())) {

			playerDTO.setPrefaliases(true);

		} else {

			playerDTO.setPrefaliases(false);

		}

		if (G4GConstants.TRUE.equals(this.getContactMeAbtWorldGamingEvents())) {

			playerDTO.setPrefdisplaycontacts(true);

		} else {

			playerDTO.setPrefdisplaycontacts(false);

		}

		if (G4GConstants.TRUE.equals(this.getEmailMySystemAlerts())) {

			playerDTO.setPrefsysmail(true);

		} else {

			playerDTO.setPrefsysmail(false);

		}

		if (G4GConstants.TRUE.equals(this.getShowMyWinLosspublically())) {

			playerDTO.setPrefdisplayrecord(true);

		} else {

			playerDTO.setPrefdisplayrecord(false);

		}

		// user.setPhone(this.getPhone());

		if (playernetworkDTO.getCreationdate() != null) {

			playernetworkDTO.setCreationdate(DataUtil.todayGMT());

		} else {
			playernetworkDTO.setLastupdatedate(DataUtil.todayGMT());
		}

		dto.setUser(user);

		dto.setPlayerDTO(playerDTO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.append(G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return dto;

	}

	/**
	 *
	 * Returns playerDTO for profile form.
	 *
	 *
	 *
	 * @param playerDTO
	 *
	 * the player dto
	 *
	 * @param request
	 *
	 * the request
	 *
	 *
	 *
	 * @return PlayerDTO
	 *
	 */

	public PlayerDTO getProfileDTO(PlayerDTO playerDTO,

	HttpServletRequest request) {

		playerDTO.setAvailability(this.getAvailability());

		playerDTO.setMessage(this.getYourMessage());

		return playerDTO;

	}

	/**
	 *
	 * Gets the old password.
	 *
	 *
	 *
	 * @return oldPassword
	 *
	 */

	public String getOldPassword() {

		return oldPassword;

	}

	/**
	 *
	 * Sets the old password.
	 *
	 *
	 *
	 * @param oldPassword
	 *
	 * the old password
	 *
	 */

	public void setOldPassword(String oldPassword) {

		this.oldPassword = oldPassword;

	}

	/**
	 *
	 * Gets the new password.
	 *
	 *
	 *
	 * @return newPassword
	 *
	 */

	public String getNewPassword() {

		return newPassword;

	}

	/**
	 *
	 * Sets the new password.
	 *
	 *
	 *
	 * @param newPassword
	 *
	 * the new password
	 *
	 */

	public void setNewPassword(String newPassword) {

		this.newPassword = newPassword;

	}

	/**
	 *
	 * Sets the xbox list.
	 *
	 *
	 *
	 * @param xboxList
	 *
	 * the xbox list
	 *
	 */

	public void setXboxList(List<GameDTO> xboxList) {

		this.xboxList = xboxList;

	}

	/**
	 *
	 * Sets the xbox360 list.
	 *
	 *
	 *
	 * @param xbox360List
	 *
	 * the xbox360 list
	 *
	 */

	public void setXbox360List(List<GameDTO> xbox360List) {

		this.xbox360List = xbox360List;

	}

	/**
	 *
	 * Sets the ps2 list.
	 *
	 *
	 *
	 * @param ps2List
	 *
	 * the ps2 list
	 *
	 */

	public void setPs2List(List<GameDTO> ps2List) {

		this.ps2List = ps2List;

	}

	/**
	 *
	 * Sets the ps3 list.
	 *
	 *
	 *
	 * @param ps3List
	 *
	 * the ps3 list
	 *
	 */

	public void setPs3List(List<GameDTO> ps3List) {

		this.ps3List = ps3List;

	}

	/**
	 *
	 * Gets the aliases.
	 *
	 *
	 *
	 * @return aliases
	 *
	 */

	public String getAliases() {

		return aliases;

	}

	/**
	 *
	 * Sets the aliases.
	 *
	 *
	 *
	 * @param aliases
	 *
	 * the aliases
	 *
	 */

	public void setAliases(String aliases) {

		this.aliases = aliases;

	}

	/**
	 *
	 * Sets the network list sets the values from the register2Form. To set what
	 *
	 * games are selected by User on registration page.
	 *
	 *
	 *
	 * @param register2Form
	 *
	 * the register2 form to get the selected games by user.
	 *
	 * @param searchCriteria
	 *
	 * the search criteria
	 *
	 */

	public void setNetworkList(Register2Form register2Form,

	SearchCriteria searchCriteria) {

		searchCriteria.removeAllAttribute();

		NetworkDTO networkDTO = new NetworkDTO();
		networkDTO.setId(G4GConstants.NETWORK_PS2_ID_LIST_KEY);
		searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NETWORK, networkDTO);
		register2Form.setPs2List(GameServiceDelegator.getList(searchCriteria));

		Iterator<GameDTO> itps2 = register2Form.getPs2List().iterator();

		String[] ps2games = new String[register2Form.getPs2List().size()];

		for (int i = 0; i < ps2games.length; i++) {

			ps2games[i] = String.valueOf(itps2.next().getId());

		}

		register2Form.setPs2Id(ps2games);

		searchCriteria.removeAllAttribute();
		networkDTO.setId(G4GConstants.NETWORK_XBOX360_LIST_KEY);
		searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NETWORK, networkDTO);

		register2Form.setXbox360List(GameServiceDelegator

		.getList(searchCriteria));

		Iterator<GameDTO> itxbox360 = register2Form.getXbox360List().iterator();

		String[] xbox360games = new String[register2Form.getXbox360List()

		.size()];

		for (int i = G4GConstants.ZERO; i < xbox360games.length; i++) {

			xbox360games[i] = String.valueOf(itxbox360.next().getId());

		}

		register2Form.setXbox360Id(xbox360games);

		searchCriteria.removeAllAttribute();
		networkDTO.setId(G4GConstants.NETWORK_PS3_ID_LIST_KEY);
		searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NETWORK, networkDTO);

		register2Form.setPs3List(GameServiceDelegator.getList(searchCriteria));

		Iterator<GameDTO> itps3 = register2Form.getPs3List().iterator();

		String[] ps3games = new String[register2Form.getPs3List().size()];

		for (int i = G4GConstants.ZERO; i < ps3games.length; i++) {

			ps3games[i] = String.valueOf(itps3.next().getId());

		}

		register2Form.setPs3Id(ps3games);

		searchCriteria.removeAllAttribute();
		networkDTO.setId(G4GConstants.NETWORK_XBOX_LIST_KEY);
		searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NETWORK, networkDTO);

		register2Form.setXboxList(GameServiceDelegator.getList(searchCriteria));

		Iterator<GameDTO> itxbox = register2Form.getXbox360List().iterator();

		String[] xboxgames = new String[register2Form.getXboxList().size()];

		for (int i = G4GConstants.ZERO; i < xboxgames.length; i++) {

			xboxgames[i] = String.valueOf(itxbox.next().getId());

		}

		register2Form.setXboxId(xboxgames);

		searchCriteria.removeAllAttribute();

	}

	/**
	 *
	 * Validates the screenname, console values.If validations are violated it
	 *
	 * will generate an arror message.
	 *
	 *
	 *
	 * @param mapping
	 *
	 * the ActionMapping to get the ActionErrors.
	 *
	 * @param request
	 *
	 * the request object to get attribute from session.
	 *
	 *
	 *
	 * @return the action errors generated for validation violation.
	 *
	 *
	 *
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping,
	 *
	 * javax.servlet.http.HttpServletRequest)
	 *
	 */

	@Override
	public ActionErrors validate(ActionMapping mapping,

	HttpServletRequest request) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		ActionErrors errors = super.validate(mapping, request);

		ActionMessage msg;

		if (request.getSession().getAttribute(G4GConstants.ATTR_SCREEN_NAME) != null) {

			if (this.getScreenName() == null

			|| this.getScreenName().length() < G4GConstants.TWO_NUMBER) {

				msg = new ActionMessage(G4GConstants.ERRORS_REQUIRED,

				G4GConstants.SCREENBLANKNAME);

				errors.add(G4GConstants.SCREENNAME, msg);

			} else {

				SearchCriteria searchCriteria = new SearchCriteria();

				searchCriteria.setAttribute(G4GConstants.SCREENNAME_DB, this

				.getScreenName());

				List<PlayerDTO> list = PlayerServiceDelegator.getList(

				searchCriteria, true);

				if (list.size() > G4GConstants.ZERO) {

					msg = new ActionMessage(G4GConstants.ERROR_SCREENNAME);

					errors.add(G4GConstants.SCREEN_NAME, msg);

				}

				UserDTO userRequest = DataUtil.getUserDTO(request);

				userRequest.getPlayerDTO().setScreenname(this.getScreenName());

			}

		}

		if (this.getXbox() == null && this.getXbox360() == null) {

			this.setXboxLiveTag(null);

		}

		if (this.getPs2() == null && this.getPs3() == null) {

			this.setPsNetTag(null);

		}

		if (this.xboxLiveTag != null) {

			SearchCriteria searchCriteria = new SearchCriteria();

			searchCriteria.setAttribute(G4GConstants.PLAYERNETWORKTAG,

			this.xboxLiveTag.trim());

			if (PlayerNetworkServiceDelegator.getList(searchCriteria).size() > G4GConstants.ZERO) {

				msg = new ActionMessage(G4GConstants.ERRORS_UNIQUE,

				G4GConstants.XBOX);

				errors.add(G4GConstants.ERRORS_XBOXLIVETAG, msg);

			}

		}

		if (this.psNetTag != null) {

			SearchCriteria searchCriteria = new SearchCriteria();

			searchCriteria.setAttribute(G4GConstants.PLAYERNETWORKTAG,

			this.psNetTag.trim());

			if (PlayerNetworkServiceDelegator.getList(searchCriteria).size() > G4GConstants.ZERO) {

				msg = new ActionMessage(G4GConstants.ERRORS_UNIQUE,

				G4GConstants.PLAY_STATION);

				errors.add(G4GConstants.ERRORS_PSNETTAG, msg);

			}

		}

		if ((this.getPs2() != null || this.getPs3() != null)

		&& DataValidator.isNull(this.getPsNetTag())) {

			msg = new ActionMessage(G4GConstants.NETWORK_INVALID_PS2);

			errors.add(G4GConstants.NETWORK_INVALID_PS2, msg);

			this.setPsNetTag(null);

			this.setPs2(null);

			this.setPs3(null);

		}

		if ((this.getXbox() != null || this.getXbox360() != null)

		&& DataValidator.isNull(this.getXboxLiveTag())) {

			msg = new ActionMessage(G4GConstants.NETWORK_INVALID_XBOX);

			errors.add(G4GConstants.NETWORK_INVALID_XBOX, msg);

			this.setXboxLiveTag(null);

			this.setXbox(null);

			this.setXbox360(null);

		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.append(G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return errors;

	}

	/**
	 *
	 * @return the pictureId
	 *
	 */

	public String getPictureId() {

		return this.pictureId;

	}

	/**
	 *
	 * @param pictureId
	 *            the pictureId to set
	 *
	 */

	public void setPictureId(String pictureId) {

		this.pictureId = pictureId;

	}

	/**
	 *
	 * @return the picture
	 *
	 */

	public FormFile getPicture() {

		return this.picture;

	}

	/**
	 *
	 * @param picture
	 *            the picture to set
	 *
	 */

	public void setPicture(FormFile picture) {

		this.picture = picture;

	}

	/**
	 *
	 * @return the yourMessageTemp
	 *
	 */

	public String getYourMessageTemp() {

		return this.yourMessageTemp;

	}

	/**
	 *
	 * @param yourMessageTemp
	 *            the yourMessageTemp to set
	 *
	 */

	public void setYourMessageTemp(String yourMessageTemp) {

		this.yourMessageTemp = yourMessageTemp;

	}

	/**
	 *
	 * @return the yourStoryTemp
	 *
	 */

	public String getYourStoryTemp() {

		return this.yourStoryTemp;

	}

	/**
	 *
	 * @param yourStoryTemp
	 *            the yourStoryTemp to set
	 *
	 */

	public void setYourStoryTemp(String yourStoryTemp) {

		this.yourStoryTemp = yourStoryTemp;

	}

	/**
	 *
	 * @return the availabilityTemp
	 *
	 */

	public String getAvailabilityTemp() {

		return this.availabilityTemp;

	}

	/**
	 *
	 * @param availabilityTemp
	 *            the availabilityTemp to set
	 *
	 */

	public void setAvailabilityTemp(String availabilityTemp) {

		this.availabilityTemp = availabilityTemp;

	}

	/**
	 *
	 * @return the picturetemp
	 *
	 */

	public String getPicturetemp() {

		return this.picturetemp;

	}

	/**
	 *
	 * @param picturetemp
	 *            the picturetemp to set
	 *
	 */

	public void setPicturetemp(String picturetemp) {

		this.picturetemp = picturetemp;

	}

	/* (non-Javadoc)
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		// TODO Auto-generated method stub

	}

}
