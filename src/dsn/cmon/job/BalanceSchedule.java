package dsn.cmon.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import dsn.cmon.dao.CronSchedulerDAO;
import dsn.cmon.dao.CronSchedulerDAOImpl;
import dsn.cmon.util.GlobalVariable;

public class BalanceSchedule {
	Scheduler scheduler = null;
	static Logger log = Logger.getLogger("JOB");
	private CronSchedulerDAO dao;

	public BalanceSchedule() {
		// TODO Auto-generated constructor stub
		dao = new CronSchedulerDAOImpl();
	}

	public boolean start() {
		boolean result = false;

		try {
			// Setup the Job class and the Job group
			JobDetail job = newJob(BalanceJob.class).withIdentity("BalanceJobJobDetail", "Group").build();

			// Create a Trigger that fires.
			Trigger trigger = newTrigger().withIdentity("BalanceJobTrigger", "Group").withSchedule(
					CronScheduleBuilder.cronSchedule(dao.getCronSchedulerByJobName("BalanceJob").getCronexpression()))
					.build();

			// Setup the Job and Trigger with Scheduler & schedule jobs
			scheduler = new StdSchedulerFactory().getScheduler();

			// scheduler.start();
			try {
				scheduler.scheduleJob(job, trigger);
				GlobalVariable.STOP_BALANCE_LOOP=false;
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
			scheduler.deleteJob(JobKey.jobKey("BalanceJobJobDetail", "Group"));
			GlobalVariable.STOP_BALANCE_LOOP=true;

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
			if (scheduler.getJobDetail(JobKey.jobKey("BalanceJobJobDetail", "Group")) != null)
				result = true;

		} catch (SchedulerException se) {
			log.error("StackTrace: " + se.getStackTrace());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
		}
		return result;
	}

}
