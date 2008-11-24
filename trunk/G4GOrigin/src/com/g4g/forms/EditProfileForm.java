/**********************************************************
 * className.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.AvatarsServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.delegator.SkinServiceDelegator;
import com.g4g.delegator.TimeZoneServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class EditProfileForm has attribute for editProfile tab on profile page.
 * EditProfileAction class uses it.Its attributes are xbox, password, xbox360,
 * ..etc.It contains the properties of user that can be edited.
 * 
 * @author Pratik
 */
public class EditProfileForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The password. */
	private String password;

	/** The xbox. */
	private String xbox;

	/** The xbox360. */
	private String xbox360;

	/** The ps2. */
	private String ps2;

	/** The ps3. */
	private String ps3;

	/** The avatar image id. */
	private String avatarImageId;

	/** The your message. */
	private String yourMessage;

	/** The your story. */
	private String yourStory;

	/** The availability. */
	private String availability;

	/** The your messageTemp. */
	private String yourMessageTemp;

	/** The your storyTemp. */
	private String yourStoryTemp;

	/** The availabilityTemp. */
	private String availabilityTemp;

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

	/** The phone. */
	private String phone;

	/** The pref contact info. */
	private String prefContactInfo;

	/** The contact me abt world gaming events. */
	private String contactMeAbtWorldGamingEvents;

	/** The email my system alerts. */
	private String emailMySystemAlerts;

	/** The show my win losspublically. */
	private String showMyWinLosspublically;

	/** The xbox live tag. */
	private String xboxLiveTag;

	/** The ps net tag. */
	private String psNetTag;

	/** The pref time zone. */
	private String prefTimeZone;

	/** The old password. */
	private String oldPassword;

	/** The new password. */
	private String newPassword;

	/** The confirm password. */
	private String confirmPassword;

	/** The aliases. */
	private String aliases;

	/** The avatar list. */
	private List<AvatarsDTO> avatarList;

	/** The first login. */
	private String firstLogin;

	/** The xbox id. */
	private String[] xboxId = null;

	/** The xbox list. */
	private List<GameDTO> xboxList = null;

	/** The xbox360 id. */
	private String[] xbox360Id = null;

	/** The xbox360 list. */
	private List<GameDTO> xbox360List = null;

	/** The ps2 id. */
	private String[] ps2Id = null;

	/** The ps2 list. */
	private List<GameDTO> ps2List = null;

	/** The ps3 id. */
	private String[] ps3Id = null;

	/** The ps3 list. */
	private List<GameDTO> ps3List = null;

	/**
	 * Gets the aim.
	 * 
	 * @return the aim
	 */
	public String getAim() {
		return aim;
	}

	/**
	 * Gets the availability.
	 * 
	 * @return the availability
	 */
	public String getAvailability() {
		return availability;
	}

	/**
	 * Gets the avatar image id.
	 * 
	 * @return the avatar image id
	 */
	public String getAvatarImageId() {
		return avatarImageId;
	}

	/**
	 * Gets the avatar list.
	 * 
	 * @return the avatar list
	 */
	public List<AvatarsDTO> getAvatarList() {
		return avatarList;
	}

	/**
	 * Gets the contact me abt world gaming events.
	 * 
	 * @return the contact me abt world gaming events
	 */
	public String getContactMeAbtWorldGamingEvents() {
		return contactMeAbtWorldGamingEvents;
	}

	/**
	 * Gets the email my system alerts.
	 * 
	 * @return the email my system alerts
	 */
	public String getEmailMySystemAlerts() {
		return emailMySystemAlerts;
	}

	/**
	 * Gets the first login.
	 * 
	 * @return the first login
	 */
	public String getFirstLogin() {
		return firstLogin;
	}

	/**
	 * Gets the msn.
	 * 
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * Gets the phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Gets the playfor$.
	 * 
	 * @return the playfor$
	 */
	public String getPlayfor$() {
		return playfor$;
	}

	/**
	 * Gets the pref contact info.
	 * 
	 * @return the pref contact info
	 */
	public String getPrefContactInfo() {
		return prefContactInfo;
	}

	/**
	 * Gets the pref time zone.
	 * 
	 * @return the pref time zone
	 */
	public String getPrefTimeZone() {
		return prefTimeZone;
	}

	/**
	 * Gets the ps2.
	 * 
	 * @return the ps2
	 */
	public String getPs2() {
		return ps2;
	}

	/**
	 * Gets the ps2 id.
	 * 
	 * @return the ps2 id
	 */
	public String[] getPs2Id() {
		return ps2Id;
	}

	/**
	 * Gets the ps2 list.
	 * 
	 * @return the ps2 list
	 */
	public List<GameDTO> getPs2List() {
		return ps2List;
	}

	/**
	 * Gets the ps3.
	 * 
	 * @return the ps3
	 */
	public String getPs3() {
		return ps3;
	}

	/**
	 * Gets the ps3 id.
	 * 
	 * @return the ps3 id
	 */
	public String[] getPs3Id() {
		return ps3Id;
	}

	/**
	 * Gets the ps3 list.
	 * 
	 * @return the ps3 list
	 */
	public List<GameDTO> getPs3List() {
		return ps3List;
	}

	/**
	 * Gets the ps net tag.
	 * 
	 * @return the ps net tag
	 */
	public String getPsNetTag() {
		return psNetTag;
	}

	/**
	 * Gets the show my win losspublically.
	 * 
	 * @return the show my win losspublically
	 */
	public String getShowMyWinLosspublically() {
		return showMyWinLosspublically;
	}

	/**
	 * Gets the skype.
	 * 
	 * @return the skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * Gets the xbox.
	 * 
	 * @return the xbox
	 */
	public String getXbox() {
		return xbox;
	}

	/**
	 * Gets the xbox360.
	 * 
	 * @return the xbox360
	 */
	public String getXbox360() {
		return xbox360;
	}

	/**
	 * Gets the xbox360 id.
	 * 
	 * @return the xbox360 id
	 */
	public String[] getXbox360Id() {
		return xbox360Id;
	}

	/**
	 * Gets the xbox360 list.
	 * 
	 * @return the xbox360 list
	 */
	public List<GameDTO> getXbox360List() {
		return xbox360List;
	}

	/**
	 * Gets the xbox id.
	 * 
	 * @return the xbox id
	 */
	public String[] getXboxId() {
		return xboxId;
	}

	/**
	 * Gets the xbox list.
	 * 
	 * @return the xbox list
	 */
	public List<GameDTO> getXboxList() {
		return xboxList;
	}

	/**
	 * Gets the xbox live tag.
	 * 
	 * @return the xbox live tag
	 */
	public String getXboxLiveTag() {
		return xboxLiveTag;
	}

	/**
	 * Gets the yahoo.
	 * 
	 * @return the yahoo
	 */
	public String getYahoo() {
		return yahoo;
	}

	/**
	 * Gets the your message.
	 * 
	 * @return the your message
	 */
	public String getYourMessage() {
		return yourMessage;
	}

	/**
	 * Gets the your story.
	 * 
	 * @return the your story
	 */
	public String getYourStory() {
		return yourStory;
	}

	/**
	 * Sets the aim.
	 * 
	 * @param aim
	 *            the new aim
	 */
	public void setAim(String aim) {
		this.aim = aim;
	}

	/**
	 * Sets the availability.
	 * 
	 * @param availability
	 *            the new availability
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
	}

	/**
	 * Sets the avatar image id.
	 * 
	 * @param avatarImageId
	 *            the new avatar image id
	 */
	public void setAvatarImageId(String avatarImageId) {
		this.avatarImageId = avatarImageId;
	}

	/**
	 * Sets the avatar list.
	 * 
	 * @param avatarList
	 *            the new avatar list
	 */
	public void setAvatarList(List<AvatarsDTO> avatarList) {
		this.avatarList = avatarList;
	}

	/**
	 * Sets the contact me abt world gaming events.
	 * 
	 * @param contactMeAbtWorldGamingEvents
	 *            the new contact me abt world gaming events
	 */
	public void setContactMeAbtWorldGamingEvents(
			String contactMeAbtWorldGamingEvents) {
		this.contactMeAbtWorldGamingEvents = contactMeAbtWorldGamingEvents;
	}

	/**
	 * Sets the email my system alerts.
	 * 
	 * @param emailMySystemAlerts
	 *            the new email my system alerts
	 */
	public void setEmailMySystemAlerts(String emailMySystemAlerts) {
		this.emailMySystemAlerts = emailMySystemAlerts;
	}

	/**
	 * Sets the first login.
	 * 
	 * @param firstLogin
	 *            the new first login
	 */
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	/**
	 * Sets the msn.
	 * 
	 * @param msn
	 *            the new msn
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * Sets the phone.
	 * 
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Sets the playfor$.
	 * 
	 * @param playfor$
	 *            the new playfor$
	 */
	public void setPlayfor$(String playfor$) {
		this.playfor$ = playfor$;
	}

	/**
	 * Sets the pref contact info.
	 * 
	 * @param prefContactInfo
	 *            the new pref contact info
	 */
	public void setPrefContactInfo(String prefContactInfo) {
		this.prefContactInfo = prefContactInfo;
	}

	/**
	 * Sets the pref time zone.
	 * 
	 * @param prefTimeZone
	 *            the new pref time zone
	 */
	public void setPrefTimeZone(String prefTimeZone) {
		this.prefTimeZone = prefTimeZone;
	}

	/**
	 * Sets the ps2.
	 * 
	 * @param ps2
	 *            the new ps2
	 */
	public void setPs2(String ps2) {
		this.ps2 = ps2;
	}

	/**
	 * Sets the ps2 id.
	 * 
	 * @param ps2Id
	 *            the new ps2 id
	 */
	public void setPs2Id(String[] ps2Id) {
		this.ps2Id = ps2Id;
	}

	/**
	 * Sets the ps3.
	 * 
	 * @param ps3
	 *            the new ps3
	 */
	public void setPs3(String ps3) {
		this.ps3 = ps3;
	}

	/**
	 * Sets the ps3 id.
	 * 
	 * @param ps3Id
	 *            the new ps3 id
	 */
	public void setPs3Id(String[] ps3Id) {
		this.ps3Id = ps3Id;
	}

	/**
	 * Sets the ps net tag.
	 * 
	 * @param psNetTag
	 *            the new ps net tag
	 */
	public void setPsNetTag(String psNetTag) {
		this.psNetTag = psNetTag;
	}

	/**
	 * Sets the show my win losspublically.
	 * 
	 * @param showMyWinLosspublically
	 *            the new show my win losspublically
	 */
	public void setShowMyWinLosspublically(String showMyWinLosspublically) {
		this.showMyWinLosspublically = showMyWinLosspublically;
	}

	/**
	 * Sets the skype.
	 * 
	 * @param skype
	 *            the new skype
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * Sets the xbox.
	 * 
	 * @param xbox
	 *            the new xbox
	 */
	public void setXbox(String xbox) {
		this.xbox = xbox;
	}

	/**
	 * Sets the xbox360.
	 * 
	 * @param xbox360
	 *            the new xbox360
	 */
	public void setXbox360(String xbox360) {
		this.xbox360 = xbox360;
	}

	/**
	 * Sets the xbox360 id.
	 * 
	 * @param xbox360Id
	 *            the new xbox360 id
	 */
	public void setXbox360Id(String[] xbox360Id) {
		this.xbox360Id = xbox360Id;
	}

	/**
	 * Sets the xbox id.
	 * 
	 * @param xboxId
	 *            the new xbox id
	 */
	public void setXboxId(String[] xboxId) {
		this.xboxId = xboxId;
	}

	/**
	 * Sets the xbox live tag.
	 * 
	 * @param xboxLiveTag
	 *            the new xbox live tag
	 */
	public void setXboxLiveTag(String xboxLiveTag) {
		this.xboxLiveTag = xboxLiveTag;
	}

	/**
	 * Sets the yahoo.
	 * 
	 * @param yahoo
	 *            the new yahoo
	 */
	public void setYahoo(String yahoo) {
		this.yahoo = yahoo;
	}

	/**
	 * Sets the your message.
	 * 
	 * @param yourMessage
	 *            the new your message
	 */
	public void setYourMessage(String yourMessage) {
		this.yourMessage = yourMessage;
	}

	/**
	 * Sets the your story.
	 * 
	 * @param yourStory
	 *            the new your story
	 */
	public void setYourStory(String yourStory) {
		this.yourStory = yourStory;
	}

	/**
	 * 
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
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
		com.impessa.worldgaming.client.User userDTO = dto.getUser();
		PlayerDTO playerDTO = dto.getPlayerDTO();
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.PLAYERID_DB, playerDTO.getId());
		List<PlayernetworkDTO> networkPlayernetworkDTO = PlayerNetworkServiceDelegator
				.getList(searchCriteria);
		Iterator<PlayernetworkDTO> it = networkPlayernetworkDTO.iterator();
		// Populate the userDTO
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
					this.setPs2(String.valueOf(playernetworkDTO.getNetwork()
							.getId()));
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
		this.setPhone(playerDTO.getPhone());
		this.avatarList = AvatarsServiceDelegator.getList();

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
		this.setPhone(userDTO.getPhone());

		// populate all String[] xboxId xbox360Id from dto(UserDTO)
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
	 * @see com.g4g.forms.BaseForm#getDTO()
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
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
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
	 * Gets the dTO to display the already existing values.
	 * 
	 * @param dto
	 *            the dto
	 * @param request
	 *            the request
	 * 
	 * @return the dTO
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
		playerDTO.setAvatars(AvatarsServiceDelegator.getAvatars(DataUtil
				.getInteger(this.getAvatarImageId())));
		playerDTO.setStory(this.getYourStory());
		playerDTO.setPhone(this.getPhone());
		playerDTO.setSkin(SkinServiceDelegator.getSkinDTO(DataUtil
				.getSessionSiteId(request)));
		playerDTO.setTimezone(TimeZoneServiceDelegator.getTimeZone(Integer
				.parseInt(this.getPrefTimeZone())));

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
				dto
						.getPlayernetworkDTOxbox360()
						.setNetwork(
								NetworkServiceDelegator
										.getNetworkDTO(G4GConstants.NETWORK_XBOX360_LIST_KEY));
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
				dto
						.getPlayernetworkDTO3ps2()
						.setNetwork(
								NetworkServiceDelegator
										.getNetworkDTO(G4GConstants.NETWORK_PS2_ID_LIST_KEY));
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

		if (G4GConstants.TRUE.equals(this.getPrefContactInfo())) {
			playerDTO.setPrefContactInfo(true);
		} else {
			playerDTO.setPrefContactInfo(false);
		}

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
		user.setPhone(this.getPhone());

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
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).append(
								G4GConstants.DASHES).append(
								G4GConstants.SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return dto;
	}

	/**
	 * Gets the profile dto.
	 * 
	 * @param playerDTO
	 *            the player dto
	 * @param request
	 *            the request
	 * 
	 * @return the profile dto
	 */
	public PlayerDTO getProfileDTO(PlayerDTO playerDTO,
			HttpServletRequest request) {
		playerDTO.setAvailability(this.getAvailability());
		playerDTO.setMessage(this.getYourMessage());
		return playerDTO;
	}

	/**
	 * Gets the old password.
	 * 
	 * @return the old password
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * Sets the old password.
	 * 
	 * @param oldPassword
	 *            the new old password
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Gets the new password.
	 * 
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 * 
	 * @param newPassword
	 *            the new new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Sets the xbox list.
	 * 
	 * @param xboxList
	 *            the new xbox list
	 */
	public void setXboxList(List<GameDTO> xboxList) {
		this.xboxList = xboxList;
	}

	/**
	 * Sets the xbox360 list.
	 * 
	 * @param xbox360List
	 *            the new xbox360 list
	 */
	public void setXbox360List(List<GameDTO> xbox360List) {
		this.xbox360List = xbox360List;
	}

	/**
	 * Sets the ps2 list.
	 * 
	 * @param ps2List
	 *            the new ps2 list
	 */
	public void setPs2List(List<GameDTO> ps2List) {
		this.ps2List = ps2List;
	}

	/**
	 * Sets the ps3 list.
	 * 
	 * @param ps3List
	 *            the new ps3 list
	 */
	public void setPs3List(List<GameDTO> ps3List) {
		this.ps3List = ps3List;
	}

	/**
	 * Gets the aliases.
	 * 
	 * @return the aliases
	 */
	public String getAliases() {
		return aliases;
	}

	/**
	 * Sets the aliases.
	 * 
	 * @param aliases
	 *            the new aliases
	 */
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	/**
	 * Sets the network list.
	 * 
	 * @param editProfileForm
	 *            the edit profile form
	 * @param searchCriteria
	 *            the search criteria
	 */
	public void setNetworkList(EditProfileForm editProfileForm,
			SearchCriteria searchCriteria) {

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.NETWORKID_DB, 3);
		editProfileForm
				.setPs2List(GameServiceDelegator.getList(searchCriteria));
		searchCriteria.removeAllAttribute();

		searchCriteria.setAttribute(G4GConstants.NETWORKID_DB, 2);
		editProfileForm.setXbox360List(GameServiceDelegator
				.getList(searchCriteria));
		searchCriteria.removeAllAttribute();

		searchCriteria.setAttribute(G4GConstants.NETWORKID_DB, 4);
		editProfileForm
				.setPs3List(GameServiceDelegator.getList(searchCriteria));
		searchCriteria.removeAllAttribute();

		searchCriteria.setAttribute(G4GConstants.NETWORKID_DB, 1);
		editProfileForm.setXboxList(GameServiceDelegator
				.getList(searchCriteria));
		searchCriteria.removeAllAttribute();

	}

	/**
	 * 
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
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
		request.getSession().setAttribute(G4GConstants.LOAD_EDIT_PROFILE, true);

		if (this.getXbox() == null && this.getXbox360() == null) {
			this.setXboxLiveTag(null);
		}

		if (this.getPs2() == null && this.getPs3() == null) {
			this.setPsNetTag(null);
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
	 * Gets the confirm password.
	 * 
	 * @return the confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 * 
	 * @param confirmPassword
	 *            the new confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the your message temp.
	 * 
	 * @return the your message temp
	 */
	public String getYourMessageTemp() {
		return yourMessageTemp;
	}

	/**
	 * Sets the your message temp.
	 * 
	 * @param yourMessageTemp
	 *            the new your message temp
	 */
	public void setYourMessageTemp(String yourMessageTemp) {
		this.yourMessageTemp = yourMessageTemp;
	}

	/**
	 * Gets the your story temp.
	 * 
	 * @return the your story temp
	 */
	public String getYourStoryTemp() {
		return yourStoryTemp;
	}

	/**
	 * Sets the your story temp.
	 * 
	 * @param yourStoryTemp
	 *            the new your story temp
	 */
	public void setYourStoryTemp(String yourStoryTemp) {
		this.yourStoryTemp = yourStoryTemp;
	}

	/**
	 * Gets the availability temp.
	 * 
	 * @return the availability temp
	 */
	public String getAvailabilityTemp() {
		return availabilityTemp;
	}

	/**
	 * Sets the availability temp.
	 * 
	 * @param availabilityTemp
	 *            the new availability temp
	 */
	public void setAvailabilityTemp(String availabilityTemp) {
		this.availabilityTemp = availabilityTemp;
	}

}