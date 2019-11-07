package dsn.cmon.dao;

import dsn.cmon.model.SimpleScheduler;

public interface SimpleSchedulerDAO {
	public SimpleScheduler getSimpleSchedulerByJobName(String jobName);

}
