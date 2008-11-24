package com.g4g.utils;

import java.util.List;

import org.apache.log4j.Level;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.notification.UpcomingMatchNotification;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * @author jigar mistry
 * It implements org.quartz.Job interface
 * 
 */
public class PopupNotificationJob implements Job {
	private static final String URL = G4GProperties.getProperty(PropertiesConstants.G4G_NOTIFICATION_PATH);

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	
	public void execute(JobExecutionContext cntxt) throws JobExecutionException {
		List<TwoplayermatchDTO> matchList = TwoPlayerMatchServiceDelegator
				.getUpcomingMatches();
		int totalMatches = matchList.size();
	for (int index = G4GConstants.ZERO; index < totalMatches; index++) {
		TwoplayermatchDTO twoplayermatchDTO = matchList
					.get(index);
			UpcomingMatchNotification upcomingMatchNotification = new UpcomingMatchNotification(
					twoplayermatchDTO.getPlayeroneid().getId(), twoplayermatchDTO
							.getPlayertwoid().getId(), twoplayermatchDTO.getId(), URL,
					twoplayermatchDTO.getScheduledstartdate());
			try {
				NotificationServiceDelegator.sendNotification(upcomingMatchNotification, null);
			}
			catch (Exception exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,exception , Level.ERROR);
			}

			upcomingMatchNotification = new UpcomingMatchNotification(
					twoplayermatchDTO.getPlayertwoid().getId(), twoplayermatchDTO
							.getPlayeroneid().getId(), twoplayermatchDTO.getId(), URL,
					twoplayermatchDTO.getScheduledstartdate());
			try {
				NotificationServiceDelegator.sendNotification(upcomingMatchNotification, null);
			}
			catch (Exception exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,exception , Level.ERROR);
			}
		} 
	}
}
