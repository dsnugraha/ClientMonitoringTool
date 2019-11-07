package dsn.cmon.job;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import dsn.cmon.dao.SimpleSchedulerDAO;
import dsn.cmon.dao.SimpleSchedulerDAOImpl;

public class HeartbeatSchedule {
	Scheduler scheduler = null;
	static Logger log = Logger.getLogger("HEARTBEAT");
	private SimpleSchedulerDAO dao;

	public HeartbeatSchedule() {
		// TODO Auto-generated constructor stub
		dao = new SimpleSchedulerDAOImpl();
	}

	public boolean start() {
		boolean result = false;

		try {
			// Setup the Job class and the Job group
			JobDetail job = newJob(HeartbeatJob.class).withIdentity("HeartbeatJobJobDetail", "Group").build();

			// Create a Trigger that fires.		
			Trigger trigger = newTrigger()
					.withIdentity("HeartbeatJobTrigger", "Group")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(dao.getSimpleSchedulerByJobName("HeartbeatJob").getIntervaljob().intValue())
							.repeatForever())
					.build();


			// Setup the Job and Trigger with Scheduler & schedule jobs
			scheduler = new StdSchedulerFactory().getScheduler();

			// scheduler.start();
			try {
				scheduler.scheduleJob(job, trigger);
				result = true;
			} catch (ObjectAlreadyExistsException oae) {
				log.warn(job.getKey() + " already started.");
				log.error("StackTrace: " + oae.getStackTrace());
				log.error("Message: " + oae.getMessage());
			} 

		} catch (SchedulerException se) {
			log.error("StackTrace: " + se.getStackTrace());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
		} 

		return result;
	}

	public void stop() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.deleteJob(JobKey.jobKey("HeartbeatJobJobDetail", "Group"));

		} catch (SchedulerException se) {
			log.error("StackTrace: " + se.getStackTrace());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
		}
	}

	public boolean isStarted() {
		boolean result = false;

		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			if (scheduler.getJobDetail(JobKey.jobKey("HeartbeatJobJobDetail", "Group")) != null)
				result = true;

		} catch (SchedulerException se) {
			log.error("StackTrace: " + se.getStackTrace());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
		}
		return result;
	}

}
