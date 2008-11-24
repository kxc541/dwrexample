/**********************************************************
 * MessageCenterAction.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.FriendsServiceDelegator;
import com.g4g.delegator.MessageServiceDelegator;
import com.g4g.dto.MessageDTO;
import com.g4g.dto.NotificationtypeDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.MessageCenterForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The MessageCenterAction action class displays the list of messages available
 * in messageCenter tab. It uses delegator methods to use the services from
 * message center.<br>
 * XDoclet definition:.
 * 
 * @struts.action path="/messageCenter" name="messageCenterForm" scope="request"
 * @struts.action-forward name="success" path="/WebContent/messageCenter.jsp"
 * @author Jigar Mistry
 */
public class MessageCenterAction extends BaseAction {

	private static final String STATIC_ACCESS = "static-access"; //$NON-NLS-1$
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * The display method displays the messages for new messages, sent messages
	 * friends requests, new messages can be match confirmation, incoming
	 * challenges or any of the available from the 12 types of messages.<br>
	 * The 12 types of message are: <br>
	 * 1. Message<br>
	 * 2. Clan<br>
	 * 3. Challenge<br>
	 * 4. ChallengeResult<br>
	 * 5. Friend Request<br>
	 * 6. WorldGaming<br>
	 * 7. MatchConfirm<br>
	 * 8. TournamentJoin<br>
	 * 9. CashConfirm<br>
	 * 10. MatchResults<br>
	 * 11. TournamentResults<br>
	 * 12. UpcomingMatch<br>
	 * 
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings( { UNCHECKED, STATIC_ACCESS })
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,String operation, HttpServletRequest request,HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append(G4GConstants.CALLINGMETHOD) .append( DataUtil.getCallingMethod()) .append( G4GConstants.DISPLAY_STARTS ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString(),Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(
							request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		
		MessageCenterForm messageCenterForm = (MessageCenterForm) form;
		PlayerDTO playerDTO  ;
		int userId  ;
        UserDTO userDTO =DataUtil.getUserDTO(request);
        playerDTO = userDTO.getPlayerDTO();
        userId = playerDTO.getId();

		SearchListCriteria searchCriteria = new SearchListCriteria();
		// Friends List
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer( this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.GET_PLAYERFRIENDS_LIST ).append(
							request.getSession().getId()).toString());
		
		List<PlayerDTO> friendsList = new ArrayList<PlayerDTO>();
		if (FriendsServiceDelegator.getFriends(userId).size() > G4GConstants.ZERO) {
			friendsList = FriendsServiceDelegator.getFriends(userId);
		}

		// Sent Messages
		searchCriteria.removeAllAttribute();
		NotificationtypeDTO notificationtypeDTO = new NotificationtypeDTO();
		notificationtypeDTO.setId(G4GConstants.MSG_MESSAGE);
		searchCriteria.setAttribute(G4GConstants.PLAYERBYFROMPLAYERID,new Object[] { playerDTO, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,new Object[] { notificationtypeDTO, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.IS_ARCHIEVED_BY_SENDER,new Object[] { false, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.IS_DELETED_BY_SENDER,new Object[] { false, SearchListCriteria.EQ });

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer( this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.GET_PLAYERMESSAGE_LIST ).append(
							request.getSession().getId()).toString());
		
		List<MessageDTO> sentMessageList = MessageServiceDelegator.getList(searchCriteria);
		
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.SENT_MESSAGE_LIST ).append( sentMessageList.size()).append(
							request.getSession().getId()).toString());

		// New Messages
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYERBYTOPLAYERID,new Object[] { playerDTO, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.IS_ARCHIEVED_BY_RECIEVER,new Object[] { false, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.IS_DELETED_BY_RECIEVER,new Object[] { false, SearchListCriteria.EQ });
		searchCriteria.setAttribute(G4GConstants.IS_READ, new Object[] { false,SearchListCriteria.EQ });

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.GETNEW_MESSAGE_LIST ).append(
							request.getSession().getId()).toString());
		List<MessageDTO> newMessageList = MessageServiceDelegator.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.NEW_MESSAGE_LISTSIZE ).append( newMessageList.size()).append(
							request.getSession().getId()).toString());
		messageCenterForm.setFriends(friendsList);
		messageCenterForm.setSentMessages(sentMessageList.size());
		messageCenterForm.setNewMessages(newMessageList.size());

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method in message center sends the composed message, Friends
	 * request, challenge.
	 * 
	 * @throws Exception
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName() ).append( CURRENTMETHOD ).append( DataUtil.getCurrentMethod()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS ).append( DASHES ).append( request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		MessageCenterForm messageCenterForm = (MessageCenterForm) form;

		if (G4GConstants.SEND.equalsIgnoreCase(operation)) {
			
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
							 G4GConstants.ADD_MESSAGE ).append( 
								request.getSession().getId()).toString());
			
			MessageDTO dto = (MessageDTO) messageCenterForm.getDTO();
			
			MessageServiceDelegator.add(dto);
		}

		return mapping.findForward(G4GConstants.SUCCESS);
	}
}