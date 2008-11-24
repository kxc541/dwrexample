package com.g4g.helper;

/**
 * 
 * @author Jigar Mistry
 */

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class GameValidatorSchedule {
	/**
	 * Constructor.
	 *  
	 * @param jobName
	 * 			Name of the job defined in Scheduler.
	 * 
	 * @param triggerName
	 * 			Name of the trigger defined in Scheduler.
	 * 
	 * @param sleepTime
	 * 			Sleep time between each call. Ex. 10L*1000L (10 Seconds)
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public GameValidatorSchedule(String jobName, String triggerName, Long sleepTime)throws Exception{
		  SchedulerFactory schedulerFactory =new StdSchedulerFactory();
		  Scheduler scheduler = schedulerFactory.getScheduler();
		  
		  scheduler.start();
		  
		  JobDetail jobDetail = new JobDetail(jobName, scheduler.DEFAULT_GROUP, GameValidatorJob.class);
		  SimpleTrigger simpleTrigger = new SimpleTrigger(triggerName, scheduler.DEFAULT_GROUP, new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY, sleepTime);
		  
		  scheduler.scheduleJob(jobDetail, simpleTrigger);
	  }
}
