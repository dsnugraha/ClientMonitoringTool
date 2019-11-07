package dsn.cmon.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.job.BalanceSchedule;
import dsn.cmon.job.DataStaticSchedule;
import dsn.cmon.job.StatementSchedule;
import dsn.cmon.model.Job;
import dsn.cmon.model.JobProperty;

public class JobDAOImpl implements JobDAO {
	static Logger log = Logger.getLogger("APP");
	private JobPropertyDAOImpl jobPropertyDAOImpl;
	private BalanceSchedule balanceSchedule;
	private StatementSchedule statementSchedule;
	private DataStaticSchedule dataStaticSchedule;

	public JobDAOImpl() {
		// TODO Auto-generated constructor stub
		jobPropertyDAOImpl = new JobPropertyDAOImpl();
		balanceSchedule = new BalanceSchedule();
		statementSchedule = new StatementSchedule();
		dataStaticSchedule = new DataStaticSchedule();

	}

	@Override
	public List<Job> getProperties() {
		// TODO Auto-generated method stub
		List<Job> listjob = new ArrayList<Job>();
		JobProperty jobProp = new JobProperty();

		try {
			Job balancejob = new Job();
			if (balanceSchedule.isStarted()) {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("BalanceJob", 1);
			} else {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("BalanceJob", 0);
			}
			balancejob.setName(jobProp.getAliasName());
			balancejob.setColor(jobProp.getSignColors());
			balancejob.setStatus(jobProp.getStatusDescription());
			balancejob.setUrl(jobProp.getActionUrl());
			listjob.add(balancejob);

			Job statementjob = new Job();
			if (statementSchedule.isStarted()) {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("StatementJob", 1);
			} else {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("StatementJob", 0);
			}
			statementjob.setName(jobProp.getAliasName());
			statementjob.setColor(jobProp.getSignColors());
			statementjob.setStatus(jobProp.getStatusDescription());
			statementjob.setUrl(jobProp.getActionUrl());
			listjob.add(statementjob);

			Job datastaticjob = new Job();
			if (dataStaticSchedule.isStarted()) {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("DataStaticJob", 1);
			} else {
				jobProp = jobPropertyDAOImpl.getJobPropertyByJobNameStatus("DataStaticJob", 0);
			}
			datastaticjob.setName(jobProp.getAliasName());
			datastaticjob.setColor(jobProp.getSignColors());
			datastaticjob.setStatus(jobProp.getStatusDescription());
			datastaticjob.setUrl(jobProp.getActionUrl());
			listjob.add(datastaticjob);

		} catch (Exception e) {
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
			return null;

		}
		return listjob;

	}

	public static void main(String[] args) {
		List<Job> jobDAOImpl = new JobDAOImpl().getProperties();
		System.out.println("+++++++++++++++++++++" + jobDAOImpl.size());
		for (int i = 0; i < jobDAOImpl.size(); i++) {
			System.out.println("Job Name " + jobDAOImpl.get(i).getName());
			System.out.println("Color " + jobDAOImpl.get(i).getColor());
			System.out.println("Status " + jobDAOImpl.get(i).getStatus());
			System.out.println("URL " + jobDAOImpl.get(i).getUrl());
			System.out.println("=================================");
		}
	}

}
