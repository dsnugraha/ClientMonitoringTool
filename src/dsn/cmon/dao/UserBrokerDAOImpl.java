package dsn.cmon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import dsn.cmon.model.UserBroker;
import dsn.cmon.util.DBUtil;

public class UserBrokerDAOImpl implements UserBrokerDAO {
	static Logger log = Logger.getLogger("APP");
	private Connection conn;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public UserBrokerDAOImpl() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	@Override
	public UserBroker getUserBroker() {
		// TODO Auto-generated method stub
		UserBroker userBroker = new UserBroker();
		try {
			String query = "select username, password, serialnum from bman_users_broker where activation=1 limit 1";

			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				userBroker.setUsername(resultSet.getString("username"));
				userBroker.setPassword(resultSet.getString("password"));
				userBroker.setSerialnum(resultSet.getString("serialnum"));
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
			} catch (Exception e) {
			}
		}
		return userBroker;
	}

	@Override
	public int getCountRec() {
		// TODO Auto-generated method stub
		int total = 0;
		try {
			String query = "select count(username) from bman_users_broker";

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

	@Override
	public void upsertUserBroker(UserBroker userBroker) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into bman_users_broker(username, password, serialnum, activation) values(?,?,?,?) on duplicate key update username =?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userBroker.getUsername());
			preparedStatement.setString(2, userBroker.getPassword());
			preparedStatement.setString(3, userBroker.getSerialnum());
			preparedStatement.setInt(4, userBroker.getActivation());
			preparedStatement.setString(5, userBroker.getUsername());
			preparedStatement.executeUpdate();

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

}
