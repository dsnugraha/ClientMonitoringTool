package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dsn.cmon.model.SimpleScheduler;
import dsn.cmon.util.DBUtil;

public class SimpleSchedulerDAOImpl implements SimpleSchedulerDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public SimpleSchedulerDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public SimpleScheduler getSimpleSchedulerByJobName(String jobName) {
		// TODO Auto-generated method stub
		SimpleScheduler simpleScheduler = new SimpleScheduler();
		try {
			String query = "select * from bman_simple_scheduler where job_name=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, jobName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				simpleScheduler.setJobname(resultSet.getString("job_name"));
				simpleScheduler.setIntervaljob(resultSet.getLong("interval_job"));
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
		return simpleScheduler;	}

}
