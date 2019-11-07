package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import dsn.cmon.model.Balance;
import dsn.cmon.util.DBUtil;

public class BalanceDAOImpl implements BalanceDAO {
	static Logger log = Logger.getLogger("DATA");
	private Connection conn;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public BalanceDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public void addBalance(Balance balance) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into bman_balance (acct_no, date_time, bank_code, investor_name, currency_code, balance, val_date, time_stamp, investor_id, sub_account, participant_id) values (?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, balance.getAcctno());
			preparedStatement.setTimestamp(2, balance.getDatetime());
			preparedStatement.setString(3, balance.getBankcode());
			preparedStatement.setString(4, balance.getInvestorname());
			preparedStatement.setString(5, balance.getCurrencycode());
			preparedStatement.setBigDecimal(6, balance.getBalance());
			preparedStatement.setInt(7, balance.getValdate());
			preparedStatement.setBigDecimal(8, balance.getTimestamp());
			preparedStatement.setString(9, balance.getInvestorid());
			preparedStatement.setString(10, balance.getSubaccount());
			preparedStatement.setString(11, balance.getParticipantid());
			preparedStatement.executeUpdate();

			log.info("[BALANCE] ACCTNO " + balance.getAcctno() + " was inserted.");

		} catch (SQLException se) {
			log.error("[BALANCE] ACCTNO " + balance.getAcctno() + " was not inserted.");
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();

		} catch (Exception e) {
			log.error("[BALANCE] ACCTNO " + balance.getAcctno() + " was not inserted.");
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
	public void updateBalance(Balance balance) {
		// TODO Auto-generated method stub
		try {
			String query = "update bman_balance set date_time=?, bank_code=?, investor_name=?, currency_code=?, balance=?, val_date=?, time_stamp=?, investor_id=?, sub_account=?, participant_id=?) where acct_no=?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setTimestamp(1, balance.getDatetime());
			preparedStatement.setString(2, balance.getBankcode());
			preparedStatement.setString(3, balance.getInvestorname());
			preparedStatement.setString(4, balance.getCurrencycode());
			preparedStatement.setBigDecimal(5, balance.getBalance());
			preparedStatement.setInt(6, balance.getValdate());
			preparedStatement.setBigDecimal(7, balance.getTimestamp());
			preparedStatement.setString(8, balance.getInvestorid());
			preparedStatement.setString(9, balance.getSubaccount());
			preparedStatement.setString(10, balance.getParticipantid());
			preparedStatement.setString(11, balance.getAcctno());
			preparedStatement.executeUpdate();

			log.info("[BALANCE] ACCTNO " + balance.getAcctno() + " was updated.");

		} catch (SQLException se) {
			log.error("[BALANCE] ACCTNO " + balance.getAcctno() + " was not updated.");
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();

		} catch (Exception e) {
			log.error("[BALANCE] ACCTNO " + balance.getAcctno() + " was not updated.");
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
	public boolean checkAccountNumber(String acctno) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			String query = "select * from bman_balance where acct_no=?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, acctno);
			resultSet = preparedStatement.executeQuery();
			result = resultSet.next();

		} catch (SQLException se) {
			log.error("SQLState: " + se.getSQLState());
			log.error("ErrorCode: " + se.getErrorCode());
			log.error("Message: " + se.getMessage());
			se.printStackTrace();

		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	@Override
	public void upsertBalance(List<Balance> balances) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into bman_balance (acct_no, date_time, bank_code, investor_name, currency_code, balance, val_date, time_stamp, investor_id, sub_account, participant_id) values (?,?,?,?,?,?,?,?,?,?,?) on duplicate key update "
					+ "date_time=?, bank_code=?, investor_name=?, currency_code=?, balance=?, val_date=?, time_stamp=?, investor_id=?, sub_account=?, participant_id=?";

			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(query);

			int i = 0;
			int[] result = null;
			for (Balance balance : balances) {
				preparedStatement.setString(1, balance.getAcctno());
				preparedStatement.setTimestamp(2, balance.getDatetime());
				preparedStatement.setString(3, balance.getBankcode());
				preparedStatement.setString(4, balance.getInvestorname());
				preparedStatement.setString(5, balance.getCurrencycode());
				preparedStatement.setBigDecimal(6, balance.getBalance());
				preparedStatement.setInt(7, balance.getValdate());
				preparedStatement.setBigDecimal(8, balance.getTimestamp());
				preparedStatement.setString(9, balance.getInvestorid());
				preparedStatement.setString(10, balance.getSubaccount());
				preparedStatement.setString(11, balance.getParticipantid());
				preparedStatement.setTimestamp(12, balance.getDatetime());
				preparedStatement.setString(13, balance.getBankcode());
				preparedStatement.setString(14, balance.getInvestorname());
				preparedStatement.setString(15, balance.getCurrencycode());
				preparedStatement.setBigDecimal(16, balance.getBalance());
				preparedStatement.setInt(17, balance.getValdate());
				preparedStatement.setBigDecimal(18, balance.getTimestamp());
				preparedStatement.setString(19, balance.getInvestorid());
				preparedStatement.setString(20, balance.getSubaccount());
				preparedStatement.setString(21, balance.getParticipantid());

				preparedStatement.addBatch();
				log.info("[BALANCE] ACCTNO " + balance.getAcctno() + " to batch processing.");

				i++;

				if (i % 1000 == 0 || i == balances.size()) {
					result = preparedStatement.executeBatch(); // Execute every
																// 1000 items.
					log.info("[BALANCE] Number of rows inserted: " + result.length);
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
		try {
			String query = "select count(acct_no) from bman_balance";

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
			} catch (Exception e) {
			}
		}
		return total;
	}

}
