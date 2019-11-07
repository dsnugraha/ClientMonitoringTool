package dsn.cmon.dao;

import java.util.List;

import dsn.cmon.model.CronScheduler;

public interface CronSchedulerDAO {
	public boolean updateCronScheduler(CronScheduler cronScheduler);
	public List<CronScheduler> getCronScheduler();
	public CronScheduler getCronSchedulerByJobName(String jobName);

}