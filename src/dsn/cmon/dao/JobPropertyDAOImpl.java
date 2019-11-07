package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.model.JobProperty;
import dsn.cmon.util.DBUtil;

public class JobPropertyDAOImpl implements JobPropertyDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public JobPropertyDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public void addJobProperty(JobProperty jobProperty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteJobProperty(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateJobProperty(JobProperty jobProperty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JobProperty> getAllJobProperties() {
		// TODO Auto-generated method stub
		List<JobProperty> listJobProperties = new ArrayList<JobProperty>();
		
		try{
            String query = "select * from bman_job_property";

			statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            	JobProperty jobProperty = new JobProperty();
            	jobProperty.setId(resultSet.getLong("id"));
            	jobProperty.setJobName(resultSet.getString("job_name"));
            	jobProperty.setJobStatus(resultSet.getInt("job_status"));
            	jobProperty.setAliasName(resultSet.getString("alias_name"));
            	jobProperty.setStatusDescription(resultSet.getString("status_description"));
            	jobProperty.setSignColors(resultSet.getString("sign_colors"));
            	jobProperty.setActionUrl(resultSet.getString("action_url"));
            	listJobProperties.add(jobProperty);

            }

		} catch(SQLException se){
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
		return listJobProperties;
	}

	@Override
	public JobProperty getJobPropertyById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobProperty getJobPropertyByJobNameStatus(String jobName, int jobStatus) {
		// TODO Auto-generated method stub
		JobProperty jobProperty = new JobProperty();
		
		try{
            String query = "select * from bman_job_property where job_name=? and job_status=?";
            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, jobName);
            preparedStatement.setInt(2, jobStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	jobProperty.setId(resultSet.getLong("id"));
            	jobProperty.setJobName(resultSet.getString("job_name"));
            	jobProperty.setJobStatus(resultSet.getInt("job_status"));
            	jobProperty.setAliasName(resultSet.getString("alias_name"));
            	jobProperty.setStatusDescription(resultSet.getString("status_description"));
            	jobProperty.setSignColors(resultSet.getString("sign_colors"));
            	jobProperty.setActionUrl(resultSet.getString("action_url"));

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
		return jobProperty;
	}

	public static void main(String[] args0){
		JobPropertyDAOImpl jobPropertyDAOImpl = new JobPropertyDAOImpl();
		List<JobProperty> list = jobPropertyDAOImpl.getAllJobProperties();
		
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getId() + " - " +
					list.get(i).getJobName() + "-" +
					list.get(i).getJobStatus() + "-" + 
					list.get(i).getAliasName() + "-" +
					list.get(i).getStatusDescription() + "-" +
					list.get(i).getSignColors() + "-" +
					list.get(i).getActionUrl());
			
		}
		
		System.out.println("===============================");
		
		String jn = "BalanceJob";
		int sts = 0;

		JobProperty theJob = jobPropertyDAOImpl.getJobPropertyByJobNameStatus(jn,sts);
		
		System.out.println(theJob.getAliasName());

	}

}
