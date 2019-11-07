package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.model.StatementBroker;
import dsn.cmon.util.DBUtil;

public class StatementBrokerDAOImpl implements StatementBrokerDAO {
	static Logger log = Logger.getLogger("DATA");
	private Connection conn;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;

	public StatementBrokerDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public void addStatementBroker(List<StatementBroker> statementbrokers) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into bman_statement (date_time, ext_ref, seq_num, acct_no, currency_code, "
					+ "val_date, open_bal, trx_type, dc, cash_val, description, close_bal, acct_note, "
					+ "participant_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(query);
			
			int i = 0;
			int[] result = null;
			for(StatementBroker statementbroker : statementbrokers){
				preparedStatement.setTimestamp(1, statementbroker.getDatetime());
				preparedStatement.setString(2, statementbroker.getExtref());
				preparedStatement.setInt(3, statementbroker.getSeqnum());
				preparedStatement.setString(4, statementbroker.getAcctno());
				preparedStatement.setString(5, statementbroker.getCurrencycode());
				preparedStatement.setInt(6, statementbroker.getValdate());
				preparedStatement.setBigDecimal(7, statementbroker.getOpenbal());
				preparedStatement.setString(8, statementbroker.getTrxtype());
				preparedStatement.setString(9, statementbroker.getDc());
				preparedStatement.setBigDecimal(10, statementbroker.getCashval());
				preparedStatement.setString(11, statementbroker.getDescription());
				preparedStatement.setBigDecimal(12, statementbroker.getClosebal());
				preparedStatement.setString(13, statementbroker.getAcctnote());
				preparedStatement.setString(14, statementbroker.getParticipantid());
				
				preparedStatement.addBatch();
				log.info("[STATEMENT] ACCTNO " +  statementbroker.getAcctno() + "; EXTREF " + statementbroker.getExtref() + " to batch processing.");
				
				i++;

				if (i % 1000 == 0 || i == statementbrokers.size()) {
					result = preparedStatement.executeBatch(); // Execute every
																// 1000 items.
					log.info("[STATEMENT] Number of rows inserted: " + result.length);
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
			String query = "select count(id) from bman_statement";

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
