package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.model.CronScheduler;
import dsn.cmon.util.DBUtil;

public class CronSchedulerDAOImpl implements CronSchedulerDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public CronSchedulerDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public boolean updateCronScheduler(CronScheduler cronScheduler) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			String query = "update bman_cron_scheduler set cron_expression=? where job_name=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, cronScheduler.getCronexpression());
			preparedStatement.setString(2, cronScheduler.getJobname());
			preparedStatement.executeUpdate();
			result = true;

		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();

		} finally{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {	}
		}
		
		return result;
	}

	@Override
	public List<CronScheduler> getCronScheduler() {
		// TODO Auto-generated method stub
		List<CronScheduler> listcronScheduler = new ArrayList<CronScheduler>();
		try {
			String query = "select cs.job_name as job_name, jp.alias_name as alias_name, "
					+ "cs.cron_expression as cron_expression from bman_cron_scheduler cs, bman_job_property jp "
					+ "where cs.job_name=jp.job_name group by cs.job_name";

			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				CronScheduler cronScheduler = new CronScheduler();
				cronScheduler.setJobname(resultSet.getString("job_name"));
				cronScheduler.setCronexpression(resultSet.getString("cron_expression"));
				cronScheduler.setAliasName(resultSet.getString("alias_name"));
				listcronScheduler.add(cronScheduler);
			}

		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
			return null;
			
		} catch (Exception e) {
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {	}
		}		
		return listcronScheduler;

	}

	@Override
	public CronScheduler getCronSchedulerByJobName(String jobName) {
		// TODO Auto-generated method stub
		CronScheduler cronScheduler = new CronScheduler();
		try {
			String query = "select * from bman_cron_scheduler where job_name=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, jobName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cronScheduler.setJobname(resultSet.getString("job_name"));
				cronScheduler.setCronexpression(resultSet.getString("cron_expression"));
			}

		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();
			return null;
			
		} catch (Exception e) {
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {	}
		}			
		return cronScheduler;
	}
	
}
