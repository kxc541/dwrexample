package com.g4g.utils;

import gnu.trove.THashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.PlayerStatus;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * @author jigar mistry
 * It implements org.quartz.Job interface
 * 
 */
public class UpdateSessionValueJob implements Job {
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$
	private static final String URL = G4GProperties
			.getProperty(PropertiesConstants.G4G_NOTIFICATION_PATH);

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@SuppressWarnings(UNCHECKED)
	public void execute(JobExecutionContext cntxt) throws JobExecutionException {
		List<PlayerStatus> playerStatus = PlayerServiceDelegator.usersOnline();
		JobDataMap dataMap = cntxt.getJobDetail().getJobDataMap();
		ServletContext servletContext = (ServletContext) dataMap.get("context");
		
		Map<String, Boolean> onlineUsers = (THashMap<String, Boolean>) servletContext.getAttribute(G4GOriginSession.ONLINE_USERS);
		int totalPlayers = playerStatus.size();
		for (int index = 0; index < totalPlayers; index++) {
			try {
				PlayerStatus player = (PlayerStatus) playerStatus.get(index);
				onlineUsers.put(player.getScreenName(), Boolean.valueOf(player.isOnline()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		servletContext.setAttribute(G4GOriginSession.ONLINE_USERS, onlineUsers);
	}
}
