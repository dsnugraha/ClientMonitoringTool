package dsn.cmon.model;

public class CronScheduler {
	private String jobname;
	private String cronexpression;
	private String aliasName;
	
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getCronexpression() {
		return cronexpression;
	}
	public void setCronexpression(String cronexpression) {
		this.cronexpression = cronexpression;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

}
