/**
 * 
 */
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.EXPIRE_DATE;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.NOTIFICATIONQUEUEDTO_LIST_SEARCH_CRITERIA;
import static com.g4g.utils.G4GConstants.RECIPIENT_PLAYER;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.VIEW_DATE;
import static com.g4g.utils.G4GConstants.ZERO;

import java.util.List;

import org.apache.log4j.Level;

import com.g4g.delegator.NotificationQueueServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.NotificationqueueDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;

//$ANALYSIS-IGNORE
/**
 * @author jigartr
 * 
 */
@SuppressWarnings({"CloneDoesntCallSuperClone"})
public class UserAlertAjaxImpl {
	/**
	 * Instantiates a new avatar ajax impl.
	 */
	private UserAlertAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned.
	 * 
	 * @return the object
	 * 
	 * @throws CloneNotSupportedException
	 *             the clone not supported exception
	 */
	@Override
	public UserAlertAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public static String getAlert(int playerId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(UserAlertAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);

		String message = NONE;

		SearchListCriteria searchListCriteria = new SearchListCriteria();
		PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(playerId);
		if(playerDTO==null){
			playerDTO = new PlayerDTO(playerId);
		}
		searchListCriteria.setAttribute(RECIPIENT_PLAYER, new Object[] {playerDTO , SearchListCriteria.EQ });
		searchListCriteria.setAttribute(EXPIRE_DATE, new Object[] {DataUtil.todayGMT(), SearchListCriteria.GE });
		searchListCriteria.setAttribute(VIEW_DATE, new Object[] {null, SearchListCriteria.ISNULL });
		List<NotificationqueueDTO> notificationqueueList = NotificationQueueServiceDelegator.getList(searchListCriteria);
		AuditUtil.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(UserAlertAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD)
								.append(DataUtil.getCallingMethod())
								.append(CURRENTMETHOD)
								.append(DataUtil.getCurrentMethod())
								.append(DASHES)
								.append(
										NOTIFICATIONQUEUEDTO_LIST_SEARCH_CRITERIA)
								.append(notificationqueueList.size())
								.toString(), Level.INFO);
		if (notificationqueueList.size() > ZERO) {
			NotificationqueueDTO notificationqueueDTO = notificationqueueList
					.get(ZERO);
			message = notificationqueueDTO.getBody();

			notificationqueueDTO.setViewdate(DataUtil.todayGMT());
			try {
				NotificationQueueServiceDelegator.update(notificationqueueDTO);
			} catch (Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(UserAlertAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(exception)
								.toString(), Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(UserAlertAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
		return message;
	}
}
