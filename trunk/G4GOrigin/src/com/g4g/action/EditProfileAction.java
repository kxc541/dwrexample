/*
 * 
 */
package com.g4g.action;

import static com.g4g.utils.G4GConstants.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.PlayerImServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.ImnetworkDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayerimDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.forms.BaseForm;
import com.g4g.forms.EditProfileForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidPasswordException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserValidationException_Exception;

/**
 * The Class EditProfileAction.
 */
public class EditProfileAction extends BaseAction {

	/**
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.DISPLAY_STARTS)
						.append(G4GConstants.DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DISPLAY_STARTS).append(DASHES).append(
									USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		EditProfileForm profileForm = (EditProfileForm) form;
		populate(request, profileForm);
		return null;
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
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod())
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								SUBMIT_STARTS).append(DASHES).append(
								request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(SUBMIT_STARTS).append(DASHES).append(
									USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		EditProfileForm profileForm = (EditProfileForm) form;
		/**
		 * @author ankur Changing player gamer tags and preferences
		 */
		if (CHANGE.equalsIgnoreCase(operation)) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(PROFILE_CHANGE).append(
							request.getSession().getId()).toString(),
					Level.INFO);

			PlayerDTO playerDTO = DataUtil.getUserDTO(request).getPlayerDTO();
			playerDTO = profileForm.getProfileDTO(playerDTO, request);
			String newPassword = request.getParameter(NEW_PASSWORD);
			String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
			String oldPassword = request.getParameter(PASSWORD);
			User user = DataUtil.getUserDTO(request).getUser();
			user.setPhone(profileForm.getPhone());
			try {
				if (newPassword != null) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(PROFILE_CHANGE).append(
									CHANGING_PASSWORD).append(
									request.getSession().getId()).toString(),
							Level.INFO);
					G4GFinancialDelegator.setUpdateUser(DataUtil
							.getSessionId(request), user);
				}
			} catch (InternalException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								exception.getMessage()).append(
								DataUtil.getUserDTO(request).getPlayerDTO()
										.getScreenname()).append(
								request.getSession().getId()).toString(),
						Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						this.getClass().getName() + exception.getMessage(),
						Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserValidationException_Exception exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						this.getClass().getName() + exception.getMessage(),
						Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			if (newPassword != null) {
				if (newPassword.equals(confirmPassword)
						&& newPassword.length() >= G4GConstants.ONE_NUMBER) {
					if (newPassword.length() < G4GConstants.FIVE_NUMBER) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(PASSWORD_SMALLER).append(
												request.getSession().getId())
										.toString(), Level.INFO);
						msg = new ActionMessage(
								APP_PROFILEFORM_PASSWORDMINLENGTH);
						error.clear();
						error.add(NEW_PASSWORD, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					}
					try {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(IMPASSA_CHANGE_PASSWORD)
										.append(request.getSession().getId())
										.toString(), Level.INFO);
						G4GFinancialDelegator.changePassword(DataUtil
								.getUserDTO(request).getSessionId(),
								oldPassword, newPassword);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(CHANGING_PASSWORD)
										.append(BLANK).append(PASS).append(
												request.getSession().getId())
										.toString(), Level.INFO);
					} catch (InternalException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								this.getClass().getName()
										+ exception.getMessage(), Level.ERROR);
						msg = new ActionMessage(exception.getMessage());
						error.clear();
						error.add(ERROR, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					} catch (InvalidPasswordException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G, exception);
						msg = new ActionMessage(exception.getMessage());
						error.clear();
						error.add(PASSWORD, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					} catch (InvalidSessionException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								this.getClass().getName()
										+ exception.getMessage(), Level.ERROR);
						msg = new ActionMessage(exception.getMessage());
						error.clear();
						error.add(ERROR, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					} catch (UserValidationException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								this.getClass().getName()
										+ exception.getMessage(), Level.ERROR);
						msg = new ActionMessage(exception.getMessage());
						error.clear();
						error.add(ERROR, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();
					}
				}
			}
			// for updating netwok id
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(PLAYER, playerDTO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(DASHES)
							.append(GETTING).append(BLANK).append(
									PLAYER_NETWORK).toString(), Level.INFO);
			List<PlayernetworkDTO> networkPlayernetworkDTO = PlayerNetworkServiceDelegator
					.getList(searchCriteria);
			PlayernetworkDTO dtops2 = new PlayernetworkDTO();
			PlayernetworkDTO dtoxbox360 = new PlayernetworkDTO();
			PlayernetworkDTO dtops3 = new PlayernetworkDTO();
			dtops2.setPlayer(playerDTO);
			dtops3.setPlayer(playerDTO);
			dtoxbox360.setPlayer(playerDTO);
			dtops2.setCreationdate(DataUtil.todayGMT());
			dtops3.setCreationdate(DataUtil.todayGMT());
			dtoxbox360.setCreationdate(DataUtil.todayGMT());
			if (networkPlayernetworkDTO != null
					&& networkPlayernetworkDTO.size() > G4GConstants.ZERO) {
				for (PlayernetworkDTO aNetworkPlayernetworkDTO : networkPlayernetworkDTO) {
					if (aNetworkPlayernetworkDTO.getNetwork() != null) {
						if (aNetworkPlayernetworkDTO.getNetwork().getId() == NETWORK_PS2_ID_LIST_KEY) {
							dtops2.setId(aNetworkPlayernetworkDTO.getId());
							if (aNetworkPlayernetworkDTO.getCreationdate() != null) {
								dtops2.setCreationdate(aNetworkPlayernetworkDTO
										.getCreationdate());
								dtops2.setLastupdatedate(DataUtil.todayGMT());
							} else {
								dtops2.setCreationdate(DataUtil.todayGMT());
							}
						} else if (aNetworkPlayernetworkDTO.getNetwork()
								.getId() == NETWORK_PS3_ID_LIST_KEY) {
							dtops3.setId(aNetworkPlayernetworkDTO.getId());
							if (aNetworkPlayernetworkDTO.getCreationdate() != null) {
								dtops3.setCreationdate(aNetworkPlayernetworkDTO
										.getCreationdate());
								dtops3.setLastupdatedate(DataUtil.todayGMT());
							} else {
								dtops3.setCreationdate(DataUtil.todayGMT());
							}
						} else if (aNetworkPlayernetworkDTO.getNetwork()
								.getId() == NETWORK_XBOX360_LIST_KEY) {
							dtoxbox360.setId(aNetworkPlayernetworkDTO.getId());
							if (aNetworkPlayernetworkDTO.getCreationdate() != null) {
								dtoxbox360
										.setCreationdate(aNetworkPlayernetworkDTO
												.getCreationdate());
								dtoxbox360.setLastupdatedate(DataUtil
										.todayGMT());
							} else {
								dtoxbox360.setCreationdate(DataUtil.todayGMT());
							}
						}
					} else {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(DASHES).append(DELETING)
										.append(BLANK).append(PLAYER_NETWORK)
										.append(request.getSession().getId())
										.toString(), Level.INFO);
						PlayerNetworkServiceDelegator
								.delete(aNetworkPlayernetworkDTO);
					}
				}
			}
			if (String.valueOf(NETWORK_PS2_ID_LIST_KEY).equals(
					profileForm.getPs2())
					&& profileForm.getPsNetTag() != null) {
				dtops2.setNetwork(NetworkServiceDelegator
						.getNetworkDTO(NETWORK_PS2_ID_LIST_KEY));
				dtops2.setPlayernetworktag(profileForm.getPsNetTag());
			}

			if (String.valueOf(NETWORK_PS3_ID_LIST_KEY).equals(
					profileForm.getPs3())
					&& profileForm.getPsNetTag() != null) {
				dtops3.setNetwork(NetworkServiceDelegator
						.getNetworkDTO(NETWORK_PS3_ID_LIST_KEY));
				dtops3.setPlayernetworktag(profileForm.getPsNetTag());
			}

			if (String.valueOf(NETWORK_XBOX360_LIST_KEY).equals(
					profileForm.getXbox360())
					&& profileForm.getXboxLiveTag() != null) {
				dtoxbox360.setNetwork(NetworkServiceDelegator
						.getNetworkDTO(NETWORK_XBOX360_LIST_KEY));
				dtoxbox360.setPlayernetworktag(profileForm.getXboxLiveTag());
			}

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(DASHES)
							.append(UPDATING).append(BLANK).append(
									PLAYER_NETWORK).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			PlayerNetworkServiceDelegator.update(dtops3);
			PlayerNetworkServiceDelegator.update(dtops2);
			PlayerNetworkServiceDelegator.update(dtoxbox360);
			if (ON.equals(request.getParameter(PREFALIASES))) {
				playerDTO.setPrefaliases(true);
			} else {
				playerDTO.setPrefaliases(false);
			}

			if (ON.equals(request.getParameter(PREFDISPLAYRECORD))) {
				playerDTO.setPrefdisplayrecord(true);
			} else {
				playerDTO.setPrefdisplayrecord(false);
			}

			if (ON.equals(request.getParameter(PREFSYSMAIL))) {
				playerDTO.setPrefsysmail(true);
			} else {
				playerDTO.setPrefsysmail(false);
			}

			if (ON.equals(request.getParameter(PREFCONTACTINFO))) {
				playerDTO.setPrefContactInfo(true);
			} else {
				playerDTO.setPrefContactInfo(false);
			}

			HashMap<Integer, String> map = new HashMap<Integer, String>();
			Set<PlayerimDTO> playerim = new HashSet<PlayerimDTO>();
			map.put(AIM_ID, profileForm.getAim());
			map.put(YAHOO_ID, profileForm.getYahoo());
			map.put(MSN_ID, profileForm.getMsn());
			map.put(SKYPE_ID, profileForm.getSkype());
			/**
			 * If u want to add more im network than add only above set integer
			 * as defined in imnetwork table.
			 */
			Set<Integer> temp = map.keySet();
			Iterator<Integer> it = temp.iterator();
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(XML_PLAYER, playerDTO);
			// AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new
			// StringBuffer(this.getClass().getName()).append(DASHES).append(GETTING).append(BLANK).append(PLAYER_IM).append(DASHES).append(request.getSession().getId()).toString(),
			// Level.INFO);
			List<PlayerimDTO> tempimlist = PlayerImServiceDelegator
					.getList(searchCriteria);
			Iterator<PlayerimDTO> imit = null;
			if (tempimlist != null && tempimlist.size() > G4GConstants.ZERO) {
				imit = tempimlist.iterator();
			}
			while (it.hasNext()) {
				int k = it.next();
				PlayerimDTO playerims = new PlayerimDTO();
				playerims.setImnetwork(new ImnetworkDTO(k));
				playerims.setPlayerimtag(map.get(k));
				playerims.setLastupdatedate(DataUtil.todayGMT());
				if (imit != null && imit.hasNext()) {
					PlayerimDTO playerimm = imit.next();
					playerims.setId(playerimm.getId());
					playerims.setCreationdate(playerimm.getCreationdate());
				}
				playerim.add(playerims);
			}
			playerDTO.setPlayerims(playerim);
			for (PlayerimDTO element : playerDTO.getPlayerims()) {
				if (element.getPlayerimtag() == null) {
					element.setPlayerimtag(NONE);
				}
				if (element.getCreationdate() == null) {
					element.setCreationdate(DataUtil.todayGMT());
				}
			}

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(DASHES)
							.append(UPDATING).append(BLANK).append(PLAYER_IM)
							.append(BLANK).append(AND).append(BLANK).append(
									PLAYERDTO).append(DASHES).append(
									request.getSession().getId()).toString(),
					Level.INFO);

			PlayerServiceDelegator.update(playerDTO);
		}
		request.setAttribute(SUCCESS, SUCCESS);
		return mapping.findForward(SUCCESS);
	}

	/**
	 * Populate.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form
	 */
	public static void populate(HttpServletRequest request, EditProfileForm form) {

		/* Code to populate Edit profile */
		PlayerDTO playerDTO = DataUtil.getUserDTO(request).getPlayerDTO();
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(PLAYER, playerDTO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(EditProfileAction.class.getClass().getName())
						.append(DASHES).append(GETTING).append(BLANK).append(
								PLAYER_NETWORK).append(BLANK).append(DASHES)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		List<PlayernetworkDTO> networkPlayernetworkDTO = PlayerNetworkServiceDelegator
				.getList(searchCriteria);

		for (PlayernetworkDTO aNetworkPlayernetworkDTO : networkPlayernetworkDTO) {
			if (aNetworkPlayernetworkDTO.getNetwork() != null) {
				if (aNetworkPlayernetworkDTO.getNetwork().getId() == NETWORK_XBOX_LIST_KEY) {
					form.setXbox(DataUtil.getString(NETWORK_XBOX_LIST_KEY));
					form.setXboxLiveTag(aNetworkPlayernetworkDTO
							.getPlayernetworktag());

				}
				if (aNetworkPlayernetworkDTO.getNetwork().getId() == NETWORK_XBOX360_LIST_KEY) {
					form.setXbox360(DataUtil
							.getString(NETWORK_XBOX360_LIST_KEY));
					form.setXboxLiveTag(aNetworkPlayernetworkDTO
							.getPlayernetworktag());
				}
				if (aNetworkPlayernetworkDTO.getNetwork().getId() == NETWORK_PS2_ID_LIST_KEY) {
					form.setPs2(DataUtil.getString(NETWORK_PS2_ID_LIST_KEY));
					form.setPsNetTag(aNetworkPlayernetworkDTO
							.getPlayernetworktag());
				}
				if (aNetworkPlayernetworkDTO.getNetwork().getId() == NETWORK_PS3_ID_LIST_KEY) {
					form.setPs3(DataUtil.getString(NETWORK_PS3_ID_LIST_KEY));
					form.setPsNetTag(aNetworkPlayernetworkDTO
							.getPlayernetworktag());
				}
			}
		}
	}
}