package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.model.DataStatic;
import dsn.cmon.util.DBUtil;

public class DataStaticDAOImpl implements DataStaticDAO{
	static Logger log = Logger.getLogger("DATA");
	private Connection conn;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;

	public DataStaticDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public void addDataStatic(List<DataStatic> datastatics) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into bman_data_static (date_time, ext_ref, participant_id, participant_name, investor_name, sid_no, acct_no, bank_acct_no, bank_code, activity_date, activity, ack_status, ack_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(query);
			
			int i = 0;
			int[] result = null;
			for (DataStatic datastatic : datastatics){
				preparedStatement.setTimestamp(1, datastatic.getDatetime());
				preparedStatement.setString(2, datastatic.getExtref());
				preparedStatement.setString(3, datastatic.getParticipantid());
				preparedStatement.setString(4, datastatic.getParticipanname());
				preparedStatement.setString(5, datastatic.getInvestorname());
				preparedStatement.setString(6, datastatic.getSidno());
				preparedStatement.setString(7, datastatic.getAcctno());
				preparedStatement.setString(8, datastatic.getBankacctno());
				preparedStatement.setString(9, datastatic.getBankcode());
				preparedStatement.setString(10, datastatic.getActivitydate());
				preparedStatement.setString(11, datastatic.getActivity());
				preparedStatement.setString(12, datastatic.getAckstatus());
				preparedStatement.setDate(13, datastatic.getAcktime());

				preparedStatement.addBatch();
				log.info("[DATA STATIC] ACCTNO " + datastatic.getAcctno() + "; EXTREF " + datastatic.getExtref() +  " to batch processing.");

				i++;

				if (i % 1000 == 0 || i == datastatics.size()) {
					result = preparedStatement.executeBatch(); // Execute every
																// 1000 items.
					log.info("[DATA STATIC] Number of rows inserted: " + result.length);
					conn.commit();
				}

			}
			
		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();

		} catch (Exception e) {
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
		}

	}

	@Override
	public int getCountRec() {
		// TODO Auto-generated method stub
		int total = 0;
		try{
			String query = "select count(id) from bman_data_static";

			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next())
				total = resultSet.getInt(1);
			
		} catch (Exception e) {
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {	}
		}			
		return total;	
		}

}
