/**
 *
 */
package com.g4g.plugin;

import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.ee.servlet.QuartzInitializerServlet;
import org.quartz.impl.StdSchedulerFactory;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.PopupNotificationJob;
import com.g4g.utils.UpdateSessionValueJob;

/**
 * @author jigartr
 *
 */
public class G4GScheduler implements PlugIn {
	/**
	 * @param actionServlet
	 * @param module
	 */
	@SuppressWarnings(G4GConstants.STATICACCESS)
	public void init(ActionServlet actionServlet, ModuleConfig module) {

		// Retrieve the ServletContext
		ServletContext ctx = actionServlet.getServletContext();
		// The Quartz Scheduler
		Scheduler scheduler = null;
		Scheduler widgetScheduler = null;

		// Retrieve the factory from the ServletContext.
		// It will be put there by the Quartz Servlet
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx
				.getAttribute(QuartzInitializerServlet.QUARTZ_FACTORY_KEY);

		StdSchedulerFactory widgetFactory = (StdSchedulerFactory) ctx
				.getAttribute(QuartzInitializerServlet.QUARTZ_FACTORY_KEY);

		try {
			// Retrieve the scheduler from the factory
			scheduler = factory.getScheduler();
			widgetScheduler = widgetFactory.getScheduler();

			JobDetail jobDetail = new JobDetail(G4GConstants.NOTIFICATIONJOB,
					Scheduler.DEFAULT_GROUP, PopupNotificationJob.class);
			JobDetail widgeJobDetail = new JobDetail(G4GConstants.UPDATESESSIONJOB,
					Scheduler.DEFAULT_GROUP, UpdateSessionValueJob.class);

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(G4GConstants.CONTEXT, ctx);

			widgeJobDetail.setJobDataMap(jobDataMap);

			SimpleTrigger simpleTrigger = new SimpleTrigger(
					G4GConstants.NOTIFICATIONJOB, Scheduler.DEFAULT_GROUP,
					new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,
					15L * 60L * 1000L);
			SimpleTrigger widgetTrigger = new SimpleTrigger(
					G4GConstants.UPDATESESSIONJOB, Scheduler.DEFAULT_GROUP,
					new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY,
					1L * 60L * 1000L);

			scheduler.scheduleJob(jobDetail, simpleTrigger);
			widgetScheduler.scheduleJob(widgeJobDetail, widgetTrigger);

			scheduler.start();
			widgetScheduler.start();
		} catch (SchedulerException schedulerException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					schedulerException);
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
